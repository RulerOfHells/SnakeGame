package dev.rohan.gamepro;

import java.awt.Graphics;

public abstract class State {       //Generic State to display

    private static State currentState = null;
    
    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void reset();
}
