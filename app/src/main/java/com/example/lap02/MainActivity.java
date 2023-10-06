package com.example.lap02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtTen;
    Button btnNhap;
    TextView txtPosition, txtValue;
    ListView lvItem;
    ArrayAdapter<String> adapter;
    int viTri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTen = findViewById(R.id.edtTen);
        btnNhap = findViewById(R.id.btnNhap);
        txtPosition = findViewById(R.id.txtPosition);
        txtValue = findViewById(R.id.txtValue);
        lvItem = findViewById(R.id.lvItem);
        List<String> arrItem = new ArrayList<>();
        arrItem.add("Teo");
        arrItem.add("Ty");
        arrItem.add("Bin");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrItem);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viTri = i;
                String item = arrItem.get(i);
                edtTen.setText(item);
                txtPosition.setText(i + "");
                txtValue.setText(item);
            }
        });
        lvItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrItem.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edtTen.getText().toString();
                if (item.equals(""))
                {
                    edtTen.setError("Bạn chưa nhập dữ liệu");
                }
                else
                {
                    arrItem.add(item);
                    edtTen.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}