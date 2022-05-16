package praktikum;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class BurgerTest {

    @Test
    public void testConstructor() {
        Burger burger = new Burger();
        assertNull(burger.bun);
        assertEquals(0, burger.ingredients.size());

        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);

        assertEquals(filling, burger.ingredients.get(0));
        assertEquals(1, burger.ingredients.size());

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "bar", 3.0f);
        burger.addIngredient(sauce);

        assertEquals(sauce, burger.ingredients.get(1));
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient_shouldThrowWhenNoIngredients() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertThrows(
                "burger.removeIngredient on empty burger",
                IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(0)
        );
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient_shouldThrowWhenNoIngredients() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertThrows(
                "burger.moveIngredient on empty burger",
                IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(0, 1)
        );
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "bar", 3.0f);
        burger.addIngredient(sauce);

        assertEquals(2, burger.ingredients.size());
        assertEquals(filling, burger.ingredients.get(0));
        assertEquals(sauce, burger.ingredients.get(1));

        burger.moveIngredient(0, 1);
        assertEquals(sauce, burger.ingredients.get(0));
        assertEquals(filling, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice_shouldThrowWhenNoBunSet() {
        Burger burger = new Burger();

        assertThrows(
                "burger.getPrice on empty burger",
                NullPointerException.class,
                burger::getPrice
        );
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertEquals(bun.getPrice() * 2, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetPriceFilling() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);
        assertEquals(bun.getPrice() * 2 + filling.getPrice(), burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetPriceSauce() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "bar", 3.0f);
        burger.addIngredient(sauce);
        assertEquals(bun.getPrice() * 2 + sauce.getPrice(), burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt_shouldThrowWhenNoBunSet() {
        // MOCK
        Burger burger = Mockito.spy(new Burger());

        assertThrows(
                "burger.getReceipt on empty burger",
                NullPointerException.class,
                burger::getReceipt
        );
    }

    @Test
    public void testGetReceipt() {
        // MOCK
        Burger burger = Mockito.spy(new Burger());

        Bun bun = Mockito.spy(new Bun("foo", 10.0f));
        // STUB
        Mockito.when(bun.getName()).thenReturn("bar");
        burger.setBuns(bun);

        assertEquals("" +
                "(==== bar ====)\n" +
                "(==== bar ====)\n\n" +
                "Price: 20.000000\n", burger.getReceipt()
        );

        Ingredient filling = Mockito.spy(new Ingredient(IngredientType.FILLING, "foo", 10.0f));
        burger.addIngredient(filling);

        assertEquals("" +
                "(==== bar ====)\n" +
                "= filling foo =\n" +
                "(==== bar ====)\n\n" +
                "Price: 30.000000\n", burger.getReceipt()
        );
        Mockito.verify(filling, Mockito.times(1)).getType();
        Mockito.verify(filling, Mockito.times(1)).getName();

        Ingredient sauce = Mockito.spy(new Ingredient(IngredientType.SAUCE, "bar", 3.0f));
        burger.addIngredient(sauce);

        assertEquals("" +
                "(==== bar ====)\n" +
                "= filling foo =\n" +
                "= sauce bar =\n" +
                "(==== bar ====)\n\n" +
                "Price: 33.000000\n", burger.getReceipt()
        );

        Mockito.verify(sauce, Mockito.times(1)).getType();
        Mockito.verify(sauce, Mockito.times(1)).getName();

        Mockito.verify(bun, Mockito.times(6)).getName();
    }
}
