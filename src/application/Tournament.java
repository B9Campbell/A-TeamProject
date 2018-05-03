/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p2
// FILES:            Main.java
//                   Match.java
//                   Team.java
//                   Tournament.java
//
// USER:             Bryce Campbell (bwcampbell4@wisc.edu)
//                   Evan Scott (escott7@wisc.edu)
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs
// Outside Sources:  https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/  - Stream
//                        example
//
// Due: 5/3/18 by 10:00 PM
//
// 2018 May 2, 2018 9 PM Tournament.java 
//////////////////////////// 80 columns wide //////////////////////////////////

package application;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Tournament {
    
    ArrayList<Match[]> bracket;
    ArrayList<String> teamNames;
    
    /**
     * Constructor with hard coded team names
     */
    public Tournament() //not functional
    {
        bracket.add(new Match[8]);
        bracket.add(new Match[4]);
        bracket.add(new Match[2]);
        bracket.add(new Match[1]);

        
        //hard coding team names
        bracket.get(0)[0] = new Match("Challenger 1", "Challenger 16");
        bracket.get(0)[1] = new Match("Challenger 8", "Challenger 9");
        bracket.get(0)[2] = new Match("Challenger 4", "Challenger 13");
        bracket.get(0)[3] = new Match("Challenger 5", "Challenger 12");
        bracket.get(0)[4] = new Match("Challenger 2", "Challenger 15");
        bracket.get(0)[5] = new Match("Challenger 7", "Challenger 10");
        bracket.get(0)[6] = new Match("Challenger 3", "Challenger 14");
        bracket.get(0)[7] = new Match("Challenger 6", "Challenger 11");
        
        for(int c = 1; c < bracket.size(); c++)
        {
            for(int r = 0; r < bracket.get(c).length; r++)
            {
                bracket.get(c)[r] = new Match();
            }
        }
        
    }
    
    /** constructor to read team names form a file
     * 
     * @param filePath - the file path of he file to read from
     */
    public Tournament(String filePath)
    {
        bracket = new ArrayList<Match[]>();
        
        try
        {
            ArrayList<String> teamNames = new ArrayList<String>();
            Stream<String> stream = Files.lines(Paths.get(filePath));
            
            stream.forEachOrdered(s -> { // read the file into a temporary array 
                if(!s.trim().equals("")) //add each team name if it is not whitespace
                {
                    teamNames.add(s);
                }
            });
            if(teamNames.size() == 1)
            {
                bracket.add(0, new Match[1]);
                bracket.get(0)[0] = new Match(teamNames.get(0), "None");
            } else {
                bracket.add(0, new Match[teamNames.size()/2]); 
            }
            Match[] temp = new Match[teamNames.size()/2];
            
            for(int i = 0; i < (int)(teamNames.size()/2); i++) //match up teams correctly
            {
                temp[i] = new Match(teamNames.get(i), teamNames.get(teamNames.size()-i-1));
            }
            
            try //then place matches in correct spots in the bracket
            {
                bracket.get(0)[0] = temp[0];
                bracket.get(0)[1] = temp[temp.length-1];
                bracket.get(0)[2] = temp[(temp.length/2)-1];
                bracket.get(0)[3] = temp[temp.length/2];
            } catch(ArrayIndexOutOfBoundsException e)
            {
                //so that if the number of matches is less than 4 an exception will not be thrown.
            }
            
            for(int i = 0; i < (temp.length - 4)/2;i++)
            {
                bracket.get(0)[i*2 + 4] = temp[i+1];
                bracket.get(0)[i*2 + 5] = temp[temp.length-2-i];
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < (int) (Math.log(bracket.get(0).length) / Math.log(2)); i++)
        {
            bracket.add(1, new Match[(int)Math.pow(2, i)]);
        }
        for(int c = 1; c < bracket.size(); c++)
        {
            for(int r = 0; r < bracket.get(c).length; r++)
            {
                bracket.get(c)[r] = new Match();
            }
        }
    }

    /**
     * @return this tournaments bracket
     */
	public ArrayList<Match[]> getBracket()
    {
        return bracket;
    }
    
	/**
	 * get the match in the given position
	 * @param row - the row to get the match from
	 * @param column - the col to get the match from
	 * @return the match
	 */
    public Match getMatch(int row, int column)
    {
        return bracket.get(column)[row];
    }
    
    /**
     * sets the next team in the tournament after a match is completed. also sets the runner up to
     * determine third place
     * @param m - the match
     * @param t - the name of the winner
     * @param runnerup - the runnerup team
     * @return weather or not this was the final game
     */
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
                        bracket.get(c+1)[(int)(r/2)].setTeamName(r%2 + 1, t);
                        bracket.get(c+1)[(int)(r/2)].getTeam(r%2 + 1).setRunnerUp(runnerup);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        
        throw new NoSuchElementException(); // if the match is not in the tournament
    }

}
