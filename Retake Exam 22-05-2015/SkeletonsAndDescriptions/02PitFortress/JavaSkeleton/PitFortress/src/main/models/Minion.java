package main.models;

import main.interfaces.IMinion;

public class Minion implements IMinion {

    private int id;
    private int x;
    private int health;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int value) {
        this.health = value;
    }

    @Override
    public int compareTo(Minion o) {
        return 0;
    }
}
