package com.example.quiz;

import static com.example.quiz.Shared.DATE_REGEX;
import static com.example.quiz.Shared.EMAIL_REGEX;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        configureButton();
        go=(Button) findViewById(R.id.b5);
        go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SignUp.this, Home.class);
                        startActivity(intent);
            }});
        };

    /**
     * Configures the submit button to listen for click events
     */
    protected void configureButton(){
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener((View v) -> {
            if(validInputs())
                finish();
        });
    }

    /**
     * Higher level function that encapsulates all the validation functions into one
     * @return
     */
    protected boolean validInputs(){
        return validName() && validDate() && validEmail() && validPassword() ;
    }

    /**
     * Validates the email making sure its at least 5 characters long
     * @return
     */
    protected boolean validPassword(){
        TextInputLayout passwdText = findViewById(R.id.signup_password);
        String passwd = passwdText.getEditText().getText().toString();
        boolean flag = passwd.length()>=5;
        if(!flag) passwdText.setError("Password must be at least 5 characters long");
        if(flag && passwdText.getError() != null) passwdText.setError(null);
        return flag;
    }

    /**
     * Validates the email with the use of a regular expression found online
     * @return
     */
    protected boolean validEmail(){
        TextInputLayout emailText = findViewById(R.id.signup_email);
        String email = emailText.getEditText().getText().toString().trim();
        boolean flag = EMAIL_REGEX.matcher(email).matches();

        if(!flag) emailText.setError("Invalid email");
        if(flag && emailText.getError() != null) emailText.setError(null);

        return flag;
    }

    /**
     * Validates both first name and last name to be in the range of [3,30] characters
     * @return
     */
    protected boolean validName(){
        TextInputLayout fNameText = findViewById(R.id.first_name);
        TextInputLayout lNameText = findViewById(R.id.last_name);
        String fname = fNameText.getEditText().getText().toString().trim();
        String lname = lNameText.getEditText().getText().toString().trim();
        boolean fnameFlag = fname.length()>3 &&lname.length()<=30;
        boolean lnameFlag =  lname.length()>3 && fname.length()<=30;

        if(!fnameFlag) fNameText.setError("First name must be at least 3 characters and no more than 30");
        if(fnameFlag && fNameText.getError() != null) fNameText.setError(null);

        if(!lnameFlag) lNameText.setError("Last name must be at least 3 characters and no more than 30");
        if(lnameFlag && lNameText.getError() != null) lNameText.setError(null);

        return fnameFlag && lnameFlag;
    }

    /**
     * Validates the date format as mm/dd/yyyy
     * @return
     */
    protected boolean validDate(){
        TextInputLayout birthDateText = findViewById(R.id.birthDate);
        String birthdate = birthDateText.getEditText().getText().toString();
        boolean flag = DATE_REGEX.matcher(birthdate).matches();
        if(!flag) birthDateText.setError("Wrong date format");
        if(flag && birthDateText.getError() != null) birthDateText.setError(null);
        return flag;
    }

}
