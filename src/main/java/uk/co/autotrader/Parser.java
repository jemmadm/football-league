package uk.co.autotrader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Parser {

    public List<String>readFootballTableFile(String filename) throws IOException {
        List<String> footballTableLines = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader(filename));
        String line;
        input.readLine();
        while ((line = input.readLine()) != null) {
            footballTableLines.add(line);
        }

        return footballTableLines;

    }

    public List<TeamData> getAllTeamDataFromTable(List<String> tableLines) {
        List<TeamData> allTeamData = new ArrayList<>();

        for (String line:tableLines) {
            List<String> listOfLineDataWithNoSpaces = getListOfLineDataWithNoSpaces(line);
            allTeamData.add(parseListIntoTeamData(listOfLineDataWithNoSpaces));
        }

        return allTeamData;
    }

    public List<String> getListOfLineDataWithNoSpaces(String tableLine) {
        String[] splitTableIntoLines = tableLine.split(" ");
        List<String> tableWithSpacesRemoved = new ArrayList<>();

        for (String index:splitTableIntoLines) {
            if(!index.isEmpty()){
                tableWithSpacesRemoved.add(index);
            }
        }
        return tableWithSpacesRemoved;
    }

    public TeamData parseListIntoTeamData(List<String> tableLine){
        return new TeamData(tableLine.get(1), tableLine.get(6), tableLine.get(8));
    }

    public String getTeamWithSmallestGoalDifference(List<TeamData> teamData) {
        teamData.sort(new GoalDifferenceComparator());
        return teamData.get(0).getTeamName();
    }
}

class GoalDifferenceComparator implements Comparator<TeamData> {

    @Override
    public int compare(TeamData firstTeam, TeamData secondTeam) {
        return firstTeam.getGoalDifference() - secondTeam.getGoalDifference();
    }
}
