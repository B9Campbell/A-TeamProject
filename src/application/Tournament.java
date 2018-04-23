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
    }
    
    public ArrayList<Match[]> getBracket()
    {
        return bracket;
    }
    
    public Match getMatch(int row, int column)
    {
        return new Match();
    }

}
