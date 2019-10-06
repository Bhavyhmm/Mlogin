package com.example.shaw.mlogin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminInner extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv1;
    Spinner sp1;

    ArrayList<String> list1,list2,list;

    String m="";
    myClass obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv1=findViewById(R.id.alv1);
        sp1=findViewById(R.id.asp1);
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        list=new ArrayList<String>();
        obj=new myClass(this);

        list.add("Select");
        list.add("Architecture");
        list.add("Computer");
        list.add("Civil");
        list.add("EC");
        list.add("Mechanical");
        list.add("IT");
        list.add("PCT");
        list.add("Electrical");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(AdminInner.this,R.layout.support_simple_spinner_dropdown_item,list);
        sp1.setAdapter(arrayAdapter1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                m=list.get(i);

                Toast.makeText(AdminInner.this, m, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Cursor c=obj.showuser();

        try {

            list2.clear();
            list1.clear();
            while (c.moveToNext())
            {
                list1.add(c.getString(1));
                list2.add(c.getString(0));
            }

            ArrayAdapter arrayAdapter=new ArrayAdapter(AdminInner.this,android.R.layout.simple_list_item_1,list1);
            lv1.setAdapter(arrayAdapter);
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String m=list2.get(i);
                    Intent intent= new Intent(AdminInner.this,DetailAct.class);
                    intent.putExtra("val1",m);
                    startActivity(intent);
                }
            });
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_inner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(AdminInner.this,LoginAct.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user) {
            startActivity(new Intent(AdminInner.this,AdminInner.class));
            // Handle the camera action
        } else if (id == R.id.nav_faculty) {
            startActivity(new Intent(AdminInner.this,AddFaculty.class));

        }
        else if (id == R.id.manage_faculty) {
            startActivity(new Intent(AdminInner.this,ShowFaculty.class));
        } else if (id == R.id.nav_logout) {
        Intent intent =new Intent(AdminInner.this,LoginAct.class);
        startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void search(View view)
    {
        Cursor c1=obj.branchwise("select * from student where ubranch='"+m+"'");
        try {

            list1.clear();
            list2.clear();
            while (c1.moveToNext())
            {
                list1.add(c1.getString(1));
                list2.add(c1.getString(0));
            }

            ArrayAdapter arrayAdapter=new ArrayAdapter(AdminInner.this,android.R.layout.simple_list_item_1,list1);
            lv1.setAdapter(arrayAdapter);
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String m=list2.get(i);
                    Intent intent= new Intent(AdminInner.this,DetailAct.class);
                    intent.putExtra("val",m);
                    startActivity(intent);
                }
            });
        }
        catch (Exception ex)
        {
            Toast.makeText(AdminInner.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }

}
