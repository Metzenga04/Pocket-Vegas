package pt.iade.guilhermeabrantes.blackjack.models;

public class Card {
    private String suit;
    private String rank;

    public Card(String suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getCardValue() {
        try {
            int value = Integer.parseInt(rank);
            return (value > 10) ? 10 : value;
        } catch (NumberFormatException e) {
            if ("A".equals(rank)) {
                return 11; // Valor padrão para o Ás
            } else {
                return 10; // Valor padrão para cartas de figura
            }
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}