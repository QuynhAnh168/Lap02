package com.example.lap02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DateAndTimePicker extends AppCompatActivity {
    EditText edtDate, edtTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time_picker);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateAndTimePicker.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        edtDate.setText("Date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker();
                datePickerDialog.show();
                //getDateDialog(mYear, mMonth, mDay);
            }
        });
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog time = new TimePickerDialog(DateAndTimePicker.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        String formatHour = "";
                        String formatMinute;
                        if (hour < 10) {
                            formatHour = "0" + hour;
                        } else {
                            formatHour = "" + hour;
                        }
                        if (minute < 10) {
                            formatMinute = "0" + minute;
                        } else {
                            formatMinute = "" + minute;
                        }
                        edtTime.setText("Time: "+formatHour + " : " + formatMinute);
                    }
                }, mHour, mMinute, true);

                time.setTitle("Select time");
                time.show();
                //getTimeDialog(mHour, mMinute);

            }
        });

    }

//    public void getDateDialog(int mYear, int mMonth, int mDay) {
//
//        if (mYear > 0) {
//            DatePickerDialog datePickerDialog = new DatePickerDialog(DateAndTimePicker.this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                      int dayOfMonth) {
//                    // TODO Auto-generated method stub
//                    edtDate.setText("Date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                }
//            }, mYear, mMonth, mDay);
//            datePickerDialog.getDatePicker();
//            datePickerDialog.show();
//
//        } else {
//
//        }
//
//    }

//    public void getTimeDialog(int mHour, int mMinute) {
//        TimePickerDialog time = new TimePickerDialog(DateAndTimePicker.this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
//                String formatHour = "";
//                String formatMinute;
//                if (hour < 10) {
//                    formatHour = "0" + hour;
//                } else {
//                    formatHour = "" + hour;
//                }
//                if (minute < 10) {
//                    formatMinute = "0" + minute;
//                } else {
//                    formatMinute = "" + minute;
//                }
//                edtTime.setText("Time: "+formatHour + " : " + formatMinute);
//            }
//        }, mHour, mMinute, true);
//
//        time.setTitle("Select time");
//        time.show();
//    }


}