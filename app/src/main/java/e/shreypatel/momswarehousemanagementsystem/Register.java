package e.shreypatel.momswarehousemanagementsystem;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegisterClick(View v)
    {
        if(v.getId()==R.id.bCreateAccount)
        {
            EditText email = (EditText)findViewById(R.id.TFemailreg);
            EditText pass = (EditText)findViewById(R.id.TFpass);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String emailStr = email.getText().toString();
            String passStr = pass.getText().toString();
            String pass2Str = pass2.getText().toString();

            if(!passStr.equals(pass2Str))
            {
                //pop up message
                Toast passPopup = Toast.makeText(Register.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                passPopup.show();
            }
            else {
                //insert into database
                Contact c = new Contact();
                c.setEmail(emailStr);
                c.setPassword(passStr);
                helper.insertContact(c);

                Toast passPopup = Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT);
                passPopup.show();

            }
        }
    }


}
