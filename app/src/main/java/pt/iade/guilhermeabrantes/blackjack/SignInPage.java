package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPage extends AppCompatActivity {

    private Button btnRegister;
    private Button btnSignIn;
    private EditText emailInputIn;
    private EditText passwordInputIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);

        emailInputIn = (EditText) findViewById(R.id.emailInputIn);
        passwordInputIn = (EditText) findViewById(R.id.passInputIn);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPage.this, SignUpPage.class);
                startActivity(intent);
            }
        });

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail(emailInputIn.getText().toString())) {
                    emailInputIn.setError("Invalid Email");
                } else if (!validatePassword(passwordInputIn.getText().toString())) {
                    passwordInputIn.setError("Invalid Password!\n" + "At least 9 characters");
                } else {
                    startActivity(new Intent(SignInPage.this, FrontPage.class));
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
        if(!(password.isEmpty()) && password.length() > 8) {
            return true;
        } else {
            return false;
        }
    }
}