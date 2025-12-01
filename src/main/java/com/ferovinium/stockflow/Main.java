package com.ferovinium.stockflow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("WELCOME TO FEROVINIUM STOCKFLOW APPLICATION");
        System.out.println("Enter your command (e.g. sell wine 1000):");

        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            // ignore blank lines
            if (line.isEmpty()) {
                continue;
            }

            // split by ANY whitespace (fixes duplicate execution bug)
            String[] p = line.split("\\s+");
            if (p.length != 3) {
                continue;  // ignore malformed input
            }

            String cmd = p[0].toLowerCase();
            String sku = p[1].toLowerCase();

            int qty;
            try {
                qty = Integer.parseInt(p[2]);
            } catch (NumberFormatException e) {
                continue;  // ignore invalid quantity
            }

            switch (cmd) {
                case "sell":
                    inventory.sell(sku, qty);
                    break;
                case "buy":
                    inventory.buy(sku, qty);
                    break;
                default:
                    // ignore unknown commands
                    continue;
            }

            // print history ONCE per command
            inventory.printAll();
        }
    }
}
