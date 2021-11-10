package pebbelgame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class PebbleGame {

    ReentrantLock lock = new ReentrantLock();

    private Bag[] whiteBags;
    private Bag[] blackBags;
    private static int numberOfPlayers = 0;

    private Player finishedPlayer = null;

    private static ArrayList<Player> players;

    public PebbleGame() {

        // Creates white bags for game
        Bag whiteBagA = new Bag();
        Bag whiteBagB = new Bag();
        Bag whiteBagC = new Bag();

        Bag[] whiteBags = {whiteBagA, whiteBagB, whiteBagC};

        this.whiteBags = whiteBags;
    }

    public Player getFinishedPlayer() {
        return finishedPlayer;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Bag[] getBlackBags() {
        return blackBags;
    }

    public Bag[] getWhiteBags() {
        return whiteBags;
    }

    public void setBlackBags(Bag[] blackBags) {
        this.blackBags = blackBags;
    }

    public class Player extends Thread{

        private int playerNum;
        private Bag defaultBlackBag;
        private Bag whiteBag;
        private Character lastBagDrawnFrom = null;

        public Player(){
            //TODO - change test to remove this function!!
            numberOfPlayers++;
            // Testing function
            System.out.println("Player() to only be used for testing");
        }

        public Player(int playerNum, Bag defaultBlackBag, Bag whiteBag) {

            numberOfPlayers++;

            this.playerNum = numberOfPlayers;

            this.whiteBag = whiteBag;
            this.defaultBlackBag = defaultBlackBag;
            this.playerNum = playerNum;

        }

        public int getPlayerNum(){
            return playerNum;
        }

        private void draw() {

            // Sets the default bag
            Bag defaultBag = defaultBlackBag;

            // Checks default bag is not empty, selects new bag if it is
            while ( defaultBag.getPebbleList().isEmpty() ) {

                int randomBagIndex = ThreadLocalRandom.current().nextInt( 3 );

                defaultBag = blackBags[randomBagIndex];

            }

            lastBagDrawnFrom = defaultBag.getBagIdentifier();

            whiteBag.place(defaultBag.draw());

            // TODO - log

        }

        public void gameStartDraw() {

            for ( int i = 0; i < 10; i++ ) {
                draw();
            }

        }

        private void place() {
            // TODO - removes a random pebble, change later if adding AI to choose which pebble

            Bag blackBag = null;

            switch (lastBagDrawnFrom) {
                case 'X':

            }

            blackBag.place( whiteBag.draw() );

            // TODO - log

        }

        @Override
        public void run() {

            //TODO - on starting player selects 10 random pebbles

            // Runs whilst no player has won
            while (finishedPlayer == null) {

                // Player removes and gets a new pebble for their white bag

                /**
                Bag blackBag = null;

                Bag[] blackBagList = getBlackBags();
                for (Bag i : blackBagList) {
                    if ( i.getBagIdentifier() == lastBagDrawnFrom) {

                    }
                }
                 *//

                place();

                draw();

                // Win condition checking is locked so only one player can check at any given time
                lock.lock();

                // If a player has won and no other player has already fished, the player is set as the finishedPlayer (the winner)
                // if another player has already  nothing happens, while loop will be skipped on next step due to finishedPlayer not being null
                if ( whiteBag.sumBag() == 100 && finishedPlayer != null) {
                    finishedPlayer = this;
                }

                lock.unlock();

            }

            // Thread will terminate when while loop is terminated (when a player has finished)

        }
    }
}
