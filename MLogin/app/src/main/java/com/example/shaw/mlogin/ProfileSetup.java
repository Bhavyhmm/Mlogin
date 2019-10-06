package com.example.shaw.mlogin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.camera2.CaptureRequest;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ProfileSetup extends AppCompatActivity {

    Button bt3,/*bt4*/bt5;
    ImageView iv1;
    CoordinatorLayout coordinator;
    int REQUEST_CODE_GALLERY=999;
    private static final int CAMERA_REQUEST = 0;

    myClass obj;
    String name="",password="",email="",prn="",phone="",branch="",birthcity="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
        bt3=findViewById(R.id.bt3);
        /*bt4=findViewById(R.id.bt4);*/
        bt5=findViewById(R.id.bt5);
        iv1=findViewById(R.id.iv1);
        coordinator=findViewById(R.id.coordinator);
        obj=new myClass(this);

        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        password=getIntent().getStringExtra("password");
        prn=getIntent().getStringExtra("prn");
        phone=getIntent().getStringExtra("phone");
        branch=getIntent().getStringExtra("branch");
        birthcity=getIntent().getStringExtra("birthcity");
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });
        /*bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });*/
        /*bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileSetup.this,MainActivity.class);
                startActivity(intent);
               // overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
            }
        });*/
    }

    public void signup(View view)
    {

        //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

        try
        {
            //Toast.makeText(this, name+" "+email+" "+password+" "+prn+" "+phone+ ""+branch, Toast.LENGTH_SHORT).show();
            obj.adduser(name,password,email,prn,phone,branch,birthcity,imageviewtobyte(iv1));
            //Toast.makeText(this, "User Added Successfully", Toast.LENGTH_SHORT).show();
            Snackbar snackbar=Snackbar.make(coordinator,"User signup successful",Snackbar.LENGTH_INDEFINITE).setAction("SIGN IN", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ProfileSetup.this,LoginAct.class));
                    overridePendingTransition(R.anim.slideinleft,R.anim.slideoutright);
                }
            });
            snackbar.show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((requestCode==REQUEST_CODE_GALLERY) && resultCode==RESULT_OK && data!=null)
        {
            Uri uri=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                iv1.setImageBitmap(bitmap);

            }
            catch (Exception ex)
            {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //byte format

    public  byte[] imageviewtobyte(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] sarray=stream.toByteArray();

        return  sarray;
    }
}
