package com.example.lap02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuanLyCongViecHangTuan extends AppCompatActivity {
    EditText edtCongViec, edtNoiDung;
    TextView txtDate, txtTime;
    Button btnDate, btnTime, btnThemCV;
    ListView lvDanhSach;
    int viTri = -1;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_cong_viec_hang_tuan);
        // Ánh xạ
        edtCongViec = findViewById(R.id.edtCongViec);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnThemCV = findViewById(R.id.btnThemCV);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("CẢNH BÁO");
        //alertDialogBuilder.setIcon(R.drawable.q)
        alertDialogBuilder.setMessage("BẠN CÓ MUỐN XÓA");
        alertDialogBuilder.setCancelable(false);

        //
        List<String> arrDS  = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrDS);
        lvDanhSach.setAdapter(adapter);
        //
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(QuanLyCongViecHangTuan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtDate.setText("Ngày HT: " + day + "/" + (month +1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker();
                datePickerDialog.show();
            }
        });
        //
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(QuanLyCongViecHangTuan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        txtTime.setText("Giờ HT: "+hour + ":" + minute);
                    }
                },hour,minute,true);
                timePickerDialog.getActionBar();
                timePickerDialog.show();
            }
        });
        //
        btnThemCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Date = txtDate.getText().toString();
                String Time = txtTime.getText().toString();
                String CV = edtCongViec.getText().toString();
                String ND = edtNoiDung.getText().toString();
                if (Date.equals(""))
                {
                    btnDate.setError("Ban chua dien ngay");
                    txtDate.setError("Bạn chưa điền ngày");
                }
                else
                if (Time.equals(""))
                {
                    txtTime.setError("Bạn chưa điền giờ");
                }
                else
                    if (CV.equals(""))
                {
                    edtCongViec.setError("Bạn chưa điền công việc");
                }
                else
                    if (ND.equals(""))
                    {
                        edtNoiDung.setError("Bạn chưa điền nội dung");
                    }
                else
                    {
                        arrDS.add("Công việc: " + CV + " - Nội dung: " + ND + " - " + Date + " - " + Time + " AM");
                        edtCongViec.setText("");
                        edtNoiDung.setText("");
                        txtDate.setText("Ngày HT: ");
                        txtTime.setText("Giờ HT: ");
                        adapter.notifyDataSetChanged();
                        Toast.makeText(QuanLyCongViecHangTuan.this, "Thêm nội dung công việc thành công!", Toast.LENGTH_SHORT).show();

                    }
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrDS.get(i);
                viTri = i;
            }
        });
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertDialogBuilder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(QuanLyCongViecHangTuan.this, "Bạn đã không đồng ý", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrDS.remove(viTri);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(QuanLyCongViecHangTuan.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });
    }
}