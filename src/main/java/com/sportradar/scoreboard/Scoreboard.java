package com.sportradar.scoreboard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Simplest scoreboard implementation
 *
 */

public class Scoreboard
{
    private Map<String, Game> games;

    public Scoreboard() {
        games = new HashMap<>();
    }

    public String startNewGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        games.put(game.getId(), game);
        return game.getId();
    }

    public List<Game> getSummary() {
        return games.values().stream().sorted(new GameComparator()).collect(Collectors.toList());
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
            if (o1.getTotalScore() > o2.getTotalScore()) {
                return 1;
            }
            else if (o1.getTotalScore() < o2.getTotalScore()) {
                return -1;
            }
            else {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        }
    }
}
