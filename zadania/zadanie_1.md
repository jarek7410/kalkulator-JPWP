# Zadanie 1
## Popraw poniższy kod by był bardziej czytelny i możliwie najlepiej pasował do wiadomości zawrtych w prezentacji.
    
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
