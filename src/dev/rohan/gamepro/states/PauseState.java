package dev.rohan.gamepro.states;

import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.managers.MouseManager;
import dev.rohan.gamepro.uicomponent.Button;
import dev.rohan.gamepro.utils.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PauseState extends State {

    private final BufferedImage panel;
    private final List<Button> buttons;
    private final MouseManager mouseManager;

    private int panelX;
    private int panelY;


    public PauseState(Handler handler) {
        super(handler);

        panel = Assets.panels[0];
        buttons = new ArrayList<>();
        mouseManager = handler.getMouseManager();

        init();
    }

    private void init() {
        panelX = handler.getWidth()/2 - panel.getWidth()/2 - 8;
        panelY = handler.getHeight()/2 - panel.getHeight()/2 - 8;

        int buttonX = panelX + panel.getWidth() / 2 - Assets.buttons[2].getWidth() / 2;
        int buttonY = panelY + panel.getHeight() / 4;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[2], Assets.buttons[3], () -> State.setState(handler.getGame().getGameState())));
        buttonY += buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[6], Assets.buttons[7], () -> {
            handler.getGame().getGameState().reset();
            State.setState(handler.getGame().getGameState());
        }));
        buttonY += buttons.getLast().getHeight() + 2;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[8], Assets.buttons[9], () -> {
            handler.getMouseManager().reset();
            State.setState(handler.getGame().getMenuState());
        }));
    }

    @Override
    public void tick() {
        for (Button button : buttons)
            button.tick(mouseManager.getX(), mouseManager.getY(), mouseManager.isClick());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(panel, panelX, panelY,null);
        g.setColor(Color.RED);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        g.drawString("Pause Menu", panelX + panel.getWidth()/4, panelY + 57);
        for (Button button : buttons)
            button.render(g);
    }

    @Override
    public void reset() {
        //no need
    }
}