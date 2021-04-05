package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;


public class Menu implements KeyListener{


    ArrayList<Players> All_players = new ArrayList <Players>();
    Admin admin = new Admin();
//Players players = new Players();

    private final static Audio backgroundMusic = new Audio("background_music.wav");

    private boolean is_player_icon_animated = false;
    private boolean is_level3_animated = false;
    private boolean is_admin_icon_animated = false;
    private boolean is_level1_animated = false;
    private boolean is_play_icon_animated = false;
    private boolean is_level4_animated = false;
    private boolean is_LeaderBoardIcon_animated = false;
    private boolean is_level2_animated= false;
    private boolean is_name_typed = false;
    private boolean is_next_button_animated = false;
    private boolean is_next_button2_animated = false;
    private boolean is_next_button3_animated = false;
    private boolean is_name_of_admin_correct = false;
    private boolean is_pass_of_admin_correct = false;
    private boolean is_credits_Icon_animated = false;

    private String name_of_user = "";
    public static String pass_name ="";
    private String name_of_admin ="";
    private String pass_of_admin ="";
    public String entered_string_of_horizontal_candies = "";
    public String entered_string_of_vertical_candies = "";
    public String entered_string_of_candies = "";
    public String entered_string_of_moves  = "";


    int custom_game_index =0;
    int menu_game_index =-1;
    final int main_menu = 0 ;
    final int admin_player_select_menu = -1;
    final int name_enter_menu = 1 ;
    final int level_number_select_menu = 2;
    final int leaderboard_menu = 3;
    final int user_customized_menu = 5;
    final int admin_name_menu = 6;
    final int admin_pass_menu = 7;
    final int sound_optios_menu =8;
    final int custom_introduction_page =0;
    final int custom_horizontal_candies =1;
    final int custom_vertical_candies=2;
    final int custom_numberof_candies =3;
    final int custom_number_of_moves =4;
    final int credits_menu = 9;
    public static int entered_number_of_horizontal_candies  ;
    public static int entered_number_of_vertical_candies ;
    public static int entered_number_of_candies ;
    public static int entered_number_of_moves   ;
    private int sound_level_number = 0 ;
    String[] names_arr = new String[5];
    int[] scores_arr = new int[5];




    private final Image  menu = new ImageIcon("Textures\\menu.jpg").getImage();
    public final Image  music_on_icon = new ImageIcon("Textures\\music_on.png").getImage();
    public final Image  music_off_icon = new ImageIcon("Textures\\music_off.png").getImage();
    private final Image enter_name_admin_background = new ImageIcon ("Textures\\enter_admin_name.jpg").getImage();
    private final Image enter_pass_admin_background = new ImageIcon ("Textures\\enter_admin_pass.jpg").getImage();
    private final Image player_icon = new ImageIcon ("Textures\\player_icon.png").getImage();
    private final Image animated_player_icon = new ImageIcon ("Textures\\player_icon.png").getImage();
    private final Image admin_icon = new ImageIcon ("Textures\\admin_icon.png").getImage();
    private final Image animated_admin_icon = new ImageIcon ("Textures\\admin_icon.png").getImage();
    private final Image play_icon = new ImageIcon ("Textures\\play_button.png").getImage();
    private final Image player_name_enter_background = new ImageIcon ("Textures\\ccs_banner.jpg").getImage();
    private final Image player_or_admin_background = new ImageIcon ("Textures\\welcome_page.jpg").getImage();
    private final Image level_select_background = new ImageIcon ("Textures\\choose_level_back.jpg").getImage();
    private final Image customized_text_bar = new ImageIcon("Textures\\blue_text_bar.png").getImage();
    private final Image create_your_game_button = new ImageIcon("Textures\\create_your_game_btn.png").getImage();
    private final Image credits_btn = new ImageIcon("Textures\\credits_btn.png").getImage();
    private final Image credits_background = new ImageIcon ("Textures\\credits_photo.jpg").getImage();
    private final Image level_1_icon = new ImageIcon ("Textures\\button1.png").getImage();
    private final Image level_2_icon = new ImageIcon ("Textures\\button2.png").getImage();
    private final Image level_3_icon = new ImageIcon ("Textures\\button3.png").getImage();
    private final Image level_4_icon = new ImageIcon ("Textures\\button4.png").getImage();
    private final Image Returnbtn=new ImageIcon("Textures\\b.png").getImage();
    private final Image back_icon = new ImageIcon ("Textures\\back_btn.png").getImage();
    private final Image next_icon = new ImageIcon ("Textures\\main_next_btn.png").getImage();
    private final Image text_bar = new ImageIcon ("Textures\\text_bar.png").getImage();
    private final Image next_btn = new ImageIcon ("Textures\\next_btn.png").getImage();
    private final Image leaderboard_icon = new ImageIcon ("Textures\\leaderboard_icon.png").getImage();
    private final Image leaderboard_background = new ImageIcon ("Textures\\highScoreback.jpg").getImage();
    private final Image custome_instructions_1 = new ImageIcon ("Textures\\cutome_game_instructios_1.jpg").getImage();
    private final Image custome_instructions_2 = new ImageIcon ("Textures\\cutome_game_instructios_2.jpg").getImage();
    private final Image custome_instructions_3 = new ImageIcon ("Textures\\cutome_game_instructios_3.jpg").getImage();
    private final Image custome_instructions_4 = new ImageIcon ("Textures\\cutome_game_instructios_4.jpg").getImage();
    private final Image custome_instructions_5 = new ImageIcon ("Textures\\cutome_game_instructios_5.jpg").getImage();
    private final Image music_settings_background = new ImageIcon ("Textures\\sound_setting_background.jpg").getImage();
    private final Image plus_button = new ImageIcon ("Textures\\plus_btn.png").getImage();
    private final Image minus_button = new ImageIcon ("Textures\\minus_btn.png").getImage();
    private final Image music_bar_icon = new ImageIcon ("Textures\\volume_bar.png").getImage();
    private final Image settings_icon = new ImageIcon("Textures\\settings_button.png").getImage();

