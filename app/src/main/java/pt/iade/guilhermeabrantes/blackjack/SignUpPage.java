package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.iade.guilhermeabrantes.blackjack.models.User;
import pt.iade.guilhermeabrantes.blackjack.retrofit.RetrofitService;
import pt.iade.guilhermeabrantes.blackjack.retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPage extends AppCompatActivity {

    private Button btnSignUp;
    private ImageButton btnBack;
    private EditText nameInputUp;
    private EditText emailInputUp;
    private EditText surnameInputUp;
    private EditText passwordInputUp;
    private EditText repassInputUp;
    private String name;
    private String surname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        nameInputUp = (EditText) findViewById(R.id.nameInputUp);
        emailInputUp = (EditText) findViewById(R.id.emailInputUp);
        passwordInputUp = (EditText) findViewById(R.id.passInputUp);
        repassInputUp = (EditText) findViewById(R.id.repassInputUp);
        surnameInputUp = (EditText) findViewById(R.id.surnameInputUp);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);


        btnSignUp.setOnClickListener(view -> {
            if (!validateName()) {
                nameInputUp.setError("Invalid Name!");
            } else if (!validateSurName()) {
                surnameInputUp.setError("Invalid Surname");
            } else if (!validateEmail(emailInputUp.getText().toString())) {
                emailInputUp.setError("Invalid Email!");
                emailInputUp.requestFocus();
            } else if (!validatePassword(passwordInputUp.getText().toString())) {
                passwordInputUp.setError("Invalid Password!\n" +
                        "At least 8 characters");
                passwordInputUp.requestFocus();
            } else if (!validateRepassword(repassInputUp.getText().toString(), passwordInputUp.getText().toString())) {
                repassInputUp.setError("The password does not match!\n");
            } else {
                String name = String.valueOf(nameInputUp.getText());
                String email = String.valueOf(emailInputUp.getText());
                String password = String.valueOf(passwordInputUp.getText());
                String surname = String.valueOf(surnameInputUp.getText());

                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setSurname(surname);

                userApi.save(user)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(SignUpPage.this,"Save Successful!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(SignUpPage.this,"Save Failed!!!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE,"Error Occurred",t);
                            }
                        });

                startActivity(new Intent(SignUpPage.this, SignInPage.class));
            }


        });
        btnBack.setOnClickListener(view -> startActivity(new Intent(SignUpPage.this, SignInPage.class)));
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
        if(!(password.isEmpty()) &&  password.length() > 7) {
            return true;
        } else {
            return false;
        }
    }
    protected Boolean validateRepassword(String repassword, String password) {
        if (!repassword.equals(password)) {
            return false;
        } else {
            return true;
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
    protected Boolean validateSurName() {

        surname = surnameInputUp.getText().toString();

        if (!(surname.isEmpty()) && surname.length() > 2) {
            return true;
        } else {
            return false;
        }
    }


}