package com.example.shaw.mlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MessageAct extends AppCompatActivity {

    myClass obj;

    String uid="",rid=" ";
    EditText et1;
    ListView lv1;
    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";
    public  static  final String RId="rkey";
    ArrayList<String> list1;

    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        et1=findViewById(R.id.sendet1);
        lv1=findViewById(R.id.msglv1);
        list1=new ArrayList<String>();
        obj=new myClass(this);
        rid=getIntent().getStringExtra("val1");
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RId,rid);
        editor.commit();

        //sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");

        messageAdapter=new MessageAdapter(MessageAct.this,R.layout.list_row_layout_odd);

       // Toast.makeText(this, "The id"+uid+"and"+rid, Toast.LENGTH_SHORT).show();

        showmsg();



    }

    public void send(View view)
    {
        String msg=et1.getText().toString();

        try
        {
            obj.addMessage(uid,rid,msg);

            showmsg();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void showmsg()
    {
        Cursor c=obj.showMessage(uid,rid);

        try
        {
            messageAdapter.clear();

            while (c.moveToNext())
            {
                String msgid=c.getString(0);
                String senduid=c.getString(1);
                String receiveruid=c.getString(2);
                String msg=c.getString(3);

                MessageClass messageClass=new MessageClass(msgid,senduid,receiveruid,msg);
                messageAdapter.add(messageClass);
            }
            lv1.setAdapter(messageAdapter);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