    Menu(){

        backgroundMusic.resume();
    }
    public static Audio getBackgroundMusic(){
        return backgroundMusic;}


    public void read_players_fromFile()throws IOException {

        FileReader fr =new FileReader("players.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        String name_tmp="";
        String score_tmp="";


        while((line = br.readLine())!= null){
            Players obj_tmp = new Players();
            name_tmp= line;
            line = br.readLine();
            System.out.println(name_tmp);
            obj_tmp.setCurrent_username(name_tmp);
            score_tmp =line;
            System.out.println(score_tmp);
            obj_tmp.setCurrent_score(Integer.parseInt(score_tmp));
            All_players.add(obj_tmp);
        }

        br.close();
        fr.close();



        All_players.sort(new Comparator<Players>() {

            @Override
            public int compare(Players o1, Players o2) {
                return Integer.compare(o2.getCurrent_score(), o1.getCurrent_score());
            }
        });
        for (int i = 0; i < 5; i++) {
            names_arr[i] = All_players.get(i).getCurrent_username();
            scores_arr[i] = All_players.get(i).getCurrent_score();
        }

    }
    //======================================================================
    public void draw  (Graphics g) {
        if (GamePanel.game_index == main_menu) {
            if (menu_game_index == admin_player_select_menu) {
                g.drawImage(player_or_admin_background, 0, 0, null);

                if (!is_player_icon_animated) {

                    g.drawImage(player_icon, 220, 440, null);
                } else if (is_player_icon_animated) {
                    g.drawImage(animated_player_icon, 240, 420, null);
                }


                if (!is_admin_icon_animated) {

                    g.drawImage(admin_icon, 560, 440, null);
                } else if (is_admin_icon_animated) {
                    g.drawImage(animated_admin_icon, 540, 420, null);
                }
                if (backgroundMusic.isMusicPlaying())
                    g.drawImage(music_on_icon, 930, 5, null);
                else if (!backgroundMusic.isMusicPlaying())
                    g.drawImage(music_off_icon, 930, 5, null);
                
            }


            //============================================================================

            else if (menu_game_index == main_menu) {
                g.drawImage(menu, 0, 0, null);
                g.drawImage(settings_icon , 950 , 5, null);
                if (!is_LeaderBoardIcon_animated) {
                    g.drawImage(leaderboard_icon, 530, 400, null);
                } else if (is_LeaderBoardIcon_animated) {
                    g.drawImage(leaderboard_icon, 560, 430, null);
                }

                if (!is_credits_Icon_animated) {
                    g.drawImage(credits_btn, 400, 400, null);
                } else if (is_credits_Icon_animated) {
                    g.drawImage(credits_btn, 430, 430, null);
                }


                if (!is_play_icon_animated) {

                    g.drawImage(play_icon, 420, 320, null);
                } else if (is_play_icon_animated) {
                    g.drawImage(play_icon, 450, 350, null);
                }
                g.drawImage(back_icon, 5, 5, null);

            }

            else if (menu_game_index == sound_optios_menu){
                backgroundMusic.setVolume(sound_level_number);
                if (sound_level_number == -5){
                    backgroundMusic.setVolume (-20);
                }
                g.drawImage (music_settings_background ,0,0,null);
                g.drawImage(back_icon,5,5,null);
                g.drawImage(minus_button,390,250,null);
                g.drawImage(plus_button,490,250,null);
                for (int i=-5 ; i<sound_level_number ; i++){
                    g.drawImage(music_bar_icon,i*50+500 , 380 ,null);
                }

            }

            else if (menu_game_index == admin_name_menu) {
                g.drawImage(enter_name_admin_background, 0, 0, null);


                if (!is_next_button_animated) {
                    g.drawImage(next_btn, 620, 400, null);
                } else {
                    g.drawImage(next_btn, 640, 420, null);
                }


                g.setColor(Color.magenta);
                g.setFont(new Font("TimesRoman", Font.BOLD, 50));
                g.drawString(name_of_admin, 550, 320);
                g.drawImage(back_icon,5,5,null);

            }



            else if (menu_game_index == admin_pass_menu) {
                g.drawImage(enter_pass_admin_background, 0, 0, null);


                if (!is_next_button2_animated) {
                    g.drawImage(next_btn, 620, 400, null);
                } else {
                    g.drawImage(next_btn, 640, 420, null);
                }


                g.setColor(Color.magenta);
                g.setFont(new Font("TimesRoman", Font.BOLD, 50));
                g.drawString(pass_of_admin, 550, 320);
                g.drawImage(back_icon,5,5,null);
            }

            else if (menu_game_index == name_enter_menu) {
                g.drawImage(player_name_enter_background, 0, 0, null);
                g.drawImage(back_icon, 5, 5, null);
                if (is_next_button3_animated == false)
                    g.drawImage(next_btn,460,250,null);
                
                else if (is_next_button3_animated == true)
                    g.drawImage(next_btn,480,270,null);

                g.drawImage(text_bar, 380, 90, null);
                g.setColor(Color.MAGENTA);
                g.setFont(new Font("TimesRoman", Font.BOLD, 40));
                g.drawString(name_of_user, 420, 195);
                g.setColor(Color.magenta);
                g.setFont(new Font("TimesRoman", Font.BOLD, 50));
                g.drawString("Enter your name ", 320, 75);
                g.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g.drawString("(between 4 and 10 char)",460,120);


            }
            else if (menu_game_index == level_number_select_menu) {
                g.drawImage(level_select_background, 0, 0, null);

                if (!is_level1_animated)
                    g.drawImage(level_1_icon, 130, 20, null);
                else if (is_level1_animated)
                    g.drawImage(level_1_icon, 160, 40, null);
                if (!is_level2_animated)
                    g.drawImage(level_2_icon, 380, 20, null);
                   else if (is_level2_animated)
                       g.drawImage(level_2_icon, 410, 40, null);
                if (!is_level3_animated)
                        g.drawImage(level_3_icon, 630, 20, null);
                    else if (is_level3_animated)
                        g.drawImage(level_3_icon, 660, 40, null);
                 if (!is_level4_animated)
                        g.drawImage(level_4_icon, 840, 5, null);
                    else if (is_level4_animated)
                        g.drawImage(level_4_icon, 860, 25, null);
    

                g.drawImage(back_icon, 5, 5, null);
            }
            else if (menu_game_index == leaderboard_menu) {
                g.drawImage(leaderboard_background, 0, 0, null);
                g.drawImage(back_icon, 10, 5, null);
                g.setColor(Color.yellow);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
                g.drawString("   Name        Score", 90, 210);
                g.setColor(Color.yellow);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));

                //score display variables

                for (int i = 0; i < 5; i++) {
                    //System.out.println(All_players.get(i).current_username +" awab" + All_players.get(i).getCurrent_score());
                    g.drawString(String.valueOf(i + 1), 130, i * 50 + 260);
                    g.drawString("-", 150, i * 50 + 260);
                    g.drawString(All_players.get(i).getCurrent_username(), 170, i * 50 + 260);
                    g.drawString(String.valueOf(All_players.get(i).getCurrent_score()), 360, i * 50 + 260);
                }

            }

            else if (menu_game_index== credits_menu){
                g.drawImage(credits_background, 0, 0, null);
                g.drawImage(back_icon, 10, 5, null);

            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////
           

            else if (menu_game_index == user_customized_menu){
                if (custom_game_index ==custom_introduction_page){
                    g.drawImage(custome_instructions_1,0,0,null);
                    g.drawImage (next_icon,930,550,null);
                    g.drawImage(back_icon,5,5,null);
                }

                else if (custom_game_index == custom_horizontal_candies){
                    g.drawImage(custome_instructions_2,0,0,null);
                    g.drawImage (next_icon,930,550,null);
                    g.drawImage(back_icon,5,5,null);
                    g.drawImage(customized_text_bar,350,400,null);
                    g.setColor(Color.BLUE);
                    g.setFont( new Font("TimesRoman", Font.BOLD , 50));
                    g.drawString(entered_string_of_horizontal_candies,475, 510);
                }

                else if (custom_game_index == custom_vertical_candies){
                    g.drawImage(custome_instructions_3,0,0,null);
                    g.drawImage (next_icon,930,550,null);
                    g.drawImage(back_icon,5,5,null);
                    g.drawImage(customized_text_bar,350,400,null);
                    g.setColor(Color.BLUE);
                    g.setFont( new Font("TimesRoman", Font.BOLD , 50));
                    g.drawString(entered_string_of_vertical_candies,475, 510);
                }
                else if (custom_game_index == custom_numberof_candies){
                    g.drawImage(custome_instructions_4,0,0,null);
                    g.drawImage (next_icon,930,550,null);
                    g.drawImage(back_icon,5,5,null);
                    g.drawImage(customized_text_bar,350,400,null);
                    g.setColor(Color.blue);
                    g.setFont( new Font("TimesRoman", Font.BOLD , 50));
                    g.drawString(entered_string_of_candies,475, 510);
                }

                else if (custom_game_index == custom_number_of_moves){
                    g.drawImage(custome_instructions_5,0,0,null);
                    g.drawImage (create_your_game_button,830,530,null);;
                    g.drawImage(back_icon,5,5,null);
                    g.drawImage(customized_text_bar,350,400,null);
                    g.setColor(Color.BLUE);
                    g.setFont( new Font("TimesRoman", Font.BOLD , 50));
                    g.drawString(entered_string_of_moves,475, 510);

                }

            }

        }

    }



//*********************************************************************************************************************************************

