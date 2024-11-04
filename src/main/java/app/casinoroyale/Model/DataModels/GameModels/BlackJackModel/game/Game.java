package app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game;

import app.casinoroyale.Controller.FirebaseControllers.PrimaryController;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.deck.Deck;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.role.Dealer;
import app.casinoroyale.Model.DataModels.UserModels.Player;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Game class runs a single blackjack game session.
 * A game session is to be destroyed when the game ends.
 */
public class Game {
    private app.casinoroyale.Controller.FirebaseControllers.PrimaryController primaryController;

    private final Player player;
    private final Dealer dealer;
    private final Deck deck;
    private final ArrayList<Chip> chips;
    private GameStatus gameStatus;

    public Game(Player player) {
        this.primaryController = new PrimaryController();
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.chips = new ArrayList<>();
        this.player = player.getInstance();
        this.gameStatus = GameStatus.CONTINUE;


    }

    /**
     * Game start. Shuffle the deck. Dealer and player draw 2 cards each from the deck.
     */

    public void start() {
        deck.shuffle();
        dealer.takeCard(deck);
        dealer.takeCard(deck);
        player.takeCard(deck);
        player.takeCard(deck);

    }

    /**
     * Increase bet by placing one more chip in betting box.
     * @param chip A chip.
     */
    public void increaseBet(Chip chip) {
        chips.add(chip);
    }

    /**
     * Clear all the chips in betting box.
     */
    public void clearBet() {
        chips.clear();
    }

    public void reset() {
        player.reset();  // Add a reset method in the Player class
        dealer.clearHand();
        player.clearHand();
        chips.clear();
        gameStatus = GameStatus.CONTINUE;
    }




    /**
     * Calculate current bet value.
     * @return bet value.
     */
    public int getCurrentBetValue() {
        return chips.stream().mapToInt(Chip::getValue).sum();
    }

    /**
     * Calculate current bet value and format.
     * @return bet value string formatted as "Bet: $1,000"
     */
    public String getCurrentBetValueFormatted() {
        DecimalFormat formatter = new DecimalFormat("\t$###,###");
        return "Bet: " + formatter.format(getCurrentBetValue());
    }

    /**
     * Update gameStatus based on whether player has busted.
     */
    public void checkAfterHit() {
        if (player.getTotalPoints() > 21)
            gameStatus = GameStatus.PLAYER_BUSTED;
        else
            gameStatus = GameStatus.CONTINUE;
    }

    /**
     * Update gameStatus based on player and dealer's hands.
     */
    public void checkAfterStand() {
        if (dealer.getTotalPoints() > 21) {
            if (player.getTotalPoints() == 21 && player.getHand().size() == 2)
                gameStatus = GameStatus.PLAYER_BLACKJACK;
            else
                gameStatus = GameStatus.PLAYER_WIN;
        } else if (dealer.getTotalPoints() == 21) {
            if (player.getTotalPoints() == 21 && player.getHand().size() == 2)
                gameStatus = GameStatus.PLAYER_BLACKJACK;
            else if (player.getTotalPoints() == 21)
                gameStatus = GameStatus.PUSH;
            else
                gameStatus = GameStatus.DEALER_WIN;
        } else if (dealer.getTotalPoints() > player.getTotalPoints()) {
            gameStatus = GameStatus.DEALER_WIN;
        } else if (dealer.getTotalPoints() == player.getTotalPoints()) {
            gameStatus = GameStatus.PUSH;
        } else {
            if (player.getTotalPoints() == 21 && player.getHand().size() == 2)
                gameStatus = GameStatus.PLAYER_BLACKJACK;
            else
                gameStatus = GameStatus.PLAYER_WIN;
        }
        rewardPlayer();
    }

    /**
     * Reward player according to gameStatus and player's previous bet.
     */
    private void rewardPlayer() {
        double originalBet = player.getBet();

        if (getGameStatus() == GameStatus.PLAYER_BLACKJACK) {
            player.setBlackJackAccountBalance(player.getAccountBalance() + originalBet * 3);
        } else if (getGameStatus() == GameStatus.PUSH) {
            player.setBlackJackAccountBalance(player.getAccountBalance() + originalBet);
        } else if (getGameStatus() == GameStatus.PLAYER_WIN) {
            player.setBlackJackAccountBalance(player.getAccountBalance() + originalBet * 2);
        }
        primaryController.updateBalance(player.getAccountBalance());
    }



    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Dealer getDealer() {
        return new Dealer();
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Chip> getChips() {
        return new ArrayList<>(chips);
    }
}
