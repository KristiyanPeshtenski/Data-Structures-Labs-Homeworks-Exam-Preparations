package test.performance;


import main.models.Mine;
import main.models.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.BaseTestClass;
import test.categories.Performance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PerformanceSetMine extends BaseTestClass{
    @Category(Performance.class)
    @Test
    public void PerformanceSetMine_WithRandomAmounts1() throws FileNotFoundException, IOException {
        File file = new File("Tests/SetMine/mine.0.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)[0])
            {
                case "player":
                    this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 80);

        Assert.assertEquals("Mines Count did not match!", commands.size() - 8, this.PitFortressCollection.getMineCount());

        File result = new File("Results/SetMine/mine.0.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Mine mine : mines) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Mine Delay did not match!",Integer.parseInt(line[0]),mine.getDelay());
            Assert.assertEquals("Mine Id did not match!",Integer.parseInt(line[1]),mine.getId());
            Assert.assertEquals("Mine Coordinates did not match!",Integer.parseInt(line[2]),mine.getX());
            Assert.assertEquals("Mine Damage did not match!", Integer.parseInt(line[3]), mine.getDamage());
            Assert.assertEquals("Mine Player did not match!", line[4], mine.getPlayer().getName());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceSetMine_WithRandomAmounts2() throws FileNotFoundException, IOException {
        File file = new File("Tests/SetMine/mine.1.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)[0])
            {
                case "player":
                    this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 80);

        Assert.assertEquals("Mines Count did not match!", commands.size() - 8, this.PitFortressCollection.getMineCount());

        File result = new File("Results/SetMine/mine.1.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Mine mine : mines) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Mine Delay did not match!",Integer.parseInt(line[0]),mine.getDelay());
            Assert.assertEquals("Mine Id did not match!",Integer.parseInt(line[1]),mine.getId());
            Assert.assertEquals("Mine Coordinates did not match!",Integer.parseInt(line[2]),mine.getX());
            Assert.assertEquals("Mine Damage did not match!", Integer.parseInt(line[3]), mine.getDamage());
            Assert.assertEquals("Mine Player did not match!", line[4], mine.getPlayer().getName());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceSetMine_WithRandomAmounts3() throws FileNotFoundException, IOException {
        File file = new File("Tests/SetMine/mine.2.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)[0])
            {
                case "player":
                    this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 80);

        Assert.assertEquals("Mines Count did not match!", commands.size() - 8, this.PitFortressCollection.getMineCount());

        File result = new File("Results/SetMine/mine.2.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Mine mine : mines) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Mine Delay did not match!",Integer.parseInt(line[0]),mine.getDelay());
            Assert.assertEquals("Mine Id did not match!",Integer.parseInt(line[1]),mine.getId());
            Assert.assertEquals("Mine Coordinates did not match!",Integer.parseInt(line[2]),mine.getX());
            Assert.assertEquals("Mine Damage did not match!", Integer.parseInt(line[3]), mine.getDamage());
            Assert.assertEquals("Mine Player did not match!", line[4], mine.getPlayer().getName());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceSetMine_WithRandomAmounts4() throws FileNotFoundException, IOException {
        File file = new File("Tests/SetMine/mine.3.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)[0])
            {
                case "player":
                    this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 80);

        Assert.assertEquals("Mines Count did not match!", commands.size() - 8, this.PitFortressCollection.getMineCount());

        File result = new File("Results/SetMine/mine.3.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Mine mine : mines) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Mine Delay did not match!",Integer.parseInt(line[0]),mine.getDelay());
            Assert.assertEquals("Mine Id did not match!",Integer.parseInt(line[1]),mine.getId());
            Assert.assertEquals("Mine Coordinates did not match!",Integer.parseInt(line[2]),mine.getX());
            Assert.assertEquals("Mine Damage did not match!", Integer.parseInt(line[3]), mine.getDamage());
            Assert.assertEquals("Mine Player did not match!", line[4], mine.getPlayer().getName());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceSetMine_WithRandomAmounts5() throws FileNotFoundException, IOException {
        File file = new File("Tests/SetMine/mine.4.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)[0])
            {
                case "player":
                    this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 80);

        Assert.assertEquals("Mines Count did not match!", commands.size() - 8, this.PitFortressCollection.getMineCount());

        File result = new File("Results/SetMine/mine.4.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Mine mine : mines) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Mine Delay did not match!",Integer.parseInt(line[0]),mine.getDelay());
            Assert.assertEquals("Mine Id did not match!",Integer.parseInt(line[1]),mine.getId());
            Assert.assertEquals("Mine Coordinates did not match!",Integer.parseInt(line[2]),mine.getX());
            Assert.assertEquals("Mine Damage did not match!", Integer.parseInt(line[3]), mine.getDamage());
            Assert.assertEquals("Mine Player did not match!", line[4], mine.getPlayer().getName());
        }
    }
}
