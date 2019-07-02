package uk.co.autotrader;

import java.util.Objects;

public class TeamData {

    private String teamName;
    private String goalsFor;
    private String goalsAgainst;
    private int goalDifference;


    TeamData(String teamName, String goalsFor, String goalsAgainst){
        this.teamName = teamName;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = Math.abs(Integer.parseInt(goalsFor) - Integer.parseInt(goalsAgainst));
    }

    public String getTeamName() {
        return teamName;
    }

    public String getGoalsFor() {
        return goalsFor;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamData teamData = (TeamData) o;
        return goalDifference == teamData.goalDifference &&
                teamName.equals(teamData.teamName) &&
                goalsFor.equals(teamData.goalsFor) &&
                goalsAgainst.equals(teamData.goalsAgainst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, goalsFor, goalsAgainst, goalDifference);
    }
}
