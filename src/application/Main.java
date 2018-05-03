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
// 2018 May 2, 2018 9 PM Main.java 
//////////////////////////// 80 columns wide //////////////////////////////////

package application;
	
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Main extends Application {
	
	static String fileName;
    public static Tournament tournament;
	
	public static void main(String[] args) {
		if(args.length > 0)
		{
		    fileName = args[0];
		} else {
		    fileName = "none";
		}
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {

		try {
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			if(!fileName.equals("none")) //only try to read a file if one was passed in
			{
			    tournament = new Tournament(fileName);
			    
			    ArrayList<Match[]> bracket = tournament.getBracket();
	            GridPane grid = new GridPane();

	            
	            //grid.setGridLinesVisible(true);
	            
	            //adding the matched to the grid layout, this algorithm only works for the first two columns as you can see
	            int tableHeight = bracket.get(0).length*4;
	            for(int c = 0; c < bracket.size(); c++)
	            {
	                for(int r = 0; r < bracket.get(c).length; r++)
	                {
	                    //add matches
	                    int count = (tableHeight/bracket.get(c).length);
	                    int start = ((count/2) - 2) + r*count;
	                    
	                    int colStart = c*2;
	                    
	                    grid.add(new Label(" "), colStart, start, 1, 1);
	                    grid.add(tournament.getMatch(r, c).getTeamHBox(1), colStart, start + 1, 1, 1);
	                    grid.add(tournament.getMatch(r, c).getTeamHBox(2), colStart, start + 2, 1, 1);
	                    grid.add(new Label(" "), colStart, start + 3, 1, 1);
	                    
	                    grid.add(tournament.getMatch(r, c).getSubmit(), colStart+1, start + 1, 1, 2);
	                    
	                    //add lines
	                    if(c != bracket.size()-1) //don't put a line after the final!
	                    {
	                        if(r%2 == 0) //even ones should point down
	                        {
	                            grid.add(new ImageView(new Image("/corner_down.gif")), colStart+2, start + 1, 1, 2);
	                            
	                            for(int i = 0; i < 2*Math.pow(2, c)-2; i++) //add straight lines based on how far they need to go
	                            {
	                                grid.add(new ImageView(new Image("/straight.gif")), colStart+2, start+2 + i, 1, 2);
	                            }
	                        } else { //odd should point up
	                            grid.add(new ImageView(new Image("/corner_up.gif")), colStart+2, start + 1, 1, 2);
	                            
	                            for(int i = 0; i < 2*Math.pow(2, c)-2; i++) //add straight lines based on how far they need to go
	                            {
	                                grid.add(new ImageView(new Image("/straight.gif")), colStart+2, start - i, 1, 2);
	                            }
	                        }
	                    }
	                    
	                }
	            }
	            
	            //adding labels for the round numbers
	            for(int c = 0; c < bracket.size()-1; c++)
	            {
	                grid.add(new Label("   Round " + (c+1)), c*2, 0, 1, 1);
	            }
	            grid.add(new Label("   Final Round") , bracket.size()*2-2, 0, 1, 1);
	            
	            
	            //adding the header
	            Label header = new Label("Tournament Bracket");
	            header.setStyle("-fx-font: 36 arial;");
	            
	            //set content into windows
	            scroll.setContent(grid);
	            root.setCenter(scroll);
	            root.setTop(header);
	            
	            root.setMargin(scroll, new Insets(20,20,20,20));
	            
			} else {
			    root.setCenter(new Label("No file passed in for teams!"));
			}
			
			
			Scene scene = new Scene(root,650,950);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			if(tournament.getBracket().size() == 1 && tournament.getBracket().get(0).length == 0)
			{
			    Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Tournament Over");
                alert.setHeaderText("Not much of a tournament.");
                alert.setContentText("Champion: None" + "\nSecond Place: None" + "\nThirdPlace: None");
                alert.showAndWait();
			} else if(tournament.getBracket().size() == 1 && tournament.getBracket().get(0)[0].getTeam(2).getName().equals("None"))
			{
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Tournament Over");
                alert.setHeaderText("We have a winner!");
                alert.setContentText("Champion: " + tournament.getBracket().get(0)[0].getTeam(1).getName() + "\nSecond Place: None" + "\nThirdPlace: None");
                alert.showAndWait();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
