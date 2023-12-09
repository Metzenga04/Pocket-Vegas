package pt.iade.guilhermeabrantes.blackjack.models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Integer> cartas;

    public Deck() {
        inicializarBaralho();
        embaralharBaralho();
    }

    private void inicializarBaralho() {
        cartas = new ArrayList<>();
        String[] suits = {"H", "D", "C", "S"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (int i = 0; i < 2; i++) {
            for (int valor = 2; valor <= 14; valor++) {
                cartas.add(valor);
            }
        }
    }

    public void embaralharBaralho() {
        Collections.shuffle(cartas);
    }
    public int drawCard(){
        if (cartas.isEmpty()) {
            // Se o baralho estiver vazio, recria e embaralha
            inicializarBaralho();
            embaralharBaralho();
        }
        // Remove e retorna a carta do topo do baralho
        return cartas.remove(0);
    }
}