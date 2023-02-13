package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;
    private int orderPrice;
    private int cnt;
}
