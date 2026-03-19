package com.example.assignment42;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText idInput, nameInput, priceInput, amountInput;
    TextView summaryText;

    ArrayList<String> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // ScrollView (IMPORTANT requirement)
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        // ID
        idInput = new EditText(this);
        idInput.setHint("Product ID");
        layout.addView(idInput);

        // Name
        nameInput = new EditText(this);
        nameInput.setHint("Product Name");
        layout.addView(nameInput);

        // Unit Price
        priceInput = new EditText(this);
        priceInput.setHint("Unit Price");
        priceInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(priceInput);

        // Amount
        amountInput = new EditText(this);
        amountInput.setHint("Amount");
        amountInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(amountInput);

        // Submit Button
        Button submitBtn = new Button(this);
        submitBtn.setText("Submit");
        layout.addView(submitBtn);

        // Clear Button
        Button clearBtn = new Button(this);
        clearBtn.setText("Clear");
        layout.addView(clearBtn);

        // Summary TextView
        summaryText = new TextView(this);
        layout.addView(summaryText);

        // Add layout to scrollview
        scrollView.addView(layout);
        mainLayout.addView(scrollView);

        setContentView(mainLayout);

        // Submit button logic
        submitBtn.setOnClickListener(v -> {

            String id = idInput.getText().toString();
            String name = nameInput.getText().toString();
            String priceStr = priceInput.getText().toString();
            String amountStr = amountInput.getText().toString();

            //LENGTH_SHORT means that popup will show for a short time
            if (id.isEmpty() || name.isEmpty() || priceStr.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            //DOUBLE STORES DECIMAL NUMBERS
            try {
                double price = Double.parseDouble(priceStr);
                int amount = Integer.parseInt(amountStr);
                double total = price * amount;

                String product = "ID: " + id +
                        ", Name: " + name +
                        ", Total: " + total;

                products.add(product);

                updateSummary();

                clearFields();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Enter valid numbers!", Toast.LENGTH_SHORT).show();
            }
        });

        // Clear button logic
        clearBtn.setOnClickListener(v -> clearFields());
    }

    private void updateSummary() {
        summaryText.setText("");

        for (String p : products) {
            summaryText.append(p + "\n");
        }
    }

    private void clearFields() {
        idInput.setText("");
        nameInput.setText("");
        priceInput.setText("");
        amountInput.setText("");
    }
}