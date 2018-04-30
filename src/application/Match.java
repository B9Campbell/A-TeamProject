package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Match {
    
    private Team team1; //the two teams in this match
    private Team team2;
    
    private Button submitButton; //button to submit scores
    
    private Alert alert; //alert to show warnings
    
    private Alert winners; //alert to show winners
    
    
    /**
     * Creates a match with TBD teams and sets functionality for the submit button
     */
    public Match()
    {
        team1 = new Team(); //create TBD teams with no-arg constructor
        team2 = new Team();
        
        submitButton = new Button("Submit Scores");
        
        alert = new Alert(AlertType.WARNING); //set alert types for alerts while instantiating them
        winners = new Alert(AlertType.INFORMATION);
        
        submitButton.setOnAction(e -> { //when submit scores is pressed do this
            
            try
            {
                if(team1.getName().trim().toUpperCase().equals("TBD") || team2.getName().trim().toUpperCase().equals("TBD"))
                {
                    //displays warning if the button is pressed before teams for this match have been determined
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning, one or more teams in this match has yet to be determined!");
                    alert.setContentText("Please complete scores for the previous games and click the \"Submit Scores\" button.");
                    alert.showAndWait();
                } else if (Integer.parseInt(team1.getScoreField().getText().trim()) == Integer.parseInt(team2.getScoreField().getText().trim())) {
                    //displays warning if the scored entered result in a tie
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning, this match cannot be a tie!");
                    alert.setContentText("Please rectify the issue and click the \"Submit Scores\" button.");
                    alert.showAndWait();
                } else {
                    
                        
                    if(Integer.parseInt(team1.getScoreField().getText().trim()) > Integer.parseInt(team2.getScoreField().getText().trim()))
                    {
                      //this if-statement triggers if the submit scores button was pushed on the final match otherwise it updates the team in the next match
                        if(!TournamentGUI.tournament.setNextTeam(this, team1.getName(), team2))
                        {
                            String third = ""; //get third place team based on scores of runner ups
                            if(Integer.parseInt(team1.getRunnerUp().getScoreField().getText()) > (Integer.parseInt(team2.getRunnerUp().getScoreField().getText())))
                            {
                                third = team1.getRunnerUp().getName();
                            } else if(Integer.parseInt(team1.getRunnerUp().getScoreField().getText()) < (Integer.parseInt(team2.getRunnerUp().getScoreField().getText())))
                            {
                                third = team2.getRunnerUp().getName();
                            } else {//it is possible to tie here so we display both teams
                                third = team1.getRunnerUp().getName() + " tied with " + team2.getRunnerUp().getName();
                            }
                            
                            //show alert for winners
                            alert.setTitle("Tournament Over");
                            alert.setHeaderText("We have a winner!");
                            alert.setContentText("First Place: " + team1.getName() + "\nSecond Place: " + team2.getName() + "\nThirdPlace: " + third);
                            alert.showAndWait();
                        }
                        
                        team1.getScoreField().setDisable(true);
                        team2.getScoreField().setDisable(true);
                    } else if(Integer.parseInt(team2.getScoreField().getText().trim()) > Integer.parseInt(team1.getScoreField().getText().trim()))
                    {
                        if(!TournamentGUI.tournament.setNextTeam(this, team2.getName(), team1))
                        {
                            //this if-statement triggers if the submit scores button was pushed on the final match otherwise it updates the team in the next match
                            
                            String third = ""; //get third place team based on scores of runner ups
                            if(Integer.parseInt(team1.getRunnerUp().getScoreField().getText()) > (Integer.parseInt(team2.getRunnerUp().getScoreField().getText())))
                            {
                                third = team1.getRunnerUp().getName();
                            } else if(Integer.parseInt(team1.getRunnerUp().getScoreField().getText()) < (Integer.parseInt(team2.getRunnerUp().getScoreField().getText())))
                            {
                                third = team2.getRunnerUp().getName();
                            } else { //it is possible to tie here so we display both teams
                                third = team1.getRunnerUp().getName() + " tied with " + team2.getRunnerUp().getName();
                            }
                            
                            //show alert for winners
                            alert.setTitle("Tournament Over");
                            alert.setHeaderText("We have a winner!");
                            alert.setContentText("First Place: " + team2.getName() + "\nSecond Place: " + team1.getName() + "\nThirdPlace: " +  third);
                            alert.showAndWait();
                        }
                        
                        //disable score fields for the completed games
                        team1.getScoreField().setDisable(true);
                        team2.getScoreField().setDisable(true);
                    }
                }
            } catch(Exception err)
            {
                //catches the exception if non integer scores are given
                alert.setTitle("Warning");
                alert.setHeaderText("Warning, non integer input for score");
                alert.setContentText("Please only choose integer scores.");
                alert.showAndWait();
            }
        });
    }
    
    /**
     * Creates a match filled with two teams
     * @param team1Name - one team
     * @param team2Name - the other team
     */
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
    
    /**
     * gets a team by number
     * @param number of the team to get
     * @return the team
     */
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
    
    /**
     * sets the name of a team in the match
     * @param number - the team number to set
     * @param s - the string to set it to
     */
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
    
    /**
     * @return returns a reference to this matches submit button
     */
    public Button getSubmit()
    {
        return submitButton;
    }

}
