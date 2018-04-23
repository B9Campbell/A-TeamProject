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
        team1Field.setPrefWidth(50);
        team2Field = new TextField("Score");
        team2Field.setPrefWidth(50);
        
        team1Box = new HBox();
        team2Box = new HBox();
        
        team1Box.getChildren().addAll(team1Label, team1Field);
        team2Box.getChildren().addAll(team2Label, team2Field);
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
        if(number == 1)
        {
            return team1Box;
        } else if(number ==2)
        {
            return team2Box;
        } else {
            return null; //should throw an error here
        }
    }

}
