package ui;


import pebbelgame.Bag;
import pebbelgame.InvalidDataException;
import pebbelgame.PebbleGame;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class App {


    private final Scanner scanner = new Scanner(System.in);


    private int setNumberOfPlayers() {

        System.out.print("Please enter the number of players:\n");

        try {

            // App's Scanner objects gets next user input
            int numberOfPlayers = scanner.nextInt();

            // If the integer input is less than 1, input will be requested again
            // Otherwise input is returned
            if (numberOfPlayers < 1) {

                System.out.print("Please enter a positive integer.\n\n");

                return setNumberOfPlayers();

            } else {

                return numberOfPlayers;
            }

            //Non integer inputs are caught with catch block and input is requested again
        } catch (InputMismatchException e) {

            System.out.print("Please enter a positive integer (less than 2,147,483,647)\n\n");

            return setNumberOfPlayers();
        }
    }

    private String getBagFileLocation(int bagNumber) {

        try {

            System.out.print("\nPlease enter location of bag number " + bagNumber + " to load:\n");

            return scanner.nextLine();

        } catch (NoSuchElementException e) {

            System.out.print("\nError: No input was found\n\n");

            return getBagFileLocation(bagNumber);

        }
    }

    private Bag createBlackBag(int bagNumber, char bagIdentifier) {

        try {

            String blackBagLocation = getBagFileLocation( bagNumber );

            Bag blackBag = new Bag(bagIdentifier);

            File csvFile = new File( blackBagLocation );

            if (!csvFile.isFile()) {

                System.out.print("\nERROR: File does not exist\n\n");

                return createBlackBag( bagNumber, bagIdentifier );

            }

            BufferedReader csvReader = new BufferedReader( new FileReader( blackBagLocation ) );

            ArrayList<Integer> pebbles = new ArrayList<>();

            while ( true ) {

                try {

                    String[] data = csvReader.readLine().split(",");

                    for (String i : data) {
                        pebbles.add(Integer.parseInt(i));
                    }

                } catch ( NumberFormatException e ) {

                    throw new InvalidDataException();

                } catch ( NullPointerException e) {
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            blackBag.setPebbles( pebbles );

            return blackBag;

        } catch (InvalidDataException e) {

            System.out.print("\nERROR: Invalid data please select a different file\n\n");

            return createBlackBag( bagNumber, bagIdentifier );

        } catch ( FileNotFoundException e ) {

            System.out.print("\nERROR: File does not exist\n\n");

            return createBlackBag( bagNumber, bagIdentifier );

        }
    }

    public void gameStart() throws InvalidDataException {

        System.out.print("""
    Welcome to the PebbleGame!!
    
    You will be basked to enter the number of players.
    Then you will be asked for the location of three files in turn containing comma separated
    integer values for the pebble weights.
    The number of pebbles per bag must be at least 11 times the number of players.
    All integer values must be strictly positive integers.
    
    The game will be simulated, the winner will be announced
    and output written to files in this directory.
    
    
    """);

        // Gets number of players
        int numberOfPlayers = setNumberOfPlayers();

        // Creates game and 3 white bags within constructor
        PebbleGame game = new PebbleGame();


        // Sets bags for game
        Bag blackBagX = createBlackBag(1, 'X');
        Bag blackBagY = createBlackBag(2, 'Y');
        Bag blackBagZ = createBlackBag(3, 'Z');

        Bag whiteBagA = new Bag( 'A' );
        Bag whiteBagB = new Bag( 'B' );
        Bag whiteBagC = new Bag( 'C' );


        blackBagX.setSiblingBag(whiteBagA);
        whiteBagA.setSiblingBag(blackBagX);

        blackBagY.setSiblingBag(whiteBagB);
        whiteBagB.setSiblingBag(blackBagY);

        blackBagZ.setSiblingBag(whiteBagC);
        whiteBagC.setSiblingBag(blackBagZ);


        Bag[] blackBags = {blackBagX, blackBagY, blackBagZ};


        game.setBlackBags(blackBags);

        game.setWhiteBags();



        // Creates and adds players to game
        for ( int i = 0; i < numberOfPlayers; i++) {

            try {

                game.addPlayer();

            } catch (IOException e) {

                System.out.print("FAILURE: Output file creation has failed");

                break;

            }
        }

        // Checks there are 11 times as many pebbles as players

        int totalPebbleCount = 0;

        for (Bag i : game.getBlackBags()) {
            totalPebbleCount += i.getPebbles().size();
        }

        if ( totalPebbleCount < 11*numberOfPlayers ) {

            System.out.print("\n\nERROR: There are not enough pebbles to run this game.\nPlease make sure there are at least 11 times as many pebbles as players.\n\nExiting...");

            throw new InvalidDataException();
        }


        // Each player collects 10 pebbles and starts playing the game
        game.startGame();

        System.out.print("\n Game has started...");

        while (true) {
            if (game.getFinishedPlayerBoolean()) {

                System.out.println( game.announceFinishedPlayer() );

                return;

            }

        }


    }

}
