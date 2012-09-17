package no.karianneberg;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegisterTest {

    private Register register;
    private static final Item RED_BEANS = new Item("Red beans", 5);
    private static final Item CHOCOLATE = new Item("Chocolate", 10);
    private static final Item PIZZA = new Item("Pizza", 15);
    private static final Item CHEESE = new Item("Cheese", 20);

    @Before
    public void setUp() throws Exception {
        register = new Register(Arrays.asList(PIZZA, RED_BEANS, CHEESE, CHOCOLATE));
    }

    @Test
    public void registerWithNoItemsScannedShouldShowZeroTotalPrice() {
        assertThat(register.calculateTotal(), is(0));
    }

    @Test
    public void registerWithOneItemScannedShouldHaveTotalPriceEqualPriceOfItem() {
        register.scan(RED_BEANS);

        assertThat(register.calculateTotal(), is(RED_BEANS.getPrice()));
    }

    @Test
    public void registerWithTwoItemsScannedShouldHaveTotalPriceEqualPriceOfBothItems() {
        register.scan(RED_BEANS);
        register.scan(PIZZA);

        assertThat(register.calculateTotal(), is(RED_BEANS.getPrice() + PIZZA.getPrice()));
    }

    @Test
    public void scanningUnknownItemShouldNotAddToTotal() {
        register.scan(new Item("Unknown", 100));

        assertThat(register.calculateTotal(), is(0));
    }
    
    @Test
    public void buyThreePayForTwo() {
        register.addTwoForThreeOffer(PIZZA);

        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);

        assertThat(register.calculateTotal(), is(PIZZA.getPrice() * 2));
    }

    @Test
    public void buyThreePayForTwoWithFourItems() {
        register.addTwoForThreeOffer(PIZZA);

        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);

        assertThat(register.calculateTotal(), is(PIZZA.getPrice() * 3));
    }

    @Test
    public void buyThreePayForTwoWithSixItems() {
        register.addTwoForThreeOffer(PIZZA);

        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);
        register.scan(PIZZA);

        assertThat(register.calculateTotal(), is(PIZZA.getPrice() * 4));
    }

}