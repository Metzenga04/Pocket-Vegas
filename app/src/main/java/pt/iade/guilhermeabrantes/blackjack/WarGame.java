package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import pt.iade.guilhermeabrantes.blackjack.models.Deck;
import pt.iade.guilhermeabrantes.blackjack.models.User;
import pt.iade.guilhermeabrantes.blackjack.retrofit.RetrofitService;
import pt.iade.guilhermeabrantes.blackjack.retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WarGame extends AppCompatActivity {

    private ImageView iv_card_left, iv_card_right;
    private TextView tv_score_left, tv_score_right, totalCredits, betResult, pCredits, cCredits;
    private Button deal, leaveTable, start, ok;
    private Deck deck;
    private SeekBar creditsBar;
    private int leftScore, rightScore, playerBet, userCredits, userId;
    private String userEmail, userPassword, userName, userSurname;
    RetrofitService retrofitService = new RetrofitService();
    Retrofit retrofit = retrofitService.getRetrofit();
    UserApi userApi = retrofit.create(UserApi.class);
    User user = new User();

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_game);

        creditsBar = (SeekBar) findViewById(R.id.seekBarCreditsWG);
        betResult = (TextView) findViewById(R.id.playerBetWG);
        pCredits = (TextView) findViewById(R.id.playerCredits);
        cCredits = (TextView) findViewById(R.id.cpuCredits);
        totalCredits = (TextView) findViewById(R.id.totalCreditsWG);
        tv_score_left = (TextView) findViewById(R.id.tv_score_left);
        tv_score_right = (TextView) findViewById(R.id.tv_score_right);
        iv_card_left = (ImageView) findViewById(R.id.iv_card_left);
        iv_card_right = (ImageView) findViewById(R.id.iv_card_right);
        deal = (Button) findViewById(R.id.btnDeal);
        leaveTable = (Button) findViewById(R.id.btnLeaveWG);
        start = (Button) findViewById(R.id.btnStartWG);
        ok = (Button) findViewById(R.id.btnOkWG);

        deck = new Deck();

        leaveTable.setVisibility(View.VISIBLE);
        totalCredits.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
        creditsBar.setVisibility(View.VISIBLE);
        betResult.setVisibility(View.VISIBLE);
        iv_card_left.setVisibility(View.GONE);
        iv_card_right.setVisibility(View.GONE);
        tv_score_left .setVisibility(View.GONE);
        tv_score_right.setVisibility(View.GONE);
        deal.setVisibility(View.GONE);
        pCredits.setVisibility(View.GONE);
        cCredits.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", 0);
        userCredits = intent.getIntExtra("userCredits", 0);
        userEmail = intent.getStringExtra("userEmail");
        userPassword = intent.getStringExtra("userPassword");
        userName = intent.getStringExtra("userName");
        userSurname = intent.getStringExtra("userSurname");

        updateTotalCredits(userCredits);

        creditsBar.setMax(userCredits);
        creditsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int credits, boolean b) {
                betResult.setText("Bet: " + credits);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                betResult.setText("Bet: " + seekBar.getProgress());
                playerBet = seekBar.getProgress();
                seekBar.setMax(userCredits);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (playerBet == 0 || playerBet > userCredits) {
                    Toast.makeText(WarGame.this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                    return;
                }

                leaveTable.setVisibility(View.GONE);
                totalCredits.setVisibility(View.VISIBLE);
                iv_card_left.setVisibility(View.VISIBLE);
                iv_card_right.setVisibility(View.VISIBLE);
                tv_score_left .setVisibility(View.VISIBLE);
                tv_score_right.setVisibility(View.VISIBLE);
                deal.setVisibility(View.VISIBLE);
                pCredits.setVisibility(View.VISIBLE);
                cCredits.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                creditsBar.setVisibility(View.GONE);
                betResult.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);


                userCredits -= playerBet;
                updateTotalCredits(userCredits);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                leaveTable.setVisibility(View.VISIBLE);
                totalCredits.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
                creditsBar.setVisibility(View.VISIBLE);
                betResult.setVisibility(View.VISIBLE);
                iv_card_left.setVisibility(View.GONE);
                iv_card_right.setVisibility(View.GONE);
                tv_score_left .setVisibility(View.GONE);
                tv_score_right.setVisibility(View.GONE);
                deal.setVisibility(View.GONE);
                pCredits.setVisibility(View.GONE);
                cCredits.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);

            }
        });

        deal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                leaveTable.setVisibility(View.VISIBLE);
                totalCredits.setVisibility(View.VISIBLE);
                iv_card_left.setVisibility(View.VISIBLE);
                iv_card_right.setVisibility(View.VISIBLE);
                tv_score_left .setVisibility(View.VISIBLE);
                tv_score_right.setVisibility(View.VISIBLE);
                pCredits.setVisibility(View.VISIBLE);
                cCredits.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                deal.setVisibility(View.GONE);
                creditsBar.setVisibility(View.GONE);
                betResult.setVisibility(View.GONE);

                dealCards();
            }
        });
        leaveTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveUpdatedCredits();
                Intent intentLeave = new Intent(WarGame.this, FrontPage.class);
                intentLeave.putExtra("userId", userId);
                intentLeave.putExtra("userCredits", userCredits);
                intentLeave.putExtra("userEmail", userEmail);
                intentLeave.putExtra("userPassword", userPassword);
                intentLeave.putExtra("userName", userName);
                intentLeave.putExtra("userSurname", userSurname);
                WarGame.this.startActivity(intentLeave);

            }
        });
    }

    private void dealCards() {
        int playerCard = deck.drawCard();
        int cpuCard = deck.drawCard();

        int playerImage = getResources().getIdentifier( "card" + playerCard, "drawable", getPackageName());
        iv_card_left.setImageResource(playerImage);

        int cpuImage = getResources().getIdentifier("card" + cpuCard, "drawable", getPackageName());
        iv_card_right.setImageResource(cpuImage);

        updateScores(playerCard, cpuCard);
    }

    private void updateScores(int playerCard, int cpuCard) {
        if (playerCard > cpuCard) {
            leftScore++;
            tv_score_left.setText(String.valueOf(leftScore));
            userCredits += 2 * playerBet;
            updateTotalCredits(userCredits);
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        } else if (playerCard < cpuCard) {
            rightScore++;
            tv_score_right.setText(String.valueOf(rightScore));
            Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
        } else {
            userCredits += playerBet;
            updateTotalCredits(userCredits);
            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateTotalCredits(int credits) {
        totalCredits.setText("Credits: " + credits);
    }
    private void saveUpdatedCredits() {
        User user = new User();
        user.setId(userId);
        user.setCredits(userCredits);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setName(userName);
        user.setSurname(userSurname);

        userApi.save(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(WarGame.this, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WarGame.this, "Failed to save credits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(WarGame.this, "Failed to save credits", Toast.LENGTH_SHORT).show();
                Logger.getLogger(WarGame.class.getName()).log(Level.SEVERE, "Error Occurred", t);
            }
        });
    }
}