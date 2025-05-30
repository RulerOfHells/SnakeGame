package dev.rohan.gamepro.state;

import dev.rohan.gamepro.Handler;
import dev.rohan.gamepro.gameobjects.Food;
import dev.rohan.gamepro.gameobjects.Snake2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameState extends State {      //Implementation of State to display game contents

    private final Handler handler;
    // private Snake snake;
    private final Snake2 snake;
    private final Food food;

    public GameState(Handler handler) {
        this.handler = handler;
        food = handler.getFood();
        snake = handler.getSnake2();
    }

    @Override
    public void tick() {    //checks for events and updates snake
        snake.tick();

        if(handler.getKeyManager().isSwitchState())
           State.setState(handler.getGame().getPauseState());

        if(handler.getMenuActionManager().isAction())
           handler.getGame().stop();

        if(snake.isGameOver()) {
            ((GameOverState) handler.getGame().getGameOverState()).setScore(snake.getCount());
            State.setState(handler.getGame().getGameOverState());
        }
    }

    @Override
    public void render(Graphics g) {    //Displays snake and foods
        food.placeFood(g);
        snake.render(g);

        //draw score
        g.setColor(Color.GREEN);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Score: "+snake.getCount(), 5, 20);
    }

    @Override
    public void reset() {       //resets when game is over
        food.renew();
        snake.reinit();
    }
}