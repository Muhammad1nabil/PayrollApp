package com.example.muhammad.elitepayrollapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ViewDataActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    EditText name, NID, salary, payday, addr, phone1, phone2;
    Button save, del;
    Switch update;
    int id;
    DatabaseHelper db;
    Employee emp;
    Intent recieved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        name = findViewById(R.id.ViewData_name_ET);
        NID = findViewById(R.id.ViewData_NID_ET);
        salary = findViewById(R.id.ViewData_Salary_ET);
        payday = findViewById(R.id.ViewData_Paymentperday_ET);
        addr = findViewById(R.id.ViewData_address_ET);
        phone1 = findViewById(R.id.ViewData_Phone1_ET);
        phone2 = findViewById(R.id.ViewData_Phone2_ET);
        save = findViewById(R.id.ViewData_Save_Button);
        del = findViewById(R.id.Viewddata_Delete_Button);
        update = findViewById(R.id.ViewData_upate_swtich);

        db = new DatabaseHelper(this);

        recieved = getIntent();
        id = recieved.getExtras().getInt("employeeID");
        Log.e("ViewDataID", String.valueOf(id));

        emp = db.getEmployeeByID(id);
        name.setText(emp.getName());
        NID.setText(emp.getNID());
        salary.setText(String.valueOf(emp.getSalary()));
        payday.setText(String.valueOf(emp.getPaymentperDay()));
        addr.setText(emp.getAddress());
        phone1.setText(emp.getPhone1());
        phone2.setText(emp.getPhone2());

        update.setChecked(false);
        update.setOnCheckedChangeListener(this);
        save.setOnClickListener(this);
        del.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent goToSalary = new Intent(this, SalaryActivity.class);
        goToSalary.putExtra("employeeID", id);
        startActivity(goToSalary);
    }


    @Override
    public void onClick(View v) {
        if(v == save) {
            if(name.getText().toString().isEmpty() || salary.getText().toString().isEmpty() ||
                    NID.getText().toString().isEmpty() || payday.getText().toString().isEmpty() ||
                    addr.getText().toString().isEmpty() || phone1.getText().toString().isEmpty() ||
                    phone2.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Missing Input Data!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(Integer.parseInt(salary.getText().toString())== emp.getSalary() )) {

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Reset Balance");
                alert.setMessage("By changing Salary the balance and note will reset");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Employee emp = db.getEmployeeByID(id);

                        emp.setName(name.getText().toString());
                        emp.setNID(NID.getText().toString());
                        emp.setSalary(Integer.parseInt(salary.getText().toString()));
                        emp.setBalance(Integer.parseInt(salary.getText().toString()));
                        emp.setNotes("");
                        emp.setPaymentperDay(Integer.parseInt(payday.getText().toString()));
                        emp.setAddress(addr.getText().toString());
                        emp.setPhone1(phone1.getText().toString());
                        emp.setPhone2(phone2.getText().toString());
                        db.UpdateEmployeeInfo(emp, getBaseContext());
                        Log.e("emp nafso",db.getEmployeeByID(id).toString());
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
            else
            {
                Employee emp = db.getEmployeeByID(id);

                emp.setName(name.getText().toString());
                emp.setNID(NID.getText().toString());
                emp.setPaymentperDay(Integer.parseInt(payday.getText().toString()));
                emp.setAddress(addr.getText().toString());
                emp.setPhone1(phone1.getText().toString());
                emp.setPhone2(phone2.getText().toString());
                db.UpdateEmployeeInfo(emp, this);
                Log.e("emp nafso",db.getEmployeeByID(id).toString());
            }
        }
        else if(v == del)
        {
            final Intent gotolist = new Intent(this, EmployeesListActivity.class);
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete "+ name.getText().toString());
            alert.setMessage("Are you sure you want to delete this employee?");
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    db.deleteEmployeeByID(id, getBaseContext());
                    startActivity(gotolist);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            name.setEnabled(true);
            NID.setEnabled(true);
            salary.setEnabled(true);
            payday.setEnabled(true);
            addr.setEnabled(true);
            phone1.setEnabled(true);
            phone2.setEnabled(true);
            save.setEnabled(true);
            del.setEnabled(true);
        }
        else
        {
            name.setEnabled(false);
            NID.setEnabled(false);
            salary.setEnabled(false);
            payday.setEnabled(false);
            addr.setEnabled(false);
            phone1.setEnabled(false);
            phone2.setEnabled(false);
            save.setEnabled(false);
            del.setEnabled(false);
        }
    }
}
