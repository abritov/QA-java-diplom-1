package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BurgerTest {

    @Test
    void testConstructor() {
        Burger burger = new Burger();
        assertNull(burger.bun);
        assertNotNull(burger.ingredients);

        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    void testAddIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertEquals(0, burger.ingredients.size());

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
    void testRemoveIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(0),
                "burger.removeIngredient on empty burger"
        );

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    void testMoveIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(0, 1),
                "burger.moveIngredient on empty burger"
        );

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
    void testGetPrice() {
        Burger burger = new Burger();

        assertThrows(
                NullPointerException.class,
                burger::getPrice,
                "burger.getPrice on empty burger"
        );

        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertEquals(bun.getPrice() * 2, burger.getPrice());

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);
        assertEquals(bun.getPrice() * 2 + filling.getPrice(), burger.getPrice());

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "bar", 3.0f);
        burger.addIngredient(sauce);
        assertEquals(bun.getPrice() * 2 + filling.getPrice() + sauce.getPrice(), burger.getPrice());
    }

    @Test
    void testGetReceipt() {
        Burger burger = new Burger();

        assertThrows(
                NullPointerException.class,
                burger::getReceipt,
                "burger.getReceipt on empty burger"
        );

        Bun bun = new Bun("foo", 10.0f);
        burger.setBuns(bun);

        assertEquals("" +
                "(==== foo ====)\n" +
                "(==== foo ====)\n\n" +
                "Price: 20.000000\n", burger.getReceipt()
        );

        Ingredient filling = new Ingredient(IngredientType.FILLING, "foo", 10.0f);
        burger.addIngredient(filling);

        assertEquals("" +
                "(==== foo ====)\n" +
                "= filling foo =\n" +
                "(==== foo ====)\n\n" +
                "Price: 30.000000\n", burger.getReceipt()
        );

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "bar", 3.0f);
        burger.addIngredient(sauce);

        assertEquals("" +
                "(==== foo ====)\n" +
                "= filling foo =\n" +
                "= sauce bar =\n" +
                "(==== foo ====)\n\n" +
                "Price: 33.000000\n", burger.getReceipt()
        );
    }
}
