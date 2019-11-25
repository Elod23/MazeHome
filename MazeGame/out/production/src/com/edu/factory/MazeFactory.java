package com.edu.factory;

import com.edu.MazeApp;
import com.edu.entity.*;

public class MazeFactory implements AbstractFactory {

    @Override
    public MapSite create(String modifier, int i) {
        switch (modifier){
            case ("Room"):
                return new Room(i);
            case ("MagicRoom"):
                return new MagicRoom(i);
            case ("Door"):
                return new Door();
            case ("Wall"):
                return new Wall();
        }
        return null;
    }

    @Override
    public Object returnPlayer(MazeApp mazeApp) {
        return new Person(0, mazeApp);
    }
}
