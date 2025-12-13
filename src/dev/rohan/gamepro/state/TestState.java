package dev.rohan.gamepro.state;

import dev.rohan.gamepro.Assets;
import dev.rohan.gamepro.Handler;

import java.awt.*;

public class TestState extends State {      //purely for testing purposes

    public TestState(Handler handler) {
        super(handler);
    }
    @Override
    public void tick() {
        //empty
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect(0,0, 640, 480);
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, handler.getWidth(), handler.getHeight());
    }

    @Override
    public void reset() {
        //empty
    }
}
