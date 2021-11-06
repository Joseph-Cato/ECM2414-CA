package ui;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class App {

    private Scanner scanner = new Scanner(System.in);

    // Static variable referencing single instance of App
    private static App instance = null;

    // Private constructor to avoid multiple instances of App
    private App() {

    }

    // Public method acts as constructor if no instance of App exists or returns current instance
    public static App getInstance() {
        if (instance == null) {
            instance =  new App();
        }

        return instance;
    }

    private String introText = """
    Welcome to the PebbleGame!!
    
    You will be basked to enter the number of players.
    Then you will be asked for the location of three files in turn containg comma seperated
    integer values for the pebble weights.
    The number of pebbles per bag must be at least 11 times the number of players.
    All integer values must be strictly positive integers.
    
    The game will be simulated, the winner will be announced
    and output written to files in this directory.
    
    
    """;

    private int setNumberOfPlayers() {

        System.out.print("Please enter the number of players:\n");

        try {

            // App's Scanner objects gets next user input
            int numberOfplayers = scanner.nextInt();

            // If the integer input is less than 1, input will be requested again
            // Otherwise input is returned
            if (numberOfplayers < 1) {

                System.out.print("Please enter a positive integer.\n\n");

                return setNumberOfPlayers();

            } else {

                return numberOfplayers;
            }

            //Non integer inputs are caught with catch block and input is requested again
        } catch (InputMismatchException e) {

            System.out.print("Please enter a positive integer (less than 2,147,483,647)\n\n");

            return setNumberOfPlayers();
        }
    }

    private String getBagFileLocation(int bagNumber) {

        System.out.print("\nPlease enter location of bag number " + bagNumber + " to load:\n");

        try {

            String fileLocation = scanner.nextLine();

            return fileLocation;

        } catch (NoSuchElementException e) {

            System.out.print("\nError: No input was found\n\n");

            return getBagFileLocation(bagNumber);

        }
    }

    public void gameStart() {

        System.out.print(introText);

        int numberOfPlayers = setNumberOfPlayers();

        String blackBagOneLocation = getBagFileLocation(1);

        

    }

}
