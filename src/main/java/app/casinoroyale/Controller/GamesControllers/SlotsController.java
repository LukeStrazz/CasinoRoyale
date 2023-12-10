package app.casinoroyale.Controller.GamesControllers;
import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.FirebaseControllers.PrimaryController;
import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import java.io.File;
import java.io.IOException;
import java.util.*;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.*;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.SlotGame;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import com.google.cloud.firestore.Firestore;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;



public class SlotsController {
    @FXML
    private ImageView imageBlock1, imageBlock2, imageBlock3, imageBlock4, imageBlock5, imageBlock6, imageBlock7, imageBlock8, imageBlock9,
            buttonImageView1, infoButtonImage, jackpotImageView, exitButtonImage;
    @FXML
    private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20,
            i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35;
    @FXML
    private Label aTextLabel, textLabel, historyTF, winText, welcomeLabel, lastBetLabel;
    @FXML
    private ImageView[] imageBlocksArry;
    Image[] pngsArry = new Image[15];
    ImageView[] jackpotArray = new ImageView[35];
    File[] fileArray = new File[14];
    ArrayList<String> resultArrList = new ArrayList<>();
    Bet bet1 = new Bet(-4);
    private int executionCount = 0;
    private final int totalExecutions = 5;
    private int jackpotImagesCounter = 1;
    private int jackpotSignCounter = 1;
    private Timeline timeline;
    private boolean canSpin = true;
    private Image[] flashImages = new Image[2];
    private app.casinoroyale.Controller.FirebaseControllers.PrimaryController primaryController;
    private app.casinoroyale.Controller.HomeController homeController;
    private Firestore firestore;
    private Stage stage;

    public SlotsController(){
        this.homeController = new HomeController();
        this.stage = new Stage();
        this.primaryController = new PrimaryController();
        firestore = CSRApplication.fstore;
    }


