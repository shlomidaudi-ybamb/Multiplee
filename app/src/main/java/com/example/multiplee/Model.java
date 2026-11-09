package com.example.multiplee;

public abstract class Model {

    protected int num1;
    protected int num2;

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public abstract void generateNumbers();

    public boolean checkAnswer(int answer) {
        return answer == num1 * num2;
    }
}