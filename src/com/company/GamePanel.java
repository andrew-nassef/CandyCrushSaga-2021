package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable {
    static int game_index = 0 ;
    static final Dimension SCREEN_SIZE = new Dimension(1000,600);
    Thread gameThread;
    Image image;
    Graphics graphics;
    static Levels [] level = new Levels[5];
    static Menu menu = new Menu();
    private static int currentLevel ;
    private static boolean []levelPassed;
   
        GamePanel() {
            level[0] = new Levels(4,4,15,3);
            level[1] = new Levels(4,7,10,4);
            level[2] = new Levels(6,4,20,5);
            level[3] = new Levels(8,8,30,6);
            levelPassed = new boolean[4];
        this.setFocusable(true);
        this.addMouseListener(new AL());
        this.addMouseMotionListener(new AL());
        this.addKeyListener(menu);
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
        public static void defineCustom(){
            level[4] = new Levels(Menu.entered_number_of_horizontal_candies, Menu.entered_number_of_vertical_candies
          , Menu.entered_number_of_moves, Menu.entered_number_of_candies);
        }
    public static void setCurrentLevel(int currentLevel){
            GamePanel.currentLevel = currentLevel;
    }
    public static int getCurrentLevel(){return GamePanel.currentLevel;}

    public static boolean getLevelPassed(int i) {
        return levelPassed[i];
    }

    public static void setLevelPassed(int x , boolean y) {
        GamePanel.levelPassed[x] =y;
    }

    public void paint(Graphics g) {

        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
                //draw here:
        if (game_index==1){
              level[currentLevel].draw(g);
        }
       else if (game_index==0)
               menu.draw(g);
    }
    public void run(){
    //game loop
        long lastTime =System.nanoTime();
        double amountOfTicks = 60.0;
        double ns= 1000000000/amountOfTicks;
        double delta =0;
        while(true){
            long now =System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime=now;
            if(delta >=1){
                if(game_index >0){
                level[currentLevel].Break_detector();
                level[currentLevel].Move();
                }
                repaint();
                delta--;
            }
        }
    }
    public static class AL extends MouseAdapter implements MouseListener,MouseMotionListener {


        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(GamePanel.game_index==0)
           menu.mouseMoved(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        if (GamePanel.game_index==1)
           level[currentLevel].mouseClicked(e);
        if(GamePanel.game_index==0)
           menu.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
         
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
         
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
