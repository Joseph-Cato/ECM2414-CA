package main;

import pebbelgame.InvalidDataException;
import ui.App;

public class Main {

    public static void main(String[] args) {

        App app = new App();

        try {
            app.gameStart();
        } catch (InvalidDataException e) {
            // Do Nothing
        }
    }


}
