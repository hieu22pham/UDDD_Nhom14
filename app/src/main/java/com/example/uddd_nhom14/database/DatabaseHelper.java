package com.example.uddd_nhom14.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uddd_nhom14.entity.Account;
import com.example.uddd_nhom14.entity.Rent;
import com.example.uddd_nhom14.entity.Room;

import java.util.ArrayList;
import java.util.List;

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

//Thông số bảng rentlish
    public static final String RENTLIST_TABLE_NAME = "rentlist";
    public static final String COLUMN_RENTID = "roomid";
    public static final String COLUMN_ENDDATE = "enddate";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
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
                + COLUMN_ENDDATE + " TEXT, "
                + "CONSTRAINT fk1 FOREIGN KEY(" + COLUMN_USERNAME + ") "
                + " REFERENCES " + ACCOUNT_TABLE_NAME + " (" + COLUMN_USERNAME + "), "
                + "CONSTRAINT fk2 FOREIGN KEY(" + COLUMN_ROOMNUMBER + ", " + COLUMN_AREA + ") "
                + " REFERENCES " + ROOM_TABLE_NAME + " (" + COLUMN_ROOMNUMBER + ", " + COLUMN_AREA  + ")  )";
        db.execSQL(createTableQuery3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ROOM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RENTLIST_TABLE_NAME);
        onCreate(db);
    }

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
        cv.put(COLUMN_ENDDATE, r.getEnddate());
        db.insert(RENTLIST_TABLE_NAME, null, cv);
        db.update(RENTLIST_TABLE_NAME, cv, COLUMN_USERNAME + " = ?", new String[] {r.getUsername()});
    }

    public void deleteRoomFromDatabase(String roomnumber, String area) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ROOMNUMBER + " = ? AND " + COLUMN_AREA + " = ?";
        String[] whereArgs = {roomnumber, area};
        db.delete(ROOM_TABLE_NAME, whereClause, whereArgs);
    }

    public List<Room> getRoom() {
        List<Room> roomList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + ROOM_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        int roomIdIndex = cursor.getColumnIndex(COLUMN_ROOMID);
        int roomNumberIndex = cursor.getColumnIndex(COLUMN_ROOMNUMBER);
        int areaIndex = cursor.getColumnIndex(COLUMN_AREA);
        int floorIndex = cursor.getColumnIndex(COLUMN_FLOOR);
        int roomTypeIndex = cursor.getColumnIndex(COLUMN_ROOMTYPE);
        int roomPriceIndex = cursor.getColumnIndex(COLUMN_ROOMPRICE);

        if (cursor.moveToFirst()) {
            do {
                int roomId = (roomIdIndex >= 0) ? cursor.getInt(roomIdIndex) : -1;
                String roomNumber = (roomNumberIndex >= 0) ? cursor.getString(roomNumberIndex) : "";
                String area = (areaIndex >= 0) ? cursor.getString(areaIndex) : "";
                String floor = (floorIndex >= 0) ? cursor.getString(floorIndex) : "";
                int roomType = (roomTypeIndex >= 0) ? cursor.getInt(roomTypeIndex) : 0;
                int roomPrice = (roomPriceIndex >= 0) ? cursor.getInt(roomPriceIndex) : 0;

                Room room = new Room(roomNumber, area, floor, roomPrice, roomType);
                roomList.add(room);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return roomList;
    }

    public List<Room> getRoomByArea(String area) {
        List<Room> roomList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + ROOM_TABLE_NAME + " WHERE " + COLUMN_AREA + " = ?";
        String[] selectionArgs = { area };
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        int roomIdIndex = cursor.getColumnIndex(COLUMN_ROOMID);
        int roomNumberIndex = cursor.getColumnIndex(COLUMN_ROOMNUMBER);
        int floorIndex = cursor.getColumnIndex(COLUMN_FLOOR);
        int roomTypeIndex = cursor.getColumnIndex(COLUMN_ROOMTYPE);
        int roomPriceIndex = cursor.getColumnIndex(COLUMN_ROOMPRICE);

        if (cursor.moveToFirst()) {
            do {
                int roomId = (roomIdIndex >= 0) ? cursor.getInt(roomIdIndex) : -1;
                String roomNumber = (roomNumberIndex >= 0) ? cursor.getString(roomNumberIndex) : "";
                String floor = (floorIndex >= 0) ? cursor.getString(floorIndex) : "";
                int roomType = (roomTypeIndex >= 0) ? cursor.getInt(roomTypeIndex) : 0;
                int roomPrice = (roomPriceIndex >= 0) ? cursor.getInt(roomPriceIndex) : 0;

                Room room = new Room(roomNumber, area, floor, roomPrice, roomType);
                roomList.add(room);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return roomList;
    }

    public void updateRoom(Room r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ROOMNUMBER, r.getRoomnumber());
        cv.put(COLUMN_AREA, r.getArea());
        cv.put(COLUMN_FLOOR, r.getFloor());
        cv.put(COLUMN_ROOMTYPE, r.getRoomtype());
        cv.put(COLUMN_ROOMPRICE, r.getRoomprice());
        db.update(ROOM_TABLE_NAME, cv, COLUMN_ROOMNUMBER + " = ? AND " + COLUMN_AREA + " = ?", new String[] {r.getRoomnumber(),r.getArea()});
    }

    public List<Account> getRegistrantsByRoom(String roomNumber, String area) {
        List<Account> registrants = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RENTLIST_TABLE_NAME + " r "
                + "INNER JOIN " + ACCOUNT_TABLE_NAME + " a "
                + "ON r." + COLUMN_USERNAME + " = a." + COLUMN_USERNAME + " "
                + "WHERE r." + COLUMN_ROOMNUMBER + " = ? AND r." + COLUMN_AREA + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{roomNumber, area});

        int usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
        int roleIndex = cursor.getColumnIndex(COLUMN_ROLE);

        if (cursor.moveToFirst()) {
            do {
                if (usernameIndex >= 0 && nameIndex >= 0 && passwordIndex >= 0 && roleIndex >= 0) {
                    String username = cursor.getString(usernameIndex);
                    String name = cursor.getString(nameIndex);
                    String password = cursor.getString(passwordIndex);
                    int role = cursor.getInt(roleIndex);

                    Account account = new Account(username, password, name, role);
                    registrants.add(account);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return registrants;
    }


    public void removeRegistrant(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_USERNAME + " = ?";
        String[] whereArgs = {account.getUsername()};
        db.delete(RENTLIST_TABLE_NAME, whereClause, whereArgs);
    }

    public Boolean checkRoomExist(String roomNumber, String roomArea) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ROOMNUMBER + " = ? AND " + COLUMN_AREA + " = ?";
        String[] selectionArgs = {roomNumber, roomArea};
        Cursor cursor = db.query(ROOM_TABLE_NAME, null, selection, selectionArgs, null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void addRoom(Room r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ROOMNUMBER, r.getRoomnumber());
        cv.put(COLUMN_AREA, r.getArea());
        cv.put(COLUMN_FLOOR, r.getFloor());
        cv.put(COLUMN_ROOMTYPE, r.getRoomtype());
        cv.put(COLUMN_ROOMPRICE, r.getRoomprice());
        db.insert(ROOM_TABLE_NAME, null, cv);
    }

}
