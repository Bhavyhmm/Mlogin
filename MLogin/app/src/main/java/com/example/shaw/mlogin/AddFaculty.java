package com.example.shaw.mlogin;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddFaculty extends AppCompatActivity {

    private final AppCompatActivity activity = AddFaculty.this;

    myClass obj;
    CoordinatorLayout coordinator;
    public TextInputLayout textInputLayoutName;
    public TextInputLayout textInputLayoutPassword;
    public TextInputLayout textInputLayoutEmail;
    public TextInputLayout textInputLayoutPhone;

    public AppCompatButton btf1;
    public AppCompatSpinner spf1;
    public AppCompatTextView tvf5;
    public TextInputEditText etf1;
    public TextInputEditText etf2;
    public TextInputEditText etf3;
    public TextInputEditText etf4;
    String m = "",branch="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        /*android.support.v7.widget.Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        obj = new myClass(this);
        coordinator = findViewById(R.id.coordinator);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);

        btf1 = (AppCompatButton) findViewById(R.id.ftbt1);
        spf1 = (AppCompatSpinner) findViewById(R.id.ftsp1);
        etf1 = (TextInputEditText) findViewById(R.id.ftet1);
        etf2 = (TextInputEditText) findViewById(R.id.ftet2);
        etf3 = (TextInputEditText) findViewById(R.id.ftet3);
        etf4 = (TextInputEditText) findViewById(R.id.ftet5);
        tvf5=(AppCompatTextView) findViewById(R.id.ftv5);
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddFaculty.this, R.layout.support_simple_spinner_dropdown_item, list);
        spf1.setAdapter(arrayAdapter);

        spf1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                branch=list.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addfaculty(View view) {
        String name = etf1.getText().toString();
        String password = etf2.getText().toString();
        String email = etf3.getText().toString();
        String phone = etf4.getText().toString();
        textInputLayoutName.setErrorEnabled(false);
        textInputLayoutPassword.setErrorEnabled(false);
        textInputLayoutEmail.setErrorEnabled(false);

        textInputLayoutPhone.setErrorEnabled(false);
        if (name.length() == 0 || password.length() < 6 || email.length() == 0
                || phone.length() != 10) {


            if (name.length() == 0) {
                etf1.setText(null);
                etf1.requestFocus();
                textInputLayoutName.setError("Faculty field cannot be empty");
                //Toast.makeText(getApplicationContext(), "Username field cannot be empty", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "Faculty field cannot be empty", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (password.length() < 6) {
                etf2.setText(null);
                etf2.requestFocus();
                if (password.length() == 0) {
                    textInputLayoutPassword.setError("Password field cannot be empty");
                    //Toast.makeText(SignUp.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinator, "Password field cannot be empty", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    textInputLayoutPassword.setError("Enter password of length more than SIX");
                    //Toast.makeText(SignUp.this, "Enter password of length more than SIX", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinator, "Enter password of length more than SIX", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }
            if (email.length() == 0 || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etf3.setText(null);
                etf3.requestFocus();
                if (email.length() == 0) {
                    textInputLayoutEmail.setError("Email field cannot be empty");
                    //Toast.makeText(SignUp.this, "Email field cannot be empty", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinator, "Email field cannot be empty", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    textInputLayoutEmail.setError("Email not valid");
                    //Toast.makeText(SignUp.this, "Email not valid", Toast.LENGTH_SHORT).show();
                    Snackbar.make(coordinator, "Email not valid", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }

            if (phone.length() != 10) {
                etf4.setText(null);
                etf4.requestFocus();
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
                obj.addfaculty(name,password,email,phone,branch);
                //Toast.makeText(activity, "Faculty Added Successfully", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator,"Faculty Added Successful",Snackbar.LENGTH_SHORT).show();
            }
            catch (Exception ex)
            {
                Toast.makeText(activity, ex.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        Cursor c1 = obj.checkEmail(etf3.getText().toString());
        try {
            if (c1.getCount() > 0) {
                etf3.setText(null);
                etf3.requestFocus();
                textInputLayoutEmail.setError("Email already exist");
                //Toast.makeText(SignUp.this, "Email already exist", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "Email already exist", Snackbar.LENGTH_LONG).show();
                return;
            }
        } catch (Exception ex) {
            Toast.makeText(AddFaculty.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Cursor c3 = obj.checkPhone(etf4.getText().toString());
        try {
            if (c3.getCount() > 0) {
                etf4.setText(null);
                etf4.requestFocus();
                textInputLayoutPhone.setError("Phone number already exist");
                //Toast.makeText(SignUp.this, "Phone number already exist", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "Phone number already exist", Snackbar.LENGTH_LONG).show();
                return;
            }
        } catch (Exception ex) {
            Toast.makeText(AddFaculty.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);

    }

}