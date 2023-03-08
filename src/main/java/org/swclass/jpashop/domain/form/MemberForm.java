package org.swclass.jpashop.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    private Long memberId;
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
