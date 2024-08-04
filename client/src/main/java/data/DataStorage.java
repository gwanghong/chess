package data;

import Facade.ServerFacade;

public class DataStorage {

    private static DataStorage instance = new DataStorage();

    public static DataStorage getInstance() {
        return instance;
    }

    private ServerFacade facade;

    public ServerFacade getFacade() {
        return facade;
    }
}
