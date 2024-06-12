package com.example.uddd_nhom14.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;
import com.example.uddd_nhom14.entity.itemListQLP;
import com.example.uddd_nhom14.entity.quanLyPhieuAdapter;

import java.util.ArrayList;

public class quanLyPhieu extends AppCompatActivity {
    ListView listViewQuanLyPhieu;
    ArrayList<itemListQLP> myList;
    quanLyPhieuAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_ly_phieu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listViewQuanLyPhieu = findViewById(R.id.listViewQuanLyPhieu);
        myList = new ArrayList<>();
        itemListQLP item0 = new itemListQLP("STT", "Mã phiếu", "Tên phiếu", "Trạng thái");
        myList.add(item0);
        //lấy dữ liệu từ table request
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getRequestList();

        if (cursor != null && cursor.moveToFirst()) {
            int i = 1;
            do {
                int loaiPhieu =  cursor.getInt(1);
                int trangThai = cursor.getInt(7);
                String loaiPhieuStr, trangThaiStr;
                if(loaiPhieu  == 1)
                {
                    loaiPhieuStr = "Gia hạn";
                }
                else if(loaiPhieu  == 2){
                    loaiPhieuStr = "Đăng ký mới";
                }
                else {
                    loaiPhieuStr = "Loại phiếu lạ";

                }

                if(trangThai == - 1)
                {
                    trangThaiStr = "Không duyệt";
                }
                else if(trangThai == 0)
                {
                    trangThaiStr = "Chưa duyệt";
                }
                else if(trangThai == 1)
                {
                    trangThaiStr = "Đã duyệt";
                }
                else {
                    trangThaiStr = "Trạng thái lạ";

                }
                itemListQLP item = new itemListQLP(String.valueOf(i) , cursor.getString(0),loaiPhieuStr,trangThaiStr);
                myList.add(item);
                i++;
            } while (cursor.moveToNext());

            cursor.close();
        }
        dbHelper.close();

        // dữ liệu ảo
//        itemListQLP item1 = new itemListQLP("1", "phieu1", " ten1", "1");
//        itemListQLP item2 = new itemListQLP("2", "phieu2", " ten2", "2");
//        itemListQLP item3 = new itemListQLP("3", "phieu3", " ten3", "3");
//        itemListQLP item4 = new itemListQLP("4", "phieu4", " ten4", "4");
//        itemListQLP item5 = new itemListQLP("5", "phieu5", " ten5", "5");
//        itemListQLP item6 = new itemListQLP("6", "phieu6", " ten6", "6");
//        myList.add(item1);
//        myList.add(item2);
//        myList.add(item3);
//        myList.add(item4);
//        myList.add(item5);
//        myList.add(item6);

        myadapter = new quanLyPhieuAdapter(quanLyPhieu.this, R.layout.layout_item_quan_ly_phieu, myList);
        listViewQuanLyPhieu.setAdapter(myadapter);

    }
}