/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.GameModels.RouletteModel;

import javafx.collections.ObservableList;

public class Bet {
    
    private final ObservableList<Integer> selectedNumbers;
    private final int bet;
    
    public Bet(ObservableList<Integer> selectedNumbers, int bet) {
        this.selectedNumbers = selectedNumbers;
        this.bet = bet;
    }
    
    public ObservableList<Integer> getSelectedNumbers() {
        return selectedNumbers;
    }
    
    public int getBet() {
        return bet;
    }
}
