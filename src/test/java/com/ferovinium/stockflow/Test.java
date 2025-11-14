package com.ferovinium.stockflow;

public class Test {

    public static void main(String[] args) {

        Inventory inv = new Inventory();

        inv.sell("wine", 1000);
        inv.printAll();

        inv.sell("whisky", 100);
        inv.printAll();

        inv.buy("wine", 500);
        inv.printAll();

        inv.buy("wine", 1000);
        inv.printAll();

        inv.buy("wine", 500);
        inv.printAll();

        inv.sell("whisky", 100);
        inv.printAll();

        inv.buy("whisky", 120);
        inv.printAll();
    }
}
