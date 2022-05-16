package praktikum;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void testSauceConstructorType() {
        final String name = "foo";
        final float price = 10.0f;
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, price);

        assertEquals(IngredientType.SAUCE, ingredient.type);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void testSauceConstructorName() {
        final String name = "foo";
        final float price = 10.0f;
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, price);

        assertEquals(name, ingredient.name);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testSauceConstructorPrice() {
        final String name = "foo";
        final float price = 10.0f;
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, price);

        assertEquals(price, ingredient.price, 0.001f);
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void testFillingConstructor() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "foo", 10.0f);

        assertEquals(IngredientType.FILLING, ingredient.type);
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }
}
