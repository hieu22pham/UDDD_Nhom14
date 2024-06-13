package com.example.uddd_nhom14.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;

public class XacNhanDuyet extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private int requestId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_duyet);
        databaseHelper = new DatabaseHelper(this);
        // Retrieve the selected item from the intent
        String selectedItem = getIntent().getStringExtra("selectedItem");

        // Parse the selectedItem to extract the details
        String[] parts = selectedItem.split("\n");

        String requestId = parts[0].split(":")[1].trim();
        String requestType = parts[1].split(":")[1].trim();
        String requestStatus = parts[2].split(":")[1].trim();
        String requestUsername = parts[3].split(":")[1].trim();
        String roomNumber = parts[4].split(":")[1].trim();

        // Display the details in the TextViews
        TextView textViewRequestId = findViewById(R.id.textView);
        TextView textViewRequestType = findViewById(R.id.txt_loaiphong);
        TextView textkhu = findViewById(R.id.txt_khu);
        TextView textViewRequestUsername = findViewById(R.id.txtuser);
        TextView textViewRoomNumber = findViewById(R.id.txtphong);

        textViewRequestId.setText("ID Phiếu: " + requestId);
        textViewRequestType.setText(requestType);
        textkhu.setText("A");
        textViewRequestUsername.setText("Người làm:\n"+ requestUsername);
        textViewRoomNumber.setText("Phòng: " + roomNumber);

        // Initialize other TextViews
        int tang= Integer.parseInt(roomNumber);
        tang= tang/100;
        tang++;
        TextView textViewTang = findViewById(R.id.txt_tang);
        TextView textViewUuTien = findViewById(R.id.txt_doituonguutien);
        TextView textViewTienIch = findViewById(R.id.txt_tienich);
        TextView textViewNgayVaoO = findViewById(R.id.txt_vaoo);
        TextView textViewNgayKetThuc = findViewById(R.id.txt_ketthuc);
        TextView textViewTongTien = findViewById(R.id.txt_thanhtien);

        // Set any additional information as needed
        // For now, set placeholders or default values
        textViewTang.setText(tang+" ");
        textViewUuTien.setText("N/A");
        textViewTienIch.setText("N/A");
        textViewNgayVaoO.setText("N/A");
        textViewNgayKetThuc.setText("N/A");
        textViewTongTien.setText("N/A");

        // Set button listeners for accept and reject actions
        Button btnChapNhan = findViewById(R.id.btnChapnhan);
        Button btnTuChoi = findViewById(R.id.ưbtn_tuchoi);

        btnChapNhan.setOnClickListener(v -> {

            boolean isUpdated = databaseHelper.updateRequestStatus(Integer.parseInt(requestId), 1);
            if (isUpdated) {
                Toast.makeText(XacNhanDuyet.this, "Đã chấp nhận phiếu thành công", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(XacNhanDuyet.this, "Cập nhật trạng thái thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btnTuChoi.setOnClickListener(v -> {
            boolean isUpdated = databaseHelper.updateRequestStatus(Integer.parseInt(requestId), -1);
            if (isUpdated) {
                Toast.makeText(XacNhanDuyet.this, "Đã từ chôi phiếu thành công", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(XacNhanDuyet.this, "Cập nhật trạng thái thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
