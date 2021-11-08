package pebbelgame;

class PebbleGame {

    private Bag[] whiteBags;
    private Bag[] blackBags;



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



        }

        private void place() {

        }

        @Override
        public void run() {

        }
    }

}
