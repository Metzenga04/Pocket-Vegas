package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.iade.guilhermeabrantes.blackjack.models.User;
import pt.iade.guilhermeabrantes.blackjack.retrofit.RetrofitService;
import retrofit2.Retrofit;
import pt.iade.guilhermeabrantes.blackjack.retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPage extends AppCompatActivity {

    private Button btnRegister;
    private Button btnSignIn;
    private EditText emailInputIn;
    private EditText passwordInputIn;
    RetrofitService retrofitService = new RetrofitService();
    Retrofit retrofit = retrofitService.getRetrofit();
    private UserApi userApi;

    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);

        emailInputIn = (EditText) findViewById(R.id.emailInputIn);
        passwordInputIn = (EditText) findViewById(R.id.passInputIn);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        RetrofitService retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        UserApi userApi = retrofit.create(UserApi.class);
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
                    passwordInputIn.setError("Invalid Password!\n" + "At least 8 characters");
                } else {
                    String email = String.valueOf(emailInputIn.getText());
                    String password = String.valueOf(passwordInputIn.getText());
                    userApi.getAllUsers().enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if (response.isSuccessful()) {
                                List<User> userList = response.body();
                                handleLoginResponse(userList, email, password);
                            } else {
                                // Resposta sem sucesso
                                Toast.makeText(SignInPage.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(SignInPage.this, "Failed to load user data", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(SignInPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                        }
                    });
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
        if(!(password.isEmpty()) && password.length() > 7) {
            return true;
        } else {
            return false;
        }
    }
    private void handleLoginResponse(List<User> userList, String email, String password) {
        boolean loginSuccessful = false;
        int userId = 0;

        if (userList != null) {
            for (User user : userList) {
                if (user != null && user.getEmail() != null && user.getPassword() != null) {
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        userId = user.getId();
                        loginSuccessful = true;
                        break;
                    }
                }
            }
        }

        if (loginSuccessful) {
            navigateToNextActivity(userId);
        } else {
            // Credenciais inválidas ou lista de usuários nula
            Toast.makeText(SignInPage.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
    private void navigateToNextActivity(int userId) {
        Toast.makeText(SignInPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignInPage.this, FrontPage.class);
        intent.putExtra("userInfo", userId);
        SignInPage.this.startActivity(intent);
    }

    private void saveUpdatedCredits(User user) {
        userApi.save(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Créditos do usuário atualizados com sucesso
                    Toast.makeText(SignInPage.this, "Credits updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Trate o caso em que a resposta não foi bem-sucedida
                    // Exemplo: exibir uma mensagem de erro
                    Toast.makeText(SignInPage.this, "Failed to update credits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Trate o caso de falha na chamada
                // Exemplo: exibir uma mensagem de erro
                Toast.makeText(SignInPage.this, "Failed to update credits", Toast.LENGTH_SHORT).show();
                Logger.getLogger(SignInPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
            }
        });
    }

}