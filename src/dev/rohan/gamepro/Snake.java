package dev.rohan.gamepro;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Snake {
    private int body;
    private int size;
    private int count;
    private int[] x;
    private int[] y;
    private Handler handler;
    private boolean gameOver;

    public Snake(Handler handler) {
        this.handler = handler;
        reinit();
    }

    public void reinit() {
        count = 0;
        body = 5;         //Starts with snake having this much body parts
        size = 30;
        x = new int[(handler.getWidth()*handler.getHeight())/size];     //grid-based snake movement approach
        y = new int[(handler.getWidth()*handler.getHeight())/size];
        x[0] = 0;
        y[0] = 30;
        gameOver = false;

        handler.getKeyManager().setDirection('R');  //initial snake direction
    }

    private boolean isOutOfBoundsY() {      //checks if snake head is out of bounds
        if(y[0] < 0) {
            y[0] = 0;
            return true;
        }

        if(y[0] > 580 - size) {
            y[0] = 580 - size;
            return true;
        }
        return false;
    }

    private boolean isOutOfBoundsX() {
        if(x[0] < 0) {
            x[0] = 0;
            return true;
        }

        if(x[0] > 707 - size) {
            x[0] = 707 - size;
            return true;
        }
        return false;
    }

    private boolean checkBoundCrashes() {
        return isOutOfBoundsX() || isOutOfBoundsY();
    }

    private boolean checkSelfCrash() {
        for(int i = 1; i < body; i++) {
            Rectangle rect = new Rectangle(x[i], y[i], size, size);
            if(rect.contains(x[0], y[0])) return true;
        }
        return false;
    }

    private void checkGameOver() {
        gameOver = checkBoundCrashes() || checkSelfCrash();
    }

    private void move() {                    //Handles snake movements
        for(int i = body; i > 0; i--) {         //Updates snake body positions
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(handler.getKeyManager().getDirection()) {    //updates snake head position and registers game over
            case 'U':
            y[0] -= size;
            break;

            case 'D':
            y[0] += size;
            break;

            case 'L':
            x[0] -= size;
            break;

            case 'R':
            x[0] += size;
            break;

            default: //do nothing
        }
    }

    private void collide() {         //Eats the food on collision
        int[] foodX = handler.getFood().getFoodX();
        int[] foodY = handler.getFood().getFoodY();

        for(int i = 0; i < foodX.length; i++) {     //Loops through each food and check if snake collides

            if(foodX[i] >= x[0] && foodX[i] <= x[0]+size && foodY[i] >= y[0] && foodY[i] <= y[0]+size) {
                handler.getFood().unPlaceFood(foodX[i], foodY[i]);
                body++;
                count++;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void tick() {
        move();
        collide();
        checkGameOver();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void render(Graphics g) {        //Displays snake on screen
        for(int i = 0; i < body; i++)
            if(i == 0) {                //snake head
                g.setColor(Color.BLUE);
                g.fillRect(x[i], y[i], size, size);
            }
            else {
                g.setColor(new Color(94, 3, 152));
                g.fillRect(x[i], y[i], size, size);
                g.setColor(Color.red);
                g.fillRect((int) (x[i] + size/2.0), (int) (y[i] + size/2.0), 5, 5);
            }
    }
}