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

    @OneToMany(mappedBy = "busket", fetch = FetchType.LAZY)
    private List<BusketItem> busketItems = new ArrayList<>();

}
