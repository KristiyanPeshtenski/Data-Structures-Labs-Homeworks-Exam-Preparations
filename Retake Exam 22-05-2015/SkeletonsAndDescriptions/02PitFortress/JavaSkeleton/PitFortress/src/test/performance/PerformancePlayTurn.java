package test.performance;


import main.models.Mine;
import main.models.Minion;
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

public class PerformancePlayTurn extends BaseTestClass {
    @Category(Performance.class)
    @Test
    public void PerformancePlayTurn_WithRandomAmounts1() throws FileNotFoundException, IOException {
        File file = new File("Tests/PlayTurn/turn.0.txt");
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
            }
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 30; i++) {
            this.PitFortressCollection.playTurn();
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 650);

        Iterable<Player> topPlayers = this.PitFortressCollection.top3PlayersByScore();
        Iterable<Player> botPlayers = this.PitFortressCollection.min3PlayersByScore();
        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();
        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        File result = new File("Results/PlayTurn/turn.0.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : topPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Player player : botPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }

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
    public void PerformancePlayTurn_WithRandomAmounts2() throws FileNotFoundException, IOException {
        File file = new File("Tests/PlayTurn/turn.1.txt");
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
            }
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 40; i++) {
            this.PitFortressCollection.playTurn();
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 650);

        Iterable<Player> topPlayers = this.PitFortressCollection.top3PlayersByScore();
        Iterable<Player> botPlayers = this.PitFortressCollection.min3PlayersByScore();
        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();
        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        File result = new File("Results/PlayTurn/turn.1.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : topPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Player player : botPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }

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
    public void PerformancePlayTurn_WithRandomAmounts3() throws FileNotFoundException, IOException {
        File file = new File("Tests/PlayTurn/turn.2.txt");
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
            }
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            this.PitFortressCollection.playTurn();
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 650);

        Iterable<Player> topPlayers = this.PitFortressCollection.top3PlayersByScore();
        Iterable<Player> botPlayers = this.PitFortressCollection.min3PlayersByScore();
        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();
        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        File result = new File("Results/PlayTurn/turn.2.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : topPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Player player : botPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }

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
    public void PerformancePlayTurn_WithRandomAmounts4() throws FileNotFoundException, IOException {
        File file = new File("Tests/PlayTurn/turn.3.txt");
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
            }
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 60; i++) {
            this.PitFortressCollection.playTurn();
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 700);

        Iterable<Player> topPlayers = this.PitFortressCollection.top3PlayersByScore();
        Iterable<Player> botPlayers = this.PitFortressCollection.min3PlayersByScore();
        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();
        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        File result = new File("Results/PlayTurn/turn.3.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : topPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Player player : botPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }

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
    public void PerformancePlayTurn_WithRandomAmounts5() throws FileNotFoundException, IOException {
        File file = new File("Tests/PlayTurn/turn.4.txt");
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
            }
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 80; i++) {
            this.PitFortressCollection.playTurn();
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 700);

        Iterable<Player> topPlayers = this.PitFortressCollection.top3PlayersByScore();
        Iterable<Player> botPlayers = this.PitFortressCollection.min3PlayersByScore();
        Iterable<Minion> minions = this.PitFortressCollection.reportMinions();
        Iterable<Mine> mines = this.PitFortressCollection.getMines();

        File result = new File("Results/PlayTurn/turn.4.result.txt");
        Scanner resultReader = new Scanner(result);

        for (Player player : topPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Player player : botPlayers) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Player Name did not match!",line[0],player.getName());
            Assert.assertEquals("Player Radius did not match!",Integer.parseInt(line[1]),player.getRadius());
            Assert.assertEquals("Player Score did not match!",Integer.parseInt(line[2]),player.getScore());
        }

        for (Minion minion : minions) {
            String[] line = resultReader.nextLine().split(" ");
            Assert.assertEquals("Minion Coordinates did not match!",Integer.parseInt(line[0]),minion.getX());
            Assert.assertEquals("Minion Id did not match!",Integer.parseInt(line[1]),minion.getId());
            Assert.assertEquals("Minion Health did not match!",Integer.parseInt(line[2]),minion.getHealth());
        }

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
