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
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Roulette  {

    private static Roulette instance;

    private final ObservableList<Bet> bets = FXCollections.observableArrayList();

    private final RouletteWheel rouletteWheel;
    
    private int winnings;

    private Roulette() {
        rouletteWheel = new RouletteWheel();

    }

    

    @SuppressFBWarnings({"SING_SINGLETON_GETTER_NOT_SYNCHRONIZED", "MS_EXPOSE_REP"})
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

    public static void infoButton() {
        String winningInfo = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ROULETTE STRATEGIES");
        alert.setHeaderText("WINNING AT ROULETTE REQUIRES KNOWLEDGE OF BETS AND ODDS.");
        alert.setContentText(
                "   BASIC BET TYPES AND THEIR PAYOUTS:\n" +
                        "\n    STRAIGHT (Single number): 35 to 1   SPLIT (Two numbers): 17 to 1\n" +
                        "    STREET (Three numbers): 11 to 1   CORNER (Four numbers): 8 to 1\n" +
                        "    LINE (Six numbers): 5 to 1\n\n" +
                        "    DOZEN/BET COLUMN (12 numbers): 2 to 1\n" +
                        "    EVEN/ODD, RED/BLACK, 1-18/19-36: 1 to 1\n\n" +
                        "    IF YOU ARE TO PLACE BET ON A WINNING ROLL, YOU WILL RECEIVE YOUR BET + WINNINGS \n\n" +
                        "    ************************** TIPS FOR BEGINNERS **************************\n" +
                        "\n    - Start with outside bets like Red/Black or Even/Odd for better odds.\n" +
                        "    - Manage your bankroll and avoid betting more than you can afford to lose.\n" +
                        "    - Remember, roulette is a game of chance; no strategy guarantees a win.\n" +
                        "    *************************************************************************\n" +
                        "\n$$$$$$$$$$$$$$$ REMEMBER TO PLAY RESPONSIBLY $$$$$$$$$$$$$$$$"
        );
        alert.showAndWait();
    }
}
