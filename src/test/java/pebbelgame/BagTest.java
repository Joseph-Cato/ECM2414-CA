package pebbelgame;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BagTest {

    @Test
    public void drawTest() {

        Boolean b = false;

        Bag bag2 = new Bag('2');

        ArrayList arrayList = new ArrayList();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        bag2.setPebbles(arrayList);

        int i = bag2.draw();

        if ( i == 1 || i == 2 || i == 3 ) {
            b = true;
        }

        Assert.assertTrue(b);

    }

    @Test
    public void drawTestPebbleRemoval() {

        Bag bag3 = new Bag('3');

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);

        bag3.setPebbles(arrayList);

        bag3.draw();

        Assert.assertEquals(0, bag3.getPebbles().size());

    }

    @Test
    public void drawTestEmptyBag() {

        Bag bag1 = new Bag('1');

        Assert.assertThrows(NullPointerException.class, () -> bag1.draw());
    }


}
