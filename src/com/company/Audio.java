
package com.company;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
public class Audio {
    private boolean musicPlaying ;
    Clip clip;
    AudioInputStream audioinput;

    Audio(String musiclocation){
        try {
            audioinput = AudioSystem.getAudioInputStream(new File(musiclocation));
            clip = AudioSystem.getClip();
            clip.open(audioinput);


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void pause(){clip.stop(); musicPlaying =false;}
    public void resume(){
        musicPlaying=true;
        clip.start();
        clip.loop(clip.LOOP_CONTINUOUSLY);

    }
    public void Start(){
        clip.start();
    }

    public boolean isMusicPlaying() {
        return musicPlaying;
    }

    public void setMusicPlaying(boolean musicPlaying) {
        this.musicPlaying = musicPlaying;
    }

    public void setVolume (int level){
        FloatControl gaincontrol = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        gaincontrol.setValue(level);
    }

}
