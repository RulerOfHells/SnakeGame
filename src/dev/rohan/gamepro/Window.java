package dev.rohan.gamepro;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {        //Constructs the main window on screen
    private final Canvas canvas;
    private final int gameWidth;
    private final int gameHeight;
    private final String gameTitle;

    public Window(int gameWidth, int gameHeight, String title) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.gameTitle = title;
        
        canvas = new Canvas();
    }

    public void display() {

        canvas.setSize(gameWidth, gameHeight);
        canvas.setFocusable(false);

        setTitle(gameTitle);
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setMaximumSize(new Dimension(gameWidth, gameHeight));
        setMinimumSize(new Dimension(gameWidth, gameHeight));
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        add(canvas);
        setResizable(false);
        pack();
        
        setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
