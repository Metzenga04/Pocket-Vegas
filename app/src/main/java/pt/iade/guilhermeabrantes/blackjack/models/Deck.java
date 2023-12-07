package pt.iade.guilhermeabrantes.blackjack.models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }
    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        Card drawnCard = cards.remove(cards.size() - 1);

        return drawnCard;
    }
    public boolean isEmpty() {
        // Verifique se o baralho está vazio
        return cards.isEmpty();
    }
    }