package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.iade.guilhermeabrantes.blackjack.models.Card;
import pt.iade.guilhermeabrantes.blackjack.models.Dices;

public class DiceGame extends AppCompatActivity {

    private Button btnRollDice;
    private Button btnLeave;
    private Button btnStartDG;
    private Button btnStay;
    private Button ok;

    @SuppressLint({"MissingInflatedId", "DiscouragedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        List<Dices> pHand = new ArrayList<>();
        List<Dices> dHand = new ArrayList<>();

        btnStartDG= (Button) findViewById(R.id.btnStardDG);
        btnRollDice = (Button) findViewById(R.id.btnRollDice);
        btnLeave = (Button) findViewById(R.id.btnLeave);
        btnStay = (Button) findViewById(R.id.btnStay);
        ok = (Button) findViewById(R.id.btnOkDG);

        ImageView dice1 = findViewById(R.id.playerDice);
        ImageView dice2 = findViewById(R.id.playerDice2);
        ImageView dice3 = findViewById(R.id.playerDice3);
        ImageView dice4 = findViewById(R.id.playerDice4);
        ImageView dice5 = findViewById(R.id.playerDice5);

        ImageView dDice1 = findViewById(R.id.dealerCard);
        ImageView dDice2 = findViewById(R.id.dealerDice2);
        ImageView dDice3 = findViewById(R.id.dealerDice3);
        ImageView dDice4 = findViewById(R.id.dealerDice4);
        ImageView dDice5 = findViewById(R.id.dealerDice5);

        btnStartDG.setVisibility(View.VISIBLE);
        btnStay.setVisibility(View.GONE);
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

        btnLeave.setOnClickListener(v -> {
            startActivity(new Intent(DiceGame.this, FrontPage.class));
        });
        btnStartDG.setOnClickListener(v -> {
            btnStartDG.setVisibility(View.GONE);
            btnRollDice.setVisibility(View.VISIBLE);
        });
        btnStay.setOnClickListener(v -> {
            btnStay.setVisibility(View.GONE);

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

            int sum = 0;
            for (int i = 0; i < dHand.size(); i++) {
                sum += dHand.get(i).getRank();
            }
            int pSum = 0;
            for (int i = 0; i < pHand.size(); i++) {
                pSum += pHand.get(i).getRank();
            }
            int dSum = 0;
            for (int i = 0; i < dHand.size(); i++) {
                dSum += dHand.get(i).getRank();
            }

            if (pSum > dSum) {
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
            }
            ok.setVisibility(View.VISIBLE);
        });

        btnRollDice.setOnClickListener(v -> {
                btnStartDG.setVisibility(View.GONE);
                btnStay.setVisibility(View.VISIBLE);

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

        btnStartDG.setVisibility(View.VISIBLE);
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
        });

    }

}