package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Match {
    
    private Team team1;
    private Team team2;
    
    private Button submitButton;
    
    private Alert alert;
    
    private Alert winners;
    
    
    
    public Match()
    {
        team1 = new Team();
        team2 = new Team();
        
        submitButton = new Button("Submit Scores");
        
        alert = new Alert(AlertType.WARNING);
        winners = new Alert(AlertType.INFORMATION);
        
        submitButton.setOnAction(e -> {
            
            try
            {
                if(team1.getName().trim().toUpperCase().equals("TBD") || team2.getName().trim().toUpperCase().equals("TBD"))
                {
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning, one or more teams in this match has yet to be determined!");
                    alert.setContentText("Please complete scores for the previous games and click the \"Submit Scores\" button.");
                    alert.showAndWait();
                } else if (Integer.parseInt(team1.getScoreField().getText().trim()) == Integer.parseInt(team2.getScoreField().getText().trim())) {
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning, this match cannot be a tie!");
                    alert.setContentText("Please rectify the issue and click the \"Submit Scores\" button.");
                    alert.showAndWait();
                } else {
                    
                        
                    if(Integer.parseInt(team1.getScoreField().getText().trim()) > Integer.parseInt(team2.getScoreField().getText().trim()))
                    {
                        if(!TournamentGUI.tournament.setNextTeam(this, team1.getName(), team2))
                        {
                            alert.setTitle("Tournament Over");
                            alert.setHeaderText("We have a winner!");
                            alert.setContentText("First Place: " + team1.getName() + "\nSecond Place: " + team2.getName() + "\nThirdPlace: ");
                            alert.showAndWait();
                        }
                        
                        team1.getScoreField().setDisable(true);
                        team2.getScoreField().setDisable(true);
                    } else if(Integer.parseInt(team2.getScoreField().getText().trim()) > Integer.parseInt(team1.getScoreField().getText().trim()))
                    {
                        if(!TournamentGUI.tournament.setNextTeam(this, team2.getName(), team1))
                        {
                            String third = (Integer.parseInt(team1.getRunnerUp().getScoreField().getText()) > (Integer.parseInt(team2.getRunnerUp().getScoreField().getText()))) ? team1.getRunnerUp().getName() : team2.getRunnerUp().getName();
                            alert.setTitle("Tournament Over");
                            alert.setHeaderText("We have a winner!");
                            alert.setContentText("First Place: " + team2.getName() + "\nSecond Place: " + team1.getName() + "\nThirdPlace: " +  third);
                            alert.showAndWait();
                        }
                        
                        team1.getScoreField().setDisable(true);
                        team2.getScoreField().setDisable(true);
                    }
                }
            } catch(Exception err)
            {
                alert.setTitle("Warning");
                alert.setHeaderText("Warning, non integer input for score");
                alert.setContentText("Please only choose integer scores.");
                alert.showAndWait();
            }
        });
    }
    
    public Match(String team1Name, String team2Name)
    {
        this();
        team1 = new Team(team1Name);
        team2 = new Team(team2Name);
        
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
