package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView tv_score_left, tv_score_right, totalCreditsWG, betResultWG, pCredits, cCredits;
    private Button deal, leaveTable, start, ok;
    private Deck deck;
    private SeekBar creditsBarWG;
    private int leftScore = 0, rightScore = 0, playerBetWG, maxBetWG;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_game);

        creditsBarWG = (SeekBar) findViewById(R.id.seekBarCreditsWG);
        betResultWG = (TextView) findViewById(R.id.playerBet);
        pCredits = (TextView) findViewById(R.id.playerCreditsTextView);
        cCredits = (TextView) findViewById(R.id.cpuCredits);
        totalCreditsWG = (TextView) findViewById(R.id.totalCredits);
        tv_score_left = (TextView) findViewById(R.id.tv_score_left);
        tv_score_right = (TextView) findViewById(R.id.tv_score_right);
        iv_card_left = (ImageView) findViewById(R.id.iv_card_left);
        iv_card_right = (ImageView) findViewById(R.id.iv_card_right);
        deal = (Button) findViewById(R.id.btnDeal);
        leaveTable = (Button) findViewById(R.id.btnLeaveTable);
        start = (Button) findViewById(R.id.btnStartWG);
        ok = (Button) findViewById(R.id.btnOkWG);
        maxBetWG = creditsBarWG.getMax();

        deck = new Deck();

        leaveTable.setVisibility(View.VISIBLE);
        totalCreditsWG.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
        creditsBarWG.setVisibility(View.VISIBLE);
        betResultWG.setVisibility(View.VISIBLE);
        iv_card_left.setVisibility(View.GONE);
        iv_card_right.setVisibility(View.GONE);
        tv_score_left .setVisibility(View.GONE);
        tv_score_right.setVisibility(View.GONE);
        deal.setVisibility(View.GONE);
        pCredits.setVisibility(View.GONE);
        cCredits.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);

        creditsBarWG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int credits, boolean b) {
                betResultWG.setText("Apostar: " + credits);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                betResultWG.setText("Apostar: " + seekBar.getProgress());
                playerBetWG = seekBar.getProgress();
                seekBar.setMax(maxBetWG);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (playerBetWG == 0) {
                    Toast.makeText(WarGame.this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                    return;
                }

                leaveTable.setVisibility(View.VISIBLE);
                totalCreditsWG.setVisibility(View.VISIBLE);
                iv_card_left.setVisibility(View.VISIBLE);
                iv_card_right.setVisibility(View.VISIBLE);
                tv_score_left .setVisibility(View.VISIBLE);
                tv_score_right.setVisibility(View.VISIBLE);
                deal.setVisibility(View.VISIBLE);
                pCredits.setVisibility(View.VISIBLE);
                cCredits.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                creditsBarWG.setVisibility(View.GONE);
                betResultWG.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);


                maxBetWG -= playerBetWG;
                totalCreditsWG.setText("Créditos: " + String.valueOf(maxBetWG));
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                leaveTable.setVisibility(View.VISIBLE);
                totalCreditsWG.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
                creditsBarWG.setVisibility(View.VISIBLE);
                betResultWG.setVisibility(View.VISIBLE);
                creditsBarWG.setVisibility(View.VISIBLE);
                betResultWG.setVisibility(View.VISIBLE);
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
                totalCreditsWG.setVisibility(View.VISIBLE);
                creditsBarWG.setVisibility(View.VISIBLE);
                betResultWG.setVisibility(View.VISIBLE);
                iv_card_left.setVisibility(View.VISIBLE);
                iv_card_right.setVisibility(View.VISIBLE);
                tv_score_left .setVisibility(View.VISIBLE);
                tv_score_right.setVisibility(View.VISIBLE);
                pCredits.setVisibility(View.VISIBLE);
                cCredits.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                deal.setVisibility(View.GONE);
                creditsBarWG.setVisibility(View.GONE);
                betResultWG.setVisibility(View.GONE);

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
        // Draw cards for each player
        int playerCard = deck.drawCard();
        int cpuCard = deck.drawCard();

        // Set card images based on drawn cards
        int playerImage = getResources().getIdentifier( "card" + playerCard, "drawable", getPackageName());
        iv_card_left.setImageResource(playerImage);

        int cpuImage = getResources().getIdentifier("card" + cpuCard, "drawable", getPackageName());
        iv_card_right.setImageResource(cpuImage);

        // Compare the drawn cards and update scores
        updateScores(playerCard, cpuCard);
    }

    private void updateScores(int playerCard, int cpuCard) {
        if (playerCard > cpuCard) {
            leftScore++;
            tv_score_left.setText(String.valueOf(leftScore));
            totalCreditsWG.setText("Créditos: " + String.valueOf(maxBetWG + (2 * playerBetWG)));
            maxBetWG += 2 * playerBetWG;
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        } else if (playerCard < cpuCard) {
            rightScore++;
            tv_score_right.setText(String.valueOf(rightScore));
            Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
        } else {
            totalCreditsWG.setText("Créditos: " + String.valueOf(maxBetWG + playerBetWG));
            maxBetWG += playerBetWG;
            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
        }
    }
}