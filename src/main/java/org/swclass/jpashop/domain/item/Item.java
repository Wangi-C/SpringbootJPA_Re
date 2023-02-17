package org.swclass.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.swclass.jpashop.domain.Category;
import org.swclass.jpashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quatity) {
        this.stockQuatity += quatity;
    }

    public void removeStock(int quatity) {
        int restQuatity = this.stockQuatity - quatity;

        if (restQuatity < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }

        this.stockQuatity = restQuatity;
    }
}
