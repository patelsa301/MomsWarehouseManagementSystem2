package e.shreypatel.momswarehousemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shreypatel on 4/4/18.
 */

public class DatabaseHelperList extends SQLiteOpenHelper{
    // private static final int DATABASE_VERSION = 2;
    //public static final String DATABASE_NAME = "list.db";
    public static final String TABLE_NAME = "list";
    public static final String COLUMN_ID = "id";
    public static final String ITEM = "item";
    public static final String QUANTITY = "quantity";


    private static final String TAG = "DatabaseHelperList";


    public DatabaseHelperList(Context context)
    {
        super(context, TABLE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEM + " TEXT , "  + QUANTITY + " TEXT )";
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        String query2 = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(query2);
        onCreate(db);
    }


    public boolean addData(String item, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM,item);
        contentValues.put(QUANTITY, quantity);

        //ADDED ***
        Log.d(TAG, "addData: Adding " + item + " and " + quantity + " to " + TABLE_NAME);
        //****
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }

    //ADDED ***
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME +
                " WHERE " + ITEM + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + ITEM +
                " = '" + newName + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + ITEM + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateQuant(String newQuant, int id, String oldQuant){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + QUANTITY +
                " = '" + newQuant + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + QUANTITY + " = '" + oldQuant + "'";
        Log.d(TAG, "updateQuant: query: " + query);
        Log.d(TAG, "updateQuant: Setting quantity to " + newQuant);
        db.execSQL(query);
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COLUMN_ID + " = '" + id + "'" +
                " AND " + ITEM + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
    //***
}
