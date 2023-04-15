package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    private int n;
    private int r;

    @FXML
    public Label outputTextOld;
    private String SteteOfOutput;
    private double output=0;
    private int placesLimit=10;
    private int places=0;
    private String bufferCurrent="0";
    private String bufferOld ="0";
    private Operations lastOperation= Operations.none;
    private boolean isDotPressed =false;
    private int decimalPlacec=0;
    @FXML
    private Label outputText;
//    private ToggleGroup Q;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        Button button= (Button) event.getSource();
        outputText.setText(button.getId());
        String s=button.getId();
        if(s.contains("num")){
            imputNumber(s);
        }
        if(s.contains("dot")
            &&!bufferCurrent.contains(".")){

            bufferCurrent+=".";
        }
        if(s.equals("add")){
            doLastOperation(Operations.add);
        }
        if(s.equals("sub")){
            doLastOperation(Operations.subtract);
        }
        if(s.contains("eql")){
            doLastOperation(Operations.none);
        }
        if(s.equals("mult")){
            doLastOperation(Operations.multiply);
        }
        if(s.equals("plsu")){
            //bufferCurrent *=-1;
        }
        if(s.equals("sin1")){

        }
        outputTextOld.setText(String.valueOf(bufferOld));
        outputText.setText(String.valueOf(bufferCurrent));

    }
    private void imputNumber(String number){
        if(!(bufferCurrent.length()<placesLimit)){
            return;
        }
        int i = Integer.parseInt(String.valueOf(number.charAt(3)));
        if(!isDotPressed){

        }
        bufferCurrent += i;

    }
    private void doLastOperation(Operations newOperation){
        var numberFirst = Double.valueOf(bufferOld);
        var numberSecend = Double.valueOf(bufferCurrent);
        double output=0;
        switch (lastOperation){
            case add -> {
                output=numberFirst+numberSecend;
            }
            case subtract -> {
                output=numberFirst-numberSecend;
            }
            case none -> {
                output=numberSecend;
            }
            case multiply -> {
                output=numberFirst*numberSecend;
            }
            case divide -> {
                output=numberFirst/numberSecend;
            }
        }
        lastOperation=newOperation;
        bufferCurrent ="0";
        bufferOld=Double.toString(output);
        refreshOutput();
    }

    private void refreshOutput() {
        if(bufferOld.length()>placesLimit){
            bufferOld=bufferOld.substring(0,placesLimit);
        }
        if(bufferCurrent.length()>placesLimit){
            bufferCurrent=bufferCurrent.substring(0,placesLimit);
        }
        outputText.setText(bufferCurrent);
        outputTextOld.setText(bufferOld);
    }
}