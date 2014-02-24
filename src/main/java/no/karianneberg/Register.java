package no.karianneberg;

import java.util.Arrays;
import java.util.List;

public class Register {

    private int totalPrice;
    private List<Item> allowedItems;

    public Register(Item... allowedItems) {
        this.allowedItems = Arrays.asList(allowedItems);
    }

    public int getTotal() {
        return totalPrice;
    }

    public void scan(Item item) {
        if (allowedItems.contains(item)) {
            totalPrice += item.getPrice();
        }
    }
}
