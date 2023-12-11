package pt.iade.guilhermeabrantes.blackjack;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import pt.iade.guilhermeabrantes.blackjack.models.Card;
import pt.iade.guilhermeabrantes.blackjack.models.Deck;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlackJack extends AppCompatActivity {

    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private ImageView dealercard, dealercard2, dealercard3, dealercard4, dealercard5, playercard, playercard2, playercard3, playercard4, playercard5;
    private Button btnExit;
    private TextView scoreDealer, scorePlayer;
    private Button btnHit;
    private Button btnStand;
    private int sDealer = 0, sPlayer = 0;
    private Button btnStart;
    private LinearLayout linearLayout;
    Random r;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        deck = new Deck();
        playerHand = new ArrayList<>();  // Distribui 2 cartas para o jogador
        dealerHand = new ArrayList<>();  // Distribui 2 cartas para o dealer
        dealercard = (ImageView) findViewById(R.id.dealerCard);
        playercard = (ImageView) findViewById(R.id.playerCard);
        dealercard2 = (ImageView) findViewById(R.id.dealerCard2);
        playercard2 = (ImageView) findViewById(R.id.playerCard2);
        dealercard3 = (ImageView) findViewById(R.id.dealerCard3);
        playercard3 = (ImageView) findViewById(R.id.playerCard3);
        dealercard4 = (ImageView) findViewById(R.id.dealerCard4);
        playercard4 = (ImageView) findViewById(R.id.playerCard4);
        dealercard5 = (ImageView) findViewById(R.id.dealerCard5);
        playercard5 = (ImageView) findViewById(R.id.playerCard5);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnStand = (Button) findViewById(R.id.btnStand);
        btnHit = (Button) findViewById(R.id.btnHit);
        btnStart = (Button) findViewById(R.id.btnStart);
        scorePlayer = (TextView) findViewById(R.id.scoreViewPlayer);
        scoreDealer = (TextView) findViewById(R.id.scoreViewDealer);
        r = new Random();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlackJack.this, FrontPage.class));
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View start) {
                dealCards();
            }
        });
        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View hit) {
                dealCards2();
            }
        });
        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View stand) {
                dealCards3();
            }
        });
    }

    private void dealCards() {
        // Draw cards for each player
        int dealerCard = deck.drawCard();
        int playerCard = deck.drawCard();
        int playerCard2 = deck.drawCard();

        // Set card images based on drawn cards
        int dealerImage = getResources().getIdentifier("card" + dealerCard, "drawable", getPackageName());
        dealercard.setImageResource(dealerImage);

        int playerImage = getResources().getIdentifier("card" + playerCard, "drawable", getPackageName());
        playercard.setImageResource(playerImage);

        int playerImage2 = getResources().getIdentifier("card" + playerCard2, "drawable", getPackageName());
        playercard2.setImageResource(playerImage2);
    }

    private void dealCards2() {
        // Draw cards for each player
        int playerCard3 = deck.drawCard();

        int playerImage3 = getResources().getIdentifier("card" + playerCard3, "drawable", getPackageName());
        playercard3.setImageResource(playerImage3);
    }

    private void dealCards3() {

        int dealerCard2 = deck.drawCard();

        // Set card images based on drawn cards
        int dealerImage2 = getResources().getIdentifier("card" + dealerCard2, "drawable", getPackageName());
        dealercard2.setImageResource(dealerImage2);
    }
}