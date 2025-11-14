package com.ferovinium.stockflow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("WELCOME TO FEROVINIUM STOCKFLOW APPLICATION");
        System.out.println("Enter your command: eg. sell wine 1000");
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split(" ");
            if (p.length != 3) continue;

            String cmd = p[0];
            String sku = p[1];
            int qty = Integer.parseInt(p[2]);

            if (cmd.equals("sell")) {
                inventory.sell(sku, qty);
            } else if (cmd.equals("buy")) {
                inventory.buy(sku, qty);
            }

            inventory.printAll();
        }
    }
}
