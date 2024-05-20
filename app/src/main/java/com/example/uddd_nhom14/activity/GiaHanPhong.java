package com.example.uddd_nhom14.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;

public class GiaHanPhong extends AppCompatActivity {

    TextView tvNguoiLamPhieu, tvSoPhong, tvGiaPhong, tvLoaiPhong, tvKhu, tvTang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gia_han_phong);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
    }
    @SuppressLint("Range")
    public void getWidget() {
        tvNguoiLamPhieu = findViewById(R.id.tvNguoiLamPhieu);
        tvSoPhong = findViewById(R.id.tvSoPhong);
        tvGiaPhong = findViewById(R.id.tvGiaPhong);
        tvLoaiPhong = findViewById(R.id.tvLoaiPhong);
        tvKhu = findViewById(R.id.tvKhu);
        tvTang = findViewById(R.id.tvTang);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        DatabaseHelper db = new DatabaseHelper(this);
        assert bundle != null;
        Cursor cursor = db.getRentByUsername(bundle.getString("username"));
        if (cursor.moveToFirst()) {
            //Lấy thông tin sinh viên từ bảng user nhờ cột username của bảng rentlist
            String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
            Cursor cursor1 = db.getAccountByUsernameCursor(username);
            if (cursor1.moveToFirst()) {
                String name = cursor1.getString(cursor1.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                tvNguoiLamPhieu.setText(name);
            }
            //Lấy thông tin phòng từ bảng rentlist cột roomnumber và area
            String roomnumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ROOMNUMBER));
            String roomarea = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AREA));
            Cursor cursor2 = db.getRoomByRoomNumberAndAreaCursor(roomnumber, roomarea);
            if (cursor2.moveToFirst()) {
                tvSoPhong.setText(roomnumber);
                long roomprice = cursor2.getLong(cursor2.getColumnIndex(DatabaseHelper.COLUMN_ROOMPRICE));
                tvGiaPhong.setText(String.valueOf(roomprice));
                int roomtype = cursor2.getInt(cursor2.getColumnIndex(DatabaseHelper.COLUMN_ROOMTYPE));
                tvLoaiPhong.setText(String.valueOf(roomtype));
                int floor = cursor2.getInt(cursor2.getColumnIndex(DatabaseHelper.COLUMN_FLOOR));
                tvTang.setText(String.valueOf(floor));
                tvKhu.setText(roomarea);
            }

        }

        db.close();
    }
}