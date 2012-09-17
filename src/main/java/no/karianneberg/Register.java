package no.karianneberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register {

    private final List<Item> allowedItems;

    private Map<Item, Integer> scannedItems = new HashMap<Item, Integer>();
    private final List<Item> itemsWithTwoForThreeOffers = new ArrayList<Item>();

    public Register(List<Item> allowedItems) {
        this.allowedItems = allowedItems;
    }

    public int calculateTotal() {

        int totalPrice = 0;

        for (Item item : scannedItems.keySet()) {
            Integer numberOfItems = scannedItems.get(item);
            if (itemsWithTwoForThreeOffers.contains(item)) {
                int numberOfDeals = numberOfItems / 3;
                int rest = numberOfItems % 3;
                totalPrice += (2 * item.getPrice() * numberOfDeals) + (rest * item.getPrice());
            } else {
                totalPrice += numberOfItems * item.getPrice();
            }
        }

        return totalPrice;
    }

    public void scan(Item item) {
        if (!allowedItems.contains(item)) {
            return;
        }

        if (!scannedItems.containsKey(item)) {
            scannedItems.put(item, 0);
        }

        scannedItems.put(item, scannedItems.get(item) + 1);
    }

    public void addTwoForThreeOffer(Item forItem) {
        itemsWithTwoForThreeOffers.add(forItem);
    }
}