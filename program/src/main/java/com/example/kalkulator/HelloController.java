package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    public Label outputTextOld;
    private String SteteOfOutput;
    private double output=0;
    private int placesLimit=9;
    private int places=0;
    private double bufCurrent=0;
    private double bufOld=0;
    private Operations lastOperation= Operations.none;
    private boolean dotPressed=false;
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
            if(!(places<placesLimit)){
                return;
            }else {
                places++;
            }
            int i = Integer.parseInt(String.valueOf(s.charAt(3)));
            if(dotPressed){
                decimalPlacec++;
                bufCurrent += i /Math.pow(10,decimalPlacec);

            }
            else{
                bufCurrent *=10;
                bufCurrent += i;
            }
        }
        if(s.contains("dot")){
            dotPressed=true;
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
            bufCurrent*=-1;
        }
        outputTextOld.setText(String.valueOf(bufOld));
        outputText.setText(String.valueOf(bufCurrent));

    }
    private void doLastOperation(Operations newOperation){
        switch (lastOperation){
            case add -> {
                bufOld+=bufCurrent;
            }
            case subtract -> {
                bufOld-=bufCurrent;
            }
            case none -> {
                bufOld=bufCurrent;
            }
            case multiply -> {
                bufOld*=bufCurrent;
            }
            case divide -> {
                bufOld/=bufCurrent;
            }
        }
        lastOperation=newOperation;
        bufCurrent=0;
        System.out.println(bufOld);
    }
}