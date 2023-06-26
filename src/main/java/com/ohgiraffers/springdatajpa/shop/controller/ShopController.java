package com.ohgiraffers.springdatajpa.shop.controller;
import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.shop.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.shop.dto.ShopDTO;
import com.ohgiraffers.springdatajpa.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{shopCode}")
    public String findShopByCode(@PathVariable int shopCode, Model model) {

        ShopDTO shop = shopService.findShopByCode(shopCode);
        model.addAttribute("shop", shop);

        return "shop/detail";
    }

    @GetMapping("/list")
    public String findShopList(@PageableDefault Pageable pageable, Model model) {
        /* page -> number, size, sort 파라미터가 Pageable 객체에 담긴다. */
        log.info("pageable : {}", pageable);

        Page<ShopDTO> shopList = shopService.findShopList(pageable);

        log.info("조회한 내용 목록 : {}", shopList.getContent());
        log.info("총 페이지 수 : {}", shopList.getTotalPages());
        log.info("총 메뉴 수 : {}", shopList.getTotalElements());
        log.info("해당 페이지에 표시 될 요소 수 : {}", shopList.getSize());
        log.info("해당 페이지에 실제 요소 수 : {}", shopList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", shopList.isFirst());
        log.info("마지막 페이지 여부 : {}", shopList.isLast());
        log.info("정렬 방식 : {}", shopList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", shopList.getNumber());

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(shopList);
        model.addAttribute("paging", paging);
        model.addAttribute("shopList", shopList);

        return "shop/list";
    }

    @GetMapping("querymethod")
    public void queryMethodPage() {}

    @GetMapping("search")
    public String findByTotalSales(@RequestParam Integer totalSales, Model model) {

        List<ShopDTO> shopList = shopService.findByTotalSales(totalSales);

        model.addAttribute("shopList", shopList);
        model.addAttribute("totalSales", totalSales);

        return "shop/searchResult";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping(value="category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {

        return shopService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registShop(ShopDTO shop) {

        shopService.registNewShop(shop);

        return "redirect:/shop/list";

    }

    @GetMapping("/modify")
    public void modifyPage() {}

    @PostMapping("/modify")
    public String modifyShop(ShopDTO shop) {

        shopService.modifyShop(shop);

        return "redirect:/shop/" + shop.getShopCode();
    }

    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deleteShop(@RequestParam Integer shopCode) {

        shopService.deleteShop(shopCode);

        return "redirect:/shop/list";
    }





}
