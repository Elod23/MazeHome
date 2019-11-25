package com.edu;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Maze");
        MazeApp app = new MazeApp();
        frame.add(app);
        frame.addKeyListener(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

}
