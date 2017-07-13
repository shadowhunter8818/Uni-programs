package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {
	@Test (expected = CompetitionException.class)
	public void GetLeague_negLeagueNum_Exception() throws CompetitionException {
		SoccerCompetition aSoccerCompetition = new SoccerCompetition("Comp", 2, 2);
		SoccerLeague actual;
		SoccerLeague league2 = null;
		SoccerLeague Expected = league2; 
		actual = aSoccerCompetition.getLeague(-1);
		assertEquals(Expected, actual);
	}
	
	@Test (expected = CompetitionException.class)
	public void GetLeague_indexOutOfRange_exception() throws CompetitionException {
		SoccerCompetition aSoccerCompetition = new SoccerCompetition("Comp", 2, 2);
		SoccerLeague actual;
		SoccerLeague league2 = null;
		SoccerLeague Expected = league2; 
		actual = aSoccerCompetition.getLeague(10);
		assertEquals(Expected, actual);
	}
}

