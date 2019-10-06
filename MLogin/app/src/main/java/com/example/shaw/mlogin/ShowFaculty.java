package com.example.shaw.mlogin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class ShowFaculty extends AppCompatActivity {

    ListView lv1;
    myClass obj;
    ArrayList<String> list1,list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_faculty);
        obj=new myClass(this);
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        lv1=findViewById(R.id.flv1);

        Cursor c=obj.showfaculty();
        try
        {
            while (c.moveToNext())
            {
                list1.add(c.getString(1));
                list2.add(c.getString(0));
            }

            ArrayAdapter arrayAdapter=new ArrayAdapter(ShowFaculty.this,android.R.layout.simple_list_item_1,list1);
            lv1.setAdapter(arrayAdapter);

            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String m=list2.get(i);
                    Intent intent=new Intent(ShowFaculty.this,DetailFaculty.class);
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
