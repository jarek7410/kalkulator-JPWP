package com.example.kalkulator;

public enum Operations {
    none{
        @Override
        public double calculate(double a, double b) {
            return b;
        }
    },
    add{
        @Override
        public double calculate(double a, double b) {
            return a+b;
        }
    },
    subtract{
        @Override
        public double calculate(double a, double b) {
            return a-b;
        }
    },
    multiply{
        @Override
        public double calculate(double a, double b) {
            return a*b;
        }
    },
    divide{
        @Override
        public double calculate(double a, double b) {
            return a/b;
        }
    },
    sin{
        @Override
        public double calculate(double a, double b) {
            return Math.sin(b);
        }
    },
    cos{
        @Override
        public double calculate(double a, double b) {
            return Math.cos(b);
        }
    },
    tan{
        @Override
        public double calculate(double a, double b) {
            return Math.tan(b);
        }
    },
    cot{
        @Override
        public double calculate(double a, double b) {
            return 1 / Math.tan(b);
        }
    },
    sec{
        @Override
        public double calculate(double a, double b) {
            return 1/Math.cos(b);
        }
    },
    log{
        @Override
        public double calculate(double a, double b) {
            return Math.log(b);
        }
    },
    ln{
        @Override
        public double calculate(double a, double b) {
            return Math.log10(b);
        }
    },
    sqrt{
        @Override
        public double calculate(double a, double b) {
            return Math.sqrt(b);
        }
    },
    pow{
        @Override
        public double calculate(double a, double b) {
            return Math.pow(a,b);
        }
    },
    mod{
        @Override
        public double calculate(double a, double b) {
            return a%b;
        }
    },
    factorial{
        @Override
        public double calculate(double a, double b) {
            double result=1;
            for(int i=1;i<=b;i++){
                result*=i;
            }
            return result;
        }
    },
    percent{
        @Override
        public double calculate(double a, double b) {
            return b/100;
        }
    },
    plusMinus{
        @Override
        public double calculate(double a, double b) {
            return b*-1;
        }
    },
    inverse{
        @Override
        public double calculate(double a, double b) {
            return 1/b;
        }
    },
    pi{
        @Override
        public double calculate(double a, double b) {
            return (b==0?
                    Math.PI:
                    Math.PI*b);
        }
    },
    e{
        @Override
        public double calculate(double a, double b) {
            return (b==0?
                    Math.E:
                    Math.E*b);
        }
    };
    public abstract double calculate(double a, double b);



}
