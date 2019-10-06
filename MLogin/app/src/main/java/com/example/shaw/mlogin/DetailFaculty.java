package com.example.shaw.mlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailFaculty extends AppCompatActivity {


    myClass obj;
    String fid="",branch="";
    ArrayList<String> list;
    CoordinatorLayout coordinator;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPhone;


    public AppCompatButton bt1;
    private AppCompatSpinner sp1;
    private AppCompatTextView tv5;
    private TextInputEditText et1;
    private TextInputEditText et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_faculty);
        obj=new myClass(this);
        coordinator=findViewById(R.id.coordinator);
        fid=getIntent().getStringExtra("val1");
        Toast.makeText(this, "the"+fid, Toast.LENGTH_SHORT).show();
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);
        list=new ArrayList<String>();

        sp1 = (AppCompatSpinner) findViewById(R.id.dftsp1);
        et1 = (TextInputEditText) findViewById(R.id.dftet1);
        et2 = (TextInputEditText) findViewById(R.id.dftet2);

        final List<String> list = new ArrayList<>();
        list.add("Select");
        list.add("Architecture");
        list.add("Computer");
        list.add("Civil");
        list.add("EC");
        list.add("Mechanical");
        list.add("IT");
        list.add("PCT");
        list.add("Electrical");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailFaculty.this, R.layout.support_simple_spinner_dropdown_item, list);
        sp1.setAdapter(arrayAdapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                branch=list.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Cursor c=obj.getfaculty(fid);

        try {
            while (c.moveToNext()) {

                et1.setText(c.getString(1));
                et2.setText(c.getString(4));
            }


        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void updatefaculty(View view)
    {
        String name = et1.getText().toString();
        String phone= et2.getText().toString();
        textInputLayoutName.setErrorEnabled(false);

        textInputLayoutPhone.setErrorEnabled(false);
        if (name.length() == 0
                || phone.length() != 10) {


            if (name.length() == 0) {
                et1.setText(null);
                et1.requestFocus();
                textInputLayoutName.setError("Faculty field cannot be empty");
                //Toast.makeText(getApplicationContext(), "Username field cannot be empty", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "Faculty field cannot be empty", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (phone.length() != 10) {
                et2.setText(null);
                et2.requestFocus();
                if (phone.length() == 0) {
                    textInputLayoutPhone.setError("Phone number cannot be empty");
                    //Toast.makeText(SignUp.this, "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinator, "Phone number cannot be empty", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    textInputLayoutPhone.setError("Phone number not valid");
                    //Toast.makeText(SignUp.this, "Phone number not valid", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinator, "Phone number not valid", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }
            return;
        }
        else
        {
            try
            {
                obj.updatefaculty(fid,name,phone,branch);
                //Toast.makeText(activity, "Faculty Added Successfully", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator,"Faculty Update Successful",Snackbar.LENGTH_SHORT).show();
                startActivity(new Intent(DetailFaculty.this,ShowFaculty.class));
            }
            catch (Exception ex)
            {
                Toast.makeText(DetailFaculty.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        Cursor c3 = obj.checkPhone(et2.getText().toString());
        try {
            if (c3.getCount() > 0) {
                et2.setText(null);
                et2.requestFocus();
                textInputLayoutPhone.setError("Phone number already exist");
                //Toast.makeText(SignUp.this, "Phone number already exist", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "Phone number already exist", Snackbar.LENGTH_LONG).show();
                return;
            }
        } catch (Exception ex) {
            Toast.makeText(DetailFaculty.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);


    }
    public void deletefaculty(View view)
    {
        AlertDialog.Builder ab=new AlertDialog.Builder(DetailFaculty.this);
        ab.setTitle("Delete Faculty");
        ab.setMessage("Sure Do You Want to Delete?");
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try
                {
                    if(obj.delfaculty(fid))
                    {
                        startActivity(new Intent(DetailFaculty.this,ShowFaculty.class));
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(DetailFaculty.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ab.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog ad=ab.create();
        ad.show();
    }
}
