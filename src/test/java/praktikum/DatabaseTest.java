package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {

    @Test
    void testConstructor() {
        Database db = new Database();

        assertTrue(db.availableBuns().size() > 0);
        assertTrue(db.availableIngredients().size() > 0);
    }
}