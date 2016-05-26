package test.correctness;

import junit.framework.Assert;
import main.models.Mine;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class SetMine extends BaseTestClass {
    @Category(Correctness.class)
            @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithNonExistingPlayer_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.setMine("Mr.MMS", 0, 1, 50);
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithNegativeCoordinate_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMs", 10);
        this.PitFortressCollection.setMine("Mr.MMS", -20, 1, 50);
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithInvalidCoordinate_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMs", 10);
        this.PitFortressCollection.setMine("Mr.MMS", 2000000, 1, 50);
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithNegativeDelay_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMs", 10);
        this.PitFortressCollection.setMine("Mr.MMS", 0, -1, 50);
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithIncorrectDelay_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMs", 10);
        this.PitFortressCollection.setMine("Mr.MMS", 0, 20000, 50);
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithNegativeDamage_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMs", 10);
        this.PitFortressCollection.setMine("Mr.MMS", 0, 5, -5);
    }

    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnesSetMine_WithInvalidDamage_ShouldThrowCorrectException()
    {
        this.PitFortressCollection.addPlayer("Mr.MMs", 10);
        this.PitFortressCollection.setMine("Mr.MMS", 0, 5, 101);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnesSetMine_WithValidMine_ShouldIncreaseMineCount()
    {
        this.PitFortressCollection.addPlayer("Jichkata", 10);
        this.PitFortressCollection.setMine("Jichkata", 13, 5, 89);

        Assert.assertEquals(this.PitFortressCollection.getMineCount(),1);

    }

    @Category(Correctness.class)
            @Test
    public void CorrectnesSetMine_WithExistingMines_ShouldIncreaseMineCountCorrectly()
    {
        this.PitFortressCollection.addPlayer("Jichkata", 5);
        this.PitFortressCollection.addPlayer("Pesho", 10);

        this.PitFortressCollection.setMine("Pesho", 13, 1, 20);
        this.PitFortressCollection.setMine("Jichkata", 1, 1, 15);
        this.PitFortressCollection.setMine("Pesho", 20, 2, 25);
        this.PitFortressCollection.setMine("Pesho", 25, 3, 40);

        Assert.assertEquals(this.PitFortressCollection.getMineCount(), 4);

        this.PitFortressCollection.setMine("Jichkata", 117, 13, 99);

        Assert.assertEquals(this.PitFortressCollection.getMineCount(), 5);

    }

    @Category(Correctness.class)
            @Test
    public void CorrectnesSetMine_WithValidMine_ShouldAddCorrectMine()
    {
        this.PitFortressCollection.addPlayer("Jichkata", 10);
        this.PitFortressCollection.setMine("Jichkata", 13, 5, 89);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(mines.get(0).getId(), 1);
        Assert.assertEquals(mines.get(0).getX(), 13);
        Assert.assertEquals(mines.get(0).getDelay(), 5);
        Assert.assertEquals(mines.get(0).getDamage(), 89);
        Assert.assertEquals(mines.get(0).getPlayer().getName(), "Jichkata");
        Assert.assertEquals(mines.get(0).getPlayer().getRadius(), 10);
        Assert.assertEquals(mines.get(0).getPlayer().getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnesSetMine_MultipleMinesWithMultiplePlayers_ShouldAddCorrectMines()
    {
        this.PitFortressCollection.addPlayer("Pencho", 1);
        this.PitFortressCollection.addPlayer("Oncho", 2);
        this.PitFortressCollection.addPlayer("Gencho", 3);

        this.PitFortressCollection.setMine("Gencho", 999996, 96, 96);
        this.PitFortressCollection.setMine("Pencho", 999997, 97, 97);
        this.PitFortressCollection.setMine("Gencho", 999998, 98, 98);
        this.PitFortressCollection.setMine("Pencho", 999999, 99, 99);
        this.PitFortressCollection.setMine("Oncho", 1000000, 100, 100);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(mines.get(0).getId(), 1);
        Assert.assertEquals(mines.get(0).getX(), 999996);
        Assert.assertEquals(mines.get(0).getDelay(), 96);
        Assert.assertEquals(mines.get(0).getDamage(), 96);
        Assert.assertEquals(mines.get(0).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(0).getPlayer().getRadius(), 3);
        Assert.assertEquals(mines.get(0).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(1).getId(), 2);
        Assert.assertEquals(mines.get(1).getX(), 999997);
        Assert.assertEquals(mines.get(1).getDelay(), 97);
        Assert.assertEquals(mines.get(1).getDamage(), 97);
        Assert.assertEquals(mines.get(1).getPlayer().getName(), "Pencho");
        Assert.assertEquals(mines.get(1).getPlayer().getRadius(), 1);
        Assert.assertEquals(mines.get(1).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(2).getId(), 3);
        Assert.assertEquals(mines.get(2).getX(), 999998);
        Assert.assertEquals(mines.get(2).getDelay(), 98);
        Assert.assertEquals(mines.get(2).getDamage(), 98);
        Assert.assertEquals(mines.get(2).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(2).getPlayer().getRadius(), 3);
        Assert.assertEquals(mines.get(2).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(3).getId(), 4);
        Assert.assertEquals(mines.get(3).getX(), 999999);
        Assert.assertEquals(mines.get(3).getDelay(), 99);
        Assert.assertEquals(mines.get(3).getDamage(), 99);
        Assert.assertEquals(mines.get(3).getPlayer().getName(), "Pencho");
        Assert.assertEquals(mines.get(3).getPlayer().getRadius(), 1);
        Assert.assertEquals(mines.get(3).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(4).getId(), 5);
        Assert.assertEquals(mines.get(4).getX(), 1000000);
        Assert.assertEquals(mines.get(4).getDelay(), 100);
        Assert.assertEquals(mines.get(4).getDamage(), 100);
        Assert.assertEquals(mines.get(4).getPlayer().getName(), "Oncho");
        Assert.assertEquals(mines.get(4).getPlayer().getRadius(), 2);
        Assert.assertEquals(mines.get(4).getPlayer().getScore(), 0);
    }

}
