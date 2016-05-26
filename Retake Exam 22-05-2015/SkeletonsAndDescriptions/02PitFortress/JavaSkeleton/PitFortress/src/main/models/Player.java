package main.models;

import main.interfaces.IPlayer;

public class Player implements IPlayer {

    private int radius;
    private String name;
    private int score;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    @Override
    public int compareTo(Player o) {
       return 0;
    }
}
