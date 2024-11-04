/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.GameModels.RouletteModel;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouletteWheel {
    
    List<Integer> wheelNumbers;
    
    private Ball ball;
    private static final SecureRandom RANDOM = new SecureRandom();

    public RouletteWheel() {
        wheelNumbers = Arrays.asList(0, 26, 3, 35, 12, 28, 7, 29, 18, 22, 9, 31, 14, 20, 1, 33, 16, 24, 5, 10, 23, 8, 30, 11, 36, 13, 27, 6, 34, 17, 25, 2, 21, 4, 19, 15, 32);
    }
    
    public Ball spinWheel() {
        
        SecureRandom randomNumber = (RANDOM);
        int index = wheelNumbers.indexOf(randomNumber.nextInt(36));
        
        this.ball = new Ball(randomNumber.nextInt(36), index);
        return ball;
    }
    
    public List<Integer> getWheelNumbers() {
        List<Integer> newWheelList = new ArrayList<>();
        for (Integer wheelNumber : wheelNumbers) {
            newWheelList.add(wheelNumber);
        }

        return newWheelList;
    }
    
    public Ball getBall() {
        return ball;
    }
}
