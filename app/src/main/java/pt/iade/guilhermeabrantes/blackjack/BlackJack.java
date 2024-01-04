package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import pt.iade.guilhermeabrantes.blackjack.models.Card;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})

    private SeekBar creditsBarBJ;
    private TextView creditsResultBJ, totalCreditsBJ;
    private int playerBetBJ, maxBetBJ;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        creditsBarBJ = (SeekBar) findViewById(R.id.seekBarCreditsBJ);
        creditsResultBJ = (TextView) findViewById(R.id.playerBetBJ);
        totalCreditsBJ = (TextView) findViewById(R.id.totalCreditsBJ);
        maxBetBJ = creditsBarBJ.getMax();

        List<Card> pHand = new ArrayList<>();
        List<Card> dHand = new ArrayList<>();
        List<Card> split1 = new ArrayList<>();
        List<Card> split2 = new ArrayList<>();

        Button start = findViewById(R.id.btnStartBJ);
        Button stand = findViewById(R.id.btnStand);
        Button standSplit = findViewById(R.id.btnStandSplit);
        Button split = findViewById(R.id.btnSplit);
        Button hit = findViewById(R.id.btnHit);
        Button ok = findViewById(R.id.btnOk);
        Button leave = findViewById(R.id.btnExit);
        Button hit2 = findViewById(R.id.btnHit2);
        Button hit3 = findViewById(R.id.btnHitAgain);
        Button hitSplit1 = findViewById(R.id.btnHitSplit1);
        Button hit2Split1 = findViewById(R.id.btnHit2Split1);
        Button hitSplit2 = findViewById(R.id.btnHitSplit2);
        Button hit2Split2 = findViewById(R.id.btnHit2Split2);


        LinearLayout linearSplit1 = findViewById(R.id.linearSplit1);
        LinearLayout linearSplit2 = findViewById(R.id.linearSplit2);
        LinearLayout linearPlayer = findViewById(R.id.linearPlayer);

        TextView playerPoints = findViewById(R.id.playerPoints);
        TextView dealerPoints = findViewById(R.id.dealerPoints);
        TextView playerPointsSplit = findViewById(R.id.playerPointsSplit);
        TextView playerPointsSplit2 = findViewById(R.id.playerPointsSplit2);

        ImageView card1Split1 = findViewById(R.id.card1Split1);
        ImageView card2Split1 = findViewById(R.id.card2Split1);
        ImageView card3Split1 = findViewById(R.id.card3Split1);
        ImageView card4Split1 = findViewById(R.id.card4Split1);

        ImageView card1Split2 = findViewById(R.id.card1Split2);
        ImageView card2Split2 = findViewById(R.id.card2Split2);
        ImageView card3Split2 = findViewById(R.id.card3Split2);
        ImageView card4Split2 = findViewById(R.id.card4Split2);

        ImageView card1 = findViewById(R.id.playerCard);
        ImageView card2 = findViewById(R.id.playerCard2);
        ImageView card3 = findViewById(R.id.playerCard3);
        ImageView card4 = findViewById(R.id.playerCard4);
        ImageView card5 = findViewById(R.id.playerCard5);

        ImageView dCard1 = findViewById(R.id.dealerCard);
        ImageView dCard2 = findViewById(R.id.dealerCard2);
        ImageView dCard3 = findViewById(R.id.dealerCard3);
        ImageView dCard4 = findViewById(R.id.dealerCard4);
        ImageView dCard5 = findViewById(R.id.dealerCard5);

        start.setVisibility(View.VISIBLE);
        stand.setVisibility(View.GONE);
        standSplit.setVisibility(View.GONE);
        hitSplit1.setVisibility(View.GONE);
        hit2Split1.setVisibility(View.GONE);
        hitSplit2.setVisibility(View.GONE);
        hit2Split2.setVisibility(View.GONE);
        linearSplit1.setVisibility(View.GONE);
        linearSplit2.setVisibility(View.GONE);
        hit.setVisibility(View.GONE);
        hit2.setVisibility(View.GONE);
        hit3.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        card1.setVisibility(View.GONE);
        card2.setVisibility(View.GONE);
        card3.setVisibility(View.GONE);
        card4.setVisibility(View.GONE);
        card5.setVisibility(View.GONE);
        dCard1.setVisibility(View.GONE);
        dCard2.setVisibility(View.GONE);
        dCard3.setVisibility(View.GONE);
        dCard4.setVisibility(View.GONE);
        dCard5.setVisibility(View.GONE);
        split.setVisibility(View.GONE);

        creditsBarBJ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int credits, boolean b) {
                creditsResultBJ.setText("Apostar: " + credits);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                creditsResultBJ.setText("Apostar: " + seekBar.getProgress());
                playerBetBJ = seekBar.getProgress();
                seekBar.setMax(maxBetBJ);
            }
        });

        leave.setOnClickListener(v -> {
            startActivity(new Intent(BlackJack.this, FrontPage.class));
        });


        start.setOnClickListener(v -> {
            if (playerBetBJ == 0) {
                Toast.makeText(BlackJack.this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                return;
            } else if (playerBetBJ > maxBetBJ) {
                Toast.makeText(BlackJack.this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                return;
            }
            split.setVisibility(View.VISIBLE);
            stand.setVisibility(View.VISIBLE);
            hit.setVisibility(View.VISIBLE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
            leave.setVisibility(View.GONE);
            creditsResultBJ.setVisibility(View.GONE);
            creditsBarBJ.setVisibility(View.GONE);

            maxBetBJ -= playerBetBJ;
            totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ));

            Card first = new Card();
            Card second = new Card();

            pHand.add(first);
            pHand.add(second);

            card1.setImageResource(getResources().getIdentifier(first.getName(), "drawable", getPackageName()));
            card2.setImageResource(getResources().getIdentifier(second.getName(), "drawable", getPackageName()));

            card1.setVisibility(View.VISIBLE);
            card2.setVisibility(View.VISIBLE);

            int pSum = 0;
            int pAces = 0;
            for (int i = 0; i < pHand.size(); i++) {
                pSum += pHand.get(i).getRank();
                if (pHand.get(i).getRank() == 11) {
                    pAces++;
                }
            }
            if (pSum > 21 && pAces > 0) {
                pSum -= pAces * 10;
            }

            if (pHand.size() == 2 && pHand.get(0).getRank() == pHand.get(1).getRank()) {
                split.setVisibility(View.VISIBLE);
                split.setEnabled(true);
            } else {
                split.setVisibility(View.GONE);
                split.setEnabled(false);
            }
            playerPoints.setText("Player: " + pSum);
            playerPoints.setVisibility(View.VISIBLE);

            Card dFirst = new Card();
            Card dSecond = new Card();

            dHand.add(dFirst);
            dHand.add(dSecond);

            dCard1.setImageResource(getResources().getIdentifier(dFirst.getName(), "drawable", getPackageName()));
            dCard2.setImageResource(getResources().getIdentifier("cardback", "drawable", getPackageName()));

            dCard1.setVisibility(View.VISIBLE);
            dCard2.setVisibility(View.VISIBLE);
        });

        stand.setOnClickListener(v -> {
            stand.setVisibility(View.GONE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.GONE);
            split.setVisibility(View.GONE);

            boolean canHit = true;
            int dSum = 0;
            int dAces = 0;

            dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(), "drawable", getPackageName()));
            for (int i = 0; i < 2; i++) {
                dSum += dHand.get(i).getRank();
                if (dHand.get(i).getRank() == 11) {
                    dAces++;
                }
            }
            while (canHit && (dSum < 17 || (dSum == 17 && dAces > 0))) {
                Card toAdd = new Card();
                dHand.add(toAdd);

                int cardIndex = dHand.size() - 1;
                int resourceId = getResources().getIdentifier(dHand.get(cardIndex).getName(), "drawable", getPackageName());
                switch (cardIndex) {
                    case 2:
                        dCard3.setImageResource(resourceId);
                        dCard3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        dCard4.setImageResource(resourceId);
                        dCard4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        dCard5.setImageResource(resourceId);
                        dCard5.setVisibility(View.VISIBLE);
                        break;
                }

                dSum += toAdd.getRank();

                if (toAdd.getRank() == 11) {
                    dAces++;
                }
                if (dSum > 21 && dAces > 0) {
                    dSum -= dAces * 10;
                }
            }
            int pSum = 0;
            int pAces = 0;
            for (int i = 0; i < pHand.size(); i++) {
                pSum += pHand.get(i).getRank();
                if (pHand.get(i).getRank() == 11) {
                    pAces++;
                }
            }
            if (pSum > 21 && pAces > 0) {
                pSum -= pAces * 10;
            }
            playerPoints.setText("Player: " + pSum);
            playerPoints.setVisibility(View.VISIBLE);


            dealerPoints.setText("Dealer: " + dSum);
            dealerPoints.setVisibility(View.VISIBLE);

            if (dSum > 21) {
                Toast.makeText(BlackJack.this, "Dealer busted. You win!", Toast.LENGTH_SHORT).show();
                totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ + (2 * playerBetBJ)));
                maxBetBJ += 2 * playerBetBJ;
            } else if (pSum > dSum) {
                Toast.makeText(BlackJack.this, "You win!", Toast.LENGTH_SHORT).show();
                totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ + (2 * playerBetBJ)));
                maxBetBJ += 2 * playerBetBJ;
            } else if (pSum == dSum) {
                Toast.makeText(BlackJack.this, "Dead end!", Toast.LENGTH_SHORT).show();
                totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ + playerBetBJ));
                maxBetBJ += playerBetBJ;
            } else {
                Toast.makeText(BlackJack.this, "Dealer Wins!", Toast.LENGTH_SHORT).show();
            }
            ok.setVisibility(View.VISIBLE);
        });
        split.setOnClickListener(v -> {
            stand.setVisibility(View.GONE);
            hitSplit1.setVisibility(View.VISIBLE);
            hitSplit2.setVisibility(View.VISIBLE);
            standSplit.setVisibility(View.VISIBLE);
            hit.setVisibility(View.GONE);
            split.setVisibility(View.GONE);
            linearSplit1.setVisibility(View.VISIBLE);
            linearSplit2.setVisibility(View.VISIBLE);
            linearPlayer.setVisibility(View.GONE);
            playerPoints.setVisibility(View.GONE);
            boolean splitDone = false;
            // Verifica se o split ainda não foi realizado
            if (!splitDone && pHand.size() == 2 && pHand.get(0).getRank() == pHand.get(1).getRank()) {
                // Adiciona a primeira carta à lista split1
                split1.add(pHand.get(0));

                // Adiciona a segunda carta à lista split2
                split2.add(pHand.get(1));

                // Move a primeira carta para card1Split1
                card1Split1.setImageResource(getResources().getIdentifier(pHand.get(0).getName(), "drawable", getPackageName()));

                // Move a segunda carta para card1Split2
                card1Split2.setImageResource(getResources().getIdentifier(pHand.get(1).getName(), "drawable", getPackageName()));

                // Limpa a lista pHand
                pHand.clear();

                Card added = new Card();
                split1.add(added);
                card2Split1.setImageResource(getResources().getIdentifier(split1.get(split1.size() - 1).getName(), "drawable", getPackageName()));
                card2Split1.setVisibility(View.VISIBLE);

                Card added2 = new Card();
                split2.add(added2);
                card2Split2.setImageResource(getResources().getIdentifier(split2.get(split2.size() - 1).getName(), "drawable", getPackageName()));
                card2Split2.setVisibility(View.VISIBLE);
                // Marca o split como concluído para evitar múltiplos splits
                splitDone = true;
            }
        });

        standSplit.setOnClickListener(v -> {

            standSplit.setVisibility(View.GONE);
            hitSplit1.setVisibility(View.GONE);
            hitSplit2.setVisibility(View.GONE);
            hit2Split1.setVisibility(View.GONE);
            hit2Split2.setVisibility(View.GONE);

            boolean canHit = true;
            int dSum = 0;
            int dAces = 0;

            dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(), "drawable", getPackageName()));
            for (int i = 0; i < 2; i++) {
                dSum += dHand.get(i).getRank();
                if (dHand.get(i).getRank() == 11) {
                    dAces++;
                }
            }
            while (canHit && (dSum < 17 || (dSum == 17 && dAces > 0))) {
                Card toAdd = new Card();
                dHand.add(toAdd);

                int cardIndex = dHand.size() - 1;
                int resourceId = getResources().getIdentifier(dHand.get(cardIndex).getName(), "drawable", getPackageName());
                switch (cardIndex) {
                    case 2:
                        dCard3.setImageResource(resourceId);
                        dCard3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        dCard4.setImageResource(resourceId);
                        dCard4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        dCard5.setImageResource(resourceId);
                        dCard5.setVisibility(View.VISIBLE);
                        break;
                }

                dSum += toAdd.getRank();

                if (toAdd.getRank() == 11) {
                    dAces++;
                }
                if (dSum > 21 && dAces > 0) {
                    dSum -= dAces * 10;
                }
            }

            //pSum = Primeira mão
            int pSum = 0;
            int pAces = 0;
            for (int i = 0; i < split1.size(); i++) {
                pSum += split1.get(i).getRank();
                if (split1.get(i).getRank() == 11) {
                    pAces++;
                }
            }
            if (pSum > 21 && pAces > 0) {
                pSum -= pAces * 10;
            }
            playerPointsSplit.setText("Player: " + pSum);
            playerPointsSplit.setVisibility(View.VISIBLE);

            //pSum2 = Segunda mão
            int pSum2 = 0;
            int pAces2 = 0;
            for (int i = 0; i < split2.size(); i++) {
                pSum2 += split2.get(i).getRank();
                if (split2.get(i).getRank() == 11) {
                    pAces2++;
                }
            }
            if (pSum2 > 21 && pAces2 > 0) {
                pSum2 -= pAces2 * 10;
            }
            playerPointsSplit2.setText("Player: " + pSum2);
            playerPointsSplit2.setVisibility(View.VISIBLE);


            dealerPoints.setText("Dealer: " + dSum);
            dealerPoints.setVisibility(View.VISIBLE);

            /* if (dSum > 21) {
                Toast.makeText(BlackJack.this, "Dealer busted. You win!", Toast.LENGTH_SHORT).show();
                totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ + (2 * playerBetBJ)));
                maxBetBJ += 2 * playerBetBJ;
            } else if (pSum > dSum) {
                Toast.makeText(BlackJack.this, "You win!", Toast.LENGTH_SHORT).show();
                totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ + (2 * playerBetBJ)));
                maxBetBJ += 2 * playerBetBJ;
            } else if (pSum == dSum) {
                Toast.makeText(BlackJack.this, "Dead end!", Toast.LENGTH_SHORT).show();
                totalCreditsBJ.setText("Créditos: " + String.valueOf(maxBetBJ + playerBetBJ));
                maxBetBJ += playerBetBJ;
            } else {
                Toast.makeText(BlackJack.this, "Dealer Wins!", Toast.LENGTH_SHORT).show();
            }

             */
            ok.setVisibility(View.VISIBLE);
        });
        hitSplit1.setOnClickListener(v -> {
            Card added = new Card();
            split1.add(added);

            card3Split1.setImageResource(getResources().getIdentifier(split1.get(split1.size() - 1).getName(), "drawable", getPackageName()));

            card3Split1.setVisibility(View.VISIBLE);
            hitSplit1.setVisibility(View.GONE);
            hit2Split1.setVisibility(View.VISIBLE);

            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < split1.size(); i++) {
                sum += split1.get(i).getRank();
                if (split1.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPointsSplit.setText("Player: " + sum);
            playerPointsSplit.setVisibility(View.VISIBLE);
            if (sum > 21) {

                Toast.makeText(BlackJack.this, "Lose first hand.Dealer Wins!", Toast.LENGTH_SHORT).show();

                ok.setVisibility(View.VISIBLE);
                hit2Split1.setVisibility(View.GONE);
            }
        });
        hit2Split1.setOnClickListener(v -> {
            Card added = new Card();
            split1.add(added);

            card4Split1.setImageResource(getResources().getIdentifier(split1.get(split1.size() - 1).getName(), "drawable", getPackageName()));

            card4Split1.setVisibility(View.VISIBLE);
            hit2Split1.setVisibility(View.GONE);


            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < split1.size(); i++) {
                sum += split1.get(i).getRank();
                if (split1.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPointsSplit.setText("Player: " + sum);
            playerPointsSplit.setVisibility(View.VISIBLE);
            if (sum > 21) {

                Toast.makeText(BlackJack.this, "Lose first hand. Dealer Wins!", Toast.LENGTH_SHORT).show();

                ok.setVisibility(View.VISIBLE);
            }
        });
        hitSplit2.setOnClickListener(v -> {
            Card added2 = new Card();
            split2.add(added2);

            card3Split2.setImageResource(getResources().getIdentifier(split2.get(split2.size() - 1).getName(), "drawable", getPackageName()));

            card3Split2.setVisibility(View.VISIBLE);
            hit2Split2.setVisibility(View.VISIBLE);
            hitSplit2.setVisibility(View.GONE);

            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < split2.size(); i++) {
                sum += split2.get(i).getRank();
                if (split2.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPointsSplit2.setText("Player: " + sum);
            playerPointsSplit2.setVisibility(View.VISIBLE);
            if (sum > 21) {

                Toast.makeText(BlackJack.this, "Lose second hand. Dealer Wins!", Toast.LENGTH_SHORT).show();

                hit2Split2.setVisibility(View.GONE);
                ok.setVisibility(View.VISIBLE);
            }
        });
        hit2Split2.setOnClickListener(v -> {
            Card added2 = new Card();
            split2.add(added2);

            card4Split2.setImageResource(getResources().getIdentifier(split2.get(split2.size() - 1).getName(), "drawable", getPackageName()));

            card4Split2.setVisibility(View.VISIBLE);
            hit2Split2.setVisibility(View.GONE);

            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < split2.size(); i++) {
                sum += split2.get(i).getRank();
                if (split2.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPointsSplit2.setText("Player: " + sum);
            playerPointsSplit2.setVisibility(View.VISIBLE);
            if (sum > 21) {

                Toast.makeText(BlackJack.this, "Lose second hand. Dealer Wins!", Toast.LENGTH_SHORT).show();

                hit2Split2.setVisibility(View.GONE);
                ok.setVisibility(View.VISIBLE);
            }
        });

        hit.setOnClickListener(v -> {
            Card added = new Card();
            pHand.add(added);

            card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable", getPackageName()));
            card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(), "drawable", getPackageName()));
            card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(), "drawable", getPackageName()));

            card3.setVisibility(View.VISIBLE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.VISIBLE);
            hit3.setVisibility(View.GONE);
            split.setVisibility(View.GONE);

            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < pHand.size(); i++) {
                sum += pHand.get(i).getRank();
                if (pHand.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPoints.setText("Player: " + sum);
            playerPoints.setVisibility(View.VISIBLE);

            if (sum > 21) {

                Toast.makeText(BlackJack.this, "Bad luck. Dealer Wins!", Toast.LENGTH_SHORT).show();

                ok.setVisibility(View.VISIBLE);
                stand.setVisibility(View.GONE);
                hit.setVisibility(View.GONE);
                hit2.setVisibility(View.GONE);
                hit3.setVisibility(View.GONE);
                split.setVisibility(View.GONE);
            }
        });

        hit2.setOnClickListener(v -> {
            Card added = new Card();
            pHand.add(added);

            card4.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable", getPackageName()));
            card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(), "drawable", getPackageName()));
            card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(), "drawable", getPackageName()));
            card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 4).getName(), "drawable", getPackageName()));

            card4.setVisibility(View.VISIBLE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.VISIBLE);

            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < pHand.size(); i++) {
                sum += pHand.get(i).getRank();
                if (pHand.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPoints.setText("Player: " + sum);
            playerPoints.setVisibility(View.VISIBLE);

            if (sum > 21) {

                Toast.makeText(BlackJack.this, "Bad luck. Dealer Wins!", Toast.LENGTH_SHORT).show();

                ok.setVisibility(View.VISIBLE);
                stand.setVisibility(View.GONE);
                hit.setVisibility(View.GONE);
                hit2.setVisibility(View.GONE);
                hit3.setVisibility(View.GONE);
                split.setVisibility(View.GONE);
            }
        });

        hit3.setOnClickListener(v -> {
            Card added = new Card();
            pHand.add(added);

            card5.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable", getPackageName()));
            card4.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(), "drawable", getPackageName()));
            card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(), "drawable", getPackageName()));
            card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 4).getName(), "drawable", getPackageName()));
            card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 5).getName(), "drawable", getPackageName()));

            card5.setVisibility(View.VISIBLE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.GONE);

            int sum = 0;
            int aceCtr = 0;
            for (int i = 0; i < pHand.size(); i++) {
                sum += pHand.get(i).getRank();
                if (pHand.get(i).getRank() == 11) {
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0) {
                sum -= aceCtr * 10;
            }
            playerPoints.setText("Player: " + sum);
            playerPoints.setVisibility(View.VISIBLE);

            if (sum > 21) {

                Toast.makeText(this, "Bad luck. Dealer Wins!", Toast.LENGTH_SHORT).show();

                ok.setVisibility(View.VISIBLE);
                stand.setVisibility(View.GONE);
                hit.setVisibility(View.GONE);
                hit2.setVisibility(View.GONE);
                hit3.setVisibility(View.GONE);
            }
        });

        ok.setOnClickListener(v -> {

            Toast.makeText(this, "BlackJack", Toast.LENGTH_SHORT).show();
            pHand.clear();
            dHand.clear();

            creditsResultBJ.setVisibility(View.VISIBLE);
            creditsBarBJ.setVisibility(View.VISIBLE);
            start.setVisibility(View.VISIBLE);
            leave.setVisibility(View.VISIBLE);
            ok.setVisibility(View.GONE);
            card1.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
            card3.setVisibility(View.GONE);
            card4.setVisibility(View.GONE);
            card5.setVisibility(View.GONE);
            dCard1.setVisibility(View.GONE);
            dCard2.setVisibility(View.GONE);
            dCard3.setVisibility(View.GONE);
            dCard4.setVisibility(View.GONE);
            dCard5.setVisibility(View.GONE);
            dealerPoints.setVisibility(View.GONE);
            playerPoints.setVisibility(View.GONE);
            playerPointsSplit.setVisibility(View.GONE);
            playerPointsSplit2.setVisibility(View.GONE);
            linearSplit1.setVisibility(View.GONE);
            linearSplit2.setVisibility(View.GONE);
            linearPlayer.setVisibility(View.VISIBLE);
        });
    }
}