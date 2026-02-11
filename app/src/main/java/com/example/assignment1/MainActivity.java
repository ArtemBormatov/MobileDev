package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String tag = "EVH_Demo: ";

    // store times between callbacks
    long lastTime = 0; //long stores large integers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // calculate elapsed time since last callback

        displayElapsedTime("OnCreate");
        setContentView(R.layout.activity_main);
        // Logs on logcat (EVH for filter)
    }

    private void displayElapsedTime(String Text) {
        long elapsedTime = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        Log.d(tag, tag + Text + " elapsedTime=" + elapsedTime + " ms");
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayElapsedTime("OnStart");
        // calculate elapsed since onCreate


        /*if (getWindow() != null) {
            getWindow().setLayout(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
            );
        } */

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        displayElapsedTime("OnRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        displayElapsedTime("OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        displayElapsedTime("OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        displayElapsedTime("OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        displayElapsedTime("OnDestroy");
    }
}
