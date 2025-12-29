package dev.rohan.gamepro.states;

import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.gameobjects.Snake2;
import dev.rohan.gamepro.gameobjects.SnakeColor;
import dev.rohan.gamepro.uicomponent.Button;
import dev.rohan.gamepro.uicomponent.ColorSlider;
import dev.rohan.gamepro.utils.Assets;

import java.awt.*;

public class SettingsState extends GUIState {

    private ColorSlider rSlider, gSlider, bSlider, sensSlider;
    private Snake2 snake;

    public SettingsState(Handler handler) {
        super(handler, Assets.panels[0], "Settings", Color.YELLOW, (int) (handler.getWidth()/1.2), (int) (handler.getHeight()/1.2));
    }

    @Override
    protected void initUI() {
        int buttonX = panelBounds.x + panelBounds.width/2 - Assets.buttons[8].getWidth();
        int buttonY = panelBounds.y + panelBounds.height - Assets.buttons[8].getHeight() - 10;

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[8].getWidth(), Assets.buttons[8].getHeight(), Assets.buttons[8], Assets.buttons[9], () -> {
            mouseManager.reset();
            handler.getSnake2().updateTextures();
            State.setState(handler.getGame().getMenuState());
        }));
        buttonX += buttons.getLast().getWidth();

        buttons.add(new Button(buttonX, buttonY, Assets.buttons[10], Assets.buttons[11], this::reset));

        buttonX = panelBounds.x + 310;
        buttonY = panelBounds.y + 240;
        buttons.add(new Button(buttonX, buttonY, Assets.buttons[12], Assets.buttons[13], () -> {
            SnakeColor.setReset(false);
            snake.updateTextures();
        }));

        int startX = panelBounds.x + 50, startY = panelBounds.y + 100;
        rSlider = new ColorSlider(startX, startY, 200, "Red", 0, 255, Color.RED);
        gSlider = new ColorSlider(startX, startY + 60, 200, "Green", 0, 255, Color.GREEN);
        bSlider = new ColorSlider(startX, startY + 120, 200, "Blue", 0, 255, Color.BLUE);
        sensSlider = new ColorSlider(startX, startY + 180, 200, "Sensitivity", 1, 10, Color.YELLOW);

        snake = new Snake2(handler, 15*5, 'U', panelBounds.x + panelBounds.width - 100, (panelBounds.y + panelBounds.height)/3);
    }

    @Override
    public void tick() {
        super.tick();
        int mx = handler.getMouseManager().getX();
        int my = handler.getMouseManager().getY();
        boolean mDown = handler.getMouseManager().isClick(); // You might need a "isDown" method in MouseManager

        rSlider.tick(mx, my, mDown);
        gSlider.tick(mx, my, mDown);
        bSlider.tick(mx, my, mDown);
        sensSlider.tick(mx, my, mDown);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        rSlider.render(g);
        gSlider.render(g);
        bSlider.render(g);
        sensSlider.render(g);

        SnakeColor.setColor(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
        SnakeColor.setSensitivity(sensSlider.getValue());

        g.setColor(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
        g.fillRect(panelBounds.x + 300, panelBounds.y + 120, 100, 100);
        g.setColor(Color.WHITE);
        g.drawRect(panelBounds.x + 300, panelBounds.y + 120, 100, 100);

        snake.render(g);
    }

    @Override
    public void reset() {
        SnakeColor.setReset(true);
        snake.updateTextures();
    }
}
