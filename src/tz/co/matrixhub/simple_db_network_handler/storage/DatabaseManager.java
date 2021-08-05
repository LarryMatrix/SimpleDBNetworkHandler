package tz.co.matrixhub.simple_db_network_handler.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    // Database Init
    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "any_db_name";

    // Table Names
    private static final String ANY_TABLE_NAME = "any_table_name";

    // Shared columns
    public static final String KEY_TABLE_ID = "table_id";
    public static final String KEY_ID = "id";

    // any_table_name Table -> Column Names
    public static final String KEY_COLUMN_NAME = "any_column_name";


    private static final String CREATE_TABLE_TABLE_NAME = "CREATE TABLE "
            + ANY_TABLE_NAME + "("
            + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + KEY_COLUMN_NAME + " varchar(200) NOT NULL);";

    public DatabaseManager(Context context) {
        // init of database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_STATION);
        // and so on goes down here
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATION);
        // and so on goes down here

        //create new tables
        onCreate(db);
    }

    public boolean addEntry(int user,
                            String string_value) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, user);
        contentValues.put(KEY_COLUMN_NAME, string_value);

        return sqLiteDatabase.insert(ANY_TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getEntry() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + ANY_TABLE_NAME;

        Log.e(LOG, selectQuery);
        return sqLiteDatabase.rawQuery(selectQuery, null);
    }

    public boolean updateEntry(int table_id, String string_value) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_COLUMN_NAME, string_value);

        return sqLiteDatabase.update(ANY_TABLE_NAME, contentValues, KEY_TABLE_ID + "=?", new String[]{String.valueOf(table_id)}) > 0;
    }

    public int countEntry() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String countQuery = "SELECT  * FROM " + ANY_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        Log.e(LOG, countQuery);
        Log.e(LOG, String.valueOf(count));

        return count;
    }

    public void clearDB() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete(ANY_TABLE_NAME, null, null);
    }

}