    @FXML
    void bank(ActionEvent event) throws IOException {
homeController.launchBank(event);
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
    private void playRoulatte(ActionEvent event) throws IOException {
        homeController.playRoulette(event);
    }
    @FXML
    private void homeDash(ActionEvent event) throws IOException {
        homeController.homeDash(event);
        Player player = Player.getInstance();
        primaryController.updateBalance(player.getAccountBalance());
    }
    @FXML
    public void initialize() {
        Player player = Player.getInstance();
        this.imageBlocksArry = new ImageView[]{this.imageBlock1, this.imageBlock2, this.imageBlock3, this.imageBlock4, this.imageBlock5, this.imageBlock6, this.imageBlock7, this.imageBlock8, this.imageBlock9};
        this.jackpotArray = new ImageView[]{this.i1, this.i2, this.i3, this.i4, this.i5, this.i6, this.i7, this.i8, this.i9,
                this.i10, this.i11, this.i12, this.i13, this.i14, this.i15, this.i16, this.i17, this.i18, this.i19, this.i20,
                this.i21, this.i22, this.i23, this.i24, this.i25, this.i26, this.i27, this.i28, this.i29, this.i30, this.i31,
                this.i32, this.i33, this.i34, this.i35};
        initializeImages();
        initializeBetHistoryTF();
        InitializeSetImages();
        InitializeButtonImages();
        setTextLabelInitialiaze();
        initializeWelcomeLabel();
        setUpTimeline();
        setUpTimeline2();
        initialJackpotImageSetUp();
    }

    public void initialJackpotImageSetUp(){
        if(jackpotImagesCounter == 1)
        for (int i = 0; i < 35; i++) {
            jackpotArray[i].setImage(pngsArry[7]);
        }
    }

    public void setUpTimeline2(){
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(5000), e -> initializeJackpotImages()));
        timeline1.setCycleCount(150);
        timeline1.play();
    }

    public void setUpTimeline(){
        Timeline tLine = new Timeline(new KeyFrame(Duration.millis(400  ), e -> initializeSlotsW()));
        tLine.setCycleCount(10);
        tLine.play();
    }

    public void initializeBetHistoryTF() {  //Initialize the betHistoryTF to beginning prompt.
        SlotGame.displayBalanceAmount(textLabel);
        lastBetLabel.setText("$4");
    }

    public void setTextLabelInitialiaze() {
        this.aTextLabel.setText("$$$");
    }

    private void initializeWelcomeLabel() {
        welcomeLabel.setText("   WELCOME \n          TO \n         THE \n        SLOT \n   MACHINE");
    }

    private void initializeSlotsW() {
        File s2File = new File("src/main/resources/app/Assets/Slots/css/casinoPlay.png");
        File sFile = new File("src/main/resources/app/Assets/Slots/css/slotsTitle.png");
        Image sImage = new Image(sFile.toURI().toString());
        Image s2Image = new Image(s2File.toURI().toString());
        flashImages[0] = sImage;
        if(jackpotSignCounter%2 == 0) {
            jackpotImageView.setImage(sImage);
        }
        else {jackpotImageView.setImage(s2Image);}
      jackpotSignCounter++;

    }

    private void initializeJackpotImages() {

        if (jackpotImagesCounter % 2 == 0 ) {
            for (int i = 0; i < 35; i++) {
                jackpotArray[i].setImage(pngsArry[7]);
            }
        } else {
            for (int i = 0; i < 35; i++) {
                jackpotArray[i].setImage(pngsArry[12]);
            }
        }
        jackpotImagesCounter++;
    }

    private void initializeImages() {
        int i;
        for (i = 0; i < 14; ++i) {
            this.fileArray[i] = new File("src/main/resources/app/Assets/Slots/icons/" + i + ".png");
        }
        for (i = 0; i < 14; ++i) {
            this.pngsArry[i] = new Image(this.fileArray[i].toURI().toString());
        }
    }

    private void InitializeSetImages() {
        for (int i = 0; i < 3; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[13]);
        }
        for (int i = 3; i < 6; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[13]);
        }
        for (int i = 6; i < 9; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[13]);
        }
    }

    private void InitializeButtonImages() {
        File[] dollarPngFileArry = new File[4];

        dollarPngFileArry[0] = new File("src/main/resources/app/Assets/Slots/css/spinButton.png");
        dollarPngFileArry[1] = new File("src/main/resources/app/Assets/Slots/css/spinButton.png");
        dollarPngFileArry[2] = new File("src/main/resources/app/Assets/Slots/css/infoButton.png");
        dollarPngFileArry[3] = new File("src/main/resources/app/Assets/Slots/css/exitButton.png");

        Image infoImage = new Image(dollarPngFileArry[2].toURI().toString());
        Image exitImage = new Image(dollarPngFileArry[3].toURI().toString());
        Image spinImage = new Image(dollarPngFileArry[1].toURI().toString());

        infoButtonImage.setImage(infoImage);
        exitButtonImage.setImage(exitImage);
        buttonImageView1.setImage(spinImage);
    }

    @FXML
    public void spinButton() {
        if(Player.getInstance().getAccountBalance() >= 4 ) {
            primaryController.updateBalance(Player.getInstance().getAccountBalance());
            spin();
            attrb();
        }
        else{SlotGameView.insufficientFunds();}
    }

    public void attrb(){
        lastBetLabel.setText("$4");
        welcomeLabel.setText("");
    }

    public void spin() {
        if(!canSpin){
            System.out.println("Wait 1.2 sec!");
            return;
        }
        canSpin = false;
        executionCount = 0;
        Player.getInstance().setAccountBalance(bet1.getAmount());
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> {

            SlotGameView.spin(imageBlocksArry, pngsArry);
            executionCount++;

            if (executionCount >= totalExecutions) {
                timeline.stop();
                SlotGame.setResults(resultArrList, pngsArry, imageBlocksArry);
                SlotGameView.printResults(resultArrList, historyTF, winText);
                SlotGame.setMostRepeated(resultArrList);
                SlotGame.setMultiplier();
                SlotGame.setWinnings();
                SlotGame.displayWinAmount(aTextLabel);
                SlotGame.displayBalanceAmount(textLabel);
                SlotGame.setCounter();
                Player player = Player.getInstance();
                primaryController.updateBalance(player.getAccountBalance());
            }
        }));

        timeline.setOnFinished(e -> canSpin = true);
        timeline.setCycleCount(totalExecutions);
        timeline.play();
        new Timeline(new KeyFrame(Duration.millis(1200), e -> canSpin = true)).play();
    }

    @FXML
    private void launchBank(ActionEvent event) throws IOException {
        homeController.launchBank(event);
    }
    @FXML
    public void infoButtonHandler() {SlotGameView.infoButton();}
    @FXML
    void exitButtonHandler() {
        Player player = Player.getInstance();
        primaryController.updateBalance(player.getAccountBalance());
        System.out.println(player.getAccountBalance());
        SlotGameView.exitButton();
    }
}
