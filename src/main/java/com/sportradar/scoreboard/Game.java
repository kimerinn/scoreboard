package com.sportradar.scoreboard;

import java.util.Date;
import java.util.UUID;

/**
 *
 */
public class Game implements Cloneable{
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private Date startTime;

    private String id;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        startTime = new Date();
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
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

    public int getTotalScore() {
        return homeScore + awayScore;
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
}
