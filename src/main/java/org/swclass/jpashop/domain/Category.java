package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.item.Item;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;
    @ManyToMany
    private List<Item> items;

    @ManyToOne
    private Category parent;
}
