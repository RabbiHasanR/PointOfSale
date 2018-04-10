package com.example.diu.pointofsale.Model;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private String productBarcode;
    private double sellingPrice;
    private double buyingPrice;
    private int productImage;
    private String productCategory;
    private String productSize;
    private int productQuantity;
    private String productInsertDate;
    private String productUpdateDate;
    private int productUserId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductInsertDate() {
        return productInsertDate;
    }

    public void setProductInsertDate(String productInsertDate) {
        this.productInsertDate = productInsertDate;
    }

    public String getProductUpdateDate() {
        return productUpdateDate;
    }

    public void setProductUpdateDate(String productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }

    public int getProductUserId() {
        return productUserId;
    }

    public void setProductUserId(int productUserId) {
        this.productUserId = productUserId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
