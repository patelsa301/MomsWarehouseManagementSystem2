package e.shreypatel.momswarehousemanagementsystem;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.bRegister) {

            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
        if(v.getId() == R.id.bLogin)
        {
            EditText a = (EditText)findViewById(R.id.TFemail);
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String str = a.getText().toString();
            String str2 = b.getText().toString();

            String password = helper.searchPass(str);
            if(str2.equals(password))
            {
                Intent i = new Intent(MainActivity.this, Homepage.class);
                i.putExtra("Email", str);
                i.putExtra("Password", str2);
                startActivity(i);
            }
            else
            {
                //pop up message
                Toast temp = Toast.makeText(MainActivity.this, "Email and Username are invalid", Toast.LENGTH_SHORT);
                temp.show();
            }


        }
    }
}
