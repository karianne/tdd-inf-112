package no.karianneberg;

import java.util.*;

public class Register {

    private int total;
    private List<Item> allowedItems;

    public Register(Item... allowedItems) {
        this.allowedItems = Arrays.asList(allowedItems);
    }

    public int getTotal() {
        return total;
    }

    public void scan(Item item) {
        if (allowedItems.contains(item)) {
            total += item.getPrice();
        }
    }
}
