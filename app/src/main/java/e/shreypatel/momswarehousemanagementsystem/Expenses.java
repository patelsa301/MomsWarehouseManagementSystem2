package e.shreypatel.momswarehousemanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Expenses extends AppCompatActivity {

    DatabaseHelperItem mDatabaseHelper = new DatabaseHelperItem(this);
    private TextView editable_item_expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
    }

    protected double calcExpenses(){
        double total = 0;
        Cursor data = mDatabaseHelper.getListContents();
        final ArrayList<String> listData = new ArrayList<>();
        final ArrayList<Double> listDataDouble = new ArrayList<>();
        while(data.moveToNext()){

            listData.add(data.getString(3));

        }
        for(int i = 0;i < listData.size();i++){
            String temp = listData.get(i);
            double num = Double.parseDouble(temp);
            listDataDouble.add(num);
        }

        for(int i = 0;i < listDataDouble.size();i++){
            total += listDataDouble.get(i);
        }
        return total;
    }

    public void onExpensesClick(View v){
        if(v.getId() == R.id.btnExp) {
            double refreshedExpenses = calcExpenses();
            refreshedExpenses = Math.round(refreshedExpenses * 100.0) / 100.0;


            String expenses = Double.toString(refreshedExpenses);
            editable_item_expenses = (TextView) findViewById(R.id.total);
            editable_item_expenses.setText("$" + expenses);
        }
    }

    public void onReturnClick(View v){
        if(v.getId() == R.id.btnReturnExp) {
            Intent intent = new Intent(Expenses.this, Homepage.class);
            startActivity(intent);
        }
    }


}
