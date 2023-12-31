package app.casinoroyale.Controller.GamesControllers;
import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.FirebaseControllers.PrimaryController;
import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Model.DataModels.GameModels.RouletteModel.Bet;
import app.casinoroyale.Model.DataModels.GameModels.RouletteModel.Roulette;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.SlotGameView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import app.casinoroyale.Model.DataModels.UserModels.Player;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import app.casinoroyale.Controller.FirebaseControllers.PrimaryController;

import java.io.IOException;

import static app.casinoroyale.Model.DataModels.GameModels.RouletteModel.Roulette.infoButton;

public class RouletteController implements Initializable{

    private app.casinoroyale.Controller.HomeController homeController;

    private Stage stage;
    private app.casinoroyale.Controller.FirebaseControllers.PrimaryController primaryController;

    public RouletteController(){
       this.homeController = new HomeController();
       this.stage = new Stage();
    }
    @FXML
    private void playBlackJack(ActionEvent event) throws IOException {
        homeController.playBlackJack(event);
    }
    @FXML
    private void playHorseRacing(ActionEvent event) throws IOException {
        homeController.playHorseRacing(event);
    }
    @FXML
    private void playSlots(ActionEvent event) throws IOException {
        homeController.playSlots(event);
    }
    @FXML
    private void homeDash(ActionEvent event) throws IOException {
        homeController.homeDash(event);
    }
    @FXML
    private void launchBank(ActionEvent event) throws IOException {
        homeController.launchBank(event);
    }

    /**
     * FXML Declarations
     */
    @FXML private Label lbl0, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, lbl19, lbl20, lbl21, lbl22, lbl23, lbl24, lbl25, lbl26, lbl27, lbl28, lbl29, lbl30, lbl31, lbl32, lbl33, lbl34, lbl35, lbl36;
    @FXML private Label lbl1to18, lblEven, lblOdd, lbl19to36, lbl1st12, lbl2nd12, lbl3rd12, lbl2to1Row1, lbl2to1Row2, lbl2to1Row3, lblRed, lblBlack;
    @FXML private Label lblBalance, lblBetAmount, lblPlay, lblRandomNumber, lblWinnings;
    @FXML private ImageView chip0, chip1, chip5, chip10, chip50, chip100, imgViewPlay, imgViewRouletteWheel, imgViewBall, imgViewRouletteWheelMid;
    @FXML private Pane draggablePane, numberPane, paneStreet1, paneStreet2, paneStreet3, paneStreet4, paneStreet5, paneStreet6, paneStreet7, paneStreet8, paneStreet9, paneStreet10, paneStreet11, paneStreet12;
    @FXML private AnchorPane mainPane;
    @FXML private Ellipse ellipseCover;
    @FXML private Rectangle rectangle1;

    //Label that hovers over a placed chip.
    private final Label lblChipHover = new Label();

    @FXML
    private ImageView selectedImageView, infoButtonImage;
    @FXML
    private ImageView imgViewTutorial, goBack, minimize, nextImg;

    private double x, y;
    private double mainSceneX, mainSceneY;
    private double chipXOrigin, chipYOrigin;
    private int index;


    private final boolean infoOpened = false;

    private int lastAddedAmount = 0;

    private final ObservableList<Label> numbers = FXCollections.observableArrayList();
    private final ArrayList<ImageView> chips = new ArrayList<>();
    private final ObservableList<Label> rangeNumbers = FXCollections.observableArrayList();
    private final ObservableList<Pane> streetNumbers = FXCollections.observableArrayList();

    int betAmount = 0;

    private int winningNumber;
    private Player player;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.primaryController = new PrimaryController();

        player = Player.getInstance();

        // Adds the label that allows the hovering over the chip to retrieve the bet amount.
        mainPane.getChildren().add(lblChipHover);
        lblChipHover.setVisible(false);

        // Adds the initial chip.
       addChip();

       // Gets the x and y coordinates of the chip's origin.
       Bounds chip = chips.get(0).getBoundsInLocal();
       chipXOrigin = chip.getMinX();
       chipYOrigin = chip.getMinY();

