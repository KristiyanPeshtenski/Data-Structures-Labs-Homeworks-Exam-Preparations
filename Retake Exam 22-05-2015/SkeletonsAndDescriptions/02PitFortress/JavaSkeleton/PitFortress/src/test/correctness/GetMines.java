package test.correctness;

import junit.framework.Assert;
import main.models.Mine;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class GetMines extends BaseTestClass {
    @Category(Correctness.class)
            @Test
    public void CorrectnessGetMines_WithoutMines_ShouldReturnEmptyCollection()
    {
        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(0, mines.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessGetMines_WithMultipleMines_ShouldReturnCorrectMountOfMines()
    {
        this.PitFortressCollection.addPlayer("Gencho", 13);
        this.PitFortressCollection.setMine("Gencho", 1, 1, 1);
        this.PitFortressCollection.setMine("Gencho", 2, 2, 2);
        this.PitFortressCollection.setMine("Gencho", 3, 3, 3);
        this.PitFortressCollection.setMine("Gencho", 4, 4, 4);
        this.PitFortressCollection.setMine("Gencho", 5, 5, 5);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(5, mines.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessGetMines_WithASingleMine_ShouldReturnCorrectMine()
    {
        this.PitFortressCollection.addPlayer("Gencho", 13);
        this.PitFortressCollection.setMine("Gencho", 20, 20, 20);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(mines.get(0).getId(), 1);
        Assert.assertEquals(mines.get(0).getX(), 20);
        Assert.assertEquals(mines.get(0).getDelay(), 20);
        Assert.assertEquals(mines.get(0).getDamage(), 20);
        Assert.assertEquals(mines.get(0).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(0).getPlayer().getRadius(), 13);
        Assert.assertEquals(mines.get(0).getPlayer().getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessGetMines_WithMinesWithDuplicateDelays_ShouldReturnCorrectlySortedMines()
    {
        this.PitFortressCollection.addPlayer("Poncho", 77);
        this.PitFortressCollection.setMine("Poncho", 77, 77, 88);
        this.PitFortressCollection.setMine("Poncho", 77, 77, 77);
        this.PitFortressCollection.setMine("Poncho", 77, 77, 77);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(mines.get(0).getId(), 1);
        Assert.assertEquals(mines.get(0).getX(), 77);
        Assert.assertEquals(mines.get(0).getDelay(), 77);
        Assert.assertEquals(mines.get(0).getDamage(), 88);
        Assert.assertEquals(mines.get(0).getPlayer().getName(), "Poncho");
        Assert.assertEquals(mines.get(0).getPlayer().getRadius(), 77);
        Assert.assertEquals(mines.get(0).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(1).getId(), 2);
        Assert.assertEquals(mines.get(1).getX(), 77);
        Assert.assertEquals(mines.get(1).getDelay(), 77);
        Assert.assertEquals(mines.get(1).getDamage(), 77);
        Assert.assertEquals(mines.get(1).getPlayer().getName(), "Poncho");
        Assert.assertEquals(mines.get(1).getPlayer().getRadius(), 77);
        Assert.assertEquals(mines.get(1).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(2).getId(), 3);
        Assert.assertEquals(mines.get(2).getX(), 77);
        Assert.assertEquals(mines.get(2).getDelay(), 77);
        Assert.assertEquals(mines.get(2).getDamage(), 77);
        Assert.assertEquals(mines.get(2).getPlayer().getName(), "Poncho");
        Assert.assertEquals(mines.get(2).getPlayer().getRadius(), 77);
        Assert.assertEquals(mines.get(2).getPlayer().getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessGetMines_WithSinglePlayerWithMultipleMines_ShouldReturnMinesCorrectlySorted()
    {
        this.PitFortressCollection.addPlayer("Gencho", 5);
        this.PitFortressCollection.setMine("Gencho", 20, 20, 20);
        this.PitFortressCollection.setMine("Gencho", 100, 5, 30);
        this.PitFortressCollection.setMine("Gencho", 333, 25, 50);
        this.PitFortressCollection.setMine("Gencho", 201, 15, 50);
        this.PitFortressCollection.setMine("Gencho", 666, 30, 60);
        this.PitFortressCollection.setMine("Gencho", 200, 15, 40);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(mines.get(0).getId(), 2);
        Assert.assertEquals(mines.get(0).getX(), 100);
        Assert.assertEquals(mines.get(0).getDelay(), 5);
        Assert.assertEquals(mines.get(0).getDamage(), 30);
        Assert.assertEquals(mines.get(0).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(0).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(0).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(1).getId(), 4);
        Assert.assertEquals(mines.get(1).getX(), 201);
        Assert.assertEquals(mines.get(1).getDelay(), 15);
        Assert.assertEquals(mines.get(1).getDamage(), 50);
        Assert.assertEquals(mines.get(1).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(1).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(1).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(2).getId(), 6);
        Assert.assertEquals(mines.get(2).getX(), 200);
        Assert.assertEquals(mines.get(2).getDelay(), 15);
        Assert.assertEquals(mines.get(2).getDamage(), 40);
        Assert.assertEquals(mines.get(2).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(2).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(2).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(3).getId(), 1);
        Assert.assertEquals(mines.get(3).getX(), 20);
        Assert.assertEquals(mines.get(3).getDelay(), 20);
        Assert.assertEquals(mines.get(3).getDamage(), 20);
        Assert.assertEquals(mines.get(3).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(3).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(3).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(4).getId(), 3);
        Assert.assertEquals(mines.get(4).getX(), 333);
        Assert.assertEquals(mines.get(4).getDelay(), 25);
        Assert.assertEquals(mines.get(4).getDamage(), 50);
        Assert.assertEquals(mines.get(4).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(4).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(4).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(5).getId(), 5);
        Assert.assertEquals(mines.get(5).getX(), 666);
        Assert.assertEquals(mines.get(5).getDelay(), 30);
        Assert.assertEquals(mines.get(5).getDamage(), 60);
        Assert.assertEquals(mines.get(5).getPlayer().getName(), "Gencho");
        Assert.assertEquals(mines.get(5).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(5).getPlayer().getScore(), 0);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessGetMines_WithMultiplePlayersWithMultipleMines_ShouldReturnMinesCorrectlySorted()
    {
        this.PitFortressCollection.addPlayer("Pesho", 1);
        this.PitFortressCollection.addPlayer("Tosho", 2);
        this.PitFortressCollection.addPlayer("Gesho", 3);
        this.PitFortressCollection.addPlayer("Rosho", 5);

        this.PitFortressCollection.setMine("Gesho", 10, 10, 10);
        this.PitFortressCollection.setMine("Tosho", 20, 9, 20);
        this.PitFortressCollection.setMine("Tosho", 30, 171, 30);
        this.PitFortressCollection.setMine("Pesho", 40, 15, 40);
        this.PitFortressCollection.setMine("Gesho", 50, 66, 50);
        this.PitFortressCollection.setMine("Rosho", 60, 1032, 60);

        List<Mine> mines = toList(this.PitFortressCollection.getMines());

        Assert.assertEquals(mines.get(0).getId(), 2);
        Assert.assertEquals(mines.get(0).getX(), 20);
        Assert.assertEquals(mines.get(0).getDelay(), 9);
        Assert.assertEquals(mines.get(0).getDamage(), 20);
        Assert.assertEquals(mines.get(0).getPlayer().getName(), "Tosho");
        Assert.assertEquals(mines.get(0).getPlayer().getRadius(), 2);
        Assert.assertEquals(mines.get(0).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(1).getId(), 1);
        Assert.assertEquals(mines.get(1).getX(), 10);
        Assert.assertEquals(mines.get(1).getDelay(), 10);
        Assert.assertEquals(mines.get(1).getDamage(), 10);
        Assert.assertEquals(mines.get(1).getPlayer().getName(), "Gesho");
        Assert.assertEquals(mines.get(1).getPlayer().getRadius(), 3);
        Assert.assertEquals(mines.get(1).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(2).getId(), 4);
        Assert.assertEquals(mines.get(2).getX(), 40);
        Assert.assertEquals(mines.get(2).getDelay(), 15);
        Assert.assertEquals(mines.get(2).getDamage(), 40);
        Assert.assertEquals(mines.get(2).getPlayer().getName(), "Pesho");
        Assert.assertEquals(mines.get(2).getPlayer().getRadius(), 1);
        Assert.assertEquals(mines.get(2).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(3).getId(), 5);
        Assert.assertEquals(mines.get(3).getX(), 50);
        Assert.assertEquals(mines.get(3).getDelay(), 66);
        Assert.assertEquals(mines.get(3).getDamage(), 50);
        Assert.assertEquals(mines.get(3).getPlayer().getName(), "Gesho");
        Assert.assertEquals(mines.get(3).getPlayer().getRadius(), 3);
        Assert.assertEquals(mines.get(3).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(4).getId(), 3);
        Assert.assertEquals(mines.get(4).getX(), 30);
        Assert.assertEquals(mines.get(4).getDelay(), 171);
        Assert.assertEquals(mines.get(4).getDamage(), 30);
        Assert.assertEquals(mines.get(4).getPlayer().getName(), "Tosho");
        Assert.assertEquals(mines.get(4).getPlayer().getRadius(), 2);
        Assert.assertEquals(mines.get(4).getPlayer().getScore(), 0);

        Assert.assertEquals(mines.get(5).getId(), 6);
        Assert.assertEquals(mines.get(5).getX(), 60);
        Assert.assertEquals(mines.get(5).getDelay(), 1032);
        Assert.assertEquals(mines.get(5).getDamage(), 60);
        Assert.assertEquals(mines.get(5).getPlayer().getName(), "Rosho");
        Assert.assertEquals(mines.get(5).getPlayer().getRadius(), 5);
        Assert.assertEquals(mines.get(5).getPlayer().getScore(), 0);
    }
}
