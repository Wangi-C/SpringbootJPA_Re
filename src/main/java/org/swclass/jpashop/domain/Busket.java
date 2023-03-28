package org.swclass.jpashop.domain;

import lombok.Getter;
import org.swclass.jpashop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Busket {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "busket_id")
    private Long id;

    @OneToOne(mappedBy = "busket", fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "busket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BusketItem> busketItems = new ArrayList<>();

    //연관관계 메소드
    private void setMember(Member member) {
        this.member = member;
    }

    protected Busket() {

    }

    //생성 메소드
    public static Busket createBusket() {
        Busket busket = new Busket();

        return busket;
    }

    public void addBusketItems(BusketItem busketItem) {
        busketItems.add(busketItem);
        busketItem.setBusket(this);
    }

    public int getTotalBusketPrice() {
        int totalPrice = 0;

        for (BusketItem busketItem :
                this.busketItems) {
            totalPrice += busketItem.getTotalPrice();
        }

        return totalPrice;
    }
}
