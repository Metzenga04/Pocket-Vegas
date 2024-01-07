package pt.iade.guilhermeabrantes.blackjack;

import pt.iade.guilhermeabrantes.blackjack.models.Dices;
import pt.iade.guilhermeabrantes.blackjack.models.User;
import pt.iade.guilhermeabrantes.blackjack.retrofit.RetrofitService;
import pt.iade.guilhermeabrantes.blackjack.retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiceGame extends AppCompatActivity {

    private Button roll, leave, start, stay, ok;
    private SeekBar creditsBar;
    private TextView betResult, totalCredits;
    private int playerBet, clickCounter,userCredits, userId;
    private String userEmail, userPassword, userName, userSurname;
    RetrofitService retrofitService = new RetrofitService();
    Retrofit retrofit = retrofitService.getRetrofit();
    UserApi userApi = retrofit.create(UserApi.class);
    User user = new User();
    List<Dices> pHand = new ArrayList<>();
    List<Dices> dHand = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        creditsBar = (SeekBar) findViewById(R.id.seekBarCreditsDG);

        start = (Button) findViewById(R.id.btnStartDG);
        leave = (Button) findViewById(R.id.btnLeaveDG);
        stay = (Button) findViewById(R.id.btnStayDG);
        ok = (Button) findViewById(R.id.btnOkDG);
        roll = (Button) findViewById(R.id.btnRollDice);

        betResult = (TextView)  findViewById(R.id.playerBetDG);
        totalCredits = (TextView) findViewById(R.id.totalCreditsDG);

        ImageView dice1 = findViewById(R.id.playerDice);
        ImageView dice2 = findViewById(R.id.playerDice2);
        ImageView dice3 = findViewById(R.id.playerDice3);
        ImageView dice4 = findViewById(R.id.playerDice4);
        ImageView dice5 = findViewById(R.id.playerDice5);

        ImageView dDice1 = findViewById(R.id.dealerDice);
        ImageView dDice2 = findViewById(R.id.dealerDice2);
        ImageView dDice3 = findViewById(R.id.dealerDice3);
        ImageView dDice4 = findViewById(R.id.dealerDice4);
        ImageView dDice5 = findViewById(R.id.dealerDice5);

        TextView pPoints = findViewById(R.id.playerPoints);
        TextView dPoints = findViewById(R.id.dealerPoints);
        TextView dTypeOfPoints = findViewById(R.id.typeOfPointsD);
        TextView pTypeOfPoints = findViewById(R.id.typeOfPointsP);

        start.setVisibility(View.VISIBLE);
        roll.setVisibility(View.GONE);
        stay.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        pTypeOfPoints.setVisibility(View.GONE);
        dTypeOfPoints.setVisibility(View.GONE);
        dPoints.setVisibility(View.GONE);
        pPoints.setVisibility(View.GONE);
        dice1.setVisibility(View.GONE);
        dice2.setVisibility(View.GONE);
        dice3.setVisibility(View.GONE);
        dice4.setVisibility(View.GONE);
        dice5.setVisibility(View.GONE);
        dDice1.setVisibility(View.GONE);
        dDice2.setVisibility(View.GONE);
        dDice3.setVisibility(View.GONE);
        dDice4.setVisibility(View.GONE);
        dDice5.setVisibility(View.GONE);

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
            @Override
            public void onProgressChanged(SeekBar seekBar, int credits, boolean b) {
                betResult.setText("Bet: " + credits);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                betResult.setText("Bet: " + seekBar.getProgress());
                playerBet = seekBar.getProgress();
                seekBar.setMax(userCredits);
            }
        });

        leave.setOnClickListener(v -> {
            Intent intentLeave = new Intent(DiceGame.this, FrontPage.class);
            intentLeave.putExtra("userId", userId);
            intentLeave.putExtra("userCredits", userCredits);
            intentLeave.putExtra("userEmail", userEmail);
            intentLeave.putExtra("userPassword", userPassword);
            intentLeave.putExtra("userName", userName);
            intentLeave.putExtra("userSurname", userSurname);

            DiceGame.this.startActivity(intentLeave);
        });

        start.setOnClickListener(v -> {
            if (playerBet == 0 || playerBet > userCredits) {
                Toast.makeText(this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                return;
            }

            roll.setVisibility(View.VISIBLE);
            leave.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
            betResult.setVisibility(View.GONE);
            creditsBar.setVisibility(View.GONE);

            clickCounter = 0;
            userCredits -= playerBet;
            updateTotalCredits(userCredits);
        });

        stay.setOnClickListener(v -> {
            pTypeOfPoints.setVisibility(View.VISIBLE);
            dTypeOfPoints.setVisibility(View.VISIBLE);
            stay.setVisibility(View.GONE);

            Dices first = new Dices();
            Dices second = new Dices();
            Dices third = new Dices();
            Dices fourth = new Dices();
            Dices fifth = new Dices();

            dHand.add(first);
            dHand.add(second);
            dHand.add(third);
            dHand.add(fourth);
            dHand.add(fifth);

            dDice1.setImageResource(getResources().getIdentifier(first.getName(), "drawable", getPackageName()));
            dDice2.setImageResource(getResources().getIdentifier(second.getName(), "drawable", getPackageName()));
            dDice3.setImageResource(getResources().getIdentifier(third.getName(), "drawable", getPackageName()));
            dDice4.setImageResource(getResources().getIdentifier(fourth.getName(), "drawable", getPackageName()));
            dDice5.setImageResource(getResources().getIdentifier(fifth.getName(), "drawable", getPackageName()));

            dDice1.setVisibility(View.VISIBLE);
            dDice2.setVisibility(View.VISIBLE);
            dDice3.setVisibility(View.VISIBLE);
            dDice4.setVisibility(View.VISIBLE);
            dDice5.setVisibility(View.VISIBLE);

            int pSum = chance(pHand);

            if (isYatzeeScore(pHand)) {
                pSum = 50;
                pTypeOfPoints.setText("Yatzee!!!!");
            } else if (isLargeStraight(pHand)) {
                pSum=40;
                pTypeOfPoints.setText("Large Straight!!");
            } else if (isSmallStraight(pHand)) {
                if (pSum<=30){
                    pSum= 30;
                    pTypeOfPoints.setText("Small Straight!");
                } else {
                    pTypeOfPoints.setText("Chance");
                }
            } else if (isFullHouseScore(pHand)) {
                if (pSum<=25){
                    pSum= 25;
                    pTypeOfPoints.setText("Full House!");
                } else {
                    pTypeOfPoints.setText("Chance");
                }
            } else if (isFourOfAKind(pHand)) {
                pTypeOfPoints.setText("Four of a kind!");
            } else if(isThreeOfAKind(pHand)) {
                pTypeOfPoints.setText("Three of a kind!");
            } else {
                pTypeOfPoints.setText("Chance");
            }

            int dSum = chance(dHand);

            if (isYatzeeScore(dHand)) {
                dSum = 50;
                dTypeOfPoints.setText("Yatzee!!!!");
            } else if (isLargeStraight(dHand)) {
                dSum=40;
                dTypeOfPoints.setText("Large Straight!!");
            } else if (isSmallStraight(dHand)) {
                if (dSum<=30){
                    dSum= 30;
                    dTypeOfPoints.setText("Small Straight!");
                } else {
                    dTypeOfPoints.setText("Chance");
                }
            } else if (isFullHouseScore(dHand)) {
                if (dSum<=25){
                    dSum= 25;
                    dTypeOfPoints.setText("Full House!");
                } else {
                    dTypeOfPoints.setText("Chance");
                }
            } else if (isFourOfAKind(dHand)) {
                dTypeOfPoints.setText("Four of a kind!");
            } else if(isThreeOfAKind(dHand)) {
                dTypeOfPoints.setText("Three of a kind!");
            } else {
                dTypeOfPoints.setText("Chance");
            }

            pPoints.setText("Player: " + pSum);
            pPoints.setVisibility(View.VISIBLE);
            dPoints.setText("Dealer: " + dSum);
            dPoints.setVisibility(View.VISIBLE);

            if (pSum > dSum) {
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
                userCredits += 2 * playerBet;
                updateTotalCredits(userCredits);
            } else if (pSum == dSum){
                Toast.makeText(this, "Dead End!", Toast.LENGTH_SHORT).show();
                userCredits += playerBet;
                updateTotalCredits(userCredits);
            }else{
                Toast.makeText(this, "You Lose", Toast.LENGTH_SHORT).show();
            }

            ok.setVisibility(View.VISIBLE);
        });

        roll.setOnClickListener(v -> {
            clickCounter++;

            pHand.clear();
            dHand.clear();

            stay.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);

            if (clickCounter == 3) {
                roll.setVisibility(View.GONE);
            }

            Dices first = new Dices();
            Dices second = new Dices();
            Dices third = new Dices();
            Dices fourth = new Dices();
            Dices fifth = new Dices();

            pHand.add(first);
            pHand.add(second);
            pHand.add(third);
            pHand.add(fourth);
            pHand.add(fifth);

            dice1.setImageResource(getResources().getIdentifier(first.getName(), "drawable", getPackageName()));
            dice2.setImageResource(getResources().getIdentifier(second.getName(), "drawable", getPackageName()));
            dice3.setImageResource(getResources().getIdentifier(third.getName(), "drawable", getPackageName()));
            dice4.setImageResource(getResources().getIdentifier(fourth.getName(), "drawable", getPackageName()));
            dice5.setImageResource(getResources().getIdentifier(fifth.getName(), "drawable", getPackageName()));

            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.VISIBLE);
            dice3.setVisibility(View.VISIBLE);
            dice4.setVisibility(View.VISIBLE);
            dice5.setVisibility(View.VISIBLE);

            pTypeOfPoints.setVisibility(View.VISIBLE);
            pTypeOfPoints.setText(typeOfCombination(pHand));

        });

        ok.setOnClickListener(v -> {
            saveUpdatedCredits(userId, userCredits, userEmail, userPassword, userName, userSurname);

            pHand.clear();
            dHand.clear();

            betResult.setVisibility(View.VISIBLE);
            creditsBar.setVisibility(View.VISIBLE);
            start.setVisibility(View.VISIBLE);
            leave.setVisibility(View.VISIBLE);
            roll.setVisibility(View.GONE);
            ok.setVisibility(View.GONE);
            dice1.setVisibility(View.GONE);
            dice2.setVisibility(View.GONE);
            dice3.setVisibility(View.GONE);
            dice4.setVisibility(View.GONE);
            dice5.setVisibility(View.GONE);
            dDice1.setVisibility(View.GONE);
            dDice2.setVisibility(View.GONE);
            dDice3.setVisibility(View.GONE);
            dDice4.setVisibility(View.GONE);
            dDice5.setVisibility(View.GONE);
            dPoints.setVisibility(View.GONE);
            pPoints.setVisibility(View.GONE);
            dTypeOfPoints.setVisibility(View.GONE);
            pTypeOfPoints.setVisibility(View.GONE);
        });
    }

    private String typeOfCombination(List<Dices> hand) {
        if (isYatzeeScore(pHand)) {
            return "Yatzee!!!!";
        } else if (isLargeStraight(pHand)) {
            return "Large Straight!!";
        } else if (isSmallStraight(pHand)) {
            return "Small Straight!";
        } else if (isFullHouseScore(pHand)) {
            return "Full House!";
        } else if (isFourOfAKind(pHand)) {
            return "Four of a kind!";
        } else if(isThreeOfAKind(pHand)) {
            return "Three of a kind!";
        } else {
            return "Chance";
        }
    }
    private boolean isYatzeeScore(List<Dices> hand) {
        Map<Integer, Integer> rankCount = new HashMap<>();

        for (Dices dice : hand) {
            int rank = dice.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        for (int count : rankCount.values()) {
            if (count >= 5) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouseScore(List<Dices> hand) {
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Dices dices : hand) {
            int rank = dices.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        boolean hasThreeOfAKind = false;
        boolean hasTwoOfAKind = false;

        for (int count : rankCount.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasTwoOfAKind = true;
            }
        }
        return hasThreeOfAKind && hasTwoOfAKind;
    }

    private boolean isThreeOfAKind(List<Dices> hand) {
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Dices dices : hand) {
            int rank = dices.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        for (int count : rankCount.values()) {
            if (count == 3) {
                return true;
            }
        }
        return false;
    }
    private boolean isFourOfAKind(List<Dices> hand) {
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Dices dices : hand) {
            int rank = dices.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        for (int count : rankCount.values()) {
            if (count == 4) {
                return true;
            }
        }
        return false;
    }
    private boolean isSmallStraight(List<Dices> hand) {
        hand.sort(Comparator.comparingInt(Dices::getRank));

        int consecutiveCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i + 1).getRank() == hand.get(i).getRank() + 1) {
                consecutiveCount++;
                if (consecutiveCount >= 3) {
                    return true;
                }
            } else if (hand.get(i + 1).getRank() != hand.get(i).getRank()) {
                consecutiveCount = 0;
            }
        }
        return false;
    }
    private boolean isLargeStraight(List<Dices> hand) {
        Collections.sort(hand, Comparator.comparingInt(Dices::getRank));

        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i + 1).getRank() != hand.get(i).getRank() + 1) {
                return false;
            }
        }
        return true;
    }
    private int chance(List<Dices> hand) {
        int sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).getRank();
        }
        return sum;
    }
    private void updateTotalCredits(int credits) {
        totalCredits.setText("Credits: " + credits);
    }
    private void saveUpdatedCredits(int userId, int credits, String email, String password, String name, String surname) {
        User user = new User();
        user.setId(userId);
        user.setCredits(credits);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);

        userApi.save(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                } else {
                    Toast.makeText(DiceGame.this, "Failed to save credits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DiceGame.this, "Failed to save credits", Toast.LENGTH_SHORT).show();
                Logger.getLogger(DiceGame.class.getName()).log(Level.SEVERE, "Error Occurred", t);
            }
        });
    }
}