package application;

import java.awt.List;
import java.util.ArrayList;

public class Tournament {
    
    ArrayList<Match[]> bracket;
    
    public Tournament()
    {
        //temporary
        bracket = new ArrayList<Match[]>();
        bracket.add(new Match[8]);
        bracket.add(new Match[4]);
        bracket.add(new Match[2]);
        bracket.add(new Match[1]);
        
        //hard coding team names
        bracket.get(0)[0] = new Match("Challenger 1 -", "Challenger 16-");
        bracket.get(0)[1] = new Match("Challenger 8 -", "Challenger 9 -");
        bracket.get(0)[2] = new Match("Challenger 4 -", "Challenger 13-");
        bracket.get(0)[3] = new Match("Challenger 5 -", "Challenger 12-");
        bracket.get(0)[4] = new Match("Challenger 2 -", "Challenger 15-");
        bracket.get(0)[5] = new Match("Challenger 7 -", "Challenger 10-");
        bracket.get(0)[6] = new Match("Challenger 3 -", "Challenger 14-");
        bracket.get(0)[7] = new Match("Challenger 6 -", "Challenger 11-");
        
    }
    
    public ArrayList<Match[]> getBracket()
    {
        return bracket;
    }
    
    public Match getMatch(int row, int column)
    {
        if(bracket.get(column)[row] == null)
        {
            return new Match(); //don't want to be creating a new match here
        }
        return bracket.get(column)[row];
    }

}
