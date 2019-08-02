package com.example.muhammad.elitepayrollapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Muhammad on 15/06/2019.
 */

public class employeeListAdapter extends ArrayAdapter<Employee> {

    private Context mContext;
    int mResource;
    public employeeListAdapter( Context context, int resource, ArrayList<Employee> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        int salary = getItem(position).getSalary();
        int balance = getItem(position).getBalance();
        String nid = getItem(position).getNID();
        int payday = getItem(position).getPaymentperDay();
        String addr = getItem(position).getAddress();
        String phone1 = getItem(position).getPhone1();
        String phone2 = getItem(position).getPhone2();
        Employee employee = new Employee(name,salary,balance, nid, payday, addr, phone1, phone2);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        TextView nametv = (TextView)convertView.findViewById(R.id.AdopterView_name_TextView);
        TextView balancetv = (TextView)convertView.findViewById(R.id.AdopterView_balance_TextView);
        TextView salarytv = (TextView)convertView.findViewById(R.id.AdopterView_salary_TextView);

        nametv.setText(name);
        balancetv.setText("Balance: " + String.valueOf(balance));
        salarytv.setText("Salary: " + String.valueOf(salary));
        return convertView;
    }
}
