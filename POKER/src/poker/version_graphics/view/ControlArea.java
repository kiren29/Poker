package poker.version_graphics.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import poker.version_graphics.model.DeckOfCards;

public class ControlArea extends HBox{
    private DeckLabel lblDeck = new DeckLabel();
    private Region spacer = new Region(); // Empty spacer
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    Button btnPlayer = new Button("Add Player");
<<<<<<< HEAD
=======
    protected int rounds = 0;
    Label round = new Label("Rounds:   " +rounds);
>>>>>>> branch 'master' of https://github.com/kiren29/Poker.git

    public ControlArea() {
    	super(); // Always call super-constructor first !!
    	
<<<<<<< HEAD
    	this.getChildren().addAll(lblDeck, spacer, btnShuffle, btnDeal, btnPlayer);
=======
    	this.getChildren().addAll(lblDeck, spacer, round, btnShuffle, btnDeal, btnPlayer);
>>>>>>> branch 'master' of https://github.com/kiren29/Poker.git

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
    }
    
    public void linkDeck(DeckOfCards deck) {
    	lblDeck.setDeck(deck);
    }
}
