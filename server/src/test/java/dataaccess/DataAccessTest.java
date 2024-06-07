package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DataAccessTest {

    private static MySqlDataAccess dataAccess;

    @BeforeAll
    static void setUp() {
    }

    @Test
    @DisplayName("createStatement Test")
    public void createStatementTest() {

        try {
            dataAccess = new MySqlDataAccess();
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }

        Assertions.assertNotNull(dataAccess);
    }
}
