package dev.rohan.gamepro.states;

import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.uicomponent.Button;
import dev.rohan.gamepro.utils.Assets;

public class MenuState extends GUIState {

    public MenuState(Handler handler) {
        super(handler, Assets.panels[0], "Snake Game");
    }

    public void initUI() {
        int buttonX = panelBounds.x + panelBounds.width / 2 - Assets.buttons[2].getWidth() / 2;
        int buttonY = panelBounds.y + panelBounds.height / 4;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[2], Assets.buttons[3], () -> {
            handler.getGame().getGameState().reset();
            State.setState(handler.getGame().getGameState());
        }));
        buttonY += buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[4], Assets.buttons[5], () -> {
            mouseManager.reset();
            State.setState(handler.getGame().getSettingsState());
        }));
        buttonY += buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[0], Assets.buttons[1], () -> handler.getGame().stop()));
    }

    @Override
    public void reset() {
        //no need
    }
}