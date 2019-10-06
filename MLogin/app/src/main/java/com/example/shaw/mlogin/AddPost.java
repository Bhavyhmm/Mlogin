package com.example.shaw.mlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddPost extends AppCompatActivity {

    int REQUEST_CODE_GALLERY=999;
    EditText et1;
    ImageView image1;
    Button btn1,btn2;
    myClass obj;

    String uid="";
    SharedPreferences sharedPreferences;

    public static final String Mypreferences="MyLogin";
    public  static final String UserID="userkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        et1=(EditText)findViewById(R.id.postet1);
        btn1=(Button)findViewById(R.id.pbtn1);
        btn2=(Button)findViewById(R.id.pbtn2);
        image1=(ImageView)findViewById(R.id.pimage1);
        obj=new myClass(this);
        sharedPreferences=getSharedPreferences(Mypreferences, Context.MODE_PRIVATE);
        uid=sharedPreferences.getString(UserID,"");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    String pname=et1.getText().toString();
                    obj.addpost(uid,pname,imageviewtobyte(image1));
                    Toast.makeText(AddPost.this, "the"+uid+" "+pname, Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddPost.this, "Post Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddPost.this,UserInner.class));
                }
                catch (Exception ex)
                {
                    Toast.makeText(AddPost.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                };
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null)
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
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] imageviewtobyte(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] sarray=stream.toByteArray();
        return sarray;
    }


}
