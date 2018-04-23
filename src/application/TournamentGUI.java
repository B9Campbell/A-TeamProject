package application;
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class TournamentGUI extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			Tournament tournament = new Tournament();
			
			ArrayList<Match[]> bracket = tournament.getBracket();
			GridPane grid = new GridPane();
			grid.setGridLinesVisible(true);
			
			for(int c = 0; c < bracket.size(); c++)
			{
			    for(int r = 0; r < bracket.get(c).length; r++)
	            {
			        int addition = c*(2+4*r);
			        System.out.println(addition);
			        grid.add(new Label(" "), c, (r*4) + addition, 1, 1);
	                grid.add(tournament.getMatch(r, c).getTeamHBox(1), c, (r*4 + 1) + addition, 1, 1);
	                grid.add(tournament.getMatch(r, c).getTeamHBox(2), c, (r*4 + 2) + addition, 1, 1);
	                grid.add(new Label(" "), c, (r*4 + 3) + addition, 1, 1);
	                
	            }
			}
			
			
			Label Header = new Label("Tournament Bracket");
			Header.setStyle("-fx-font: 36 arial;");
			
			scroll.setContent(grid);
			root.setCenter(scroll);
			root.setTop(Header);
			
			Scene scene = new Scene(root,400,400);
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
