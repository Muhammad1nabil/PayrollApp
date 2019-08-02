package com.example.muhammad.elitepayrollapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SalaryActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper db;
    Button add, minus, viewData, day25, day5, day, savenotes;
    TextView balance, name;
    EditText amount, Notes;
    Employee emp;
    int id;
    String namestr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        name = (TextView)findViewById(R.id.Salary_name_TextView);
        add = (Button)findViewById(R.id.Salary_Add_Button);
        minus = (Button)findViewById(R.id.Salary_Minus_Button);
        day25 = (Button)findViewById(R.id.Salary_14Day_Button);
        day5 = (Button)findViewById(R.id.Salary_12Day_Button);
        day = (Button)findViewById(R.id.Salary_1Day_Button);
        savenotes = (Button)findViewById(R.id.Salary_saveNote_Button);
        viewData = (Button)findViewById(R.id.Salary_viewData_Button);
        balance = (TextView)findViewById(R.id.Salary_Balance_TextView);
        amount = (EditText)findViewById(R.id.Salary_Amount_EditText);
        Notes = (EditText)findViewById(R.id.Salary_Note_EditText);

        db = new DatabaseHelper(this);
        Intent received = getIntent();
        id = received.getExtras().getInt("employeeID");
        emp = db.getEmployeeByID(id);

        balance.setText(String.valueOf(emp.getBalance()));
        name.setText(namestr);
        Notes.setText(emp.getNotes());

        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        viewData.setOnClickListener(this);
        day.setOnClickListener(this);
        day5.setOnClickListener(this);
        day25.setOnClickListener(this);
        savenotes.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        Intent goTolist = new Intent(this, EmployeesListActivity.class);
        startActivity(goTolist);


    }

    @Override
    public void onClick(View v) {
        if(v == add){
            if(amount.getText().toString().isEmpty())
            {
                Toast.makeText(this, "No amount entered!",Toast.LENGTH_SHORT ).show();
            }
            else {
                int amountint = Integer.parseInt(amount.getText().toString());
                int newbalance = emp.getBalance() + amountint;
                db.editBalance(id, newbalance);
                emp = db.getEmployeeByID(id);
                balance.setText(String.valueOf(newbalance));
                //Intent goToList = new Intent(this, EmployeesListActivity.class);
                //startActivity(goToList);
            }
        }
        else if(v == minus){
            if(amount.getText().toString().isEmpty())
            {
                Toast.makeText(this, "No amount entered!",Toast.LENGTH_SHORT ).show();
            }
            else {
                int amountint = Integer.parseInt(amount.getText().toString());
                int newbalance = emp.getBalance() - amountint;
                db.editBalance(id, newbalance);
                emp = db.getEmployeeByID(id);
                balance.setText(String.valueOf(newbalance));
                //Intent goToList = new Intent(this, EmployeesListActivity.class);
                //startActivity(goToList);
            }
        }


        else if(v == viewData)
        {
            Intent goToData = new Intent(this, ViewDataActivity.class);
            goToData.putExtra("employeeID", id);

            startActivity(goToData);
        }

        else if(v == day)
        {
            int payday = emp.getPaymentperDay();
            amount.setText(String.valueOf(payday));
        }

        else if(v == day5)
        {
            int payday = emp.getPaymentperDay()/2;
            amount.setText(String.valueOf(payday));
        }

        else if(v == day25)
        {
            int payday = emp.getPaymentperDay()/4;
            amount.setText(String.valueOf(payday));
        }

        else if(v == savenotes){
            db.UpdateNotes(id, Notes.getText().toString());
            emp = db.getEmployeeByID(id);
            Notes.setText(emp.getNotes());
            Toast.makeText(this, "Notes Saved!",Toast.LENGTH_SHORT ).show();
            //Intent goToList = new Intent(this, EmployeesListActivity.class);
            //startActivity(goToList);
        }

        /*
        else if(v == delete){

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete "+ emp.getName()+ "?");
            alert.setMessage("Are you sure you want to delete?");
            final Intent goToList = new Intent(this, EmployeesListActivity.class);
            final Toast tost = Toast.makeText(this," Employee "+ emp.getName() +
                            " has been Deleted Successfully!", Toast.LENGTH_LONG);
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
            {

                public void onClick(DialogInterface dialog, int which)
                {
                    db.deleteEmployeeByID(emp.getID());
                    tost.show();
                    startActivity(goToList);
                }
            });
            alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // close dialog
                    dialog.cancel();
                }
            });
            alert.show();

        }

        else if(v == update){
            int salaryInt = Integer.parseInt(salary.getText().toString());
            db.editSalary(id, salaryInt);
            emp = db.getEmployeeByID(id);
            balance.setText(String.valueOf(emp.getSalary()));
            salary.setText(String.valueOf(emp.getSalary()));
        }*/
    }
}
