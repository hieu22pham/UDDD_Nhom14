package com.example.uddd_nhom14.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class CapNhatTTSV extends AppCompatActivity {

    EditText edtName, edtEmail, edtPhone, edtPassword, edtConfirmPassword;
    Button btnUpdate;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_ttsv);
        getWidget();
    }
    @SuppressLint("Range")
    public void getWidget(){
        edtName = findViewById(R.id.edtName); edtName.setEnabled(false);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnUpdate = findViewById(R.id.btnUpdate);

        try {
            DatabaseHelper db = new DatabaseHelper(this);

            Cursor usrCur = db.getSession();
            if (usrCur.moveToFirst()) username = usrCur.getString(usrCur.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));

            Cursor profileCur = db.getProfileInfo(username);
            if (profileCur.moveToFirst()) {
                String name = profileCur.getString(profileCur.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                        email = profileCur.getString(profileCur.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)),
                        sdt = profileCur.getString(profileCur.getColumnIndex(DatabaseHelper.COLUMN_SDT));
                edtName.setText(name);
                edtEmail.setText(email);
                edtPhone.setText(sdt);
            }
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}