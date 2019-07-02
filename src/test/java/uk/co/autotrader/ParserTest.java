package uk.co.autotrader;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

	@Test
	public void shouldAddEachLineOfTableIntoList() throws IOException{
		Parser parser = new Parser();
		List<String> footballResults = parser.readFootballTableFile("data/football.dat");
		List<String> expected = Collections.singletonList("    1. Arsenal         38    26   9   3    79  -  36    87");
		assertThat(footballResults).containsAnyElementsOf(expected);
	}

	@Test
	public void returnListOfLineDataWithNoSpaces() throws IOException{
		Parser parser = new Parser();
		List<String> table = parser.readFootballTableFile("data/football.dat");

		List<String> expected = new ArrayList<>();
		expected.add("1.");
		expected.add("Arsenal");
		expected.add("38");
		expected.add("26");
		expected.add("9");
		expected.add("3");
		expected.add("79");
		expected.add("-");
		expected.add("36");
		expected.add("87");


		assertThat(parser.getListOfLineDataWithNoSpaces(table.get(0))).isEqualTo(expected);

	}

	@Test
	public void shouldParseDataIntoTeamNameWithForAndAgainstResults() throws IOException {
		Parser parser = new Parser();

		List<String> footballResults = parser.readFootballTableFile("data/football.dat");
		List<String> listOfLineDataWithNoSpaces = parser.getListOfLineDataWithNoSpaces(footballResults.get(0));

		TeamData expected = new TeamData("Arsenal", "79", "36");

		TeamData teamData = parser.parseListIntoTeamData(listOfLineDataWithNoSpaces);
		assertThat(teamData).isEqualTo(expected);
	}

	@Test
	public void shouldCalculateGoalDifferenceForTeam() throws IOException {
		Parser parser = new Parser();

		List<String> footballResults = parser.readFootballTableFile("data/football.dat");
		List<String> listOfLineDataWithNoSpaces = parser.getListOfLineDataWithNoSpaces(footballResults.get(0));

		int expected = Math.abs(79 - 36);

		TeamData teamData = parser.parseListIntoTeamData(listOfLineDataWithNoSpaces);
		assertThat(teamData.getGoalDifference()).isEqualTo(expected);
	}

	@Test
	public void shouldGetAllTestDataFromFootballTable() throws IOException {
		Parser parser = new Parser();

		List<String> footballResults = parser.readFootballTableFile("data/football.dat");
		List<TeamData> actual = parser.getAllTeamDataFromTable(footballResults);

		TeamData arsenal = new TeamData("Arsenal", "79", "36");
		TeamData liverpool = new TeamData("Liverpool", "67", "30");
		TeamData manchesterUnited = new TeamData("Manchester_U", "87", "45");

		List<TeamData> expected = Arrays.asList(arsenal, liverpool, manchesterUnited);

		assertThat(actual).containsAnyElementsOf(expected);

	}

	@Test
	public void shouldGetTeamWithTheSmallestGoalDifference() throws IOException {
		Parser parser = new Parser();
		List<String> footballResults = parser.readFootballTableFile("data/football.dat");
		List<TeamData> teamData = parser.getAllTeamDataFromTable(footballResults);

		String actual = parser.getTeamWithSmallestGoalDifference(teamData);

		String expected = "Aston_Villa";

		assertThat(actual).isEqualTo(expected);
	}


//	print/return table with team names column without numbers and columns we want (team name, gf, ga)
//	print/return table with new goal difference field
//	check goal difference calculations are correct
//	print/return table sorted by goal difference, lowest to highest
//	print/return team name with lowest goal difference
}
