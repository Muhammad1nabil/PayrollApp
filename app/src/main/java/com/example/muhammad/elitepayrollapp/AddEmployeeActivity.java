package com.example.muhammad.elitepayrollapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper mDatabaseHelper;
    Button add;
    EditText name, salary, NID, PaymentperDay, Address, Phone1, Phone2;
    int id;
    private static final String SHARED_PREF_NAME = "ID";
    private static final String KEY_ID = "key_ID";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        add = (Button)findViewById(R.id.AddEmp_Add_Button);
        name = (EditText)findViewById(R.id.AddEmp_Name_Input);
        salary = (EditText)findViewById(R.id.AddEmp_Salary_Input);
        NID = (EditText)findViewById(R.id.addEmp_NID_Input);
        PaymentperDay = (EditText)findViewById(R.id.addEmp_PayperDday_Input);
        Address = (EditText)findViewById(R.id.addEmp_Address_Input);
        Phone1 = (EditText)findViewById(R.id.addEmp_Phone_Input);
        Phone2 = (EditText)findViewById(R.id.addEmp_Phone2_Input);

        mDatabaseHelper = new DatabaseHelper(this);
        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String nm = name.getText().toString();
        String slrystr = salary.getText().toString();
        String nid = NID.getText().toString();
        String payperday = PaymentperDay.getText().toString();
        String addr = Address.getText().toString();
        String phone1 = Phone1.getText().toString();
        String phone2 = Phone2.getText().toString();


        if (nm.isEmpty() || slrystr.isEmpty() || nid.isEmpty() || payperday.isEmpty() || addr.isEmpty()
                || phone1.isEmpty() || phone2.isEmpty() ){
            Toast.makeText(this, "Input data is missing!",Toast.LENGTH_SHORT ).show();
        }

        else{
            int slryint = Integer.parseInt(slrystr);
            int payday = Integer.parseInt(payperday);

            Employee emp = new Employee(id, nm, slryint, slryint, nid, payday, addr, phone1, phone2, "");
            mDatabaseHelper.addEmployee(emp);
            Toast.makeText(this, "Added Successfully!",Toast.LENGTH_SHORT ).show();
            Intent goToList = new Intent(this,EmployeesListActivity.class);
            startActivity(goToList);
        }
    }

    @Override
    public void onBackPressed() {
        Intent goToList = new Intent(this,EmployeesListActivity.class);
        startActivity(goToList);
    }
}
