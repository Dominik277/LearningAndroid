package hr.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS theNewUsers (name VARCHAR, age INT(4), id INTEGER PRIMARY KEY)");
            sqLiteDatabase.execSQL("INSERT INTO theNewUsers (name,age) VALUES ('Tom',21)");
            sqLiteDatabase.execSQL("INSERT INTO theNewUsers (name,age) VALUES ('Nick',47)");
            sqLiteDatabase.execSQL("INSERT INTO theNewUsers (name,age) VALUES ('Nick',37)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers",null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();

            while (c != null){
                Log.i("UserResults-event",c.getString(nameIndex));
                Log.i("UserResults-age",Integer.toString(c.getInt(ageIndex)));
                Log.i("userResults-id",Integer.toString(c.getInt(idIndex)));
            }

            c.moveToNext();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}