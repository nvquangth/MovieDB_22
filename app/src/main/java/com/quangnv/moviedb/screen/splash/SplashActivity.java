package com.quangnv.moviedb.screen.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quangnv.moviedb.screen.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMain();
                finish();
            }
        }, TIME_DELAY);
    }

    public static Intent getMainIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private void gotoMain() {
        startActivity(getMainIntent(this));
    }
}
