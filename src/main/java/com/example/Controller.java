package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private void handleClick() {
        System.out.println("Click");
        button.setDisable(false);
    }
}
