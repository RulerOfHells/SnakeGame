package dev.rohan.gamepro.gameobjects;

import dev.rohan.gamepro.utils.Assets;
import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.utils.TextureAdjust;

import static dev.rohan.gamepro.gameobjects.SnakeDirTile.DIRWIDTH;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public final class Snake2 {
    private LinkedList<SnakeDirTile> snake;
    private volatile BufferedImage[] snakeHead;
    private volatile BufferedImage[] snakeBody;
    private volatile BufferedImage[] snakeTail;
    private final Handler handler;
    private boolean gameOver;
    private char dir;
    private int count;
    private volatile int speed;

    public Snake2(Handler handler) {
        this(handler, DIRWIDTH * 3, 'R', 100, 100);
    }

    public Snake2(Handler handler, int size, char dir, int x, int y) {
        this.handler = handler;
        snakeHead = Assets.snakeHead;
        snakeBody = Assets.snakeBody;
        snakeTail = Assets.snakeTail;
        reinit(x, y, size, dir);
    }

    public void reinit() {
        reinit(100, 100, DIRWIDTH * 3, 'R');
    }

    private void reinit(int x, int y, int size, char dir) {
        gameOver = false;
        count = 0;
        speed = 1;
        handler.getKeyManager().setDirection(dir);

        if(snake == null)
            snake = new LinkedList<>();
        else
            snake.clear();

        if(dir == 'L' || dir == 'R')
            snake.add(new SnakeDirTile(new Rectangle(x, y, size, DIRWIDTH), dir));
        else
            snake.add(new SnakeDirTile(new Rectangle(x, y, DIRWIDTH, size), dir));
    }

    public void updateTextures() {
        if(SnakeColor.isReset()) {
            snakeHead = Assets.snakeHead;
            snakeBody = Assets.snakeBody;
            snakeTail = Assets.snakeTail;
        }
        else {
            assert snakeHead != null;
            assert snakeBody != null;
            assert snakeTail != null;
            snakeHead = TextureAdjust.shiftBlueToTarget(Assets.snakeHead, SnakeColor.getColor(), SnakeColor.getSensitivity());
            snakeBody = TextureAdjust.shiftBlueToTarget(Assets.snakeBody, SnakeColor.getColor(), SnakeColor.getSensitivity());
            snakeTail = TextureAdjust.shiftBlueToTarget(Assets.snakeTail, SnakeColor.getColor(), SnakeColor.getSensitivity());
        }
    }

    private boolean checkBoundCrashes() {
        final Rectangle rect = new Rectangle(0, 0, 707, 580);
        return !rect.contains(snake.getFirst().rect);
    }

    private boolean checkSelfCrash() {
        int collidingX = 0, collidingY = 0;
        SnakeDirTile first = snake.getFirst();

        collidingY = switch (first.direction) {   //Determines the colliding point based on the direction of the snake
            case 'U' -> {
                collidingX = first.rect.x + DIRWIDTH / 2;
                yield first.rect.y;
            }
            case 'D' -> {
                collidingX = first.rect.x + DIRWIDTH / 2;
                yield first.rect.y + first.rect.height;
            }
            case 'L' -> {
                collidingX = first.rect.x;
                yield first.rect.y + DIRWIDTH / 2;
            }
            case 'R' -> {
                collidingX = first.rect.x + first.rect.width;
                yield first.rect.y + DIRWIDTH / 2;
            }
            default -> collidingY;
        };

        for(int i = snake.size()-1; i > 1; i--) //Checks if the colliding point is inside any of the snake's body parts

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
        switch (dir) {
            case 'R':
                if (first.direction == 'R')
                    first.rect.width += speed;

                else if (first.rect.height >= 15) {
                    Rectangle r = new Rectangle(first.rect.x + 15, first.rect.y + ((first.direction == 'U')? 0 : first.rect.height - 15), 1, 15);
                    snake.addFirst(new SnakeDirTile(r, dir));
                }
                else if(first.direction == 'U') {
                    first.rect.y -= speed;
                    first.rect.height += speed;
                }
                else
                    first.rect.height += speed;
                break;

            case 'L':
                if (first.direction == 'L') {
                    first.rect.x -= speed;
                    first.rect.width += speed;
                } else if (first.rect.height >= 15) {
                    Rectangle r = new Rectangle(first.rect.x, first.rect.y + ((first.direction == 'U') ? 0 : first.rect.height - 15), 1, 15);
                    snake.addFirst(new SnakeDirTile(r, dir));
                }
                else if(first.direction == 'U') {
                    first.rect.y -= speed;
                    first.rect.height += speed;
                }
                else
                    first.rect.height += speed;
                break;

            case 'U':
                if (first.direction == 'U') {
                    first.rect.y -= speed;
                    first.rect.height += speed;
                } else if (first.rect.width >= 15) {
                    Rectangle r = new Rectangle(first.rect.x + ((first.direction == 'L') ? 0 : first.rect.width - 15), first.rect.y, 15, 1);
                    snake.addFirst(new SnakeDirTile(r, dir));
                }
                else if(first.direction == 'R')
                    first.rect.width += speed;
                else {
                    first.rect.x -= speed;
                    first.rect.width += speed;
                }
                break;

            case 'D':
                if (first.direction == 'D')
                    first.rect.height += speed;

                else if (first.rect.width >= 15) {
                    Rectangle r = new Rectangle(first.rect.x + ((first.direction == 'L') ? 0 : first.rect.width - 15), first.rect.y + 15, 15,1);
                    snake.addFirst(new SnakeDirTile(r, dir));
                }
                else if(first.direction == 'R')
                    first.rect.width += speed;
                else {
                    first.rect.x -= speed;
                    first.rect.width += speed;
                }
                break;
        }
        if (snake.size() > 1 && last.update(speed, snake.get(snake.size()-2).direction))
            snake.removeLast();
        else if(snake.size() == 1)
            last.update(speed);
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
    
    public void tick() {
        dir = handler.getKeyManager().getDirection();
        speed = (count / 20 == 0)? 1 : (count / 20) + 1;
        move();
        collide();
        checkGameOver();
    }
    
    public void render(Graphics graphics) {     // Displays snake on screen
        final Graphics2D g = (Graphics2D) graphics;

        if(snake.size() == 1 && (snake.getLast().rect.width + snake.getLast().rect.height <= 2*DIRWIDTH))   //not appropriate size
            return;

        drawTail(g);
        drawBody(g);
        drawHead(g);
    }

    private void drawHead(Graphics g) {
        SnakeDirTile tile = snake.getFirst();
        switch(tile.direction) {
            case 'U':
                g.drawImage(snakeHead[0], tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
                break;
            case 'D':
                g.drawImage(snakeHead[1], tile.rect.x, tile.rect.y + tile.rect.height - DIRWIDTH, DIRWIDTH, DIRWIDTH, null);
                break;
            case 'L':
                g.drawImage(snakeHead[2], tile.rect.x, tile.rect.y,  DIRWIDTH, DIRWIDTH, null);
                break;
            case 'R':
                g.drawImage(snakeHead[3], tile.rect.x + tile.rect.width - DIRWIDTH, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
        }
    }

    private void drawTail(Graphics g) {
        SnakeDirTile tile = snake.getLast();
        g.setColor(Color.RED);
        switch(tile.direction) {
            case 'U':
                g.drawImage(snakeTail[1], tile.rect.x, tile.rect.y + tile.rect.height - DIRWIDTH, DIRWIDTH, DIRWIDTH, null);
//                g.drawRect(tile.rect.x, tile.rect.y + tile.rect.height - DIRWIDTH, DIRWIDTH, DIRWIDTH);
                break;
            case 'D':
                g.drawImage(snakeTail[0], tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
//                g.drawRect(tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH);
                break;
            case 'L':
                g.drawImage(snakeTail[3], tile.rect.x + tile.rect.width - DIRWIDTH, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
//                g.drawRect(tile.rect.x + tile.rect.width - DIRWIDTH, tile.rect.y, DIRWIDTH, DIRWIDTH);
                break;
            case 'R':
                g.drawImage(snakeTail[2], tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
//                g.drawRect(tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH);
                break;
        }
    }

    private void drawBody(Graphics g) {
        var tile = snake.getFirst();
        if(snake.size() == 1) {
            switch(tile.direction) {
                case 'U': case 'D':
                    g.drawImage(snakeBody[1], tile.rect.x, tile.rect.y + DIRWIDTH, DIRWIDTH, tile.rect.height - 2*DIRWIDTH, null);
                    break;
                case 'L': case 'R':
                    g.drawImage(snakeBody[0], tile.rect.x + DIRWIDTH, tile.rect.y, tile.rect.width - 2*DIRWIDTH, DIRWIDTH, null);
            }
        }
        else {
            SnakeDirTile previous;
            Image turn;
            boolean isFirst;
            boolean isLast;
            int amtShift;
            int amtReduce;

            for(int i = 0; i < snake.size(); i++) {
                tile = snake.get(i);
                isLast = i == snake.size() - 1;
                isFirst = i == 0;
                previous = (!isFirst)? snake.get(i-1) : null;

                turn = getTurnImage(isFirst, isLast, previous != null ? previous.direction : 0, tile.direction);

                if(tile.isLesserSquare())
                    continue;

                if(isLast) {
                    amtShift = DIRWIDTH;
                    amtReduce = 2*DIRWIDTH;
                }
                else {
                    amtShift = 0;
                    amtReduce = DIRWIDTH;
                }

                switch(tile.direction) {
                    case 'U':
                        g.drawImage(turn, tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
                        amtReduce += incrementTailHandoff(i, dir);
                        g.drawImage(snakeBody[1], tile.rect.x, tile.rect.y + DIRWIDTH, DIRWIDTH, tile.rect.height - amtReduce, null);
                        break;
                    case 'D':
                        g.drawImage(turn,  tile.rect.x, tile.rect.y + tile.rect.height - DIRWIDTH, DIRWIDTH, DIRWIDTH, null);
                        amtShift += incrementTailHandoff(i, dir);
                        amtReduce += incrementTailHandoff(i, dir);
                        g.drawImage(snakeBody[1], tile.rect.x, tile.rect.y + amtShift, DIRWIDTH, tile.rect.height - amtReduce, null);
                        break;
                    case 'L':
                        g.drawImage(turn, tile.rect.x, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
                        amtReduce += incrementTailHandoff(i, dir);
                        g.drawImage(snakeBody[0], tile.rect.x + DIRWIDTH, tile.rect.y, tile.rect.width - amtReduce, DIRWIDTH, null);
                        break;
                    case 'R':
                        g.drawImage(turn, tile.rect.x + tile.rect.width - DIRWIDTH, tile.rect.y, DIRWIDTH, DIRWIDTH, null);
                        amtShift += incrementTailHandoff(i, dir);
                        amtReduce += incrementTailHandoff(i, dir);
                        g.drawImage(snakeBody[0], tile.rect.x + amtShift, tile.rect.y, tile.rect.width - amtReduce, DIRWIDTH, null);
                }
            }
        }
    }

    private int incrementTailHandoff(int i, char dir) {
        if(i == snake.size() - 2 && snake.getLast().isLesserSquare()) {
            return switch(dir) {
                case 'U', 'D' -> DIRWIDTH - snake.getLast().rect.height;
                default -> DIRWIDTH - snake.getLast().rect.width;
            };
        }
        return 0;
    }

    private Image getTurnImage(boolean isFirst, boolean isLast, char prevDir, char currDir) {
        if(isFirst)
            return null;

        if(isLast && prevDir == snake.getLast().direction)
            return switch (dir) {
                case 'U', 'D' -> snakeBody[1];
                default -> snakeBody[0];
            };
        else {
            return switch (currDir) {
                case 'U' -> (prevDir == 'L') ? snakeBody[4] : snakeBody[5];
                case 'D' -> (prevDir == 'L') ? snakeBody[2] : snakeBody[3];
                case 'L' -> (prevDir == 'U') ? snakeBody[3] : snakeBody[5];
                default -> (prevDir == 'U') ? snakeBody[2] : snakeBody[4];    //R
            };
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCount() {
        return count;
    }

    public int getSpeed() {
        return speed;
    }
}
