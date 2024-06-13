package com.example.uddd_nhom14.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;

public class XacNhanDuyet extends AppCompatActivity {
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    private int requestId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_duyet);

        // Retrieve the selected item from the intent
        String selectedItem = getIntent().getStringExtra("selectedItem");

        // Parse the selectedItem to extract the details
        String[] parts = selectedItem.split("\n");

//        request4", 1, 0));
        String requestId = parts[0].split(":")[1].trim();
        String requestType = parts[1].split(":")[1].trim();
        String requestStatus = parts[2].split(":")[1].trim();
        String requestUsername = parts[3].split(":")[1].trim();
        String roomNumber = parts[4].split(":")[1].trim();
        String roomarea = parts[5].split(":")[1].trim();
//        Cursor cursor = databaseHelper.getRoomByRoomNumberAndAreaCursor(roomNumber, roomarea);
//        int price = cursor.getInt(5);
//        int type = cursor.getInt(4);
        // Display the details in the TextViews

        String type="";
        String price="";
        Cursor cursor = dbHelper.getRoomByRoomNumberAndAreaCursor(roomNumber,roomarea);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                type = cursor.getString(4);
                price = cursor.getString(5);
            }


        }
        String ki="";
        String nam="";
        cursor = dbHelper.getRentByUsername(requestUsername);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ki= cursor.getString(4);
                nam = cursor.getString(5);
            }

        }


        TextView textViewRequestId = findViewById(R.id.textView);
        TextView textViewRequestType = findViewById(R.id.txt_loaiphong);
        TextView textkhu = findViewById(R.id.txt_khu);
        TextView textViewRequestUsername = findViewById(R.id.txtuser);
        TextView textViewRoomNumber = findViewById(R.id.txtphong);

        textViewRequestId.setText("ID Phiếu: " + requestId);
        textViewRequestType.setText(requestType);
        textkhu.setText(roomarea);
        textViewRequestUsername.setText("Người làm:\n"+ requestUsername);
        textViewRoomNumber.setText("Phòng: " + roomNumber);

        // Initialize other TextViews
        int tang= Integer.parseInt(roomNumber);
        tang= tang/100;

        TextView textViewTang = findViewById(R.id.txt_tang);

        TextView textViewTienIch = findViewById(R.id.txt_tienich);
        TextView textViewKhi = findViewById(R.id.txt_vaoo);
        TextView textViewNam = findViewById(R.id.txt_ketthuc);
        TextView textViewTien = findViewById(R.id.txt_thanhtien);

        // Set any additional information as needed
        // For now, set placeholders or default values
        textViewTang.setText(tang+" ");

        String tienich="";
        if(price.equals("700000")){
            tienich="nóng lạnh";
        }
        else if(price.equals("800000")){
            tienich="nóng lạnh, điều hòa";
        }
        else{
            tienich="nóng lạnh, điều hòa, máy giặt";
        }
        textViewTienIch.setText(tienich);
        textViewKhi.setText(ki);
        textViewNam.setText(nam);
        textViewTien.setText(price);

        // Set button listeners for accept and reject actions
        Button btnChapNhan = findViewById(R.id.btnChapnhan);
        Button btnTuChoi = findViewById(R.id.btn_tuchoi);

        btnChapNhan.setOnClickListener(v -> {

            boolean isUpdated = dbHelper.updateRequestStatus(Integer.parseInt(requestId), 1);
            if (isUpdated) {
                Toast.makeText(XacNhanDuyet.this, "Đã chấp nhận phiếu thành công", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(XacNhanDuyet.this, "Cập nhật trạng thái thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btnTuChoi.setOnClickListener(v -> {
            boolean isUpdated = dbHelper.updateRequestStatus(Integer.parseInt(requestId), -1);
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
