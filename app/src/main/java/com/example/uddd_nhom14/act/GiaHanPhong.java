package com.example.uddd_nhom14.act;

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
import com.example.uddd_nhom14.dbclass.DatabaseHelper;

public class GiaHanPhong extends AppCompatActivity {

    TextView tvNguoiLamPhieu;
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
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        DatabaseHelper db = new DatabaseHelper(this);
        assert bundle != null;
        Cursor cursor = db.getAccountByUsernameCursor(bundle.getString("username"));
        if (cursor.moveToFirst()) {
            tvNguoiLamPhieu.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
        }

        db.close();
    }
}