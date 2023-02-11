package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Member member;
    private List<OrderItem> orderItems;
    private Delivery delivery;
    private Date orderDate;
    private OrderStatus orderStatus;
}
