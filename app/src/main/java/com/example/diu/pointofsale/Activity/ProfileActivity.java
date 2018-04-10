package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.Model.User;
import com.example.diu.pointofsale.R;

public class ProfileActivity extends AppCompatActivity {
    private TextView name,email,gender,type,phone;
    private ImageView photo;
    private String userEmail;
    private byte[] userImage;
    private Intent intent;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        //getData();
        //setData();
    }

    //initialize values
    public void initViews(){
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        phone=(TextView)findViewById(R.id.phone);
        gender=(TextView)findViewById(R.id.gender);
        type=(TextView)findViewById(R.id.type);
        photo=(ImageView)findViewById(R.id.userImage);
    }
    //get data from intent
    public void getData(){
        intent=getIntent();
        userEmail=getIntent().getStringExtra("email");
        Toast.makeText(this, userEmail, Toast.LENGTH_LONG).show();
    }

    //setData
    public void setData(){
        User user=new User();
        myDb.getData(userEmail);
        name.setText(user.getUserName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        gender.setText(user.getGender());
        type.setText(user.getType());
        userImage=user.getPhoto();
        //convert byteArray to bitmap
        Bitmap bmp = BitmapFactory.decodeByteArray(userImage, 0, userImage.length);
        //set bitmap
        photo.setImageBitmap(bmp);
    }

}
