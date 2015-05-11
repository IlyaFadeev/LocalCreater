package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        LocalCreater frame = new LocalCreater();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(270, 245));
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
