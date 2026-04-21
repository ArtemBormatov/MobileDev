package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerEventType;
    private Button btnSelectDate, btnSelectTime, btnSubmit;
    private RecyclerView recyclerView;

    private List<Event> eventList;
    private EventAdapter adapter;

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        spinnerEventType = findViewById(R.id.spinner_event_type);
        btnSelectDate = findViewById(R.id.btn_select_date);
        btnSelectTime = findViewById(R.id.btn_select_time);
        btnSubmit = findViewById(R.id.btn_submit_event);
        recyclerView = findViewById(R.id.recyclerView_events);

        //Spinner
        String[] eventTypes = {"Meeting", "Birthday", "Work", "School"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                eventTypes
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventType.setAdapter(spinnerAdapter);

        //RecyclerView setup
        eventList = new ArrayList<>();
        adapter = new EventAdapter(eventList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //Date Picker Button
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int y, int m, int d) {

                                selectedDate = d + "/" + (m + 1) + "/" + y;

                                Toast.makeText(MainActivity.this,
                                        "Selected date: " + selectedDate,
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        year, month, day
                );

                // Show dialog
                datePickerDialog.show();
            }
        });

        //Time Picker Button
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get current time
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Create TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int h, int m) {

                                selectedTime = String.format("%02d:%02d", h, m);

                                Toast.makeText(MainActivity.this,
                                        "Selected time: " + selectedTime,
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        hour, minute, true
                );

                // Show dialog
                timePickerDialog.show();
            }
        });

        //Submit Button
        //spinner is a selector
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = spinnerEventType.getSelectedItem().toString();

                // Validation
                if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please select date and time",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create event and add to list
                Event newEvent = new Event(type, selectedDate, selectedTime);
                eventList.add(newEvent);

                // Notify adapter to refresh data
                adapter.notifyDataSetChanged();
            }
        });
    }
}