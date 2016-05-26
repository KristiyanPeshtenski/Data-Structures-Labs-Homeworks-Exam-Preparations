package main.models;

import main.interfaces.IMine;

public class Mine implements IMine {

    private int id;
    private int delay;
    private int x;
    private Player player;
    private int damage;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    public void setDelay(int value) {
        this.delay = value;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(Mine o) {
        return 0;
    }
}
