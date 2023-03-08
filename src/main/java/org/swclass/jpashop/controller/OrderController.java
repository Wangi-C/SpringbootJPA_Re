package org.swclass.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.domain.OrderSearch;
import org.swclass.jpashop.domain.Orders;
import org.swclass.jpashop.domain.item.Item;
import org.swclass.jpashop.exception.NotEnoughStockException;
import org.swclass.jpashop.service.ItemService;
import org.swclass.jpashop.service.MemberService;
import org.swclass.jpashop.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    // 주문 폼
    @GetMapping(value = "/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    //주문 목록 검색
    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch,
                            Model model) {
        List<Orders> orderList = orderService.findAll(orderSearch);
        model.addAttribute("orders", orderList);

        return "order/list";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId")Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }

    @PostMapping(value = "/orders/{id}/cancel")
    public String cancel(@PathVariable("id") Long orderId) {

        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }
}
