package com.example.shaw.mlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowEvent extends AppCompatActivity {

    ListView lv1;

    myClass obj;
    String uid="",branch="";
    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";
    public static final String Branch="branchkey";

    EventAdpater eventAdpater;

    ArrayList<String> list1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv1=findViewById(R.id.sflv1);
        obj=new myClass(this);
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");
        branch=sharedPreferences.getString(Branch,"");
        list1=new ArrayList<String>();
        eventAdpater=new EventAdpater(ShowEvent.this,R.layout.row_event);

        Cursor c=obj.branchwise("select * from event");

        try
        {
            while (c.moveToNext())
            {
                String eid=c.getString(0);
                String name=c.getString(1);
                String edate=c.getString(2);
                String etime=c.getString(3);
                String edesc=c.getString(4);

                EventClass eventClass=new EventClass(eid,name,edate,etime,edesc);
                eventAdpater.add(eventClass);
            }
            lv1.setAdapter(eventAdpater);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
    }

