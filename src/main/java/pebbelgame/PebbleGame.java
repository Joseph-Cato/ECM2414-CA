package pebbelgame;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

class PebbleGame {

    ReentrantLock lock = new ReentrantLock();

    private Bag[] whiteBags;
    private Bag[] blackBags;
    private Player finishedPlayer = null;
    private static Player[] players;


    public PebbleGame(Bag[] blackBags) {
        this.blackBags = blackBags;


    }

    public Player getFinishedPlayer() {
        return finishedPlayer;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Bag[] getBlackBags() {
        return blackBags;
    }

    public Bag[] getWhiteBags() {
        return whiteBags;
    }

    class Player extends Thread{

        private Bag defaultBlackBag;
        private Bag whiteBag;



        public Player(){
            //TODO - change test to remove this function!!

            // Testing function
            System.out.println("Player() to only be used for testing");
        }

        public Player( Bag defaultBlackBag, Bag whiteBag) {

            this.whiteBag = whiteBag;
            this.defaultBlackBag = defaultBlackBag;

        }

        private void draw() {

            // Sets the default bag
            Bag defaultBag = defaultBlackBag;

            // Checks default bag is not empty, selects new bag if it is
            while ( defaultBag.getPebbleList().isEmpty() ) {

                int randomBagIndex = ThreadLocalRandom.current().nextInt( 3 );

                defaultBag = blackBags[randomBagIndex];

            }

            whiteBag.place(defaultBag.draw());

            // TODO - log

        }

        private void place() {
            // TODO - removes a random pebble, change later if adding AI to choose which pebble

            defaultBlackBag.place( whiteBag.draw() );

            // TODO - log

        }

        @Override
        public void run() {

            //TODO - on starting player selects 10 random pebbles

            // Runs whilst no player has won
            while (finishedPlayer == null) {

                // Player removes and gets a new pebble for their white bag

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

            // Thread will terminate

        }
    }
}
