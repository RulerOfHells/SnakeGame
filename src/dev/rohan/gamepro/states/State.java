package dev.rohan.gamepro.states;

import dev.rohan.gamepro.Handler;

import java.awt.Graphics;

public abstract class State {       //Generic State to tick and render

    protected final Handler handler;
    private static State currentState = null;
    
    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public State(Handler handler) {this.handler = handler;}

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void reset();
}
