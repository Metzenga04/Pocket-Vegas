package pt.iade.guilhermeabrantes.blackjack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.iade.guilhermeabrantes.blackjack.models.Dices;

public class DiceGame extends AppCompatActivity {

    private Button btnRollDice;
    private Button btnLeave;
    private Button btnStartDG;
    private Button btnStay;
    private Button ok;
    private Button btnRollDiceAgain;
    private Button btnRollDiceAgain2;
    private SeekBar creditsBarDG;
    private TextView creditsResultDG, totalCreditsDG;
    private int playerBetDG, maxBetDG;
    List<Dices> pHand = new ArrayList<>();
    List<Dices> dHand = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        creditsBarDG = (SeekBar) findViewById(R.id.seekBarCreditsDG);
        creditsResultDG = (TextView) findViewById(R.id.playerBetDG);
        totalCreditsDG = (TextView) findViewById(R.id.totalCreditsDG);
        maxBetDG = creditsBarDG.getMax();
        btnStartDG = (Button) findViewById(R.id.btnStardDG);
        btnRollDice = (Button) findViewById(R.id.btnRollDice);
        btnLeave = (Button) findViewById(R.id.btnLeave);
        btnStay = (Button) findViewById(R.id.btnStay);
        ok = (Button) findViewById(R.id.btnOkDG);
        btnRollDiceAgain = (Button) findViewById(R.id.btnRollAgain);
        btnRollDiceAgain2 = (Button) findViewById(R.id.btnRollAgain2);

        ImageView dice1 = findViewById(R.id.playerDice);
        ImageView dice2 = findViewById(R.id.playerDice2);
        ImageView dice3 = findViewById(R.id.playerDice3);
        ImageView dice4 = findViewById(R.id.playerDice4);
        ImageView dice5 = findViewById(R.id.playerDice5);

        TextView playerPoints = findViewById(R.id.player_points);
        TextView dealerPoints = findViewById(R.id.dealer_points);
        TextView typeOfPointsD = findViewById(R.id.typeOfPointsD);
        TextView typeOfPointsP = findViewById(R.id.typeOfPointsP);

        ImageView dDice1 = findViewById(R.id.dealerDice);
        ImageView dDice2 = findViewById(R.id.dealerDice2);
        ImageView dDice3 = findViewById(R.id.dealerDice3);
        ImageView dDice4 = findViewById(R.id.dealerDice4);
        ImageView dDice5 = findViewById(R.id.dealerDice5);

        btnStartDG.setVisibility(View.VISIBLE);
        btnRollDice.setVisibility(View.GONE);
        btnStay.setVisibility(View.GONE);
        btnRollDiceAgain.setVisibility(View.GONE);
        btnRollDiceAgain2.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        typeOfPointsP.setVisibility(View.GONE);
        typeOfPointsD.setVisibility(View.GONE);
        dealerPoints.setVisibility(View.GONE);
        playerPoints.setVisibility(View.GONE);
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

