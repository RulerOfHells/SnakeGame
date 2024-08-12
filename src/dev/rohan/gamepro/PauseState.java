package dev.rohan.gamepro;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PauseState extends State {

    private Handler handler;

    public PauseState(Handler handler) {
        this.handler = handler;
    }

    private boolean isHovering(int x, int y) {               //checks for mouse hover in menu title                
        Rectangle rect = new Rectangle(152, 320, 437, 103);
        return rect.contains(x, y);
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isClick() &&
                isHovering(handler.getMouseManager().getX(), handler.getMouseManager().getY()))
           State.setState(handler.getGame().getGameState());      //switch state on mouse click

        if(handler.getMenuActionManager().isAction())
           handler.getGame().stop();
    }

    @Override
    public void render(Graphics g) {
        if(Assets.menu[0] != null && Assets.menu[1] != null) {      //Menu hover animation
            if(isHovering(handler.getMouseManager().getX(), handler.getMouseManager().getY()))
                g.drawImage(Assets.pause[0], 0, 0, null);
            else
                g.drawImage(Assets.pause[0], 0, 0, null);
        }
    }

    @Override
    public void reset() {
        //no need
    }
}