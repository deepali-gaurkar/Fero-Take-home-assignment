package com.ferovinium.stockflow;

public class Order {

    private final String type;
    private final String sku;
    private final int quantity;
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

    public void reduceRemaining(int amount) {
        this.remaining -= amount;
    }

    @Override
    public String toString() {
        String status = remaining == 0 ? "closed" : "remaining:" + remaining;
        return type + " " + sku + " " + quantity + " " + status;
    }
}
