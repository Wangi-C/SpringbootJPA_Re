package org.swclass.jpashop;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.Busket;
import org.swclass.jpashop.domain.BusketItem;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.domain.item.Movie;
import org.swclass.jpashop.service.BusketService;
import org.swclass.jpashop.service.ItemService;
import org.swclass.jpashop.service.MemberService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BusketTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BusketService busketService;

    @Test
    @DisplayName("장바구니 담기 Test")
    @Rollback(value = false)
    public void busketAddTest() {
        // 1.item member 객체가 필요. -> member는 왜? 한명의 member는 하나의 busket을 가지고 있기 때문이다.
        // 2.busktItem 생성
        // 3.busketItem을 busket에 추가
        // 확인사항) item의 stockQuantity 양을 확인하자
        Member member = createMember();
        Movie movie = createMovie();
        Busket busket = member.getBusket();
        int busketCnt = 2;
        int busketPrice = movie.getPrice() * busketCnt;

        BusketItem busketItem = BusketItem.createBusketItem(movie, busketPrice, busketCnt);
        busket.addBusketItems(busketItem);

        busketService.saveBusket(busket);


    }

    private Movie createMovie() {
        Movie movie = new Movie();
        movie.setName("abc를 찾아서");
        movie.setPrice(20000);
        movie.setStockQuatity(10);
        movie.setActor("조완기");
        movie.setDirector("신윤남");

        itemService.saveItem(movie);
        //item의 generatedValue는 어느 시점에 생기는걸까? 1.영속성 컨테스트에서 commit시점에? 2.영속성 컨텍스트에 캐시로 들어갈때?
        return movie;
    }

    private Member createMember() {
        Member member = new Member();
        Busket busket = Busket.createBusket();

        member.setName("wangi");
        member.setBusket(busket);

        memberService.join(member);

        return member;
    }
}
