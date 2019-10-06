package com.example.shaw.mlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BranchWisePost extends AppCompatActivity {

    private TextView mTextMessage;
    ListView lv1;
    myClass obj;

    String uid="",cmid="",branch="";
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
                    startActivity(new Intent(BranchWisePost.this,AddPost.class));
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    startActivity(new Intent( BranchWisePost.this,ShowuUser.class));
                    return true;
                case R.id.navigation_event:
                  //  mTextMessage.setText(R.string.title_notifications);
                    startActivity(new Intent( BranchWisePost.this,ShowEvent.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(BranchWisePost.this,ProfileActivity.class));
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_wise_post);
        Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        obj=new myClass(this);
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");
        branch=sharedPreferences.getString(Branch,"");
        lv1=findViewById(R.id.blv1);
        list1=new ArrayList<String>();

        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        disppost();
    }

    public void disppost()
    {
        Cursor c=obj.showpost("select p.pimage,u.uname,p.pdatetime,p.pdesc,p.postid,u.uprofile from student u,post p where p.uid=u.uid and u.ubranch='"+branch+"'");

        //Toast.makeText(this, c.getCount(), Toast.LENGTH_SHORT).show();
        postAdapter=new PostAdapter(BranchWisePost.this,R.layout.row_post);
        try
        {
            while (c.moveToNext())
            {

                byte[] pimage=c.getBlob(0);
                byte[] uimage=c.getBlob(5);
                //Bitmap bitmap=BitmapFactory.decodeByteArray(uimage,0,uimage.length);

                String uname=c.getString(1);
                String pdate=c.getString(2);
                String pdesc=c.getString(3);
                String pid=c.getString(4);
                list1.add(pid);
                Cursor c1=obj.showpost("select count(cmid) from comment where postid="+pid);
                try
                {
                    while (c1.moveToNext())
                    {
                        cmid=c1.getString(0);
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                PostClass postClass=new PostClass(pid,uimage,pimage,uname,pdate,pdesc,cmid);
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

        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
