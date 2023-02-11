package org.swclass.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.item.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;
    private List<Item> items;
    private Category parent;
}
