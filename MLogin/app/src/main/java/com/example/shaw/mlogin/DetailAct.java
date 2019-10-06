package com.example.shaw.mlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailAct extends AppCompatActivity {

    myClass obj;
    String uid="";
    CoordinatorLayout coordinator;
    EditText et1,et2,et3,et4;
    String m="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        coordinator=findViewById(R.id.coordinator);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        m=getIntent().getStringExtra("val");
        uid=getIntent().getStringExtra("val1");
        obj=new myClass(this);
        et1=findViewById(R.id.duet1);
        et2=findViewById(R.id.duet2);
        et3=findViewById(R.id.duet3);
        et4=findViewById(R.id.duet4);

        Toast.makeText(this, "the"+uid, Toast.LENGTH_SHORT).show();

        Cursor c=obj.detailuser(uid);

        try
        {
            while (c.moveToNext())
            {
                et1.setText(c.getString(1));
                et2.setText(c.getString(3));
                et3.setText(c.getString(4));
                et4.setText(c.getString(5));
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void deleteuser(View view)
    {
        AlertDialog.Builder ab=new AlertDialog.Builder(DetailAct.this);
        ab.setTitle("Delete Faculty");
        ab.setMessage("Sure Do You Want to Delete?");
        ab.setPositiveButton("yes",new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {



                try
                {
                    if(obj.deluser(uid))
                    {
                        startActivity(new Intent(DetailAct.this,AdminInner.class));
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(DetailAct.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
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