        this.stage = HomeController.getPrimaryStage();

        initNumbersArrayList();

        // Gets the account balance from the user out of the database and sets it to a label.
        lblBalance.setText(String.valueOf(player.getAccountBalance()));

        chip1.setOnMouseClicked(e -> {
            betAmount += 1;
            lastAddedAmount = 1;
            lblBetAmount.setText(Integer.toString(betAmount));
        });

        chip5.setOnMouseClicked(e -> {
            betAmount += 5;
            lastAddedAmount = 5;
            lblBetAmount.setText(Integer.toString(betAmount));
        });

        chip10.setOnMouseClicked(e -> {
            betAmount += 10;
            lastAddedAmount = 10;
            lblBetAmount.setText(Integer.toString(betAmount));
        });

        chip50.setOnMouseClicked(e -> {
            betAmount += 50;
            lastAddedAmount = 50;
            lblBetAmount.setText(Integer.toString(betAmount));
        });

        chip100.setOnMouseClicked(e -> {
            betAmount += 100;
            lastAddedAmount = 100;
            lblBetAmount.setText(Integer.toString(betAmount));
        });

        /**
         * Sets the mouse click action for the play button.
         */
        imgViewPlay.setOnMouseClicked(e -> {
            imgViewPlay.setDisable(true);
            imgViewPlay.setOpacity(0.5);
            lblPlay.setOpacity(0.5);
            lblWinnings.setText("0");

            ellipseCover.setVisible(false);
            lblRandomNumber.setVisible(false);
            imgViewRouletteWheelMid.setVisible(true);

            winningNumber = Roulette.getInstance().checkBet();
            handleCircleAnimation(Roulette.getInstance().getIndexInWheel());
        });

        /**
         * Since we're using an undecorated stage, this is needed to move the window around.
         */
        draggablePane.setOnMousePressed(e -> {
            x = stage.getX() - e.getScreenX();
            y = stage.getY() - e.getScreenY();
        });

