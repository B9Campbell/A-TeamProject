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
        bracket.get(0)[0] = new Match("Challenger1", "Challenger16");
        bracket.get(0)[1] = new Match("Challenger8", "Challenger9");
        bracket.get(0)[2] = new Match("Challenger4", "Challenger13");
        bracket.get(0)[3] = new Match("Challenger5", "Challenger12");
        bracket.get(0)[4] = new Match("Challenger2", "Challenger15");
        bracket.get(0)[5] = new Match("Challenger7", "Challenger10");
        bracket.get(0)[6] = new Match("Challenger3", "Challenger14");
        bracket.get(0)[7] = new Match("Challenger6", "Challenger11");
        
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
