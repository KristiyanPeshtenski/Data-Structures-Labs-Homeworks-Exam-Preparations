package test.performance;

import main.models.Minion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Performance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PerformanceAddMinion extends BaseTestClass {
    @Category(Performance.class)
    @Test
    public void PerformanceAddMinion_WithRandomAmounts1() throws FileNotFoundException, IOException {
        File file = new File("Tests/AddMinion/addMinion.0.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(Integer.parseInt(scanner.nextLine()));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addMinion(commands.get(i));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        Assert.assertEquals("Minions Count did not match!", commands.size(), this.PitFortressCollection.getMinionCount());

        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();

        File result = new File("Results/AddMinion/addMinion.0.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddMinion_WithRandomAmounts2() throws FileNotFoundException, IOException {
        File file = new File("Tests/AddMinion/addMinion.1.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(Integer.parseInt(scanner.nextLine()));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addMinion(commands.get(i));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        Assert.assertEquals("Minions Count did not match!", commands.size(), this.PitFortressCollection.getMinionCount());

        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();

        File result = new File("Results/AddMinion/addMinion.1.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddMinion_WithRandomAmounts3() throws FileNotFoundException, IOException {
        File file = new File("Tests/AddMinion/addMinion.2.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(Integer.parseInt(scanner.nextLine()));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addMinion(commands.get(i));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        Assert.assertEquals("Minions Count did not match!", commands.size(), this.PitFortressCollection.getMinionCount());

        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();

        File result = new File("Results/AddMinion/addMinion.2.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddMinion_WithRandomAmounts4() throws FileNotFoundException, IOException {
        File file = new File("Tests/AddMinion/addMinion.3.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(Integer.parseInt(scanner.nextLine()));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addMinion(commands.get(i));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        Assert.assertEquals("Minions Count did not match!", commands.size(), this.PitFortressCollection.getMinionCount());

        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();

        File result = new File("Results/AddMinion/addMinion.3.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddMinion_WithRandomAmounts5() throws FileNotFoundException, IOException {
        File file = new File("Tests/AddMinion/addMinion.4.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(Integer.parseInt(scanner.nextLine()));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addMinion(commands.get(i));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        Assert.assertEquals("Minions Count did not match!", commands.size(), this.PitFortressCollection.getMinionCount());

        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();

        File result = new File("Results/AddMinion/addMinion.4.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }
    }

}
