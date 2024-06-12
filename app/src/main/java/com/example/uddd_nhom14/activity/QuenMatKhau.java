package com.example.uddd_nhom14.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;
import com.example.uddd_nhom14.entity.Account;
import com.example.uddd_nhom14.entity.Profile;

import java.io.File;

public class QuenMatKhau extends AppCompatActivity {
    Button btnXacNhanQMK;
    EditText edtTenDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quen_mat_khau);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnXacNhanQMK = findViewById(R.id.btnXacNhanQMK);
        edtTenDN = findViewById(R.id.edtTenDN);
        btnXacNhanQMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = edtTenDN.getText().toString();
                int idAcc = getIdByUserName( username);
                if(idAcc> -1) {
                    Account acc = getAccById(idAcc);
                    String phoneNumber = getPhoneNumberByUserName(username);
                    if (phoneNumber.length() > 0) {
                        Log.d("Thông tin acc", acc.getName() + acc.getUsername());

                        // Activity đầu tiên
                        Intent intent = new Intent(QuenMatKhau.this, otpForm.class);
                        Bundle myBundle = new Bundle();
                        myBundle.putSerializable("account", acc);
                        myBundle.putInt("id", idAcc);
                        myBundle.putString("phoneNumber", phoneNumber);
                        intent.putExtra("myPackage", myBundle);

                        startActivity(intent);
                    }

                }

            }
        });
    }
    //
    public String getPhoneNumberByUserName(String username)
    {
        try
        {

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Cursor cursor = dbHelper.getProfileInfo(username);
            if (cursor != null)
            {
                // Kiểm tra xem Cursor có chứa bất kỳ hàng nào không
                if (cursor.moveToFirst())
                {
                    String phoneNumber = cursor.getString(2);
                    return phoneNumber;
                } else
                {
                    Toast.makeText(QuenMatKhau.this, "Không tìm thấy số điện thoại của tài khoản trên hệ thống!", Toast.LENGTH_LONG).show();
                    return "";
                }

            }
            cursor.close();
            dbHelper.close();

        } catch (Exception e)
        {
            Toast.makeText(QuenMatKhau.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Lỗi đa này", e.toString());

        }
        return "";
    }
    public Account getAccById(int id)
    {
        try
        {

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Cursor cursor = dbHelper.getAccountInfoById(id);
            if (cursor != null)
            {
                // Kiểm tra xem Cursor có chứa bất kỳ hàng nào không
                if (cursor.moveToFirst())
                {
                    Account acc = new Account(cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getInt(4));
                    return acc;
                } else
                {
                    Toast.makeText(QuenMatKhau.this, "Không tìm thấy tài khoản trên hệ thống!", Toast.LENGTH_LONG).show();
                    return null;
                }

            }
            cursor.close();
            dbHelper.close();

        } catch (Exception e)
        {
            Toast.makeText(QuenMatKhau.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Lỗi đa này", e.toString());

        }
        return null;
    }
    public int getIdByUserName(String username)
    {
        try
        {

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Cursor cursor = dbHelper.getAccountByUsernameCursor(username);
            if (cursor != null)
            {
                // Kiểm tra xem Cursor có chứa bất kỳ hàng nào không
                if (cursor.moveToFirst())
                {
                    int id = Integer.parseInt(cursor.getString(0));
                    return id;
                } else
                {
                    Toast.makeText(QuenMatKhau.this, "Không tìm thấy tài khoản trên hệ thống!", Toast.LENGTH_LONG).show();
                    return -1;
                }

            }
            cursor.close();
            dbHelper.close();

        } catch (Exception e)
        {
            Toast.makeText(QuenMatKhau.this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Lỗi đa này", e.toString());

        }
        return -1;
    }

    public void initAccountsDatabase() {
        DatabaseHelper db = new DatabaseHelper(this);
        //db.addAccountToDatabase(new Account( "khanh", "12345", "Đặng Khánh", 1));
        db.addAccountToDatabase(new Account( "Khai", "12345", "Đặng Khải", 1));

//        db.addAnProfile(new Profile("khanh", "0393511358", "bangbang2k3kul@gmail.com"));
//        db.addAnProfile(new Profile("Khai", "0385946895", "abc@gmail.com"));
//        db.addAnProfile(new Profile("bao", "3", "abc@gmail.com"));
//        db.addAnProfile(new Profile("dung", "2", "abc@gmail.com"));

        db.close();
    }

}