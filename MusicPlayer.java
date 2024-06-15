import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Random;

public class MusicPlayer {
    private static Random rand = new Random();
    private static MusicPlayer instance;
    private ArrayList<GreenfootSound> playlist;
    private boolean[] hasPlayed;
    private int currentSongIndex;
    private boolean isPaused;
    GreenfootSound currentSong;

    private MusicPlayer() {
        playlist = new ArrayList<>();
        loadSongs();
        hasPlayed = new boolean[playlist.size()];
        resetHasPlayed();
        randomizeSong();
    }
    
    private void loadSongs() {
        playlist.add(new GreenfootSound("song1.mp3"));
        playlist.add(new GreenfootSound("song2.mp3"));
        playlist.add(new GreenfootSound("song3.mp3"));
        playlist.add(new GreenfootSound("song4.mp3"));
    }

    private void resetHasPlayed() {
        for(int i = 0; i < hasPlayed.length; i++) {
            hasPlayed[i] = false;
        }
    }
    
    private void randomizeSong(){
        currentSongIndex = rand.nextInt(0, playlist.size());
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
        currentSong.play();
        hasPlayed[currentSongIndex] = true;
        System.out.println("Now Playing Track " + (currentSongIndex + 1));
    }
    
    public void pause() {
            currentSong.pause();
            isPaused = true;
    }
    
    public void checkAndPlayNext() {
        if (!currentSong.isPlaying() && !isPaused) {
            if (currentSong != null) {
                currentSong.stop();
            }

            boolean allSongsPlayed = true;
            for (boolean played : hasPlayed) {
                if (!played) {
                    allSongsPlayed = false;
                    break;
                }
            }
            
            if (allSongsPlayed) {
                resetHasPlayed();
            }
            
            while (hasPlayed[currentSongIndex]) {
                randomizeSong();
            }
            play();
        }
    }
}