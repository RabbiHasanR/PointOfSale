package com.example.diu.pointofsale.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diu.pointofsale.Database.DatabaseHelper;
import com.example.diu.pointofsale.Model.User;
import com.example.diu.pointofsale.R;
import com.example.diu.pointofsale.Validation.InputValidation;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity =SignInActivity.this;
    private AppCompatSpinner userTypeSpinner;
    private TextInputEditText email,password;
    private TextInputLayout layoutForEmail,layoutForPassword;
    private Button signIn;
    private AppCompatTextView signUp;
    private String userType;
    private DatabaseHelper myDb;
    private InputValidation inputValidation;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findView();
        initObjects();
        initListner();
    }
    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        myDb=new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

    }

    /**
     * This method is to initialize views
     */
    private void findView() {
        userTypeSpinner = (AppCompatSpinner) findViewById(R.id.userType_spinner);
        email=(TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        password=(TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        layoutForEmail=(TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        layoutForPassword=(TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        signIn=(Button)findViewById(R.id.signIn);
        signUp=(AppCompatTextView)findViewById(R.id.signUpTextView);
        setUserTypeSpinnerValue();
    }

    /**
     * This method is initialize listner
     */
    public void initListner(){
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

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


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        String userEmail=email.getText().toString().trim();
        String userPassword=password.getText().toString().trim();

        if (!inputValidation.isInputEditTextFilled(email, layoutForEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(email, layoutForEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password, layoutForPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (myDb.verifyLogin(userEmail,userPassword,userType)) {
            if(userType.equalsIgnoreCase("Admin")){
                 intent = new Intent(".Activity.AdminHomeActivity");
                intent.putExtra("EMAIL", email.getText().toString().trim());
                Toast.makeText(this, "successfull with admin", Toast.LENGTH_SHORT).show();
                emptyInputEditText();
                startActivity(intent);
            }
            else {
                 intent = new Intent(".Activity.UserHomeActivity");
                //intent.putExtra("EMAIL", email.getText().toString().trim());
                Toast.makeText(this, "successfull with salesman", Toast.LENGTH_SHORT).show();
                emptyInputEditText();
                startActivity(intent);
            }



        } else {
            // Snack Bar to show success message that record is wrong
            //Snackbar.make( getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            Toast.makeText(this,getString(R.string.error_valid_email_password), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        email.setText(null);
        password.setText(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                verifyFromSQLite();
                break;
            case R.id.signUp:
                intent=new Intent(".Activity.SignUpActivity");
                startActivity(intent);
                break;

        }

    }
}
