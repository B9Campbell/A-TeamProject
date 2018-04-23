package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Match {
    
    private Label team1Label;
    private Label team2Label;
    
    private TextField team1Field;
    private TextField team2Field;
    
    private HBox team1Box;
    private HBox team2Box;
    
    public Match()
    {
        team1Label = new Label("none");
        team2Label = new Label("none");
        
        team1Field = new TextField("Score");
        team2Field = new TextField("Score");
        
        team1Box = new HBox();
        team2Box = new HBox();
    }
    
    /** 
     * Returns the team based on the number
     * 
     * @param number of the team to return
     * @return the team name
     */
    public Label getTeamLabel(int number)
    {
        if(number == 1)
        {
            return team1Label;
        } else if(number ==2)
        {
            return team2Label;
        } else {
            return null; //should throw an error here
        }
    }
    
    public HBox getTeamHBox(int number)
    {
        return null;
    }

}
