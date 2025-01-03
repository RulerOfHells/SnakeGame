package dev.rohan.gamepro;

import java.awt.Rectangle;

public class SnakeDirTile {
    public static final int DIRWIDTH = 15;
    public final Rectangle rect;
    public final char direction;

    public SnakeDirTile(Rectangle rect, char direction) {
        this.rect = rect;
        this.direction = direction;
    }

    public boolean update() {
        switch(direction) {
            case 'U':
                return 1 >= rect.height--;
            case 'D':
                rect.y++;
                return 1 >= rect.height--;
            case 'L':
                return 1 >= rect.width--;
            case 'R':
                rect.x++;
                return 1 >= rect.width--;
        }
        return false;
    }
}