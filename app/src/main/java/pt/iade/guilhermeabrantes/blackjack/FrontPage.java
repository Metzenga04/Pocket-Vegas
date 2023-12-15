package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity {

    private Button btnLogOff;
    private Button btnPlayBlack;
    private Button btnPlayWar;
    private Button btnPlayDice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        btnLogOff = (Button) findViewById(R.id.btnLogOff);
        btnPlayBlack = (Button) findViewById(R.id.btnBjPlay);
        btnPlayWar = (Button) findViewById(R.id.btnWgPlay);
        btnPlayDice = (Button) findViewById(R.id.btnDgPlay);

        btnLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrontPage.this,WarGame.class));
            }
        });
        btnPlayBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrontPage.this, BlackJack.class));
            }
        });
        btnPlayWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrontPage.this, WarGame.class));
            }
        });
        btnPlayDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrontPage.this, DiceGame.class));
            }
        });

    }
}