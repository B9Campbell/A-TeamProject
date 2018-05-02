package application;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Tournament {
    
    ArrayList<Match[]> bracket;
    ArrayList<String> teamNames;
    
    public Tournament()
    {
        //bracket.add(new Match[8]);
        //bracket.add(new Match[4]);
        //bracket.add(new Match[2]);
        //bracket.add(new Match[1]);

        
        //hard coding team names
        //bracket.get(0)[0] = new Match("Challenger 1", "Challenger 16");
        //bracket.get(0)[1] = new Match("Challenger 8", "Challenger 9");
        //bracket.get(0)[2] = new Match("Challenger 4", "Challenger 13");
        //bracket.get(0)[3] = new Match("Challenger 5", "Challenger 12");
        //bracket.get(0)[4] = new Match("Challenger 2", "Challenger 15");
        //bracket.get(0)[5] = new Match("Challenger 7", "Challenger 10");
        //bracket.get(0)[6] = new Match("Challenger 3", "Challenger 14");
        //bracket.get(0)[7] = new Match("Challenger 6", "Challenger 11");
        
        for(int c = 1; c < bracket.size(); c++)
        {
            for(int r = 0; r < bracket.get(c).length; r++)
            {
                bracket.get(c)[r] = new Match();
            }
        }
        
    }
    public Tournament(ArrayList<String> team)
    {
    	this.teamNames = team;
    	teamNames = sortTeams(teamNames);
    	for(int i =0,j=0; i< teamNames.size()/2; i++, j+=2){
        	bracket.add(new Match[i]);
        	bracket.get(0)[i] = new Match(teamNames.get(j), teamNames.get(j+1));
        }
        
        //temporary
        bracket = new ArrayList<Match[]>();
        
        for(int c = 1; c < bracket.size(); c++)
        {
            for(int r = 0; r < bracket.get(c).length; r++)
            {
                bracket.get(c)[r] = new Match();
            }
        }
    }
    
    private ArrayList<String> sortTeams(ArrayList<String> teams) {
		for(int i =teams.size(), j=1; i>teams.size()/2 && j<teams.size(); i-=2,j+=2){
			String temp = teams.get(i);
			teams.set(i, teams.get(j));
			teams.set(j, temp);
		}
		return null;
	}

	public ArrayList<Match[]> getBracket()
    {
        return bracket;
    }
    
    public Match getMatch(int row, int column)
    {
        return bracket.get(column)[row];
    }
    
    public boolean setNextTeam(Match m, String t, Team runnerup)
    {
        for(int c = 0; c < bracket.size(); c++)
        {
            for(int r = 0; r < bracket.get(c).length; r++)
            {
                if(bracket.get(c)[r] == m)
                {
                    if(c != bracket.size()-1)
                    {
                        System.out.println();
                        bracket.get(c+1)[(int)(r/2)].setTeamName(r%2 + 1, t);
                        bracket.get(c+1)[(int)(r/2)].getTeam(r%2 + 1).setRunnerUp(runnerup);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        
        throw new NoSuchElementException();
    }

}
