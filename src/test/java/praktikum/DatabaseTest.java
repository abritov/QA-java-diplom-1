package praktikum;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    @Test
    public void testConstructor() {
        Database db = new Database();

        assertTrue(db.availableBuns().size() > 0);
        assertTrue(db.availableIngredients().size() > 0);
    }
}