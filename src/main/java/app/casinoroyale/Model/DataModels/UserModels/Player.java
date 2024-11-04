/*
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0
 */

package app.casinoroyale.Model.DataModels.UserModels;


import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.deck.Deck;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Chip;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.role.Cardholder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import app.casinoroyale.Controller.FirebaseControllers.Person;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class Player extends Cardholder {

    public static final Person person = null;
    private static Player instance = null;
    private double bet;

    public String name;
    private double accountBalance;

    @SuppressFBWarnings("SING_SINGLETON_HAS_NONPRIVATE_CONSTRUCTOR")
    public Player() {
        super();
    }

    @SuppressFBWarnings({"SING_SINGLETON_GETTER_NOT_SYNCHRONIZED", "MS_EXPOSE_REP"})
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void reset() {
        this.bet = 0;
        this.clearHand();
        // Reset any other game-specific information in the Player class
    }


    public void deal(ArrayList<Chip> chips) {
        bet = 0; // Reset the bet amount
        chips.forEach(chip -> bet += chip.getValue());
        accountBalance -= bet;
    }


    public void doubleDeal() {
        bet *= 2;
        accountBalance -= bet; // Deduct the adjusted bet amount
    }


    @SuppressFBWarnings("PA_PUBLIC_PRIMITIVE_ATTRIBUTE")
    public String getName(){return name;}
    public void setName(String name) {
        this.name = name;
    }

    public void hit(Deck deck) {
        takeCard(deck);
    }

    public void setBalance(double balance) {
        this.accountBalance = balance;
    }

    public double getBet() {
        return bet;
    }

    public double getBalance() {
        return accountBalance;
    }

    public String getAccountBalanceFormatted() {
        DecimalFormat formatter = new DecimalFormat("\t$###,###");
        return formatter.format(accountBalance);
    }
    public void setAccountBalanceFromFirebase(Double balance){
        this.accountBalance  = balance;
    }
    public double getAccountBalance() {
        return accountBalance;
    }


    public void setBlackJackAccountBalance(double balance) {
        this.accountBalance = balance;
    }
    public void setAccountBalance(double balance) {
        this.accountBalance += balance;
    }

    /**
     * Checks if the player can withdraw the specified amount.
     * @param amount The amount to check for withdrawal.
     * @return true if withdrawal is possible, false otherwise.
     */
    public boolean canWithdraw(double amount) {
        return amount > 0 && this.accountBalance >= amount;
    }

    /**
     * Withdraws the specified amount from the player's account balance.
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            setAccountBalance(-amount); // Negative value to deduct from balance
        } else {
            // Handle the case where withdrawal is not possible
            // This could be throwing an exception or logging an error
            throw new IllegalArgumentException("Cannot withdraw the specified amount. Insufficient funds or invalid amount.");
        }
    }


}