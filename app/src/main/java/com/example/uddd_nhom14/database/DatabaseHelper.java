package com.example.uddd_nhom14.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uddd_nhom14.entity.Account;
import com.example.uddd_nhom14.entity.Profile;
import com.example.uddd_nhom14.entity.Rent;
import com.example.uddd_nhom14.entity.Request;
import com.example.uddd_nhom14.entity.Room;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    //Thông số bảng account
    public static final String ACCOUNT_TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ROLE = "role";

    //Thông số bảng rooms
    public static final String ROOM_TABLE_NAME = "rooms";
    public static final String COLUMN_ROOMID = "roomid";
    public static final String COLUMN_ROOMNUMBER = "roomnumber";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_FLOOR = "floor";
    public static final String COLUMN_ROOMPRICE = "price";
    public static final String COLUMN_ROOMTYPE = "roomtype";

    //Thông số bảng rentlist
    public static final String RENTLIST_TABLE_NAME = "rentlist";
    public static final String COLUMN_RENTID = "roomid";
    public static final String COLUMN_KYHOC = "kyhoc";
    public static final String COLUMN_NAMHOC = "namhoc";


    //
    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String COLUMN_PROFILEID = "profileid";
    public static final String COLUMN_SDT = "sdt";
    public static final String COLUMN_EMAIL = "email";

    //request
    public static final String REQUEST_TABLE_NAME = "request";
    public static final String COLUMN_REQUESTID = "requestid";
    public static final String COLUMN_REQUESTTYPE = "requesttype"; // 1: gia hạn, 2:đk mới
    public static final String COLUMN_REQUESTSTATUS = "requeststatus";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        //Tạo bảng account
        String createTableQuery = "CREATE TABLE " + ACCOUNT_TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_ROLE + " INTEGER)";
        db.execSQL(createTableQuery);
        //Tạo bảng rooms
        String createTableQuery2 = "CREATE TABLE " + ROOM_TABLE_NAME + "("
                + COLUMN_ROOMID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ROOMNUMBER + " TEXT, "
                + COLUMN_AREA + " TEXT, "
                + COLUMN_FLOOR + " TEXT, "
                + COLUMN_ROOMTYPE + " INTEGER, "
                + COLUMN_ROOMPRICE + " INTEGER)";
        db.execSQL(createTableQuery2);
        //Tạo bảng rentlist
        String createTableQuery3 = "CREATE TABLE " + RENTLIST_TABLE_NAME + "("
                + COLUMN_RENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ROOMNUMBER + " TEXT, "
                + COLUMN_AREA + " TEXT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_KYHOC+ " TEXT, "
                + COLUMN_NAMHOC + " TEXT, "
                + "CONSTRAINT fk1 FOREIGN KEY(" + COLUMN_USERNAME + ") "
                + " REFERENCES " + ACCOUNT_TABLE_NAME + " (" + COLUMN_USERNAME + "), "
                + "CONSTRAINT fk2 FOREIGN KEY(" + COLUMN_ROOMNUMBER + ", " + COLUMN_AREA + ") "
                + " REFERENCES " + ROOM_TABLE_NAME + " (" + COLUMN_ROOMNUMBER + ", " + COLUMN_AREA  + ")  )";
        db.execSQL(createTableQuery3);

        // profile

        String createTableQuery5 = "CREATE TABLE " + PROFILE_TABLE_NAME + "("
                + COLUMN_PROFILEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_SDT + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + "CONSTRAINT fk4 FOREIGN KEY(" + COLUMN_USERNAME + ") "
                + " REFERENCES " + ACCOUNT_TABLE_NAME + " (" + COLUMN_USERNAME + ")  )";
        db.execSQL(createTableQuery5);

        //request
        String createTableQuery6 = "CREATE TABLE " + REQUEST_TABLE_NAME + "("
                + COLUMN_REQUESTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_REQUESTTYPE + " INTEGER, "
                + COLUMN_ROOMNUMBER + " TEXT, "
                + COLUMN_AREA + " TEXT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_KYHOC+ " TEXT, "
                + COLUMN_NAMHOC + " TEXT, "
                + COLUMN_REQUESTSTATUS + " INTEGER, "
                + "CONSTRAINT fk5 FOREIGN KEY(" + COLUMN_ROOMNUMBER + ", " + COLUMN_AREA + ") "
                + " REFERENCES " + ROOM_TABLE_NAME + " (" + COLUMN_ROOMNUMBER + ", " + COLUMN_AREA + "), "
                + "CONSTRAINT fk6 FOREIGN KEY(" + COLUMN_USERNAME + ") "
                + " REFERENCES " + ACCOUNT_TABLE_NAME + " (" + COLUMN_USERNAME + ")  )";
        db.execSQL(createTableQuery6);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ROOM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RENTLIST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REQUEST_TABLE_NAME);

        onCreate(db);
    }
    //.......
    // Khanh code databasse o day
    public int updatePasswordAccount(Account c, int id) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + ACCOUNT_TABLE_NAME + " SET " + COLUMN_PASSWORD + " = '" + c.getPassword() + "' WHERE " + COLUMN_ID + " = " + id;
        db.execSQL(updateQuery);
        db.close();
        return 1;
    }
    public Cursor getAccountInfoById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ACCOUNT_TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
    }



    //.......

    public Cursor getAccountByUsernameAndPasswordCursor(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        return db.query(ACCOUNT_TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }
    public Cursor getAccountByUsernameCursor(String username) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        return db.query(ACCOUNT_TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }
    public Cursor getRoomByRoomNumberAndAreaCursor(String roomnumber, String area) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ROOM_TABLE_NAME, null, COLUMN_ROOMNUMBER + " = ? AND " + COLUMN_AREA + " = ?", new String[]{roomnumber, area}, null, null, null);
    }
    public Cursor getRentByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(RENTLIST_TABLE_NAME, null, COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);
    }

    public Cursor getProfileInfo(String username) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(PROFILE_TABLE_NAME, null, COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);
    }
    public Cursor getGiaHanRequestByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(REQUEST_TABLE_NAME, null, COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);
    }
    public Cursor getRequestList(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(REQUEST_TABLE_NAME, null, null , null, null, null, null);
    }


    public void addAccountToDatabase (Account a) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, a.getUsername());
        cv.put(COLUMN_PASSWORD, a.getPassword());
        if (a.getRole() == 0) cv.put(COLUMN_NAME, a.getName());
        cv.put(COLUMN_ROLE, a.getRole());
        db.insert(ACCOUNT_TABLE_NAME, null, cv);
        db.update(ACCOUNT_TABLE_NAME, cv, COLUMN_USERNAME + " = ?", new String[] {a.getUsername()});
    }
    public void addRoomToDatabase(Room r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ROOMNUMBER, r.getRoomnumber());
        cv.put(COLUMN_AREA, r.getArea());
        cv.put(COLUMN_FLOOR, r.getFloor());
        cv.put(COLUMN_ROOMTYPE, r.getRoomtype());
        cv.put(COLUMN_ROOMPRICE, r.getRoomprice());
        db.insert(ROOM_TABLE_NAME, null, cv);
        db.update(ROOM_TABLE_NAME, cv, COLUMN_ROOMNUMBER + " = ?", new String[] {r.getRoomnumber()});
    }
    public void addARentToDatabase(Rent r) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, r.getUsername());
        cv.put(COLUMN_ROOMNUMBER, r.getRoomnumber());
        cv.put(COLUMN_AREA, r.getRoomarea());
        cv.put(COLUMN_KYHOC, r.getKyhoc());
        cv.put(COLUMN_NAMHOC, r.getNamhoc());
        db.insert(RENTLIST_TABLE_NAME, null, cv);
        db.update(RENTLIST_TABLE_NAME, cv, COLUMN_USERNAME + " = ?", new String[] {r.getUsername()});
    }
    public void addAnProfile(Profile p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, p.getUsername());
        cv.put(COLUMN_SDT, p.getSdt());
        cv.put(COLUMN_EMAIL, p.getEmail());
        db.insert(PROFILE_TABLE_NAME, null, cv);
    }
    public void addAGiaHanRequest(Request r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_REQUESTTYPE, r.getRequesttype());
        cv.put(COLUMN_USERNAME, r.getUsername());
        cv.put(COLUMN_ROOMNUMBER, r.getRoomnumber());
        cv.put(COLUMN_AREA, r.getArea());
        cv.put(COLUMN_REQUESTSTATUS, r.getRequeststatus());
        cv.put(COLUMN_NAMHOC, r.getNamhoc());
        cv.put(COLUMN_KYHOC, r.getKyhoc());
        db.insert(REQUEST_TABLE_NAME, null, cv);
        db.update(REQUEST_TABLE_NAME, cv, COLUMN_USERNAME + " = ?", new String[] {r.getUsername()});
    }
}