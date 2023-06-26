package com.ohgiraffers.springdatajpa.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_shop")
public class Shop {
    @Id
    @Column(name="shop_code")
    private int shopCode;
    @Column(name="shop_name")
    private String shopName;
    @Column(name="total_sales")
    private int totalSales;
    @Column(name="category_code")
    private int categoryCode;
    @Column(name="orderable_status")
    private String orderableStatus;
    public Shop() {}
    public Shop(int shopCode, String shopName, int totalSales, int categoryCode,
                String orderableStatus) {
        super();
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.totalSales = totalSales;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }
    public int getShopCode() {
        return shopCode;
    }
    public void setShopCode(int menuCode) {
        this.shopCode = menuCode;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String menuName) {
        this.shopName = menuName;
    }
    public int getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
    public int getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getOrderableStatus() {
        return orderableStatus;
    }
    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }
    @Override
    public String toString() {
        return "Shop [shopCode=" + shopCode + ", shopName=" + shopName + ", totalSales=" + totalSales
                + ", categoryCode=" + categoryCode + ", orderableStatus=" + orderableStatus + "]";
    }
}
