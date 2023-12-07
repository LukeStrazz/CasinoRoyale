package app.casinoroyale.Controller;

import app.casinoroyale.Controller.GamesControllers.BlackJackController.PlayroomBetController;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HomeController {


    private final Player player;
    private static Stage primaryStage;

    private Stage stage = new Stage();

    @FXML
    private ImageView slotsImageView;
    @FXML
    private ImageView rouletteImageView;
    @FXML
    private ImageView horseraceImageView;
    @FXML
    private ImageView blackjackImageView;
    @FXML
    private ImageView casionoImageView;


    @FXML
    public void initialize() {
        initializeImages();
    }


    public HomeController(){
        this.player = new Player();
    }


    public void initializeImages(){

        File roulette = new File("src/main/resources/app/Assets/HomePage/Games/roulette.png");
        File slots = new File("src/main/resources/app/Assets/HomePage/Games/slots.png");
        File horseRace = new File("src/main/resources/app/Assets/HomePage/Games/horseracing.png");
        File blackJack = new File("src/main/resources/app/Assets/HomePage/Games/blackjack.png");
        File casino = new File("src/main/resources/app/Assets/HomePage/Casino/casino.png");

        Image rouletteImage = new Image(roulette.toURI().toString());
        Image slotsImage = new Image(slots.toURI().toString());
        Image horseRaceImage = new Image(horseRace.toURI().toString());
        Image blackJackImage = new Image(blackJack.toURI().toString());
        Image casinoImage = new Image(casino.toURI().toString());

        rouletteImageView.setImage(rouletteImage);
        slotsImageView.setImage(slotsImage);
        horseraceImageView.setImage(horseRaceImage);
        blackjackImageView.setImage(blackJackImage);
        casionoImageView.setImage(casinoImage);
    }

    javafx.geometry.Rectangle2D screenSize = Screen.getPrimary().getBounds();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();


    FXMLLoader homeFXML = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Dashboards/HomePage.fxml"));

    public void homeDash(ActionEvent actionEvent) throws IOException {
        stage.close();
        Scene scene = new Scene(homeFXML.load(), screenWidth * 0.8, screenHeight * 0.8);
        stage.setTitle("Casino Royale");
        stage.setScene(scene);
        stage.show();
    }

    public void playBlackJack(ActionEvent actionEvent) throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/playroom-bet-view.fxml"));
        Scene betViewScene = new Scene(fxmlLoader.load());
        PlayroomBetController betController = fxmlLoader.getController();
        betController.loadContents(Player.getInstance());

        primaryStage = stage;
        stage.setTitle("Blackjack Game");
        stage.setScene(betViewScene);
        stage.show();
    }

    public void playRoulette(ActionEvent actionEvent) throws IOException {
        this.stage.close();
        this.stage.hide();

        stage.close();
        stage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/Roulette.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth * 0.8, screenHeight * 0.8);
        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.show();
    }

    public void playHorseRacing(ActionEvent actionEvent) throws IOException {
        this.stage.close();
        this.stage.hide();

        stage.close();
        stage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/HorseRacing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth * 0.8, screenHeight * 0.8);
        stage.setTitle("Horse Racing");
        stage.setScene(scene);
        stage.show();
    }

    public void playSlots(ActionEvent actionEvent) throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/Slots.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth * 0.8, screenHeight * 0.8);
        stage.setTitle("Slots");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
