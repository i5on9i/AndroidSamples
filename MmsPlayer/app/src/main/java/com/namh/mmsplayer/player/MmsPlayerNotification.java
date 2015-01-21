package com.namh.mmsplayer.player;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.namh.mmsplayer.MainActivity;
import com.namh.mmsplayer.R;
import com.namh.mmsplayer.util.FilenameUtils;

/**
 * Created by namh on 2015-01-20.
 */
public class MmsPlayerNotification {


    private final Service service;




    ////
    private Notification notification;

    private RemoteViews notiBigRemoteView;
    private RemoteViews notiNormalRemoteView;

    public MmsPlayerNotification(Service service) {
        this.service = service;


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(service)
                    .setSmallIcon(R.drawable.noti_stat_hand)
                    .setContentIntent(  // this is essential for Gingerbread, Do not remove this
                            PendingIntent.getActivity(service, 0, getIntentForMainActivity(), 0));
            notification = mBuilder.build();

            // only for jellybean or newer versions
            notification.contentView = getContentView();
            notification.bigContentView = getBigContentView();

        }else{
            String appName = service.getResources().getString(R.string.app_name);
            CharSequence title = "FilenameUtils.getName(mMediaSource)";
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(service)
                    .setSmallIcon(R.drawable.noti_stat_hand)
                    .setAutoCancel(true)
                    .setTicker(appName)
                    .setContentTitle(title)
                    .setContentIntent(
                            PendingIntent.getActivity(service, 0, getIntentForMainActivity(), 0));

            notification = mBuilder.build();
        }

        notification.flags |= Notification.FLAG_NO_CLEAR;


    }


    private RemoteViews getBigContentView() {
        if (notiBigRemoteView != null)
            return notiBigRemoteView;

        notiBigRemoteView = new RemoteViews(service.getPackageName(), R.layout.player_big_notification);
        notiBigRemoteView.setTextViewText(R.id.noti_text, "FilenameUtils.getName(mMediaSource)");


        notiBigRemoteView.setOnClickPendingIntent(
                R.id.noti_button_veto,
                PendingIntent.getService(service,
                        0,
                        new Intent("grabeco.musicservicecommand.veto").setClass(service, service.getClass()),
                        0));

        notiBigRemoteView.setOnClickPendingIntent(
                R.id.noti_button_play,
                PendingIntent.getService(service,
                        0,
                        new Intent("grabeco.musicservicecommand.play").setClass(service, service.getClass()),
                        0));

        notiBigRemoteView.setOnClickPendingIntent(
                R.id.noti_button_rewind,
                PendingIntent.getService(service,
                        0,
                        new Intent("grabeco.musicservicecommand.rew").setClass(service, service.getClass()),
                        0));

        notiBigRemoteView.setOnClickPendingIntent(
                R.id.noti_button_fastforward,
                PendingIntent.getService(service,
                        0,
                        new Intent("grabeco.musicservicecommand.ff").setClass(service, service.getClass()),
                        0));


        return notiBigRemoteView;

        // @FYI
        // for veto, onStartCommand() is used.

    }

    private RemoteViews getContentView() {
        if (notiNormalRemoteView != null)
            return notiNormalRemoteView;

        notiNormalRemoteView = new RemoteViews(service.getPackageName(), R.layout.player_normal_notification);
        notiNormalRemoteView.setTextViewText(R.id.noti_text, "FilenameUtils.getName(mMediaSource)");

        notiNormalRemoteView.setOnClickPendingIntent(
                R.id.noti_button_veto,
                PendingIntent.getService(service,
                        0,
                        new Intent("grabeco.musicservicecommand.veto").setClass(service, service.getClass()),
                        0));

        notiNormalRemoteView.setOnClickPendingIntent(
                R.id.noti_button_play,
                PendingIntent.getService(service,
                        0,
                        new Intent("grabeco.musicservicecommand.play").setClass(service, service.getClass()),
                        0));


        return notiNormalRemoteView;

        // @FYI
        // for veto, onStartCommand() is used.

    }

    private Intent getIntentForMainActivity() {
        Intent intent = new Intent(service, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("action", "close"); // gingerbread
        return intent;
    }


    ////////////////////////////////////////////////////////////////
    ////
    ////    Getters & Setters
    ////
    ////////////////////////////////////////////////////////////////
    public Notification getNotification() {
        return notification;
    }
}
