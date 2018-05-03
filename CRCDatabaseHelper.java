package edu.utep.cs.cs4330.crccards;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseErrorHandler;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

/**
 * Created by Carlos Fajardo on 4/29/2018.
 */

public class CRCDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "CRCDatabaseHelper";

    private static final String TABLE_NAME = "CRC_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Class Name";
    private static final String COL3 = "Res1";
    private static final String COL4 = "Res2";
    private static final String COL5 = "Res3";
    private static final String COL6 = "Collab1";
    private static final String COL7 = "Collab2";
    private static final String COL8 = "Collab3";


    public CRCDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + "TEXT" + COL3 + "TEXT" + COL4 + "TEXT"+ COL5 + "TEXT"+ COL6 + "TEXT"+ COL7 +" TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String className, String res1, String res2, String res3, String collab1, String collab2, String collab3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, className);
        contentValues.put(COL3, res1);
        contentValues.put(COL4, res2);
        contentValues.put(COL5, res3);
        contentValues.put(COL6, collab1);
        contentValues.put(COL7, collab2);
        contentValues.put(COL8, collab3);

        Log.d(TAG, "addData: Adding " + className + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the Class that matches the name passed in
     * @param className
     * @return
     */
    public Cursor getItemID(String className){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + className + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the CRC card fields
     * @param newClassName
     * @param res1
     * @param res2
     * @param res3
     * @param collab1
     * @param collab2
     * @param collab3
     * @param oldClassName
     */
    public void updateName(String newClassName, String res1, String res2, String res3,
                           String collab1, String collab2, String collab3, String oldClassName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newClassName + "' WHERE " + COL2 + " = '" + oldClassName + "'" +
                " UPDATE " + COL3 + " = '" + res1 + "'" +
                " UPDATE " + COL4 + " = '" + res2 + "'" +
                " UPDATE " + COL5 + " = '" + res3 + "'" +
                " UPDATE " + COL6 + " = '" + collab1 + "'" +
                " UPDATE " + COL7 + " = '" + collab2 + "'" +
                " UPDATE " + COL8 + " = '" + collab3 + "'" ;

        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newClassName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param className
     */
    public void deleteName(String className){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL2 + " = '" + className + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + className + " from database.");
        db.execSQL(query);
    }

}
