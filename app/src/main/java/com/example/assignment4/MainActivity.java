package com.example.assignment4;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        //TEXT
        TextView textView = new TextView(this);
        textView.setText("Type your favourite number");
        textView.setTextSize(18);
        layout.addView(textView);

        //TEXTBOX
        EditText editText = new EditText(this);
        editText.setHint("Enter number");
        layout.addView(editText);

        // Listens to press of Enter instead of a button
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                String input = editText.getText().toString();

                //! before input fixes logic (It means there is text in the input)
                if (!input.isEmpty()) {

                    int userNumber = Integer.parseInt(input);
                    int randomNumber = new Random().nextInt(100) + 1;

                    if (userNumber == randomNumber) {
                        Toast.makeText(this, "Same number. Lucky!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Not the same, random number is " + randomNumber, Toast.LENGTH_SHORT).show();
                    }

                    editText.setText("");
                } else {
                    Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
            return false;
        });

        setContentView(layout);
    }
}