package app.casinoroyale;

import app.casinoroyale.Controller.FirebaseControllers.FirestoreContext;
import app.casinoroyale.Controller.HomeController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class CSRApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        URL resource = getClass().getResource("/app/casinoroyale/View/Dashboards/LoginPage.fxml");

        if (resource == null) {
            throw new IOException("Cannot load resource: /app/casinoroyale/View/Dashboards/LoginPage.fxml");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane root = fxmlLoader.load();

        HomeController.setPrimaryStage(primaryStage); // Set the primary stage in HomeController

        javafx.geometry.Rectangle2D screenSize = Screen.getPrimary().getBounds();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        Scene scene = new Scene(root, screenWidth * 0.6, screenHeight * 0.6);
        primaryStage.setTitle("Casino Royale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}