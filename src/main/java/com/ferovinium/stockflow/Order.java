package com.ferovinium.stockflow;

public class Order {

    private String type;
    private String sku;
    private int quantity;
    private int remaining;

    public Order(String type, String sku, int quantity) {
        this.type = type;
        this.sku = sku;
        this.quantity = quantity;
        this.remaining = quantity;
    }

    public String getType() {
        return type;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        String status = remaining == 0 ? "closed" : "remaining:" + remaining;
        return type + " " + sku + " " + quantity + " " + status;
    }
}
