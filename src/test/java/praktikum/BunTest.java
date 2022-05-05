package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    void testConstructor() {
        final String name = "foo";
        final float price = 10.0f;
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.name);
        assertEquals(name, bun.getName());

        assertEquals(price, bun.price);
        assertEquals(price, bun.getPrice());
    }
}
