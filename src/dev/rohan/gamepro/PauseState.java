package dev.rohan.gamepro;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PauseState extends State {

    private Handler handler;

    public PauseState(Handler handler) {
        this.handler = handler;
    }

    private int isHovering(int x, int y) {               //checks for mouse hover in menu title                
        Rectangle rect = new Rectangle(152, 320, 437, 103);
        Rectangle rect2 = new Rectangle(253, 428, 239, 110);
        if(rect.contains(x, y))
            return 1;
        if(rect2.contains(x, y))
            return 2;
        return 0;
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isClick() &&
                isHovering(handler.getMouseManager().getX(), handler.getMouseManager().getY()) == 1)
           State.setState(handler.getGame().getGameState());      //switch state on mouse click
        else if(handler.getMouseManager().isClick() &&
                isHovering(handler.getMouseManager().getX(), handler.getMouseManager().getY()) == 2)
            handler.getGame().stop();

        if(handler.getMenuActionManager().isAction())
           handler.getGame().stop();
    }

    @Override
    public void render(Graphics g) {
        if(Assets.menu[0] != null && Assets.menu[1] != null) {      //Menu hover animation
            if(isHovering(handler.getMouseManager().getX(), handler.getMouseManager().getY()) == 1)
                g.drawImage(Assets.pause[1], 0, 0, null);
            else if(isHovering(handler.getMouseManager().getX(), handler.getMouseManager().getY()) == 2)
                g.drawImage(Assets.pause[2], 0, 0, null);
            else g.drawImage(Assets.pause[0], 0, 0, null);
        }
    }

    @Override
    public void reset() {
        //no need
    }
}