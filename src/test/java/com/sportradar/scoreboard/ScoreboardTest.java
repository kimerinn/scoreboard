package com.sportradar.scoreboard;

import junit.framework.TestCase;

/**
 * Unit tests for Scoreboard
 */
public class ScoreboardTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ScoreboardTest(String testName )
    {
        super( testName );
    }

    public void testStartGame() {
        Scoreboard unit = new Scoreboard();
        String gameId = unit.startNewGame("Argentina", "Yamaika");
        assertNotNull(gameId);
    }

    public void testUpdateScore() {
    }

    public void testFinishGame() {
    }

    public void testGetSummary() {
    }
}
