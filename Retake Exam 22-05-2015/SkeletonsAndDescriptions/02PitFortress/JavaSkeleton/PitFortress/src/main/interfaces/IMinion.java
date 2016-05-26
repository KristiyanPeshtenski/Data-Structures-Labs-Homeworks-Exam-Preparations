package main.interfaces;

import main.models.Minion;

public interface IMinion extends Comparable<Minion> {

    int getId();

    int getX();

    int getHealth();
}
