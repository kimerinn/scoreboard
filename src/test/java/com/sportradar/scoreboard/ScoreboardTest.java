package com.sportradar.scoreboard;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit tests for Scoreboard
 */
public class ScoreboardTest extends TestCase
{
    private Scoreboard scoreboard;

    @BeforeEach
    public void before() {
        scoreboard = new Scoreboard();
    }

    @Test
    public void testStartGame() {
        String gameId = scoreboard.startNewGame("Argentina", "Yamaika");
        List<Game> summary = scoreboard.getSummary();
        assertNotNull(gameId);
        assertEquals(1, summary.size());
        assertEquals(gameId, summary.get(0).getId());
    }

    @Test
    public void testUpdateScore() {
        String gameId = scoreboard.startNewGame("Argentina", "Yamaika");
        scoreboard.updateScore(gameId, 5, 1);
        List<Game> summary = scoreboard.getSummary();
        Game updatedGame = scoreboard.getSummary().get(0);
        assertEquals(5, updatedGame.getHomeScore());
        assertEquals(0, updatedGame.getAwayScore());
        assertEquals(6, updatedGame.getTotalScore());
    }

    @Test
    public void testFinishGame() {
        String id = scoreboard.startNewGame("Argentina", "Yamaika");
        scoreboard.finishGame(id);
        List<Game> summary = scoreboard.getSummary();
        assertEquals(0, summary.size());
    }

    @Test
    public void testFinishNotExistingGame() {
        String id = scoreboard.startNewGame("Argentina", "Yamaika");
        scoreboard.finishGame(id + "_fake");
        List<Game> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals(id, summary.get(0).getId());
    }

    @Test
    public void testFinishNullGame() {
        String id = scoreboard.startNewGame("Argentina", "Yamaika");
        scoreboard.finishGame(null);
        List<Game> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
    }

    @Test
    public void testGetSummary() {
    }

    @Test
    public void testMutation() {
    }
}
