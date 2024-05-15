package com.example.uddd_nhom14;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuSinhVien extends AppCompatActivity {

    Button btnDangKyPhong, btnGiaHanPhong, btnQuanLyPhieu;
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
    public void getWidget(){
        btnDangKyPhong = findViewById(R.id.btnDangKyPhong);
        btnGiaHanPhong = findViewById(R.id.btnGiaHanPhong);
        btnQuanLyPhieu = findViewById(R.id.btnQuanLyPhieu);
        btnGiaHanPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}