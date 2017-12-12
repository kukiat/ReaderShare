package com.example.kukiat.readershare;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.netpie.microgear.Microgear;
import io.netpie.microgear.MicrogearEventListener;

/**
 * Created by kukiat on 11/20/2017 AD.
 */

public class NotificationService extends Service{
    private Microgear microgear = new Microgear(this);
    private String appid = "noti";
    private String key = "a4OavAaNmJ2HUUZ";
    private String secret = "KnE8YvodLiWpBCtMvE9fmrFaD";
    private String alias = "client";
    Handler handler;
    FirebaseUser user;
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service", "onBlind BackgroudService");

        return null;
    }

    @Override
    public void onCreate() {
        microgear.setCallback(new MicrogearCallBack());
        microgear.subscribe("message");
        microgear.connect(appid, key, secret, alias);
        Log.i("service", "create BackgroudService");
    }

    @SuppressLint("HandlerLeak")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("service", "onStartCommand BackgroudService");

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String message = bundle.getString("myKey");
                Log.i("dataa", message);
                JSONObject data;
                user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    try {
                        data = new JSONObject(message);
                        Log.i("push", data.toString());
                        JSONArray allFollow = data.getJSONArray("allFollower");
                        String reviewId = data.getString("lastKey");
                        String bookName = data.getString("bookName");
                        String reviewerName = data.getString("reviewerName");
                        for(int i=0;i<allFollow.length();i++) {
                            if(allFollow.getString(i).equals(user.getUid())) {
                                showNotification(reviewId, bookName, reviewerName);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        return START_STICKY;
    }

    public void showNotification(String reviewId, String bookName, String reviewerName) {
        Intent intent = new Intent(getBaseContext(), ShowActivity.class);
        intent.putExtra("reviewId", reviewId);

        PendingIntent pIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder noti = new NotificationCompat.Builder(getBaseContext())
                .setContentTitle("New Book Review For You")
                .setContentText(reviewerName+" has review "+ bookName)
                .setSmallIcon(R.drawable.mail)
                .setContentIntent(pIntent);

        noti.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, noti.build());
    }

    @Override
    public void onDestroy() {
        Log.i("service", "destroy BackgroudService");
        super.onDestroy();
        microgear.disconnect();
    }

    class MicrogearCallBack implements MicrogearEventListener {
        @Override
        public void onConnect() {
            Log.i("service", "netpie connect");
        }

        @Override
        public void onMessage(String topic, String message) {
            Message msg = handler.obtainMessage();
            Bundle bundle = new Bundle();
//            Log.i("service",message);
            bundle.putString("myKey", message);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        @Override
        public void onPresent(String token) {
//            Log.i("service", "netpie "+token);
        }

        @Override
        public void onAbsent(String token) {
//            Log.i("service", "netpie absend");
        }

        @Override
        public void onDisconnect() {
            Log.i("service", "netpie disconnect");
        }

        @Override
        public void onError(String error) {

//            Log.i("service", "netpie "+error);
//            onCreate();
        }

        @Override
        public void onInfo(String info) {
            Log.i("service", "netpie info");
        }
    }
}
