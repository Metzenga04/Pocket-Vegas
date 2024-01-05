package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import pt.iade.guilhermeabrantes.blackjack.models.Deck;

public class WarGame extends AppCompatActivity {

    private ImageView iv_card_left, iv_card_right;
    private TextView tv_score_left, tv_score_right, totalCredits, betResult, pCredits, cCredits;
    private Button deal, leaveTable, start, ok;
    private Deck deck;
    private SeekBar creditsBar;
    private int leftScore, rightScore, playerBet, maxBet;

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
        maxBet = creditsBar.getMax();

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

        creditsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int credits, boolean b) {
                betResult.setText("Apostar: " + credits);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                betResult.setText("Apostar: " + seekBar.getProgress());
                playerBet = seekBar.getProgress();
                seekBar.setMax(maxBet);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (playerBet == 0 || playerBet > maxBet) {
                    Toast.makeText(WarGame.this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                    return;
                }

                leaveTable.setVisibility(View.VISIBLE);
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


                maxBet -= playerBet;
                totalCredits.setText("Créditos: " + String.valueOf(maxBet));
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
                startActivity(new Intent(WarGame.this,FrontPage.class));
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
            totalCredits.setText("Créditos: " + String.valueOf(maxBet + (2 * playerBet)));
            maxBet += 2 * playerBet;
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        } else if (playerCard < cpuCard) {
            rightScore++;
            tv_score_right.setText(String.valueOf(rightScore));
            Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
        } else {
            totalCredits.setText("Créditos: " + String.valueOf(maxBet + playerBet));
            maxBet += playerBet;
            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
        }
    }
}