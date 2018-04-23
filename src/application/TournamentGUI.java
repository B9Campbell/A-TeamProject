package application;
	
import javafx.scene.control.Button;
import java.util.ArrayList;
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
	@Override
	public void start(Stage primaryStage) {
		try {
		    
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			Tournament tournament = new Tournament();
			
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

			        grid.add(new Label(" "), c, start, 1, 1);
	                grid.add(tournament.getMatch(r, c).getTeamHBox(1), c, start + 1, 1, 1);
	                grid.add(tournament.getMatch(r, c).getTeamHBox(2), c, start + 2, 1, 1);
	                grid.add(new Label(" "), c, start + 3, 1, 1);
	                
	                
	                //add lines
	                if(c != bracket.size()-1) //don't put a line after the final!
	                {
                        if(r%2 == 0) //even ones should point down
                        {
                            grid.add(new ImageView(new Image("/corner_down.gif")), c+1, start + 1, 1, 2);
                            
                            for(int i = 0; i < c*c + c*2; i++) //add straight lines based on how far they need to go
                            {
                                grid.add(new ImageView(new Image("/straight.gif")), c+1, start+2 + i, 1, 3);
                            }
                        } else { //odd should point up
                            grid.add(new ImageView(new Image("/corner_up.gif")), c+1, start + 1, 1, 2);
                            
                            for(int i = 0; i < c*c + c*2; i++) //add straight lines based on how far they need to go
                            {
                                grid.add(new ImageView(new Image("/straight.gif")), c+1, start - 1 - i, 1, 3);
                            }
                        }
	                }
	                
	            }
			}
			
			//adding labels for the round numbers
			for(int c = 0; c < bracket.size()-1; c++)
            {
                grid.add(new Label("   Round " + (c+1)), c, 0, 1, 1);
            }
			grid.add(new Label("   Final Round") , bracket.size()-1, 0, 1, 1);
			
			//adds the "commit scores", and "reset scores" buttons
			//Buttons grow to the size of the text
			Button btn1 = new Button("Commit Score");
			Button btn2 = new Button("Reset Scores");
			HBox hBox = new HBox();
			HBox.setHgrow(btn1, Priority.ALWAYS);
			HBox.setHgrow(btn2, Priority.ALWAYS);
			hBox.getChildren().addAll(btn1,btn2);
			grid.add(hBox, 0, grid.impl_getRowCount(),2,1);
			
			
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
