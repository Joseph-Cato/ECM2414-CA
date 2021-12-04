package pebbelgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {

    private ArrayList<Integer> pebbles;
    private char bagIdentifier;
    private Bag siblingBag;


    public Bag(char bagIdentifier) {
        pebbles = new ArrayList<>(100);
        this.bagIdentifier = bagIdentifier;
    }


    public void setPebbles(ArrayList<Integer> pebbles) {
        this.pebbles = pebbles;
    }

    public ArrayList<Integer> getPebbles() {
        return pebbles;
    }


    // ! Throwing NullPointer when bag is empty, player class can catch exception and try another bag
    public synchronized int draw() throws NullPointerException {

        try {

            // Using ThreadLocalRandom for performance reasons
            int randomIndex = ThreadLocalRandom.current().nextInt(pebbles.size());

            int pebble = pebbles.get(randomIndex);

            pebbles.remove(randomIndex);

            return pebble;

        } catch (IllegalArgumentException e) {
            throw new NullPointerException();
        }
    }

    public void place(int i) {

        // Pebble is added to the end of the ArrayList so the indexes being used by draw() are not interfered with
        pebbles.add(i);

    }






}
