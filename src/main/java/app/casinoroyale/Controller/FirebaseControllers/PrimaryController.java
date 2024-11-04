package app.casinoroyale.Controller.FirebaseControllers;

import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Controller.LoginController;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

interface regex{
    String regexUserName = "\\b[A-Z][a-zA-Z]+";
    String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,6}";
}
public class PrimaryController implements regex{
    private app.casinoroyale.Controller.HomeController homeController;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField startingBalanceTextField;
    @FXML
    private RadioButton radioButton;
    @FXML
    private Button writeButton;
    boolean isSelected;
    boolean isExist;
    boolean isRegistered = false;
    private boolean key;
    private ArrayList<String> registeredEmailArryList = new ArrayList<>();
    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private Person person;
    private Firestore firestore;
    private String ID;
    public void setID(String ID){
        this.ID = ID;
    }

    public ObservableList<Person> getListOfUsers() {
        return listOfUsers;
    }
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    public PrimaryController(){
        //LG = new LoginController();
        this.homeController = new HomeController();
        firestore = contxtFirebase.firebase();
    }

    void initialize() {
        AccessDataView accessDataViewModel = new AccessDataView();
        nameTextField.textProperty().bindBidirectional(accessDataViewModel.userNameProperty());
        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());
    }


    @FXML
    void writeButtonClicked(ActionEvent event) throws IOException {
        addData();
        if(isRegistered) {
            homeController.loginDash(event);
        }
    }

    public boolean updateBalance(double newBalance) {
        DocumentReference docRef = firestore.collection("Persons")
                .document(ID);
        Map<String, Object> updates = new HashMap<>();
        updates.put("Balance", newBalance);

        ApiFuture<WriteResult> writeResult = docRef.update(updates);
        try {
            writeResult.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean readFirebase() {
        key = false;
        ApiFuture<QuerySnapshot> future =  firestore.collection("Persons").get();
        List<QueryDocumentSnapshot> documents;
        try
        {
            documents = future.get().getDocuments();
            if(documents.size()>0)
            {
                System.out.println("Outing data from firabase database....");
                listOfUsers.clear();
                for (QueryDocumentSnapshot document : documents)
                {
                    registeredEmailArryList.add((String) document.getData().get("email"));
                    System.out.println(document.getId() + " => " + document.getData().get("Name"));
                    person  = new Person(
                            String.valueOf(document.getData().get("Name")),
                            String.valueOf(document.getData().get("email")),
                            String.valueOf(document.getData().get("Password")),
                            Integer.parseInt(document.getData().get("Age").toString()),
                            Double.parseDouble(document.getData().get("Balance").toString())
                    );
                    listOfUsers.add(person);
                }
            }
            else
            {
                System.out.println("No data");
            }
            key=true;
        }
        catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }
        return key;
    }

    @SuppressFBWarnings({"DM_BOXED_PRIMITIVE_FOR_PARSING", "UC_USELESS_OBJECT"})
    public void addData() {
        readFirebase();
        isSelected = radioButton.isSelected();
        isExist = true;
            for(int i = 0; i < registeredEmailArryList.size(); i++){
            if(registeredEmailArryList.get(i).equals(emailTextField.getText())){
                isExist = false;
        }}

        if(nameTextField.getText().matches(regexUserName) && Double.valueOf(ageTextField.getText()) >= 18 &&
                emailTextField.getText().matches(regexEmail) && isSelected && !passwordTextField.getText().isEmpty() &&
                !startingBalanceTextField.getText().isEmpty() && isExist) {
            DocumentReference docRef = firestore.collection("Persons").document(UUID.randomUUID().toString());
            Map<String, Object> data = new HashMap<>();
            data.put("Name", nameTextField.getText());
            data.put("Age", Integer.parseInt(ageTextField.getText()));
            data.put("email", emailTextField.getText());
            data.put("Password", passwordTextField.getText());
            data.put("Balance", Double.parseDouble(startingBalanceTextField.getText()));
            System.out.println("User registration is successful");
            isRegistered = true;
        }
         else{
            Alert requirementsAlert = new Alert(Alert.AlertType.ERROR);
            requirementsAlert.setTitle("REGISTER ERROR");
            requirementsAlert.setHeaderText("                                REGISTER ERROR");
            ArrayList<String> errorMessages = new ArrayList<>();

            if(nameTextField.getText().matches(regexUserName) == false){
                errorMessages.add("Invalid or Empty Name!!");
            }
            if(Double.valueOf(ageTextField.getText()) >= 18 == false ){
                errorMessages.add("You Have To Be Older Than 18!!");
            }
            if(emailTextField.getText().matches(regexEmail) == false){
                errorMessages.add("Invalid Email Or Empty Email. Exp: example@domain.com");
            }
            if(passwordTextField.getText().isEmpty() || startingBalanceTextField.getText().isEmpty()){
                errorMessages.add("Password And Balance Can't Be Empty!!");
            }
            if(!isExist){
                errorMessages.add("This Email Already Exist, Please Enter A Different One!!");
            }
            if(!isSelected){
                errorMessages.add("Please Accept The Terms Of Use And Privacy Policy.");
            }
            StringBuffer strBfr = new StringBuffer();

            Iterator<String> iterator1 = errorMessages.iterator();

            while (iterator1.hasNext()) {
                strBfr.append(iterator1.next() + "\n");
            }
            requirementsAlert.setContentText(strBfr.toString());
            requirementsAlert.showAndWait();
        }
    }


    public void signInButtonHandler(ActionEvent actionEvent) throws IOException{
        homeController.loginDash(actionEvent);
    }
}