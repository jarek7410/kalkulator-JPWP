package com.example.kalkulator;

public enum Metastrophe {

    sin{
        @Override
        public double translate(double a) {
            return Math.sin(a);
        }
    },
    cos{
        @Override
        public double translate(double a) {
            return Math.cos(a);
        }
    },
    tan{
        @Override
        public double translate(double a) {
            return Math.tan(a);
        }
    },
    cot{
        @Override
        public double translate(double a) {
            return 1 / Math.tan(a);
        }
    },
    sec{
        @Override
        public double translate(double a) {
            return 1/Math.cos(a);
        }
    },
    log{
        @Override
        public double translate(double a) {
            return Math.log(a);
        }
    },
    ln{
        @Override
        public double translate(double a) {
            return Math.log10(a);
        }
    },
    sqrt{
        @Override
        public double translate(double a) {
            return Math.sqrt(a);
        }
    },
    cbrt{
        @Override
        public double translate(double a) {
            return Math.cbrt(a);
        }
    },
    plusMinus{
        @Override
        public double translate(double a) {
            return a*-1;
        }
    },
    pi{
        @Override
        public double translate(double a) {
            return (a==0?
                    Math.PI:
                    Math.PI*a);
        }
    },
    percent{
        @Override
        public double translate(double a) {
            return a/100;
        }
    },
    inverse{
        @Override
        public double translate(double a) {
            return 1/a;
        }
    },
    e{
        @Override
        public double translate(double a) {
            return (a==0?
                    Math.E:
                    Math.E*a);
        }
    },
    factorial{
        @Override
        public double translate(double b) {
            double result=1;
            for(int i=1;i<=b;i++){
                result*=i;
            }
            return result;
        }
    },
    cst {
        @Override
        public double translate(double a) {
            return 1/Math.cos(a);
        }
    },
    x22{
        @Override
        public double translate(double a) {
            return Math.pow(a,2);
        }
    },
    x23{
        @Override
        public double translate(double a) {
            return Math.pow(a,3);
        }
    },
    e2x{
        @Override
        public double translate(double a) {
            return Math.pow(Math.E,a);
        }
    },
    t2x{
        @Override
        public double translate(double a) {
            return Math.pow(10,a);
        }
    };
    public abstract double translate(double a);
}
