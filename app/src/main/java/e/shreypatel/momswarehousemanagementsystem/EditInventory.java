package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditInventory extends AppCompatActivity {

        private static final String TAG = "EditInventory";

        private Button btnSaveInv,btnDeleteInv, btnUpdateInv;
        private EditText editable_item_inv, editable_item_price, editable_item_notes, editable_item_quant,editable_item_usage , editable_item_ed;

        DatabaseHelperItem mDatabaseHelper;

        private String selectedName;
        private String selectedQuant;
        private String selectedPrice;
        private String selectedExpirationDate;
        private String selectedNotes;
        private String selectedUsage;


        private int selectedID;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_inventory);
            btnSaveInv = (Button) findViewById(R.id.btnSaveInv);
            btnDeleteInv = (Button) findViewById(R.id.btnDeleteInv);
            btnUpdateInv = (Button) findViewById(R.id.btnUpdateInv);
            editable_item_inv = (EditText) findViewById(R.id.editable_item_inv);
            editable_item_quant = (EditText) findViewById(R.id.editable_item_quant_inv);
            editable_item_price = (EditText) findViewById(R.id.editable_item_price);
            editable_item_notes = (EditText) findViewById(R.id.editable_text_notes);
            editable_item_usage = (EditText) findViewById(R.id.editText5);
            editable_item_ed = (EditText) findViewById(R.id.editable_text_ed);



            mDatabaseHelper = new DatabaseHelperItem(this);

            //get the intent extra from the ListDataActivity
            Intent receivedIntent = getIntent();

            //now get the itemID we passed as an extra
            selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

            //now get the name we passed as an extra
            selectedName = receivedIntent.getStringExtra("name");
            selectedQuant = receivedIntent.getStringExtra("quantity");
            selectedPrice = receivedIntent.getStringExtra("price");
            selectedExpirationDate = receivedIntent.getStringExtra("expiration");
            selectedNotes = receivedIntent.getStringExtra("notes");
            selectedUsage = receivedIntent.getStringExtra("usage");


            //set the text to show the current selected name
            editable_item_inv.setText(selectedName);
            editable_item_quant.setText(selectedQuant);
            editable_item_notes.setText(selectedNotes);
            editable_item_price.setText(selectedPrice);
            editable_item_usage.setText(selectedUsage);
            editable_item_ed.setText(selectedExpirationDate);



            btnSaveInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String item = editable_item_inv.getText().toString();
                    String quant = editable_item_quant.getText().toString();
                    String notes = editable_item_notes.getText().toString();
                    String price = editable_item_price.getText().toString();
                    String usage = editable_item_usage.getText().toString();
                    String ed = editable_item_ed.getText().toString();
                    if(!item.equals("")){
                        mDatabaseHelper.updateName(item,selectedID,selectedName);
                        if(!quant.equals(""))
                        {
                            mDatabaseHelper.updateQuant(quant,selectedID,selectedQuant);
                        }

                        else{
                            mDatabaseHelper.updateQuant(quant, selectedID, "0");
                        }

                        if(!notes.equals("")){
                            mDatabaseHelper.updateNotes(notes, selectedID, selectedNotes);
                        }
                        else{
                            mDatabaseHelper.updateNotes(notes,selectedID,"");
                        }

                        if(!price.equals(""))
                        {
                            mDatabaseHelper.updatePrice(price,selectedID, selectedPrice);
                        }
                        else{
                            mDatabaseHelper.updatePrice(price, selectedID,"0");
                        }

                        if(!usage.equals(""))
                        {
                            usageToast(usage);
                            mDatabaseHelper.updateUsage(usage, selectedID, selectedUsage);
                        }

                        else{
                            mDatabaseHelper.updateUsage(usage, selectedID, "medium");
                        }

                        if(!ed.equals(""))
                        {
                            mDatabaseHelper.updateExpiration(ed, selectedID, selectedExpirationDate);
                        }

                        else{
                            mDatabaseHelper.updateExpiration(ed, selectedID, " / / ");
                        }


                    }

                        else{
                        toastMessage("You must enter a name");
                    }
                }
            });

            btnDeleteInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabaseHelper.deleteName(selectedID,selectedName);
                    editable_item_inv.setText("");
                    editable_item_quant.setText("");
                    editable_item_notes.setText("");
                    editable_item_price.setText("");
                    editable_item_usage.setText("");
                    editable_item_ed.setText("");
                    toastMessage("removed from database");
                }
            });

            btnUpdateInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(e.shreypatel.momswarehousemanagementsystem.EditInventory.this, Inventory.class);
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


        public void usageToast(String usage)
        {
            if(usage.equals("high"))
            {

                Toast.makeText(EditInventory.this, "This product will approximately run out in less than one week", Toast.LENGTH_LONG).show();
            }

            else if (usage.equals("medium"))
            {
                Toast.makeText(EditInventory.this, "This product will approximately run out in two weeks", Toast.LENGTH_LONG).show();
            }
            else if (usage.equals("low")) {
                Toast.makeText(EditInventory.this, "This product will approximately run out in more than three weeks", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(EditInventory.this, "Invalid Usage: please enter high, medium, or low for usage", Toast.LENGTH_LONG).show();
            }
        }
    }
