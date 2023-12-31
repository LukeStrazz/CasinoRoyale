/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.GameModels.RouletteModel;

public class Ball {

    private final int number;
    private final String color;
    private final int indexInWheel;

    public Ball(int number, int indexInWheel) {
        this.indexInWheel = indexInWheel;
        this.number = number;
        if (number == 15 || number == 4 || number == 2
                || number == 2 || number == 17 || number == 6
                || number == 13 || number == 11 || number == 8
                || number == 10 || number == 24 || number == 33
                || number == 20 || number == 31 || number == 22
                || number == 29 || number == 28 || number == 35
                || number == 26) {
            this.color = BallColors.BLACK.toString();
        } else if (number == 0) {
            this.color = BallColors.GREEN.toString();
        } else {
            this.color = BallColors.RED.toString();
        }
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }
    
    public int getIndexInWheel() {
        return indexInWheel;
    }
}
