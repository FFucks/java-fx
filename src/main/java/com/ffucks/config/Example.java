package com.ffucks.config;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.awt.*;

public class Example {

    public Scene tests() {


        Button button = new Button("Click");
        VBox layout = new VBox(button);
        return new Scene(layout);


    }
}
