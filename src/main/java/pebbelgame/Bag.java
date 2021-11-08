package pebbelgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class Bag {

    // Array List so when items are deleted they will not be selected by our random picker
    // TODO - if you think of a solution which involves a more efficient data structure go for it
    private ArrayList<Integer> pebbles;

    public Bag() {
        pebbles = new ArrayList<>();
    }

    public Bag(String fileLocation) throws InvalidDataException, IOException  {

        pebbles = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fileLocation));

        String line;

        try {
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                for (String i : values) {

                    if (Integer.parseInt(i) < 1) {

                        throw new InvalidDataException("File contained integer of value less than 1");

                    }

                    pebbles.add(Integer.parseInt(i));

                }
            }

        } catch (NumberFormatException e) {

            throw new InvalidDataException("File contained a non parsable integer (doubles, chars, etc)");

        }

        // Throws InvalidDataException if number of pebbles in bag is not greater than or equal to 11 times the player count
        if (pebbles.size() < PebbleGame.getPlayers().length*11) {
            throw new InvalidDataException();
        }

    }

    public ArrayList<Integer> getPebbleList() {
        return pebbles;
    }

    // Draw() and Place() are both synchronized so that no Player object can act on a Bag object at the same time

    // ! Throwing NullPointer when bag is empty, player class can catch exception and try another bag
    public synchronized int draw() throws NullPointerException{
        //TODO - removes an random pebble, change later if adding AI to choose which pebble

        // Using ThreadLocalRandom for performance reasons
        int randomIndex = ThreadLocalRandom.current().nextInt( pebbles.size() );

        int pebble = pebbles.get(randomIndex);

        pebbles.remove(randomIndex);

        return pebble;
    }

    //TODO - does place need to be synchronized?
    public synchronized void place(int i) {

        // Pebble is added to the end of the ArrayList so the indexes being used by draw() are not interfered with
        pebbles.add(i);

    }

    public int sumBag() {

        int pebbleSum = 0;

        for (int i : pebbles) {
            pebbleSum += i;
        }

        return pebbleSum;

    }

}
