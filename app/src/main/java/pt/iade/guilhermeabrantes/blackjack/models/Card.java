package pt.iade.guilhermeabrantes.blackjack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Card {
    public final static int ACE = 1;
    public final static int ACE2 =14 ;
    public final static int TWO = 2;
    public final static int TWO2 = 15;
    public final static int THREE = 3;
    public final static int THREE2 = 16;
    public final static int FOUR = 4;
    public final static int FOUR2 = 17;
    public final static int FIVE = 5;
    public final static int FIVE2 = 18;
    public final static int SIX = 6;
    public final static int SIX2 =19;
    public final static int SEVEN = 7;
    public final static int SEVEN2 =20;
    public final static int EIGHT = 8;
    public final static int EIGHT2 = 21;
    public final static int NINE = 9;
    public final static int NINE2 = 22;
    public final static int TEN = 10;
    public final static int TEN2 =23;
    public final static int JACK = 11;
    public final static int JACK2 = 24;
    public final static int QUEEN = 12;
    public final static int QUEEN2 = 25;
    public final static int KING = 13;
    public final static int KING2 = 26;

    //SUITS
    public final static int DIAMONDS = 1;
    public final static int CLUBS = 2;
    public final static int HEARTS = 3;
    public final static int SPADES = 4;
    private int rank;

    private int suit;
    private String nameOfCard;

    public Card() {
        int tempRank = (int) Math.floor(Math.random() * 13) + 1;
        this.suit = (int) Math.floor(Math.random() * 4) + 1;

        switch (tempRank) {
            case ACE:
                this.nameOfCard = "asde";
                this.rank = 11;
                break;
            case ACE2:
                this.nameOfCard ="asde2";
                this.rank = 11;
                break;
            case TWO:
                this.nameOfCard = "doisde";
                this.rank = 2;
                break;
            case TWO2:
                this.nameOfCard ="doisde2";
                this.rank = 2;
                break;
            case THREE:
                this.nameOfCard = "tresde";
                this.rank = 3;
                break;
            case THREE2:
                this.nameOfCard = "tresde2";
                this.rank= 3;
                break;
            case FOUR:
                this.nameOfCard = "quatrode";
                this.rank = 4;
                break;
            case FOUR2:
                this.nameOfCard = "quatrode2";
                this.rank = 4;
                break;
            case FIVE:
                this.nameOfCard = "cincode";
                this.rank = 5;
                break;
            case FIVE2:
                this.nameOfCard = "cincode2";
                this.rank = 5;
                break;
            case SIX:
                this.nameOfCard = "seisde";
                this.rank = 6;
                break;
            case SIX2:
                this.nameOfCard = "seisde2";
                this.rank = 6;
                break;
            case SEVEN:
                this.nameOfCard = "setede";
                this.rank = 7;
                break;
            case SEVEN2:
                this.nameOfCard = "setede2";
                this.rank = 7;
                break;
            case EIGHT:
                this.nameOfCard = "oitode";
                this.rank = 8;
                break;
            case EIGHT2:
                this.nameOfCard = "oitode2";
                this.rank = 8;
                break;
            case NINE:
                this.nameOfCard = "novede";
                this.rank = 9;
                break;
            case NINE2:
                this.nameOfCard = "novede2";
                this.rank = 9;
                break;
            case TEN:
                this.nameOfCard = "dezde";
                this.rank = 10;
                break;
            case TEN2:
                this.nameOfCard = "dezde2";
                this.rank = 10;
                break;
            case JACK:
                this.nameOfCard = "valetede";
                this.rank = 10;
                break;
            case JACK2:
                this.nameOfCard = "valetede2";
                this.rank = 10;
                break;
            case QUEEN:
                this.nameOfCard = "damade";
                this.rank = 10;
                break;
            case QUEEN2:
                this.nameOfCard = "damade2";
                this.rank =10;
                break;
            case KING:
                this.nameOfCard = "reide";
                this.rank = 10;
                break;
            case KING2:
                this.nameOfCard = "reide2";
                this.rank = 10;
                break;

        }
        switch (this.suit) {
            case DIAMONDS:
                this.nameOfCard = this.nameOfCard.concat("ouros");
                break;
            case CLUBS:
                this.nameOfCard = this.nameOfCard.concat("paus");
                break;
            case HEARTS:
                this.nameOfCard = this.nameOfCard.concat("copas");
                break;
            case SPADES:
                this.nameOfCard = this.nameOfCard.concat("espadas");
                break;
        }
    }
    public int getRank(){
        return rank;
    }
    public String getName(){
        return nameOfCard;
    }
}

