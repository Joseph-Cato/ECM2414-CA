package pebbelgame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PebbleGame {

    private Bag[] whiteBags;
    private Bag[] blackBags;
    private static int numberOfPlayers = 0;
    private ArrayList<Player> players = new ArrayList<Player>();
    private static Boolean finishedPlayerBoolean = false;
    private static Player finishedPlayer = null;


    public PebbleGame() {

        this.whiteBags = new Bag[]{new Bag('A'), new Bag('B'), new Bag('C')};
        this.blackBags = new Bag[]{new Bag('X'), new Bag('Y'), new Bag('Z')};
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setFinishedPlayer(Player finishedPlayer) {
        this.finishedPlayer = finishedPlayer;
    }

    public void setFinishedPlayerBoolean(Boolean finishedPlayerBoolean) {
        this.finishedPlayerBoolean = finishedPlayerBoolean;
    }

    public Boolean getFinishedPlayerBoolean() {
        return finishedPlayerBoolean;
    }

    public Bag[] getBlackBags() {
        return blackBags;
    }

    public void setWhiteBags(Bag[] whiteBags) {
        this.whiteBags = whiteBags;
    }

    public void setBlackBags(Bag[] blackBags) {
        this.blackBags = blackBags;
    }

    public void addPlayer() throws IOException {
         players.add( new Player(this) );
    }

    public void startGame() {

        for (Player i : players) {
            i.getTenPebbles();
        }

        for (Player i : players) {
            i.run();
        }

    }

    public String announceFinishedPlayer() {
        return "Player " + finishedPlayer.getPlayerNum() + " has won!";
    }

}


    class Player extends Thread{

        private int playerNum;
        private Bag lastBagDrawnFrom = null;
        private ArrayList<Integer> playerHand = new ArrayList<Integer>(10);
        private PebbleGame pebbleGame;
        private static int numberOfPlayers = 0;
        FileWriter fileWriter;

        static ReentrantLock lock = new ReentrantLock();


        public Player( PebbleGame pebbleGame ) throws IOException {

            numberOfPlayers += 1;

            this.pebbleGame = pebbleGame;
            this.playerNum = numberOfPlayers;

            fileWriter = new FileWriter( String.valueOf( numberOfPlayers +".txt" ) );
        }

        public int getPlayerNum() {
            return playerNum;
        }

        private synchronized void draw() {

            while (true) {

                int randomBagIndex = ThreadLocalRandom.current().nextInt( 3 );

                Bag bag = pebbleGame.getBlackBags()[randomBagIndex];

                if ( bag.getPebbles().size() == 0 ) {

                    lock.lock();

                    bag.emptyBag();

                    lock.unlock();

                    continue;
                }

                int pebble = bag.draw();

                lastBagDrawnFrom = bag;

                playerHand.add( pebble );

                try {

                    fileWriter.append("\nplayer" + playerNum + " has drawn a " + pebble + " from bag " + bag.getBagIdentifier() +
                            "\n" + "player" + playerNum + " hand is " );

                    for ( Integer i : playerHand ) {
                        fileWriter.append( i + ", ");
                    }

                } catch ( IOException e ) {

                    System.out.print( "ERROR: Player " + playerNum + " has failed to write to file." );

                }

                break;

            }

        }

        private void place() {

            int randomHandIndex = ThreadLocalRandom.current().nextInt( playerHand.size() );

            int pebble = playerHand.get(randomHandIndex);

            playerHand.remove(randomHandIndex);

            lastBagDrawnFrom.getSiblingBag().place(pebble);

            try {

                fileWriter.append("\nplayer" + playerNum + " has discarded a " + pebble + " to bag " + lastBagDrawnFrom.getSiblingBag().getBagIdentifier() +
                        "\n" + "player" + playerNum + " hand is " );

                for ( Integer i : playerHand ) {
                    fileWriter.append( i + ", ");
                }

            } catch ( IOException e ) {

                System.out.print( "ERROR: Player " + playerNum + " has failed to write to file." );

            }

        }

        public void getTenPebbles() {
            for ( int j = 0; j < 10; j++ ) {
                this.draw();
            }
        }

        @Override
        public void run() {

            while (pebbleGame.getFinishedPlayerBoolean() == false) {

                lock.lock();

                place();

                draw();

                int sum = 0;

                for (int i : playerHand) {
                    sum += i;
                }

                if (sum == 100) {
                    pebbleGame.setFinishedPlayerBoolean( true );

                    pebbleGame.setFinishedPlayer( this );

                }

                lock.unlock();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                
            }
        }

}
