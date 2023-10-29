module app.casinoroyale {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics; // Include if you are using classes from javafx.graphics
    requires javafx.base; // Include if you are using classes from javafx.base

    requires org.kordamp.bootstrapfx.core;

    // Exporting and opening the necessary packages
    exports app.casinoroyale;
    opens app.casinoroyale to javafx.fxml, javafx.base;

    exports app.casinoroyale.Controller;
    opens app.casinoroyale.Controller to javafx.fxml, javafx.base;

    exports app.casinoroyale.Controller.GamesControllers;
    opens app.casinoroyale.Controller.GamesControllers to javafx.fxml, javafx.base;
}
