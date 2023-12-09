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
    private Button btnStart;
    private LinearLayout linearLayout;

    @SuppressLint("MissingInflatedId")
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
        btnStart = (Button)findViewById(R.id.btnStart);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlackJack.this, FrontPage.class));
            }
        });
    }

}