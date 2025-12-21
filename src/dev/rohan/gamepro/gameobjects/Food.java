package dev.rohan.gamepro.gameobjects;

import dev.rohan.gamepro.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public final class Food {
    private static final int AMT = 20;      //game starts with these amount of food
    private int count = 0;
    private final int[] foodX;
    private final int[] foodY;
    private final int foodWidth;
    private final int foodHeight;
    private final Random random;

    public Food() {
        foodX = new int[AMT];
        foodY = new int[AMT];
        foodWidth = 32;
        foodHeight = 32;
        count = 0;

        random = new Random();

        renew();
    }

    public void renew() {               //random food square co-ordinates
        for(int i = 0; i < AMT; i++) {
            foodX[i] = random.nextInt(720 - 14 - foodWidth);
            foodY[i] = random.nextInt(640 - 60 - foodHeight);
        }
        count = 0;
    }

    public void placeFood(Graphics g) {     //displays all the food on screen
        for(int i = 0; i < AMT; i++) {
            if(foodX[i] >= 0 && foodY[i] >= 0) {
                g.setColor(Color.WHITE);
                // g.fillRect(foodX[i], foodY[i], foodWidth, foodHeight);
                g.drawImage(Assets.apple[0], foodX[i] - foodWidth/2, foodY[i] - foodHeight/2, foodWidth, foodHeight, null);
            }
       }
    }

    public boolean unPlaceFood(Rectangle snakeHead) {         //remove the food which snake eats  - collision
        for(int i = 0; i < foodX.length; i++) {
            if(snakeHead.contains(foodX[i], foodY[i])) {
                foodX[i] = foodY[i] = -1;
                count++;
                return true;
            }
        }
        if(count == AMT) renew();   // reset food if all eaten
        return false;
    }
}