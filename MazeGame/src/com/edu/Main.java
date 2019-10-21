package com.edu;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Maze");
        frame.setLayout(new GridLayout(2,1));
        JLabel moveState = new JLabel("Welcome to the mazeGame!");
        moveState.setSize(400, 50);
        frame.add(moveState, BorderLayout.PAGE_START);
        moveState.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(new MazeApp(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}
