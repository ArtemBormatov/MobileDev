  package com.example.assignment31;

  import androidx.appcompat.app.AppCompatActivity;
  import android.graphics.Color;
  import android.os.Bundle;
  import android.text.Editable;
  import android.text.TextWatcher;
  import android.view.View;
  import android.widget.Button;
  import android.widget.EditText;
  import android.widget.TextView;
  import java.util.List;

  public class MainActivity extends AppCompatActivity {

      private EditText etUserName, etComment, etSearch;
      private TextView tvEntries;
      private Button btnSubmit, btnSearch;
      private BlogEntryHandler handler;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          handler = new BlogEntryHandler();

          etUserName = findViewById(R.id.et_user_name);
          etComment = findViewById(R.id.et_comment);
          etSearch = findViewById(R.id.et_search);
          tvEntries = findViewById(R.id.tv_entries);
          btnSubmit = findViewById(R.id.btn_submit);
          btnSearch = findViewById(R.id.btn_search);
          // Reset highlight when typing
          etUserName.addTextChangedListener(new TextWatcher() {
              @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
              @Override public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                  etUserName.setBackgroundColor(Color.TRANSPARENT);
              }
              @Override public void afterTextChanged(Editable s) {}
          });

          etComment.addTextChangedListener(new TextWatcher() {
              @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
              @Override public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                  etComment.setBackgroundColor(Color.TRANSPARENT);
              }
              @Override public void afterTextChanged(Editable s) {}
          });

          // Submit Button
          btnSubmit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  boolean error = false;

                  if (etUserName.getText().toString().trim().isEmpty()) {
                      etUserName.setBackgroundColor(Color.rgb(255, 200, 200));
                      error = true;
                  }

                  if (etComment.getText().toString().trim().isEmpty()) {
                      etComment.setBackgroundColor(Color.rgb(255, 200, 200));
                      error = true;
                  }

                  if (error) return;

                  BlogEntry entry = new BlogEntry(etUserName.getText().toString().trim(),
                          etComment.getText().toString().trim());
                  handler.addEntry(entry);
                  etComment.setText("");
                  etUserName.setText("");
                  displayEntries(handler.getAllEntries());
              }
          });

          // Search Button
          btnSearch.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String query = etSearch.getText().toString().trim();
                  if (query.isEmpty()) {
                      displayEntries(handler.getAllEntries());
                  } else {
                      List<BlogEntry> results = handler.searchByText(query);
                      displayEntries(results);
                  }
              }
          });
      }

      private void displayEntries(List<BlogEntry> entries) {
          StringBuilder sb = new StringBuilder();
          int counter = 1;
          for (BlogEntry e : entries) {
              sb.append(counter++).append(". ").append(e.toString());
          }
          tvEntries.setText(sb.toString());
      }
  }