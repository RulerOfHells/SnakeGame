package dev.rohan.gamepro;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
    private static final int AMT = 20;      //game starts with these amount of food
    private int count = 0;
    private int[] foodX;
    private int[] foodY;
    private int foodWidth;
    private int foodHeight;
    private Random random;

    public Food() {
        foodX = new int[AMT];
        foodY = new int[AMT];
        foodWidth = 5;
        foodHeight = 5;
        count = 0;

        random = new Random();

        renew();
    }

    public void renew() {               //random food square co-ordinates
        for(int i = 0; i < AMT; i++) {
            foodX[i] = random.nextInt(680);
            foodY[i] = random.nextInt(580);
        }
        count = 0;
    }

    public void placeFood(Graphics g) {     //displays all the food on screen
        for(int i = 0; i < AMT; i++) {
            if(foodX[i] >= 0 && foodY[i] >= 0) {
                g.setColor(Color.WHITE);
                g.fillRect(foodX[i], foodY[i], foodWidth, foodHeight);
            }
       }
    }

    public void unPlaceFood(int x, int y) {         //remove the food which snake eats  - collision
        for(int i = 0; i < foodX.length; i++) {
            if(x == foodX[i] && y == foodY[i])
                foodX[i] = foodY[i] = -1;
        }
        if(++count == AMT) renew();
    }

    public int[] getFoodX() {
        return foodX;
    }

    public int[] getFoodY() {
        return foodY;
    }
}