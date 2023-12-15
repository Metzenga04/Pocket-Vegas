package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiceGame extends AppCompatActivity {

    private Button btnRollDice;
    private Button btnLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        btnRollDice = (Button) findViewById(R.id.btnRollDice);
        btnLeave = (Button) findViewById(R.id.btnLeave);

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiceGame.this, FrontPage.class));
            }
        });

    }

}