package com.example.diu.pointofsale.Model;

public class Cart {
    private int cartId;
    private int cartProductId;
    private int cartUserId;
    private int cartQuantity;
    private String cartTime;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(int cartProductId) {
        this.cartProductId = cartProductId;
    }

    public int getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(int cartUserId) {
        this.cartUserId = cartUserId;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public String getCartTime() {
        return cartTime;
    }

    public void setCartTime(String cartTime) {
        this.cartTime = cartTime;
    }
}