        draggablePane.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + x);
            stage.setY(e.getScreenY() + y);
        });

    }

    /**
     * Adds a new chip to the view.
     */
    private void addChip() {
        ImageView chip = new ImageView();
        chip.setImage(chip0.getImage());
        mainPane.getChildren().add(chip);

        chip.setX(775);
        chip.setY(615);
        chip.setFitWidth(44);
        chip.setFitHeight(44);

        chips.add(chip);
        setChipActions();
        handleFadeTransition(chips.get(chips.size() - 1));
    }

    /**
     * Universal method that handles the fade transition.
     * @param node
     */
    private void handleFadeTransition(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    /**
     * Method that moves the chip back to it's origin.
     * @param x
     * @param y
     */
    private void handleTimeLineAnimation(double x, double y) {

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        final KeyValue chipX = new KeyValue(chips.get(chips.size() - 1).xProperty(), x);
        final KeyFrame chipXKf = new KeyFrame(Duration.millis(500), chipX);

        final KeyValue chipY = new KeyValue(chips.get(chips.size() - 1).yProperty(), y);
        final KeyFrame chipYKf = new KeyFrame(Duration.millis(500), chipY);

        timeline.getKeyFrames().addAll(chipXKf, chipYKf);
        timeline.play();
    }

    /**
     * Handles the circular animation, that is used for the roulette wheel.
     * @param number
     */
    private void handleCircleAnimation(int number) {
        imgViewBall.setRotate(0);
        imgViewRouletteWheelMid.setRotate(0);

        RotateTransition rT = new RotateTransition(Duration.millis(1000));
        rT.setByAngle(-360);
        rT.setInterpolator(Interpolator.LINEAR);
        rT.setCycleCount(3);

        RotateTransition rTVar = new RotateTransition(Duration.millis(1000));
        rTVar.setByAngle(-(9.73 * number));
        rTVar.setCycleCount(1);

        RotateTransition rTMid = new RotateTransition(Duration.millis(4000));
        rTMid.setByAngle(1080 + 9.73 * number);
        rTMid.setInterpolator(Interpolator.EASE_OUT);
        rTMid.setCycleCount(1);
        rTMid.setNode(imgViewRouletteWheelMid);


        SequentialTransition seqT = new SequentialTransition(imgViewBall, rT, rTVar);

        ParallelTransition parT = new ParallelTransition(rTMid, seqT);

        parT.play();

        // If the animation is finished, the following code snippet is being run.
        parT.setOnFinished(e -> {
            ellipseCover.setVisible(true);
            lblRandomNumber.setVisible(true);
            imgViewRouletteWheelMid.setVisible(false);
            lblRandomNumber.setText(Integer.toString(Roulette.getInstance().getNumber()));
            lblBalance.setText(Double.toString(player.getAccountBalance()));
            lblWinnings.setText(Integer.toString(Roulette.getInstance().getWinnings()));
            Player player = Player.getInstance();
            primaryController.updateBalance(player.getAccountBalance());

            // Removes all the chips on the screen.
            for(int i = 0; i <chips.size() - 1; i++) {
                mainPane.getChildren().remove(chips.get(i));
            }
        });
    }

    /**
     * Removes the actions on the chips, so that they can't be moved anymore once they're placed.
     */
    public void unsetChipAction() {
        chips.get(chips.size() - 1).setOnMousePressed(null);
        chips.get(chips.size() - 1).setOnMouseDragged(null);
        chips.get(chips.size() - 1).setOnMouseReleased(null);
    }

    /**
     * Sets the actions for every newly generated chip.
     */
    public void setChipActions() {

        chips.get(chips.size() - 1).setOnMousePressed(e -> {
            mainSceneX = e.getSceneX();
            mainSceneY = e.getSceneY();
        });

        chips.get(chips.size() - 1).setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - mainSceneX;
            double offsetY = e.getSceneY() - mainSceneY;

            chips.get(chips.size() - 1).setX(chips.get(chips.size() - 1).getX() + offsetX);
            chips.get(chips.size() - 1).setY(chips.get(chips.size() - 1).getY() + offsetY);

            mainSceneX = e.getSceneX();
            mainSceneY = e.getSceneY();
        });


        chips.get(chips.size() - 1).setOnMouseReleased(e -> {
            int intersects = 0;

            ObservableList<Integer> selectedNumbers = FXCollections.observableArrayList();

            Bounds boundsChip = chips.get(chips.size() - 1).localToScene(chips.get(chips.size() - 1).getBoundsInLocal());

            for (int i = 0; i < numbers.size(); i++) {

                Bounds labelBounds = numbers.get(i).localToScene(numbers.get(i).getBoundsInLocal());


                if (boundsChip.intersects(labelBounds)) {
                    selectedNumbers.add(Integer.parseInt(numbers.get(i).getText()));
                    intersects++;
                }

            }

            for(int i = 0; i < rangeNumbers.size(); i++) {

                Bounds labelBounds = rangeNumbers.get(i).localToScene(rangeNumbers.get(i).getBoundsInLocal());

                if(labelBounds.contains(boundsChip)) {
                    selectedNumbers = getNumbersByRange(rangeNumbers.get(i).getId());
                    intersects = 1;
                }

            }

            for(int i = 0; i < streetNumbers.size(); i++) {
                Bounds rectangleBounds = streetNumbers.get(i).localToScene(streetNumbers.get(i).getBoundsInLocal());

                if(rectangleBounds.contains(boundsChip)) {
                    selectedNumbers = getNumbersByRange(streetNumbers.get(i).getId());
                    intersects = 1;
                }
            }

            if (intersects == 0 || Integer.parseInt(lblBetAmount.getText()) == 0 || betAmount > Player.getInstance().getAccountBalance()) {
                handleTimeLineAnimation(chipXOrigin, chipYOrigin);
            } else if (intersects > 0 && Integer.parseInt(lblBetAmount.getText()) > 0){
                Roulette.getInstance().addBet(new Bet(selectedNumbers, Integer.parseInt(lblBetAmount.getText())));

                Player.getInstance().setAccountBalance(-Integer.parseInt(lblBetAmount.getText()));
                lblBalance.setText(Double.toString(Player.getInstance().getAccountBalance()));

                lblBetAmount.setText("0");
                betAmount = 0;
                unsetChipAction();
                setChipHoverAction();
                addChip();

                imgViewPlay.setDisable(false);
                imgViewPlay.setOpacity(1);

                lblPlay.setOpacity(1);

            }
        });

    }

    /**
     * Input the number range as a string and return an array list with all the numbers that are in the specified range.
     * @param range
     * @return
     */
    public ObservableList<Integer> getNumbersByRange(String range) {
        int min = 0;
        int max = 0;
        int interval = 1;

        ObservableList<Integer> selectedNumbers = FXCollections.observableArrayList();

        switch (range) {
            case "lbl0":
                min=0;
                max=0;
                break;
            case "lbl1to18":
                min = 1;
                max = 18;
                break;
            case "lbl19to36":
                min = 19;
                max = 36;
                break;
            case "lbl1st12":
                min = 1;
                max = 12;
                break;
            case "lbl2nd12":
                min = 13;
                max = 24;
                break;
            case "lbl3rd12":
                min = 25;
                max = 36;
                break;
            case "lbl2to1Row1":
                min = 3;
                max = 36;
                interval = 3;
                break;
            case "lbl2to1Row2":
                min = 2;
                max = 35;
                interval = 3;
                break;
            case "lbl2to1Row3":
                min = 1;
                max = 34;
                interval = 3;
                break;
            case "lblEven":
                min = 2;
                max = 36;
                interval = 2;
                break;
            case "lblOdd":
                min = 1;
                max = 35;
                interval = 2;
                break;
            case "paneStreet1":
                min = 1;
                max = 3;
                break;
            case "paneStreet2":
                min = 4;
                max = 6;
                break;
            case "paneStreet3":
                min = 7;
                max = 9;
                break;
            case "paneStreet4":
                min = 10;
                max = 12;
                break;
            case "paneStreet5":
                min = 13;
                max = 15;
                break;
            case "paneStreet6":
                min = 16;
                max = 18;
                break;
            case "paneStreet7":
                min = 19;
                max = 21;
                break;
            case "paneStreet8":
                min = 22;
                max = 24;
                break;
            case "paneStreet9":
                min = 25;
                max = 27;
                break;
            case "paneStreet10":
                min = 28;
                max = 30;
                break;
            case "paneStreet11":
                min = 31;
                max = 33;
                break;
            case "paneStreet12":
                min = 34;
                max = 36;
                break;
        }

        if(max > 1) {
            for(int i = min; i <= max; i += interval) {
                selectedNumbers.add(i);
            }
        } else {
            if(range.equals("lblBlack")) {
                selectedNumbers.addAll(15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26);
            } else if(range.equals("lblRed")){
                selectedNumbers.addAll(32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3);
            }
        }

        System.out.println(selectedNumbers);
        return selectedNumbers;
    }

    public void setChipHoverAction() {
        // Check if the chips list is empty to avoid IndexOutOfBoundsException
        if (chips.isEmpty()) {
            return; // Early return if the chips list is empty
        }

        // Set the hover action for each chip in the list
        for (ImageView chip : chips) {
            chip.setOnMouseEntered(e -> {
                ImageView currentChip = (ImageView) e.getSource();
                Bounds chipBounds = currentChip.localToScene(currentChip.getBoundsInLocal());

                if (!(chipBounds.getMinX() == chipXOrigin) && !(chipBounds.getMinY() == chipYOrigin && !e.isDragDetect())) {
                    int index = chips.indexOf(currentChip);

                    // Check if the index is valid for the bets list
                    List<Bet> bets = Roulette.getInstance().getBets();
                    System.out.println("newb bets" + bets.size()+ " bet "+ bets.get(index).getBet()  +  " index" + bets.indexOf(currentChip));
                    if (index >= 0 && index < bets.size()) {
                        lblChipHover.setText(String.valueOf(bets.get(index).getBet()));
                    } else {
                        lblChipHover.setText("Invalid bet"); // or handle the error as appropriate
                    }

                    lblChipHover.setMinWidth(76);
                    lblChipHover.setMaxWidth(100);
                    lblChipHover.setVisible(true);
                    lblChipHover.setFont(new Font(32));
                    lblChipHover.setStyle("-fx-background-color: #bf4722; -fx-text-fill: #ffd454; -fx-padding: 10px");

                    // Recalculate bounds in case they have changed
                    chipBounds = currentChip.localToScene(currentChip.getBoundsInLocal());

                    // Calculate the position of lblChipHover dynamically
                    double layoutX = (chipBounds.getMinX() + chipBounds.getMaxX()) / 2 - (lblChipHover.getWidth() / 2);
                    double layoutY = chipBounds.getMinY() - lblChipHover.getHeight() - 10; // Adjust Y position above the chip

                    // Set the position of lblChipHover
                    lblChipHover.setLayoutX(layoutX);
                    lblChipHover.setLayoutY(layoutY);

                    lblChipHover.toFront();
                }
            });

            chip.setOnMouseExited(e -> {
                lblChipHover.setVisible(false);
            });
        }
    }



    /**
     * Initializes the label number array list
     */
    public void initNumbersArrayList() {
        ObservableList<Node> children = numberPane.getChildren();

        for (Node n : children) {
            numbers.add((Label) n);
        }

        rangeNumbers.addAll(lbl0, lbl1to18, lblEven, lblOdd, lbl19to36, lbl1st12, lbl2nd12, lbl3rd12, lbl2to1Row1, lbl2to1Row2, lbl2to1Row3, lblRed, lblBlack);
        streetNumbers.addAll(paneStreet1, paneStreet2, paneStreet3, paneStreet4, paneStreet5, paneStreet6, paneStreet7, paneStreet8, paneStreet9, paneStreet10, paneStreet11, paneStreet12);
    }



    /**
     * Hover effect for all the buttons.
     */
    @FXML
    private void highlightSquareButton(MouseEvent event) {
        selectedImageView = ((ImageView) event.getSource());
        selectedImageView.setImage(imgViewPlay.getImage());
    }

    @FXML
    private void unHighlightSquareButton(MouseEvent event) {
        selectedImageView.setImage(imgViewPlay.getImage());
    }

    @FXML
    private void highlightRoundButton(MouseEvent event) {
        selectedImageView = ((ImageView) event.getSource());
        selectedImageView.setImage(nextImg.getImage());
    }



    @FXML
    public void goBack(MouseEvent event) {
        Player player = Player.getInstance();
        primaryController.updateBalance(player.getAccountBalance());
        Platform.exit();
    }
    @FXML
    public void infoButtonDisplay(MouseEvent event) {
        infoButton();
    }



    /**
     * Opens the step by step tutorial.
     * @param event
     */
    @FXML
    public void nextImage(MouseEvent event) {
        // Adapted version of code from Andrea Mangione.
        index++;
        try {
            imgViewTutorial.setVisible(true);
            imgViewTutorial.setImage(new Image("../../../images/roulette/tutorial/" + index + ".png"));
        } catch(Exception ex) {
            index = 0;
            imgViewTutorial.setVisible(false);
        }
    }

    /**
     * Resets the pending bet amount.
     * @param event
     */
    @FXML
    private void handleResetBetAmount(MouseEvent event) {
        this.betAmount = 0;
        lblBetAmount.setText(Integer.toString(betAmount));
    }

    /**
     * Reverts the last set amount.
     * @param event
     */
    @FXML
    private void handleRevertLastAction(MouseEvent event) {
        if(this.betAmount > lastAddedAmount) {
            this.betAmount -= lastAddedAmount;
            lblBetAmount.setText(Integer.toString(betAmount));
        }
    }

}
