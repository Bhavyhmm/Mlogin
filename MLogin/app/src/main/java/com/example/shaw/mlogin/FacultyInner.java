package com.example.shaw.mlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;


public class FacultyInner extends AppCompatActivity {

    int REQUEST_CODE_GALLERY=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_inner);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void event(View view)
    {
        startActivity(new Intent(FacultyInner.this,AddEvent.class));
    }
    public void material(View view)
    {
        Intent intent;
        intent = new Intent(Intent.ACTION_PICK);
        startActivityForResult(intent,REQUEST_CODE_GALLERY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((requestCode==REQUEST_CODE_GALLERY) && data!=null)
        {
            Toast.makeText(this, "Material uploaded successfully", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
