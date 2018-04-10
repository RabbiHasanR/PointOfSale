package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.Model.User;
import com.example.diu.pointofsale.R;
import com.example.diu.pointofsale.Validation.InputValidation;

import java.util.ArrayList;
import java.util.List;

public class UpdateUserActivity extends AppCompatActivity {
   // private List<CharSequence> options;
    private NestedScrollView nestedScrollView;
    private TextInputLayout layoutForName,layoutForEmail,layoutForPassword,layoutForPhone;
    private TextInputEditText userName,userEamil,userPassword,userPhone;
    private AppCompatSpinner genderSpinner,typeSpinner;
    private String name,email,password,phone,gender,type;
    private Intent intent;
    private User user;
    private DatabaseHelper myDb;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initViews();
        initObject();
    }

    /**
     * initializ view method
     */
    public void initViews(){
        nestedScrollView=(NestedScrollView)findViewById(R.id.nestedScrollViewForUpdate);
        layoutForName=(TextInputLayout)findViewById(R.id.userUpdateNameLayout);
        layoutForEmail=(TextInputLayout)findViewById(R.id.userUpdateEmailLayout) ;
        layoutForPassword=(TextInputLayout)findViewById(R.id.userUpdatePasswordLayout);
        layoutForPhone=(TextInputLayout)findViewById(R.id.userUpdatePhoneLayout);
        userName=(TextInputEditText)findViewById(R.id.updateUserName);
        userEamil=(TextInputEditText)findViewById(R.id.updateEmail);
        userPassword=(TextInputEditText)findViewById(R.id.userUpdatePassword);
        userPhone=(TextInputEditText)findViewById(R.id.userUpdatePhone);
        genderSpinner=(AppCompatSpinner) findViewById(R.id.gender_spinnerUpdate);
        typeSpinner=(AppCompatSpinner) findViewById(R.id.userType_spinnerUpdate);
        setGenderSpinnerValue();
        setUserTypeSpinnerValue();
    }

    /**
     * initilaize object
     */
    public void initObject(){
        intent = getIntent();
         name=getIntent().getStringExtra("name");
         email=getIntent().getStringExtra("email");
         password=getIntent().getStringExtra("password");
         phone=getIntent().getStringExtra("phone");
         gender=getIntent().getStringExtra("gender");
         type=getIntent().getStringExtra("type");
        Toast.makeText(this, name+email+password+phone+gender+type, Toast.LENGTH_LONG).show();

         setData();

    }
    //setUserTypeSpinnerValue
    private void setUserTypeSpinnerValue() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.userType_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        typeSpinner.setAdapter(adapter1);
        if(type!=null){
            int spinnerPosition = adapter1.getPosition(type);
            typeSpinner.setSelection(spinnerPosition);
        }


        typeSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type=typeSpinner.getItemAtPosition(position).toString();
                        type=(String)parent.getItemAtPosition(position);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    //setGenderSpinnerValue
    private void setGenderSpinnerValue() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(adapter);
        if(gender!=null){
            int spinnerPosition = adapter.getPosition(gender);
            genderSpinner.setSelection(spinnerPosition);
        }

        genderSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        gender=genderSpinner.getItemAtPosition(position).toString();
                        gender=(String)parent.getItemAtPosition(position);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    //retrive data from sqlite and set on editText
    public void setData(){

        //setData
        userName.setText(name);
        userEamil.setText(email);
        userPassword.setText(password);
        userPhone.setText(phone);
    }

    private void postUpdateDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(userName, layoutForName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(userEamil, layoutForEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(userEamil, layoutForEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(userPassword, layoutForPassword, getString(R.string.error_message_password))) {
            return;
        }
//        if (!inputValidation.isInputEditTextMatches(password, confirmPassword,
//                layoutForConfirmPassword, getString(R.string.error_password_match))) {
//            return;
//        }

        if (!inputValidation.isInputEditTextFilled(userPhone, layoutForPhone, getString(R.string.error_message_phone))) {
            return;
        }

        if (!myDb.checkUser(userEamil.getText().toString().trim())) {

            user.setUserName(userName.getText().toString());
            user.setEmail(userEamil.getText().toString());
            user.setPassword(userPassword.getText().toString());
            user.setPhone(userPhone.getText().toString());
            user.setGender(gender);
            user.setType(type);



            myDb.updateUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.update_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText() {
        userName.setText(null);
        userEamil.setText(null);
        userPassword.setText(null);
        userPhone.setText(null);
    }
}
