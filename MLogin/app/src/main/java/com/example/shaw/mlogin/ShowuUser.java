package com.example.shaw.mlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowuUser extends AppCompatActivity {

    String uid="";

    ListView lv1;
    Spinner sp1;
    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";

    myClass obj;

    ArrayList<String> list1,list2,list3;

    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showu_user);
        Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv1=findViewById(R.id.uslv1);

        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        list3=new ArrayList<String>();

        obj=new myClass(this);
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");
        userAdapter=new UserAdapter(ShowuUser.this,R.layout.row_user);

        Cursor c=obj.showmsguser(uid);
        try
        {
            while (c.moveToNext())
            {
                String name=c.getString(1);
                String email=c.getString(2);
                String usid=c.getString(0);
                list1.add(usid);
                byte[] uimage=c.getBlob(7);

                UserClass userClass=new UserClass(usid,name,email,uimage);
                userAdapter.add(userClass);

            }

            lv1.setAdapter(userAdapter);

            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String m=list1.get(i);
                    Intent intent=new Intent(ShowuUser.this,MessageAct.class);
                    intent.putExtra("val1",m);
                    startActivity(intent);
                }
            });

        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
