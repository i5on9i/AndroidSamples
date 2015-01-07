package com.namh.mmsplayer.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by namh on 2015-01-03.
 */
public class NamhMmsPlayer {

    private static final String TAG = "NamhMmsPlayer";
    private MediaPlayer mediaPlayer = null;
    private final String EBS_RTSP_URL = "rtsp://ebsonairandaod.ebs.co.kr/fmradiobandiaod/bandiappaac";



    public NamhMmsPlayer() throws IOException {

        mediaPlayer = new MediaPlayer();
        // Set type to streaming
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // Listen for if the audio file can't be prepared
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // ... react appropriately ...
                // The MediaPlayer has moved to the Error state, must be reset!

                // @see http://developer.android.com/reference/android/media/MediaPlayer.OnErrorListener.html
                Log.e(TAG, "media player error");
                if (mediaPlayer != null)
                    mediaPlayer.reset();
                Log.e(TAG, "what:" + what + "; extra:" + extra);
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
        mediaPlayer.setDataSource(EBS_RTSP_URL);
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
