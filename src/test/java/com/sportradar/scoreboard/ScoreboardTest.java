package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for Scoreboard
 */
public class ScoreboardTest
{
    @Test
    public void testStartGame() {
        Scoreboard scoreboard = new Scoreboard();
        String gameId = scoreboard.startNewGame("Argentina", "Jamaica");
        List<Game> summary = scoreboard.getSummary();
        assertNotNull(gameId);
        assertEquals(1, summary.size());
        assertEquals(gameId, summary.get(0).getId());
    }

    @Test
    public void testUpdateScore() {
        Scoreboard scoreboard = new Scoreboard();
        String gameId = scoreboard.startNewGame("Argentina", "Jamaica");
        scoreboard.updateScore(gameId, 5, 1);
        List<Game> summary = scoreboard.getSummary();
        Game updatedGame = scoreboard.getSummary().get(0);
        assertEquals(5, updatedGame.getHomeScore());
        assertEquals(1, updatedGame.getAwayScore());
    }

    @Test
    public void testFinishGame() {
        Scoreboard scoreboard = new Scoreboard();
        String id = scoreboard.startNewGame("Argentina", "Jamaica");
        scoreboard.finishGame(id);
        List<Game> summary = scoreboard.getSummary();
        assertEquals(0, summary.size());
    }

    @Test
    public void testFinishNotExistingGame() {
        Scoreboard scoreboard = new Scoreboard();
        String id = scoreboard.startNewGame("Argentina", "Jamaica");
        scoreboard.finishGame(id + "_fake");
        List<Game> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals(id, summary.get(0).getId());
    }

    @Test
    public void testFinishNullGame() {
        Scoreboard scoreboard = new Scoreboard();
        String id = scoreboard.startNewGame("Argentina", "Jamaica");
        scoreboard.finishGame(null);
        List<Game> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
    }

    @Test
    public void testGetSummary() {
        Scoreboard scoreboard = new Scoreboard();
        String argentinaJamaicaId = scoreboard.startNewGame("Argentina", "Jamaica");
        String yemenBrazilId = scoreboard.startNewGame("Yemen", "Brazil");
        String usaCanadaId = scoreboard.startNewGame("USA", "Canada");
        String monacoChinaId = scoreboard.startNewGame("Monaco", "China");
        scoreboard.updateScore(argentinaJamaicaId, 5, 1);//Argentina - Jamaica 5:1
        scoreboard.updateScore(yemenBrazilId, 3,3);//Yemen - Brazil 3:3
        scoreboard.updateScore(monacoChinaId, 3, 4);//Monaco - China 3:4
        List<Game> summary = scoreboard.getSummary();
        assertEquals(4, summary.size());
        //Correct totals should be:
        //Monaco - China 3:4 (total score = 7)
        //Yemen - Brazil 3:3 (total score = 6, more recent than Argentina-Jamaica)
        //Argentina - Jamaica 3:3 (total score = 6)
        //USA - Canada 0:0 (total score = 0)
        assertEquals(monacoChinaId, summary.get(0).getId());
        assertEquals(yemenBrazilId, summary.get(1).getId());
        assertEquals(argentinaJamaicaId, summary.get(2).getId());
        assertEquals(usaCanadaId, summary.get(3).getId());
    }

    @Test
    public void testGetSummaryWithStop() {
        Scoreboard scoreboard = new Scoreboard();
        String argentinaJamaicaId = scoreboard.startNewGame("Argentina", "Jamaica");
        String yemenBrazilId = scoreboard.startNewGame("Yemen", "Brazil");
        String usaCanadaId = scoreboard.startNewGame("USA", "Canada");
        String monacoChinaId = scoreboard.startNewGame("Monaco", "China");
        scoreboard.updateScore(argentinaJamaicaId, 5, 1);//Argentina - Jamaica 5:1
        scoreboard.updateScore(yemenBrazilId, 3,3);//Yemen - Brazil 3:3
        scoreboard.updateScore(monacoChinaId, 3, 4);//Monaco - China 3:4

        scoreboard.finishGame(yemenBrazilId);
        List<Game> summary = scoreboard.getSummary();
        assertEquals(3, summary.size());
        //Correct totals should be:
        //Monaco - China 3:4 (total score = 7)
        //Argentina - Jamaica 3:3 (total score = 6)
        //USA - Canada 0:0 (total score = 0)
        assertEquals(monacoChinaId, summary.get(0).getId());
        assertEquals(argentinaJamaicaId, summary.get(1).getId());
        assertEquals(usaCanadaId, summary.get(2).getId());
    }

    @Test
    public void testMutation() {
        Scoreboard scoreboard = new Scoreboard();
        String argentinaJamaicaId = scoreboard.startNewGame("Argentina", "Jamaica");
        String yemenBrazilId = scoreboard.startNewGame("Yemen", "Brazil");
        String usaCanadaId = scoreboard.startNewGame("USA", "Canada");
        String monacoChinaId = scoreboard.startNewGame("Monaco", "China");
        scoreboard.updateScore(argentinaJamaicaId, 5, 1);//Argentina - Jamaica 5:1
        scoreboard.updateScore(yemenBrazilId, 3,3);//Yemen - Brazil 3:3
        scoreboard.updateScore(monacoChinaId, 3, 4);//Monaco - China 3:4

        List<Game> summaryHacked = scoreboard.getSummary();
        Game usaCanada = summaryHacked.get(3);
        assertEquals(usaCanadaId, usaCanada.getId());
        usaCanada.updateScore(100, 100);//scoreboard should not be hacked. USA-Canada still should not be at the end of the summary

        List<Game> summary = scoreboard.getSummary();
        assertEquals(4, summary.size());
        //Correct totals should be:
        //Monaco - China 3:4 (total score = 7)
        //Yemen - Brazil 3:3 (total score = 6, more recent than Argentina-Jamaica)
        //Argentina - Jamaica 3:3 (total score = 6)
        //USA - Canada 0:0 (total score = 0)
        assertEquals(monacoChinaId, summary.get(0).getId());
        assertEquals(yemenBrazilId, summary.get(1).getId());
        assertEquals(argentinaJamaicaId, summary.get(2).getId());
        assertEquals(usaCanadaId, summary.get(3).getId());
    }
}
