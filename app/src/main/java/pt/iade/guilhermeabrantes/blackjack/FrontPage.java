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

        if (getIntent().hasExtra("userId")) {
            userId = getIntent().getIntExtra("userId", 0);
            userCredits = getIntent().getIntExtra("userCredits", 0);
            userEmail = getIntent().getStringExtra("userEmail");
            userPassword = getIntent().getStringExtra("userPassword");
            userName = getIntent().getStringExtra("userName");
            userSurname = getIntent().getStringExtra("userSurname");

            // Atualiza a interface do usuário com os novos dados
            updateTotalCredits(userCredits);
        }

        RetrofitService retrofitService = new RetrofitService();
        userApi = retrofitService.getRetrofit().create(UserApi.class);

        userId = getIntent().getIntExtra("userInfo", 0);
        userApi.getUserById(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    playerCreditsTextView.setText("Crédits: " + user.getCredits());
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

                RetrofitService retrofitService = new RetrofitService();
                userApi = retrofitService.getRetrofit().create(UserApi.class);
                int userId = getIntent().getIntExtra("userInfo", 0);
                userApi.getUserById(userId).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User detailedUser = response.body();
                            navigateToBlackjackActivity(userId, detailedUser.getCredits(),detailedUser.getEmail(),detailedUser.getPassword(),detailedUser.getName(),detailedUser.getSurname());
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
                startActivity(new Intent(FrontPage.this, WarGame.class));
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
                startActivity(new Intent(FrontPage.this, DiceGame.class));
            }
        });
    }
    private void navigateToBlackjackActivity(int userId, int userCredits, String userEmail, String userPassword, String userName, String userSurname) {
        Intent intent = new Intent(FrontPage.this, BlackJack.class);
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
}