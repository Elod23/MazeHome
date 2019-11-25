package com.edu.entity;

import com.edu.MazeApp;

import javax.swing.*;

public class Person extends JLabel {
    private Integer startingRoomNo, currentRoomNo;
    private boolean reachedTheEnd;
    private MazeApp mazeIn;
    private boolean hasKey;



    public Person(Integer startingRoomNo, MazeApp mazeIn) {
        this.startingRoomNo = startingRoomNo;
        this.currentRoomNo = startingRoomNo;
        this.reachedTheEnd = false;
        this.mazeIn = mazeIn;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void setKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public Integer getCurrentRoomNo() {
        return currentRoomNo;
    }

    public void setCurrentRoomNo(Integer currentRoomNo) {
        this.currentRoomNo = currentRoomNo;
    }

    public void setReachedTheEnd(boolean reachedTheEnd) {
        this.reachedTheEnd = reachedTheEnd;
    }

}
