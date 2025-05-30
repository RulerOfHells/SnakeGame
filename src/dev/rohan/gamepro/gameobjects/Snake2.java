package dev.rohan.gamepro.gameobjects;

import dev.rohan.gamepro.Handler;
import static dev.rohan.gamepro.gameobjects.SnakeDirTile.DIRWIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Snake2 {
    private LinkedList<SnakeDirTile> snake;
    private final Handler handler;
    private boolean gameOver;
    private Color snakeColor;
    private char dir;
    private int count;
    private volatile int speed;

    public Snake2(Handler handler) {
        this.handler = handler;
        reinit();
    }

    public void reinit() {
        gameOver = false;
        count = 0;
        speed = 1;
        dir = 'R';
        snakeColor = Color.BLUE;
        snake = new LinkedList<>();
        snake.add(new SnakeDirTile(new Rectangle(100, 100, DIRWIDTH, DIRWIDTH), dir));
    }

    private boolean checkBoundCrashes() {
        final Rectangle rect = new Rectangle(0, 0, 707, 580);
        return !rect.contains(snake.getFirst().rect);
    }

    private boolean checkSelfCrash() {
        int collidingX = 0, collidingY = 0;
        SnakeDirTile first = snake.getFirst();
        
        switch(first.direction) {
            case 'U':
                collidingX = first.rect.x + DIRWIDTH/2;
                collidingY = first.rect.y;
                break;
            
            case 'D':
                collidingX = first.rect.x + DIRWIDTH/2;
                collidingY = first.rect.y + first.rect.height;
                break;
            case 'L':
                collidingX = first.rect.x;
                collidingY = first.rect.y + DIRWIDTH/2;
                break;
            case 'R':
                collidingX = first.rect.x + first.rect.width;
                collidingY = first.rect.y + DIRWIDTH/2;
                break;
        }

        for(int i = snake.size()-1; i > 1; i--)
                if(snake.get(i).rect.contains(collidingX, collidingY) && first.rect.width >= DIRWIDTH/2 &&
                    first.rect.height >= DIRWIDTH/2 && snake.get(i).rect.width >= DIRWIDTH/2 &&
                        snake.get(i).rect.height >= DIRWIDTH/2)
                    return true;
        return false;
    }

    private void checkGameOver() {
        gameOver = checkBoundCrashes() || checkSelfCrash();
    }

    private void move() {                    //Handles snake movements
        final SnakeDirTile first = snake.getFirst();
        final SnakeDirTile last = snake.getLast();
        switch(dir) {
            case 'D':
                if(first.rect.height >= DIRWIDTH) {
                    Rectangle rect = new Rectangle(first.rect.x + (first.direction == 'L' ? 0 : first.rect.width - DIRWIDTH), first.rect.y + DIRWIDTH, DIRWIDTH, 1);
                    snake.addFirst(new SnakeDirTile(rect, dir));
                } else {
                    first.rect.height += speed;
                }
            break;

            case 'U':
                if(first.rect.height >= DIRWIDTH) {
                    Rectangle rect = new Rectangle(first.rect.x + (first.direction == 'L' ? 0 : first.rect.width - DIRWIDTH), first.rect.y, DIRWIDTH, 1);
                    snake.addFirst(new SnakeDirTile(rect, dir));
                } else {
                    first.rect.y -= speed;
                    first.rect.height += speed;
                }
            break;

            case 'L':
                if(first.rect.width >= DIRWIDTH) {
                    Rectangle rect = new Rectangle(first.rect.x - speed, first.rect.y + (first.direction == 'U' ? 0 : first.rect.height - DIRWIDTH), speed, DIRWIDTH);
                    snake.addFirst(new SnakeDirTile(rect, dir));
                } else {
                    first.rect.x -= speed;
                    first.rect.width += speed;
                }
            break;

            case 'R':
                if(first.rect.width >= DIRWIDTH) {
                    Rectangle rect = new Rectangle(first.rect.x + first.rect.width, first.rect.y + (first.direction == 'U' ? 0 : first.rect.height - DIRWIDTH), 1, DIRWIDTH);
                    snake.addFirst(new SnakeDirTile(rect, dir));
                } else {
                    first.rect.width += speed;
                }
            break;
        }
        if (last.update(speed))
            snake.removeLast();
    }

    private void collide() {         //Eats the food on collision
        var first = snake.getFirst();
        var last = snake.getLast();
        if (handler.getFood().unPlaceFood(first.rect)) { // If snake eats food
            count++;
            switch (last.direction) {
                case 'U':
                    last.rect.height += DIRWIDTH;
                    break;

                case 'D':
                    last.rect.y -= DIRWIDTH;
                    last.rect.height += DIRWIDTH;
                    break;

                case 'L':
                    last.rect.width += DIRWIDTH;
                    break;

                case 'R':
                    last.rect.x -= DIRWIDTH;
                    last.rect.width += DIRWIDTH;
                    break;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void tick() {
        dir = handler.getKeyManager().getDirection();
        speed = (count / 20 == 0)? 1 : (count / 20) + 1;
        move();
        collide();
        checkGameOver();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void render(Graphics g) {        //Displays snake on screen
        final Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(snakeColor);
        for (var body : snake) {    //rendering body
            final Rectangle tile = body.rect;
            graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
        }
        
        graphics2D.setColor(Color.RED);
        final SnakeDirTile first = snake.getFirst();
        switch(first.direction) { //rendering head
            case 'U': case 'L':
                graphics2D.fillRect(first.rect.x, first.rect.y, DIRWIDTH, DIRWIDTH);
                break;

            case 'D':
                graphics2D.fillRect(first.rect.x, first.rect.y + first.rect.height - DIRWIDTH, DIRWIDTH, DIRWIDTH);
                break;

            case 'R':
                graphics2D.fillRect(first.rect.x + first.rect.width - DIRWIDTH, first.rect.y, DIRWIDTH, DIRWIDTH);
                break;
        }
    }
}
