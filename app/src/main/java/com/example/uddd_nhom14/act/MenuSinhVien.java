package com.example.uddd_nhom14.act;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.dbclass.DatabaseHelper;

public class MenuSinhVien extends AppCompatActivity {

    Button btnDangKyPhong, btnGiaHanPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_sinh_vien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
    }
    @SuppressLint("Range")
    public void getWidget(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        btnDangKyPhong = findViewById(R.id.btnDangKyPhong);
        btnGiaHanPhong = findViewById(R.id.btnGiaHanPhong);
        btnGiaHanPhong.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            assert bundle != null;
            Cursor cursor = db.query(DatabaseHelper.RENTLIST_TABLE_NAME, null, DatabaseHelper.COLUMN_USERNAME + " = ?", new String[] {bundle.getString("username")}, null, null, null);
            if (cursor.moveToFirst()) Toast.makeText(MenuSinhVien.this, cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ROOMNUMBER)), Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(MenuSinhVien.this, "Chưa có", Toast.LENGTH_LONG).show();
            }
            cursor.close();
            db.close();
        });
    }

}