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
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String EXPIRATION = "expiration";
    public static final String USAGE = "usage";
    public static final String NOTES = "notes";
//    public static final String DEPLETION = "depletion";

    private static final String TAG = "DatabaseHelperInv";

    public DatabaseHelperItem(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "CREATE TABLE inventory (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ITEM + " TEXT , "  + QUANTITY + " TEXT , price TEXT , expiration TEXT , usage TEXT , notes TEXT )";
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        String query2 = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(query2);
        onCreate(db);
    }

    public boolean addData(String item1, String quantity, String price, String expiration, String usage, String notes){
        //ADDED****
        Log.d(TAG, "addData: Adding " + item1 + " to " + TABLE_NAME);
        //****
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM,item1);
        contentValues.put(QUANTITY,quantity);
        contentValues.put(PRICE,price);
        contentValues.put(EXPIRATION,expiration);
        contentValues.put(USAGE,usage);
        contentValues.put(NOTES,notes);

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

    public void updateExpiration(String newExpiration, int id, String oldExpiration){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + EXPIRATION +
                " = '" + newExpiration + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + EXPIRATION + " = '" + oldExpiration + "'";
        Log.d(TAG, "updateExpiration: query: " + query);
        Log.d(TAG, "updateExpiration: Setting expiration date to " + newExpiration);
        db.execSQL(query);
    }

    public void updatePrice(String newPrice, int id, String oldPrice){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + PRICE +
                " = '" + newPrice + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + PRICE + " = '" + oldPrice + "'";
        Log.d(TAG, "updatePrice: query: " + query);
        Log.d(TAG, "updatePrice: Setting price to " + newPrice);
        db.execSQL(query);
    }

    public void updateUsage(String newUsage, int id, String oldUsage){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + USAGE +
                " = '" + newUsage + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + USAGE + " = '" + oldUsage + "'";
        Log.d(TAG, "updateUsage: query: " + query);
        Log.d(TAG, "updateUsage: Setting usage to " + newUsage);
        db.execSQL(query);
    }

    public void updateNotes(String newNotes, int id, String oldNotes){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + NOTES +
                " = '" + newNotes + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
                " AND " + NOTES + " = '" + oldNotes + "'";
        Log.d(TAG, "updateNotes: query: " + query);
        Log.d(TAG, "updateNotes: Setting notes to " + newNotes);
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
