package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter @Setter
public class BusketItem {

    @Id @GeneratedValue
    @Column(name = "busketItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "busket_id")
    private Busket busket;

    private int busketPrice;
    private int busketCnt;

    public static BusketItem createBusketItem(Item item, int busketPrice, int busketCnt) {
        BusketItem busketItem = new BusketItem();
        busketItem.setItem(item);
        busketItem.setBusketPrice(busketPrice);
        busketItem.setBusketCnt(busketCnt);

        return busketItem;
    }

    public int getTotalPrice() {
        return getBusketPrice() * getBusketCnt();
    }
}
