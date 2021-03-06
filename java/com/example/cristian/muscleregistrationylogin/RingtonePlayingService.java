package com.example.cristian.muscleregistrationylogin;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Created by cristian on 20/11/2016.
 */

public class RingtonePlayingService extends Service {

    private boolean isRunning;
    private Context context;
    MediaPlayer mMediaPlayer;
    private int startId;

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("MyActivity", "In the Richard service");
        return null;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {


        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), UserAreaActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("Hora de Entrenar" + "!")
                .setContentText("Haz clic aqui!")
                .setSmallIcon(R.drawable.rutina)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        String state = intent.getExtras().getString("extra");

        Log.e("what is going on here  ", state);

        assert state != null;
        switch (state) {
            case "no":
                startId = 0;
                break;
            case "yes":
                startId = 1;
                break;
            default:
                startId = 0;
                break;
        }

        String richard_id = intent.getExtras().getString("quote id");
        Log.e("Service: richard id is " , richard_id);

        if(!this.isRunning && startId == 1) {
            Log.e("si no hay sonido ", " y quieres empezar");

            assert richard_id != null;
            if (richard_id.equals("0")) {

                int min = 1;
                int max = 9;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("El numero al azar es ", String.valueOf(random_number));

                if (random_number == 1) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.artic);
                }
                else if (random_number == 2) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.blucky);
                }
                else if (random_number == 3) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.cutto);
                }
                else if (random_number == 4) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.djsnake);
                }
                else if (random_number == 5) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.dnce);
                }
                else if (random_number == 6) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.iwarriors);
                }
                else if (random_number == 7) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.kygo);
                }
                else if (random_number == 8) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mpower);
                }
                else if (random_number == 9) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.ram);
                }
                else {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.artic);
                }
            }
            else if (richard_id.equals("1")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.artic);
            }
            else if (richard_id.equals("2")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.blucky);
            }
            else if (richard_id.equals("3")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.cutto );
            }
            else if (richard_id.equals("4")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.djsnake);
            }
            else if (richard_id.equals("5")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.dnce);
            }
            else if (richard_id.equals("6")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.iwarriors);
            }
            else if (richard_id.equals("7")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.kygo);
            }
            else if (richard_id.equals("8")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.mpower);
            }
            else if (richard_id.equals("9")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.ram);
            }
            else {
                mMediaPlayer = MediaPlayer.create(this, R.raw.artic);
            }


            mMediaPlayer.start();

            mNM.notify(0, mNotify);

            this.isRunning = true;
            this.startId = 0;

        }
        else if (!this.isRunning && startId == 0){
            Log.e("si no hay sonido", " y quieres terminar");

            this.isRunning = false;
            this.startId = 0;

        }

        else if (this.isRunning && startId == 1){
            Log.e("Si el sonido es ", " y quieres empezarlo");

            this.isRunning = true;
            this.startId = 0;

        }
        else {
            Log.e("si hay sonido ", " y quieres terminarlo");

            mMediaPlayer.stop();
            mMediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }


        Log.e("MyActivity", "In the service");

        return START_NOT_STICKY;
    }



    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        this.isRunning = false;
    }


}