package com.ffucks;

import com.ffucks.config.DbConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ContactApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        DbConfig.initializeDatabase();

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/contact.fxml"));

        Scene scene = new Scene(loader.load(), 800, 600);

        /*scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/view/style.css")).toExternalForm());*/

        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}