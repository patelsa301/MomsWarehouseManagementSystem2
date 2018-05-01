package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class EditShoppingList extends AppCompatActivity {

    private static final String TAG = "EditShoppingList";

    private Button btnSave,btnDelete, btnUpdateView;
    private EditText editable_item, item_quant;

    DatabaseHelperList mDatabaseHelper;

    private String selectedName, selectedQuant;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_shopping_list);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdateView = (Button) findViewById(R.id.btnUpdateList);
        editable_item = (EditText) findViewById(R.id.editable_item_list);
        item_quant = (EditText) findViewById(R.id.editable_item_quant);
        mDatabaseHelper = new DatabaseHelperList(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedQuant = receivedIntent.getStringExtra("quantity");


        //set the text to show the current selected name
        editable_item.setText(selectedName);
        item_quant.setText(selectedQuant);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                String quant = item_quant.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                    toastMessage("item edited");
                    if(!quant.equals("")) {
                        mDatabaseHelper.updateQuant(quant, selectedID, selectedQuant);
                    }
                    else {
                        mDatabaseHelper.updateQuant(quant, selectedID, "0");
                    }
                }else{
                    toastMessage("You must enter a name");
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                item_quant.setText("");
                toastMessage("removed from database");
            }
        });

        btnUpdateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditShoppingList.this, ShoppingList.class);
                startActivity(intent);
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

