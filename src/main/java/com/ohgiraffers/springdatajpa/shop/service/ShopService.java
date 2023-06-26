package com.ohgiraffers.springdatajpa.shop.service;

import com.ohgiraffers.springdatajpa.shop.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.shop.dto.ShopDTO;
import com.ohgiraffers.springdatajpa.shop.entity.Category;
import com.ohgiraffers.springdatajpa.shop.entity.Shop;
import com.ohgiraffers.springdatajpa.shop.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.shop.repository.ShopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /* 1. 메뉴 코드로 메뉴 하나 조회 : findById */
    public ShopDTO findShopByCode(int shopCode) {

        Shop shop = shopRepository.findById(shopCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(shop, ShopDTO.class);
    }

    /* 2-1. 메뉴 테이블의 모든 행 조회 : findAll(Sort) */
    public List<ShopDTO> findShopList() {

        List<Shop> shopList = shopRepository.findAll(Sort.by("shopCode").ascending());

        return shopList.stream().map(shop -> modelMapper.map(shop, ShopDTO.class)).collect(Collectors.toList());
    }

    /* 2-2. 메뉴 테이블의 요청 된 페이지에 맞는 요소 조회 : findAll(Pageable) */
    public Page<ShopDTO> findShopList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()  - 1,
                pageable.getPageSize(),
                Sort.by("shopCode").ascending());

        Page<Shop> shopList = shopRepository.findAll(pageable);

        return shopList.map(shop -> modelMapper.map(shop, ShopDTO.class));
    }

    /* 3. 입력 받은 가격을 초과하는 메뉴 목록 조회 : Query Method Test */
    public List<ShopDTO> findByTotalSales (Integer totalSales) {

        //List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(totalSales);
        //List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(totalSales);
        List<Shop> shopList = shopRepository.findByTotalSalesGreaterThan(totalSales, Sort.by("totalSales").ascending());

        return shopList.stream().map(shop -> modelMapper.map(shop, ShopDTO.class)).collect(Collectors.toList());
    }

    /* 4. 카테고리 목록 조회 : JPQL or Native Query 사용 */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    /* 5. 메뉴 등록 : save */
    @Transactional
    public void registNewShop(ShopDTO newShop) {
        shopRepository.save(modelMapper.map(newShop, Shop.class));
    }




    @Transactional
    public void modifyShop(ShopDTO shop) {
        Shop foundShop = shopRepository.findById(shop.getShopCode()).orElseThrow(IllegalArgumentException::new);
        foundShop.setShopName(shop.getShopName());
    }

    @Transactional
    public void deleteShop (int shopCode) {

        shopRepository.deleteById(shopCode);
    }
}
