package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;



/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerTeamTests {

	@Test
	public void GetOfficialName() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		String actual = aSoccerTeam.getOfficialName();
		String Expected = "Official";
		assertEquals(Expected, actual);
	}
	
	@Test (expected = TeamException.class)
	public void Getofficialname_NoOfficial_exception() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("", "Nick");
		String actual = aSoccerTeam.getOfficialName();
		String Expected = "Exception";
		assertEquals(Expected, actual);
	}
	
	@Test
	public void GetNickName() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		String actual = aSoccerTeam.getNickName();
		String Expected = "Nick";
		assertEquals(Expected, actual);
	}
	
	@Test (expected = TeamException.class)
	public void GetNickname_NoNickname_exception() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "");
		String actual = aSoccerTeam.getOfficialName();
		String Expected = "Exception";
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_goalsscored() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getGoalsScoredSeason();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void goalsscored() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		int actual = aSoccerTeam.getGoalsScoredSeason();
		int Expected = 5;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_goalsconceded() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getGoalsConcededSeason();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void goalsconceded() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		int actual = aSoccerTeam.getGoalsConcededSeason();
		int Expected = 3;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_comppoints() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getCompetitionPoints();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void comppoints() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		int actual = aSoccerTeam.getCompetitionPoints();
		int Expected = 3;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_goaldifference() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getGoalDifference();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void goaldifference() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		int actual = aSoccerTeam.getGoalDifference();
		int Expected = 2;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_matchesdrawn() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getMatchesDrawn();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void matchesdrawn() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 5);
		int actual = aSoccerTeam.getMatchesDrawn();
		int Expected = 1;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_matcheslost() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getMatchesLost();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void matcheslost() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(1, 3);
		int actual = aSoccerTeam.getMatchesLost();
		int Expected = 1;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_matcheswon() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		int actual = aSoccerTeam.getMatchesWon();
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void matcheswon() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		int actual = aSoccerTeam.getMatchesWon();
		int Expected = 1;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_getform() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		aSoccerTeam.resetStats();
		String actual = aSoccerTeam.getFormString();
		String Expected = "-----";
		assertEquals(Expected, actual);
	}
	
	@Test
	public void getform_W() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		String actual = aSoccerTeam.getFormString();
		String Expected = "W----";
		assertEquals(Expected, actual);
	}
	
	@Test
	public void getform_L() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(1, 3);
		String actual = aSoccerTeam.getFormString();
		String Expected = "L----";
		assertEquals(Expected, actual);
	}
	
	@Test
	public void getform_D() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(3, 3);
		String actual = aSoccerTeam.getFormString();
		String Expected = "D----";
		assertEquals(Expected, actual);
	}
	
	@Test
	public void resetstats_CompareTo() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		SoccerTeam bSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		bSoccerTeam.playMatch(3, 5);
		aSoccerTeam.resetStats();
		bSoccerTeam.resetStats();
		int actual = aSoccerTeam.compareTo(bSoccerTeam);
		int Expected = 0;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void CompareTo_neg() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		SoccerTeam bSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		bSoccerTeam.playMatch(3, 5);
		int actual = aSoccerTeam.compareTo(bSoccerTeam);
		int Expected = -3;
		assertEquals(Expected, actual);
	}
	
	@Test
	public void compareto_pos() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		SoccerTeam bSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(5, 3);
		bSoccerTeam.playMatch(3, 5);
		int actual = bSoccerTeam.compareTo(aSoccerTeam);
		int Expected = 3;
		assertEquals(Expected, actual);
	}
	
	@Test (expected = TeamException.class)
	public void Playmatch_NegGoals_Exception() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		SoccerTeam bSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(-5, 3);
		bSoccerTeam.playMatch(3, 5);
		int actual = bSoccerTeam.compareTo(aSoccerTeam);
		int Expected = 3;
		assertEquals(Expected, actual);
	}
	
	@Test (expected = TeamException.class)
	public void Playmatch_21Goals_Exception() throws TeamException {
		SoccerTeam aSoccerTeam = new SoccerTeam("Official", "Nick");
		SoccerTeam bSoccerTeam = new SoccerTeam("Official", "Nick");
		aSoccerTeam.playMatch(21, 3);
		bSoccerTeam.playMatch(3, 5);
		int actual = bSoccerTeam.compareTo(aSoccerTeam);
		int Expected = 3;
		assertEquals(Expected, actual);
	}
}
