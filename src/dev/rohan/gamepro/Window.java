package dev.rohan.gamepro;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Color;

public class Window extends JFrame {        //Constructs the main window on screen
    private Canvas canvas;
    private JMenuBar jmenuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private int gameWidth;
    private int gameHeight;
    private String gameTitle;

    public Window(int gameWidth, int gameHeight, String title) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.gameTitle = title;
        
        canvas = new Canvas();
        jmenuBar = new JMenuBar();
        menu = new JMenu("Options");
        menuItem = new JMenuItem("Exit");
    }

    public void menuInit() {
        jmenuBar.add(menu);
        menu.add(menuItem);
        setJMenuBar(jmenuBar);
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

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
