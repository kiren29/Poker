package poker.version_graphics.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import poker.version_graphics.model.DeckOfCards;

public class ControlArea extends HBox{
    private DeckLabel lblDeck = new DeckLabel();
    private Region spacer = new Region(); // Empty spacer
    Label lblRounds = new Label("Rounds: 0");
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    Button btnPlayer = new Button("Add Player");
        

    public ControlArea() {
        super(); // Always call super-constructor first !!
        
        this.getChildren().addAll(lblDeck, spacer, btnShuffle, btnDeal, btnPlayer);

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
    }
    
    public void linkDeck(DeckOfCards deck) {
        lblDeck.setDeck(deck);
    }
}

