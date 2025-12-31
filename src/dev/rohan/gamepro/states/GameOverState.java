package dev.rohan.gamepro.states;

import java.awt.*;
import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.uicomponent.Button;
import dev.rohan.gamepro.utils.Assets;

public class GameOverState extends GUIState {       //Game overs when snake collides with game bounds
    private int score;

    public GameOverState(Handler handler) {
        super(handler, Assets.panels[0], "Game Over");
    }

    public void initUI() {
        int buttonX = panelBounds.x + panelBounds.width / 2 - Assets.buttons[2].getWidth() / 2;
        int buttonY = panelBounds.y + panelBounds.height - Assets.buttons[2].getHeight() - 10;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[8], Assets.buttons[9], () -> {
            mouseManager.reset();
            State.setState(handler.getGame().getMenuState());
        }));
        buttonY -= buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[6], Assets.buttons[7], () -> {
            handler.getGame().getGameState().reset();
            State.setState(handler.getGame().getGameState());
        }));
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.PINK);
        FontMetrics metrics = g.getFontMetrics(font);
        String scoreLabel = "Your score is: " + score;
        g.drawString(scoreLabel, panelBounds.x + (panelBounds.width - metrics.stringWidth(scoreLabel))/2, buttons.getLast().getY() - 50);
    }

    @Override
    public void reset() {
        //no need
    }

    public void setScore(int score) {
        this.score = score;
    }
}
