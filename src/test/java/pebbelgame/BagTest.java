package pebbelgame;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BagTest {

    @Test
    public void BagTest() {

        Bag bag1 = new Bag();

        Assert.assertEquals(new ArrayList<Integer>(), bag1.getPebbleList());

        try {

            Bag bag2 = new Bag("testfiles/testFile1.csv");

            ArrayList<Integer> expectedBag2 = new ArrayList<>();

            expectedBag2.add(1);
            expectedBag2.add(2);
            expectedBag2.add(3);
            expectedBag2.add(4);

            Assert.assertEquals(expectedBag2, bag2.getPebbleList());

        } catch (InvalidDataException | NullPointerException e) {

            // Do nothing
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BagTestPebbleCount() {

        PebbleGame game = new PebbleGame();

        PebbleGame.Player player1 = game.new Player();
        PebbleGame.Player player2 = game.new Player();

        PebbleGame.Player[] players = {player1, player2};

        // Sets the instance of the game to have two players
        game.setPlayers(players);


        Assert.assertThrows(InvalidDataException.class, () -> new Bag("testfiles/testFile1.csv"));

        try {

            new Bag("testfiles/testFile2.csv");
            new Bag("testfiles/testFile3.csv");
        } catch (Exception e) {

            e.printStackTrace();
            throw new AssertionError();
        }
    }


}
