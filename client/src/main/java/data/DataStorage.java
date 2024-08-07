package data;

import client.ServerFacade;
import ui.PostLogin;
import ui.PreLogin;

public class DataStorage {

    private DataStorage() {
        this.preLogin = new PreLogin();
        this.postLogin = new PostLogin();
    }

    private static class Holder {
        private static final DataStorage INSTANCE = new DataStorage();
    }

    public static DataStorage getInstance() {
        return Holder.INSTANCE;
    }

    private State state;
    private String authToken;
    private ServerFacade facade;
    private final PreLogin preLogin;
    private final PostLogin postLogin;

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

    public ServerFacade getFacade() {
        return facade;
    }

    public PreLogin getPreLogin() {
        return preLogin;
    }

    public PostLogin getPostLogin() {
        return postLogin;
    }

    public enum State {
        LOGGED_OUT,
        LOGGED_IN
    }

    public void setRun(String url) {
        facade = new ServerFacade(url);
    }
}
