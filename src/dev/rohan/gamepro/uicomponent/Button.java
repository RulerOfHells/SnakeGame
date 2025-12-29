package dev.rohan.gamepro.uicomponent;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private final Rectangle rectangle;
    private final BufferedImage defaultImage;
    private final BufferedImage hoverImage;
    private final ButtonAction action;

    private boolean hovering;

    public Button(int x, int y, BufferedImage defaultImage, BufferedImage hoverImage,  ButtonAction action) {
        this(x, y, defaultImage.getWidth(), defaultImage.getHeight(), defaultImage, hoverImage, action);
    }

    public Button(int x, int y, int width, int height, BufferedImage defaultImage, BufferedImage hoverImage,  ButtonAction action) {
        rectangle = new Rectangle(x, y, width, height);
        this.defaultImage = defaultImage;
        this.hoverImage = hoverImage;
        this.action = action;
    }

    public void tick(int mx, int my, boolean isClick) {
        hovering = rectangle.contains(mx, my);
        if (hovering && isClick)
            action.action();
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image = (hovering) ? hoverImage : defaultImage;
        g2d.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
    }

    public int getX() {
        return rectangle.x;
    }
    public int getY() {
        return rectangle.y;
    }
    public int getWidth() {
        return rectangle.width;
    }
    public int getHeight() {
        return rectangle.height;
    }

}
