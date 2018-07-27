package com.example.administrator.myapp.util;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class MusicUtil {
    static MediaPlayer mediaPlayer=null;
    public static void listenerMusic(File file){

        try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void pauseMusic(){
        if (mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }
    public static void restartMusic(){
        if (mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
    public static void closeMusic(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
