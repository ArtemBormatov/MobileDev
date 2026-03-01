package com.example.assignment22;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvRandom, tvLastRandom;
    private int currentRandom = 0;

    private static final String RANDOM_KEY = "random_key";
    private static final String TIME_KEY = "time_key";

    private Handler handler = new Handler();
    private Random random = new Random();

    private Runnable randomTask = new Runnable() {
        @Override
        public void run() {
            currentRandom = random.nextInt(1000);
            tvRandom.setText("Random: " + currentRandom);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRandom = findViewById(R.id.tv_random);
        tvLastRandom = findViewById(R.id.tv_last_random);

        handler.post(randomTask);
    }

    //Saves data for pop-up
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String dateTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
        ).format(new Date());

        outState.putInt(RANDOM_KEY, currentRandom);
        outState.putString(TIME_KEY, dateTime);
    }
    //This restores data
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int lastRandom = savedInstanceState.getInt(RANDOM_KEY, 0);
        String savedDateTime = savedInstanceState.getString(TIME_KEY);

        tvLastRandom.setText("Last Random: " + lastRandom);

        Toast.makeText(
                this,
                "Orientation changed at: " + savedDateTime,
                Toast.LENGTH_LONG
        ).show();
    }

    //reset on destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(randomTask);
    }
}
