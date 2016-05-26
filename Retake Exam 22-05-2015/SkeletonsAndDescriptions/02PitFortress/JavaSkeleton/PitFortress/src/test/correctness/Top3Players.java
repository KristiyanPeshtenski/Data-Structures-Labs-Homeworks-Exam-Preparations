package test.correctness;

import junit.framework.Assert;
import main.models.Player;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class Top3Players extends BaseTestClass {
    @Category(Correctness.class)
            @Test(expected = IllegalArgumentException.class)
    public void CorrectnessTop3Players_WithoutPlayers_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.top3PlayersByScore();
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnessTop3Players_WithLessThan3Players_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Cecho", 5);
        this.PitFortressCollection.addPlayer("Decho", 5);

        this.PitFortressCollection.top3PlayersByScore();
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessTop3Players_WithValidPlayers_ShouldReturnExactly3Players()
    {
        this.PitFortressCollection.addPlayer("Cecho", 5);
        this.PitFortressCollection.addPlayer("Decho", 5);
        this.PitFortressCollection.addPlayer("Echo", 5);
        this.PitFortressCollection.addPlayer("Jecho", 6);
        this.PitFortressCollection.addPlayer("Zecho", 7);

        List<Player> players = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(3,players.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessTop3Players_WithMultipleValidPlayers_ShouldReturnCorrectPlayers()
    {
        this.PitFortressCollection.addPlayer("Mr.MMS", 0);
        this.PitFortressCollection.addPlayer("Memory", 1);
        this.PitFortressCollection.addPlayer("Limit", 2);
        this.PitFortressCollection.addPlayer("Stack", 3);
        this.PitFortressCollection.addPlayer("Overflow", 4);
        this.PitFortressCollection.addPlayer("Memory Limit Memory Limit Stack Overflow", 5);

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "Stack");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 3);
        Assert.assertEquals(topPlayers.get(0).getScore(), 0);

        Assert.assertEquals(topPlayers.get(1).getName(), "Overflow");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 4);
        Assert.assertEquals(topPlayers.get(1).getScore(), 0);

        Assert.assertEquals(topPlayers.get(2).getName(), "Mr.MMS");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(2).getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessTop3Players_WithThreeeValidPlayers_ShouldReturnCorrectlyRankedPlayers()
    {
        this.PitFortressCollection.addPlayer("A", 17);
        this.PitFortressCollection.addPlayer("B", 33);
        this.PitFortressCollection.addPlayer("C", 9);

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "C");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 9);
        Assert.assertEquals(topPlayers.get(0).getScore(), 0);

        Assert.assertEquals(topPlayers.get(1).getName(), "B");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 33);
        Assert.assertEquals(topPlayers.get(1).getScore(), 0);

        Assert.assertEquals(topPlayers.get(2).getName(), "A");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 17);
        Assert.assertEquals(topPlayers.get(2).getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessTop3Players_AfterAPlayerKillsAMinion_ShouldReturnCorrectlyRankedPlayers()
    {
        this.PitFortressCollection.addPlayer("Pesho", 20);
        this.PitFortressCollection.addPlayer("Gosho", 30);
        this.PitFortressCollection.addPlayer("StamatLoveca", 10);

        this.PitFortressCollection.addMinion(5);

        this.PitFortressCollection.setMine("StamatLoveca", 10, 1, 100);

        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "StamatLoveca");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 10);
        Assert.assertEquals(topPlayers.get(0).getScore(), 1);

        Assert.assertEquals(topPlayers.get(1).getName(), "Pesho");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 20);
        Assert.assertEquals(topPlayers.get(1).getScore(), 0);

        Assert.assertEquals(topPlayers.get(2).getName(), "Gosho");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 30);
        Assert.assertEquals(topPlayers.get(2).getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessTop3Players_AfterMulitplePlayersKillingMultipleMinions_ShouldReturnCorrectlyRankedPlayers()
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

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(topPlayers.get(0).getName(), "BoikoSnaiperista");
        Assert.assertEquals(topPlayers.get(0).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(0).getScore(), 2);

        Assert.assertEquals(topPlayers.get(1).getName(), "StamatLoveca");
        Assert.assertEquals(topPlayers.get(1).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(1).getScore(), 1);

        Assert.assertEquals(topPlayers.get(2).getName(), "JichkaTokoprovoda");
        Assert.assertEquals(topPlayers.get(2).getRadius(), 0);
        Assert.assertEquals(topPlayers.get(2).getScore(), 1);
    }

}
