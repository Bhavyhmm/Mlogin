package com.example.shaw.mlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.UnicodeSetSpanner;
import android.media.MediaCas;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    private final AppCompatActivity activity = SignUp.this;

    myClass obj;
    CoordinatorLayout coordinator;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPrn;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutBirthcity;

    private AppCompatButton bt1;
    private AppCompatSpinner sp1;
    private AppCompatTextView tv5;
    private TextInputEditText et1;
    private TextInputEditText et2;
    private TextInputEditText et3;
    private TextInputEditText et4;
    private TextInputEditText et5;
    private TextInputEditText et6;
    String m="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        obj=new myClass(this);
        coordinator=findViewById(R.id.coordinator);
        textInputLayoutName = (TextInputLayout)findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputLayoutEmail = (TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPrn = (TextInputLayout)findViewById(R.id.textInputLayoutPrn);
        textInputLayoutPhone = (TextInputLayout)findViewById(R.id.textInputLayoutPhone);
        textInputLayoutBirthcity = (TextInputLayout)findViewById(R.id.textInputLayoutBirthcity);


        bt1= (AppCompatButton) findViewById(R.id.bt1);
        sp1= (AppCompatSpinner) findViewById(R.id.sp1);
        et1=(TextInputEditText)findViewById(R.id.et1);
        et2=(TextInputEditText)findViewById(R.id.et2);
        et3=(TextInputEditText)findViewById(R.id.et3);
        et4=(TextInputEditText)findViewById(R.id.et4);
        et5=(TextInputEditText)findViewById(R.id.et5);
        et6=(TextInputEditText)findViewById(R.id.et6);

        final List<String> list= new ArrayList<>();
        list.add("Select");
        list.add("Architecture");
        list.add("Computer");
        list.add("Civil");
        list.add("EC");
        list.add("Mechanical");
        list.add("IT");
        list.add("PCT");
        list.add("Electrical");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(SignUp.this,R.layout.support_simple_spinner_dropdown_item,list);
        sp1.setAdapter(arrayAdapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                m=list.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString();
                String password = et2.getText().toString();
                String email = et3.getText().toString();
                String prn = et4.getText().toString();
                String phone = et5.getText().toString();
                String birthcity=et6.getText().toString();
                textInputLayoutName.setErrorEnabled(false);
                textInputLayoutPassword.setErrorEnabled(false);
                textInputLayoutEmail.setErrorEnabled(false);
                textInputLayoutPrn.setErrorEnabled(false);
                textInputLayoutPhone.setErrorEnabled(false);
                textInputLayoutBirthcity.setErrorEnabled(false);

                if (name.length() == 0 || password.length() < 6 || email.length() == 0 || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        || prn.length() != 16 || phone.length() != 10 || birthcity.length() == 0) {
                    if (name.length() == 0) {
                        et1.setText(null);
                        et1.requestFocus();
                        textInputLayoutName.setError("User field cannot be empty");
                        //Toast.makeText(getApplicationContext(), "Username field cannot be empty", Toast.LENGTH_SHORT).show();
                        Snackbar.make(coordinator,"User field cannot be empty",Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    if (password.length() < 6) {
                        et2.setText(null);
                        et2.requestFocus();
                        if (password.length() == 0) {
                            textInputLayoutPassword.setError("Password field cannot be empty");
                            //Toast.makeText(SignUp.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"Password field cannot be empty",Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            textInputLayoutPassword.setError("Enter password of length more than SIX");
                            //Toast.makeText(SignUp.this, "Enter password of length more than SIX", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"Enter password of length more than SIX",Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (email.length() == 0 || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        et3.setText(null);
                        et3.requestFocus();
                        if(email.length()==0) {
                            textInputLayoutEmail.setError("Email field cannot be empty");
                            //Toast.makeText(SignUp.this, "Email field cannot be empty", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"Email field cannot be empty",Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        else{
                            textInputLayoutEmail.setError("Email not valid");
                            //Toast.makeText(SignUp.this, "Email not valid", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"Email not valid",Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (prn.length() != 16) {
                        et4.setText(null);
                        et4.requestFocus();
                        if (prn.length() == 0) {
                            textInputLayoutPrn.setError("PRN field cannot be empty");
                            //Toast.makeText(SignUp.this, "PRN field cannot be empty", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"PRN field cannot be empty",Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            textInputLayoutPrn.setError("PRN should be of 16 digits");
                            //Toast.makeText(SignUp.this, "PRN should be of 16 digits", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"PRN should be of 16 digits",Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if(phone.length()!=10){
                        et5.setText(null);
                        et5.requestFocus();
                        if(phone.length()==0){
                            textInputLayoutPhone.setError("Phone number cannot be empty");
                            //Toast.makeText(SignUp.this, "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"Phone number cannot be empty",Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        else {
                            textInputLayoutPhone.setError("Phone number not valid");
                            //Toast.makeText(SignUp.this, "Phone number not valid", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator,"Phone number not valid",Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (birthcity.length() == 0){
                        textInputLayoutBirthcity.setError("Security question cannot be empty");
                        et6.setText(null);
                        et6.requestFocus();
                        Snackbar.make(coordinator,"Security question cannot be empty",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    return;
                }

                Cursor c1=obj.checkEmail(et3.getText().toString());
                try{
                    if (c1.getCount()>0){
                        et3.setText(null);
                        et3.requestFocus();
                        textInputLayoutEmail.setError("Email already exist");
                        //Toast.makeText(SignUp.this, "Email already exist", Toast.LENGTH_SHORT).show();
                        Snackbar.make(coordinator,"Email already exist",Snackbar.LENGTH_LONG).show();
                        return;
                    }
                }
                catch (Exception ex){
                    Toast.makeText(SignUp.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Cursor c2=obj.checkPrn(et4.getText().toString());
                try{
                    if (c2.getCount()>0){
                        et4.setText(null);
                        et4.requestFocus();
                        textInputLayoutPrn.setError("PRN already exist");
                        //Toast.makeText(SignUp.this, "PRN already exist", Toast.LENGTH_SHORT).show();
                        Snackbar.make(coordinator,"PRN already exist",Snackbar.LENGTH_LONG).show();
                        return;
                    }
                }
                catch (Exception ex){
                    Toast.makeText(SignUp.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Cursor c3=obj.checkPhone(et5.getText().toString());
                try{
                    if (c3.getCount()>0){
                        et5.setText(null);
                        et5.requestFocus();
                        textInputLayoutPhone.setError("Phone number already exist");
                        //Toast.makeText(SignUp.this, "Phone number already exist", Toast.LENGTH_SHORT).show();
                        Snackbar.make(coordinator,"Phone number already exist",Snackbar.LENGTH_LONG).show();
                        return;
                    }
                }
                catch (Exception ex){
                    Toast.makeText(SignUp.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                try{

                    Intent intent=new Intent(SignUp.this,ProfileSetup.class);
                    intent.putExtra("name",name);
                    intent.putExtra("password",password);
                    intent.putExtra("email",email);
                    intent.putExtra("prn",prn);
                    intent.putExtra("phone",phone);
                    intent.putExtra("branch",m);
                    intent.putExtra("birthcity",birthcity);
                    startActivity(intent);

                }
                catch (Exception ex){
                    Toast.makeText(SignUp.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
            }
        });
    }
}
