package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShoppingList extends AppCompatActivity {

    DatabaseHelperList databaseHelper;
    Button bAddList,bViewList, btnHomepage;
    EditText TFAddList, quant_List;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        quant_List = findViewById(R.id.quant_List);
        TFAddList = (EditText) findViewById(R.id.TFAddList);
        bAddList = (Button) findViewById(R.id.bAddList);
        bViewList = (Button) findViewById(R.id.bViewList);
        btnHomepage = (Button) findViewById(R.id.btnHomepage);
        databaseHelper = new DatabaseHelperList(this);

//        bViewList.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(ShoppingList.this,ViewShoppingList.class);
//                startActivity(intent);
//
//            }
//
//        });

        bAddList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newEntry = TFAddList.getText().toString();
                String newEntryQuant = quant_List.getText().toString();
                System.out.println("This is newEntry: " + newEntry);
                if (TFAddList.length() != 0 || quant_List.length() != 0) {
                    AddData(newEntry, newEntryQuant);
                    TFAddList.setText("");
                    quant_List.setText("");

                } else {
                    Toast.makeText(ShoppingList.this, "You must put something in the text field!", Toast.LENGTH_SHORT).show();

                }
            }

        });

        //ADDED***
        bViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingList.this, ViewShoppingList.class);
                startActivity(intent);
            }
        });

        btnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingList.this, Homepage.class);
                startActivity(intent);
            }
        });
        //***
    }

    public void AddData(String newEntry, String newEntryQuant){
        boolean insertData = databaseHelper.addData(newEntry, newEntryQuant);

        if(insertData==true)
        {
            Toast.makeText(ShoppingList.this, "Successfully Entered Data!", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(ShoppingList.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

}

