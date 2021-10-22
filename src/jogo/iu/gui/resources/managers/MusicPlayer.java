package jogo.iu.gui.resources.managers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import jogo.iu.gui.resources.Resources;

public class MusicPlayer {
    static MediaPlayer mp;
    public static void playMusic(String name) {
        String path = Resources.getResourceFilename("sounds/"+name);
        if(path == null) return;
        Media music = new Media(path);
        mp = new MediaPlayer(music);
        mp.setStartTime(Duration.ZERO);
        mp.setStopTime(music.getDuration());
        mp.setAutoPlay(true);
    }
}
