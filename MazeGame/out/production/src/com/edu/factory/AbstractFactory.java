package com.edu.factory;

import com.edu.MazeApp;

public interface AbstractFactory<T> {
    T create(String modifier, int i);
    T returnPlayer(MazeApp mazeApp);
}
