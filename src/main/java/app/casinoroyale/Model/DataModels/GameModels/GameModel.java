package app.casinoroyale.Model.DataModels.GameModels;

public class GameModel {

    private String gameName;

    public GameModel(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}