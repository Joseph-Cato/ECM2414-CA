package pebbelgame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BagTest {

    @Test
    public void BagTest() {

        Bag bag1 = new Bag();

        Assert.assertEquals(new ArrayList<Integer>(), bag1.getPebbleList());

        try {

            Bag bag2 = new Bag("testFile1.csv");

            ArrayList<Integer> expectedBag2 = new ArrayList<>();

            expectedBag2.add(1);
            expectedBag2.add(2);
            expectedBag2.add(3);
            expectedBag2.add(4);

            Assert.assertEquals(expectedBag2, bag2.getPebbleList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
