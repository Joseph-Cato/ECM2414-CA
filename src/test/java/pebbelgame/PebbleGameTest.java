package pebbelgame;


import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;


public class PebbleGameTest {


    /**
     * --------------------- Player Tests --------------------- *
     */


    @Test
    public void placeAndDrawTest() {

        // Makes bag with 1 pebble in it
        ArrayList<Integer> pebbleList = new ArrayList<Integer>(1);
        for (int i = 0; i < 10; i++) {
            pebbleList.add(10);
        }
        Bag blackBag1 = new Bag( pebbleList );

        // Puts bag into array
        Bag[] bags = {blackBag1};

        // Creates new PebbleGame with one black bag
        PebbleGame game = new PebbleGame( bags );

        blackBag1 = game.getBlackBags()[0];

        Bag whiteBag1 = new Bag();



        PebbleGame.Player player = game.new Player( blackBag1, whiteBag1 );

        player.run();

        Assert.assertNotNull(game.getFinishedPlayer());




    }

}
