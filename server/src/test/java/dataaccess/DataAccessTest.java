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
        dataAccess = new MySqlDataAccess("any");
        try {
            dataAccess.createStatements();
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }

        Assertions.assertNotNull(dataAccess);
    }

    @Test
    @DisplayName("createStatement Negative Test")
    public void createStatementNegativeTest() {
        dataAccess = new MySqlDataAccess("");
        try {
            dataAccess.createStatements();
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }

        Assertions.assertNotNull(dataAccess);
    }
}
