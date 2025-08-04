package dev.rohan.gamepro.state;

import dev.rohan.gamepro.Assets;
import java.awt.Graphics;

public class TestState extends State {      //purely for testing purposes

    @Override
    public void tick() {
        //empty
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.snakeTail[0], 250, 250, 15, 15, null);
    }

    @Override
    public void reset() {
        //empty
    }
}
