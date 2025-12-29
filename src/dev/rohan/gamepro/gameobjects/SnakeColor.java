package dev.rohan.gamepro.gameobjects;

import java.awt.*;

public final class SnakeColor {
    private static volatile Color color;
    private static volatile double sensitivity;
    private static volatile boolean reset = true;

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        SnakeColor.color = color;
    }

    public static boolean isReset() {
        return reset;
    }

    public static void setReset(boolean reset) {
        SnakeColor.reset = reset;
    }

    public static double getSensitivity() {
        return sensitivity;
    }

    public static void setSensitivity(double sensitivity) {
        SnakeColor.sensitivity = sensitivity;
    }
}
