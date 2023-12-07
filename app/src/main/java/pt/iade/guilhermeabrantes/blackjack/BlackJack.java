package pt.iade.guilhermeabrantes.blackjack;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import pt.iade.guilhermeabrantes.blackjack.models.Card;
import pt.iade.guilhermeabrantes.blackjack.models.Deck;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends AppCompatActivity {

    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private Button btnExit;
    private Button btnHit;
    private Button btnStay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        deck = new Deck();
        playerHand = new ArrayList<>();  // Distribui 2 cartas para o jogador
        dealerHand = new ArrayList<>();  // Distribui 2 cartas para o dealer
        btnExit = (Button) findViewById(R.id.btnExit);
        btnStay = (Button) findViewById(R.id.btnStay);
        btnHit = (Button) findViewById(R.id.btnHit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlackJack.this, FrontPage.class));
            }
        });
        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
    });
    }
        private void startGame () {
            // Distribuir cartas iniciais
            drawInitialCards();

            // Atualizar a interface do usuário
            displayPlayerHand();
            displayDealerHand();
        }
        private void drawInitialCards () {
            // Distribuir 2 cartas para o jogador e 2 para o dealer
            playerHand.add(deck.drawCard());
            dealerHand.add(deck.drawCard());
            playerHand.add(deck.drawCard());
            dealerHand.add(deck.drawCard());
        }
    private void displayDealerHand() {
        // Suponha que você tenha ImageView para cada carta do dealer
        ImageView dealerCard1 = findViewById(R.id.dealerCard);
        ImageView dealerCard2 = findViewById(R.id.dealerCard2);

        // Certifique-se de ajustar os IDs conforme necessário

        // Exiba a primeira carta do dealer
        Card firstCard = dealerHand.get(0);
        dealerCard1.setImageResource(getResourceIdForCard(firstCard));

        // Exiba a segunda carta do dealer
        Card secondCard = dealerHand.get(1);
        dealerCard2.setImageResource(getResourceIdForCard(secondCard));

        // Adicione mais código conforme necessário se o dealer tiver mais cartas visíveis
    }
    private void displayPlayerHand() {
        // Suponha que você tenha ImageView para cada carta do dealer
        ImageView dealerCard1 = findViewById(R.id.playerCard);
        ImageView dealerCard2 = findViewById(R.id.playerCard2);

        // Certifique-se de ajustar os IDs conforme necessário

        // Exiba a primeira carta do dealer
        Card firstCard = playerHand.get(0);
        dealerCard1.setImageResource(getResourceIdForCard(firstCard));

        // Exiba a segunda carta do dealer
        Card secondCard = playerHand.get(1);
        dealerCard2.setImageResource(getResourceIdForCard(secondCard));

        // Adicione mais código conforme necessário se o player tiver mais cartas visíveis
    }
        private int getResourceIdForCard (Card card){
            String resourceName = card.getRank().toLowerCase() + "_of_" + card.getSuit().toLowerCase();
            return getResources().getIdentifier(resourceName, "drawable", getPackageName());
        }
        private void shuffleDeck () {
            deck.shuffle();
            Toast.makeText(this, "Baralho embaralhado!", Toast.LENGTH_SHORT).show();
        }
}
