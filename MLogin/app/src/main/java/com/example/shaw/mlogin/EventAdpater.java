package com.example.shaw.mlogin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by HP on 19-03-2018.
 */

public class EventAdpater extends ArrayAdapter {
    public EventAdpater(@NonNull Context context, int resource) {
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
            row=layoutInflater.inflate(R.layout.row_event,parent,false);
            contactHolder=new ContactHolder();
            contactHolder.tx_name=(TextView)row.findViewById(R.id.ret1);
            contactHolder.tx_date=(TextView)row.findViewById(R.id.ret2);
            contactHolder.tx_time=(TextView)row.findViewById(R.id.ret3);
            contactHolder.tx_desc=(TextView)row.findViewById(R.id.ret4);
            row.setTag(contactHolder);

        }
        else
        {
            contactHolder=(ContactHolder)row.getTag();
        }

        EventClass eventClass=(EventClass)this.getItem(position);
        contactHolder.tx_name.setText(eventClass.getEname());
        contactHolder.tx_date.setText(eventClass.getEdate());
        contactHolder.tx_time.setText(eventClass.getEtime());
        contactHolder.tx_desc.setText(eventClass.getEdesc());

        return  row;
    }

    public static class ContactHolder
    {
        TextView tx_name,tx_date,tx_time,tx_desc;
    }
}
