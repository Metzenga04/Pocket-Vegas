package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import pt.iade.guilhermeabrantes.blackjack.models.Deck;

public class WarGame extends AppCompatActivity {

    ImageView iv_card_left, iv_card_right;
    TextView tv_score_left, tv_score_right;
    Button btndeal;
    Random r;
    private Deck deck;
    private int leftscore = 0, rightscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_game);

        iv_card_left = (ImageView) findViewById(R.id.iv_card_left);
        iv_card_right = (ImageView) findViewById(R.id.iv_card_right);
        tv_score_left = (TextView) findViewById(R.id.tv_score_left);
        tv_score_right = (TextView) findViewById(R.id.tv_score_right);
        btndeal = (Button) findViewById(R.id.btndeal);

        r = new Random();
        deck = new Deck();
        btndeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealCards();
            }
        });
    }
    private void dealCards() {
        // Draw cards for each player
        int leftCard = deck.drawCard();
        int rightCard = deck.drawCard();

        // Set card images based on drawn cards
        int leftImage = getResources().getIdentifier( "card" + leftCard, "drawable", getPackageName());
        iv_card_left.setImageResource(leftImage);

        int rightImage = getResources().getIdentifier("card" + rightCard, "drawable", getPackageName());
        iv_card_right.setImageResource(rightImage);

        // Compare the drawn cards and update scores
        updateScores(leftCard, rightCard);
    }

    private void updateScores(int leftCard, int rightCard) {
        if (leftCard > rightCard) {
            leftscore++;
            tv_score_left.setText(String.valueOf(leftscore));
            Toast.makeText(this, "Player1 win!", Toast.LENGTH_SHORT).show();
        } else if (leftCard < rightCard) {
            rightscore++;
            tv_score_right.setText(String.valueOf(rightscore));
            Toast.makeText(this, "Player2 win!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
        }
    }
}