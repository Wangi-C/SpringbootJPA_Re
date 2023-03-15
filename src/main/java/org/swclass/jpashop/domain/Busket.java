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

    @ManyToOne(fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

}
