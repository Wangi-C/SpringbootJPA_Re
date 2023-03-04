package org.swclass.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.swclass.jpashop.domain.form.BookForm;
import org.swclass.jpashop.domain.item.Book;
import org.swclass.jpashop.domain.item.Item;
import org.swclass.jpashop.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private static ItemService itemService;

    //상품 등록 폼
    @RequestMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    //상품 등록
    @PostMapping("/items/new")
    public String createItem(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuatity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redircet:/items";
    }

    //상품 조회 폼
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> itemList = itemService.findItems();
        model.addAttribute("items", itemList);
        return "/items/list";
    }
}
