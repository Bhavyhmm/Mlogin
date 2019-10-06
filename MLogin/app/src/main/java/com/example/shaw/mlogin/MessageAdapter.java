package com.example.shaw.mlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HP on 16-03-2018.
 */

public class MessageAdapter extends ArrayAdapter {
    Context context;

    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";
    public  static  final String RId="rkey";
    String suid="",ruid="";

    public MessageAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        sharedPreferences=getContext() .getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        ruid=sharedPreferences.getString(UserID,"");
        suid=sharedPreferences.getString(RId,"");

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
        ContcatHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_row_layout_odd,parent,false);
            contactHolder=new ContcatHolder();
            contactHolder.tx_name=(TextView) row.findViewById(R.id.senderuser);
            row.setTag(contactHolder);

        }
        else
        {
            contactHolder=(ContcatHolder) row.getTag();
        }
        MessageClass messageClass=(MessageClass) this.getItem(position);

        //contactHolder.tx_name.setText(messageClass.getMsg());

       if(ruid.equals(messageClass.getRuid()) && suid.equals(messageClass.getSuid())) {

            contactHolder.tx_name.setText(messageClass.getMsg());
           //contactHolder.tx_name.setText("Rid and Sid combnation");
           // contactHolder.tx_name.setTextColor(Color.CYAN);
        }
        else if(ruid.equals(messageClass.getSuid()) && suid.equals(messageClass.getRuid()))
        //(suid.equals(messageClass.getSuid())
        {
            contactHolder.tx_name.setText(messageClass.getMsg());
            //contactHolder.tx_name.setText("Sid and Rid Combination");
            //contactHolder.tx_name.setTextColor(Color.BLACK);

        }
        return row;

    }

    public static class ContcatHolder
    {
        TextView tx_name;

    }
}
