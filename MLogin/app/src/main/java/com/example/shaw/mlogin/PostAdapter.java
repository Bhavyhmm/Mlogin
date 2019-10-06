package com.example.shaw.mlogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 15-03-2018.
 */



public class PostAdapter extends ArrayAdapter{
    Context ctx;
    myClass obj;

    PostClass postClass;
    String uid="",pid="",p="";
    public LayoutInflater inflater;
    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";

    ArrayList<String> list1,list2;
    ArrayAdapter arrayAdapter;


    public PostAdapter(@NonNull Context context, int resource) {

        super(context, resource);
        this.ctx=context;
        obj=new myClass(ctx);
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row=convertView;
        ContactHolder contactHolder;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_post,parent,false);
            inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            contactHolder=new ContactHolder();
            contactHolder.ut1=(TextView)row.findViewById(R.id.uname);
            contactHolder.pt1=(TextView)row.findViewById(R.id.pt1);
            contactHolder.pt2=(TextView)row.findViewById(R.id.pdesc);
            contactHolder.ct1=(TextView)row.findViewById(R.id.cmnt);
            contactHolder.uimage=(ImageView)row.findViewById(R.id.uimage);
            contactHolder.pimage=(ImageView)row.findViewById(R.id.pimage);
            contactHolder.ib1=(ImageButton)row.findViewById(R.id.gcmt1);
            contactHolder.sb1=(ImageButton)row.findViewById(R.id.scmt1);
            contactHolder.ib1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pid=postClass.getPid();
                    // Toast.makeText(ctx, "pid:"+pid, Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(ctx, "Hello", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                    final View dialogview=inflater.inflate(R.layout.alert_post,null);
                    builder.setView(dialogview);
                    final EditText et1=(EditText)dialogview.findViewById(R.id.postet1);
                    builder.setTitle("User Comment");
                    builder.setMessage("Add Your Comment");

                    builder.setPositiveButton("Add Comment", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            String msg=et1.getText().toString();
                            try
                            {
                                if(obj.addcomment(uid,pid,msg))
                                {
                                    Toast.makeText(ctx, uid+""+pid+msg, Toast.LENGTH_SHORT).show();
                                }

                            }
                            catch (Exception ex)
                            {
                                Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    });

                    AlertDialog ad=builder.create();
                    ad.show();
                }
            });

            contactHolder.sb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    pid=postClass.getPid();
                    //Toast.makeText(ctx, "ppid:"+pid, Toast.LENGTH_SHORT).show();
                    //

                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                    final View dialogview=inflater.inflate(R.layout.show_comment,null);
                    builder.setView(dialogview);
                    final ListView lv1=(ListView)dialogview.findViewById(R.id.sclv1);
                    builder.setMessage("Show User Comment");
                    Cursor c=obj.showcomment("select u.uname,c.message from comment c,student u,post p where u.uid=c.uid and c.postid="+pid);
                    // Cursor c=obj.showcomment("select count(cmid) from comment");
                    String m ="";

                    try
                    {
                        list1.clear();
                        // arrayAdapter.clear();
                        while (c.moveToNext())
                        {
                            // m = c.getString(0);
                            //   Toast.makeText(ctx, "the value is "+m, Toast.LENGTH_SHORT).show();
                            list1.add(c.getString(0)+" : "+c.getString(1));
                        }
                        arrayAdapter=new ArrayAdapter(ctx,android.R.layout.simple_list_item_1,list1);
                        lv1.setAdapter(arrayAdapter);

                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                   /* builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                          //  Cursor c=obj.showcomment("select u.uname,c.message from comment c,student u where u.uid=c.uid");
                            Cursor c=obj.showcomment("select count(cmid) from comment");

                            Toast.makeText(ctx, c.getCount(), Toast.LENGTH_SHORT).show();
                            try
                            {
                                while (c.moveToNext())
                                {
                                    list1.add(c.getString(0)+" : "+c.getString(1));
                                }
                                ArrayAdapter arrayAdapter=new ArrayAdapter(ctx,android.R.layout.simple_list_item_1,list1);
                                lv1.setAdapter(arrayAdapter);
                            }
                            catch (Exception ex)
                            {
                                Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog ad=builder.create();
                    ad.show();


                }
            });
           /* android.view.ViewGroup.LayoutParams layoutParams=contactHolder.uimage.getLayoutParams();
            layoutParams.width=200;
            layoutParams.height=200;
            contactHolder.uimage.setLayoutParams(layoutParams);*/
           /* android.view.ViewGroup.LayoutParams layoutParams1=contactHolder.pimage.getLayoutParams();
            layoutParams1.width=900;
            layoutParams1.height=100;
            contactHolder.pimage.setLayoutParams(layoutParams1);*/
            row.setTag(contactHolder);

        }
        else
        {
            contactHolder=(ContactHolder)row.getTag();
        }
        postClass=(PostClass) this.getItem(position);
        contactHolder.ut1.setText(postClass.getUname());
        contactHolder.ct1.setText(postClass.getPcmnt());
        contactHolder.pt1.setText(postClass.getPdatetime());
        contactHolder.pt2.setText(postClass.getPdesc());

        Toast.makeText(ctx, "the id: "+pid, Toast.LENGTH_SHORT).show();
        //p=(postClass.getPid())this.getItem(position);

        sharedPreferences=getContext() .getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");

        byte[]uimage=postClass.getUimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(uimage,0,uimage.length);
        contactHolder.uimage.setImageBitmap(bitmap);
        byte[]pimage=postClass.getPimage();
        Bitmap bitmap1= BitmapFactory.decodeByteArray(pimage,0,pimage.length);
        contactHolder.pimage.setImageBitmap(bitmap1);

        return row;
    }
    public static  class ContactHolder
    {
        TextView ut1,pt1,pt2,ct1;
        ImageView uimage,pimage;
        ImageButton ib1,sb1;
    }
}
