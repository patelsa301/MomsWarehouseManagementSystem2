package e.shreypatel.momswarehousemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shreypatel on 4/4/18.
 */

public class DatabaseHelperList extends SQLiteOpenHelper{
    // private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "list.db";
    public static final String TABLE_NAME = "list";
    public static final String COLUMN_ID = "id";
    public static final String ITEM = "item";


    public DatabaseHelperList(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "CREATE TABLE list (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
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
}
