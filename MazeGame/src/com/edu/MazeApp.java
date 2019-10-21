package com.edu;

import com.edu.entity.MagicRoom;
import com.edu.entity.Person;
import com.edu.entity.Room;
import com.edu.factory.AbstractFactory;
import com.edu.factory.MazeFactory;

import javax.swing.*;
import java.awt.*;


public class MazeApp extends JPanel {

    private int xPosition = 0, yPosition = 0;
    private AbstractFactory mazeFactory;
    private Room[] rooms = new Room[16];
    private Person person;
    public MazeApp() {
//        this.setSize(400, 400);
        this.setBackground(Color.WHITE);
        this.mazeFactory = new MazeFactory();
        this.setLayout(new GridLayout(4,4));

        for (int i = 0; i < 16; i++){
            if (i % 2 == 0){
                Room room = (Room) mazeFactory.create("Room", i);
                rooms[i] = room;
                this.add(room);
            }else{
                MagicRoom room = (MagicRoom) mazeFactory.create("MagicRoom", i);
                rooms[i] = room;
                this.add(room);
            }
        }
        person = (Person) mazeFactory.returnPlayer(this);
        this.setVisible(true);
    }

    public AbstractFactory getMazeFactory() {
        return mazeFactory;
    }

    public void setMazeFactory(AbstractFactory mazeFactory) {
        this.mazeFactory = mazeFactory;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
}