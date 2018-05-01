package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {

    DatabaseHelperItem databaseHelper;
    DatabaseHelperItem mDatabaseHelper = new DatabaseHelperItem(this);
    Button bAddItem,bViewInv,bAddCat, bHomepage;
    EditText editText2,TFcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        editText2 = (EditText) findViewById(R.id.editText2);
        bAddItem = (Button) findViewById(R.id.bAddItem);
        bViewInv = (Button) findViewById(R.id.bViewInv);
        bHomepage = (Button) findViewById(R.id.btnHomepageI);
        databaseHelper = new DatabaseHelperItem(this);

        bViewInv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(Inventory.this,ViewListContents.class);
                startActivity(intent);

            }

        });

        bAddItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newEntry = editText2.getText().toString();

                System.out.println("This is newEntry: " + newEntry);
                if (editText2.length() != 0) {
                    AddData(newEntry);
                    editText2.setText("");

                } else {
                    Toast.makeText(Inventory.this, "You must put something in the text field!", Toast.LENGTH_SHORT).show();

                }
            }

        });



        bHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inventory.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
        public void AddData(String item1){
            boolean insertData = databaseHelper.addData(item1,"0","0"," / / ","medium","");

            Cursor data = mDatabaseHelper.getListContents();
            final ArrayList<String> nameList = new ArrayList<>();
            while(data.moveToNext()){

                nameList.add(data.getString(1));

            }
            for(int i = 0;i < nameList.size()-1;i++){
               if (item1.equals(nameList.get(i)))
               {
                   Toast.makeText(Inventory.this, "WARNING: This item is already in your inventory!", Toast.LENGTH_LONG).show();
               }
            }
            if(insertData == false)
            {
                Toast.makeText(Inventory.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }




}

