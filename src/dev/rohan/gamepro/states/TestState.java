package dev.rohan.gamepro.states;

import dev.rohan.gamepro.utils.Assets;
import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.utils.TextureAdjust;

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
        //xLast = width - 14,   yLast = height - 37   GAME BOUNDS [0, 2, width-14, height-40]
        g.setColor(Color.BLUE);
        g.drawRect(0, 2, handler.getWidth() - 15, handler.getHeight() - 40);
        g.drawImage(Assets.panels[0], handler.getWidth()/2 - Assets.panels[0].getWidth()/2 - 8, handler.getHeight()/2 - Assets.panels[0].getHeight()/2 - 8,null);

        //g.drawImage(TextureAdjust.shiftBlueToTarget(Assets.snakeHead[3], Color.RED, 1), 100, 115, 15, 15, null);
    }

    @Override
    public void reset() {
        //empty
    }
}
