package com.example.android.myaudio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    Button play , pause ;
    SeekBar volBar , clip;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.Fart_Common_Everyday_Fart_Mike_Koenig);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);



        int currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);


        volBar = (SeekBar) findViewById(R.id.seekBar);
        volBar.setMax(maxVol);
        volBar.setProgress(currentVol);

        volBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //final
        clip = (SeekBar)findViewById(R.id.sb_clip);

        clip.setMax(mediaPlayer.getDuration());

        // could use the method handler() instead

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                clip.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0 , 1000);

        clip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btPlay:

                mediaPlayer.start();

                break;

            case R.id.btPause:

                mediaPlayer.pause();

                break;
        }


    }
}
