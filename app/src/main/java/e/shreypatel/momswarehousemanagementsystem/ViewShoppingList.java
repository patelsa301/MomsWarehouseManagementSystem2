package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shreypatel on 4/4/18.
 */

public class ViewShoppingList extends AppCompatActivity{

DatabaseHelperList mDatabaseHelper;
    private ArrayList<String> listDataName = new ArrayList<>();
    private ListView mListView;
    private static final String TAG = "ShopListDataActivity";
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list_layout);
        mListView= (ListView) findViewById(R.id.listView);
        EditText theFilter = (EditText)findViewById(R.id.searchFilter);

        mDatabaseHelper = new DatabaseHelperList(this);

        populateListView();
        //ArrayAdapter<String> searchAdapter;
        //searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listDataName);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listDataName);
        mListView.setAdapter(adapter);

        theFilter.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {

            }
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                (ViewShoppingList.this).adapter.getFilter().filter(charSequence);
            }
            public void afterTextChanged(Editable editable)
            {

            }
        });

    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getListContents();
        final ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));

            listData.add(data.getString(2));
        }
        for(String ld: listData)
        {
            System.out.println(ld);
        }
        //create the list adapter and set the adapter

        for(int i = 0; i < listData.size()-1;i=i+2)
        {
            listDataName.add(listData.get(i));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listDataName);
        mListView.setAdapter(adapter);



        //search items
//        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String text) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String text) {
//                searchAdapater.getFilter().filter(text);
//                return false;
//            }
//        });

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){

                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    int num= 0;
                    for(i = 0; i < listData.size(); i++)
                    {
                        String nameToCheck = listData.get(i);
                        if(nameToCheck.equals(name)) {
                            num = i + 1;
                        }
                    }
                    String quant = listData.get(num);
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ViewShoppingList.this, EditShoppingList.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    editScreenIntent.putExtra("quantity", quant);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(ViewShoppingList.this, "No ID associated with this item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
