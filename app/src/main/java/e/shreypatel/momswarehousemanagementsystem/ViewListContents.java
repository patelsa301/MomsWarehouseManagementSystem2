package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shreypatel on 4/3/18.
 */

public class ViewListContents extends AppCompatActivity {

DatabaseHelperItem mDatabaseHelper;

    private ListView mListView;
    private static final String TAG = "ShopListDataActivity";
    private ArrayAdapter adapter;
    private ArrayList<String> listDataName = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        mListView= (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelperItem(this);

        EditText theFilter = (EditText)findViewById(R.id.searchInv);

        populateListView();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listDataName);
        mListView.setAdapter(adapter);

        theFilter.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {

            }
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                (ViewListContents.this).adapter.getFilter().filter(charSequence);
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
            listData.add(data.getString(3));
            listData.add(data.getString(4));
            listData.add(data.getString(5));
            listData.add(data.getString(6));
        }
        for(String ld: listData)
        {
            System.out.println(ld);
        }

        for(int i = 0; i < listData.size()-5;i=i+6)
        {
            listDataName.add(listData.get(i));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listDataName);
        mListView.setAdapter(adapter);

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
                    String price = listData.get(num+1);
                    String expire = listData.get(num+2);
                    String usage = listData.get(num+3);
                    String notes = listData.get(num+4);
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ViewListContents.this, EditInventory.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    editScreenIntent.putExtra("quantity",quant);
                    editScreenIntent.putExtra("price",price);
                    editScreenIntent.putExtra("expiration",expire);
                    editScreenIntent.putExtra("usage",usage);
                    editScreenIntent.putExtra("notes",notes);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(ViewListContents.this, "No ID associated with that name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}