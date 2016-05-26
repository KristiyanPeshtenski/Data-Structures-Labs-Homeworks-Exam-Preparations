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

public class PerformanceMin3Players extends BaseTestClass{
    @Category(Performance.class)
    @Test
    public void PerformanceMin3Players_WithRandomAmounts1() throws FileNotFoundException, IOException {
        File file = new File("Tests/Min3Players/min3.0.txt");
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
                case "minion":
                    this.PitFortressCollection.addMinion(Integer.parseInt(commands.get(i)[1]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
                case "skip":
                    this.PitFortressCollection.playTurn();
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Player> players = this.PitFortressCollection.min3PlayersByScore();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 10);

        File result = new File("Results/Min3Players/min3.0.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : players) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceMin3Players_WithRandomAmounts2() throws FileNotFoundException, IOException {
        File file = new File("Tests/Min3Players/min3.1.txt");
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
                case "minion":
                    this.PitFortressCollection.addMinion(Integer.parseInt(commands.get(i)[1]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
                case "skip":
                    this.PitFortressCollection.playTurn();
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Player> players = this.PitFortressCollection.min3PlayersByScore();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 10);

        File result = new File("Results/Min3Players/min3.1.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : players) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceMin3Players_WithRandomAmounts3() throws FileNotFoundException, IOException {
        File file = new File("Tests/Min3Players/min3.2.txt");
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
                case "minion":
                    this.PitFortressCollection.addMinion(Integer.parseInt(commands.get(i)[1]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
                case "skip":
                    this.PitFortressCollection.playTurn();
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Player> players = this.PitFortressCollection.min3PlayersByScore();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 10);

        File result = new File("Results/Min3Players/min3.2.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : players) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceMin3Players_WithRandomAmounts4() throws FileNotFoundException, IOException {
        File file = new File("Tests/Min3Players/min3.3.txt");
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
                case "minion":
                    this.PitFortressCollection.addMinion(Integer.parseInt(commands.get(i)[1]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
                case "skip":
                    this.PitFortressCollection.playTurn();
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Player> players = this.PitFortressCollection.min3PlayersByScore();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 10);

        File result = new File("Results/Min3Players/min3.3.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : players) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }
    }

    @Category(Performance.class)
    @Test
    public void PerformanceMin3Players_WithRandomAmounts5() throws FileNotFoundException, IOException {
        File file = new File("Tests/Min3Players/min3.4.txt");
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
                case "minion":
                    this.PitFortressCollection.addMinion(Integer.parseInt(commands.get(i)[1]));
                    break;
                case "mine":
                    this.PitFortressCollection.setMine(
                            commands.get(i)[1],
                            Integer.parseInt(commands.get(i)[2]),
                            Integer.parseInt(commands.get(i)[3]),
                            Integer.parseInt(commands.get(i)[4]));
                    break;
                case "skip":
                    this.PitFortressCollection.playTurn();
                    break;
            }
        }

        long start = System.currentTimeMillis();

        Iterable<Player> players = this.PitFortressCollection.min3PlayersByScore();

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 10);

        File result = new File("Results/Min3Players/min3.4.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : players) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }
    }
}
