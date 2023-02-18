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

    //==생성 메소드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int cnt) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCnt(cnt);

        item.removeStock(cnt);

        return orderItem;
    }

    public void cancel() {
        getItem().addStock(cnt);
        //item.addStock(cnt)로 하면 안될까?
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCnt();
    }
}
