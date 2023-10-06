package com.example.lap02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BaseListView extends AppCompatActivity {
    EditText edtItem;
    Button btnThem, btnSua;
    ListView lvItem;
    ArrayAdapter<String>adapter;
    int viTri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list_view);
        edtItem = findViewById(R.id.edtItem);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        lvItem = findViewById(R.id.lvItem);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("CẢNH BÁO");
        //alertDialogBuilder.setIcon(R.drawable.q)
        alertDialogBuilder.setMessage("BẠN CÓ MUỐN XÓA");
        alertDialogBuilder.setCancelable(false);
        List<String> arrItem = new ArrayList<>();
        arrItem.add("Android");
        arrItem.add("PHP");
        arrItem.add("NodeJS");
        arrItem.add("Javascript");
        arrItem.add("Java");
        arrItem.add("C#");
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrItem);
        lvItem.setAdapter(adapter);
        // Hien edt
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = arrItem.get(i);
                viTri = i;
                edtItem.setText(item);
            }
        });
        lvItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertDialogBuilder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseListView.this, "Bạn đã không đồng ý", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrItem.remove(viTri);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(BaseListView.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edtItem.getText().toString();

                if(item.equals(""))
                {
                    edtItem.setError("Bạn chưa nhập dữ liệu");
                }
                else
                {
                    arrItem.add(item);
                    edtItem.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(BaseListView.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edtItem.getText().toString();
                arrItem.set(viTri,item);
                edtItem.setText("");;
                adapter.notifyDataSetChanged();
                Toast.makeText(BaseListView.this, "Sửa dữ liệu thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}