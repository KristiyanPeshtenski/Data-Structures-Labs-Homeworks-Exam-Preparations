package main.interfaces;

import main.models.Player;

public interface IPlayer extends Comparable<Player> {

    String getName();

    int getRadius();

    int getScore();
}
