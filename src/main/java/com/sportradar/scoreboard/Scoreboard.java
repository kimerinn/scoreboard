package com.sportradar.scoreboard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Simplest scoreboard implementation
 *
 */

public class Scoreboard
{
    private Map<String, Game> games;
    private int counter;

    public Scoreboard() {
        games = new HashMap<>();
    }

    public String startNewGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam, counter++);
        games.put(game.getId(), game);
        return game.getId();
    }

    public List<Game> getSummary() {
        List<Game> result = games.values().stream().sorted(new GameComparator()).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    public void updateScore(String id, int homeScore, int awayScore) {
        Game gameToUpdate = games.get(id);

        if (gameToUpdate == null) {
            return;
        }

        Game updatedGame = gameToUpdate.updateScore(homeScore, awayScore);
        games.put(id, updatedGame);
    }

    public void finishGame(String id) {
        games.remove(id);
    }

    public static class GameComparator implements Comparator<Game> {

        @Override
        public int compare(Game o1, Game o2) {
            int compareResult = Integer.compare(o1.getHomeScore() + o1.getAwayScore(), o2.getHomeScore() + o2.getAwayScore());
            return compareResult == 0 ? Integer.compare(o1.getOrder(), o2.getOrder()) : compareResult;
        }
    }
}
