package com.example.shaw.mlogin;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity {

    myClass obj;

    CoordinatorLayout coordinator;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutDate;
    private TextInputLayout textInputLayoutTime;
    private TextInputLayout textInputLayoutDesc;


    private TextInputEditText et1;
    private TextInputEditText et2;
    private TextInputEditText et3;
    private TextInputEditText et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        obj=new myClass(this);
        coordinator=findViewById(R.id.coordinator);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutDate = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutTime = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutDesc = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);


        et1=findViewById(R.id.vet1);
        et2=findViewById(R.id.vet2);
        et3=findViewById(R.id.vet3);
        et4=findViewById(R.id.vet4);

    }
    public void addevent(View view)
    {
        String name = et1.getText().toString();
        String edate = et2.getText().toString();
        String etime = et3.getText().toString();
        String edesc = et4.getText().toString();
        textInputLayoutName.setErrorEnabled(false);
        textInputLayoutDate.setErrorEnabled(false);
        textInputLayoutTime.setErrorEnabled(false);

        textInputLayoutDesc.setErrorEnabled(false);
        if (name.length() == 0 || edate.length() ==0 || etime.length() == 0
                || edesc.length() ==0) {


            if (name.length() == 0) {
                et1.setText(null);
                et1.requestFocus();
                textInputLayoutName.setError("Event field cannot be empty");
                //Toast.makeText(getApplicationContext(), "Username field cannot be empty", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "User field cannot be empty", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (edate.length() ==0) {
                et2.setText(null);
                et2.requestFocus();

                textInputLayoutDate.setError("Date field cannot be empty");
                //Toast.makeText(SignUp.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator, "Date field cannot be empty", Snackbar.LENGTH_LONG).show();
                return;
            }
        }
        if (edesc.length() == 0 ) {
            et4.setText(null);
            et4.requestFocus();
            textInputLayoutTime.setError("Description field cannot be empty");
            //Toast.makeText(SignUp.this, "Email field cannot be empty", Toast.LENGTH_SHORT).show();
            Snackbar.make(coordinator, "Description field cannot be empty", Snackbar.LENGTH_LONG).show();
            return;
        }

        else
        {
            try
            {
                obj.addevent(name,edate,etime,edesc);
                //Toast.makeText(activity, "Faculty Added Successfully", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinator,"Event Added Successful",Snackbar.LENGTH_SHORT).show();
            }
            catch (Exception ex)
            {
                Toast.makeText(AddEvent.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }



        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);


    }
}
