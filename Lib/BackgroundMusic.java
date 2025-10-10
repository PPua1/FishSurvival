package Lib;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
    private Clip clip;
    
    public void playMusic(String path) {
        if (clip == null || !clip.isRunning()) {
            try {
                File musicFile = new File(path);
                if (musicFile.exists()) {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    audioStream.close(); // Close the stream after opening
                    
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopMusic() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
            clip = null;
        }
    }
}