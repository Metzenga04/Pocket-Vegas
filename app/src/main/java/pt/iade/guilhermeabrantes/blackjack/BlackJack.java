package pt.iade.guilhermeabrantes.blackjack;

import pt.iade.guilhermeabrantes.blackjack.models.Card;
import pt.iade.guilhermeabrantes.blackjack.models.User;
import pt.iade.guilhermeabrantes.blackjack.retrofit.RetrofitService;
import pt.iade.guilhermeabrantes.blackjack.retrofit.SessionApi;
import pt.iade.guilhermeabrantes.blackjack.retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import android.content.SharedPreferences;

public class BlackJack extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId"})

    private SeekBar creditsBar;
    private TextView betResult, totalCredits;
    private Button start, stand, standSplit, standSplit2, split, hit, ok, leave, hitSplit, hitSplit2;
    private int playerBet, userId, userCredits, clickerCounterHit, clickerCounterHitSplit, clickerCounterHitSplit2, pSumSplit, pSumSplit2;
    private String userEmail, userPassword, userName, userSurname;
    private Boolean standSplitClicked, standSplit2Clicked;
    User user = new User();
    RetrofitService retrofitService = new RetrofitService();
    Retrofit retrofit = retrofitService.getRetrofit();
    UserApi userApi = retrofit.create(UserApi.class);

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        List<Card> pHand = new ArrayList<>();
        List<Card> dHand = new ArrayList<>();
        List<Card> split1 = new ArrayList<>();
        List<Card> split2 = new ArrayList<>();

        creditsBar = (SeekBar) findViewById(R.id.seekBarCreditsBJ);
        start = (Button) findViewById(R.id.btnStartBJ);
        stand = (Button) findViewById(R.id.btnStand);
        standSplit = (Button) findViewById(R.id.btnStandSplit1);
        standSplit2 = (Button) findViewById(R.id.btnStandSplit2);
        split = (Button) findViewById(R.id.btnSplit);
        hit = (Button) findViewById(R.id.btnHit);
        ok = (Button) findViewById(R.id.btnOk);
        leave = (Button) findViewById(R.id.btnExit);
        hitSplit = (Button) findViewById(R.id.btnHitSplit1);
        hitSplit2 = (Button) findViewById(R.id.btnHitSplit2);

        betResult = (TextView) findViewById(R.id.playerBetBJ);
        totalCredits = (TextView) findViewById(R.id.totalCreditsBJ);

        LinearLayout linearSplit1 = findViewById(R.id.linearSplit1);
        LinearLayout linearSplit2 = findViewById(R.id.linearSplit2);
        LinearLayout linearPlayer = findViewById(R.id.linearPlayer);

        TextView playerPoints = findViewById(R.id.playerPoints);
        TextView dealerPoints = findViewById(R.id.dealerPoints);
        TextView playerPointsSplit = findViewById(R.id.playerPointsSplit);
        TextView playerPointsSplit2 = findViewById(R.id.playerPointsSplit2);

        ImageView card1 = findViewById(R.id.playerCard);
        ImageView card2 = findViewById(R.id.playerCard2);
        ImageView card3 = findViewById(R.id.playerCard3);
        ImageView card4 = findViewById(R.id.playerCard4);
        ImageView card5 = findViewById(R.id.playerCard5);
        ImageView card6 = findViewById(R.id.playerCard6);

        ImageView dCard1 = findViewById(R.id.dealerCard);
        ImageView dCard2 = findViewById(R.id.dealerCard2);
        ImageView dCard3 = findViewById(R.id.dealerCard3);
        ImageView dCard4 = findViewById(R.id.dealerCard4);
        ImageView dCard5 = findViewById(R.id.dealerCard5);
        ImageView dCard6 = findViewById(R.id.dealerCard6);

        ImageView card1Split1 = findViewById(R.id.card1Split1);
        ImageView card2Split1 = findViewById(R.id.card2Split1);
        ImageView card3Split1 = findViewById(R.id.card3Split1);
        ImageView card4Split1 = findViewById(R.id.card4Split1);
        ImageView card5Split1 = findViewById(R.id.card5Split1);
        ImageView card6Split1 = findViewById(R.id.card6Split1);

        ImageView card1Split2 = findViewById(R.id.card1Split2);
        ImageView card2Split2 = findViewById(R.id.card2Split2);
        ImageView card3Split2 = findViewById(R.id.card3Split2);
        ImageView card4Split2 = findViewById(R.id.card4Split2);
        ImageView card5Split2 = findViewById(R.id.card5Split2);
        ImageView card6Split2 = findViewById(R.id.card6Split2);

        start.setVisibility(View.VISIBLE);
        stand.setVisibility(View.GONE);
        standSplit.setVisibility(View.GONE);
        standSplit2.setVisibility(View.GONE);
        hit.setVisibility(View.GONE);
        hitSplit.setVisibility(View.GONE);
        hitSplit2.setVisibility(View.GONE);
        split.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        card1.setVisibility(View.GONE);
        card2.setVisibility(View.GONE);
        card3.setVisibility(View.GONE);
        card4.setVisibility(View.GONE);
        card5.setVisibility(View.GONE);
        card6.setVisibility(View.GONE);
        dCard1.setVisibility(View.GONE);
        dCard2.setVisibility(View.GONE);
        dCard3.setVisibility(View.GONE);
        dCard4.setVisibility(View.GONE);
        dCard5.setVisibility(View.GONE);
        dCard6.setVisibility(View.GONE);
        linearSplit1.setVisibility(View.GONE);
        linearSplit2.setVisibility(View.GONE);

        creditsBar.setMax(userCredits);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", 0);
        userCredits = intent.getIntExtra("userCredits", 0);
        userEmail = intent.getStringExtra("userEmail");
        userPassword = intent.getStringExtra("userPassword");
        userName = intent.getStringExtra("userName");
        userSurname = intent.getStringExtra("userSurname");



        updateTotalCredits(userCredits);

        creditsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,int credits, boolean b) {
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
            saveUpdatedCredits(userId, userCredits, userEmail, userPassword, userName, userSurname);
            Intent intentleave = new Intent(BlackJack.this, FrontPage.class);
            intentleave.putExtra("userId", userId);
            intentleave.putExtra("userCredits", userCredits);
            intentleave.putExtra("userEmail", userEmail);
            intentleave.putExtra("userPassword", userPassword);
            intentleave.putExtra("userName", userName);
            intentleave.putExtra("userSurname", userSurname);

            BlackJack.this.startActivity(intentleave);
        });

        start.setOnClickListener(v -> {
            standSplitClicked = false;
            standSplit2Clicked = false;
            clickerCounterHit = 0;
            clickerCounterHitSplit = 0;
            clickerCounterHitSplit2 = 0;
            pSumSplit = 0;
            pSumSplit2 = 0;

            if (playerBet == 0 || playerBet > userCredits) {
                Toast.makeText(BlackJack.this, "Invalid Bet!", Toast.LENGTH_SHORT).show();
                return;
            }

            stand.setVisibility(View.VISIBLE);
            hit.setVisibility(View.VISIBLE);
            split.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
            leave.setVisibility(View.GONE);
            betResult.setVisibility(View.GONE);
            creditsBar.setVisibility(View.GONE);

            userCredits -= playerBet;
            updateTotalCredits(userCredits);

            Card first = new Card();
            Card second = new Card();

            pHand.add(first);
            pHand.add(second);

            card1.setImageResource(getResources().getIdentifier(first.getName(), "drawable", getPackageName()));
            card2.setImageResource(getResources().getIdentifier(second.getName(), "drawable", getPackageName()));
            card1.setVisibility(View.VISIBLE);
            card2.setVisibility(View.VISIBLE);

            int pSum = handPoints(pHand);

            if (pHand.size() == 2 && pHand.get(0).getRank() == pHand.get(1).getRank()) {
                split.setVisibility(View.VISIBLE);
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
            split.setVisibility(View.GONE);

            boolean canHit = true;

            dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(), "drawable", getPackageName()));

            int dSum = 0;
            int dAces = 0;

            for (int i = 0; i < 2; i++) {
                dSum += dHand.get(i).getRank();
                if (dHand.get(i).getRank() == 11) {
                    dAces++;
                }
            }

            while (canHit && (dSum < 17 || dSum == 17 && dAces > 0)) {
                Card toAdd = new Card();
                dHand.add(toAdd);

                int resourceId = getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(), "drawable", getPackageName());
                switch (dHand.size() - 1) {
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
                    case 5:
                        dCard6.setImageResource(resourceId);
                        dCard6.setVisibility(View.VISIBLE);
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

            int pSum = handPoints(pHand);

            playerPoints.setText("Player: " + pSum);
            playerPoints.setVisibility(View.VISIBLE);

            dealerPoints.setText("Dealer: " + dSum);
            dealerPoints.setVisibility(View.VISIBLE);

            if (dSum > 21) {
                Toast.makeText(BlackJack.this, "Dealer busted. You win!", Toast.LENGTH_SHORT).show();
                userCredits += 2 * playerBet;
                updateTotalCredits(userCredits);
            } else if (pSum > dSum) {
                Toast.makeText(BlackJack.this, "You win!", Toast.LENGTH_SHORT).show();
                userCredits += 2 * playerBet;
                updateTotalCredits(userCredits);
            } else if (pSum == dSum) {
                Toast.makeText(BlackJack.this, "Dead end!", Toast.LENGTH_SHORT).show();
                userCredits += playerBet;
                updateTotalCredits(userCredits);
            } else {
                Toast.makeText(BlackJack.this, "Dealer Wins!", Toast.LENGTH_SHORT).show();
            }

            ok.setVisibility(View.VISIBLE);
        });

        split.setOnClickListener(v -> {
            linearSplit1.setVisibility(View.VISIBLE);
            linearSplit2.setVisibility(View.VISIBLE);
            hitSplit.setVisibility(View.VISIBLE);
            hitSplit2.setVisibility(View.VISIBLE);
            standSplit.setVisibility(View.VISIBLE);
            standSplit2.setVisibility(View.VISIBLE);
            stand.setVisibility(View.GONE);
            hit.setVisibility(View.GONE);
            split.setVisibility(View.GONE);
            linearPlayer.setVisibility(View.GONE);
            playerPoints.setVisibility(View.GONE);

            userCredits -= playerBet;
            totalCredits.setText("Créditos: " + String.valueOf(userCredits));

            if (pHand.size() == 2 && pHand.get(0).getRank() == pHand.get(1).getRank()) {
                split1.add(pHand.get(0));
                split2.add(pHand.get(1));

                card1Split1.setImageResource(getResources().getIdentifier(pHand.get(0).getName(), "drawable", getPackageName()));
                card1Split2.setImageResource(getResources().getIdentifier(pHand.get(1).getName(), "drawable", getPackageName()));
                card1Split1.setVisibility(View.VISIBLE);
                card1Split2.setVisibility(View.VISIBLE);

                pHand.clear();

                Card toAdd = new Card();
                split1.add(toAdd);
                card2Split1.setImageResource(getResources().getIdentifier(split1.get(split1.size() - 1).getName(), "drawable", getPackageName()));
                card2Split1.setVisibility(View.VISIBLE);

                Card toAdd2 = new Card();
                split2.add(toAdd2);
                card2Split2.setImageResource(getResources().getIdentifier(split2.get(split2.size() - 1).getName(), "drawable", getPackageName()));
                card2Split2.setVisibility(View.VISIBLE);
            }

            pSumSplit = handPoints(split1);
            pSumSplit2 = handPoints(split2);

            playerPointsSplit.setText("Player: " + pSumSplit);
            playerPointsSplit.setVisibility(View.VISIBLE);

            playerPointsSplit2.setText("Player: " + pSumSplit2);
            playerPointsSplit2.setVisibility(View.VISIBLE);

        });

        standSplit.setOnClickListener(v -> {
            standSplit.setVisibility(View.GONE);
            hitSplit.setVisibility(View.GONE);

            standSplitClicked = true;

            pSumSplit = handPoints(split1);

            playerPointsSplit.setText("Player: " + pSumSplit);
            playerPointsSplit.setVisibility(View.VISIBLE);

            if (pSumSplit > 21) {
                Toast.makeText(BlackJack.this, "Lose first hand! Dealer Wins!", Toast.LENGTH_SHORT).show();
            } else {
                if (pSumSplit2 > 21 && !standSplit2Clicked || pSumSplit2 <= 21 && standSplit2Clicked) {
                    boolean canHit = true;
                    int dSum = 0;
                    int dAces = 0;

                    dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(), "drawable", getPackageName()));

                    while (canHit && (dSum < 17 || (dSum == 17 && dAces > 0))) {
                        Card toAdd = new Card();
                        dHand.add(toAdd);

                        int resourceId = getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(), "drawable", getPackageName());
                        switch (dHand.size() - 1) {
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
                            case 5:
                                dCard6.setImageResource(resourceId);
                                dCard6.setVisibility(View.VISIBLE);
                                break;
                        }

                        dSum += toAdd.getRank();

                        for (int i = 0; i < 2; i++) {
                            dSum += dHand.get(i).getRank();
                            if (dHand.get(i).getRank() == 11) {
                                dAces++;
                            }
                        }
                        if (toAdd.getRank() == 11) {
                            dAces++;
                        }
                        if (dSum > 21 && dAces > 0) {
                            dSum -= dAces * 10;
                        }
                    }

                    dealerPoints.setText("Dealer: " + dSum);
                    dealerPoints.setVisibility(View.VISIBLE);

                    if (dSum > 21) {
                        Toast.makeText(BlackJack.this, "Dealer busted! You win!", Toast.LENGTH_SHORT).show();
                        userCredits += 4 * playerBet;
                        updateTotalCredits(userCredits);
                    } else {
                        if (dSum == pSumSplit) {
                            Toast.makeText(BlackJack.this, "Dead end first hand!", Toast.LENGTH_SHORT).show();
                            userCredits += playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum == pSumSplit2) {
                            Toast.makeText(BlackJack.this, "Dead end second hand!", Toast.LENGTH_SHORT).show();
                            userCredits += playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum < pSumSplit) {
                            Toast.makeText(BlackJack.this, "Win first hand!", Toast.LENGTH_SHORT).show();
                            userCredits += 2 * playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum < pSumSplit2) {
                            Toast.makeText(BlackJack.this, "Win second hand!", Toast.LENGTH_SHORT).show();
                            userCredits += 2 * playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum > pSumSplit) {
                            Toast.makeText(BlackJack.this, "Lose first hand! Dealer wins!", Toast.LENGTH_SHORT).show();
                        } else if (dSum > pSumSplit2) {
                            Toast.makeText(BlackJack.this, "Lose second hand! Dealer wins!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            if (!standSplit2Clicked && pSumSplit2 > 21 || standSplit2Clicked && pSumSplit2 <= 21 && standSplitClicked && pSumSplit <= 21) {
                ok.setVisibility(View.VISIBLE);
            }
        });

        standSplit2.setOnClickListener(v -> {
            standSplit2.setVisibility(View.GONE);
            hitSplit2.setVisibility(View.GONE);

            standSplit2Clicked = true;

            pSumSplit2 = handPoints(split2);

            playerPointsSplit2.setText("Player: " + pSumSplit2);
            playerPointsSplit2.setVisibility(View.VISIBLE);


            if (pSumSplit2 > 21) {
                Toast.makeText(BlackJack.this, "Lose second hand! Dealer Wins!", Toast.LENGTH_SHORT).show();
            } else {
                if (pSumSplit > 21 && !standSplitClicked || pSumSplit <= 21 && standSplitClicked) {
                    boolean canHit = true;
                    int dSum = 0;
                    int dAces = 0;

                    dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(), "drawable", getPackageName()));

                    while (canHit && (dSum < 17 || (dSum == 17 && dAces > 0))) {
                        Card toAdd = new Card();
                        dHand.add(toAdd);

                        int resourceId = getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(), "drawable", getPackageName());
                        switch (dHand.size() - 1) {
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

                            case 5:
                                dCard6.setImageResource(resourceId);
                                dCard6.setVisibility(View.VISIBLE);
                                break;
                        }

                        dSum += toAdd.getRank();

                        for (int i = 0; i < 2; i++) {
                            dSum += dHand.get(i).getRank();
                            if (dHand.get(i).getRank() == 11) {
                                dAces++;
                            }
                        }
                        if (toAdd.getRank() == 11) {
                            dAces++;
                        }
                        if (dSum > 21 && dAces > 0) {
                            dSum -= dAces * 10;
                        }
                    }

                    dealerPoints.setText("Dealer: " + dSum);
                    dealerPoints.setVisibility(View.VISIBLE);

                    if (dSum > 21) {
                        Toast.makeText(BlackJack.this, "Dealer busted! You win!", Toast.LENGTH_SHORT).show();
                        userCredits += 4 * playerBet;
                        updateTotalCredits(userCredits);
                    } else {
                        if (dSum == pSumSplit) {
                            Toast.makeText(BlackJack.this, "Dead end first hand!", Toast.LENGTH_SHORT).show();
                            userCredits += playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum == pSumSplit2) {
                            Toast.makeText(BlackJack.this, "Dead end second hand!", Toast.LENGTH_SHORT).show();
                            userCredits += playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum < pSumSplit) {
                            Toast.makeText(BlackJack.this, "Win first hand!", Toast.LENGTH_SHORT).show();
                            userCredits += 2 * playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum < pSumSplit2) {
                            Toast.makeText(BlackJack.this, "Win second hand!", Toast.LENGTH_SHORT).show();
                            userCredits += 2 * playerBet;
                            updateTotalCredits(userCredits);
                        } else if (dSum > pSumSplit) {
                            Toast.makeText(BlackJack.this, "Lose first hand! Dealer wins!", Toast.LENGTH_SHORT).show();
                        } else if (dSum > pSumSplit2) {
                            Toast.makeText(BlackJack.this, "Lose second hand! Dealer wins!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            if (!standSplitClicked && pSumSplit > 21 || standSplit2Clicked && pSumSplit2 <= 21 && standSplitClicked && pSumSplit <= 21) {
                ok.setVisibility(View.VISIBLE);
            }
        });

        hit.setOnClickListener(v -> {
            split.setVisibility(View.GONE);
            clickerCounterHit++;

            Card toAdd = new Card();
            pHand.add(toAdd);

            ImageView newCard = null;
            switch (clickerCounterHit) {
                case 1:
                    newCard = card3;
                    break;
                case 2:
                    newCard = card4;
                    break;
                case 3:
                    newCard = card5;
                    break;
                case 4:
                    newCard = card6;
                    hit.setVisibility(View.GONE);
                    break;
            }

            newCard.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable", getPackageName()));
            newCard.setVisibility(View.VISIBLE);

            int sum = handPoints(pHand);

            playerPoints.setText("Player: " + sum);
            playerPoints.setVisibility(View.VISIBLE);

            if (sum > 21) {
                Toast.makeText(BlackJack.this, "Bad luck. Dealer Wins!", Toast.LENGTH_SHORT).show();
                ok.setVisibility(View.VISIBLE);
                stand.setVisibility(View.GONE);
                hit.setVisibility(View.GONE);
                split.setVisibility(View.GONE);
            }
        });

        hitSplit.setOnClickListener(v -> {
            clickerCounterHitSplit++;

            Card toAdd = new Card();
            split1.add(toAdd);

            ImageView newCard = null;
            switch (clickerCounterHitSplit) {
                case 1:
                    newCard = card3Split1;
                    break;
                case 2:
                    newCard = card4Split1;
                    break;
                case 3:
                    newCard = card5Split1;
                    break;
                case 4:
                    newCard = card6Split1;
                    hitSplit.setVisibility(View.GONE);
                    break;
            }

            newCard.setImageResource(getResources().getIdentifier(split1.get(split1.size() - 1).getName(), "drawable", getPackageName()));
            newCard.setVisibility(View.VISIBLE);

            pSumSplit = handPoints(split1);

            playerPointsSplit.setText("Player: " + pSumSplit);
            playerPointsSplit.setVisibility(View.VISIBLE);

            if (standSplit2Clicked && pSumSplit2 <= 21 && pSumSplit > 21) {
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
                    Card dToAdd = new Card();
                    dHand.add(dToAdd);

                    int resourceId = getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(), "drawable", getPackageName());
                    switch (dHand.size() - 1) {
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

                        case 5:
                            dCard6.setImageResource(resourceId);
                            dCard6.setVisibility(View.VISIBLE);
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

                dealerPoints.setText("Dealer: " + dSum);
                dealerPoints.setVisibility(View.VISIBLE);
            }

            if (pSumSplit > 21) {
                Toast.makeText(BlackJack.this, "Lose first hand! Dealer Wins!", Toast.LENGTH_SHORT).show();
                standSplit.setVisibility(View.GONE);
                hitSplit.setVisibility(View.GONE);

                if (!standSplit2Clicked && pSumSplit2 <= 21) {
                    ok.setVisibility(View.GONE);
                } else if (!standSplit2Clicked && pSumSplit2 > 21 || !standSplitClicked) {
                    ok.setVisibility(View.VISIBLE);
                }
            }
        });

        hitSplit2.setOnClickListener(v -> {
            clickerCounterHitSplit2++;

            Card toAdd = new Card();
            split2.add(toAdd);

            ImageView newCard = null;
            switch (clickerCounterHitSplit2) {
                case 1:
                    newCard = card3Split2;
                    break;
                case 2:
                    newCard = card4Split2;
                    break;
                case 3:
                    newCard = card5Split2;
                    break;
                case 4:
                    newCard = card6Split2;
                    hitSplit2.setVisibility(View.GONE);
                    break;
            }

            newCard.setImageResource(getResources().getIdentifier(split2.get(split2.size() - 1).getName(), "drawable", getPackageName()));
            newCard.setVisibility(View.VISIBLE);

            pSumSplit2 = handPoints(split2);

            playerPointsSplit2.setText("Player: " + pSumSplit2);
            playerPointsSplit2.setVisibility(View.VISIBLE);

            if (standSplitClicked && pSumSplit <= 21 && pSumSplit2 > 21) {
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

                    Card dToAdd = new Card();
                    dHand.add(dToAdd);

                    int resourceId = getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(), "drawable", getPackageName());
                    switch (dHand.size() - 1) {
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

                        case 5:
                            dCard6.setImageResource(resourceId);
                            dCard6.setVisibility(View.VISIBLE);
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

                dealerPoints.setText("Dealer: " + dSum);
                dealerPoints.setVisibility(View.VISIBLE);
            }

            if (pSumSplit2 > 21) {
                Toast.makeText(BlackJack.this, "Lose second hand! Dealer Wins!", Toast.LENGTH_SHORT).show();

                stand.setVisibility(View.GONE);
                hitSplit2.setVisibility(View.GONE);
                split.setVisibility(View.GONE);

                if (!standSplitClicked && pSumSplit <= 21) {
                    ok.setVisibility(View.GONE);
                } else if (!standSplitClicked && pSumSplit > 21 || !standSplit2Clicked) {
                    ok.setVisibility(View.VISIBLE);
                }
            }
        });

        ok.setOnClickListener(v -> {
            Toast.makeText(this, "BlackJack", Toast.LENGTH_SHORT).show();

            split1.clear();
            split2.clear();
            pHand.clear();
            dHand.clear();

            betResult.setVisibility(View.VISIBLE);
            creditsBar.setVisibility(View.VISIBLE);
            start.setVisibility(View.VISIBLE);
            leave.setVisibility(View.VISIBLE);
            linearPlayer.setVisibility(View.VISIBLE);
            ok.setVisibility(View.GONE);
            card1.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
            card3.setVisibility(View.GONE);
            card4.setVisibility(View.GONE);
            card5.setVisibility(View.GONE);
            card6.setVisibility(View.GONE);
            dCard1.setVisibility(View.GONE);
            dCard2.setVisibility(View.GONE);
            dCard3.setVisibility(View.GONE);
            dCard4.setVisibility(View.GONE);
            dCard5.setVisibility(View.GONE);
            dCard6.setVisibility(View.GONE);
            card1Split1.setVisibility(View.GONE);
            card2Split1.setVisibility(View.GONE);
            card3Split1.setVisibility(View.GONE);
            card4Split1.setVisibility(View.GONE);
            card5Split1.setVisibility(View.GONE);
            card6Split1.setVisibility(View.GONE);
            card1Split2.setVisibility(View.GONE);
            card2Split2.setVisibility(View.GONE);
            card3Split2.setVisibility(View.GONE);
            card4Split2.setVisibility(View.GONE);
            card5Split2.setVisibility(View.GONE);
            card6Split2.setVisibility(View.GONE);
            dealerPoints.setVisibility(View.GONE);
            playerPoints.setVisibility(View.GONE);
            playerPointsSplit.setVisibility(View.GONE);
            playerPointsSplit2.setVisibility(View.GONE);
            linearSplit1.setVisibility(View.GONE);
            linearSplit2.setVisibility(View.GONE);
        });
    }

    public int handPoints(List<Card> hand) {
        int sum = 0;
        int aces = 0;

        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).getRank();
            if (hand.get(i).getRank() == 11) {
                aces++;
            }
        }
        if (sum > 21 && aces > 0) {
            sum -= aces * 10;
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
                    Toast.makeText(BlackJack.this, "Failed to save credits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(BlackJack.this, "Failed to save credits", Toast.LENGTH_SHORT).show();
                Logger.getLogger(BlackJack.class.getName()).log(Level.SEVERE, "Error Occurred", t);
            }
        });
    }
}


