/**
 * @author Rohan Tripathy
 * @version 1.0.0
 * Entry point of the game
*/

package dev.rohan.gamepro;

public class Launcher {
    private static final int WIDTH = 720;
    private static final int HEIGHT = 640;
    private static final String TITLE = "Rohan's Game";

    public static void main(String[] args) {
        Game game = new Game(WIDTH, HEIGHT, TITLE);
        game.start();
    }
}
