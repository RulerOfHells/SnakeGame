package dev.rohan.gamepro;

import java.awt.event.*;

public class KeyManager implements KeyListener {

    private char direction;
    private boolean switchState;
    private long lastPress = 0;

    @Override
    public void keyTyped(KeyEvent e) {
        //not required
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((System.currentTimeMillis() - lastPress) > 75) {           //prevents instant key registeration
            if (e.getKeyCode() == KeyEvent.VK_W && direction != 'D')
                direction = 'U';
            if (e.getKeyCode() == KeyEvent.VK_S && direction != 'U')
                direction = 'D';
            if (e.getKeyCode() == KeyEvent.VK_A && direction != 'R')
                direction = 'L';
            if (e.getKeyCode() == KeyEvent.VK_D && direction != 'L')
                direction = 'R';
            if (e.getKeyCode() == KeyEvent.VK_Q)
                switchState = true;
            lastPress = System.currentTimeMillis();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_Q)
           switchState = false;
    }
    
    public char getDirection() {
        return direction;
    }

    public boolean isSwitchState() {
        return switchState;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}