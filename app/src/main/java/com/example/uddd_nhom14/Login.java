package com.example.uddd_nhom14;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnDangNhap;
    CheckBox ckbLuuThongTin;
    private int role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        initAccountsDatabase();
        getWidget();
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveLoginState();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean save = preferences.getBoolean("save", false);
        if (save) {
            edtUsername.setText(preferences.getString("tenDangNhap", ""));
            edtPassword.setText(preferences.getString("matKhau", ""));
            ckbLuuThongTin.setChecked(preferences.getBoolean("save", false));
        }
    }
    public void saveLoginState() {
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("tenDangNhap", edtUsername.getText().toString());
        editor.putString("matKhau", edtPassword.getText().toString());
        editor.putBoolean("save", ckbLuuThongTin.isChecked());
        editor.apply();
    }
    public void getWidget(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        ckbLuuThongTin = findViewById(R.id.ckbLuuThongTin);
        btnDangNhap.setOnClickListener(v -> {
            saveLoginState();
            String username = edtUsername.getText()+"";
            String password = edtPassword.getText()+"";
            if (authenticateUser(username, password)) {
                if (role == 0) {
                    Intent intent = new Intent(Login.this, MenuSinhVien.class);
                    startActivity(intent);
                }
                if (role == 1) {
                    Intent intent = new Intent(Login.this, MenuQuanTri.class);
                    startActivity(intent);
                }
            }
            else {
                Toast.makeText(Login.this, "Ủa alo xem lại tài khoản", Toast.LENGTH_LONG).show();
            }
        });
    }
    @SuppressLint("Range")
    private boolean authenticateUser(String username, String password) {
        try {
            AccountsDatabaseHelper dbHelper = new AccountsDatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] columns = {AccountsDatabaseHelper.COLUMN_USERNAME}; // lấy ra cột này, null thì lấy hết
            String selection = AccountsDatabaseHelper.COLUMN_USERNAME + " = ? AND " + AccountsDatabaseHelper.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(AccountsDatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

            int count = cursor.getCount();
            if (count > 0){
                Cursor cursor1 = db.query(AccountsDatabaseHelper.TABLE_USERS, null, selection, selectionArgs, null, null, null);
                if (cursor1.moveToFirst()) {
                    int columnIndex = cursor1.getColumnIndex(AccountsDatabaseHelper.COLUMN_ROLE);
//                    Log.e("TT", columnIndex +"");
                    this.role = cursor1.getInt(columnIndex);
//                    Log.e("T", role+"");
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

    public void initAccountsDatabase() {
        AccountsDatabaseHelper dbHelper = new AccountsDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AccountsDatabaseHelper.COLUMN_USERNAME, "d");
        values.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "d");
        values.put(AccountsDatabaseHelper.COLUMN_ROLE, 1); // 0 user 1 admin
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values);
        ContentValues values1 = new ContentValues();
        values1.put(AccountsDatabaseHelper.COLUMN_USERNAME, "a");
        values1.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "a");
        values1.put(AccountsDatabaseHelper.COLUMN_ROLE, 0);
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values1);
        ContentValues values2 = new ContentValues();
        values2.put(AccountsDatabaseHelper.COLUMN_USERNAME, "b");
        values2.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "b");
        values2.put(AccountsDatabaseHelper.COLUMN_ROLE, 1);
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values2);
        ContentValues values3 = new ContentValues();
        values3.put(AccountsDatabaseHelper.COLUMN_USERNAME, "c");
        values3.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "c");
        values3.put(AccountsDatabaseHelper.COLUMN_ROLE, 0);
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values3);
        ContentValues values4 = new ContentValues();
        values4.put(AccountsDatabaseHelper.COLUMN_USERNAME, "admin");
        values4.put(AccountsDatabaseHelper.COLUMN_PASSWORD, "123");
        values4.put(AccountsDatabaseHelper.COLUMN_ROLE, 1);
        db.insert(AccountsDatabaseHelper.TABLE_USERS, null, values4);

        db.close();
    }
}