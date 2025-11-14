package com.ferovinium.stockflow;

import java.util.*;

public class Inventory {

    private final List<Order> history = new ArrayList<>();
    private final Map<String, Queue<Order>> stock = new HashMap<>();

    public Inventory() {
        stock.put("wine", new LinkedList<>());
        stock.put("whisky", new LinkedList<>());
    }

    public void sell(String sku, int qty) {
        Order order = new Order("sell", sku, qty);
        history.add(order);  // adds a new sell order to the history
        stock.get(sku).offer(order); // adds the order into the queue for the sku
    }
// attempts to fulfill buy order using the available stock in FIFO order
    public void buy(String sku, int qty) {
        Queue<Order> queue = stock.get(sku);

        if (total(queue) < qty) {
            return;
        }

        int left = qty;

        while (left > 0 && !queue.isEmpty()) {
            Order s = queue.peek();
            int use = Math.min(left, s.getRemaining());

            s.setRemaining(s.getRemaining() - use);
            left -= use;

            if (s.getRemaining() == 0) {
                queue.poll();
            }
        }

        Order b = new Order("buy", sku, qty);
        b.setRemaining(0);
        history.add(b);
    }
// calculates the total remaining stock
    private int total(Queue<Order> q) {
        int sum = 0;
        for (Order o : q) {
            sum += o.getRemaining();
        }
        return sum;
    }

    public void printAll() {
        for (Order o : history) {
            System.out.println(o);
        }
        System.out.println();
    }
}
