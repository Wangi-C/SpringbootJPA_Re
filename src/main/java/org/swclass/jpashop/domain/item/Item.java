package org.swclass.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.Category;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuatity;
    private List<Category> categories;
}
