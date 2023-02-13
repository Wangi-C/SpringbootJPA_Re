package org.swclass.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //부모테이블에서 자식들을 구분짓기위해서
@Getter
@Setter
public class Book extends Item {
    private String author;
    private String isbn;
}
