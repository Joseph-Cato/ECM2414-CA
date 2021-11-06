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

    class Player implements Runnable{



        public void run() {

        }
    }

}
