package com.example.uddd_nhom14.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;
import com.example.uddd_nhom14.entity.Account;
import com.example.uddd_nhom14.entity.Rent;
import com.example.uddd_nhom14.entity.Room;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnDangNhap;
    CheckBox ckbLuuThongTin;
    private int role, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        initAccountsDatabase();
        initRoomsDatabase();
        addSomeFakeRent();
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
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putInt("id", id);
                    Intent intent = new Intent(Login.this, MenuSinhVien.class);
                    intent.putExtra("bundle", bundle);
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
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            Cursor cursor = dbHelper.getAccountByUsernameAndPasswordCursor(username, password);
            int count = cursor.getCount();
            if (count > 0){
                if (cursor.moveToFirst()) {
                    this.role = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ROLE));
                    this.id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                }
            }

            cursor.close();
            dbHelper.close();

            return count > 0;
        } catch (Exception e){
            Toast.makeText(Login.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void initAccountsDatabase() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.addAccountToDatabase(new Account("a", "a", "Tạ Thị Lạng", 0));
        db.addAccountToDatabase(new Account("b", "b",  1));
        db.addAccountToDatabase(new Account("admin", "123456", "", 1));
        db.addAccountToDatabase(new Account("e", "e", "Kha Tỷ Cân", 0));

        db.close();
    }

    public void initRoomsDatabase () {
        DatabaseHelper db = new DatabaseHelper(this);

        for (int i = 501; i <= 510; i++) {
            db.addRoomToDatabase(new Room(i+"", "A", 5+"", 800000, 4));
        }
        for (int i = 511; i <= 520; i++) {
            db.addRoomToDatabase(new Room(i+"", "B", 5+"", 800000, 4));
        }
        for (int i = 521; i <= 530; i++) {
            db.addRoomToDatabase(new Room(i+"", "C", 5+"", 800000, 4));
        }
        db.close();
    }

    public void addSomeFakeRent() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.addARentToDatabase(new Rent("a", 501+"", "A", "01-06-2024"));
        db.addARentToDatabase(new Rent("e", 501+"", "A", "01-06-2024"));

        db.close();
    }

}