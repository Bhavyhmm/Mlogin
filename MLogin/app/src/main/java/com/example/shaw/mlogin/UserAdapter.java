package com.example.shaw.mlogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

/**
 * Created by HP on 16-03-2018.
 */

public class UserAdapter extends ArrayAdapter {
    public UserAdapter(@NonNull Context context, int resource) {
        super(context, resource);
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
            row=layoutInflater.inflate(R.layout.row_user,parent,false);
            contactHolder=new ContactHolder();
            contactHolder.t1=(TextView)row.findViewById(R.id.ust1);
            contactHolder.image1=(ImageView)row.findViewById(R.id.muimage);
            row.setTag(contactHolder);

        }
        else
        {
            contactHolder=(ContactHolder)row.getTag();
        }
        UserClass userClass=(UserClass)this.getItem(position);
        contactHolder.t1.setText(userClass.getUname());

        byte[]uimage=userClass.getUimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(uimage,0,uimage.length);
        contactHolder.image1.setImageBitmap(bitmap);
        return row;

    }

    public static  class ContactHolder
    {
        ImageView image1;
        TextView t1;


    }
}
