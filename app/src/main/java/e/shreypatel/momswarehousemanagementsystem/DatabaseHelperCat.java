package e.shreypatel.momswarehousemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shreypatel on 4/4/18.
 */

public class DatabaseHelperCat extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "categories.db";
    private static final String TABLE_NAME = "categories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CAT = "category";


    public DatabaseHelperCat(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "CREATE TABLE categories (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "category TEXT)";
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        String query2 = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(query2);
        onCreate(db);
    }

    public boolean addData(String category){
        System.out.println("category: " + category);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CAT,category);

        long result = db.insert(TABLE_NAME,null,contentValues);
        System.out.println("this is the result " + result);
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
