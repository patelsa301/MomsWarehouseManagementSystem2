package e.shreypatel.momswarehousemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by shreypatel on 4/3/18.
 */

public class DatabaseHelperItem extends SQLiteOpenHelper {

   // private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "inventory.db";
    public static final String TABLE_NAME = "inventory";
    public static final String COLUMN_ID = "id";
    public static final String ITEM = "item";


    public DatabaseHelperItem(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "CREATE TABLE inventory (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "item TEXT)";
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        String query2 = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(query2);
        onCreate(db);
    }

    public boolean addData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM,item1);

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

//    public Cursor getItemID(String name)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + ITEM + " = '" + name + "'";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }

    /*public void updateName(String newName, int id, String oldname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + ITEM + " = '" + newName + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + ITEM + " = '" + oldname + "'";

        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }
    public void deleteName(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + ITEM + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    } */
}
