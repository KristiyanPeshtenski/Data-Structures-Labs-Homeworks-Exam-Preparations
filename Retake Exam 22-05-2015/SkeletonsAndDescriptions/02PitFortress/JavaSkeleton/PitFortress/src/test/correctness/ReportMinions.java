package test.correctness;

import junit.framework.Assert;
import main.models.Minion;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class ReportMinions extends BaseTestClass {
    @Category(Correctness.class)
            @Test
    public void CorrectnessReportMinions_WithoutMinions_ShouldReturnEmptyCollection()
    {
        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(0,minions.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessReportMinions_WithMultipleMinions_ShouldReturnCorrectAmountOfMinions()
    {
        this.PitFortressCollection.addMinion(1);
        this.PitFortressCollection.addMinion(2);
        this.PitFortressCollection.addMinion(3);
        this.PitFortressCollection.addMinion(4);
        this.PitFortressCollection.addMinion(5);
        this.PitFortressCollection.addMinion(6);
        this.PitFortressCollection.addMinion(7);
        this.PitFortressCollection.addMinion(8);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(8, minions.size());
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessReportMinions_WithMultipleValidMinions_ShouldReturnCorrectMinions()
    {
        this.PitFortressCollection.addMinion(5);
        this.PitFortressCollection.addMinion(13);
        this.PitFortressCollection.addMinion(27);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(minions.get(0).getX(), 5);
        Assert.assertEquals(minions.get(0).getHealth(), 100);
        Assert.assertEquals(minions.get(0).getId(), 1);

        Assert.assertEquals(minions.get(1).getX(), 13);
        Assert.assertEquals(minions.get(1).getHealth(), 100);
        Assert.assertEquals(minions.get(1).getId(), 2);

        Assert.assertEquals(minions.get(2).getX(), 27);
        Assert.assertEquals(minions.get(2).getHealth(), 100);
        Assert.assertEquals(minions.get(2).getId(), 3);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessReportMinions_WithMinionsWithDuplicateCoordinates_ShouldReturnCorrectlySortedMinions()
    {
        this.PitFortressCollection.addMinion(13);
        this.PitFortressCollection.addMinion(27);
        this.PitFortressCollection.addMinion(5);
        this.PitFortressCollection.addMinion(5066);
        this.PitFortressCollection.addMinion(5066);
        this.PitFortressCollection.addMinion(134013);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());
        Assert.assertEquals(minions.get(0).getX(), 5);
        Assert.assertEquals(minions.get(0).getHealth(), 100);
        Assert.assertEquals(minions.get(0).getId(), 3);

        Assert.assertEquals(minions.get(1).getX(), 13);
        Assert.assertEquals(minions.get(1).getHealth(), 100);
        Assert.assertEquals(minions.get(1).getId(), 1);

        Assert.assertEquals(minions.get(2).getX(), 27);
        Assert.assertEquals(minions.get(2).getHealth(), 100);
        Assert.assertEquals(minions.get(2).getId(), 2);

        Assert.assertEquals(minions.get(3).getX(), 5066);
        Assert.assertEquals(minions.get(3).getHealth(), 100);
        Assert.assertEquals(minions.get(3).getId(), 4);

        Assert.assertEquals(minions.get(4).getX(), 5066);
        Assert.assertEquals(minions.get(4).getHealth(), 100);
        Assert.assertEquals(minions.get(4).getId(), 5);

        Assert.assertEquals(minions.get(5).getX(), 134013);
        Assert.assertEquals(minions.get(5).getHealth(), 100);
        Assert.assertEquals(minions.get(5).getId(), 6);
    }

    @Category(Correctness.class)
            @Test
    public void CorrectnessReportMinions_WithMultipleValidMinions_ShouldReturnCorrectlySortedMinions()
    {
        this.PitFortressCollection.addMinion(20);
        this.PitFortressCollection.addMinion(30);
        this.PitFortressCollection.addMinion(10);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(minions.get(0).getX(), 10);
        Assert.assertEquals(minions.get(0).getHealth(), 100);
        Assert.assertEquals(minions.get(0).getId(), 3);

        Assert.assertEquals(minions.get(1).getX(), 20);
        Assert.assertEquals(minions.get(1).getHealth(), 100);
        Assert.assertEquals(minions.get(1).getId(), 1);

        Assert.assertEquals(minions.get(2).getX(), 30);
        Assert.assertEquals(minions.get(2).getHealth(), 100);
        Assert.assertEquals(minions.get(2).getId(), 2);
    }

}
