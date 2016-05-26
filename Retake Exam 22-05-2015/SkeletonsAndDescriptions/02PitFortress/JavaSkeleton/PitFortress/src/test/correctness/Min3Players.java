package test.correctness;

import junit.framework.Assert;
import main.models.Player;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class Min3Players extends BaseTestClass {
    @Category(Correctness.class)

            @Test(expected=IllegalArgumentException.class)
    public void CorrectnessMin3Players_WithoutPlayers_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.min3PlayersByScore();
    }

    @Category(Correctness.class)
            @Test(expected = IllegalArgumentException.class)
    public void CorrectnessMin3Players_WithLessThan3Players_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Acho", 5);
        this.PitFortressCollection.addPlayer("Becho", 5);

        this.PitFortressCollection.min3PlayersByScore();
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessMin3Players_WithValidPlayers_ShouldReturnExactly3Players()
    {
        this.PitFortressCollection.addPlayer("Cecho", 5);
        this.PitFortressCollection.addPlayer("Decho", 5);
        this.PitFortressCollection.addPlayer("Echo", 5);
        this.PitFortressCollection.addPlayer("Jecho", 6);
        this.PitFortressCollection.addPlayer("Zecho", 7);

        List<Player> players = toList(this.PitFortressCollection.min3PlayersByScore());

        Assert.assertEquals(3, players.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessMin3Players_WithMultipleValidPlayers_ShouldReturnCorrectPlayers()
    {
        this.PitFortressCollection.addPlayer("Mr.MMS", 0);
        this.PitFortressCollection.addPlayer("Memory", 1);
        this.PitFortressCollection.addPlayer("Limit", 2);
        this.PitFortressCollection.addPlayer("Stack", 3);
        this.PitFortressCollection.addPlayer("Overflow", 4);
        this.PitFortressCollection.addPlayer("Memory Limit Memory Limit Stack Overflow", 5);

        List<Player> botPlayers = toList(this.PitFortressCollection.min3PlayersByScore());

        Assert.assertEquals(botPlayers.get(0).getName(), "Limit");
        Assert.assertEquals(botPlayers.get(0).getRadius(), 2);
        Assert.assertEquals(botPlayers.get(0).getScore(), 0);

        Assert.assertEquals(botPlayers.get(1).getName(), "Memory");
        Assert.assertEquals(botPlayers.get(1).getRadius(), 1);
        Assert.assertEquals(botPlayers.get(1).getScore(), 0);

        Assert.assertEquals(botPlayers.get(2).getName(), "Memory Limit Memory Limit Stack Overflow");
        Assert.assertEquals(botPlayers.get(2).getRadius(), 5);
        Assert.assertEquals(botPlayers.get(2).getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessMin3Players_WithThreeeValidPlayers_ShouldReturnCorrectlyRankedPlayers()
    {
        this.PitFortressCollection.addPlayer("A", 10);
        this.PitFortressCollection.addPlayer("B", 30);
        this.PitFortressCollection.addPlayer("C", 20);

        List<Player> botPlayers = toList(this.PitFortressCollection.min3PlayersByScore());

        Assert.assertEquals(botPlayers.get(0).getName(), "A");
        Assert.assertEquals(botPlayers.get(0).getRadius(), 10);
        Assert.assertEquals(botPlayers.get(0).getScore(), 0);

        Assert.assertEquals(botPlayers.get(1).getName(), "B");
        Assert.assertEquals(botPlayers.get(1).getRadius(), 30);
        Assert.assertEquals(botPlayers.get(1).getScore(), 0);

        Assert.assertEquals(botPlayers.get(2).getName(), "C");
        Assert.assertEquals(botPlayers.get(2).getRadius(), 20);
        Assert.assertEquals(botPlayers.get(2).getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessMin3Players_AfterAPlayerKillsAMinion_ShouldReturnCorrectlyRankedPlayers() {
        this.PitFortressCollection.addPlayer("Pesho", 20);
        this.PitFortressCollection.addPlayer("Gosho", 30);
        this.PitFortressCollection.addPlayer("StamatLoveca", 10);

        this.PitFortressCollection.addMinion(5);

        this.PitFortressCollection.setMine("StamatLoveca", 10, 1, 100);

        this.PitFortressCollection.playTurn();

        List<Player> botPlayers = toList(this.PitFortressCollection.min3PlayersByScore());

        Assert.assertEquals(botPlayers.get(0).getName(), "Gosho");
        Assert.assertEquals(botPlayers.get(0).getRadius(), 30);
        Assert.assertEquals(botPlayers.get(0).getScore(), 0);

        Assert.assertEquals(botPlayers.get(1).getName(), "Pesho");
        Assert.assertEquals(botPlayers.get(1).getRadius(), 20);
        Assert.assertEquals(botPlayers.get(1).getScore(), 0);

        Assert.assertEquals(botPlayers.get(2).getName(), "StamatLoveca");
        Assert.assertEquals(botPlayers.get(2).getRadius(), 10);
        Assert.assertEquals(botPlayers.get(2).getScore(), 1);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessMin3Players_AfterMulitplePlayersKillingMultipleMinions_ShouldReturnCorrectlyRankedPlayers()
    {
        this.PitFortressCollection.addPlayer("Pesho", 0);
        this.PitFortressCollection.addPlayer("Gosho", 0);
        this.PitFortressCollection.addPlayer("StamatLoveca", 0);
        this.PitFortressCollection.addPlayer("BoikoSnaiperista", 0);
        this.PitFortressCollection.addPlayer("JichkaTokoprovoda", 0);

        this.PitFortressCollection.addMinion(5);
        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(15);
        this.PitFortressCollection.addMinion(20);

        this.PitFortressCollection.setMine("JichkaTokoprovoda", 15, 1, 100);
        this.PitFortressCollection.setMine("BoikoSnaiperista", 10, 1, 100);
        this.PitFortressCollection.setMine("BoikoSnaiperista", 20, 1, 100);
        this.PitFortressCollection.setMine("StamatLoveca", 5, 1, 100);

        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.min3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "Gosho");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(0).getScore(), 0);

        Assert.assertEquals(topPlayers.get(1).getName(), "Pesho");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(1).getScore(), 0);

        Assert.assertEquals(topPlayers.get(2).getName(), "JichkaTokoprovoda");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(2).getScore(), 1);
    }
}
