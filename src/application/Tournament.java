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
        bracket.get(0)[0] = new Match("Chalenger1", "Chalenger16");
        bracket.get(0)[1] = new Match("Chalenger8", "Chalenger9");
        bracket.get(0)[2] = new Match("Chalenger4", "Chalenger13");
        bracket.get(0)[3] = new Match("Chalenger5", "Chalenger12");
        bracket.get(0)[4] = new Match("Chalenger2", "Chalenger15");
        bracket.get(0)[5] = new Match("Chalenger7", "Chalenger10");
        bracket.get(0)[6] = new Match("Chalenger3", "Chalenger14");
        bracket.get(0)[7] = new Match("Chalenger6", "Chalenger11");
        
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
