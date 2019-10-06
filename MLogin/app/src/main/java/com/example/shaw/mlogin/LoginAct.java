package com.example.shaw.mlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginAct extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton bt1,bt2;
    private TextInputEditText et6,et7;
    CoordinatorLayout coordinator;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPassword;

    myClass obj;

    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";
    public static final String Branch="branchkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        textInputLayoutName = (TextInputLayout)findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        bt1=(AppCompatButton)findViewById(R.id.bt1);
        bt2=(AppCompatButton)findViewById(R.id.bt2);
        et6= (TextInputEditText) findViewById(R.id.et6);
        et7=(TextInputEditText)findViewById(R.id.et7);
        coordinator=findViewById(R.id.coordinator);
        findViewById(R.id.tv7).setOnClickListener(this);

        obj=new myClass(this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginAct.this,SlideActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=et6.getText().toString();
                String password=et7.getText().toString();

                Cursor c=obj.loginuser(email,password);
                Cursor c1 = obj.loginadmin(email, password);
                Cursor c2=obj.loginfaculty(email,password);
                try {
                    if (c.getCount() > 0) {
                        sharedPreferences = getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(UserID, obj.getMyId(email));
                        editor.putString(Branch, obj.getBranchId(email));
                        editor.commit();
                       Snackbar.make(coordinator, "User login successful", Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(LoginAct.this, "User Login successful"+obj.getMyId(email), Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginAct.this, UserInner.class));
                    }
                    else if (c1.getCount() > 0) {
                        Snackbar.make(coordinator,"Admin Login",Snackbar.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginAct.this, AdminInner.class));
                    }
                    else if (c2.getCount()>0){
                        Snackbar.make(coordinator,"Faculty Login",Snackbar.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginAct.this,FacultyInner.class));
                    }
                    else if (email.length() == 0 || password.length() == 0) {
                        et6.requestFocus();
                        if (email.length()==0) {
                            textInputLayoutName.setError("User field cannot be empty");
                            //Toast.makeText(MainActivity.this, "Input fields cannot be empty", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator, "Input fields cannot be empty", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        else{
                            textInputLayoutPassword.setError("Password field cannot be empty");
                            //Toast.makeText(MainActivity.this, "Input fields cannot be empty", Toast.LENGTH_SHORT).show();
                            Snackbar.make(coordinator, "Input fields cannot be empty", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    } else {
                        et7.setText(null);
                        et7.requestFocus();
                        //Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        Snackbar.make(coordinator,"Invalid email or Password",Snackbar.LENGTH_LONG).show();
                        return;
                    }
                }
                catch (Exception ex){
                    Toast.makeText(LoginAct.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv7:
                startActivity(new Intent(LoginAct.this,ForgotPassword1.class));
                overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
                break;
        }
    }
   /* public void show(View view){
        startActivity(new Intent(LoginAct.this,Loggedin.class));
    }*/
}
