package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object> testCases() {
        return Arrays.asList(new Object[][]{
                {"foo", 10.0f},
                {"bar", 100.0f},
                {"baz", 33.0f}
        });
    }

    @Test
    public void testConstructorName() {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.name);
        assertEquals(name, bun.getName());
    }

    @Test
    public void testConstructorPrice() {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.price, 0.001f);
        assertEquals(price, bun.getPrice(), 0.001f);
    }
}
