package app.casinoroyale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private Stage stage;

    public HomeController(){

    }
    FXMLLoader homeFXML = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Dashboards/HomePage.fxml"));

    public void homeDash(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(homeFXML.load(), 3200, 2400);
        stage.setTitle("Casino Royale");
        stage.setScene(scene);
        stage.show();
    }
    public void playBlackJack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/BlackJack.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Black Jack");
        stage.setScene(scene);
        stage.show();
    }
    public void playRoulette(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/Roulette.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.show();
    }
    public void playHorseRacing(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/HorseRacing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Horse Racing");
        stage.setScene(scene);
        stage.show();
    }

    public void playSlots(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.casinoroyale/View/Games/Slots.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Slots");
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
