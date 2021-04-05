package com.company;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    ImageIcon game_icon = new ImageIcon("Textures\\Candy_Crush_Saga_icon.jpg");

    GameFrame(){

    GamePanel panel = new GamePanel();
    this.setIconImage(game_icon.getImage());
    this.add(panel);
    this.setTitle("Candy Crush Saga");
    this.setBackground(Color.CYAN);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    }
}
