package pt.iade.guilhermeabrantes.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TheGame extends AppCompatActivity {

    private Button btnLeaveTable;
    private Button btnStay;
    private Button btnHit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_game);

        btnLeaveTable = (Button) findViewById(R.id.btnLeaveGame);
        btnHit = (Button) findViewById(R.id.btnHit);
        btnStay = (Button) findViewById(R.id.btnStay);

        btnLeaveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TheGame.this, FrontPage.class));
            }


        });
    }
}