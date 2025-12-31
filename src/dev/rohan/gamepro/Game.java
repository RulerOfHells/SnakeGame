package dev.rohan.gamepro;

import dev.rohan.gamepro.managers.KeyManager;
import dev.rohan.gamepro.managers.MouseManager;
import dev.rohan.gamepro.states.*;
import dev.rohan.gamepro.utils.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;

public class Game implements Runnable {
    private final int width;
    private final int height;
    private final String title;

    private boolean running = false;
    private Window win;
    private Thread thread;
    private BufferStrategy bufferStrategy;

    private State gameState;
    private State menuState;
    private State gameOverState;
    private State pauseState;
    private State settingsState;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private static final Logger logger = Logger.getLogger(Game.class.getName());

    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        
        init();
    }

    public void init() {
        Assets.init();

        win = new Window(width, height, title);
        thread = new Thread(this);
        bufferStrategy = win.getCanvas().getBufferStrategy();
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

        win.getCanvas().addKeyListener(keyManager);
        win.getCanvas().addMouseListener(mouseManager);
        win.getCanvas().addMouseMotionListener(mouseManager);
        win.addKeyListener(keyManager);
        win.addMouseListener(mouseManager);
        win.addMouseMotionListener(mouseManager);

        Handler handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        gameOverState = new GameOverState(handler);
        pauseState = new PauseState(handler);
        settingsState = new SettingsState(handler);

        State testState = new TestState(handler);  //solely for testing states

        State.setState(menuState);      //state to tick and render when the game starts
        
        win.pack();
    }

    public synchronized void start() {      //Runs the game thread
        if(running)
           return;
        thread.start();
        running = true;
        logger.info("Game started!");
    }

    public synchronized void stop() {
        if(!running)
            return;

        logger.info("Game ended");
        running = false;
        win.setVisible(false);
        win.dispose();

    }

    @Override
    public void run() {     //basic game loop
        win.display();

        final int FPS = 60;
        int counter = 0;
        double timePerTick = 1_000_000_000 / (double) FPS;
        double delta = 0.0;
        long lastTime;
        long now;
        long timer = 0;
        lastTime = System.nanoTime();
        
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
               tick();
               render();
               counter++;
               delta--;
            }

            if(timer >= 1_000_000_000) {                //logs FPS per second
               String message = "FPS : " + counter;
               logger.info(message);
               counter = 0;
               timer = 0;
            }
        }
        stop();
    }
    
    public void tick() {
        if(!running)
            return;
        State.getState().tick();
    }

    public void render() {
        if(!running)
            return;
        if(bufferStrategy == null)
            win.getCanvas().createBufferStrategy(3);

        bufferStrategy = win.getCanvas().getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.clearRect(0, 0, width, height);        //refresh screen each time

                               //Drawing area starts
        State.getState().render(graphics);
                               //Drawing area ends

        graphics.dispose();
        bufferStrategy.show();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public State getGameState() {
        return gameState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getGameOverState() {
        return gameOverState;
    }

    public State getPauseState() {
        return pauseState;
    }

    public State getSettingsState() {
        return settingsState;
    }
}