package pebbelgame;

import java.util.concurrent.ThreadLocalRandom;

class PebbleGame {

    private Bag[] whiteBags;
    private Bag[] blackBags;

    private Player finishedPlayer = null;



    private static Player[] players;

    public static Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    class Player extends Thread{

        private int playerNum;
        private Bag defaultBlackBag;
        private Bag whiteBag;

        public Player(){
            //TODO - change test to remove this function!!

            // Testing function
            System.out.println("Player() to only be used for testing");
        }

        public Player(int playerNum, Bag defaultBlackBag, Bag whiteBag) {

            this.whiteBag = whiteBag;
            this.defaultBlackBag = defaultBlackBag;
            this.playerNum = playerNum;

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

        }

        private void place() {
            // TODO - removes a random pebble, change later if adding AI to choose which pebble

            defaultBlackBag.place( whiteBag.draw() );

        }

        @Override
        public void run() {

            //TODO - on starting player selects 10 random pebbles

            // TODO - ! a player can still win after a player has won
            //  ( if finshed player is set while another player is still running this while method,
            //  and also wins before win condition is checked again

            // Runs whilst no player has won
            while (finishedPlayer == null) {

                place();

                draw();

                if ( whiteBag.sumBag() == 100) {
                    finishedPlayer = this;
                }

            }

            // Thread will terminate

        }
    }

}
