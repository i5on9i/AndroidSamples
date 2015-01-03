package com.namh.mmsplayer;


import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.namh.mmsplayer.player.NamhMmsPlayer;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rl_container_main, new PlaceholderFragment())
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tlbr_main);
        setSupportActionBar(toolbar);
        // toolbar.setTitle() requires API-21


        NamhMmsPlayer mmsplayer = null;
        try {
            mmsplayer = new NamhMmsPlayer();
        } catch (IOException e) {
            // @see http://developer.android.com/guide/topics/media/mediaplayer.html#mediaplayer

            e.printStackTrace();
            if(mmsplayer != null)
                mmsplayer.release();
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
