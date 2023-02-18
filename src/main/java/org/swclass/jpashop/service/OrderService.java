package org.swclass.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.*;
import org.swclass.jpashop.domain.item.Item;
import org.swclass.jpashop.repository.ItemRepository;
import org.swclass.jpashop.repository.OrderRepository;
import org.swclass.jpashop.repository.ShopMemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ShopMemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
    * 주문
    * */
    @Transactional
    public Long order(Long memberId, Long itemId, int itemCnt) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), itemCnt);
        Orders orders = Orders.createOrders(member, delivery, orderItem);

        orderRepository.save(orders);

        return orders.getId();
    }

    /*
    * 주문 취소
    * */
    @Transactional
    public void cancelOrder(Long orderId) {
        Orders orders = orderRepository.findOne(orderId);
        orders.cancel();
    }

    /*
    * 주문 검색
    * */

}
