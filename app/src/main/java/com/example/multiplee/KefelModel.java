package com.example.multiplee;

import java.util.Random;

public class KefelModel extends Model {

    private Random random = new Random();

    @Override
    public void generateNumbers() {
        num1 = random.nextInt(9) + 1;
        num2 = random.nextInt(9) + 1;
    }
}