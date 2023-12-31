package com.ohgiraffers.springdatajpa.shop.repository;

import com.ohgiraffers.springdatajpa.shop.entity.Shop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findByTotalSalesGreaterThan(Integer totalSales);

    List<Shop> findByTotalSalesGreaterThanOrderByTotalSales(Integer totalSales);

    List<Shop> findByTotalSalesGreaterThan(Integer totalSales, Sort sort);



}
