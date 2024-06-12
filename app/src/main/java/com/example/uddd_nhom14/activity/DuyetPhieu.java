package com.example.uddd_nhom14.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.uddd_nhom14.R;
import com.example.uddd_nhom14.database.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class DuyetPhieu extends AppCompatActivity {

    private ListView listViewRents;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyet_phieu);

        listViewRents= findViewById(R.id.listViewRents);
        databaseHelper = new DatabaseHelper(this);

        List<String> rentList = getAllRents();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rentList
        );
        listViewRents.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private List<String> getAllRents() {
        List<String> rentList = new ArrayList<>();
        Cursor cursor = databaseHelper.getReadableDatabase().query(
                DatabaseHelper.RENTLIST_TABLE_NAME,
                null, null, null, null, null, null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
                String roomNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROOMNUMBER));
                String area = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AREA));
                String endDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_));

                String rentInfo = "Username: " + username + "\nRoom Number: " + roomNumber +
                        "\nArea: " + area + "\nEnd Date: " + endDate;
                rentList.add(rentInfo);
            }
            cursor.close();
        }
        return rentList;
    }
}