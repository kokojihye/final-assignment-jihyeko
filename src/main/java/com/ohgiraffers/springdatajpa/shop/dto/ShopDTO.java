package com.ohgiraffers.springdatajpa.shop.dto;
public class ShopDTO {
    private int shopCode;
    private String shopName;
    private int totalSales;
    private int categoryCode;
    private String orderableStatus;
    public ShopDTO() {}
    public ShopDTO(int shopCode, String shopName, int totalSales, int categoryCode,
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
    public void setShopCode(int shopCode) {
        this.shopCode = shopCode;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
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
