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
// 2018 May 2, 2018 9 PM Team.java
//////////////////////////// 80 columns wide //////////////////////////////////

package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Team {
	
    private Label name; //teams name
    private Team runnerup; //teams runner up
    private Label spacer; //a spacer used for displaying
    private TextField scoreField; // the feild to type the score
    private HBox teamBox;// the hbox to display the team.
	
    /**
     * Constructor to create yet to be determined teams
     */
	public Team(){
	    this.name = new Label("TBD");
        scoreField = new TextField("Score");
        scoreField.setPrefWidth(50);
        
        spacer = new Label(" - ");
        
        teamBox = new HBox();
        
        teamBox.getChildren().addAll(this.name, spacer, scoreField);
	}
	
	/**
	 * Constructor to create a team with a name
	 * @param name - the teams name
	 */
	public Team(String name){
		this.name = new Label(name);
		scoreField = new TextField("Score");
		scoreField.setPrefWidth(50);
		
		spacer = new Label(" - ");
		
		teamBox = new HBox();
		
		teamBox.getChildren().addAll(this.name, spacer, scoreField);
	}
	
	/**
	 * gets the teams name
	 * @return the teams name
	 */
	public String getName(){
		return this.name.getText();
	}
	
	/**
	 * gets the teams name label
	 * @return the name label
	 */
	public Label getNameLabel(){
        return this.name;
    }
	
	/**
	 * t=get an hbox to display this team
	 * @return the hbox
	 */
	public HBox getBox()
	{
	    return teamBox;
	}
	
	/**
	 * get the runner up for this team
	 * @return the runner up
	 */
	public Team getRunnerUp()
	{
	    return runnerup;
	}
	
	/**
	 * sets the runner up for this team
	 * @param r - the runner up
	 */
	public void setRunnerUp(Team r)
    {
        runnerup = r;
    }
	
	/**
	 * gets the score field for this team
	 * @return the score feild
	 */
	public TextField getScoreField()
	{
	    return scoreField;
	}

}
