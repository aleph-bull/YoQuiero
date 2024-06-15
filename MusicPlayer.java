import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class MusicPlayer {
    private static MusicPlayer instance;
    private ArrayList<GreenfootSound> playlist;
    private int currentSongIndex = 0;
    private boolean isPaused;
    GreenfootSound currentSong;

    private MusicPlayer() {
        playlist = new ArrayList<>();
        loadSongs();
    }
    
    private void loadSongs() {
        playlist.add(new GreenfootSound("song1.mp3"));
        playlist.add(new GreenfootSound("song2.mp3"));
        playlist.add(new GreenfootSound("song3.mp3"));
        playlist.add(new GreenfootSound("song4.mp3"));
    }

    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }
    
    public void play(){
        if (currentSong != null && currentSong.isPlaying()) {
            return;
        }
        currentSong = playlist.get(currentSongIndex);
        currentSong.setVolume(40);
        currentSong.play();
    }
    
    public void pause() {
            currentSong.pause();
            isPaused = true;
    }
    
    public void checkAndPlayNext() {
        if (currentSong != null && !currentSong.isPlaying() && !isPaused) {
            if (currentSong != null) {
                currentSong.stop();
            }
            currentSongIndex = (currentSongIndex + 1) % playlist.size();
            play();
        }
    }
}