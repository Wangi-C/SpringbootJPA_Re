package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.item.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class BusketItem {

    @Id @GeneratedValue
    @Column(name = "busketItem_id")
    private Long id;

    private Item item;
    private Busket busket;

    private int busketPrice;
    private int busketCnt;
}
