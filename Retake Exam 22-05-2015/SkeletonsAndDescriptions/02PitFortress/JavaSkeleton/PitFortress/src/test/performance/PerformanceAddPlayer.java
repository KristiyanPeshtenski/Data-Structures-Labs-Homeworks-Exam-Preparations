package test.performance;


import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import test.categories.Performance;
import test.BaseTestClass;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PerformanceAddPlayer extends BaseTestClass {

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts1() throws FileNotFoundException {
        File file = new File("Tests/AddPlayer/addPlayer.0.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts2() throws FileNotFoundException {
        File file = new File("Tests/AddPlayer/addPlayer.1.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts3() throws FileNotFoundException {
        File file = new File("Tests/AddPlayer/addPlayer.2.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts4() throws FileNotFoundException {
        File file = new File("Tests/AddPlayer/addPlayer.3.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts5() throws FileNotFoundException {
        File file = new File("Tests/AddPlayer/addPlayer.4.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }
}

