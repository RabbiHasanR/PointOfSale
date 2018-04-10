package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.R;

public class WellcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity =WellcomeActivity.this;
    private AppCompatButton signIn,signUp;
    private Intent intent;
    private DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        initViews();
        initListners();
    }

    //initialize view
    public void initViews(){
        signIn=(AppCompatButton)findViewById(R.id.user_signIn);
        signUp=(AppCompatButton)findViewById(R.id.user_signUp);
    }

    /**
     * initialize object
     */
    public void initObject(){
        myDb=new DatabaseHelper(this);
    }

    public void initListners(){
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_signIn:
                 intent=new Intent(".Activity.SignInActivity");
                startActivity(intent);
                break;
            case R.id.user_signUp:
                intent=new Intent(".Activity.SignUpActivity");
                startActivity(intent);
                break;
        }

    }
}
