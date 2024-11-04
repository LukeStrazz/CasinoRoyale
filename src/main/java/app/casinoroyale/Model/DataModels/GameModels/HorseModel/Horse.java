package app.casinoroyale.Model.DataModels.GameModels.HorseModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.security.SecureRandom;

public class Horse {
    private String name;
    private ImageView imageView;
    private String odds;
    private double speed;
    private Timeline moveHorseAnimation;  // The new field to store each horse's animation
    private static final SecureRandom RANDOM = new SecureRandom();

    public Horse(String name, ImageView imageView) {
        this.name = name;
        this.imageView = imageView;
        setOdds();
        setSpeed(generateSpeed());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds() {
        int numerator = RANDOM.nextInt(27) + 1;  // This will generate a number between 1 and 27
        this.odds = numerator + "/1";            // Set the odds in the desired format
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double generateSpeed() {
        // Assuming the speed is between some predefined values; adjust as necessary.
        return 1 + (5 - 1) * RANDOM.nextDouble();
    }

    public void startRace(double finishLine) {
        moveHorseAnimation = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            double newPosX = imageView.getLayoutX() + speed;
            if (newPosX >= finishLine) {
                imageView.setLayoutX(finishLine);
                stopRace();  // Stop this horse's animation
            } else {
                imageView.setLayoutX(newPosX);
            }
        }));
        moveHorseAnimation.setCycleCount(Timeline.INDEFINITE);
        moveHorseAnimation.play();
    }

    public void stopRace() {
        if (moveHorseAnimation != null) {
            moveHorseAnimation.stop();
        }
    }

}