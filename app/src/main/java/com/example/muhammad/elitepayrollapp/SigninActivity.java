package com.example.muhammad.elitepayrollapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{

    EditText username, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        username = (EditText)findViewById(R.id.Signin_username_textinput);
        password = (EditText)findViewById(R.id.Signin_passwordd_textinput);
        login = (Button)findViewById(R.id.Signin_login_Button);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user = username.getText().toString();
        String pwd = password.getText().toString();
        if (user.equals("elitefood")){
            if(pwd.equals("1067")){
                Intent goToEmployeesList = new Intent(this, EmployeesListActivity.class);
                startActivity(goToEmployeesList);
                String msg = "Successfully Logged In!";
                Toast.makeText(this,msg,Toast.LENGTH_LONG ).show();
            }
            else{
                Toast.makeText(this,"Wrong Password!",Toast.LENGTH_LONG ).show();
            }
        }
        else{
            Toast.makeText(this,"Wrong Username!",Toast.LENGTH_LONG ).show();
        }
    /*
        DatabaseHelper d  = new DatabaseHelper(this);
        SQLiteDatabase db = d.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Employee");
        d.onCreate(db);
    */
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
