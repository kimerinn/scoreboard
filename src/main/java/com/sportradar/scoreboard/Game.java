package com.sportradar.scoreboard;

import java.util.UUID;

/**
 *
 */
public class Game implements Cloneable{
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private String id;
    private int order;

    public Game(String homeTeam, String awayTeam, int order) {
        this.homeTeam = homeTeam;
        this.order = order;
        this.awayTeam = awayTeam;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getOrder() {
        return order;
    }

    public Game updateScore(int homeScore, int awayScore) {
        try {
            Game updated = (Game) clone();
            updated.homeScore = homeScore;
            updated.awayScore = awayScore;
            return updated;
        } catch (CloneNotSupportedException e) {
            //newer should happen
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return homeTeam + " - " + awayTeam + " " + homeScore + ":" + awayScore + " _ " + order;
    }
}
