package com.example.muhammad.elitepayrollapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeesListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    String item[] = new String[] {};
    ListView list;
    FloatingActionButton add_button;
    Button resetall;
    DatabaseHelper mDatabaseHelper;
    ArrayList<Employee> employeeList;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        add_button = (FloatingActionButton)findViewById(R.id.Employees_add_FloatButton);
        resetall = (Button)findViewById(R.id.List_ResetAll_Button);
        list = (ListView)findViewById(R.id.Employees_List_ListView);
        mDatabaseHelper = new DatabaseHelper(this);
        employeeList = mDatabaseHelper.getAllEmployees();
        employeeListAdapter adapter = new employeeListAdapter(this, R.layout.adapter_view_layout, employeeList);

        db = new DatabaseHelper(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        add_button.setOnClickListener(this);
        resetall.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Employee emp = employeeList.get(position);
        Log.e("object",emp.toString());
        Intent goToSalary = new Intent(this,SalaryActivity.class);
        goToSalary.putExtra("employeeID", emp.getID());

        startActivity(goToSalary);
    }

    @Override
    public void onClick(View v) {
        if(v == add_button){
            Intent goToAddEmployee = new Intent(this,AddEmployeeActivity.class);
            startActivity(goToAddEmployee);
        }
        if(v == resetall){

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Reset Balance");
            alert.setMessage("Are you sure you want to reset?");
            final Intent refreash = new Intent(this, EmployeesListActivity.class);
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
            {

                public void onClick(DialogInterface dialog, int which)
                {
                    db.ResetEmployee();
                    startActivity(refreash);
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
    }

    @Override
    public void onBackPressed() {
        Intent gotologin = new Intent(this, SigninActivity.class);
        startActivity(gotologin);
    }
}
