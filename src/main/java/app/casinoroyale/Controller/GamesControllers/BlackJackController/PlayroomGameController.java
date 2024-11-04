package app.casinoroyale.Controller.GamesControllers.BlackJackController;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.deck.Card;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Game;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.GameStatus;
import app.casinoroyale.Model.DataModels.UserModels.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PlayroomGameController  {

    Player player;
    Game game;

    @FXML
    Text txtBalance;
    @FXML
    Text txtPlayerPoint;
    @FXML
    Text txtDealerPoint;
    @FXML
    StackPane playerPointBanner;
    @FXML
    StackPane dealerPointBanner;

    @FXML
    Text txtWinInfo;
    @FXML
    Text txtLoseInfo;
    @FXML
    StackPane winBanner;
    @FXML
    StackPane loseBanner;

    @FXML
    Button btnStand;
    @FXML
    Button btnDouble;
    @FXML
    Button btnNewGame;
    @FXML
    Button btnHit;

    @FXML
    ImageView playerCard1;
    @FXML
    ImageView playerCard2;
    @FXML
    ImageView playerCard3;
    @FXML
    ImageView playerCard4;
    @FXML
    ImageView playerCard5;
    @FXML
    ImageView playerCard6;
    @FXML
    ImageView playerCard7;
    @FXML
    ImageView playerCard8;

    @FXML
    ImageView dealerCard1;
    @FXML
    ImageView dealerCard2;
    @FXML
    ImageView dealerCard3;
    @FXML
    ImageView dealerCard4;
    @FXML
    ImageView dealerCard5;
    @FXML
    ImageView dealerCard6;
    @FXML
    ImageView dealerCard7;
    @FXML
    ImageView dealerCard8;

    /**
     * Load player and game into the view.
     *
     * @param player player to been shown on the UI as well as who plays the game
     * @param game   the game which player has started
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public void loadContents(Player player, Game game) {
        // Initialize the game object
        this.game = game; // or obtain reference through some other means
        this.player = player.getInstance();

        // Other initialization code

        updateScene();
        updateButtonStatus();
    }

    /**
     * Player takes no more cards.
     */
    @FXML
    protected void onStandButtonClick() {
        System.out.println("Player Stand");
        game.getDealer().hitUntilSeventeen(game.getDeck());
        game.checkAfterStand();

        updateScene();
        updateButtonStatus();
    }

    /**
     * Player takes another card.
     */
    @FXML
    protected void onHitButtonClick() {
        System.out.println("Player Hit");
        player.hit(game.getDeck());
        game.checkAfterHit();

        updateScene();
        updateButtonStatus();
    }

    /**
     * Player double the bet and take exactly one more card.
     */
    @FXML
    protected void onDoubleButtonClick() {
        System.out.println("Player Double");

        player.doubleDeal();
        player.hit(game.getDeck());
        game.checkAfterHit();

        if (game.getGameStatus() == GameStatus.CONTINUE) {
            game.getDealer().hitUntilSeventeen(game.getDeck());
            game.checkAfterStand();
        }

        btnDouble.setDisable(true);
        updateScene();
        updateButtonStatus();
    }

    /**
     * Switch to bet scene if user choose to start a new game.
     */
    @FXML
    protected void onNewGameButtonClick() {
        System.out.println("New Game");
        switchToBetScene();
    }

    /**
     * Update cards' image, player's balance and all banners in the scene.
     */
    private void updateScene() {

        /* These lists help keep track of all the cards displayed on the UI. */
        ArrayList<ImageView> imgPlayerCards = new ArrayList<>(
                List.of(playerCard1, playerCard2, playerCard3, playerCard4,
                        playerCard5, playerCard6, playerCard7, playerCard8)
        );
        ArrayList<ImageView> imgDealerCards = new ArrayList<>(
                List.of(dealerCard1, dealerCard2, dealerCard3, dealerCard4,
                        dealerCard5, dealerCard6, dealerCard7, dealerCard8)
        );

        /* Update player's hand. */
        Iterator<Card> playerHand = player.getHand().iterator();
        for (ImageView img : imgPlayerCards) {
            if (playerHand.hasNext()) {
                String url = playerHand.next().getImageUrl();
                System.out.println("Resource URL: " + url);
                Image image = new Image(Objects.requireNonNull(getClass().getResource(url)).toString());
                System.out.println("Image URL: " + image.getUrl());
                img.setVisible(true);
                img.setImage(image);
            } else {
                img.setVisible(false);
            }
        }

        /* Update dealer's hand. */
        Iterator<Card> dealerHandIter = game.getDealer().getHand().iterator();
        for (ImageView img : imgDealerCards) {
            if (dealerHandIter.hasNext()) {
                String url = dealerHandIter.next().getImageUrl();
                Image image = new Image(Objects.requireNonNull(getClass().getResource(url)).toString());

                /* Set the second card to be hidden if dealer have 2 cards. */
                if (game.getDealer().getHand().size() == 2
                        && game.getGameStatus() == GameStatus.CONTINUE
                        && !dealerHandIter.hasNext()
                ) {
                    url = "/app/Assets/BlackJack/img/card_back_1.png";
                    image = new Image(Objects.requireNonNull(getClass().getResource(url)).toString());
                }

                img.setVisible(true);
                img.setImage(image);
            } else {
                img.setVisible(false);
            }
        }

        /* Update player's balance. */
        txtBalance.setText(player.getAccountBalanceFormatted());

        /* Update player's point. */
        txtPlayerPoint.setText(String.valueOf(player.getTotalPoints()));

        /* Update dealer's point. Hide dealer's point banner if dealer have 2 cards. */
        if (game.getDealer().getHand().size() == 2 && game.getGameStatus() == GameStatus.CONTINUE) {
            dealerPointBanner.setVisible(false);
        } else {
            dealerPointBanner.setVisible(true);
            txtDealerPoint.setText(String.valueOf(game.getDealer().getTotalPoints()));
        }

        /* Update game status banner */
        switch (game.getGameStatus()) {
            case PUSH:
                loseBanner.setVisible(true);
                txtLoseInfo.setText("PUSH...");
                break;
            case PLAYER_WIN:
                winBanner.setVisible(true);
                txtWinInfo.setText("YOU WIN!");
                break;
            case PLAYER_BLACKJACK:
                winBanner.setVisible(true);
                txtWinInfo.setText("BLACKJACK!");
                break;
            case PLAYER_BUSTED:
                loseBanner.setVisible(true);
                txtLoseInfo.setText("BUSTED...");
                break;
            case DEALER_WIN:
                loseBanner.setVisible(true);
                txtWinInfo.setText("YOU LOSE");
                break;
            case CONTINUE:
        }
    }

    /**
     * Update buttons' status base on gameStatus.
     */
    private void updateButtonStatus() {
        switch (game.getGameStatus()) {
            case PUSH:
            case PLAYER_WIN:
            case PLAYER_BLACKJACK:
            case PLAYER_BUSTED:
            case DEALER_WIN:
                btnStand.setDisable(true);
                btnHit.setDisable(true);
                btnDouble.setDisable(true);
                btnNewGame.setVisible(true);
                break;
            case CONTINUE:
                if (player.getHand().size() > 2) {
                    btnDouble.setDisable(true);
                }

                // Disable the new game button if the player's balance is less than $0
                if (player.getAccountBalance() < 0) {
                    btnNewGame.setDisable(true);
                }
        }
    }





    private void switchToBetScene() {
        game.reset();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/playroom-bet-view.fxml"));
            Parent root = fxmlLoader.load();

            // Assuming HomeController is properly initialized and has a reference to the primaryStage
            HomeController.getPrimaryStage().getScene().setRoot(root);

            PlayroomBetController betController = fxmlLoader.getController();

            // Pass the existing player object to the bet controller
            betController.loadContents(Player.getInstance());  // Use a fresh instance of Player

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

