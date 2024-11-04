package app.casinoroyale.Controller;

import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.FirebaseControllers.FirestoreContext;
import app.casinoroyale.Controller.FirebaseControllers.Person;
import app.casinoroyale.Controller.FirebaseControllers.PrimaryController;
import app.casinoroyale.Controller.GamesControllers.SlotsController;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class LoginController {
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField passwordTF;

    private app.casinoroyale.Controller.HomeController homeController;
    private app.casinoroyale.Controller.FirebaseControllers.PrimaryController primaryController;
    private Firestore firestore;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    private Stage stage;
    @SuppressFBWarnings("URF_UNREAD_FIELD")
    public LoginController(){
        this.homeController = new HomeController();
        this.stage = new Stage();
        this.primaryController = new PrimaryController();
        firestore = contxtFirebase.firebase();

    }
    public boolean loginUser(String email, String password) {
        ApiFuture<QuerySnapshot> future = firestore.collection("Persons").whereEqualTo("email", email).get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    String storedPassword = document.getString("Password");
                    if (storedPassword != null && storedPassword.equals(password)) {
                        initializePlayerWithPersonData(document);
                        primaryController.setID(document.getId());

                        return true;
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initializePlayerWithPersonData(QueryDocumentSnapshot document) {
        String name = document.getString("Name");
        double balance = document.getDouble("Balance");
        Player player = Player.getInstance();

        player.setName(name);
        player.setAccountBalanceFromFirebase(balance);
    }




    @FXML
    public void registerButtonHandler(ActionEvent event) throws IOException{
        primaryController.readFirebase();
        homeController.registerDash(event);
    }


    public void signInButtonHandler(ActionEvent actionEvent) throws IOException{
        primaryController.readFirebase();
        System.out.println();
        String email = emailTF.getText();
        String password = passwordTF.getText();
        if (loginUser(email, password)) {
            System.out.println("LOGGED IN");
            homeController.homeDash(actionEvent);
        } else {
            Alert requirementsAlert = new Alert(Alert.AlertType.ERROR);
            requirementsAlert.setTitle("LOGIN ERROR");
            requirementsAlert.setHeaderText("LOGIN ERROR");
            requirementsAlert.setContentText("You have the incorrect username or password.");
            requirementsAlert.showAndWait();
        }
    }

}
