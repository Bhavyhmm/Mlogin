package com.example.shaw.mlogin;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

public class ForgotPassword2 extends AppCompatActivity {


    myClass obj;
    CoordinatorLayout coordinator;
    private TextInputLayout textInputLayoutPassword;
    String email="",birthcity="";

    private TextInputEditText et10;
    private AppCompatButton bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);

        coordinator=findViewById(R.id.coordinator);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);

        bt6= (AppCompatButton) findViewById(R.id.bt6);
        et10=(TextInputEditText)findViewById(R.id.et10);
        obj=new myClass(this);

        email=getIntent().getStringExtra("email");
        birthcity=getIntent().getStringExtra("birthcity");

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password=et10.getText().toString();
                if (password.length()<6){
                    et10.setText(null);
                    textInputLayoutPassword.setError("Password length should be more than six");
                    Snackbar.make(coordinator,"Password length should be more than six",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                try{
                    obj.resetpassword(email,password);
                    startActivity(new Intent(ForgotPassword2.this,LoginAct.class));
                    overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
                }
                catch (Exception ex){
                    Toast.makeText(ForgotPassword2.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
