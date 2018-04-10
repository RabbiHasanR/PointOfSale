package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.Model.User;
import com.example.diu.pointofsale.R;
import com.example.diu.pointofsale.Validation.InputValidation;

import java.sql.Date;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity =SignUpActivity.this;

    private NestedScrollView nestedScrollView;
    private AppCompatSpinner genderSpinner,userTypeSpinner;
    private AppCompatButton signUp;
    private AppCompatTextView appCompatTextViewLoginLink;
    private TextInputEditText userName,email,password,confirmPassword,userPhone;
    private TextInputLayout layoutForName,layoutForEmail,layoutForPassword,layoutForConfirmPassword,layoutForPhone;
    private String userType,gender;
    private DatabaseHelper myDb;
    private User user;
    private InputValidation inputValidation;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findView();
        initListeners();
        initObjects();

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(this);
        myDb = new DatabaseHelper(this);
        user = new User();


    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        signUp.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.signUp:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                intent=new Intent(".Activity.SignInActivity");
                startActivity(intent);
                break;
        }
    }

    //findViewById
    private void findView() {
        nestedScrollView=(NestedScrollView)findViewById(R.id.nestedScrollView);
        genderSpinner = (AppCompatSpinner) findViewById(R.id.gender_spinner);
        userTypeSpinner = (AppCompatSpinner) findViewById(R.id.userType_spinner);
        signUp=(AppCompatButton) findViewById(R.id.signUp);
        userName=(TextInputEditText) findViewById(R.id.userName);
        email=(TextInputEditText) findViewById(R.id.email);
        password=(TextInputEditText) findViewById(R.id.userPassword);
        confirmPassword=(TextInputEditText)findViewById(R.id.userConfirmPassword);
        userPhone=(TextInputEditText) findViewById(R.id.userPhone);
        layoutForName=(TextInputLayout)findViewById(R.id.userNameLayout);
        layoutForEmail=(TextInputLayout)findViewById(R.id.userEmailLayout);
        layoutForPassword=(TextInputLayout)findViewById(R.id.userPasswordLayout);
        layoutForConfirmPassword=(TextInputLayout)findViewById(R.id.userConfirmPasswordLayout);
        layoutForPhone=(TextInputLayout)findViewById(R.id.userPhoneLayout);
        appCompatTextViewLoginLink=(AppCompatTextView)findViewById(R.id.appCompatTextViewLoginLink);
        setGenderSpinnerValue();
        setUserTypeSpinnerValue();

    }

    //setUserTypeSpinnerValue
    private void setUserTypeSpinnerValue() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.userType_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        userTypeSpinner.setAdapter(adapter1);

        userTypeSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        userType=userTypeSpinner.getItemAtPosition(position).toString();
                        userType=(String)parent.getItemAtPosition(position);
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

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(userName, layoutForName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(email, layoutForEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(email, layoutForEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password, layoutForPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(password, confirmPassword,
                layoutForConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(userPhone, layoutForPhone, getString(R.string.error_message_name))) {
            return;
        }

        if (!myDb.checkUser(email.getText().toString().trim())) {

            user.setUserName(userName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setPhone(userPhone.getText().toString());
            user.setGender(gender);
            user.setType(userType);



            myDb.insertUserInfo(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        userName.setText(null);
        email.setText(null);
        password.setText(null);
        confirmPassword.setText(null);
        userPhone.setText(null);
    }
}
