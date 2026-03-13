  package com.example.assignment3;

  import androidx.appcompat.app.AppCompatActivity;

  import android.os.Bundle;
  import android.view.View;
  import android.widget.*;

  public class MainActivity extends AppCompatActivity {

      EditText display;

      double firstValue = 0;
      String operator = "";

      @Override
      protected void onCreate(Bundle savedInstanceState) {

          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          display = findViewById(R.id.display);

          GridLayout grid = findViewById(R.id.grid);

          for(int i=0;i<grid.getChildCount();i++){

              Button btn = (Button) grid.getChildAt(i);

              btn.setOnClickListener(buttonListener);
          }
      }

      private View.OnClickListener buttonListener = v -> {

          Button btn = (Button) v;
          String text = btn.getText().toString();

          if("0123456789".contains(text)){ /* If any of this numbers are present in the text*/

              display.append(text);
          }

          else if(text.equals("=")){

              double secondValue = Double.parseDouble(display.getText().toString());

              double result = 0;

              switch(operator){

                  case "+": result = firstValue + secondValue; break;
                  case "-": result = firstValue - secondValue; break;
                  case "*": result = firstValue * secondValue; break;
                  case "/": result = firstValue / secondValue; break;
                  case "%": result = firstValue % secondValue; break;
              }

              display.setText(String.valueOf(result));
          }

          else{

              firstValue = Double.parseDouble(display.getText().toString());
              operator = text;
              display.setText("");
          }
      };
  }