package com.example.uddd_nhom14.act;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uddd_nhom14.R;

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
    public void getWidget(){
        btnDangKyPhong = findViewById(R.id.btnDangKyPhong);
        btnGiaHanPhong = findViewById(R.id.btnGiaHanPhong);
        btnGiaHanPhong.setOnClickListener(v -> {

        });
    }

}