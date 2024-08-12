package dev.rohan.gamepro;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class MouseManager implements MouseInputListener {

    private boolean click = false;
    private int x = 0;
    private int y = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        //nAn
    }

    @Override
    public void mousePressed(MouseEvent e) {
        click = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        click = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //nAn
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //nAn
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //nAn
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //nAn
        x = e.getX();
        y = e.getY();
    }

    public boolean isClick() {
        return click;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}