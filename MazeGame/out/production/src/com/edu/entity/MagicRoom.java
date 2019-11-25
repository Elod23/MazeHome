package com.edu.entity;

import java.awt.*;

public class MagicRoom extends Room implements MapSite {

    /**
     * number : number of the room(identifier), number e[0, n], gets its value incrementally from the static context
     *
     * @param number
     */
    public MagicRoom(int number) {
        super(number);
        if (number == 0){
             setBackgroundColor(Color.BLUE);
        }else{
            setBackgroundColor(Color.MAGENTA);
        }
        System.out.println(number + " amgic number has color:" + getBackgroundColor());
    }

    @Override
    public void enter() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(getBackgroundColor());
    }

    @Override
    public Color getBackgroundColor() {
        return super.getBackgroundColor();
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        super.setBackgroundColor(backgroundColor);
    }
}
