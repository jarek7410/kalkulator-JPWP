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
    pow{
        @Override
        public double calculate(double a, double b) {
            return Math.pow(a,b);
        }
    },
    yrtx{
        @Override
        public double calculate(double a, double b) {
            return Math.pow(a,1/b);
        }
    },
    mod{
        @Override
        public double calculate(double a, double b) {
            return a%b;
        }
    };
    public abstract double calculate(double a, double b);



}
