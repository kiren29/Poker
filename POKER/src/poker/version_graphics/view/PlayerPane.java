package poker.version_graphics.view;

import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private Region spacer1 = new Region();
    private Label winsRound = new Label();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");
    private Label RoundsWon = new Label("Wins: 0 \n" +"Loses: 0");
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class
        
        // Add child nodes
        HBox h1 = new HBox();
        h1.getChildren().addAll(lblName, spacer1, RoundsWon);
        HBox.setHgrow(spacer1, Priority.ALWAYS); 
        this.getChildren().addAll(h1,winsRound,hboxCards, lblEvaluation);
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }
        lblName.setId("lblName");
    }
    public void setPlayer(Player player) {
        this.player = player;
        updatePlayerDisplay(); // Immediately display the player information
    }
    
    public void updatePlayerDisplay() {
        lblName.setText(player.getPlayerName());
        for (int i = 0; i < Player.HAND_SIZE; i++) {
            Card card = null;
            if (player.getCards().size() > i) card = player.getCards().get(i);
            CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
            cl.setCard(card);
            HandType evaluation = player.evaluateHand();
            winsRound.setText(null);
            if (evaluation != null) {
                lblEvaluation.setText(evaluation.toString());
            	RoundsWon.setText("Wins: " + player.getRoundsWon()+"\nLoses: "+ player.getRoundsLost());
            	if (player.getRoundWinner() == true) {
            	lblEvaluation.setText(evaluation.toString());
            	winsRound.setId("WinsRound");
            	winsRound.setText(" WINNER");
            	winsRound.setId("Winner");
            	}
        	}else
                lblEvaluation.setText("--");
            
            RotateTransition rc = new RotateTransition(Duration.millis(600),cl);//Rotates Cards 
            rc.setFromAngle(300);
            rc.setToAngle(360);
            rc.setAxis(Rotate.Y_AXIS);
            rc.play();
        }
    }
}