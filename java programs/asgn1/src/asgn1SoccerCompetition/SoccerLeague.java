package asgn1SoccerCompetition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;


/**
 * A class to model a soccer league. Matches are played between teams and points awarded for a win,
 * loss or draw. After each match teams are ranked, first by points, then by goal difference and then
 * alphabetically. 
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerLeague implements SportsLeague{
	// Specifies the number of team required/limit of teams for the league
	private int requiredTeams;
	// Specifies is the league is in the off season
	private boolean offSeason;
	// Specifies is the season hasn't started
	private boolean endseason = true;
	ArrayList<SoccerTeam> Teams;
	/**
	 * Generates a model of a soccer team with the specified number of teams. 
	 * A season can not start until that specific number of teams has been added. 
	 * Once that number of teams has been reached no more teams can be added unless
	 * a team is first removed. 
	 * 
	 * @param requiredTeams The number of teams required/limit for the league.
	 */
	public SoccerLeague (int requiredTeams){
		this.requiredTeams = requiredTeams;
		Teams = new ArrayList<SoccerTeam>();
	}

	
	/**
	 * Registers a team to the league.
	 * 
	 * @param team Registers a team to play in the league.
	 * @throws LeagueException If the season has already started, if the maximum number of 
	 * teams allowed to register has already been reached or a team with the 
	 * same official name has already been registered.
	 */
	public void registerTeam(SoccerTeam team) throws LeagueException {
		if(Teams.size() == requiredTeams) {
			throw new LeagueException("he maximum number of teams allowed to register has already been reached");
		}
		if (endseason == false) {
			throw new LeagueException("the season has already started");
		}
		
		for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam Yeam = t.next();
    		if (Yeam.getOfficialName() == team.getOfficialName()) {
    			throw new LeagueException("a team with the same official name has already been registered.");
    		}
		}
		Teams.add(team);
	}
	
	/**
	 * Removes a team from the league.
	 * 
	 * @param team The team to remove
	 * @throws LeagueException if the season has not ended or if the team is not registered into the league.
	 */
	public void removeTeam(SoccerTeam team) throws LeagueException{
		if (Teams.contains(team) != true) {
			throw new LeagueException("the team is not registered into the league");
		}
		if (endseason == false) {
			throw new LeagueException("the season has not ended");
		}
		Teams.remove(team);
		}
	
	/** 
	 * Gets the number of teams currently registered to the league
	 * 
	 * @return the current number of teams registered
	 */
	public int getRegisteredNumTeams(){
		return Teams.size();
	}
	
	/**
	 * Gets the number of teams required for the league to begin its 
	 * season which is also the maximum number of teams that can be registered
	 * to a league.

	 * @return The number of teams required by the league/maximum number of teams in the league
	 */
	public int getRequiredNumTeams(){
		return requiredTeams;
	}
	
	/** 
	 * Starts a new season by reverting all statistics for each team to initial values.
	 * 
	 * @throws LeagueException if the number of registered teams does not equal the required number of teams or if the season has already started
	 */
	public void startNewSeason() throws LeagueException{
		if (Teams.size() != requiredTeams) {
			throw new LeagueException("the number of registered teams does not equal the required number of teams");
		}
		if (endseason == false) {
			throw new LeagueException("the season has already started");
		}
		for(Iterator<SoccerTeam> i = Teams.iterator(); i.hasNext();) {
			SoccerTeam team = i.next();
			team.resetStats();
			}
		endseason = false;
		}
	

	/**
	 * Ends the season.
	 * 
	 * @throws LeagueException if season has not started
	 */
	public void endSeason() throws LeagueException{
		if (endseason != false) {
			throw new LeagueException("season has not started");
		}
		endseason = true;
	}
	
	/**
	 * Specifies if the league is in the off season (i.e. when matches are not played).
	 * @return True If the league is in its off season, false otherwise.
	 */
	public boolean isOffSeason(){
		for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam team = t.next();
    		if (team.getGoalsScoredSeason() == 0 && team.getGoalsConcededSeason() == 0) {
    			this.offSeason = true;
    		} else {
    			this.offSeason = false;
    		}    		
		}
		return this.offSeason;
	}
	
	
	
	/**
	 * Returns a team with a specific name.
	 * 
	 * @param name The official name of the team to search for.
	 * @return The team object with the specified official name.
	 * @throws LeagueException if no team has that official name.
	 */
	public SoccerTeam getTeamByOfficalName(String name) throws LeagueException{		
		for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam team = t.next();
    		if (team.getOfficialName() == name) {
    		return team;
    		}
    	}
		throw new LeagueException("no team has that official name");
    }

		
	/**
	 * Plays a match in a specified league between two teams with the respective goals. After each match the teams are
	 * resorted.
     *
	 * @param homeTeamName The name of the home team.
	 * @param homeTeamGoals The number of goals scored by the home team.
	 * @param awayTeamName The name of the away team.
	 * @param awayTeamGoals The number of goals scored by the away team.
	 * @throws LeagueException If the season has not started or if both teams have the same official name. 
	 */
	public void playMatch(String homeTeamName, int homeTeamGoals, String awayTeamName, int awayTeamGoals) throws LeagueException{
		if (homeTeamName == awayTeamName) {
			throw new LeagueException("both teams have the same official name");
		}
		if (endseason == true) {
			throw new LeagueException("the season has not started");
		}
		for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam team = t.next();
    		if (team.getOfficialName() == homeTeamName){
    			try {
					team.playMatch(homeTeamGoals, awayTeamGoals);
				} catch (TeamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		if (team.getOfficialName() == awayTeamName){
    			try {
					team.playMatch(awayTeamGoals, homeTeamGoals);
				} catch (TeamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
		}
	}
	
	/**
	 * Displays a ranked list of the teams in the league  to the screen.
	 */
	public void displayLeagueTable(){
		// TO DO (optional)
	}	
	
	/**
	 * Returns the highest ranked team in the league.
     *
	 * @return The highest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getTopTeam() throws LeagueException{
		if (Teams.size() == 0) {
			throw new LeagueException("the number of teams is zero");
		}
		if (Teams.size() < requiredTeams) {
			throw new LeagueException("less than the required number of teams.");
		}
		int highest_goal = 0;
		SoccerTeam highest_team = null;
		for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam team = t.next();
    		if (team.getGoalsScoredSeason() > highest_goal) {
    		highest_team = team;
    		highest_goal = team.getGoalsScoredSeason();
    		}
		}
		return highest_team;
	}

	/**
	 * Returns the lowest ranked team in the league.
     *
	 * @return The lowest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getBottomTeam() throws LeagueException{
		if (Teams.size() == 0) {
			throw new LeagueException("the number of teams is zero");
		}
		if (Teams.size() < requiredTeams) {
			throw new LeagueException("less than the required number of teams.");
		}
		int lowest_goal = 10000;
		SoccerTeam lowest_team = null;
		for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam team = t.next();
    		if (team.getGoalsScoredSeason() < lowest_goal) {
    		lowest_team = team;
    		lowest_goal = team.getGoalsScoredSeason();
    		}
		}
		return lowest_team;
	}

	/** 
	 * Sorts the teams in the league.
	 */
    public void sortTeams(){
    	Collections.sort(Teams);	
    }
    
    /**
     * Specifies if a team with the given official name is registered to the league.
     * 
     * @param name The name of a team.
     * @return True if the team is registered to the league, false otherwise. 
     */
    public boolean containsTeam(String name){
    	for(Iterator<SoccerTeam> t = Teams.iterator(); t.hasNext();) {
    		SoccerTeam team = t.next();
    		if (team.getOfficialName() == name) {
    		return true;
    		}
    	}
    return false;
    }
}

