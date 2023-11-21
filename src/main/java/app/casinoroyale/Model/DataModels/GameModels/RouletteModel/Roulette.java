/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.GameModels.RouletteModel;

import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Roulette  {

    private static Roulette instance;

    private final ObservableList<Bet> bets = FXCollections.observableArrayList();

    private final RouletteWheel rouletteWheel;
    
    private int winnings;

    private Roulette() {
        rouletteWheel = new RouletteWheel();

    }

    

    public static Roulette getInstance() {
        if(instance == null) {
            instance = new Roulette();
        }
        return instance;
    }

    public int checkBet() {
        int ballNumber = -1;
        winnings = 0;
        Ball ball = rouletteWheel.spinWheel();

        
        
        for (int i = 0; i < bets.size(); i++) {
            if (bets.get(i).getSelectedNumbers().contains(ball.getNumber())) {
                ballNumber = ball.getNumber();
                int winning = getWinning(bets.get(i).getBet(), getRatio(bets.get(i).getSelectedNumbers()));

                

               
                winnings += winning;
                System.out.println(winnings);
            } else {
               
                
            }
        }
        Player.getInstance().setAccountBalance(winnings);
        
        bets.clear();
        return ballNumber;
    }

    public int getIndexInWheel() {
        return rouletteWheel.getBall().getIndexInWheel();
    }

    public int getNumber() {
        return rouletteWheel.getBall().getNumber();
    }

    public int getWinning(int bet, int ratio) {
        return (bet * ratio)+bet;
    }

    public int getRatio(ObservableList<Integer> selectedNumbers) {
        int ratio = ((36 / selectedNumbers.size()) - 1);
        System.out.println(ratio);
        return ratio;
    }

    public void addBet(Bet bet) {
        bets.add(bet);
    }

    public ObservableList<Bet> getBets() {
        return bets;
    }
    
    public int getWinnings() {
        return winnings;
    }
}
