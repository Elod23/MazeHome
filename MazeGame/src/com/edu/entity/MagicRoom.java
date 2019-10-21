package com.edu.entity;

import javax.swing.*;
import java.awt.*;

public class MagicRoom extends Room implements MapSite {


    /**
     * number : number of the room(identifier), number e[0, n], gets its value incrementally from the static context
     *
     * @param number
     */
    public MagicRoom(int number) {
        super(number);
    }

    @Override
    public void enter() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.MAGENTA);
    }
}
