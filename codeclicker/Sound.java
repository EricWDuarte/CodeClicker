package com.example.codeclicker;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
    boolean soundOn = true;
    private static Sound instance;

    private MediaPlayer mp;
    private Context ct;

    public void addContext(Context context) {
        ct = context;
    }

    public boolean isSoundOn () {
        return soundOn;
    }

    public void onOffSound() {
        soundOn = !soundOn;
    }

    public void playTouchSound() {
        if(soundOn) {
            mp = MediaPlayer.create(ct, R.raw.click);
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp.release();
                }
            });
        }
    }

    public void playUpgradeSound() {
        if(soundOn) {
            mp = MediaPlayer.create(ct, R.raw.upgrade);
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp.release();
                }
            });
        }
    }

    public void playWrongSound() {
        if(soundOn) {
            mp = MediaPlayer.create(ct, R.raw.wrong);
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp.release();
                }
            });
        }
    }

    public void setInstance(){
        instance = null;
    }
    public static Sound getInstance(){
        if(instance == null){
            return instance = new Sound();
        }
        return instance;
    }
}