    public void mouseClicked(MouseEvent e){

        if (menu_game_index == admin_player_select_menu){
            if( e.getX()>220 && e.getX()<440 && e.getY() > 440 && e.getY() <530) {

                menu_game_index = main_menu;

            }

           else if( is_admin_icon_animated = e.getX()>560 && e.getX()<800 && e.getY() > 440 && e.getY() <530){
                menu_game_index = admin_name_menu;

            }
            else if (e.getX()>900 && e.getX()<1000 && e.getY() > 5 && e.getY() <90) {

                if (backgroundMusic.isMusicPlaying()) {
                    getBackgroundMusic().pause();
                    backgroundMusic.setMusicPlaying(false);
                } else if (!backgroundMusic.isMusicPlaying()) {
                    getBackgroundMusic().resume();
                    backgroundMusic.setMusicPlaying(true);
                }
            }
            
        }
        


        else  if (menu_game_index == admin_name_menu){
            if (e.getX()>620 && e.getX()<800 && e.getY() > 400 && e.getY() <500) {
                is_name_of_admin_correct = admin.checkAdminName(name_of_admin) ;
                if (is_name_of_admin_correct) {
                    menu_game_index = admin_pass_menu;
                } else {
                    System.out.println("name is incorrect");
                }
            }
            
            else if (e.getX()>5 && e.getX()<55 && e.getY() > 5 && e.getY() <55){

                menu_game_index=admin_player_select_menu;

            }
        }

        else if (menu_game_index == admin_pass_menu){
            if (e.getX()>620 && e.getX()<800 && e.getY() > 400 && e.getY() <500){
                is_pass_of_admin_correct = admin.checkAdminPass(pass_of_admin) ;
                if (is_pass_of_admin_correct) {
                    menu_game_index = user_customized_menu;
                   name_of_admin = "";
                   pass_of_admin = "";
                   
                } else {
                    System.out.println("pass is incorrect");
                }


            }
            
            else if (e.getX()>5 && e.getX()<55 && e.getY() > 5 && e.getY() <55){

                menu_game_index=admin_name_menu;

            }
        }

        else if (menu_game_index ==main_menu){
            if (e.getX()>5 && e.getX()<55 && e.getY() > 5 && e.getY() <55){

                menu_game_index=admin_player_select_menu;

            }
            else if (e.getX()>420 && e.getX()<620 && e.getY() > 320 && e.getY() <385)
                menu_game_index =name_enter_menu  ;

            else if (e.getX()>530 && e.getX()<630 && e.getY() > 400 && e.getY() <500){
                try {
                    read_players_fromFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                menu_game_index = leaderboard_menu ;

            }

            else if (e.getX()>400 && e.getX()<500 && e.getY() > 400 && e.getY() <500){
                menu_game_index = credits_menu ;
            }

            else if (e.getX()>950 && e.getX()<1000 && e.getY() > 0 && e.getY() <50){
                menu_game_index = sound_optios_menu ;
            }
        }
        else if (menu_game_index == credits_menu) {
            if (e.getX()>5 && e.getX()<65 && e.getY() > 5 && e.getY() <65){

                menu_game_index=main_menu;

            }
        }
        else if (menu_game_index == sound_optios_menu){


            if (e.getX()>5 && e.getX()<55 && e.getY() > 5 && e.getY() <55){

                menu_game_index=main_menu;

            }

            else if (e.getX()>390 && e.getX()<490 && e.getY() > 250 && e.getY() <350){
                if (sound_level_number >-5){
                    sound_level_number--;
                }

            }
            else if (e.getX()>490 && e.getX()<590 && e.getY() > 250 && e.getY() <350){
                if (sound_level_number <5){
                    sound_level_number++;
                }

            }

        }

        else if (menu_game_index==name_enter_menu) {
            if (e.getX()>5 && e.getX()<55 && e.getY() > 5 && e.getY() <55){

                menu_game_index=main_menu;

            }
            else if (e.getX()>460 && e.getX()<600 && e.getY() > 250&& e.getY() <350 && is_name_typed )
                menu_game_index = level_number_select_menu;


        }


        else if (menu_game_index ==level_number_select_menu){
            if (e.getX()>130 && e.getX()<260 && e.getY() > 20 && e.getY() <150){
                GamePanel.setCurrentLevel(0);
                GamePanel.game_index=1;

            }
            
            else if (e.getX()>380 && e.getX()<510 && e.getY() > 20 && e.getY() <150 && GamePanel.getLevelPassed(1)){
                GamePanel.setCurrentLevel(1);
                GamePanel.game_index=1;
            }
            else if (e.getX()>630 && e.getX()<760 && e.getY() > 20 && e.getY() <150 && GamePanel.getLevelPassed(2)){
                GamePanel.setCurrentLevel(2);
                GamePanel.game_index=1;
            }
               else if (e.getX()>840 && e.getX()<980 && e.getY() > 5 && e.getY() <135 && GamePanel.getLevelPassed(3)){
                GamePanel.setCurrentLevel(3);
                GamePanel.game_index=1;
            }

            else if (e.getX()>5 && e.getX()<55 && e.getY() > 5 && e.getY() <55){

                menu_game_index=main_menu;
                name_of_user ="";
                for (int i=0 ; i <4 ;i++){
                    GamePanel.setLevelPassed(i,false);
                }


            }
        }

        else if (menu_game_index ==leaderboard_menu) {
            if (e.getX()>5 && e.getX()<65 && e.getY() > 5 && e.getY() <65){

                menu_game_index=main_menu;

            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
     

        else if (menu_game_index == user_customized_menu ){
            if ( custom_game_index == custom_introduction_page){
                if (e.getX()>930 && e.getX() <1000 && e.getY() >550 && e.getY()<600){
                    custom_game_index = custom_horizontal_candies;
                }
                else if (e.getX()>5 && e.getX() <55 && e.getY() >5 && e.getY()<55){
                    menu_game_index=admin_player_select_menu;
                }

            }

            else if ( custom_game_index == custom_horizontal_candies){
               if (e.getX()>930 && e.getX() <1000 && e.getY() >550 && e.getY()<600){
                    if (entered_number_of_horizontal_candies >3 && entered_number_of_horizontal_candies <9)
                        custom_game_index = custom_vertical_candies;
                    else{
                        custom_game_index = custom_horizontal_candies;
                        entered_string_of_horizontal_candies ="";
                    }
                }
                else if (e.getX()>5 && e.getX() <55 && e.getY() >5 && e.getY()<55){
                    custom_game_index = custom_introduction_page ;
                }
            }


            else if ( custom_game_index == custom_vertical_candies){

                    if (e.getX()>930 && e.getX() <1000 && e.getY() >550 && e.getY()<600){
                    if (entered_number_of_vertical_candies >3 && entered_number_of_vertical_candies <9)
                        custom_game_index = custom_numberof_candies;
                    else{
                        custom_game_index = custom_vertical_candies;
                        entered_string_of_vertical_candies ="";

                    }
                }
                else if (e.getX()>5 && e.getX() <55 && e.getY() >5 && e.getY()<55){
                    custom_game_index = custom_horizontal_candies ;
                }
            }


            else if ( custom_game_index == custom_numberof_candies){
                if (e.getX()>930 && e.getX() <1000 && e.getY() >550 && e.getY()<600){
                    if (entered_number_of_candies > 2 && entered_number_of_candies <7)
                        custom_game_index = custom_number_of_moves;

                    else {
                        custom_game_index = custom_numberof_candies ;
                        entered_string_of_candies ="";
                    }
                }


                else if (e.getX()>5 && e.getX() <55 && e.getY() >5 && e.getY()<55){
                    custom_game_index = custom_vertical_candies ;
                }
            }


            else if ( custom_game_index == custom_number_of_moves){
                if (e.getX()>830 && e.getX() <1000 && e.getY() >530 && e.getY()<600){
                    if (entered_number_of_moves > 0){
                        custom_game_index = custom_introduction_page ;
                        GamePanel.defineCustom();
                        GamePanel.game_index=1;
                        GamePanel.setCurrentLevel(4);
                        entered_string_of_candies ="";
                        entered_string_of_vertical_candies ="";
                        entered_string_of_horizontal_candies = "";
                        entered_string_of_moves = "";
                    }

                    else {
                        menu_game_index = custom_number_of_moves ;
                        entered_string_of_moves ="";
                    }


                }
                else if (e.getX()>5 && e.getX() <55 && e.getY() >5 && e.getY()<55){
                    custom_game_index = custom_numberof_candies ;
                }
            }


        }



    }
    //***********************************************************************************************************************************************

    public void mouseMoved(MouseEvent e){

        if (menu_game_index == admin_player_select_menu){
            is_player_icon_animated = e.getX()>220 && e.getX()<440 && e.getY() > 440 && e.getY() <530;

            is_admin_icon_animated = e.getX()>560 && e.getX()<800 && e.getY() > 440 && e.getY() <530;
        }

        else  if (menu_game_index== admin_name_menu){
            is_next_button_animated = e.getX()>620 && e.getX()<800 && e.getY() > 400 && e.getY() <500;

        }
        else  if (menu_game_index== admin_pass_menu){
            is_next_button2_animated = e.getX()>620 && e.getX()<800 && e.getY() > 400 && e.getY() <500;
        }
        else if (menu_game_index==main_menu){
            is_play_icon_animated = e.getX()>420 && e.getX()<620 && e.getY() > 320 && e.getY() <385;

            is_LeaderBoardIcon_animated = e.getX()>530 && e.getX()<630 && e.getY() > 400 && e.getY() <500;
            is_credits_Icon_animated = e.getX()>400 && e.getX()<500 && e.getY() > 400 && e.getY() <500;

        }

        else if (menu_game_index==name_enter_menu) {

            is_next_button3_animated = e.getX()>460 && e.getX()<600 && e.getY() > 250&& e.getY() <350;

        }

        else if (menu_game_index==level_number_select_menu){
            is_level1_animated = e.getX()>130 && e.getX()<260 && e.getY() > 20 && e.getY() <150;

            is_level2_animated = e.getX()>380 && e.getX()<510 && e.getY() > 20 && e.getY() <150;

            is_level3_animated = e.getX()>630 && e.getX()<760 && e.getY() > 20 && e.getY() <150;
            
            is_level4_animated = e.getX()>840 && e.getX()<980 && e.getY() > 5 && e.getY() <135;

        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


     



        /////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (menu_game_index == name_enter_menu) {
            name_of_user += e.getKeyChar();
            if (e.getKeyChar() == '\b' && name_of_user.length() > 1)
                name_of_user = name_of_user.substring(0, name_of_user.length() - 2);

            if (name_of_user.length() > 10)
                name_of_user = name_of_user.substring(0, name_of_user.length() - 1);
            System.out.println(name_of_user);
            pass_name = name_of_user;
            System.out.println("name saved in obj successfully");


            if (name_of_user.length() > 3)
                is_name_typed = true;
            else if (name_of_user.length() <= 3)
                is_name_typed = false;
        } 
        else if (menu_game_index == admin_name_menu) {
             name_of_admin += e.getKeyChar();
             if (e.getKeyChar() == '\b' && name_of_admin.length() > 1)
              name_of_admin = name_of_admin.substring(0,name_of_admin.length() - 2);
        } 
        
        else if (menu_game_index == admin_pass_menu) {
            pass_of_admin += e.getKeyChar();
              if (e.getKeyChar() == '\b' && pass_of_admin.length() > 1)
              pass_of_admin = pass_of_admin.substring(0,pass_of_admin.length() - 2);
        } 
        
        else if (menu_game_index == user_customized_menu && custom_game_index == custom_horizontal_candies) {
            entered_string_of_horizontal_candies += e.getKeyChar();
            if (e.getKeyChar() == '\b' && entered_string_of_horizontal_candies.length() > 1)
                entered_string_of_horizontal_candies = entered_string_of_horizontal_candies.substring(0, entered_string_of_horizontal_candies.length() - 2);
            try {
                entered_number_of_horizontal_candies = Integer.parseInt(entered_string_of_horizontal_candies);
            } catch (NumberFormatException x) {
                entered_string_of_horizontal_candies = "";
                custom_game_index = custom_horizontal_candies;
            }

        } else if (menu_game_index == user_customized_menu && custom_game_index == custom_vertical_candies) {
            entered_string_of_vertical_candies += e.getKeyChar();
            if (e.getKeyChar() == '\b' && entered_string_of_vertical_candies.length() > 1)
                entered_string_of_vertical_candies = entered_string_of_vertical_candies.substring(0, entered_string_of_vertical_candies.length() - 2);
            try {
                entered_number_of_vertical_candies = Integer.parseInt(entered_string_of_vertical_candies);
            } catch (NumberFormatException x) {
                entered_string_of_vertical_candies = "";
                custom_game_index = custom_vertical_candies;
            }
        } else if (menu_game_index == user_customized_menu && custom_game_index == custom_numberof_candies) {
            entered_string_of_candies += e.getKeyChar();
            if (e.getKeyChar() == '\b' && entered_string_of_candies.length() > 1)
                entered_string_of_candies = entered_string_of_candies.substring(0, entered_string_of_candies.length() - 2);
            try {
                entered_number_of_candies = Integer.parseInt(entered_string_of_candies);
            } catch (NumberFormatException x) {
                entered_string_of_candies = "";
                custom_game_index = custom_numberof_candies;
            }

        } else if (menu_game_index == user_customized_menu && custom_game_index == custom_number_of_moves) {
            entered_string_of_moves += e.getKeyChar();
            if (e.getKeyChar() == '\b' && entered_string_of_moves.length() > 1)
                entered_string_of_moves = entered_string_of_moves.substring(0, entered_string_of_moves.length() - 2);
            try {
                entered_number_of_moves = Integer.parseInt(entered_string_of_moves);
            } catch (NumberFormatException x) {
                entered_string_of_moves = "";
                custom_game_index = custom_number_of_moves;
            }


        }


    }
        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }


    }

