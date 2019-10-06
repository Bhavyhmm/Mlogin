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
import android.view.View;
import android.widget.Toast;

public class ForgotPassword1 extends AppCompatActivity {


    myClass obj;
    CoordinatorLayout coordinator;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutBirthcity;

    private TextInputEditText et8;
    private TextInputEditText et9;
    private AppCompatButton bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        coordinator=findViewById(R.id.coordinator);
        textInputLayoutEmail = (TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutBirthcity = (TextInputLayout)findViewById(R.id.textInputLayoutBirthcity);

        bt6= (AppCompatButton) findViewById(R.id.bt6);
        et8=(TextInputEditText)findViewById(R.id.et8);
        et9=(TextInputEditText)findViewById(R.id.et9);
        obj=new myClass(this);


        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=et8.getText().toString();
                String birthcity=et9.getText().toString();
                textInputLayoutEmail.setErrorEnabled(false);
                textInputLayoutBirthcity.setErrorEnabled(false);

                Cursor c=obj.checkBirth(email,birthcity);
                try{
                    if (c.getCount()>0){
                        Intent intent=new Intent(ForgotPassword1.this,ForgotPassword2.class);
                        intent.putExtra("email",email);
                        intent.putExtra("birthcity",birthcity);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                    }
                    else{
                        et8.setText(null);
                        et9.setText(null);
                        et8.requestFocus();
                        textInputLayoutEmail.setError("Invalid input");
                        Toast.makeText(ForgotPassword1.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        Snackbar.make(coordinator,"Invalid input",Snackbar.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(ForgotPassword1.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}

