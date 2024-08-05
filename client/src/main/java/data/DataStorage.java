package data;

import Facade.ServerFacade;
import model.AuthData;

public class DataStorage {

    private static final DataStorage instance = new DataStorage();

    public static DataStorage getInstance() {
        return instance;
    }

    private State state;
    //private State state = State.LOGGED_OUT;

    private String authToken;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public enum State {
        LOGGED_OUT,
        LOGGED_IN
    }
}
