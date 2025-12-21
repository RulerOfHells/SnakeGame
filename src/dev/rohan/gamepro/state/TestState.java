package dev.rohan.gamepro.state;

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
        int shiftY = 60;     //xLast = width - 14,   yLast = height - 60
        int shiftX = 14;
        int lineWeight = 1;
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, handler.getWidth(), lineWeight);
        g.fillRect(0, handler.getHeight()-lineWeight - shiftY, handler.getWidth(), lineWeight);
        g.fillRect(0, 0, lineWeight, handler.getHeight());
        g.fillRect(handler.getWidth()-lineWeight - shiftX, 0, lineWeight, handler.getHeight());
//        g.drawImage(Assets.snakeTail[2].getSubimage(0, 0, 10, 40), 40, 40, 15, 15, null);
//        g.drawImage(Assets.snakeBody[2], 40 + 15, 40, 15, 15, null);
    }

    @Override
    public void reset() {
        //empty
    }
}
