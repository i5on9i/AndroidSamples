package com.namh.mmsplayer.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.MediaController;

import java.io.IOException;

/**
 * Created by namh on 2015-01-03.
 */
public class NamhMmsPlayer {

    private static final String TAG = "NamhMmsPlayer";
    private MediaPlayer mediaPlayer = null;
    private final String EBS_RTSP_URL = "rtsp://ebsonairandaod.ebs.co.kr/fmradiobandiaod/bandiappaac";






    public NamhMmsPlayer(MediaPlayer.OnErrorListener errorListener,
                         MediaPlayer.OnPreparedListener preparedListener,
                         MediaPlayer.OnCompletionListener completionListener) throws IOException {

        mediaPlayer = new MediaPlayer();
        // Set type to streaming
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // Listen for if the audio file can't be prepared
        mediaPlayer.setOnErrorListener(errorListener);
        // Attach to when audio file is prepared for playing
        mediaPlayer.setOnPreparedListener(preparedListener);
        // Set the data source to the remote URL
        mediaPlayer.setDataSource(EBS_RTSP_URL);
        // Trigger an async preparation which will file listener when completed
        mediaPlayer.prepareAsync();

        mediaPlayer.setOnCompletionListener(completionListener);


    }


    public void start(){
        if(mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public boolean isPlaying(){
        if(mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public void pause(){
        if(mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public int getDuration(){
        if(mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public int getCurrentPosition(){
        if(mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int pos){
        if(mediaPlayer != null) {
            mediaPlayer.seekTo(pos);
        }
    }

    public void reset(){
        if(mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }


    public void release(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }



    public static class State{
        // all possible internal states
        public static final int ERROR = -1;
        public static final int IDLE = 0;
        public static final int PREPARING = 1;
        public static final int PREPARED = 2;
        public static final int PLAYING = 3;
        public static final int PAUSED = 4;
        public static final int PLAYBACK_COMPLETED = 5;
    }
}
