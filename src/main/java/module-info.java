module app.casinoroyale {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics; // Include if you are using classes from javafx.graphics
    requires javafx.base; // Include if you are using classes from javafx.base

    requires google.cloud.firestore;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.api.apicommon;
    requires com.google.auth;
    requires java.logging;
    requires google.cloud.core;



    // Exporting and opening the necessary packages
    exports app.casinoroyale;
    exports app.casinoroyale.Controller.FirebaseControllers;
    opens app.casinoroyale to javafx.fxml, javafx.base;
    opens app.casinoroyale.Controller.FirebaseControllers to javafx.graphics;

    exports app.casinoroyale.Controller;
    opens app.casinoroyale.Controller to javafx.fxml, javafx.base;

    exports app.casinoroyale.Controller.GamesControllers;
    opens app.casinoroyale.Controller.GamesControllers to javafx.fxml, javafx.base;
    opens app.casinoroyale.Controller.GamesControllers.BlackJackController to javafx.fxml, javafx.base;
}
