package pt.iade.guilhermeabrantes.blackjack;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import pt.iade.guilhermeabrantes.blackjack.models.Card;

import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        List<Card> pHand = new ArrayList<Card>();
        List<Card> dHand = new ArrayList<>();
        Button deal =findViewById(R.id.btnStart);
        Button stand =findViewById(R.id.btnStand);
        Button hit =findViewById(R.id.btnHit);
        Button ok =findViewById(R.id.btnOk);
        Button leave =findViewById(R.id.btnExit);
        Button hit2 =findViewById(R.id.btnHit2);
        Button hit3 =findViewById(R.id.btnHitAgain);
        Button split =findViewById(R.id.btnSplit);

        ImageView card1 =findViewById(R.id.playerCard);
        ImageView card2 =findViewById(R.id.playerCard2);
        ImageView card3 =findViewById(R.id.playerCard3);
        ImageView card4 =findViewById(R.id.playerCard4);
        ImageView card5 =findViewById(R.id.playerCard5);

        ImageView dCard1 =findViewById(R.id.dealerCard);
        ImageView dCard2 =findViewById(R.id.dealerCard2);
        ImageView dCard3 =findViewById(R.id.dealerCard3);

        deal.setVisibility(View.VISIBLE);
        stand.setVisibility(View.GONE);
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
        split.setVisibility(View.GONE);


        leave.setOnClickListener(v -> {
            startActivity(new Intent(BlackJack.this,FrontPage.class));
        });


        deal.setOnClickListener(v -> {
            deal.setVisibility(View.GONE);
            stand.setVisibility(View.VISIBLE);
            hit.setVisibility(View.VISIBLE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.GONE);

            Card first = new Card();
            Card second = new Card();

            pHand.add(first);
            pHand.add(second);

            card1.setImageResource(getResources().getIdentifier(first.getName(),"drawable",getPackageName()));
            card2.setImageResource(getResources().getIdentifier(second.getName(),"drawable",getPackageName()));

            card1.setVisibility(View.VISIBLE);
            card2.setVisibility(View.VISIBLE);

            Card dFirst = new Card();
            Card dSecond =new Card();

            dHand.add(dFirst);
            dHand.add(dSecond);

            dCard1.setImageResource(getResources().getIdentifier(dFirst.getName(),"drawable",getPackageName()));
            dCard2.setImageResource(getResources().getIdentifier("cardback","drawable",getPackageName()));

            dCard1.setVisibility(View.VISIBLE);
            dCard2.setVisibility(View.VISIBLE);
        });
        stand.setOnClickListener(v -> {
            stand.setVisibility(View.GONE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.GONE);

            dCard2.setImageResource(getResources().getIdentifier(dHand.get(1).getName(),"drawable",getPackageName()));

            boolean canHit =true;

            while (canHit){
                Card toAdd = new Card();
                dHand.add(toAdd);

                dCard3.setImageResource(getResources().getIdentifier(dHand.get(dHand.size() - 1).getName(),"drawable",getPackageName()));
                dCard2.setImageResource(getResources().getIdentifier(dHand.get(dHand.size() - 2).getName(), "drawable",getPackageName()));
                dCard1.setImageResource(getResources().getIdentifier(dHand.get(dHand.size() - 3).getName(),"drawable",getPackageName()));

                dCard3.setVisibility(View.VISIBLE);
                int sum =0;
                for (int i = 0;i < dHand.size();i++){
                    sum+=dHand.get(i).getRank();
                    if (dHand.get(i).getRank()==11){
                    }
                }
                if (sum > 16){
                    canHit =false;
                }
            }
            int pSum = 0;
            int pAces =0;
            for (int i = 0; i < pHand.size(); i++){
                pSum += pHand.get(i).getRank();
                if (pHand.get(i).getRank()==11){
                    pAces++;
                }
            }
            if (pSum > 21 && pAces > 0){
                pSum -= pAces * 10;
            }

            int dSum = 0;
            int dAces =0;
            for (int i = 0;i < dHand.size();i++){
                dSum += dHand.get(i).getRank();
                if (dHand.get(1).getRank()==11){
                    dAces++;
                }
            }
            if (dSum > 21 && dAces > 0){
                dSum -= pAces * 10;
            }

            if (dSum > 21){
                Toast.makeText(this, "Dealer busted. You win!", Toast.LENGTH_SHORT).show();
            }else if (pSum > dSum){
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Dealer Wins!", Toast.LENGTH_SHORT).show();
            }
            ok.setVisibility(View.VISIBLE);
        });

        hit.setOnClickListener(v -> {
           Card added = new Card();
           pHand.add(added);

           card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(),"drawable",getPackageName()));
           card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(),"drawable",getPackageName()));
           card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(), "drawable",getPackageName()));

           card3.setVisibility(View.VISIBLE);
           hit.setVisibility(View.GONE);
           hit2.setVisibility(View.VISIBLE);
           hit3.setVisibility(View.GONE);

           int sum = 0;
           int aceCtr =0;
           for (int i = 0;i < pHand.size();i++){
               sum += pHand.get(i).getRank();
               if (pHand.get(i).getRank()==11){
                   aceCtr++;
               }
           }
           if (sum > 21 && aceCtr > 0){
               sum -= aceCtr * 10;
           }

           if (sum > 21){

               Toast.makeText(this, "Bad luck.Dealer Wins!", Toast.LENGTH_SHORT).show();

               ok.setVisibility(View.VISIBLE);
               stand.setVisibility(View.GONE);
               hit.setVisibility(View.GONE);
               hit2.setVisibility(View.GONE);
               hit3.setVisibility(View.GONE);
           }
        });

        hit2.setOnClickListener(v -> {
            Card added = new Card();
            pHand.add(added);

            card4.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable",getPackageName()));
            card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(),"drawable",getPackageName()));
            card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(),"drawable",getPackageName()));
            card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 4).getName(), "drawable",getPackageName()));

            card4.setVisibility(View.VISIBLE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.VISIBLE);

            int sum = 0;
            int aceCtr =0;
            for (int i = 0;i < pHand.size();i++){
                sum += pHand.get(i).getRank();
                if (pHand.get(i).getRank()==11){
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0){
                sum -= aceCtr * 10;
            }

            if (sum > 21){

                Toast.makeText(this, "Bad luck.Dealer Wins!", Toast.LENGTH_SHORT).show();

                ok.setVisibility(View.VISIBLE);
                stand.setVisibility(View.GONE);
                hit.setVisibility(View.GONE);
                hit2.setVisibility(View.GONE);
                hit3.setVisibility(View.GONE);
            }
        });

        hit3.setOnClickListener(v -> {
            Card added = new Card();
            pHand.add(added);

            card5.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 1).getName(), "drawable",getPackageName()));
            card4.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 2).getName(), "drawable",getPackageName()));
            card3.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 3).getName(),"drawable",getPackageName()));
            card2.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 4).getName(),"drawable",getPackageName()));
            card1.setImageResource(getResources().getIdentifier(pHand.get(pHand.size() - 5).getName(), "drawable",getPackageName()));

            card5.setVisibility(View.VISIBLE);
            hit.setVisibility(View.GONE);
            hit2.setVisibility(View.GONE);
            hit3.setVisibility(View.GONE);

            int sum = 0;
            int aceCtr =0;
            for (int i = 0;i < pHand.size();i++){
                sum += pHand.get(i).getRank();
                if (pHand.get(i).getRank()==11){
                    aceCtr++;
                }
            }
            if (sum > 21 && aceCtr > 0){
                sum -= aceCtr * 10;
            }

            if (sum > 21) {

                Toast.makeText(this, "Bad luck.Dealer Wins!", Toast.LENGTH_SHORT).show();

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

            deal.setVisibility(View.VISIBLE);
            ok.setVisibility(View.GONE);
            card1.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
            card3.setVisibility(View.GONE);
            card4.setVisibility(View.GONE);
            card5.setVisibility(View.GONE);
            dCard1.setVisibility(View.GONE);
            dCard2.setVisibility(View.GONE);
            dCard3.setVisibility(View.GONE);
        });
    }
}