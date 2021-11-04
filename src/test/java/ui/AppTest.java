package ui;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void getNumberOfPlayersTest() {
        App app = App.getInstance();

        Assert.assertEquals(3, app.getNumberOfPlayers() );
    }
}
