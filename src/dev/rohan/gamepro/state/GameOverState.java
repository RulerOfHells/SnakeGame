package dev.rohan.gamepro.state;

import java.awt.*;
import dev.rohan.gamepro.Handler;

public class GameOverState extends State {       //Game overs when snake collides with game bounds
    private final int width;
    private final int height;
    private int score;

    public GameOverState(Handler handler, int width, int height, int score) {
        super(handler);
        this.height = height;
        this.width = width;
        this.score = score;
    }

    @Override
    public void tick() {        //checks for events
        if(handler.getMouseManager().isClick()) {
            handler.getGame().getGameState().reset();
            State.setState(handler.getGame().getMenuState());
        }
        if(handler.getMenuActionManager().isAction())
           handler.getGame().stop();
    }

    @Override
    public void render(Graphics g) {        //Displays game over message with score count
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        g.setColor(Color.RED);
        g.drawString("Game Over", (handler.getWidth() / 2) - 75, 25);
        g.setColor(Color.GREEN);
        g.drawString("Score: " + score, 25, 60);
    }

    @Override
    public void reset() {
        //no need
    }

    public void setScore(int score) {
        this.score = score;
    }
}
