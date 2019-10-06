package com.example.shaw.mlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    String uid="";

    int REQUEST_CODE_GALLERY=999;

    SharedPreferences sharedPreferences;
    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";

    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    Button bt11,bt22,bt33;
    ImageView image1;
    myClass obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
        textView10=findViewById(R.id.textView10);
        image1=findViewById(R.id.image1);
        bt11=findViewById(R.id.bt11);
        bt22=findViewById(R.id.bt22);
        bt33=findViewById(R.id.bt33);

        obj=new myClass(this);
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");

        Cursor c=obj.profileuser(uid);

        try
        {
            while (c.moveToNext())
            {
                String name=c.getString(1);
                String email=c.getString(3);
                String prn=c.getString(4);
                String phone=c.getString(5);
                String branch=c.getString(6);
                textView6.setText(name);
                textView7.setText(email);
                textView8.setText(prn);
                textView9.setText(phone);
                textView10.setText(branch);
                byte[] uimage=c.getBlob(8);
                Bitmap bitmap= BitmapFactory.decodeByteArray(uimage,0,uimage.length);
                image1.setImageBitmap(bitmap);
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        bt22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,LoginAct.class));
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });

        bt33.setOnClickListener(new View.OnClickListener() {
            Cursor c1=obj.profileuser(uid);
            @Override
            public void onClick(View view) {
                try {
                    while (c1.moveToNext()) {
                        String name = c1.getString(1);
                        String password = c1.getString(2);
                        String email = c1.getString(3);
                        String prn = c1.getString(4);
                        String phone = c1.getString(5);
                        String branch = c1.getString(6);
                        String birthcity = c1.getString(7);
                        obj.updateuser(uid,name,password,email,prn,phone,branch,birthcity, imageviewtobyte(image1));
                        Toast.makeText(ProfileActivity.this, "Profile has been updated", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(ProfileActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((requestCode==REQUEST_CODE_GALLERY))
        {
            Uri uri=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                image1.setImageBitmap(bitmap);

            }
            catch (Exception ex)
            {
                Toast.makeText(ProfileActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public  byte[] imageviewtobyte(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] sarray=stream.toByteArray();

        return  sarray;
    }
}
