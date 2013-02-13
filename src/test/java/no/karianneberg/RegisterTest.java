package no.karianneberg;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegisterTest {

    private static final Item PIZZA = new Item("Pizza", 20);
    private static final Item ØL = new Item("Øl", 30);
    private Register register;

    @Before
    public void setUp() throws Exception {
        register = new Register(PIZZA, ØL);
    }

    @Test
    public void registerWithNoScannedItemsTotalsToZero() throws Exception {
        assertThatTotalIs(0);
    }

    @Test
    public void registerWithOneItemScannedShouldTotalToPriceOfThatItem() throws Exception {
        register.scan(PIZZA);
        assertThatTotalIs(PIZZA.getPrice());
    }

    @Test
    public void registerWithTwoItemsScannedShouldTotalToSumOfPriceOfItems() throws Exception {
        register.scan(PIZZA);
        register.scan(ØL);

        assertThatTotalIs(PIZZA.getPrice() + ØL.getPrice());
    }

    @Test
    public void unknownItemsShouldNotAddToTotal() throws Exception {
        register.scan(new Item("Unknown", 100));

        assertThatTotalIs(0);
    }

    private void assertThatTotalIs(int expectedPrice) {
        int totalPrice = register.getTotal();
        assertThat(totalPrice, is(expectedPrice));
    }

}