package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private Label WinnerName = new Label();
    private Region spacer1 = new Region();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");

    private Label lblRoundsWon = new Label();
   
    

    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player");// CSS style class
       
        
        // Add child nodes

        HBox h1 = new HBox();
        h1.getChildren().addAll(lblName, spacer1);
        HBox.setHgrow(spacer1, Priority.ALWAYS); 
        this.getChildren().addAll( h1,hboxCards, lblEvaluation);
       
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }
    }
    public void setPlayer(Player player) {
    	this.player = player;
    	updatePlayerDisplay(); // Immediately display the player information
    }
    
    public void updatePlayerDisplay()
    {
        lblName.setText(player.getPlayerName());
        
        for (int i = 0; i < Player.HAND_SIZE; i++)
        {
            Card card = null;
            if (player.getCards().size() > i)
            {
                card = player.getCards().get(i);
            }
            CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
            cl.setCard(card);
            HandType evaluation = player.evaluateHand();
            if (evaluation != null)
            {
                lblEvaluation.setText(evaluation.toString());
                lblRoundsWon.setText(Integer.toString(player.getRoundsWon()));
            }
            else
            {
                lblEvaluation.setText("--");
            
            }
        }
        }

	public Label getLblRoundsWon()    {
        return lblRoundsWon;
    }
}
    



