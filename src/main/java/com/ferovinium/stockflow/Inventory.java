package com.ferovinium.stockflow;

import java.util.*;

public class Inventory {

    private final List<Order> history = new ArrayList<>();
    private final Map<String, Queue<Order>> stock = new HashMap<>();

    public void sell(String sku, int qty) {

        Order sellOrder = new Order("sell", sku, qty);

        stock.computeIfAbsent(sku, k -> new LinkedList<>())
                .offer(sellOrder);

        history.add(sellOrder);
    }

    public void buy(String sku, int qty) {

        Queue<Order> queue = stock.getOrDefault(sku, new LinkedList<>());

        int available = total(queue);
        if (available == 0) {
            return;  // do nothing when no stock
        }

        int toBuy = Math.min(available, qty);
        int remainingToBuy = toBuy;

        // FIFO consumption loop
        while (remainingToBuy > 0 && !queue.isEmpty()) {
            Order oldestSell = queue.peek();

            int use = Math.min(oldestSell.getRemaining(), remainingToBuy);

            oldestSell.reduceRemaining(use);
            remainingToBuy -= use;

            if (oldestSell.getRemaining() == 0) {
                queue.poll();
            }
        }

        // DO NOT CREATE BUY ORDER IF NOTHING WAS FULFILLED
        if (toBuy == 0) {
            return;
        }

        // Create buy order with actual fulfilled amount
        Order buyOrder = new Order("buy", sku, toBuy);
        buyOrder.reduceRemaining(toBuy); // mark closed
        history.add(buyOrder);
    }


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

    public List<Order> getHistory() {
        return history;
    }
}
