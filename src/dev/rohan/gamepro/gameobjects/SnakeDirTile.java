package dev.rohan.gamepro.gameobjects;

import java.awt.Rectangle;

public class SnakeDirTile {
    public static final int DIRWIDTH = 15;
    public final Rectangle rect;
    public final char direction;

    public SnakeDirTile(Rectangle rect, char direction) {
        this.rect = rect;
        this.direction = direction;
    }

    public boolean update(int speed) {
        int tmp = 0;
        switch(direction) {
            case 'U':
                tmp = rect.height;
                rect.height -= speed;
                return 1 >= (tmp);
            case 'D':
                tmp = rect.height;
                rect.y += speed;
                rect.height -= speed;
                return 1 >= (tmp);
            case 'L':
                tmp = rect.width;
                rect.x -= speed;
                rect.width -= speed;
                return 1 >= (tmp);
            case 'R':
                tmp = rect.width;
                rect.x += speed;
                rect.width -= speed;
                return 1 >= (tmp);
        }
        return false;
    }
}