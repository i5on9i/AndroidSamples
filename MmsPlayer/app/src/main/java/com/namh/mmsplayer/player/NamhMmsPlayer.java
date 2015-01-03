package com.namh.mmsplayer.player;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by namh on 2015-01-03.
 */
public class NamhMmsPlayer {

    private MediaPlayer mediaPlayer = null;

    public NamhMmsPlayer() throws IOException {
        String url = "rtsp://ebslive.ebs.co.kr/ebswmalive";
        mediaPlayer = new MediaPlayer();
        // Set type to streaming
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // Listen for if the audio file can't be prepared
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // ... react appropriately ...
                // The MediaPlayer has moved to the Error state, must be reset!
                return false;
            }
        });
        // Attach to when audio file is prepared for playing
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        // Set the data source to the remote URL
        mediaPlayer.setDataSource(url);
        // Trigger an async preparation which will file listener when completed
        mediaPlayer.prepareAsync();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });


    }

    public void release(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

}
