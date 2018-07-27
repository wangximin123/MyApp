package com.example.administrator.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapp.util.MusicUtil;

public class BasicActivity extends AppCompatActivity {


    @Override
    protected void onPostResume() {
        super.onPostResume();
        MusicUtil.restartMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MusicUtil.pauseMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicUtil.closeMusic();
    }
}
