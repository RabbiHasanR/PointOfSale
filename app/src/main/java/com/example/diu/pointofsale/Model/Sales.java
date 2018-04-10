package com.example.diu.pointofsale.Model;

public class Sales {
    private int salesId;
    private int salesProductId;
    private int salesUserId;
    private int salesCustomerId;
    private int quantity;
    private String salesDate;

    public int getSalesCustomerId() {
        return salesCustomerId;
    }

    public void setSalesCustomerId(int salesCustomerId) {
        this.salesCustomerId = salesCustomerId;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getSalesProductId() {
        return salesProductId;
    }

    public void setSalesProductId(int salesProductId) {
        this.salesProductId = salesProductId;
    }

    public int getSalesUserId() {
        return salesUserId;
    }

    public void setSalesUserId(int salesUserId) {
        this.salesUserId = salesUserId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }
}
