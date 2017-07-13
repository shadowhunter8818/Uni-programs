package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 *
 */
public class SportsTeamFormTests {

	@Test
	public void creation() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "-----";
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void AddResults_WIN() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "W----";
		aSportsTeamForm.addResultToForm(WLD.WIN);
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void AddResults_LOSS() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "L----";
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void AddResults_Draw() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "D----";
		aSportsTeamForm.addResultToForm(WLD.DRAW);
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void AddResults_2results() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "LW---";
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void AddResults_5results() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "WLDLW";
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.DRAW);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void AddResults_6results() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "WWLDL";
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.DRAW);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void Getnumber_of_games_1game() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		int Expectedanswer = 1;
		aSportsTeamForm.addResultToForm(WLD.WIN);
		int actual = aSportsTeamForm.getNumGames();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void Getnumber_of_games_5games() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		int Expectedanswer = 5;
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.DRAW);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		int actual = aSportsTeamForm.getNumGames();
		assertEquals(Expectedanswer, actual);
	}
	
	@Test
	public void Getnumber_of_games_6games() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		int Expectedanswer = 5;
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.DRAW);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		int actual = aSportsTeamForm.getNumGames();
		assertEquals(Expectedanswer, actual);
	}
	
	public void resetform() {
		SportsTeamForm aSportsTeamForm = new SportsTeamForm();
		String Expectedanswer = "-----";
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.DRAW);
		aSportsTeamForm.addResultToForm(WLD.LOSS);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.addResultToForm(WLD.WIN);
		aSportsTeamForm.resetForm();
		String actual = aSportsTeamForm.toString();
		assertEquals(Expectedanswer, actual);
	}
}
