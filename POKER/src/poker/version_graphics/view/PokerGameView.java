package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
    private int NUM_PLAYERS = 2;
	private Label winnerIs;
    private TilePane players;
    private ControlArea controls;
    
    private PokerGameModel model;
    
    public PokerGameView(Stage stage, PokerGameModel model) {
        this.model = model;
        
        winnerIs = new Label(" ");
        // Create all of the player panes we need, and put them into an HBox
        players = new TilePane();
        for (int i = 0; i < NUM_PLAYERS; i++){
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
        
        
        
        // Disallow resizing - which is difficult to get right with images
        stage.setResizable(true);
        
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

        if (winner.isEmpty() == true)

        winnerIs.setText("Tiebreak - Split pot");

        else

        for (Player myWinners : winner)

        winnerIs.setText("The Winner is "+myWinners.getPlayerName());

        }

        }
	

