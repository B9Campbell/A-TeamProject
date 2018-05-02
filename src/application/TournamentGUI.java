package application;
	
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

public class TournamentGUI extends Application {
	
	static String fileName;
	
	public static void main(String[] args) {
		fileName = args[0];
		launch(args);
	}
    
    public static Tournament tournament;
	@Override
	public void start(Stage primaryStage) {
		ArrayList<String> teams = getTeamList();
		try {
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			tournament = new Tournament();
			tournament.teamNames = teams;
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
			
			scroll.setContent(grid);
			root.setCenter(scroll);
			root.setTop(header);
			
			root.setMargin(scroll, new Insets(20,20,20,20));
			
			Scene scene = new Scene(root,650,950);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> getTeamList() {
		ArrayList<String> teams = null;
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String team;
			while((team = br.readLine()) != null){
				teams.add(team);
				System.out.println(team);
			}
		}catch(FileNotFoundException fnf){
			System.out.println("No file found");
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		return teams;
	}
	
}
