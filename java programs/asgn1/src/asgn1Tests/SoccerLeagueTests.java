package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	@Test
	public void Getregisteredteams() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		try {
			team = new SoccerTeam("official", "nick");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		int Expected = 1;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Registerteams_OfficialNameSame_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("official", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Registerteams_ToManyTeams_exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		SoccerTeam team3 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
			team3 = new SoccerTeam("officiala", "nickss");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.registerTeam(team3);
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test
	public void StartSeason() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void StartSeason_SeasonAlreadyStarted_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.startNewSeason();
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}	
	
	@Test (expected = LeagueException.class)
	public void StartSeason_NotEnoughRegisteredTeams_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		try {
			team = new SoccerTeam("official", "nick");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.startNewSeason();
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Registerteams_SeasonStarted_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		SoccerTeam team3 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
			team3 = new SoccerTeam("officiala", "nickss");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.registerTeam(team3);
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test
	public void EndSeason() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.endSeason();
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void EndSeason_SeasonNotStarted_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.endSeason();
		int Expected = 2;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test
	public void RemoveTeam() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		try {
			team = new SoccerTeam("official", "nick");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.removeTeam(team);
		int Expected = 0;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void RemoveTeam_NoTeam_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.removeTeam(team2);
		int Expected = 0;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void RemoveTeam_SeasonStarted_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.removeTeam(team2);
		int Expected = 0;
		int actual = aSoccerLeague.getRegisteredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test 
	public void GetRequiredTeams() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		int Expected = 2;
		int actual = aSoccerLeague.getRequiredNumTeams();
		assertEquals(Expected, actual);
	}
	
	@Test
	public void ContainsTeam_true() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		boolean Expected = true;
		boolean actual = aSoccerLeague.containsTeam("official");
		assertEquals(Expected, actual);
	}
	
	@Test
	public void ContainsTeam_flase() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		boolean Expected = false;
		boolean actual = aSoccerLeague.containsTeam("dfficial");
		assertEquals(Expected, actual);
	}
	
	@Test
	public void GetTeamByOfficialsName() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		SoccerTeam Expected = team2;
		SoccerTeam actual = aSoccerLeague.getTeamByOfficalName("officials");
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void GetTeamByOfficialsName_NoOfficial_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		SoccerTeam Expected = team2;
		SoccerTeam actual = aSoccerLeague.getTeamByOfficalName("ffficials");
		assertEquals(Expected, actual);
	}
	
	@Test
	public void Playmatch_GetTopTeam() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.playMatch("official", 5, "officials", 3);
		SoccerTeam Expected = team;
		SoccerTeam actual = aSoccerLeague.getTopTeam();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Playmatch_GetTopTeam_0teams_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam actual = aSoccerLeague.getTopTeam();
		Object Expected = null;
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Playmatch_GetTopTeam_notEnoughTeams_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		try {
			team = new SoccerTeam("official", "nick");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.playMatch("official", 5, "officials", 3);
		SoccerTeam Expected = team;
		SoccerTeam actual = aSoccerLeague.getTopTeam();
		assertEquals(Expected, actual);
	}
	
	@Test
	public void Playmatch_GetBottomTeam() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.playMatch("official", 5, "officials", 3);
		SoccerTeam Expected = team2;
		SoccerTeam actual = aSoccerLeague.getBottomTeam();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Playmatch_GetBottomTeam_0Teams_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam actual = aSoccerLeague.getBottomTeam();
		Object Expected = null;
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Playmatch_GetBottomTeam_notEnoughTeams_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(3);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.playMatch("official", 5, "officials", 3);
		SoccerTeam Expected = team2;
		SoccerTeam actual = aSoccerLeague.getBottomTeam();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Playmatch_OfficialsSameName_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("official", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		SoccerTeam Expected = team;
		SoccerTeam actual = aSoccerLeague.getTopTeam();
		assertEquals(Expected, actual);
	}
	
	@Test (expected = LeagueException.class)
	public void Playmatch_SeasonNotStarted_Exception() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.playMatch("official", 5, "officials", 3);
		SoccerTeam Expected = team;
		SoccerTeam actual = aSoccerLeague.getTopTeam();
		assertEquals(Expected, actual);
	}
	
	
	@Test
	public void Offseason_False() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		aSoccerLeague.playMatch("official", 5, "officials", 3);
		boolean Expected = false;
		boolean actual = aSoccerLeague.isOffSeason();
		assertEquals(Expected, actual);
	}
	
	@Test
	public void Offseason_true() throws LeagueException {
		SoccerLeague aSoccerLeague = new SoccerLeague(2);
		SoccerTeam team = null;
		SoccerTeam team2 = null;
		try {
			team = new SoccerTeam("official", "nick");
			team2 = new SoccerTeam("officials", "nicks");
		} catch (TeamException e) {
			e.printStackTrace();
		}
		aSoccerLeague.registerTeam(team);
		aSoccerLeague.registerTeam(team2);
		aSoccerLeague.startNewSeason();
		boolean Expected = true;
		boolean actual = aSoccerLeague.isOffSeason();
		assertEquals(Expected, actual);
	}
}

