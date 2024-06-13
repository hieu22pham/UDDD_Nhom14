package com.example.uddd_nhom14.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uddd_nhom14.R;
public class MenuQuanTri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_quan_tri);
        Button duyet = findViewById(R.id.btnXemVaDuyetPhieuDangKy);

        duyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MenuQuanTri.this,DuyetPhieu.class);
                startActivity(I);
            }
        });


        // Find the button
        Button btnQuanLyPhong = findViewById(R.id.btnQuanLyPhong);

        // Set onClickListener for the button
        btnQuanLyPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the QuanLyPhong activity
                Intent intent = new Intent(MenuQuanTri.this, QuanLyPhong.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}


