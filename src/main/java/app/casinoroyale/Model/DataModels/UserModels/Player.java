/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.UserModels;

import app.casinoroyale.Controller.FirebaseControllers.Person;

public class Player extends Person {
    public static final Person person = null;
    private static Player instance = null;
   
    private double accountBalance;
    
    
    /**
     * Private constructor due to the use of the singleton-pattern.
     */

    public Player() {
        super();
    }

    /**
     * If there is no player-object, it gets created, otherwise it just returns the aforementioned object.
     * @return Player object
     */
    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }
    
   

    public double getAccountBalance() {
        return accountBalance;
    }
    
    /**
     * Adds the difference of balance to the player's account balance.
     * @param difference 
     */
    public void setAccountBalance(double difference) {
        this.accountBalance += difference;
    } 
    
    
    
    
}
