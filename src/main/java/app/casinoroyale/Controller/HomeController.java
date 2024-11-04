package app.casinoroyale.Controller;

import app.casinoroyale.Controller.GamesControllers.BlackJackController.PlayroomBetController;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Platform;
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
    private final Player player;
    private static Stage primaryStage;
    @SuppressFBWarnings("URF_UNREAD_FIELD")
    private Stage stage = new Stage();

    @FXML
    public void initialize() {
        initializeImages();
    }

    public HomeController(){
        this.player = new Player();
    }

    public static void setPrimaryStage(Stage stage) {
        HomeController.primaryStage = stage;
    }

    private void changeScene(String fxmlPath, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth * 1, screenHeight * 1);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeSceneLogin(String fxmlPath, String title) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth * 0.6, screenHeight * 0.6);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initializeImages() {
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

    public void homeDash(ActionEvent actionEvent) throws IOException {
        changeScene("/app/casinoroyale/View/Dashboards/HomePage.fxml", "Casino Royale");
    }

    public void exitGame(ActionEvent actionEvent) throws IOException {
        Platform.exit();
    }

    public void loginDash(ActionEvent actionEvent) throws IOException {
        changeSceneLogin("/app/casinoroyale/View/Dashboards/LoginPage.fxml", "Casino Royale");
    }

    public void registerDash(ActionEvent actionEvent) throws IOException {
        changeSceneLogin("/app/casinoroyale/View/Dashboards/RegisterPage.fxml", "Casino Royale");
    }

    public void playBlackJack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/playroom-bet-view.fxml"));
        Scene blackjackScene = new Scene(fxmlLoader.load(), screenWidth * 1, screenHeight * 1);

        // Get the controller and pass the blackJackPlayer to it
        PlayroomBetController betController = fxmlLoader.getController();
        betController.loadContents(Player.getInstance());

        // Set the scene on the primary stage
        primaryStage.setTitle("Blackjack Game");
        primaryStage.setScene(blackjackScene);
        primaryStage.show();
    }

    public void playRoulette(ActionEvent actionEvent) throws IOException {
        changeScene("/app/casinoroyale/View/Games/Roulette.fxml", "Roulette");
    }

    public void playHorseRacing(ActionEvent actionEvent) throws IOException {
        changeScene("/app/casinoroyale/View/Games/HorseRacing.fxml", "Horse Racing");
    }

    public void playSlots(ActionEvent actionEvent) throws IOException {
        changeScene("/app/casinoroyale/View/Games/Slots.fxml", "Slots");
    }

    public void launchBank(ActionEvent actionEvent) throws IOException {
        changeScene("/app/casinoroyale/View/Dashboards/Bank.fxml", "Bank");
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


}