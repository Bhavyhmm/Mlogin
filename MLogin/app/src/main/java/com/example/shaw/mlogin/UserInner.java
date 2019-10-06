package com.example.shaw.mlogin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class UserInner extends AppCompatActivity {

    private TextView mTextMessage;
    ListView lv1;
    myClass obj;

    String uid="",cmid="";
    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";
    public static final String Branch="branchkey";
    PostAdapter postAdapter;

    ArrayList<String> list1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    startActivity(new Intent(UserInner.this,AddPost.class));
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    startActivity(new Intent( UserInner.this,ShowuUser.class));
                    return true;
                case R.id.navigation_event:
                    // mTextMessage.setText(R.string.title_dashboard);
                    startActivity(new Intent( UserInner.this,ShowEvent.class));
                    return true;

                case R.id.navigation_notifications:
                    startActivity(new Intent(UserInner.this,ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {

            startActivity(new Intent(UserInner.this,BranchWisePost.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inner);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        obj=new myClass(this);
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");
        lv1=findViewById(R.id.postlv1);
        list1=new ArrayList<String>();
        Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      /*  Cursor c=obj.showpost("select count(postid) from post");
        try
        {
            //
            // Toast.makeText(this, c.getCount(), Toast.LENGTH_SHORT).show();
            String m="";
            while(c.moveToNext()) {
             m = c.getString(0);
            }
            Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
        disppost();

    }
    public void disppost() {
        Cursor c = obj.showpost("select p.pimage,u.uname,p.pdatetime,p.pdesc,p.postid,u.uprofile from student u,post p where p.uid=u.uid");

        //Toast.makeText(this, c.getCount(), Toast.LENGTH_SHORT).show();
        postAdapter = new PostAdapter(UserInner.this, R.layout.row_post);
        try {
            while (c.moveToNext()) {

                byte[] pimage = c.getBlob(0);
                byte[] uimage = c.getBlob(5);
                //Bitmap bitmap=BitmapFactory.decodeByteArray(uimage,0,uimage.length);

                String uname = c.getString(1);
                String pdate = c.getString(2);
                String pdesc = c.getString(3);
                String pid = c.getString(4);
                list1.add(pid);
                Cursor c1 = obj.showpost("select count(cmid) from comment where postid=" + pid);
                try {
                    while (c1.moveToNext()) {
                        cmid = c1.getString(0);
                    }
                } catch (Exception ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                PostClass postClass = new PostClass(pid, uimage, pimage, uname, pdate, pdesc, cmid);
                postAdapter.add(postClass);

            }
            lv1.setAdapter(postAdapter);

            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /* //   Toast.makeText(UserInner.this, "Hello"+i, Toast.LENGTH_SHORT).show();

                   // String m=list1.get(i);

                    //startActivity(new Intent(UserInner.this,BranchWisePost.class));
                   // Toast.makeText(UserInner.this, uid+""+m, Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder ab=new AlertDialog.Builder(UserInner.this);
                    ab.setTitle("Hello");
                    AlertDialog ad=ab.create();
                    ad.show();*/


                }
            });

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }    }
