package com.edu;

import com.edu.entity.*;
import com.edu.factory.AbstractFactory;
import com.edu.factory.MazeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MazeApp extends JPanel implements KeyListener {
    //Functional fields
    private AbstractFactory mazeFactory;
    private Room[] rooms = new Room[16];
    private Person person;

    //View fields
    private String message;
    private JLabel moveState;

    MazeApp() {
        this.setLayout(new GridLayout(2, 1));
        message = "Welcome to the mazeGame!";
        moveState = new JLabel(message);
        moveState.setSize(400, 50);

        this.add(moveState, BorderLayout.PAGE_START);
        moveState.setHorizontalAlignment(SwingConstants.CENTER);

        this.setBackground(Color.WHITE);
        this.mazeFactory = new MazeFactory();
        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) {
                Room room = (Room) mazeFactory.create("Room", i);
                rooms[i] = room;
                mazePanel.add(room);
            } else {
                MagicRoom room = (MagicRoom) mazeFactory.create("MagicRoom", i);
                rooms[i] = room;
                mazePanel.add(room);
            }
            if (i == 15) {
                rooms[i].setWinner(true);
            }
        }


        message += "You need to reach the room number 15 to win";
        moveState.setText(message);

        //Registering door No.1
        Door firstDoor = (Door) mazeFactory.create("Door", 1);
        firstDoor.setRoomFrom(rooms[0]);
        firstDoor.setRoomTo(rooms[4]);
        defineSides(rooms[0], "DOWN", firstDoor);
        defineSides(rooms[4], "UP", firstDoor);

        //Registering door No.2
        Door secondDoor = (Door) mazeFactory.create("Door", 2);
        secondDoor.setRoomFrom(rooms[4]);
        secondDoor.setRoomTo(rooms[8]);
        defineSides(rooms[4], "DOWN", secondDoor);
        defineSides(rooms[8], "UP", secondDoor);


        //Registering door No.3
        Door thirdDoor = (Door) mazeFactory.create("Door", 3);
        thirdDoor.setRoomFrom(rooms[8]);
        thirdDoor.setRoomTo(rooms[12]);
        defineSides(rooms[8], "DOWN", thirdDoor);
        defineSides(rooms[12], "UP", thirdDoor);

        //Registering door No.4
        Door fourthDoor = (Door) mazeFactory.create("Door", 4);
        fourthDoor.setRoomFrom(rooms[12]);
        fourthDoor.setRoomTo(rooms[13]);
        defineSides(rooms[12], "RIGHT", fourthDoor);
        defineSides(rooms[13], "LEFT", fourthDoor);

        //Registering door No.5
        Door fifthDoor = (Door) mazeFactory.create("Door", 4);
        fifthDoor.setRoomFrom(rooms[13]);
        fifthDoor.setRoomTo(rooms[14]);
        defineSides(rooms[13], "RIGHT", fifthDoor);
        defineSides(rooms[14], "LEFT", fifthDoor);

        //Registering door No.6
        Door sixthDoor = (Door) mazeFactory.create("Door", 4);
        sixthDoor.setRoomFrom(rooms[14]);
        sixthDoor.setRoomTo(rooms[15]);
        defineSides(rooms[14], "RIGHT", sixthDoor);
        defineSides(rooms[15], "LEFT", sixthDoor);

        person = (Person) mazeFactory.returnPlayer(this);
        this.add(mazePanel, BorderLayout.CENTER);
        this.setSize(700, 400);
        this.setVisible(true);
    }


    private Room[] getRooms() {
        return rooms;
    }

    /**
     * @param e not impl
     */

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        Room currentRoom = this.getRooms()[person.getCurrentRoomNo()];
        //always having 0 -> UP, 1 -> RIGHT, 2-> DOWN, 3-> LEFT
        MapSite[] walls = currentRoom.getSides();
        if (key == KeyEvent.VK_UP) {
            inspectMove(walls[0]);
        } else {
            if (key == KeyEvent.VK_RIGHT) {
                inspectMove(walls[1]);
            } else {
                if (key == KeyEvent.VK_DOWN) {
                    inspectMove(walls[2]);
                } else {
                    if (key == KeyEvent.VK_LEFT) {
                        inspectMove(walls[3]);
                    } else {
                        if (key == KeyEvent.VK_SPACE) {
                            message = "You have the magic key, you can move on!";
                            moveState.setText(message);
                            person.setKey(true);
                        }
                    }
                }
            }
        }
        moveState.setText(message);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
        } else {
            if (key == KeyEvent.VK_RIGHT) {
            } else {
                if (key == KeyEvent.VK_DOWN) {
                } else {
                }
            }
        }
    }

    private void inspectMove(MapSite wall) {
        if (wall instanceof Door) {
            Door door = (Door) wall;
            if (rooms[door.getRoomTo().getNumber()] instanceof MagicRoom) {
                if (person.hasKey()) {
                    checkIfCanPass(wall);
                    person.setKey(false);
                } else {
                    message = "You need to hit Space to get the magic key and pass the door!";
                    moveState.setText(message);
                }
            }else{
                checkIfCanPass(wall);
            }
        } else {
            message = "Crashed into a wall!";
            moveState.setText(message);
        }
    }

    private void checkIfCanPass(MapSite wall) {
        if (wall instanceof Door) {
            Door door = (Door) wall;
            door.enter();
            //if we go back to the < number room
            if (door.getRoomTo().getNumber().equals(person.getCurrentRoomNo())) {
                person.setCurrentRoomNo(door.getRoomFrom().getNumber());
                door.getRoomFrom().setBackgroundColor(Color.BLUE);
                resetTileColor(door.getRoomTo());
            } else { //if we step forward
                person.setCurrentRoomNo(door.getRoomTo().getNumber());
                resetTileColor(door.getRoomFrom());
                door.getRoomTo().setBackgroundColor(Color.BLUE);
            }
            message = "You moved on to room number:" + person.getCurrentRoomNo();
            moveState.setText(message);

            if (door.getRoomTo().isWinner()) {
                person.setReachedTheEnd(true);
                message = "Hurray, you won!";
                moveState.setText(message);
            }
        } else {
            message = "Crashed into a wall!";
            moveState.setText(message);
        }
        this.revalidate();
    }

    private void resetTileColor(Room roomFrom) {
        if (roomFrom instanceof MagicRoom) {
            roomFrom.setBackgroundColor(Color.MAGENTA);
        } else {
            roomFrom.setBackgroundColor(Color.WHITE);
        }
    }

    private void defineSides(Room room, String doorLocation, Door door) {
        MapSite[] sides = room.getSides();
        switch (doorLocation) {
            case ("UP"):
                sides[0] = door;
                if (!(sides[1] instanceof Door)) {
                    sides[1] = (MapSite) mazeFactory.create("Wall", 1);
                }
                if (!(sides[2] instanceof Door)) {
                    sides[2] = (MapSite) mazeFactory.create("Wall", 1);
                }
                if (!(sides[3] instanceof Door)) {
                    sides[3] = (MapSite) mazeFactory.create("Wall", 1);
                }
                break;
            case ("RIGHT"):
                if (!(sides[0] instanceof Door)) {
                    sides[0] = (MapSite) mazeFactory.create("Wall", 1);
                }
                sides[1] = door;
                if (!(sides[2] instanceof Door)) {
                    sides[2] = (MapSite) mazeFactory.create("Wall", 1);
                }
                if (!(sides[3] instanceof Door)) {
                    sides[3] = (MapSite) mazeFactory.create("Wall", 1);
                }
                break;
            case ("DOWN"):
                if (!(sides[0] instanceof Door)) {
                    sides[0] = (MapSite) mazeFactory.create("Wall", 1);
                }
                if (!(sides[1] instanceof Door)) {
                    sides[1] = (MapSite) mazeFactory.create("Wall", 1);
                }
                sides[2] = door;
                if (!(sides[3] instanceof Door)) {
                    sides[3] = (MapSite) mazeFactory.create("Wall", 1);
                }
                break;
            case ("LEFT"):
                if (!(sides[0] instanceof Door)) {
                    sides[0] = (MapSite) mazeFactory.create("Wall", 1);
                }
                if (!(sides[1] instanceof Door)) {
                    sides[1] = (MapSite) mazeFactory.create("Wall", 1);
                }
                if (!(sides[2] instanceof Door)) {
                    sides[2] = (MapSite) mazeFactory.create("Wall", 1);
                }
                sides[3] = door;
                break;
        }
    }
}