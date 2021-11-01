package ui;


class App {

    // Static variable referencing single instance of App
    private static App instance = null;

    // Private constructor to avoid multiple instances of App
    private App() {

    }

    // Public method acts as constructor if no instance of App exists or returns current instance
    public App getInstance() {
        if (instance == null) {
            instance =  new App();
        }

        return instance;
    }

}
