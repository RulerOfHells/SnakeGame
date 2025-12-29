package dev.rohan.gamepro.states;

import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.uicomponent.Button;
import dev.rohan.gamepro.utils.Assets;

import java.awt.*;

public class PauseState extends GUIState {

    public PauseState(Handler handler) {
        super(handler, Assets.panels[0], "Pause Menu");
    }

    @Override
    public void initUI() {
        int buttonX = panelBounds.x + panelBounds.width / 2 - Assets.buttons[2].getWidth() / 2;
        int buttonY = panelBounds.y + panelBounds.height / 4;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[2], Assets.buttons[3], () -> State.setState(handler.getGame().getGameState())));
        buttonY += buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[6], Assets.buttons[7], () -> {
            handler.getGame().getGameState().reset();
            State.setState(handler.getGame().getGameState());
        }));
        buttonY += buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[8], Assets.buttons[9], () -> {
            mouseManager.reset();
            State.setState(handler.getGame().getMenuState());
        }));
    }

    @Override
    public void render(Graphics g) {
        handler.getGame().getGameState().render(g);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        super.render(g);
    }

    @Override
    public void reset() {
        //no need
    }
}