package test.correctness;

import junit.framework.Assert;
import main.models.Mine;
import main.models.Minion;
import main.models.Player;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class PlayTurn extends BaseTestClass {
    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithOneSkipWithNoMines_ShouldNotChangeExistingObjectsCount()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);
        this.PitFortressCollection.addMinion(40);
        this.PitFortressCollection.addMinion(50);

        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());
        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(3, topPlayers.size());
        Assert.assertEquals(5, minions.size());
        Assert.assertEquals(0, mines.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithNoMines_ShouldNotChangeExistingObjectsCount()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);
        this.PitFortressCollection.addMinion(40);
        this.PitFortressCollection.addMinion(50);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());
        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(3, topPlayers.size());
        Assert.assertEquals(5, minions.size());
        Assert.assertEquals(0, mines.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithOneSkipWithNoMines_ShouldNotChangeExistingObjects()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());
        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(0, topPlayers.get(0).getScore());
        Assert.assertEquals(0, topPlayers.get(1).getScore());
        Assert.assertEquals(0, topPlayers.get(2).getScore());

        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(100, minions.get(1).getHealth());
        Assert.assertEquals(100, minions.get(2).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithNoMines_ShouldNotChangeExistingObjects()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());
        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(0, topPlayers.get(0).getScore());
        Assert.assertEquals(0, topPlayers.get(1).getScore());
        Assert.assertEquals(0, topPlayers.get(2).getScore());

        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(100, minions.get(1).getHealth());
        Assert.assertEquals(100, minions.get(2).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithASingleMineWithNoMinionsInRange_ShouldDeleteMineAfterBlowingUp()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);

        this.PitFortressCollection.setMine("Pesho", 100, 4, 100);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(1, mines.size());

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(0, mines.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithASingleMineWithNoMinionsInRange_ShouldBlowMineWithoutKillingMinions()
    {
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);
        this.PitFortressCollection.addMinion(40);
        this.PitFortressCollection.addMinion(50);

        this.PitFortressCollection.setMine("Stamat", 100, 4, 100);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(5, minions.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithASingleMineWithNoMinionsInRange_SholdNotDamageMinions()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 100, 4, 100);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(100, minions.get(1).getHealth());
        Assert.assertEquals(100, minions.get(2).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithAMineDamagingAMinion_ShouldBlowMineAndDamageMinion()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 31, 4, 99);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(10,  minions.get(0).getX());
        Assert.assertEquals(1,   minions.get(0).getId());
        Assert.assertEquals(100, minions.get(0).getHealth());

        Assert.assertEquals(20,  minions.get(1).getX());
        Assert.assertEquals(2,   minions.get(1).getId());
        Assert.assertEquals(100, minions.get(1).getHealth());

        Assert.assertEquals(30, minions.get(2).getX());
        Assert.assertEquals(3,  minions.get(2).getId());
        Assert.assertEquals(1,  minions.get(2).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkipsWithAMineDamagingAMinion_ShouldNotChangePlayersScore()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 31, 4, 99);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(0, topPlayers.get(0).getScore());
        Assert.assertEquals(0, topPlayers.get(1).getScore());
        Assert.assertEquals(0, topPlayers.get(2).getScore());
    }


    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithAMineKillingAMinion_ShouldDeleteCorrectMinion()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 31, 2, 100);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(2, minions.size());

        Assert.assertEquals(10,  minions.get(0).getX());
        Assert.assertEquals(1,   minions.get(0).getId());
        Assert.assertEquals(100, minions.get(0).getHealth());

        Assert.assertEquals(20,  minions.get(1).getX());
        Assert.assertEquals(2,   minions.get(1).getId());
        Assert.assertEquals(100, minions.get(1).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithKillingAMinionWith2Mines_ShouldDeleteCorrectMinion()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 31, 2, 50);
        this.PitFortressCollection.setMine("Gosho", 30, 3, 50);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());
        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(2, minions.size());

        Assert.assertEquals(10,  minions.get(0).getX());
        Assert.assertEquals(1,   minions.get(0).getId());
        Assert.assertEquals(100, minions.get(0).getHealth());

        Assert.assertEquals(20,  minions.get(1).getX());
        Assert.assertEquals(2,   minions.get(1).getId());
        Assert.assertEquals(100, minions.get(1).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithAMineKillingAMinion_ShouldChangePlayerScore()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 31, 4, 100);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(1, topPlayers.get(0).getScore());
        Assert.assertEquals("Stamat", topPlayers.get(0).getName());
        Assert.assertEquals(3, topPlayers.get(0).getRadius());

        Assert.assertEquals(0, topPlayers.get(1).getScore());
        Assert.assertEquals("Pesho", topPlayers.get(1).getName());
        Assert.assertEquals(1, topPlayers.get(1).getRadius());

        Assert.assertEquals(0, topPlayers.get(2).getScore());
        Assert.assertEquals("Gosho", topPlayers.get(2).getName());
        Assert.assertEquals(2, topPlayers.get(2).getRadius());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithKillingAMinionWith2Mines_ShouldRaiseKillingMinesPlayesScore()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Gosho", 2);
        this.PitFortressCollection.addPlayer("Stamat", 3);

        this.PitFortressCollection.addMinion(10);
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);

        this.PitFortressCollection.setMine("Stamat", 31, 4, 80);
        this.PitFortressCollection.setMine("Pesho", 31, 4, 20);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(1, topPlayers.get(0).getScore());
        Assert.assertEquals("Pesho", topPlayers.get(0).getName());
        Assert.assertEquals(1, topPlayers.get(0).getRadius());

        Assert.assertEquals(0, topPlayers.get(1).getScore());
        Assert.assertEquals("Stamat", topPlayers.get(1).getName());
        Assert.assertEquals(3, topPlayers.get(1).getRadius());

        Assert.assertEquals(0, topPlayers.get(2).getScore());
        Assert.assertEquals("Gosho", topPlayers.get(2).getName());
        Assert.assertEquals(2, topPlayers.get(2).getRadius());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithAMinionOnTheEdgeOfMinesRange_ShouldHitMinion()
    {
        this.PitFortressCollection.addPlayer("Dimcho", 5);
        this.PitFortressCollection.addPlayer("Domcho", 6);
        this.PitFortressCollection.addPlayer("Dumcho", 7);

        this.PitFortressCollection.addMinion(0);
        this.PitFortressCollection.addMinion(100);
        this.PitFortressCollection.addMinion(200);

        this.PitFortressCollection.setMine("Dumcho", 107, 3, 66);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(100, minions.get(1).getX());
        Assert.assertEquals(2, minions.get(1).getId());
        Assert.assertEquals(34, minions.get(1).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleMinionsInMinesRange_ShouldHitAllOfThem()
    {
        this.PitFortressCollection.addPlayer("Domcho", 100);

        this.PitFortressCollection.addMinion(0);
        this.PitFortressCollection.addMinion(100);
        this.PitFortressCollection.addMinion(200);
        this.PitFortressCollection.addMinion(201);

        this.PitFortressCollection.setMine("Domcho", 100, 5, 11);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(0, minions.get(0).getX());
        Assert.assertEquals(1, minions.get(0).getId());
        Assert.assertEquals(89, minions.get(0).getHealth());

        Assert.assertEquals(100, minions.get(1).getX());
        Assert.assertEquals(2, minions.get(1).getId());
        Assert.assertEquals(89, minions.get(1).getHealth());

        Assert.assertEquals(200, minions.get(2).getX());
        Assert.assertEquals(3, minions.get(2).getId());
        Assert.assertEquals(89, minions.get(2).getHealth());

        Assert.assertEquals(201, minions.get(3).getX());
        Assert.assertEquals(4, minions.get(3).getId());
        Assert.assertEquals(100, minions.get(3).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleMinesWithDifferentDelays_ShouldBlowThemAtCorrectTurns()
    {
        this.PitFortressCollection.addPlayer("Dimcho", 10);
        this.PitFortressCollection.addPlayer("Domcho", 20);
        this.PitFortressCollection.addPlayer("Dumcho", 30);

        this.PitFortressCollection.addMinion(0);
        this.PitFortressCollection.addMinion(100);
        this.PitFortressCollection.addMinion(110);
        this.PitFortressCollection.addMinion(120);

        this.PitFortressCollection.setMine("Dumcho", 135, 1, 10);
        this.PitFortressCollection.setMine("Domcho", 110, 4, 20);
        this.PitFortressCollection.setMine("Dimcho", 99, 6, 30);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(3, mines.size());

        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(100, minions.get(1).getHealth());
        Assert.assertEquals(100, minions.get(2).getHealth());
        Assert.assertEquals(100, minions.get(3).getHealth());

        //Pass first turn
        this.PitFortressCollection.playTurn();

        minions = toList(this.PitFortressCollection.reportMinions());
        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(2, mines.size());
        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(100, minions.get(1).getHealth());
        Assert.assertEquals(90, minions.get(2).getHealth());
        Assert.assertEquals(90, minions.get(3).getHealth());

        //Pass second and third turn
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        minions = toList(this.PitFortressCollection.reportMinions());
        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(2, mines.size());
        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(100, minions.get(1).getHealth());
        Assert.assertEquals(90, minions.get(2).getHealth());
        Assert.assertEquals(90, minions.get(3).getHealth());

        //Pass fourth turn
        this.PitFortressCollection.playTurn();

        minions = toList(this.PitFortressCollection.reportMinions());
        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(1, mines.size());
        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(80, minions.get(1).getHealth());
        Assert.assertEquals(70, minions.get(2).getHealth());
        Assert.assertEquals(70, minions.get(3).getHealth());

        //Pass fifth turn
        this.PitFortressCollection.playTurn();

        minions = toList(this.PitFortressCollection.reportMinions());
        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(1, mines.size());
        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(80, minions.get(1).getHealth());
        Assert.assertEquals(70, minions.get(2).getHealth());
        Assert.assertEquals(70, minions.get(3).getHealth());

        //Pass sixth turn
        this.PitFortressCollection.playTurn();

        minions = toList(this.PitFortressCollection.reportMinions());
        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(0, mines.size());
        Assert.assertEquals(100, minions.get(0).getHealth());
        Assert.assertEquals(50, minions.get(1).getHealth());
        Assert.assertEquals(70, minions.get(2).getHealth());
        Assert.assertEquals(70, minions.get(3).getHealth());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithASingleSkip_ShouldCorrectlyReduceMineDelays()
    {
        this.PitFortressCollection.addPlayer("X", 10);
        this.PitFortressCollection.addPlayer("Y", 20);
        this.PitFortressCollection.addPlayer("Z", 30);

        this.PitFortressCollection.setMine("Y", 100, 10, 2);
        this.PitFortressCollection.setMine("Z", 100, 3, 2);
        this.PitFortressCollection.setMine("Z", 100, 21, 2);
        this.PitFortressCollection.setMine("X", 100, 10000, 2);

        this.PitFortressCollection.playTurn();

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(2, mines.get(0).getDelay());
        Assert.assertEquals(9, mines.get(1).getDelay());
        Assert.assertEquals(20, mines.get(2).getDelay());
        Assert.assertEquals(9999, mines.get(3).getDelay());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleSkips_ShouldCorrectlyReduceMineDelays()
    {
        this.PitFortressCollection.addPlayer("X", 10);
        this.PitFortressCollection.addPlayer("Y", 20);
        this.PitFortressCollection.addPlayer("Z", 30);

        this.PitFortressCollection.setMine("Y", 100, 17, 2);
        this.PitFortressCollection.setMine("Z", 100, 27, 2);
        this.PitFortressCollection.setMine("X", 100, 1007, 2);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(10, mines.get(0).getDelay());
        Assert.assertEquals(20, mines.get(1).getDelay());
        Assert.assertEquals(1000, mines.get(2).getDelay());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithTwoMinesWithSameDelay_ShouldExplodeTheOneWithSmallerIdFirst()
    {
        this.PitFortressCollection.addPlayer("X", 10);
        this.PitFortressCollection.addPlayer("Y", 20);
        this.PitFortressCollection.addPlayer("Z", 30);

        this.PitFortressCollection.addMinion(100);

        this.PitFortressCollection.setMine("Z", 100, 3, 100);
        this.PitFortressCollection.setMine("Y", 100, 3, 100);

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        List<Player> topPlayers = toList(this.PitFortressCollection.top3PlayersByScore());

        Assert.assertEquals(1, topPlayers.get(0).getScore());
        Assert.assertEquals("Z", topPlayers.get(0).getName());
        Assert.assertEquals(30, topPlayers.get(0).getRadius());

        Assert.assertEquals(0, topPlayers.get(1).getScore());
        Assert.assertEquals("Y", topPlayers.get(1).getName());
        Assert.assertEquals(20, topPlayers.get(1).getRadius());


        Assert.assertEquals(0, topPlayers.get(2).getScore());
        Assert.assertEquals("X", topPlayers.get(2).getName());
        Assert.assertEquals(10, topPlayers.get(2).getRadius());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithMultipleMinesWithSameDelay_ShouldExplodeAll()
    {
        this.PitFortressCollection.addPlayer("X", 10);
        this.PitFortressCollection.addPlayer("Y", 20);
        this.PitFortressCollection.addPlayer("Z", 30);

        this.PitFortressCollection.addMinion(100);

        this.PitFortressCollection.setMine("Z", 100, 3, 100);
        this.PitFortressCollection.setMine("Z", 50, 3, 50);
        this.PitFortressCollection.setMine("Y", 100, 3, 100);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());
        Assert.assertEquals(3, mines.size());

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(0,mines.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessPlayTurn_WithAMineWith0Damage_ShouldNotDamageMinion()
    {
        this.PitFortressCollection.addPlayer("Z", 30);

        this.PitFortressCollection.addMinion(100);

        this.PitFortressCollection.setMine("Z", 100, 3, 0);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        Assert.assertEquals(100, minions.get(0).getHealth());

        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();
        this.PitFortressCollection.playTurn();

        minions = toList(this.PitFortressCollection.reportMinions());
        Assert.assertEquals(100, minions.get(0).getHealth());
    }

}
