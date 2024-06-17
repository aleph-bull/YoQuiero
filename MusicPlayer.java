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
        loadSongs(); // initialize playlist with songs
        hasPlayed = new boolean[playlist.size()];
        resetHasPlayed(); // mark all songs as not played
        randomizeSong(); // select the first song randomly
    }
    
    private void loadSongs() {
        playlist.add(new GreenfootSound("song1.mp3"));
        playlist.add(new GreenfootSound("song2.mp3"));
        playlist.add(new GreenfootSound("song3.mp3"));
        playlist.add(new GreenfootSound("song4.mp3"));
        playlist.add(new GreenfootSound("song5.mp3"));
        playlist.add(new GreenfootSound("song6.mp3"));
        playlist.add(new GreenfootSound("song7.mp3"));
    }

    private void resetHasPlayed() {
        for(int i = 0; i < hasPlayed.length; i++) {
            hasPlayed[i] = false; // mark each song as not played
        }
    }
    
    private void randomizeSong() {
        currentSongIndex = rand.nextInt(0, playlist.size()); // pick a random song index
    }
    
    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }
    
    public void play() {
        if(currentSong == null) {
            randomizeSong();
        } else if (currentSong.isPlaying()) {
            return; // if the current song is already playing, do nothing
        }
        currentSong = playlist.get(currentSongIndex);
        hasPlayed[currentSongIndex] = true; // mark the song as played
        currentSong.play();
    }
    
    public void pause() {
        currentSong.pause();
        isPaused = true;
    }
    
    public void stop() {
        currentSong.stop();
    }
    
    public void checkAndPlayNext() {
        if (!currentSong.isPlaying() && !isPaused) {
            if (currentSong != null) {
                currentSong.stop();
            }

            // check if all songs have been played
            boolean allSongsPlayed = true;
            for (boolean played : hasPlayed) {
                if (!played) {
                    allSongsPlayed = false;
                    break;
                }
            }
            
            if (allSongsPlayed) {
                resetHasPlayed(); // reset if all songs have been played
            }
            
            while (hasPlayed[currentSongIndex]) {
                randomizeSong(); // pick a new song that hasn't been played
            }
            play();
        }
        
    }
}