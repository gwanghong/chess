package data;

import model.GameData;

import java.util.Collection;

public class ListGameResponse {
    private Collection<GameData> games;

    public Collection<GameData> getGames() {
        return games;
    }

    public void setGames(Collection<GameData> games) {
        this.games = games;
    }
}
