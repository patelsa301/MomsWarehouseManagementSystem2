package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inventory extends AppCompatActivity {

    DatabaseHelperItem databaseHelper;
    DatabaseHelperCat helper;
    Button bAddItem,bViewInv,bAddCat;
    EditText editText2,TFcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        editText2 = (EditText) findViewById(R.id.editText2);
        TFcategory = (EditText) findViewById(R.id.TFcategory);
        bAddItem = (Button) findViewById(R.id.bAddItem);
        bViewInv = (Button) findViewById(R.id.bViewInv);
        bAddCat = (Button) findViewById(R.id.bAddCat);
        databaseHelper = new DatabaseHelperItem(this);
        helper = new DatabaseHelperCat(this);

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

        bAddCat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newEntry = TFcategory.getText().toString();
                System.out.println("This is newEntry: " + newEntry);
                if (TFcategory.length() != 0) {
                    AddCatData(newEntry);
                    TFcategory.setText("");

                } else {
                    Toast.makeText(Inventory.this, "You must put something in the text field!", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
        public void AddData(String newEntry){
            boolean insertData = databaseHelper.addData(newEntry);

            if(insertData==true)
            {
                Toast.makeText(Inventory.this, "Successfully Entered Data!", Toast.LENGTH_LONG).show();
            }else
            {
                Toast.makeText(Inventory.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }

    public void AddCatData(String newEntry){
        System.out.println("newEntry: " + newEntry);
        boolean insertData = helper.addData(newEntry);

        if(insertData==true)
        {
            Toast.makeText(Inventory.this, "Successfully Entered The Category: " + newEntry, Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(Inventory.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}

