package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import pt.iade.guilhermeabrantes.blackjack.models.Session;
import pt.iade.guilhermeabrantes.blackjack.models.User;
import pt.iade.guilhermeabrantes.blackjack.retrofit.RetrofitService;
import pt.iade.guilhermeabrantes.blackjack.retrofit.SessionApi;
import pt.iade.guilhermeabrantes.blackjack.retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontPage extends AppCompatActivity {
    private static final int BLACKJACK_REQUEST_CODE = 2;
    private Button btnLogOff;
    private Button btnPlayBlack;
    private Button btnPlayWar;
    private Button btnPlayDice;
    private TextView playerCreditsTextView;
    private int totalCredits;
    private int userId, userCredits;
    private String userEmail, userPassword, userName, userSurname;
    private UserApi userApi;
    RetrofitService retrofitService = new RetrofitService();
    Retrofit retrofit = retrofitService.getRetrofit();
    SessionApi sessionApi = retrofit.create(SessionApi.class);

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        btnLogOff = (Button) findViewById(R.id.btnLogOff);
        btnPlayBlack = (Button) findViewById(R.id.btnBjPlay);
        btnPlayWar = (Button) findViewById(R.id.btnWgPlay);
        btnPlayDice = (Button) findViewById(R.id.btnDgPlay);
        playerCreditsTextView = (TextView) findViewById(R.id.playerCreditsTextView);

        RetrofitService retrofitService = new RetrofitService();
        userApi = retrofitService.getRetrofit().create(UserApi.class);

        userId = getIntent().getIntExtra("userInfo", 0);
        userCredits = getIntent().getIntExtra("userCreditsFromSign", 0);
        userEmail = getIntent().getStringExtra("userEmailFromSign");
        userPassword = getIntent().getStringExtra("userPasswordFromSign");
        userName = getIntent().getStringExtra("userNameFromSign");
        userSurname = getIntent().getStringExtra("userSurnameFromSign");

        if (getIntent().hasExtra("userId")) {
            userId = getIntent().getIntExtra("userId", 0);
            userCredits = getIntent().getIntExtra("userCredits", 0);
            userEmail = getIntent().getStringExtra("userEmail");
            userPassword = getIntent().getStringExtra("userPassword");
            userName = getIntent().getStringExtra("userName");
            userSurname = getIntent().getStringExtra("userSurname");

            updateTotalCredits(userCredits);
        }

        userApi.getUserById(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    playerCreditsTextView.setText("Credits: " + user.getCredits());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(FrontPage.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
            }
        });

        totalCredits = user.getCredits();

        btnLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveFrontPage();
                startActivity(new Intent(FrontPage.this, SignInPage.class));

            }
        });
        btnPlayBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = new Session();
                sessionApi.save(session)
                        .enqueue(new Callback<Session>() {
                            @Override
                            public void onResponse(Call<Session> call, Response<Session> response) {
                                Toast.makeText(FrontPage.this, "Welcome to Black Jack.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Session> call, Throwable t) {
                                Toast.makeText(FrontPage.this, "Oops,something went wrong!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                            }
                        });


                userApi.getUserById(userId).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User detailedUser = response.body();
                            userCredits = detailedUser.getCredits();
                            userEmail = detailedUser.getEmail();
                            userPassword = detailedUser.getPassword();
                            userName = detailedUser.getName();
                            userSurname = detailedUser.getSurname();
                            navigateToBlackjack();
                        } else {
                            Toast.makeText(FrontPage.this, "Failed to load user details", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(FrontPage.this, "Failed to load user details", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                    }
                });
            }
        });
        btnPlayWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = new Session();
                sessionApi.save(session)
                        .enqueue(new Callback<Session>() {
                            @Override
                            public void onResponse(Call<Session> call, Response<Session> response) {
                                Toast.makeText(FrontPage.this, "Welcome to War Game.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Session> call, Throwable t) {
                                Toast.makeText(FrontPage.this, "Oops,something went wrong!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                            }
                        });
                userApi.getUserById(userId).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User detailedUser = response.body();
                            userCredits = detailedUser.getCredits();
                            userEmail = detailedUser.getEmail();
                            userPassword = detailedUser.getPassword();
                            userName = detailedUser.getName();
                            userSurname = detailedUser.getSurname();
                            navigateToWarGame();
                        } else {
                            Toast.makeText(FrontPage.this, "Failed to load user details", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(FrontPage.this, "Oops,something went wrong!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                    }
                });
            }
        });
        btnPlayDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = new Session();
                sessionApi.save(session)
                        .enqueue(new Callback<Session>() {
                            @Override
                            public void onResponse(Call<Session> call, Response<Session> response) {
                                Toast.makeText(FrontPage.this, "Welcome to Dice Game.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Session> call, Throwable t) {
                                Toast.makeText(FrontPage.this, "Oops,something went wrong!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                            }
                        });
                userApi.getUserById(userId).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User detailedUser = response.body();
                            userCredits = detailedUser.getCredits();
                            userEmail = detailedUser.getEmail();
                            userPassword = detailedUser.getPassword();
                            userName = detailedUser.getName();
                            userSurname = detailedUser.getSurname();
                            navigateToDiceGame();
                        } else {
                            Toast.makeText(FrontPage.this, "Failed to load user details", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(FrontPage.this, "Oops,something went wrong!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                    }
                });
            }
        });
    }
    private void navigateToBlackjack() {
        Intent intent = new Intent(FrontPage.this, BlackJack.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userCredits", userCredits);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("userPassword", userPassword);
        intent.putExtra("userName", userName);
        intent.putExtra("userSurname", userSurname);
        FrontPage.this.startActivity(intent);
    }

    private void navigateToWarGame() {
        Intent intent = new Intent(FrontPage.this, WarGame.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userCredits", userCredits);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("userPassword", userPassword);
        intent.putExtra("userName", userName);
        intent.putExtra("userSurname", userSurname);
        FrontPage.this.startActivity(intent);
    }

    private void navigateToDiceGame() {
        Intent intent = new Intent(FrontPage.this, DiceGame.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userCredits", userCredits);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("userPassword", userPassword);
        intent.putExtra("userName", userName);
        intent.putExtra("userSurname", userSurname);
        FrontPage.this.startActivity(intent);
    }

    private void updateTotalCredits(int credits) {
        // Atualize o texto da TextView com os créditos do usuário
        playerCreditsTextView.setText("Credits: " + credits);
    }
    private void updateUserInfo(User detailedUser) {
        userCredits = detailedUser.getCredits();
        userEmail = detailedUser.getEmail();
        userPassword = detailedUser.getPassword();
        userName = detailedUser.getName();
        userSurname = detailedUser.getSurname();
    }

    private void leaveFrontPage() {
        // Crie uma Intent para retornar dados, se necessário
        Intent resultIntent = new Intent();
        resultIntent.putExtra("resultKey", "dados de resultado, se necessário");

        // Define o resultado da atividade como RESULT_OK e anexa a Intent de resultado
        setResult(RESULT_OK, resultIntent);

        // Finaliza a atividade
        finish();
    }
}