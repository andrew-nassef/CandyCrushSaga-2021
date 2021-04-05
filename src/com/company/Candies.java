package com.company;

import javax.swing.*;
import java.awt.*;

public class Candies {
    private final Image [] candies = new Image[6];
    private final Image [] candyAnimation = new Image[6];
    private final Image [] explodingCandies = new Image[6];
    private final Image [] stripedCandiesV = new Image[6];
    private final Image [] stripedCandiesH = new Image[6];
    private Image candyShape;
    private int Px,Py;
    private int VelX=0,VelY=0;
    private int candyType;
    private boolean broke = false;
    private int candiesPower;
    Candies(){
        candies[0] = new ImageIcon("Textures\\Candies 50x50\\blue.png").getImage();
        candies[1] = new ImageIcon("Textures\\Candies 50x50\\green.png").getImage();
        candies[2] = new ImageIcon("Textures\\Candies 50x50\\red.png").getImage();
        candies[3] = new ImageIcon("Textures\\Candies 50x50\\orange.png").getImage();
        candies[4] = new ImageIcon("Textures\\Candies 50x50\\violet.png").getImage();
        candies[5] = new ImageIcon("Textures\\Candies 50x50\\yellow.png").getImage();
        candyAnimation[0] = new ImageIcon("Textures\\CandyBreaking\\blue.gif").getImage();
        candyAnimation[1] = new ImageIcon("Textures\\CandyBreaking\\green.gif").getImage();
        candyAnimation[2] = new ImageIcon("Textures\\CandyBreaking\\red.gif").getImage();
        candyAnimation[3] = new ImageIcon("Textures\\CandyBreaking\\orange.gif").getImage();
        candyAnimation[4] = new ImageIcon("Textures\\CandyBreaking\\purple.gif").getImage();
        candyAnimation[5] = new ImageIcon("Textures\\CandyBreaking\\yellow.gif").getImage();
        explodingCandies[0] = new ImageIcon("Textures\\Special_candies\\blue.png").getImage();
        explodingCandies[1] = new ImageIcon("Textures\\Special_candies\\green.png").getImage();
        explodingCandies[2] = new ImageIcon("Textures\\Special_candies\\red.png").getImage();
        explodingCandies[3] = new ImageIcon("Textures\\Special_candies\\orange.png").getImage();
        explodingCandies[4] = new ImageIcon("Textures\\Special_candies\\violet.png").getImage();
        explodingCandies[5] = new ImageIcon("Textures\\Special_candies\\yellow.png").getImage();
        stripedCandiesV[0] = new ImageIcon("Textures\\Special_candies\\Striped_blue_v.png").getImage();
        stripedCandiesV[1] = new ImageIcon("Textures\\Special_candies\\Striped_green_v.png").getImage();
        stripedCandiesV[2] = new ImageIcon("Textures\\Special_candies\\Striped_red_v.png").getImage();
        stripedCandiesV[3] = new ImageIcon("Textures\\Special_candies\\Striped_orange_v.png").getImage();
        stripedCandiesV[4] = new ImageIcon("Textures\\Special_candies\\Striped_purple_v.png").getImage();
        stripedCandiesV[5] = new ImageIcon("Textures\\Special_candies\\Striped_yellow_v.png").getImage();
        stripedCandiesH[0] = new ImageIcon("Textures\\Special_candies\\Striped_blue_h.png").getImage();
        stripedCandiesH[1] = new ImageIcon("Textures\\Special_candies\\Striped_green_h.png").getImage();
        stripedCandiesH[2] = new ImageIcon("Textures\\Special_candies\\Striped_red_h.png").getImage();
        stripedCandiesH[3] = new ImageIcon("Textures\\Special_candies\\Striped_orange_h.png").getImage();
        stripedCandiesH[4] = new ImageIcon("Textures\\Special_candies\\Striped_purple_h.png").getImage();
        stripedCandiesH[5] = new ImageIcon("Textures\\Special_candies\\Striped_yellow_h.png").getImage();

    }
    public void setShape(int x){
        candyShape = candies[x];
        candyType = x;
    }
    public int getCandyType(){
        return candyType;
    }
    public void setCandyAnimation(int x){
        candyShape = candyAnimation[x];
        candyType = x;
    }
    public void setSpecialCandy(int x){
        candyShape = explodingCandies[x];
        candyType = x;
    }
    public void setStripedCandiesV(int x){
        candyShape = stripedCandiesV[x];
        candyType = x;
    }
    public void setStripedCandiesH(int x){
        candyShape = stripedCandiesH[x];
        candyType = x;
    }
    public Image getCandyAnimation(int x){
        return candyAnimation[x];
    }

    public int getPx() {
        return Px;
    }

    public void setPx(int px) {
        Px = px;
    }

    public int getPy() {
        return Py;
    }

    public void setPy(int py) {
        Py = py;
    }

    public int getVelX() {
        return VelX;
    }

    public void setVelX(int velX) {
        VelX = velX;
    }

    public int getVelY() {
        return VelY;
    }

    public void setVelY(int velY) {
        VelY = velY;
    }
    
    public Image getCandyShape() {
        return candyShape;
    }

    public boolean isBroke() {
        return broke;
    }

    public void setBroke(boolean broke) {
        this.broke = broke;
    }

    public int getCandiesPower() {
        return candiesPower;
    }

    public void setCandiesPower(int candiesPower) {
        this.candiesPower = candiesPower;
    }
}
