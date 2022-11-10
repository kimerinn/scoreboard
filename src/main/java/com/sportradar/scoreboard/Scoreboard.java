package com.sportradar.scoreboard;

import java.util.HashMap;
import java.util.Map;

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
}
