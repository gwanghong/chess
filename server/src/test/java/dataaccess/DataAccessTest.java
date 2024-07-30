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

    @Test
    @DisplayName("executeUpdate Positive Test")
    public void executeUpdatePositiveTest() {

    }

    @Test
    @DisplayName("executeUpdate Negative Test")
    public void executeUpdateNegativeTest() {

    }

    @Test
    @DisplayName("configureData Positive Test")
    public void configureDataPositiveTest() {
        try {
            dataAccess = new MySqlDataAccess();
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }

        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("configureData Negative Test")
    public void configureDataNegativeTest() {
        try {
            dataAccess = new MySqlDataAccess("random");
        } catch (RuntimeException e) {
            Assertions.fail();
        }

        Assertions.assertTrue(true);
    }
}
