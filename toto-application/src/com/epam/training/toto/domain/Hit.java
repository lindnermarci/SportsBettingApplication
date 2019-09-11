package com.epam.training.toto.domain;

/*
 * A class to represent hit count, number of wagers and price of a specified hit number in a specified round.
 */

public class Hit {
    private final int hitCount;
    private final int numOfWagers;
    private final int prize;

    public Hit(int hitCount, int numOfWagers, int prize) {
        this.hitCount = hitCount;
        this.numOfWagers = numOfWagers;
        this.prize = prize;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getNumberOfWagers() {
        return numOfWagers;
    }

    public int getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        return "Hit [hitCount=" + hitCount + ", numOfWagers=" + numOfWagers + ", prize=" + prize + "]";
    }

}
