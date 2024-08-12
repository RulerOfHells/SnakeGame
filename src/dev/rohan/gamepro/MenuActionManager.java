package dev.rohan.gamepro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionManager implements ActionListener {      //Listens for JMenu clicks

    private boolean action = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        action = true;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean act) {
        action = act;
    }
}
