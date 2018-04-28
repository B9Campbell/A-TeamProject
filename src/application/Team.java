package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Team {
	
    private Label name;
    private TextField scoreField;
    private HBox teamBox;
	
	public Team(){
	    this.name = new Label("TBD - ");
        scoreField = new TextField("Score");
        scoreField.setPrefWidth(50);
        
        teamBox = new HBox();
        
        teamBox.getChildren().addAll(this.name, scoreField);
	}
	
	public Team(String name){
		this.name = new Label(name);
		scoreField = new TextField("Score");
		scoreField.setPrefWidth(50);
		
		teamBox = new HBox();
		
		teamBox.getChildren().addAll(this.name, scoreField);
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
	
	public TextField getScoreField()
	{
	    return scoreField;
	}

}
