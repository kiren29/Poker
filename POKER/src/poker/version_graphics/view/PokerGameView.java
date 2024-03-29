package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.animation.RotateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private Label winnerIs;
    private TilePane players;
    private ControlArea controls;
    private PokerGameModel model;
    
    public PokerGameView(Stage stage, PokerGameModel model) {
        this.model = model;
        
        winnerIs = new Label(" ");
        // Create all of the player panes we need, and put them into an HBox
        players = new TilePane();
        players.setPrefColumns(2);
        players.prefHeightProperty().bind(players.heightProperty());
        for (int i = 0; i < PokerGame.NUM_PLAYERS; i++){
            PlayerPane pp = new PlayerPane();
            pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
            players.getChildren().add(pp);
            
        }
        // Create the control area
        controls = new ControlArea();
        controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
        
        // Put players and controls into a BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(players);
        root.setBottom(controls);
        root.setTop(winnerIs);
        winnerIs.setId("WinnerIs");
        
        root.setId("background"); //in CSS
        
        
        // Disallow resizing - which is difficult to get right with images
        stage.setResizable(true);
        stage.setHeight(700);
        stage.setWidth(1180);
        
       
       
        
        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();        
    }
    public void addPlayerPane() {  // Method for "Add Player" Button
    	PlayerPane pp = new PlayerPane();
		pp.setPlayer(model.addPlayer());
		players.getChildren().add(pp);
	}

    public PlayerPane getPlayerPane(int i) {
        return (PlayerPane) players.getChildren().get(i);
    }
    
    public Button getShuffleButton() {
        return controls.btnShuffle;
    }
    
    public Button getDealButton() {
        return controls.btnDeal;
        
    }
    public Button getAddPlayerButton() {
        return controls.btnPlayer;
    }
    public void updateWinnerDisplay(ArrayList<Player> winner) {
    	      if (winner.size() == 2)
    	             winnerIs.setText("Tiebreak - Split pot between: \n"+winner.get(0).getPlayerName()+" & "+winner.get(1).getPlayerName());
    	      if (winner.size() == 3)
    	             winnerIs.setText("Tiebreak - Split pot between: \n"+winner.get(0).getPlayerName()+" & "+winner.get(1).getPlayerName()+" & "+winner.get(2).getPlayerName());
    	      if (winner.size() == 4)
    	             winnerIs.setText("Tiebreak - Split pot between: \n"+winner.get(0).getPlayerName()+" & "+winner.get(1).getPlayerName()+" & "+winner.get(2).getPlayerName()+" & "+winner.get(3).getPlayerName());
	 
    	        else
    	        for (Player myWinners : winner)
    	             if (winner.size() == 1)
    	                   winnerIs.setText("The "+winner.get(0).getPlayerName()+" is WINNER of this round!");	
    	      
    }
}