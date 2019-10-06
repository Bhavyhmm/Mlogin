package com.example.shaw.mlogin;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by HP on 10-03-2018.
 */

public class SlideAdapter extends PagerAdapter {


     Context context;
     LayoutInflater layoutInflater;

     public SlideAdapter(Context context){
         this.context=context;
     }


     //arrays


    public int[] slide_image={
             R.drawable.post,
            R.drawable.gossip2,
            R.drawable.chat

    };



     public String[] slide_head = {
         "Post",
        "Gossip",
        "Chat"
    };

    public String[] slide_desc = {

            "You can post on a feeds wall which could be seen by whole college"
                    ,
                    "You can comment which is now called gossip on a pic posted by your collegemates ",
                    "You can have one-to-one conversation with any person of your college "


    };

    @Override
    public int getCount() {
        return slide_head.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideimage= (ImageView) view.findViewById(R.id.slide_image);
        TextView slidehead = (TextView) view.findViewById(R.id.slide_head);
        TextView slidedesc = (TextView) view.findViewById(R.id.slide_desc);


        slideimage.setImageResource(slide_image[position]);
        slidehead.setText(slide_head[position]);
        slidedesc.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
