package dev.rohan.gamepro;

import java.awt.Graphics;
import java.awt.Rectangle;

public class MenuState extends State {

    private Handler handler;

    public MenuState(Handler handler) {
        this.handler = handler;
    }

    private boolean isHovering(int x, int y) {               //checks for mouse hover in menu title                
        Rectangle rect = new Rectangle(85, 254, 545, 64);
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
                g.drawImage(Assets.menu[1], 0, 0, null);
            else
                g.drawImage(Assets.menu[0], 0, 0, null);
        }
    }

    @Override
    public void reset() {
        //no need
    }
}