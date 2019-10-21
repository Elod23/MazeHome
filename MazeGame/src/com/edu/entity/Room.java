package com.edu.entity;

import javax.swing.*;
import java.awt.*;

public class Room extends JPanel implements MapSite{
    private boolean winner;

    private Integer number;
    private MapSite[] sides;

    /**
     *
     * number : number of the room(identifier), number e[0, n], gets its value incrementally from the static context
     */
    public Room(int number) {
        this.number = number;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void enter() {

    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public MapSite[] getSides() {
        return sides;
    }

    public void setSides(MapSite[] sides) {
        this.sides = sides;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
