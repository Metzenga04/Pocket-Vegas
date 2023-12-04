package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {

    private Button btnSignUp;
    private EditText nameInputUp;
    private EditText emailInputUp;
    private EditText passwordInputUp;
    private EditText repassInputUp;

    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        nameInputUp = (EditText) findViewById(R.id.nameInputUp);
        emailInputUp = (EditText) findViewById(R.id.emailInputUp);
        passwordInputUp = (EditText) findViewById(R.id.passInputUp);
        repassInputUp = (EditText) findViewById(R.id.repassInputUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateName()) {
                    nameInputUp.setError("Invalid Name!");
                } else if (!validateEmail(emailInputUp.getText().toString())) {
                    emailInputUp.setError("Invalid Email!");
                    emailInputUp.requestFocus();
                } else if (!validatePassword(passwordInputUp.getText().toString())) {
                    passwordInputUp.setError("Invalid Password!\n" +
                            "At least 9 letters");
                    passwordInputUp.requestFocus();
                } else {
                    startActivity(new Intent(SignUpPage.this, SignInPage.class));
                }
            }
        });
    }


    protected boolean validateEmail(String email) {

        String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`" +
                "{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*" +
                "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]" +
                "?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-" +
                "\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    protected Boolean validatePassword(String password){
        if(!(password.isEmpty()) &&  password.length() > 8) {
            return true;
        } else {
            return false;
        }
    }
    protected Boolean validateName(){

        name = nameInputUp.getText().toString();

        if(!(name.isEmpty()) && name.length() > 2) {
            return true;
        } else {
            return false;
        }
    }


}