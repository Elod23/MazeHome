package com.edu.entity;

import javax.swing.*;
import java.awt.*;

public class Room extends JPanel implements MapSite{
    private boolean winner = false;
    private Color backgroundColor;
    private Integer number;
    private MapSite[] sides = new MapSite[4];

    /**
     *
     * number : number of the room(identifier), number e[0, n), gets its value incrementally from the static context
     */
    public Room(int number) {
        this.number = number;
        if (this.number == 0){
            backgroundColor = Color.BLUE;
        }else{
            backgroundColor = Color.WHITE;
        }
        System.out.println(number + "room ahs color:"  + backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void enter() {

    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getNumber() {
        return number;
    }

    public MapSite[] getSides() {
        return sides;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
