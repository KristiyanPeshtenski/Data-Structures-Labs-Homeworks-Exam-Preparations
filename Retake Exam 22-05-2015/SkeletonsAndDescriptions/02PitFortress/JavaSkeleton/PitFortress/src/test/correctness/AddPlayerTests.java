package test.correctness;

import junit.framework.Assert;
import main.models.Player;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class AddPlayerTests extends BaseTestClass {

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnessAddPlayer_WithDuplicateName_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMS", 0);
        this.PitFortressCollection.addPlayer("Mr.MMS", 0);
    }

    @Category(Correctness.class)
            @Test(expected = IllegalArgumentException.class)
    public void CorrectnessAddPlayer_WithIncorrectRadius_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMS2", -10);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessAddPlayer_WithValidPlayerWithAnEmptyCollection_ShouldIncreasePlayerCounter()
    {
        this.PitFortressCollection.addPlayer("Mr.MMS3",0);

        Assert.assertEquals(this.PitFortressCollection.getPlayerCount(), 1);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessAddPlayer_WithExistingPlayers_ShouldIncreasePlayerCounterCorrectly()
    {
        this.PitFortressCollection.addPlayer("Kircho", 2);
        this.PitFortressCollection.addPlayer("Pencho", 0);
        this.PitFortressCollection.addPlayer("Jichka", 3);
        this.PitFortressCollection.addPlayer("Sashka", 4);
        this.PitFortressCollection.addPlayer("Ginka", 22);
        this.PitFortressCollection.addPlayer("Radomir", 87);

        Assert.assertEquals(this.PitFortressCollection.getPlayerCount(), 6);

        this.PitFortressCollection.addPlayer("Joro", 117);

        Assert.assertEquals(this.PitFortressCollection.getPlayerCount(), 7);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessAddPlayer_WithMultipleValidPlayers_ShouldIncreaseCounterCorrectly()
    {
        this.PitFortressCollection.addPlayer("Mr.MMS", 0);
        this.PitFortressCollection.addPlayer("Memory", 1);
        this.PitFortressCollection.addPlayer("Limit", 2);
        this.PitFortressCollection.addPlayer("Stack", 3);
        this.PitFortressCollection.addPlayer("Overflow", 4);

        Assert.assertEquals(this.PitFortressCollection.getPlayerCount(), 5);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessAddPlayer_WithMultipleValidPlayers_ShouldAddCorrectPlayers()
    {
        this.PitFortressCollection.addPlayer("Limit", 2);
        this.PitFortressCollection.addPlayer("Stack", 3);
        this.PitFortressCollection.addPlayer("Overflow", 4);

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "Stack");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 3);
        Assert.assertEquals(topPlayers.get(0).getScore(), 0);

           Assert.assertEquals(topPlayers.get(1).getName(), "Overflow");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 4);
        Assert.assertEquals(topPlayers.get(1).getScore(), 0);

       Assert.assertEquals(topPlayers.get(2).getName(), "Limit");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 2);
        Assert.assertEquals(topPlayers.get(2).getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessAddPlayer_WithMultipleValidPlayers2_ShouldAddCorrectPlayers()
    {
        this.PitFortressCollection.addPlayer("Pesho", 13);
        this.PitFortressCollection.addPlayer("Gosho", 21);
        this.PitFortressCollection.addPlayer("Stamat", 2);

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "Stamat");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 2);
        Assert.assertEquals(topPlayers.get(0).getScore(), 0);

        Assert.assertEquals(topPlayers.get(1).getName(), "Pesho");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 13);
        Assert.assertEquals(topPlayers.get(1).getScore(), 0);

        Assert.assertEquals(topPlayers.get(2).getName(), "Gosho");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 21);
        Assert.assertEquals(topPlayers.get(2).getScore(), 0);

        this.PitFortressCollection.addPlayer("Z", 11);

        topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "Z");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 11);
        Assert.assertEquals(topPlayers.get(0).getScore(), 0);
    }
}
