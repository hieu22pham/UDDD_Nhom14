package com.example.uddd_nhom14;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnDangNhap;
    public int role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        AccountsDatabaseHelper dbHelper = new AccountsDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AccountsDatabaseHelper.COLUMN_USERNAME, "admin");
        values.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "123456");
        values.put(AccountsDatabaseHelper.COLUMN_ROLE, 1); // 0 user 1 admin
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values);
        ContentValues values1 = new ContentValues();
        values1.put(AccountsDatabaseHelper.COLUMN_USERNAME, "a");
        values1.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "a");
        values1.put(AccountsDatabaseHelper.COLUMN_ROLE, 0);
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values1);
        db.close();
        getWidget();
    }
    public void getWidget(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText()+"";
                String password = edtPassword.getText()+"";
                if (authenticateUser(username, password)) {
                    Toast.makeText(Login.this, role+"", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, MenuSinhVien.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Ủa alo xem lại tài khoản", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @SuppressLint("Range")
    private boolean authenticateUser(String username, String password) {
        try {
            AccountsDatabaseHelper dbHelper = new AccountsDatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] columns = {AccountsDatabaseHelper.COLUMN_USERNAME};
            String selection = AccountsDatabaseHelper.COLUMN_USERNAME + " = ? AND " + AccountsDatabaseHelper.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};

            Cursor cursor = db.query(AccountsDatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            if (count > 0){
                String[] columns1 = {AccountsDatabaseHelper.COLUMN_ROLE};
                Cursor cursor1 = db.query(AccountsDatabaseHelper.TABLE_USERS, columns1, selection, selectionArgs, null, null, null);
                if (cursor1.moveToFirst()) {
                    role = cursor1.getInt(cursor1.getColumnIndex(AccountsDatabaseHelper.COLUMN_ROLE));
                }
                cursor1.close();
            }

            cursor.close();
            db.close();

            return count > 0;
        } catch (Exception e){
            Toast.makeText(Login.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return false;
    }
}