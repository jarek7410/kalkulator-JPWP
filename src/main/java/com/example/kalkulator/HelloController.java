package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    private String SteteOfOutput;
    private double output;
    @FXML
    private Label outputText;
//    private ToggleGroup Q;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        Button button= (Button) event.getSource();
        outputText.setText(button.getText());
    }
}