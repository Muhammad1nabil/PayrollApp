package com.example.muhammad.elitepayrollapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Muhammad on 15/06/2019.
 */


public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int databaseVersion = 1;
    // Database Name
    private static final String databaseName = "CompanyDB";
    // Contacts table name
    private static final String tableEmployees = "Employee";


    // Contacts Table Columns names
    public static final String key_ID = "_id";
    public static final String key_Name = "Name";
    public static final String key_Salary = "Salary";
    public static final String key_Balance = "Balance";
    public static final String key_NID = "NID";
    public static final String key_PaymentperDay = "PaymentperDay";
    public static final String key_Address = "Address";
    public static final String key_Phone1 = "Phone1";
    public static final String key_Phone2 = "Phone2";
    public static final String key_Notes = "Notes";


    public DatabaseHelper(Context context)
    {
        super(context, databaseName, null, databaseVersion);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + tableEmployees + "("
                + key_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + key_Name 		+ " TEXT , "
                + key_Salary 	+ " INTEGER , "
                + key_Balance 	+ " INTEGER , "
                + key_NID       + " TEXT , "
                + key_PaymentperDay + " INTEGER , "
                + key_Address   + " TEXT , "
                + key_Phone1    + " TEXT , "
                + key_Phone2    + " TEXT , "
                + key_Notes     + " TEXT )"
                ;
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableEmployees);
        // Create tables again
        onCreate(db);
    }

    public void addEmployee(Employee employee)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(key_Name, employee.getName());
        values.put(key_Salary, employee.getSalary());
        values.put(key_Balance, employee.getBalance());
        values.put(key_PaymentperDay, employee.getPaymentperDay());
        values.put(key_Address, employee.getAddress());
        values.put(key_NID, employee.getNID());
        values.put(key_Phone1, employee.getPhone1());
        values.put(key_Phone2, employee.getPhone2());


        // Inserting Row
        long newID = mydb.insert(tableEmployees, null, values);
        Log.e("newID", String.valueOf(newID));
        Log.e("object", getEmployeeByID((int) newID).toString());
        mydb.close(); // Closing database connection
    }


    public Employee getEmployeeByID(int employeeID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableEmployees, new String[] {key_ID, key_Name, key_Salary ,key_Balance,
                key_NID, key_PaymentperDay, key_Address, key_Phone1, key_Phone2, key_Notes},
                key_ID+" = ?",new String[]{String.valueOf(employeeID)}, null,
                null, null, null);

        Employee employee = null;

        if (cursor.moveToFirst())
        {
            employee = new Employee(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getInt(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9));
        }

        //db.close();
        return employee;
    }


    public ArrayList<Employee> getAllEmployees()
    {
        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableEmployees;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Employee oneEmployee = new Employee(cursor.getInt(0),
                        cursor.getString(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getString(4), cursor.getInt(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8),
                        cursor.getString(9));
                // Adding contact to list
                employeesList.add(oneEmployee);
                Log.e("Emp ID", String.valueOf(oneEmployee.getID()));
            }
            while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return employeesList;
    }

    public void editBalance(int id, int amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_Balance, amount);

        int a = db.update(tableEmployees, values, key_ID+"="+id, null);
        /*
        db.execSQL("Update "+tableEmployees+
        " Set "+key_Balance+" = "+amount+
        " Where _id = "+ emp.getID());
        Log.e("output", values.toString());
        *///db.close();
    }

    public boolean editSalary(int employeeID, int newSalary)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Employee emp = getEmployeeByID(employeeID);
        values.put(key_ID, employeeID);
        values.put(key_Name, emp.getName());
        values.put(key_Salary, newSalary);
        values.put(key_Balance, newSalary);
        db.update(tableEmployees, values, key_ID+"="+ employeeID,null);
        //db.close();
        return true;
    }

    public boolean editPaymentperDay(int employeeID, int newPaymentperDay)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Employee emp = getEmployeeByID(employeeID);
        values.put(key_PaymentperDay, newPaymentperDay);
        db.update(tableEmployees, values, key_ID+"="+ employeeID,null);
        //db.close();
        return true;
    }


    public int deleteEmployeeByID(int employeeID, Context aho)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int count = db.delete(tableEmployees, key_ID + " = "+employeeID,null);
        db.close();
        Toast.makeText(aho, "Employee has been Deleted Successfully!"
                ,Toast.LENGTH_SHORT).show();
        return count;
    }


    public void ResetEmployee() {

        Employee emp;
        ArrayList<Employee> emplist = getAllEmployees();
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i = 0;i<emplist.toArray().length;i++)
        {
            emp = emplist.get(i);
            ContentValues values = new ContentValues();

            values.put(key_Name, emp.getName());
            values.put(key_Salary, emp.getSalary());
            values.put(key_Balance, emp.getSalary());
            long newID = db.update(tableEmployees, values, key_ID+"="+emp.getID(),
                    null);
        }
        db.close();
    }

    public void UpdateNotes(int id, String newNote)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_Notes, newNote);

        db.update(tableEmployees, values, key_ID+"="+id, null);
    }

    public void UpdateEmployeeInfo(Employee emp, Context aho)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_Name, emp.getName());
        values.put(key_NID, emp.getNID());
        values.put(key_Salary, emp.getSalary());
        values.put(key_Balance, emp.getSalary());
        values.put(key_PaymentperDay, emp.getPaymentperDay());
        values.put(key_Address, emp.getAddress());
        values.put(key_Phone1, emp.getPhone1());
        values.put(key_Phone2, emp.getPhone2());
        values.put(key_Notes, emp.getNotes());
        int a =db.update(tableEmployees, values, key_ID+"="+emp.getID(), null);
        Log.e("UpdateEmployee", String.valueOf(a));
        Toast.makeText(aho, emp.getName() + " Data Updated",Toast.LENGTH_SHORT ).show();

    }

}