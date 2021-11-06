package pebbelgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Bag {

    // Array List so when items are deleted they will not be selected by our random picker
    // TODO - if you think of a solution which involves a more efficient data structure go for it
    private ArrayList<Integer> pebbles;

    public Bag() {
        pebbles = new ArrayList<>();
    }

    public Bag(String fileLocation) throws FileNotFoundException  {

        pebbles = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fileLocation));

        String line;

        try {
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                for (String i : values) {
                    pebbles.add(Integer.parseInt(i));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Integer> getPebbleList() {
        return pebbles;
    }

    // ! Throwing NullPointer when bag is empty, player class can catch exception and try another bag
    public int draw() throws NullPointerException{
        //TODO

        return 0;
    }

    public void place(int i) {
        //TODO
    }

}
