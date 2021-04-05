package com.company;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;
public class Levels {
    private final Menu menu ;
    private final Players score_obj =new Players();
    private final Board board;
    private final int candies_width, candies_height;
    private final Candies[][] candies;
    private final Candies[][] Replacement_candies;
    private final Random random = new Random();
    private int ClickCounter = 0;
    private int FirstCandyCol, FirstCandyRow, SecondCandyRow, SecondCandyCol;
    private final Candies switchedCandy = new Candies();
    private boolean is_settings_open = false;
    private final Image settings_icon = new ImageIcon("Textures\\settings_button.png").getImage();
    private final Image settings_menu = new ImageIcon("Textures\\settings_menu.png").getImage();
    private final Image game_background = new ImageIcon("Textures\\main_game_background.png").getImage();
    private final Image close_button = new ImageIcon("Textures\\close_button.png").getImage();
    private final Image next_icon = new ImageIcon ("Textures\\exit_button.png").getImage();
    private final Image out_of_moves_image = new ImageIcon ("Textures\\out_of_moves.jpg").getImage();
    private final Image next_icon_big = new ImageIcon ("Textures\\exit_button _large.png").getImage();
    private final Menu menu_sound_images = new Menu();
    private int level_game_index = 0;
    private int clicked_x, clicked_y;
    private boolean isHorizontal;
    private int direction;
    private int CurrentPos;
    private boolean swapping = false;
    private boolean Non_matching_swap = false;
    private Font Candy_font;
    private int Score;
    private int Moves_count;
    private final int initial_moves_count;
    private int game_ended_index = -5 ;
    private int game_out_of_moves_index = -6;
    private final int AmountOfCandies; // max 6
    private final int targetScore;
    private final Image endGame = new ImageIcon("Textures\\level_passed_background.png").getImage();
    private final Image Dispenser = new ImageIcon("Textures\\Dispenser.png").getImage();
    Levels(int candies_width, int candies_height, int moves_count, int AmountOfCandies) {
        this.Moves_count = moves_count;
        initial_moves_count = Moves_count ;
        this.AmountOfCandies = AmountOfCandies;
        this.candies_width = candies_width;
        this.candies_height = candies_height;
        int heightDiffculty = candies_height * 50;
        int widthDiffculty = candies_width * 50;
        targetScore = Moves_count*50 +heightDiffculty+widthDiffculty;
        candies = new Candies[candies_width][candies_height];
        Replacement_candies = new Candies[candies_width][candies_height];
        board = new Board(candies_height, candies_width);
        menu = new Menu();
        try {
            Candy_font = Font.createFont(Font.TRUETYPE_FONT, new File("Textures\\CANDY.TTF")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Textures\\CANDY.TTF")));
        } catch (IOException | FontFormatException exp) {

        }
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                candies[i][j] = new Candies();
                candies[i][j].setPx(i * 50 + 325);
                candies[i][j].setPy(j * 50 + 150);
                candies[i][j].setShape(random.nextInt(AmountOfCandies));
                // ----------------
                Replacement_candies[i][j] = new Candies();
                Replacement_candies[i][j].setPx(i * 50 + 325);
                Replacement_candies[i][j].setPy(100);
                Replacement_candies[i][j].setShape(random.nextInt(AmountOfCandies));
            }
        }
        do {
            checker();
        } while (match_checker());
        ResetCandies();

    }
    public void checker() {

        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                if (j < candies_height-2 && candies[i][j].getCandyType() == candies[i][j + 1].getCandyType() && candies[i][j].getCandyType() == candies[i][j + 2].getCandyType()) {
                    do {
                        candies[i][j].setShape(random.nextInt(AmountOfCandies));
                    } while (candies[i][j].getCandyType() == candies[i][j +1].getCandyType());

                } else if (i < candies_width-2 && candies[i][j].getCandyType() == candies[i + 1][j].getCandyType() && candies[i][j].getCandyType() == candies[i + 2][j].getCandyType()) {
                    do {
                        candies[i][j].setShape(random.nextInt(AmountOfCandies));
                    } while (candies[i][j].getCandyType() == candies[i +1][j].getCandyType());

                }
            }
        }
    }
    public boolean match_checker() {
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {

                if (j < candies_height - 2 && candies[i][j].getCandyType() == candies[i][j + 1].getCandyType() && candies[i][j].getCandyType() == candies[i][j + 2].getCandyType()) {
                    return true;
                } else if (i < candies_width - 2 && candies[i][j].getCandyType() == candies[i + 1][j].getCandyType() && candies[i][j].getCandyType() == candies[i + 2][j].getCandyType()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        if (level_game_index == 0) {
            g.drawImage(game_background, 0, 0, null);
            board.draw(g);/*
            g.drawImage(winScreen,0,0,null);*/
            g.setFont(Candy_font);
            g.setColor(Color.WHITE);
            g.drawString("Target:" + targetScore,15,150);
            g.drawString("" + Moves_count, 95, 70);
            g.drawString("Score:" + Score, 15, 220);

            for (int i = 0; i < candies_width; i++) {
                for (int j = 0; j < candies_height; j++) {
                    g.drawImage(candies[i][j].getCandyShape(), candies[i][j].getPx(), candies[i][j].getPy(), null);
                    g.drawImage(Replacement_candies[i][j].getCandyShape(), Replacement_candies[i][j].getPx(), Replacement_candies[i][j].getPy(), null);
                }
                g.drawImage(Dispenser, i*50+325, 100+5, null);
            }
            g.drawImage(settings_icon, 5, 545, null);
        } else if (level_game_index == 1) {

            g.drawImage(game_background, 0, 0, null);
            g.setFont(Candy_font);
            g.setColor(Color.WHITE);
            g.drawString("" + Moves_count, 95, 70);
            g.drawString("" + Score, 70, 150);
            if (is_settings_open) {
                g.drawImage(settings_menu, 350, 120, null);
                if (Menu.getBackgroundMusic().isMusicPlaying())
                    g.drawImage(menu_sound_images.music_on_icon, 520, 220, null);
                else
                    g.drawImage(menu_sound_images.music_off_icon, 520, 220, null);
            }
            g.drawImage(close_button, 700, 155, null);
        }
        else if( level_game_index==game_ended_index ){
            g.drawImage(endGame,0,0,null);
            g.drawImage(next_icon , 470,150,null);
        }
        else if( level_game_index==game_out_of_moves_index ){
            g.drawImage(out_of_moves_image,0,0,null);
            g.drawImage(next_icon_big, 10,490,null);
        }

    }

    public void Switch(Candies firstCandy, Candies secondCandy) {

        switchedCandy.setPx(firstCandy.getPx());
        switchedCandy.setPy(firstCandy.getPy());
        switchedCandy.setShape(firstCandy.getCandyType());
        switchedCandy.setCandiesPower(firstCandy.getCandiesPower());
        firstCandy.setPx(secondCandy.getPx());
        firstCandy.setPy(secondCandy.getPy());
        firstCandy.setShape(secondCandy.getCandyType());
        firstCandy.setCandiesPower(secondCandy.getCandiesPower());
        secondCandy.setPx(switchedCandy.getPx());
        secondCandy.setPy(switchedCandy.getPy());
        secondCandy.setShape(switchedCandy.getCandyType());
        secondCandy.setCandiesPower(switchedCandy.getCandiesPower());
        if(firstCandy.getCandiesPower()==1){
            firstCandy.setSpecialCandy(firstCandy.getCandyType());
        }
        else if(firstCandy.getCandiesPower() ==2){
            firstCandy.setStripedCandiesV(firstCandy.getCandyType());
        }
        else if(firstCandy.getCandiesPower() ==3){
            firstCandy.setStripedCandiesH(firstCandy.getCandyType());
        }
        if(secondCandy.getCandiesPower() == 1){
            secondCandy.setSpecialCandy(secondCandy.getCandyType());
        }
        else if(secondCandy.getCandiesPower() ==2){
            secondCandy.setStripedCandiesV(secondCandy.getCandyType());
        }
        else if(secondCandy.getCandiesPower() ==3){
            secondCandy.setStripedCandiesH(secondCandy.getCandyType());
        }
    }
    public boolean noMatchingCandies(){
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                if (j < candies_height - 1 && candies[i][j].getCandyType() == candies[i][j + 1].getCandyType()
                        &&(
                        (i<candies_width-1 && j< candies_height-2 && candies[i+1][j+2].getCandyType() == candies[i][j].getCandyType())
                                || (i>0 && j< candies_height-2 && candies[i-1][j+2].getCandyType() == candies[i][j].getCandyType())
                                || (j<candies_height-3 && candies[i][j+3].getCandyType() == candies[i][j].getCandyType())
                                || (j> 0 && i>0 && candies[i-1][j-1].getCandyType() == candies[i][j].getCandyType())
                                || (j> 0 && i<candies_width-1 && candies[i+1][j-1].getCandyType() == candies[i][j].getCandyType())
                                || (j>1 && candies[i][j-2].getCandyType() == candies[i][j].getCandyType()))) {
                    return true;
                } else if (i < candies_width - 1 && candies[i][j].getCandyType() == candies[i+1][j].getCandyType()
                        &&(
                        (j<candies_height-1 && i< candies_width-2 && candies[i+2][j+1].getCandyType() == candies[i][j].getCandyType())
                                || (j>0 && i< candies_width-2 && candies[i+2][j-1].getCandyType() == candies[i][j].getCandyType())
                                || (i<candies_width-3 && candies[i+3][j].getCandyType() == candies[i][j].getCandyType())
                                || (i> 0 && j>0 && candies[i-1][j-1].getCandyType() == candies[i][j].getCandyType())
                                || (i> 0 && j<candies_height-1 && candies[i-1][j+1].getCandyType() == candies[i][j].getCandyType())
                                || (i>1 && candies[i-2][j].getCandyType() == candies[i][j].getCandyType()))) {
                    return true;
                }
                else if(j < candies_height - 2 && candies[i][j].getCandyType() == candies[i][j + 2].getCandyType() &&
                        ((i>0 && candies[i-1][j+1].getCandyType() == candies[i][j].getCandyType())
                                ||(i<candies_width-1 && candies[i+1][j+1].getCandyType() == candies[i][j].getCandyType())
                        )){
                    return true;
                }
                else if(i < candies_width - 2 && candies[i][j].getCandyType() == candies[i+2][j].getCandyType() &&
                        ((j>0 && candies[i+1][j-1].getCandyType() == candies[i][j].getCandyType())
                                ||(j<candies_height-1 && candies[i+1][j+1].getCandyType() == candies[i][j].getCandyType())
                        )){
                    return true;
                }
            }
        }
        return false;
    }

    public void ResetCandies(){
        while(!noMatchingCandies()){
            for (int i = 0; i < candies_width; i++) {
                for (int j = 0; j < candies_height; j++) {
                    candies[i][j].setShape(random.nextInt(AmountOfCandies));
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (GamePanel.game_index == 1) {
            if (level_game_index == 0) {
                clicked_x = e.getX();
                clicked_y = e.getY();
                Detection();
                if (ClickCounter == 1) {/*
                    System.out.println(FirstCandyRow +" " + FirstCandyCol);*/
                } else if (ClickCounter == 2) {/*``
                    System.out.println(SecondCandyRow + " " + SecondCandyCol);*/
                    ClickCounter = 0;
                }
                if (e.getX() > 5 && e.getX() < 55 && e.getY() > 545 && e.getY() <600) {

                    level_game_index = 1;
                    if (is_settings_open)
                        is_settings_open = false;
                    else if (!is_settings_open)
                        is_settings_open = true;

                }

            } else if (level_game_index == 1) {
                if (e.getX() > 700 && e.getX() < 755 && e.getY() > 155 && e.getY() < 205) {
                    level_game_index = 0;
                    if (is_settings_open)
                        is_settings_open = false;
                    else if (!is_settings_open)
                        is_settings_open = true;

                } else if (e.getX() > 520 && e.getX() < 600 && e.getY() > 220 && e.getY() < 290) {
                    if (Menu.getBackgroundMusic().isMusicPlaying()) {
                        Menu.getBackgroundMusic().setMusicPlaying(false);
                        Menu.getBackgroundMusic().pause();}
                    else if (!Menu.getBackgroundMusic().isMusicPlaying()){
                        Menu.getBackgroundMusic().setMusicPlaying(true);
                        Menu.getBackgroundMusic().resume();
                    }
                } else if (e.getX() > 450 && e.getX() < 650 && e.getY() > 320 && e.getY() < 370) {

                    GamePanel.game_index = 0;
                    is_settings_open = false ;
                    level_game_index = 0;
                    Moves_count=initial_moves_count;
                    Score=0;

                }
            }
            else if (level_game_index == game_ended_index){
                if (e.getX() > 470 && e.getX() < 550 && e.getY() > 150 && e.getY() <230){
                    level_game_index = 0;
                    GamePanel.game_index = 0;
                    menu.menu_game_index  = menu.level_number_select_menu ;
                    Score = 0 ;
                    Moves_count = initial_moves_count ;

                }

            }
            else if (level_game_index == game_out_of_moves_index){
                if (e.getX() > 15 && e.getX() < 130 && e.getY() > 490 && e.getY() <600){
                    level_game_index = 0;
                    GamePanel.game_index = 0;
                    menu.menu_game_index  = menu.level_number_select_menu ;
                    Score = 0 ;
                    Moves_count = initial_moves_count ;

                }

            }

        }
    }

    public void Detection() {
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                if (clicked_x >= candies[i][j].getPx() && clicked_x <= candies[i][j].getPx() + 50
                        && clicked_y >= candies[i][j].getPy() && clicked_y <= candies[i][j].getPy() + 50
                        && ClickCounter == 0
                ) {
                    FirstCandyRow = i;
                    FirstCandyCol = j;
                    ClickCounter += 1;
                    System.out.println(candies[i][j].getCandiesPower());
                } else if (clicked_x >= candies[i][j].getPx() && clicked_x <= candies[i][j].getPx() + 50
                        && clicked_y >= candies[i][j].getPy() && clicked_y <= candies[i][j].getPy() + 50) {
                    ClickCounter += 1;
                    SecondCandyRow = i;
                    SecondCandyCol = j;
                    CurrentPos = candies[i][j].getPx();
                }
            }
        }
        if (ClickCounter == 2) {
            //left
            if (SecondCandyRow + 1 == FirstCandyRow && SecondCandyCol == FirstCandyCol) {
                System.out.println("left");
                CurrentPos = candies[SecondCandyRow][SecondCandyCol].getPx();
                isHorizontal = true;
                direction = -1;
                if (match_confirm()) {
                    swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                }
                else
                    Non_Matching_swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
            }
            //right
            else if (SecondCandyRow - 1 == FirstCandyRow && SecondCandyCol == FirstCandyCol) {
                CurrentPos = candies[SecondCandyRow][SecondCandyCol].getPx();
                isHorizontal = true;
                direction = 1;
                if (match_confirm()) {
                    swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                    if(candies[FirstCandyRow][FirstCandyCol].isBroke()){
                        candies[FirstCandyRow][FirstCandyCol].setCandiesPower(3);
                    }else if(candies[SecondCandyRow][SecondCandyCol].isBroke()){
                        candies[SecondCandyRow][SecondCandyCol].setCandiesPower(3);
                    }
                }
                else
                    Non_Matching_swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                System.out.println("matchright");
            }
            //down
            else if (SecondCandyRow == FirstCandyRow && SecondCandyCol - 1 == FirstCandyCol) {
                isHorizontal = false;
                direction = 1;
                CurrentPos = candies[SecondCandyRow][SecondCandyCol].getPy();
                if (match_confirm()) {
                    swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                }
                else
                    Non_Matching_swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                System.out.println("matchdown");
            }
            //up
            else if (SecondCandyRow == FirstCandyRow && SecondCandyCol + 1 == FirstCandyCol) {
                isHorizontal = false;
                direction = -1;
                CurrentPos = candies[SecondCandyRow][SecondCandyCol].getPy();
                if (match_confirm()) {
                    swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                }
                else
                    Non_Matching_swap(candies[FirstCandyRow][FirstCandyCol], candies[SecondCandyRow][SecondCandyCol]);
                System.out.println("matchup");
            }
        }
    }


    public void Break_detector() {
        //3 match detector
        drop_down();
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                if (!swapping && !moving() && j < candies_height - 2 && candies[i][j].getCandyType() == candies[i][j + 1].getCandyType() &&
                        candies[i][j].getCandyType() == candies[i][j + 2].getCandyType()&&
                        !candies[i][j].isBroke() && !candies[i][j+1].isBroke() && !candies[i][j+2].isBroke()) {
                    candies[i][j].setBroke(true);
                    candies[i][j + 1].setBroke(true);
                    candies[i][j + 2].setBroke(true);
                    candies[i][j].setCandyAnimation(candies[i][j].getCandyType());
                    candies[i][j + 1].setCandyAnimation(candies[i][j].getCandyType());
                    candies[i][j + 2].setCandyAnimation(candies[i][j].getCandyType());
                    for(int x=j;x<=j+2;x++){
                        if(i<candies_width-2 && candies[i][x].getCandyType() == candies[i+1][x].getCandyType() && candies[i][x].getCandyType() == candies[i+2][x].getCandyType()){

                            candies[i+1][x].setBroke(true);
                            candies[i+1][x].setCandyAnimation(candies[i+1][x].getCandyType());
                            candies[i+2][x].setBroke(true);
                            candies[i+2][x].setCandyAnimation(candies[i+2][x].getCandyType());
                            candies[i][x].setCandiesPower(1);
                            candies[i][x].setSpecialCandy(candies[i][x].getCandyType());
                            candies[i][x].setBroke(false);
                            System.out.println("i am in");
                        }
                        else if(i >=2 && candies[i][x].getCandyType() == candies[i-1][x].getCandyType() && candies[i][x].getCandyType() == candies[i-2][x].getCandyType()){
                            candies[i-1][x].setBroke(true);
                            candies[i-1][x].setCandyAnimation(candies[i-1][x].getCandyType());
                            candies[i-2][x].setBroke(true);
                            candies[i-2][x].setCandyAnimation(candies[i-2][x].getCandyType());
                            candies[i][x].setCandiesPower(1);
                            candies[i][x].setSpecialCandy(candies[i][x].getCandyType());
                            candies[i][x].setBroke(false);
                            System.out.println("i am in");
                        }
                    }

                    //stripedCandyDetection
                    if(j < candies_height - 3 && candies[i][j+3].getCandyType() == candies[i][j].getCandyType()){
                        System.out.println("can");
                        candies[i][j+3].setBroke(true);
                        candies[i][j+3].setCandyAnimation(candies[i][j+3].getCandyType());
                        for(int x=j;x<=j+3;x++){
                            if(i==FirstCandyRow && FirstCandyRow ==x || i==SecondCandyRow && SecondCandyCol ==x){
                                candies[i][x].setBroke(false);
                                if(isHorizontal) {
                                    candies[i][x].setStripedCandiesH(candies[i][x].getCandyType());
                                    candies[i][x].setCandiesPower(3);
                                }
                                else{
                                    candies[i][x].setStripedCandiesV(candies[i][x].getCandyType());
                                    candies[i][x].setCandiesPower(2);
                                }
                            }
                        }
                    }
                    //EndStripedCandyDetection

                } else if (!swapping && !moving() && i < candies_width - 2 && candies[i][j].getCandyType() == candies[i + 1][j].getCandyType() &&
                        candies[i][j].getCandyType() == candies[i + 2][j].getCandyType() &&
                        !candies[i][j].isBroke() && !candies[i+1][j].isBroke() && !candies[i+2][j].isBroke() ) {
                    //3 Candies only Detected
                    candies[i][j].setBroke(true);
                    candies[i + 1][j].setBroke(true);
                    candies[i + 2][j].setBroke(true);
                        candies[i][j].setCandyAnimation(candies[i][j].getCandyType());
                        candies[i + 1][j].setCandyAnimation(candies[i][j].getCandyType());
                        candies[i + 2][j].setCandyAnimation(candies[i][j].getCandyType());
                        //end 3 candies detected
                    //exploding candies detection
                        for(int x=i;x<=i+2;x++){
                        if(j<candies_height-2 && candies[x][j].getCandyType() == candies[x][j+1].getCandyType() && candies[x][j].getCandyType() == candies[x][j+2].getCandyType()){
                            candies[x][j+1].setBroke(true);
                            candies[x][j+1].setCandyAnimation(candies[x][j+1].getCandyType());
                            candies[x][j+2].setBroke(true);
                            candies[x][j+2].setCandyAnimation(candies[x][j+2].getCandyType());
                            candies[x][j].setCandiesPower(1);
                            candies[x][j].setSpecialCandy(candies[x][j].getCandyType());
                            candies[x][j].setBroke(false);
                            System.out.println("i am in");
                        }
                        else if(j >=2 && candies[x][j].getCandyType() == candies[x][j-1].getCandyType() && candies[x][j].getCandyType() == candies[x][j-2].getCandyType()){
                            candies[x][j-1].setBroke(true);
                            candies[x][j-1].setCandyAnimation(candies[x][j-1].getCandyType());
                            candies[x][j-2].setBroke(true);
                            candies[x][j-2].setCandyAnimation(candies[x][j-2].getCandyType());
                            candies[x][j].setCandiesPower(1);
                            candies[x][j].setSpecialCandy(candies[x][j].getCandyType());
                            candies[x][j].setBroke(false);
                            System.out.println("i am in");
                        }
                    }
                        //End Exploding candies detection
                        //stripedCandyDetection
                        if(i < candies_width - 3 && candies[i+3][j].getCandyType() == candies[i][j].getCandyType()){
                            candies[i+3][j].setBroke(true);
                            candies[i+3][j].setCandyAnimation(candies[i+3][j].getCandyType());
                            for(int x=i;x<=i+3;x++){
                                if(x==FirstCandyRow && FirstCandyRow ==j || x==SecondCandyRow && SecondCandyCol ==j){
                            candies[x][j].setBroke(false);
                            if(isHorizontal) {
                                candies[x][j].setStripedCandiesH(candies[i + 1][j].getCandyType());
                                candies[x][j].setCandiesPower(3);
                            }
                            else{
                                candies[x][j].setStripedCandiesV(candies[x][j].getCandyType());
                                candies[x][j].setCandiesPower(2);
                            }
                                }
                            }
                        }
                        //EndStripedCandyDetection
                    }
                if (!moving() && !swapping)
                    candies[i][j].getCandyAnimation(candies[i][j].getCandyType()).flush();
                }
            }
     }

    public void drop_down(){
        int []counter = new int[candies_width];
        for (int i = 0; i < candies_width; i++) {
            for (int j = candies_height-1; j >= 0; j--) {
                if(candies[i][j].isBroke() && !swapping ) {
                    if(candies[i][j].getCandiesPower()==1){
                        explode(i,j);
                    }
                    if(candies[i][j].getCandiesPower()>1){
                        stripedCandiesBroken(i,j);
                    }
                    counter[i]++;
                    Replacement_candies[i][0].setVelY(5);
                    for(int n=0;n<candies_height;n++){
                        if(Replacement_candies[i][n].getPy() >=150 && counter[i] >n+1)
                            Replacement_candies[i][n+1].setVelY(5);

                    }
                    if(Replacement_candies[i][counter[i]-1].getPy() == 150 ){
                        for(int y=0;y<counter[i];y++){
                            Replacement_candies[i][y].setVelY(0);
                        }
                    }
                    if (j != 0) {
                        for (int x = j; x > 0; x--) {
                            candies[i][x - 1].setVelY(5);
                        }
                        if (candies[i][j - 1].getPy() >= candies[i][j].getPy()) {
                            for (int x = j; x > 0; x--) {
                                candies[i][x - 1].setVelY(0);
                            }
                        }
                    }
                }
                if(candies[i][j].isBroke() && candies[i][j].getCandiesPower() >=1){
                    candies[i][j].setCandiesPower(0);
                }
            }
        }

        if(!getCounterChanges(counter)){
            if(!moving()){
                for (int i = 0; i < candies_width; i++) {
                    int counterIndex =0;
                    for (int j = candies_height-1; j >= 0; j--) {
                        if(candies[i][j].isBroke()){
                            Switch(candies[i][j],Replacement_candies[i][counterIndex]);
                            counterIndex++;
                            for(int x=0;x<j;x++){
                                if(!candies[i][x].isBroke()){
                                    Switch(candies[i][x],candies[i][j]);
                                }
                            }
                            System.out.println(i+ " " + j);
                            candies[i][j].setBroke(false);
                            Score +=30;
                        }
                    }
                }
                Reset();
                ResetCandies();
            }
        }
        if(level_game_index == 0)
            endGame();
    }

    public void Move() {
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                candies[i][j].setPx(candies[i][j].getPx() + candies[i][j].getVelX());
                candies[i][j].setPy(candies[i][j].getPy() + candies[i][j].getVelY());
                Replacement_candies[i][j].setPy(Replacement_candies[i][j].getPy() + Replacement_candies[i][j].getVelY());
            }
        }
        if (CurrentPos == candies[SecondCandyRow][SecondCandyCol].getPx() || CurrentPos == candies[FirstCandyRow][FirstCandyCol].getPx()
                || CurrentPos == candies[SecondCandyRow][SecondCandyCol].getPy() || CurrentPos == candies[FirstCandyRow][FirstCandyCol].getPy()) {
            candies[FirstCandyRow][FirstCandyCol].setVelX(0);
            candies[SecondCandyRow][SecondCandyCol].setVelX(0);
            candies[FirstCandyRow][FirstCandyCol].setVelY(0);
            candies[SecondCandyRow][SecondCandyCol].setVelY(0);
            swapping = false;
            if(Non_matching_swap) {
                if(isHorizontal){
                    candies[SecondCandyRow][SecondCandyCol].setVelX(direction*5);
                    candies[FirstCandyRow][FirstCandyCol].setVelX(direction*-5);
                }
                else{
                    candies[SecondCandyRow][SecondCandyCol].setVelY(direction*5);
                    candies[FirstCandyRow][FirstCandyCol].setVelY(direction*-5);
                }
                Non_matching_swap = false;
            }
        }
    }

    public boolean moving(){
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                if (Replacement_candies[i][j].getVelY() > 0){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean getCounterChanges(int [] arr){
        int x=0;
        for (int j : arr) {
            if (j == 0) {
                x++;
            }
        }
        return x == arr.length;
    }
    public void stripedCandiesBroken(int i,int j){
        if(candies[i][j].getCandiesPower() ==2){
            for(int x=0;x<candies_height;x++)
            {
                candies[i][x].setBroke(true);
                candies[i][x].setCandyAnimation(candies[i][x].getCandyType());
            }
        }
        else if(candies[i][j].getCandiesPower() ==3){
            for(int x=0;x<candies_width;x++)
            {
                candies[x][j].setBroke(true);
                candies[x][j].setCandyAnimation(candies[x][j].getCandyType());
            }
        }
    }
   public void swap(Candies c1, Candies c2) {
        Moves_count--;
        swapping = true;
        if (isHorizontal) {
            c1.setVelX(-5 * direction);
            c2.setVelX(5 * direction);

        } else {
            c1.setVelY(-5 * direction);
            c2.setVelY(5 * direction);
        }
        if (Moves_count ==0){
            level_game_index = game_out_of_moves_index ;
        }

    }
    public void Non_Matching_swap(Candies candy1,Candies candy2) {
        Non_matching_swap =true;
        if(isHorizontal){
            candy1.setVelX(direction*5);
            candy2.setVelX(direction*-5);
        }
        else{
            candy1.setVelY(direction*5);
            candy2.setVelY(direction*-5);
        }
    }
    public boolean match_confirm() {
        Switch(candies[FirstCandyRow][FirstCandyCol],candies[SecondCandyRow][SecondCandyCol]);
        if(match_checker()){
            return true;
        }
        Switch(candies[FirstCandyRow][FirstCandyCol],candies[SecondCandyRow][SecondCandyCol]);
        return false;
    }
    public void explode(int i, int j){
        try{
            for(int x=i-1;x<=i+1;x++){
                for(int y=j-1;y<=j+1;y++){
                    candies[x][y].setBroke(true);
                    candies[x][y].setCandyAnimation(candies[x][y].getCandyType());
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e1){
            try{
                for(int x=i+1;x>=i-1;x--){
                    for(int y=j+1;y>=j-1;y--){
                        candies[x][y].setBroke(true);
                        candies[x][y].setCandyAnimation(candies[x][y].getCandyType());
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e2){
                try{
                    for(int x=i-1;x<=i+1;x++){
                        for(int y=j+1;y>=j-1;y--){
                            candies[x][y].setBroke(true);
                            candies[x][y].setCandyAnimation(candies[x][y].getCandyType());
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException e3){
                    try{
                        for(int x=i+1;x>=i-1;x--){
                            for(int y=j-1;y<=j+1;y++){
                                candies[x][y].setBroke(true);
                                candies[x][y].setCandyAnimation(candies[x][y].getCandyType());
                            }
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e4){
                        System.out.println("outOfBounds4");
                    }
                    System.out.println("outOfBounds3");
                }
                System.out.println("outOfBounds2");
            }
            System.out.println("outOfBounds1");
        }
    }


    public void Reset() {
        for (int i = 0; i < candies_width; i++) {
            for (int j = 0; j < candies_height; j++) {
                Replacement_candies[i][j].setPx(i * 50 + 325);
                Replacement_candies[i][j].setPy(100);
                Replacement_candies[i][j].setShape(random.nextInt(AmountOfCandies));
            }
        }
    }
    public void endGame(){
        if(Score >= targetScore){
            Score += Moves_count*60;
            score_obj.add_player(Menu.pass_name,Score);
            GamePanel.setLevelPassed(GamePanel.getCurrentLevel()+1,true);
            level_game_index = game_ended_index ;
        }
    }
}
