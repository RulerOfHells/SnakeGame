package dev.rohan.gamepro.states;

import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.managers.MouseManager;
import dev.rohan.gamepro.uicomponent.Button;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class GUIState extends State {

    protected final BufferedImage panel;
    protected final List<Button> buttons;
    protected final MouseManager mouseManager;

    protected Rectangle panelBounds;

    protected Font font;
    protected Color color;
    protected String title;

    public GUIState(Handler handler, BufferedImage panel, String title) {
        this(handler, panel, title, Color.RED);
    }

    public GUIState(Handler handler, BufferedImage panel, String title, Color color) {
        this(handler, panel, title, new Font("Comic Sans MS", Font.BOLD, 50), color, panel.getWidth(), panel.getHeight());
    }

    public GUIState(Handler handler, BufferedImage panel, String title, int width, int height) {
        this(handler, panel, title, new Font("Comic Sans MS", Font.BOLD, 50), Color.RED, width, height);
    }

    public GUIState(Handler handler, BufferedImage panel, String title, Color color, int width, int height) {
        this(handler, panel, title, new Font("Comic Sans MS", Font.BOLD, 50), color, width, height);
    }

    public GUIState(Handler handler, BufferedImage panel, String title, Font font, Color color, int width, int height) {
        super(handler);

        this.panel = panel;
        this.font = font;
        this.title = title;
        this.color = color;

        panelBounds = new Rectangle(0, 0, width, height);
        mouseManager = handler.getMouseManager();
        buttons = new ArrayList<>();

        init();
        initUI();
    }

    private void init() {
        panelBounds.x = handler.getWidth()/2 - panelBounds.width/2 - 8;
        panelBounds.y = handler.getHeight()/2 - panelBounds.height/2 - 8;
    }

    protected abstract void initUI();

    @Override
    public void tick() {
        for (Button button : buttons)
            button.tick(mouseManager.getX(), mouseManager.getY(), mouseManager.isClick());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(panel, panelBounds.x, panelBounds.y, panelBounds.width, panelBounds.height, null);
        drawTitle(g);
        for (Button button : buttons)
            button.render(g);
    }

    private void drawTitle(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(color);
        g.setFont(font);
        g.drawString(title, panelBounds.x + (panelBounds.width - metrics.stringWidth(title))/2, panelBounds.y + 57);
    }
}
