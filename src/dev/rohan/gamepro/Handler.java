package dev.rohan.gamepro;

public class Handler {      //Helper class to provide smooth transition between states
    private Game game;
    private Snake snake;
    private Food food;

    public Handler(Game game) {
        this.game = game;
        snake = new Snake(this);
        food = new Food();
    }

    public Game getGame() {
        return game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public MenuActionManager getMenuActionManager() {
        return game.getMenuActionManager();
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}
