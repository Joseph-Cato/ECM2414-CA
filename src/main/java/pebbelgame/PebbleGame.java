package pebbelgame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class PebbleGame {

    private Bag[] whiteBags;
    private Bag[] blackBags;
    private static int numberOfPlayers = 0;

    public PebbleGame() {

        this.whiteBags = new Bag[]{new Bag('A'), new Bag('B'), new Bag('C')};
        this.blackBags = new Bag[]{new Bag('X'), new Bag('Y'), new Bag('Z')};
    }
}


    class Player extends Thread{

        private int playerNum;
        private Character lastBagDrawnFrom = null;
        private ArrayList<Integer> playerHand = new ArrayList<Integer>(10);


        public Player( int playerNum ) {
            this.playerNum = playerNum;
        }

}
