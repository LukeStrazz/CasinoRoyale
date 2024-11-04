package app.casinoroyale.Model.DataModels.GameModels.SlotsModel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.security.SecureRandom;

public class SlotGameView {
    static SlotGame slotGame = new SlotGame();

    private static SecureRandom RANDOM = new SecureRandom();

    @SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
    private static int randomize() {
        int max = 13;
        int min = 0;
        RANDOM = new SecureRandom();
        int a = min + (int) (RANDOM.nextInt() * (double) (max - min + 1));
        return a;
    }

    public static void printResults(ArrayList<String> resultArrList, Label historyTF, Label winText) {
        StringBuilder pngsOrder = new StringBuilder();
        StringBuilder winOrder = new StringBuilder();
        int columnWidth = 15;
        for (int i = 0; i < 3; i++) {
            pngsOrder.append(String.format("%-" + columnWidth + "s", resultArrList.get(i)));
        }
        pngsOrder.append("\n");
        for (int i = 3; i < 6; i++) {
            pngsOrder.append(String.format("%-" + columnWidth + "s", resultArrList.get(i)));
        }
        pngsOrder.append("\n");
        for (int i = 6; i < 9; i++) {
            pngsOrder.append(String.format("%-" + columnWidth + "s", resultArrList.get(i)));
        }
        System.out.println();
        System.out.println(pngsOrder);
        System.out.println();
        historyTF.setText(String.valueOf(pngsOrder));

        Platform.runLater(()->{
            winOrder.setLength(0);
            if(slotGame.getFreq() >= 3) {

                winOrder.append(slotGame.getReq() + "  ");


                winOrder.append("\n\n"+"Value - "+ slotGame.getMultiplier()+"\n"+ "Multiplier - "+ slotGame.getIconValue() );


                winText.setText(String.valueOf(winOrder));
            }
            else winText.setText("");

        });}


    public static void spin(ImageView[] imageViewArray, Image[] imageArray) {
        for (int i = 0; i < 9; i++) {
            imageViewArray[i].setImage(imageArray[randomize()]);
        }
    }



    public static void infoButton() {
        String winningInfo = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNING CHANCES");
        alert.setHeaderText("IN ORDER TO WIN AT LEAST 3 OF THE ICON MATCH IS REQUIRED.");
        alert.setContentText(
                "      HERE IS THE MULTIPLIER FOR MATCHING DIFFERENT NUMBER OF SAME ICONS \n" +
                        "\n    3 ICONS:x2   4 ICONS: x4    5 ICONS: x10   6 ICONS: x25   7 ICONS: x35   8 ICONS: x70    \n\n " +
                        "                                                                             9 ICONS: x150"+
                        "\n\n$$$$$$$$$$$$$$$$$$ HERE IS THE VALUE OF EACH ICON $$$$$$$$$$$$$$$$$$$$" +
                        "\n\n $1           WATERMELON     SHAMROCK     BAR     LEMON"+
                        "\n\n $2           HORSESHOE     STAR    GOLD     **************************************************"+
                        "\n                                                                                    **************************************************" +
                        "\n $4           DIOMAND      PUMPKIN    GUN    **************            THIS IS FOR $3 BETS "+
                        "\n                                                                                    **************$6 BETS ARE DOUBLE THE PAYOUT"+

                        "\n $5           DRAGON     HOTDOGS        **************"+
                        "\n                                                                                    **************$9 BETS ARE TRIPLE THE PAYOUT" +
                        "\n $20          SEVEN              KING                                    **************"
        );
        alert.showAndWait();
    }

    public static void insufficientFunds() {
        String winningInfo = null;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("INSUFFICIENT FUNDS");
        alert.setHeaderText("                                 YOU BROKE!!!");
        alert.setContentText(
                "                           YOU GOT NO MONEY LOSER!!! ");
        alert.showAndWait();
    }


    public static void exitButton(){
        Platform.exit();
    }
}