        creditsBarDG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int credits, boolean b) {
                creditsResultDG.setText("Apostar: " + credits);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                creditsResultDG.setText("Apostar: " + seekBar.getProgress());
                playerBetDG = seekBar.getProgress();
                seekBar.setMax(maxBetDG);
            }
        });

        btnLeave.setOnClickListener(v -> {
            startActivity(new Intent(DiceGame.this, FrontPage.class));
        });
        btnStartDG.setOnClickListener(v -> {
            if (playerBetDG == 0) {
                Toast.makeText(this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                return;
            } else if (playerBetDG > maxBetDG) {
                Toast.makeText(this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                return;
            }
            btnStartDG.setVisibility(View.GONE);
            btnRollDice.setVisibility(View.VISIBLE);
            btnLeave.setVisibility(View.GONE);
            creditsResultDG.setVisibility(View.GONE);
            creditsBarDG.setVisibility(View.GONE);
        });
        btnStay.setOnClickListener(v -> {
            btnStay.setVisibility(View.GONE);
            btnRollDiceAgain.setVisibility(View.GONE);
            btnRollDiceAgain2.setVisibility(View.GONE);
            typeOfPointsP.setVisibility(View.VISIBLE);
            typeOfPointsD.setVisibility(View.VISIBLE);

            maxBetDG -= playerBetDG;
            totalCreditsDG.setText("Créditos: " + String.valueOf(maxBetDG));

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

            int pSum = 0;
            if (calculateYatzeeScore(pHand)) {
                pSum = 50;
                typeOfPointsP.setText("Yatzee!!!!");
            } else if (isLargeStraight(pHand)) {
                    pSum=40;
                    typeOfPointsP.setText("Large Straight!!");
            } else if (isSmallStraight(pHand)) {
                if (pSum<=30){
                    pSum= 30;
                    typeOfPointsP.setText("Small Straight!");
                }else {
                    pSum= chance(pHand);
                    typeOfPointsP.setText("Chance");
                }
            } else if (calculateFullHouseScore(pHand)) {
                if (pSum<=25){
                    pSum= 25;
                    typeOfPointsP.setText("Full House!");
                }else {
                    pSum= chance(pHand);
                    typeOfPointsP.setText("Chance");
                }
            } else if (isFourOfAKind(pHand)) {
                typeOfPointsP.setText("Four of a kind!");
                pSum= chance(pHand);
            } else if(isThreeOfAKind(pHand)) {
                typeOfPointsP.setText("Three of a kind!");
                pSum= chance(pHand);
            }else {
                    pSum= chance(pHand);
                    typeOfPointsP.setText("Chance");
                }
            int dSum = 0;
            if (calculateYatzeeScore(dHand)) {
                dSum = 50;
                typeOfPointsD.setText("Yatzee!!!!");
            } else if (isLargeStraight(dHand)) {
                dSum=40;
                typeOfPointsD.setText("Large Straight!!");
            } else if (isSmallStraight(dHand)) {
                if (dSum<=30){
                    dSum= 30;
                    typeOfPointsD.setText("Small Straight!");
                }else {
                    dSum= chance(dHand);
                    typeOfPointsD.setText("Chance");
                }
            } else if (calculateFullHouseScore(dHand)) {
                if (dSum<=25){
                    dSum= 25;
                    typeOfPointsD.setText("Full House!");
                }else {
                    dSum= chance(dHand);
                    typeOfPointsD.setText("Chance");
                }
            } else if (isFourOfAKind(dHand)) {
                typeOfPointsD.setText("Four of a kind!");
                dSum= chance(dHand);
            } else if(isThreeOfAKind(dHand)) {
                typeOfPointsD.setText("Three of a kind!");
                dSum= chance(dHand);
            }else {
                dSum= chance(dHand);
                typeOfPointsD.setText("Chance");
            }
            playerPoints.setText("Player: " + pSum);
            playerPoints.setVisibility(View.VISIBLE);

            dealerPoints.setText("Dealer: " + dSum);
            dealerPoints.setVisibility(View.VISIBLE);

            if (pSum > dSum) {
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
                totalCreditsDG.setText("Créditos: " + String.valueOf(maxBetDG + (2 * playerBetDG)));
                maxBetDG += 2 * playerBetDG;
            } else if (pSum==dSum){
                Toast.makeText(this, "Dead End!", Toast.LENGTH_SHORT).show();
                totalCreditsDG.setText("Créditos: " + String.valueOf(maxBetDG + playerBetDG));
                maxBetDG += playerBetDG;
            }else{
                Toast.makeText(this, "You Lose", Toast.LENGTH_SHORT).show();

            }
            ok.setVisibility(View.VISIBLE);
        });

        btnRollDice.setOnClickListener(v -> {
            btnStartDG.setVisibility(View.GONE);
            btnStay.setVisibility(View.VISIBLE);
            btnRollDice.setVisibility(View.GONE);
            btnRollDiceAgain.setVisibility(View.VISIBLE);

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
        });

        btnRollDiceAgain.setOnClickListener(v -> {
            pHand.clear();
            dHand.clear();
            btnRollDiceAgain.setVisibility(View.GONE);
            btnRollDiceAgain2.setVisibility(View.VISIBLE);

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
        });

        btnRollDiceAgain2.setOnClickListener(v -> {
            pHand.clear();
            dHand.clear();
            btnRollDiceAgain2.setVisibility(View.GONE);

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
        });

        ok.setOnClickListener(v -> {

            Toast.makeText(this, "Dice Game", Toast.LENGTH_SHORT).show();
            pHand.clear();
            dHand.clear();

            creditsResultDG.setVisibility(View.VISIBLE);
            creditsBarDG.setVisibility(View.VISIBLE);
            btnStartDG.setVisibility(View.VISIBLE);
            btnLeave.setVisibility(View.VISIBLE);
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
            dealerPoints.setVisibility(View.GONE);
            playerPoints.setVisibility(View.GONE);
            typeOfPointsD.setVisibility(View.GONE);
            typeOfPointsP.setVisibility(View.GONE);
        });

    }

    public boolean calculateYatzeeScore(List<Dices> hand) {
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

    public boolean calculateFullHouseScore(List<Dices> hand) {

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
    public boolean isThreeOfAKind(List<Dices> hand) {
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
    public boolean isFourOfAKind(List<Dices> hand) {
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
    public boolean isSmallStraight(List<Dices> hand) {
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
    public boolean isLargeStraight(List<Dices> hand) {
        Collections.sort(hand, Comparator.comparingInt(Dices::getRank));

        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i + 1).getRank() != hand.get(i).getRank() + 1) {
                return false;
            }
        }

        return true;
    }
    public int chance(List<Dices> hand) {
        int sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).getRank();
        }
        return sum;
    }

}