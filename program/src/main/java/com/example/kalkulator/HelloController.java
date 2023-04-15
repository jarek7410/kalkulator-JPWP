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
    private int placesLimit=13;
    private String bufferCurrent="0";
    private String bufferOld ="0";
    private Operations lastOperation= Operations.none;
    private int decimalPlacec=4;
    @FXML
    private Label outputText;
//    private ToggleGroup Q;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        Button button= (Button) event.getSource();
        //outputText.setText(button.getId());
        String s=button.getId();
        if(s.contains("num")){
            imputNumber(s);
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

        //for oneargument operations use doOperation
        if(s.equals("plsu")){
            doOperation(Operations.plusMinus);
        }
        if(s.equals("sin1")){
            doOperation(Operations.sec);
        }
        if(s.equals("sin")){
            doOperation(Operations.sin);
        }
        if(s.equals("pi")){
            doOperation(Operations.pi);
        }
        if(s.equals("e")){
            doOperation(Operations.e);
        }

        //special operations
        if(s.contains("dot")
                &&!bufferCurrent.contains(".")){
            addDot();
        }
        if(s.equals("ac")){
            bufferCurrent="0";
            bufferOld="0";
            refreshOutput();
        }
//        outputTextOld.setText(String.valueOf(bufferOld));
//        outputText.setText(String.valueOf(bufferCurrent));

    }

    private void addDot() {
        if(bufferCurrent.equals("0")){
            bufferCurrent="0.";
        }else{
            bufferCurrent+=".";
        }
        refreshOutput();
        outputText.setText(bufferCurrent);
    }

    private void imputNumber(String number){
        if(!(bufferCurrent.length()<placesLimit)){
            return;
        }
        int i = Integer.parseInt(String.valueOf(number.charAt(3)));
        if(bufferCurrent.equals("0")){
            bufferCurrent="";
        }
        bufferCurrent += i;
        refreshOutput();
        outputText.setText(bufferCurrent);
    }
    private void doOperation(Operations operation){
        var numberSecend = Double.valueOf(bufferCurrent);
        double output=0;

        output=operation.calculate(0,numberSecend);
        bufferCurrent ="0";

        bufferOld =Double.toString(output);;
        refreshOutput();
        outputText.setText(bufferOld);
    }
    private void doLastOperation(Operations newOperation){
        var numberFirst = Double.valueOf(bufferOld);
        var numberSecend = Double.valueOf(bufferCurrent);
        double output=0;

        output=lastOperation.calculate(numberFirst,numberSecend);

        lastOperation=newOperation;
        bufferCurrent ="0";
        bufferOld=Double.toString(output);
        refreshOutput();
        outputText.setText(bufferOld);
    }

    private void refreshOutput() {
        if(bufferOld.length()>placesLimit){
            bufferOld=bufferOld.substring(0,placesLimit);
        }
        if(bufferCurrent.length()>placesLimit){
            bufferCurrent=bufferCurrent.substring(0,placesLimit);
        }
//        outputText.setText(bufferCurrent);
//        outputTextOld.setText(bufferOld);
    }
}