package pt.iade.guilhermeabrantes.blackjack.models;

public class Dices {
    public final static int D_ONE = 1;
    public final static int D_ONE2 = 2;
    public final static int D_ONE3 = 3;
    public final static int D_ONE4 = 4;
    public final static int D_ONE5 = 5;
    public final static int D_TWO = 6;
    public final static int D_TWO2 = 7;
    public final static int D_TWO3 = 8;
    public final static int D_TWO4 = 9;
    public final static int D_TWO5 = 10;
    public final static int D_THREE = 11;
    public final static int D_THREE2 = 12;
    public final static int D_THREE3 = 13;
    public final static int D_THREE4 = 14;
    public final static int D_THREE5 = 15;
    public final static int D_FOUR = 16;
    public final static int D_FOUR2 = 17;
    public final static int D_FOUR3 = 18;
    public final static int D_FOUR4 = 19;
    public final static int D_FOUR5 = 20;
    public final static int D_FIVE = 21;
    public final static int D_FIVE2 = 22;
    public final static int D_FIVE3 = 23;
    public final static int D_FIVE4 = 24;
    public final static int D_FIVE5 = 25;

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
        int tempRank = (int) Math.floor(Math.random() * 25) + 1;
        this.face = (int) Math.floor(Math.random() * 6) + 1;

        switch (tempRank) {
            case D_ONE:
                this.nameOfDice = "dado1";
                this.rank = 1;
                break;
            case D_ONE2:
                this.nameOfDice ="dado1_2";
                this.rank = 1;
                break;
            case D_ONE3:
                this.nameOfDice = "dado1_3";
                this.rank = 1;
                break;
            case D_ONE4:
                this.nameOfDice ="dado1_4";
                this.rank = 1;
                break;
            case D_ONE5:
                this.nameOfDice = "dado1_5";
                this.rank = 1;
                break;
            case D_TWO:
                this.nameOfDice = "dado2";
                this.rank= 2;
                break;
            case D_TWO2:
                this.nameOfDice = "dado2_2";
                this.rank = 2;
                break;
            case D_TWO3:
                this.nameOfDice = "dado2_3";
                this.rank = 2;
                break;
            case D_TWO4:
                this.nameOfDice = "dado2_4";
                this.rank = 2;
                break;
            case D_TWO5:
                this.nameOfDice = "dado2_5";
                this.rank = 2;
                break;
            case D_THREE:
                this.nameOfDice = "dado3";
                this.rank = 3;
                break;
            case D_THREE2:
                this.nameOfDice = "dado3_2";
                this.rank = 3;
                break;
            case D_THREE3:
                this.nameOfDice = "dado3_3";
                this.rank = 3;
                break;
            case D_THREE4:
                this.nameOfDice = "dado3_4";
                this.rank = 3;
                break;
            case D_THREE5:
                this.nameOfDice = "dado3_5";
                this.rank = 3;
                break;
            case D_FOUR:
                this.nameOfDice = "dado4";
                this.rank = 4;
                break;
            case D_FOUR2:
                this.nameOfDice = "dado4_2";
                this.rank = 4;
                break;
            case D_FOUR3:
                this.nameOfDice = "dado4_3";
                this.rank = 4;
                break;
            case D_FOUR4:
                this.nameOfDice = "dado4_4";
                this.rank = 4;
                break;
            case D_FOUR5:
                this.nameOfDice = "dado4_5";
                this.rank = 4;
                break;
            case D_FIVE:
                this.nameOfDice = "dado5";
                this.rank = 5;
                break;
            case D_FIVE2:
                this.nameOfDice = "dado5_2";
                this.rank = 5;
                break;
            case D_FIVE3:
                this.nameOfDice = "dado5_3";
                this.rank = 5;
                break;
            case D_FIVE4:
                this.nameOfDice = "dado5_4";
                this.rank =5;
                break;
            case D_FIVE5:
                this.nameOfDice = "dado5_5";
                this.rank = 5;
                break;

        }
        switch (this.face) {
            case ONE:
                this.nameOfDice = this.nameOfDice.concat("um");
                break;
            case TWO:
                this.nameOfDice = this.nameOfDice.concat("dois");
                break;
            case THREE:
                this.nameOfDice = this.nameOfDice.concat("tres");
                break;
            case FOUR:
                this.nameOfDice = this.nameOfDice.concat("quatro");
                break;
            case FIVE:
                this.nameOfDice = this.nameOfDice.concat("cinco");
                break;
            case SIX:
                this.nameOfDice = this.nameOfDice.concat("seis");
                break;

        }
    }
    public int getRank(){
        return rank;
    }
    public String getName(){
        return nameOfDice;
    }
}
