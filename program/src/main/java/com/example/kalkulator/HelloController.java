package com.example.kalkulator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.lang.Math.*;
import static java.lang.Math.pow;
import javafx.scene.control.RadioButton;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;
import static java.lang.Math.toDegrees;
import static java.lang.Math.log10;
import static java.lang.Math.log;
import static java.lang.Math.exp;
import static java.lang.Math.cbrt;
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

    @FXML
    protected void foo(ActionEvent event) {
        Button button= (Button) event.getSource();
        String s=button.getId();

        if(s.equals("add"))doLastOperation(Operations.add);
        if(s.equals("sub"))doLastOperation(Operations.subtract);
        if(s.equals("mult"))doLastOperation(Operations.multiply);
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
        if(s.equals("plsu"))doOperation(Metastrophe.plusMinus);


        if(s.equals("pi")){
            doOperation(Metastrophe.pi);
        }
        if(s.equals("log"))doOperation(Metastrophe.log);
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
        if(s.contains("num"))imputNumber(s);
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
            switch (s.substring(4)) {
                case "sin1" -> {
                    if(deg.isSelected()){
                        bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
                    }
                    doOperation(Metastrophe.sec);
                }
                case "sin" -> {
                    if(deg.isSelected()){
                        bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
                    }
                    doOperation(Metastrophe.sin);
                }
                case "cos1" -> {
                    if(deg.isSelected()){
                        bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
                    }
                    doOperation(Metastrophe.cst);
                }
                case "cos" -> {
                    if(deg.isSelected()){
                        bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
                    }
                    doOperation(Metastrophe.cos);
                }
                case "tan1" -> {
                    if(deg.isSelected()){
                        bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
                    }
                    doOperation(Metastrophe.cot);
                }
                case "tan" -> {
                    if(deg.isSelected()){
                        bufferCurrent=String.valueOf(Math.toRadians(Double.parseDouble(bufferCurrent)));
                    }
                    doOperation(Metastrophe.tan);
                }
            }
            if(isNewOperation(isNewOperation)){
                isNewOperation=true;
            }else {
                isNewOperation = true;
            }
        }
        if(s.contains("dot")
                &&!bufferCurrent.contains(".")){
            addDot();
        }
        if(s.equals("ac")){
            bufferCurrent="0";bufferOld="0";
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

    /**
     * addDot method adds dot to bufferCurrent and prevents it from adding more than one dot
     * @param none - no parameters
     *           - no return value
     *             - no exceptions
     *             - no side effects
     *             - no preconditions
     *             - no postconditions
     *             - no invariants
     *             - no class invariants
     */
    private void addDot() {
        if(bufferCurrent.equals("0")){
            bufferCurrent="0.";
            logger.warn("addDot: "+bufferCurrent);
        }else{
            bufferCurrent+=".";
            logger.warn("addDot: "+bufferCurrent);
        }
        logger.warn("addDot: "+bufferCurrent);
        refreshOutput();
    }

    private void imputNumber(String number){
        if(bufferCurrent.equals("0")||isNewOperation(isNewOperation)){
            bufferCurrent="";
            if(!isNewOperation(isNewOperation))isNewOperation=false;
            else isNewOperation = false;


        }
        if(!(bufferCurrent.length()<placesLimit))return;
        int i = Integer.parseInt(String.valueOf(number.charAt(3)));
        bufferCurrent += i;


        logger.warn("imputNumber: "+bufferCurrent);
        refreshOutput();
    }

    private boolean isNewOperation(boolean isNewOperation) {
        if(isNewOperation){
            return true;
        }else {
            return false;
        }
    }

    private void doOperation(Metastrophe operation){
        double numberSecend = Double.valueOf(bufferCurrent);
        double output=operation.translate(numberSecend);
        logger.debug("doOperation: "+operation.name()+" output("+output+ ")"+", input("+numberSecend+")");

        bufferCurrent =Double.toString(output);
        refreshOutput();
    }

    private void doLastOperation(Operations newOperation){
        var numberFirst = Double.valueOf(bufferOld);var numberSecend = Double.valueOf(bufferCurrent);
        double output=lastOperation.calculate(numberFirst,numberSecend);
        logger.debug("doLastOperation: "+lastOperation.name() +" output("+output+ ")"+", input("+numberFirst+", "+numberSecend+") newOperation: "+newOperation.name());bufferCurrent =Double.toString(output);bufferOld=Double.toString(output);
        refreshOutput();lastOperation=newOperation;
        if(isNewOperation(isNewOperation))isNewOperation=true;
        else {isNewOperation = true;}
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