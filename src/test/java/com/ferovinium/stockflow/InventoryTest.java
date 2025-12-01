package com.ferovinium.stockflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    void sellOrderIsAddedToHistory() {
        Inventory inv = new Inventory();

        inv.sell("wine", 1000);

        assertEquals(1, inv.getHistory().size());
        Order o = inv.getHistory().get(0);

        assertEquals("sell", o.getType());
        assertEquals("wine", o.getSku());
        assertEquals(1000, o.getQuantity());
        assertEquals(1000, o.getRemaining());
    }

    @Test
    void buyConsumesInFIFOOrder() {
        Inventory inv = new Inventory();

        inv.sell("wine", 1000);
        inv.sell("wine", 500);

        inv.buy("wine", 600); // should consume from first sell

        Order first = inv.getHistory().get(0);
        Order second = inv.getHistory().get(1);

        assertEquals(400, first.getRemaining()); // 1000 - 600
        assertEquals(500, second.getRemaining()); // untouched
    }

    @Test
    void partialBuyConsumesOnlyAvailableStock() {
        Inventory inv = new Inventory();

        inv.sell("wine", 100);
        inv.buy("wine", 300); // only 100 available

        Order sell = inv.getHistory().get(0);
        Order buy = inv.getHistory().get(1);

        assertEquals(0, sell.getRemaining());
        assertEquals(100, buy.getQuantity());
        assertEquals(0, buy.getRemaining());
    }

    @Test
    void buyDoesNothingWhenNoStock() {
        Inventory inv = new Inventory();

        inv.buy("wine", 100);

        assertEquals(0, inv.getHistory().size());
    }

    @Test
    void dynamicSKUsAreSupported() {
        Inventory inv = new Inventory();

        inv.sell("rum", 50);
        inv.buy("rum", 20);

        assertEquals(2, inv.getHistory().size());
        assertEquals("rum", inv.getHistory().get(0).getSku());
        assertEquals("rum", inv.getHistory().get(1).getSku());
    }

    @Test
    void buyOrderIsNotCreatedWhenNothingFulfilled() {
        Inventory inv = new Inventory();

        inv.sell("wine", 30);
        inv.buy("wine", 0);

        assertEquals(1, inv.getHistory().size());
    }
}
