package pt.iade.guilhermeabrantes.blackjack.models;

import android.util.Log;

public class Dices {
    public final static int D_ONE = 1;
    public final static int D_TWO = 2;
    public final static int D_THREE = 3;
    public final static int D_FOUR = 4;
    public final static int D_FIVE = 5;

    //FACE
    public final static int ONE = 1;
    public final static int TWO = 2;
    public final static int THREE = 3;
    public final static int FOUR = 4;
    public final static int FIVE = 5;
    public final static int SIX = 6;

    private int rank;
    private int face;
    private String nameOfDice;


    public Dices() {

        int tempRank = (int) Math.floor(Math.random() * 5) + 1;
        this.face = (int) Math.floor(Math.random() * 6) + 1;

        Log.d("Dices", "tempRank: " + tempRank);
        Log.d("Dices", "this.face: " + this.face);

        switch (tempRank) {
            case D_ONE:
                this.nameOfDice = "dado1";
                break;

            case D_TWO:
                this.nameOfDice = "dado2";
                break;

            case D_THREE:
                this.nameOfDice = "dado3";
                break;

            case D_FOUR:
                this.nameOfDice = "dado4";
                break;

            case D_FIVE:
                this.nameOfDice = "dado5";
                break;
        }
        switch (this.face) {
            case ONE:
                this.nameOfDice = this.nameOfDice.concat("um");
                this.rank = 1;
                break;
            case TWO:
                this.nameOfDice = this.nameOfDice.concat("dois");
                this.rank = 2;
                break;
            case THREE:
                this.nameOfDice = this.nameOfDice.concat("tres");
                this.rank = 3;
                break;
            case FOUR:
                this.nameOfDice = this.nameOfDice.concat("quatro");
                this.rank = 4;
                break;
            case FIVE:
                this.nameOfDice = this.nameOfDice.concat("cinco");
                this.rank = 5;
                break;
            case SIX:
                this.nameOfDice = this.nameOfDice.concat("seis");
                this.rank = 6;
                break;

        }
        Log.d("Dices", "this.nameOfDice before concat: " + this.nameOfDice);
        Log.d("Dices", "this.nameOfDice after concat: " + this.nameOfDice);
    }
    public int getRank(){
        return rank;
    }
    public String getName(){
        return nameOfDice;
    }
}
