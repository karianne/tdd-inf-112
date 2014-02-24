package no.karianneberg;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegisterTest {


    public static final Item PIZZA = new Item("Pizza", 20);
    public static final Item BEER = new Item("Beer", 30);

    private Register register;

    @Before
    public void setUp() throws Exception {
        register = new Register(PIZZA, BEER);
    }

    @Test
    public void registerWithNoItemsScannedShouldTotalToZero() {
        assertTotalPriceIs(0);
    }

    @Test
    public void registerWithOneItemScannedShouldTotalToItemPrice() {
        register.scan(PIZZA);

        assertTotalPriceIs(PIZZA.getPrice());
    }

    @Test
    public void registerWithTwoScannedItemsShouldTotalToSumOfPriceOfItems() {
        register.scan(PIZZA);
        register.scan(BEER);

        assertTotalPriceIs(PIZZA.getPrice() + BEER.getPrice());
    }

    @Test
    public void registerShouldOnlyAddAllowedItemsToTotal() {
        register.scan(new Item("Unknown", 1000));

        assertTotalPriceIs(0);
    }

    private void assertTotalPriceIs(int price) {
        int totalPrice = register.getTotal();
        assertThat(totalPrice, is(price));
    }

}