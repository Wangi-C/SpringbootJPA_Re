package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //===연관관계 메소드===//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    protected Orders() {

    }

    //==생성 메소드==//
    public static Orders createOrders(Member member, Delivery delivery, OrderItem... orderItems) {
        Orders orders = new Orders();
        orders.setMember(member);
        orders.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            orders.addOrderItem(orderItem);
        }
        orders.setOrderStatus(OrderStatus.ORDER);
        orders.setOrderDate(LocalDateTime.now());

        return orders;
    }

    //==비즈니스 로직==//
    /*주문 취소*/
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    /*전체 주문 가격 조회*/
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

//    public static void main(String[] args) {
//        //비즈니스 로직에서
//        Member member = new Member();
//        Orders orders = new Orders();
//
//        member.getOrders().add(orders);
//        orders.setMember(member);
//    }
}
