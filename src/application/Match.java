package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Match {
    
    private Team team1;
    private Team team2;
    
    private Button submitButton;
    
    public Match()
    {
        team1 = new Team();
        team2 = new Team();
        
        submitButton = new Button("Submit Scores");
        
        submitButton.setOnAction(e -> {
            team1.getScoreField().setDisable(true);
            team2.getScoreField().setDisable(true);
            
            try
            {
                if(Integer.parseInt(team1.getScoreField().getText().trim()) > Integer.parseInt(team2.getScoreField().getText().trim()))
                {
                    TournamentGUI.tournament.setNextTeam(this, team1.getName());
                } else if(Integer.parseInt(team2.getScoreField().getText().trim()) > Integer.parseInt(team1.getScoreField().getText().trim()))
                {
                    TournamentGUI.tournament.setNextTeam(this, team2.getName());
                } else {
                    // TODO: implement error for tie
                }
            } catch(Exception err)
            {
                // TODO: implement error for non-int input
                System.out.println("not int");
                err.printStackTrace();
            }
        });
    }
    
    public Match(String team1Name, String team2Name)
    {
        team1 = new Team(team1Name);
        team2 = new Team(team2Name);
        
        submitButton = new Button("Submit Scores");
        
        submitButton.setOnAction(e -> {
            team1.getScoreField().setDisable(true);
            team2.getScoreField().setDisable(true);
            
            try
            {
                if(Integer.parseInt(team1.getScoreField().getText().trim()) > Integer.parseInt(team2.getScoreField().getText().trim()))
                {
                    TournamentGUI.tournament.setNextTeam(this, team1.getName());
                } else if(Integer.parseInt(team2.getScoreField().getText().trim()) > Integer.parseInt(team1.getScoreField().getText().trim()))
                {
                    TournamentGUI.tournament.setNextTeam(this, team2.getName());
                } else {
                    // TODO: implement error for tie
                }
            } catch(Exception err)
            {
                // TODO: implement error for non-int input
                System.out.println("not int");
                err.printStackTrace();
            }
        });
    }
    
    /** 
     * Returns the HBox to be put in this matches cell in the grid
     * 
     * @param number of the team to return
     * @return the HBox
     */
    public HBox getTeamHBox(int number)
    {
        if(number == 1)
        {
            return team1.getBox();
        } else if(number ==2)
        {
            return team2.getBox();
        } else {
            return null; //should throw an error here
        }
    }
    
    public Team getTeam(int number)
    {
        if(number == 1)
        {
            return team1;
        } else if(number ==2)
        {
            return team2;
        } else {
            return null; //should throw an error here
        }
    }
    
    public void setTeamName(int number, String s)
    {
        if(number == 1)
        {
            
            team1.getNameLabel().setText(s);
        } else if(number == 2)
        {
            team2.getNameLabel().setText(s);
        } else {
             //should throw an error here
        }
    }
    
    public Button getSubmit()
    {
        return submitButton;
    }

}
