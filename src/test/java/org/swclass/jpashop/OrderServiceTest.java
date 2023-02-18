package org.swclass.jpashop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.Address;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.domain.OrderStatus;
import org.swclass.jpashop.domain.Orders;
import org.swclass.jpashop.domain.item.Book;
import org.swclass.jpashop.domain.item.Item;
import org.swclass.jpashop.exception.NotEnoughStockException;
import org.swclass.jpashop.repository.OrderRepository;
import org.swclass.jpashop.service.ItemService;
import org.swclass.jpashop.service.MemberService;
import org.swclass.jpashop.service.OrderService;

import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void orderItemTest() {
        Member member = createMember();
        Item item = createBook("jpa 기본", 1000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        Orders order = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", order.getOrderStatus(), OrderStatus.ORDER);
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,order.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 1000 * 2,order.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",8, item.getStockQuatity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void exceptionTest() {
        Member member = createMember();
        Item item = createBook("jpa", 1000, 10);
        int orderCnt = 11;

        Long order = orderService.order(member.getId(), item.getId(), orderCnt);

        fail("재고가 부족합니다.");
    }

    @Test
    public void cancelTest() {
        Member member = createMember();
        Item item = createBook("jpa", 1000, 10);
        int orderCnt = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCnt);

        assertEquals("남은 재고의 갯수",8,item.getStockQuatity());
        System.out.println("남은 재고의 갯수" + item.getStockQuatity());

        orderService.cancelOrder(orderId);

        Orders order = orderRepository.findOne(orderId);
        assertEquals("취소 후 재고의 갯수", 10, item.getStockQuatity());
        assertEquals("주문상태가 cancel이다.", order.getOrderStatus(), OrderStatus.CANCEL);
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuatity(stockQuantity);
        itemService.saveItem(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("chang");
        member.setAddress(new Address("seoul", "sindorim", "1234"));
        memberService.join(member);

        return member;
    }
}
