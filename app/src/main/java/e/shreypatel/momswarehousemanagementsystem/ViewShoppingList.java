package e.shreypatel.momswarehousemanagementsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shreypatel on 4/4/18.
 */

public class ViewShoppingList extends AppCompatActivity{
    DatabaseHelperList databaseHelper;
    //   private static final String TAG = "ListDataActivity";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        ListView listView = (ListView) findViewById(R.id.listView);
        databaseHelper = new DatabaseHelperList(this);


        ArrayList<String> theList = new ArrayList<>();
        Cursor data = databaseHelper.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(ViewShoppingList.this, "The database was empty", Toast.LENGTH_LONG).show();

        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
