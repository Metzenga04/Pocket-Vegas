package pt.iade.guilhermeabrantes.blackjack;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import pt.iade.guilhermeabrantes.blackjack.models.Card;
import pt.iade.guilhermeabrantes.blackjack.models.Deck;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        ImageView card1 =findViewById(R.id.playerCard);
        ImageView card2 =findViewById(R.id.playerCard2);
        ImageView card3 =findViewById(R.id.playerCard3);

        ImageView dCard1 =findViewById(R.id.dealerCard);
        ImageView dCard2 =findViewById(R.id.dealerCard2);
        ImageView dCard3 =findViewById(R.id.dealerCard3);

        //TextView dialog =findViewById(R.id.scoreViewPlayer);

        deal.setVisibility(View.VISIBLE);
        stand.setVisibility(View.GONE);
        hit.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        card1.setVisibility(View.GONE);
        card2.setVisibility(View.GONE);
        card3.setVisibility(View.GONE);
        dCard1.setVisibility(View.GONE);
        dCard2.setVisibility(View.GONE);
        dCard3.setVisibility(View.GONE);
        //dialog.setVisibility(View.GONE);

        deal.setOnClickListener(v -> {
            deal.setVisibility(View.GONE);
            stand.setVisibility(View.VISIBLE);
            hit.setVisibility(View.VISIBLE);

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
                for (int i = 0;i< dHand.size();i++){
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
            for (int i = 0;i < pHand.size();i++){
                pSum += pHand.get(i).getRank();
                if (pHand.get(i).getRank()==11){
                    pAces++;
                }
            }
            if (pSum > 21 && pAces > 0){
                pSum -= pAces * 10;
            }

        });


    }
}