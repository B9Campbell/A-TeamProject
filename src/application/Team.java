package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Team {
	
    private Label name;
    private Team runnerup;
    private Label spacer;
    private TextField scoreField;
    private HBox teamBox;
	
	public Team(){
	    this.name = new Label("TBD");
        scoreField = new TextField("Score");
        scoreField.setPrefWidth(50);
        
        spacer = new Label(" - ");
        
        teamBox = new HBox();
        
        teamBox.getChildren().addAll(this.name, spacer, scoreField);
	}
	
	public Team(String name){
		this.name = new Label(name);
		scoreField = new TextField("Score");
		scoreField.setPrefWidth(50);
		
		spacer = new Label(" - ");
		
		teamBox = new HBox();
		
		teamBox.getChildren().addAll(this.name, spacer, scoreField);
	}
	
	public String getName(){
		return this.name.getText();
	}
	
	public Label getNameLabel(){
        return this.name;
    }
	
	public HBox getBox()
	{
	    return teamBox;
	}
	
	public Team getRunnerUp()
	{
	    return runnerup;
	}
	
	public void setRunnerUp(Team r)
    {
        runnerup = r;
    }
	
	public TextField getScoreField()
	{
	    return scoreField;
	}

}
