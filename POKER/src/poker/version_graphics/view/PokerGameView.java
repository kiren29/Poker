package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private Label winnerIs;
	private HBox players;
	private ControlArea controls;
	
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		winnerIs = new Label("Player X is the Winner!");

		
		// Create all of the player panes we need, and put them into an HBox
		players = new HBox();
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);
		}
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setTop(winnerIs);
		root.setCenter(players);
		root.setBottom(controls);
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();		
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
		if (winner.isEmpty() == true)
			winnerIs.setText("Tiebreak - Split pot");
		else 
    		for (Player myWinners : winner)
			winnerIs.setText("The Winner is "+myWinners.getPlayerName());
	}
	
}
