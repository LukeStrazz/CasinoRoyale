package app.casinoroyale.Controller.GamesControllers.BlackJackController;


import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Chip;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Game;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.DecimalFormat;

public class PlayroomBetController {

    Player player;

    Game game;

    @FXML
    Text txtBetValue;
    @FXML
    Text txtBalance;
    @FXML
    Button btnDeal;
    @FXML
    Button btnCancel;

    /**
     * Initialize a new game for the player.
     *
     * @param Player player to been shown on the UI as well as who plays the game
     */


    private final app.casinoroyale.Controller.HomeController HomeController = new HomeController();




    @FXML
    private void playHorseRacing(ActionEvent event) throws IOException {
        HomeController.playHorseRacing(event);
    } @FXML
    private void playRoulette(ActionEvent event) throws IOException {
        HomeController.playRoulette(event);
    }@FXML
    private void playSlots(ActionEvent event) throws IOException {
        HomeController.playSlots(event);
    }
    @FXML
    private void homeDash(ActionEvent event) throws IOException {
        HomeController.homeDash(event);
    }
    @FXML
    private void launchBank(ActionEvent event) throws IOException {
        HomeController.launchBank(event);
    }
    @FXML
    private void showInstructions(ActionEvent event) {
        Alert instructionAlert = new Alert(Alert.AlertType.INFORMATION);
        instructionAlert.setTitle("How to Play");
        instructionAlert.setHeaderText("Instructions for BlackJack Game");
        instructionAlert.setContentText(
                        "1. Choose a starting bet amount for your blackjack hand.\n" +
                        "2. Click 'Deal' to receive your initial two cards.\n" +
                        "3. Decide whether to 'Hit' to get another card or 'Stand' to keep your current hand.\n" +
                        "4. Utilize 'Double Down' if you're feeling confident.\n" +
                        "5. Continue making strategic decisions until you decide to 'Stand' or until you exceed a total of 21 points, resulting in a bust.\n" +
                        "6. If you win the round, your payout will be added to your balance.\n" +
                        "7. Enjoy the excitement of playing blackjack and aim to beat the dealer!"
        );

        instructionAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        instructionAlert.showAndWait();
    }

    public void updateBalanceText(double balance) {
        DecimalFormat formatter = new DecimalFormat("\t$###,###");
        txtBalance.setText(formatter.format(balance));
    }
    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
    public void loadContents(Player player) {
        this.player = player.getInstance();

        // Load the player's current balance
        player.getAccountBalance();  // You need to update this line based on how you load the balance

        // Only create a new game if it hasn't been created yet
        if (this.game == null) {
            this.game = new Game(player);
        }

        // Update balance text on UI
        updateBalanceText(player.getAccountBalance());

        updateScene();
    }

    /**
     * Player adds a $10 value chip to the bet.
     */
    @FXML
    protected void onBet10ChipClicked() {
        if (player.getAccountBalance() < 10) {
            showInsufficientFundsAlert();
            return;
        }

        System.out.println("Add a $10 chip to the bet");
        game.increaseBet(new Chip(10));
        updateScene();
    }

    /**
     * Player adds a $20 value chip to the bet.
     */
    @FXML
    protected void onBet20ChipClicked() {
        if (player.getAccountBalance() < 20) {
            showInsufficientFundsAlert();
            return;
        }

        System.out.println("Add a $20 chip to the bet");
        game.increaseBet(new Chip(20));
        updateScene();
    }

    /**
     * Player adds a $50 value chip to the bet.
     */
    @FXML
    protected void onBet50ChipClicked() {
        if (player.getAccountBalance() < 50) {
            showInsufficientFundsAlert();
            return;
        }

        System.out.println("Add a $50 chip to the bet");
        game.increaseBet(new Chip(50));
        updateScene();
    }

    /**
     * Player adds a $100 value chip to the bet.
     */
    @FXML
    protected void onBet100ChipClicked() {
        if (player.getAccountBalance() < 100) {
            showInsufficientFundsAlert();
            return;
        }

        System.out.println("Add a $100 chip to the bet");
        game.increaseBet(new Chip(100));
        updateScene();
    }

    private void showInsufficientFundsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Insufficient Funds");
        alert.setHeaderText(null);
        alert.setContentText("Cannot bet. Insufficient funds.");

        alert.showAndWait();
    }

    /**
     * Cancel the bet and return all chips.
     */
    @FXML
    protected void onCancelButtonClick() {
        System.out.println("Bet Canceled");
        game.clearBet();
        updateScene();
    }

    /**
     * Deal the bet (chips) and start the game.
     * Switch to game scene and update balance text in UI.
     */
    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
    @FXML
    protected void onDealButtonClick() {
        if (game.getCurrentBetValue() > player.getAccountBalance()) {
            showInsufficientFundsAlert();
            return;
        }

        System.out.println("Bet Dealt Game Start");
        player.deal(game.getChips());
        game.start();

        // Update balance immediately after dealing
        player.getAccountBalance();  // You need to update this line based on how you update the balance

        switchToGameScene();
    }




    /**
     * Update player's balance in the scene.
     */
    private void updateScene() {
        if (game.getCurrentBetValue() == 0) {
            btnCancel.setDisable(true);
            btnDeal.setDisable(true);
        } else {
            btnCancel.setDisable(false);
            btnDeal.setDisable(false);
        }
        txtBetValue.setText(game.getCurrentBetValueFormatted());
        txtBalance.setText("$" + player.getAccountBalance());  // Adjust this line based on how you get the balance
    }


    private void switchToGameScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/playroom-game-view.fxml"));
            Parent root = fxmlLoader.load();

            // Assuming HomeController is properly initialized and has a reference to the primaryStage
            HomeController.getPrimaryStage().getScene().setRoot(root);

            PlayroomGameController gameController = fxmlLoader.getController();

            // Pass the existing player and game objects to the PlayroomGameController
            gameController.loadContents(player, game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

