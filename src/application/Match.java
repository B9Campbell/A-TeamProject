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
    
    private int team1Score;
    private int Team2Score;
    
    public Match()
    {
        team1Label = new Label("TBD");
        team2Label = new Label("TBD");
        
        team1Field = new TextField("Score");
        team1Field.setPrefWidth(50);
        team2Field = new TextField("Score");
        team2Field.setPrefWidth(50);
        
        team1Box = new HBox();
        team2Box = new HBox();
        
        team1Box.getChildren().addAll(team1Label, team1Field);
        team2Box.getChildren().addAll(team2Label, team2Field);
    }
    
    public Match(String team1, String team2)
    {
        team1Label = new Label(team1);
        team2Label = new Label(team2);
        
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
     * Returns the HBox to be put in this matches cell in the grid
     * 
     * @param number of the team to return
     * @return the HBox
     */
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
