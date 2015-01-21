package com.namh.mmsplayer;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.namh.mmsplayer.player.MmsPlayerService;
import com.namh.mmsplayer.player.NamhMmsPlayer;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private MediaPlayer mMediaPlayer;

    private MmsPlayerService mMmsPlayerService;
    boolean mIsBound = false;
    private PlaceholderFragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (savedInstanceState == null) {
            fragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rl_container_main, fragment)
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tlbr_main);
        setSupportActionBar(toolbar);
        // toolbar.setTitle() requires API-21


        doBindService();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(fragment != null){
            Button testButton = fragment.getButton();
            testButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Bind to LocalService

                    if(mMmsPlayerService != null){
                        mMmsPlayerService.start();
                        Log.d(TAG, mMmsPlayerService.testFunc());
                    }

                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        doUnbindService();
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

        public Button getButton(){
            return (Button)getView().findViewById(R.id.btn_test);
        }
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MmsPlayerService.LocalBinder binder = (MmsPlayerService.LocalBinder) service;
            mMmsPlayerService = binder.getService();
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mIsBound = false;
        }
    };


    private void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        Intent intent = new Intent(this, MmsPlayerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        mIsBound = true;
    }

    private void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

}
