package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloController {
    static Logger logger =  LogManager.getLogger("kalkulator/MainControler");


    @FXML
    public Label outputTextOld;
    public RadioButton deg;
    private int placesLimit=13;
    private String bufferCurrent="0";
    private String bufferOld ="0";
    private Operations lastOperation= Operations.none;
    @FXML
    private Label outputText;
    private boolean isNewOperation;
//    private ToggleGroup Q;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        Button button= (Button) event.getSource();
        String s=button.getId();

        if(s.equals("add")){
            doLastOperation(Operations.add);
        }
        if(s.equals("sub")){
            doLastOperation(Operations.subtract);
        }
        if(s.equals("mult")){
            doLastOperation(Operations.multiply);
        }
        if(s.equals("div")){
            doLastOperation(Operations.divide);
        }
        if(s.equals("yrtx")){
            doLastOperation(Operations.yrtx);
        }
        if(s.equals("x2y")){
            doLastOperation(Operations.pow);
        }

        //for oneargument operations use doOperation

        if(s.equals("plsu")){
            doOperation(Metastrophe.plusMinus);
        }

        if(s.equals("pi")){
            doOperation(Metastrophe.pi);
        }
        if(s.equals("log")){
            doOperation(Metastrophe.log);
        }
        if(s.equals("ln")){
            doOperation(Metastrophe.ln);
        }
        if(s.equals("sqrt")){
            doOperation(Metastrophe.sqrt);
        }
        if(s.equals("cbrt")){
            doOperation(Metastrophe.cbrt);
        }
        if(s.equals("ptc")){
            doOperation(Metastrophe.percent);
        }
        if(s.equals("x22")){
            doOperation(Metastrophe.x22);
        }
        if(s.equals("x23")){
            doOperation(Metastrophe.x23);
        }
        if(s.equals("e2x")){
            doOperation(Metastrophe.e2x);
        }
        if(s.equals("102x")){
            doOperation(Metastrophe.t2x);
        }

        if(s.equals("e")){
            doOperation(Metastrophe.e);
        }
        if(s.equals("1ovx")){
            doOperation(Metastrophe.inverse);
        }
        if(s.equals("fact")){
            doOperation(Metastrophe.factorial);
        }

        //special operations
        if(s.contains("num")){
            imputNumber(s);
        }
        if(s.equals("back")){
            if(bufferCurrent.length()>1){
                bufferCurrent=bufferCurrent.substring(0,bufferCurrent.length()-1);
            }else{
                bufferCurrent="0";
            }
            refreshOutput();
            outputText.setText(bufferCurrent);
        }
        if(s.contains("Trig")) {
            if(deg.isSelected()){
                bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
            }
            switch (s.substring(4)) {
                case "sin1" -> doOperation(Metastrophe.sec);
                case "sin" -> doOperation(Metastrophe.sin);
                case "cos1" -> doOperation(Metastrophe.cst);
                case "cos" -> doOperation(Metastrophe.cos);
                case "tan1" -> doOperation(Metastrophe.cot);
                case "tan" -> doOperation(Metastrophe.tan);
            }
            isNewOperation=true;
        }
        if(s.contains("dot")
                &&!bufferCurrent.contains(".")){
            addDot();
        }
        if(s.equals("ac")){
            bufferCurrent="0";
            bufferOld="0";
            lastOperation=Operations.none;
            refreshOutput();
        }
        if(s.contains("eql")){
            String buffer;
            if(bufferCurrent.equals("0")) {
                bufferCurrent=bufferOld;
                buffer = bufferOld;
            }
            else {
                buffer = bufferCurrent;
            }
            doLastOperation(lastOperation);
            bufferCurrent=buffer;
            logger.warn("eql: "+lastOperation.name()+", input("+bufferOld+", "+bufferCurrent+"), buffer("+buffer+")");
        }

    }

    private void addDot() {
        if(bufferCurrent.equals("0")){
            bufferCurrent="0.";
        }else{
            bufferCurrent+=".";
        }
        refreshOutput();
    }

    private void imputNumber(String number){
        if(bufferCurrent.equals("0")||isNewOperation){
            bufferCurrent="";
            isNewOperation=false;
        }
        if(!(bufferCurrent.length()<placesLimit)){
            return;
        }
        int i = Integer.parseInt(String.valueOf(number.charAt(3)));
        bufferCurrent += i;
        refreshOutput();
    }
    private void doOperation(Metastrophe operation){
        double numberSecend = Double.valueOf(bufferCurrent);
        double output=operation.translate(numberSecend);
        logger.debug("doOperation: "+operation.name()+" output("+output+ ")"+", input("+numberSecend+")");

        bufferCurrent =Double.toString(output);
        refreshOutput();
    }
    private void doLastOperation(Operations newOperation){
        var numberFirst = Double.valueOf(bufferOld);
        var numberSecend = Double.valueOf(bufferCurrent);
    //do old operation
        double output=lastOperation.calculate(numberFirst,numberSecend);

        logger.debug("doLastOperation: "+lastOperation.name() +" output("+output+ ")"+", input("+numberFirst+", "+numberSecend+") newOperation: "+newOperation.name());
    //print old operation
        bufferCurrent =Double.toString(output);
        bufferOld=Double.toString(output);
        refreshOutput();
    //seve new operation
        lastOperation=newOperation;
        isNewOperation=true;
    }

    private void refreshOutput() {
        if(bufferOld.length()>placesLimit){
            bufferOld=bufferOld.substring(0,placesLimit);
        }
        if(bufferCurrent.length()>placesLimit){
            bufferCurrent=bufferCurrent.substring(0,placesLimit);
        }
        logger.trace("refresh: bufferOld("+bufferOld+ "), bufferCurrent("+bufferCurrent+")");
        outputText.setText(bufferCurrent);
        outputTextOld.setText(bufferOld);
    }
}