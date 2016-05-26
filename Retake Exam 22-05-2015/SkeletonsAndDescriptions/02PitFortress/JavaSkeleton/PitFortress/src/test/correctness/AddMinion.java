package test.correctness;


import junit.framework.Assert;
import main.models.Minion;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Correctness;

import java.util.List;

public class AddMinion extends BaseTestClass {
    @Category(Correctness.class)
    @Test(expected = IllegalArgumentException.class)
    public void CorrectnessAddMinion_WithNegativeCoordinate_ShouldThrowCorrectException() {
        this.PitFortressCollection.addMinion(-1);
    }

    @Category(Correctness.class)

    @Test(expected = IllegalArgumentException.class)
    public void CorrectnessAddMinion_WithIncorrectCoordinate_ShouldThrowCorrectException() {
        this.PitFortressCollection.addMinion(1000001);
    }

    @Category(Correctness.class)
    @Test
    public void CorrectnessAddMinion_WithValidCoordinate_ShouldIncreaseMinionCounter() {
        this.PitFortressCollection.addMinion(13);

        Assert.assertEquals(1, this.PitFortressCollection.getMinionCount());
    }

    @Category(Correctness.class)
    @Test
    public void CorrectnessAddMinion_WithExistingMinions_ShouldIncreaseMinionCounterCorrectly() {
        this.PitFortressCollection.addMinion(1000);
        this.PitFortressCollection.addMinion(999);
        this.PitFortressCollection.addMinion(998);
        this.PitFortressCollection.addMinion(997);

        Assert.assertEquals(4, this.PitFortressCollection.getMinionCount());

        this.PitFortressCollection.addMinion(10);

        Assert.assertEquals(5, this.PitFortressCollection.getMinionCount());
    }

    @Category(Correctness.class)
    @Test
    public void CorrectnessAddMinion_WithMinionsWithSameCoordinate_ShouldIncreaseMinionCounterCorrectly() {
        this.PitFortressCollection.addMinion(13);
        this.PitFortressCollection.addMinion(27);
        this.PitFortressCollection.addMinion(5);
        this.PitFortressCollection.addMinion(13);

        Assert.assertEquals(4, this.PitFortressCollection.getMinionCount());
    }

    @Category(Correctness.class)
    @Test
    public void CorrectnessAddMinion_WithMultipleValidMinions_ShouldAddCorrectMinions() {
        this.PitFortressCollection.addMinion(13);
        this.PitFortressCollection.addMinion(27);
        this.PitFortressCollection.addMinion(5);

        List<Minion> minions = toList(this.PitFortressCollection.reportMinions());

        Assert.assertEquals(minions.get(0).getX(), 5);
        Assert.assertEquals(minions.get(0).getHealth(), 100);

        Assert.assertEquals(minions.get(1).getX(), 13);
        Assert.assertEquals(minions.get(1).getHealth(), 100);

        Assert.assertEquals(minions.get(2).getX(), 27);
        Assert.assertEquals(minions.get(2).getHealth(), 100);
    }
}
