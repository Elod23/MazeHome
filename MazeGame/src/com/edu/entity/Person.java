package com.edu.entity;

import com.edu.MazeApp;
import com.edu.direction.Direction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Person extends JPanel implements KeyListener {
    private Integer startingRoomNo, currentRoomNo;
    private boolean reachedTheEnd;
    private MazeApp mazeIn;

    public Person() {};

    public Person(Integer startingRoomNo, MazeApp mazeIn) {
        this.startingRoomNo = startingRoomNo;
        this.currentRoomNo = startingRoomNo;
        this.reachedTheEnd = false;
        this.mazeIn = mazeIn;
    }

    public Integer getStartingRoomNo() {
        return startingRoomNo;
    }

    public void setStartingRoomNo(Integer startingRoomNo) {
        this.startingRoomNo = startingRoomNo;
    }

    public Integer getCurrentRoomNo() {
        return currentRoomNo;
    }

    public void setCurrentRoomNo(Integer currentRoomNo) {
        this.currentRoomNo = currentRoomNo;
    }

    public boolean isReachedTheEnd() {
        return reachedTheEnd;
    }

    public void setReachedTheEnd(boolean reachedTheEnd) {
        this.reachedTheEnd = reachedTheEnd;
    }

    public void move(){
        MapSite currentRoom = mazeIn.getRooms()[currentRoomNo];

    }

    /**
     *
     * @param e not impl
     */

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent event){
        int key = event.getKeyCode();
        Room currentRoom = (Room) mazeIn.getRooms()[currentRoomNo];
        //always having 0 -> UP, 1 -> RIGHT, 2-> DOWN, 3-> LEFT
        MapSite[] walls = currentRoom.getSides();
        if (key ==  KeyEvent.VK_UP){
            inspectMove(walls[0]);
        }else{
            if (key ==  KeyEvent.VK_RIGHT){
                if (walls[1] instanceof Door){
                    inspectMove(walls[1]);
                }
            }else{
                if (key == KeyEvent.VK_DOWN){
                    inspectMove(walls[2]);
                }else{
                    inspectMove(walls[3]);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key ==  KeyEvent.VK_UP){
            System.out.println("Moving up finished");
        }else{
            if (key ==  KeyEvent.VK_RIGHT){
                System.out.println("Moving right finished");
            }else{
                if (key == KeyEvent.VK_DOWN){
                    System.out.println("Moving down finished");
                }else{
                    System.out.println("Moving left finished");
                }
            }
        }
    }

    private void inspectMove(MapSite wall) {
        if (wall instanceof Door){
            Door currentDoor = (Door) wall;
            Room nextRoom = (Room) currentDoor.getRoomTo();
            int nextRoomNo = nextRoom.getNumber();

            setCurrentRoomNo(nextRoomNo);
            if (nextRoom.isWinner()){
                System.out.println("Hurray, you won!");
            }
        }else{
            System.out.println("Crashed into a wall!");
        }
    }
}
