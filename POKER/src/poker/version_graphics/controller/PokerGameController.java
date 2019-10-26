package poker.version_graphics.controller;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.scene.control.Alert;
import java.util.Comparator;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.CardLabel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	private Label winnerIs;
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getAddPlayerButton().setOnAction(e -> addPlayer());
	}
	


    private void addPlayer() {
<<<<<<< HEAD
    	DeckOfCards deck = model.getDeck();

=======
    	for(int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = view.getPlayerPane(i);
			
>>>>>>> branch 'master' of https://github.com/kiren29/Poker.git
    	}
<<<<<<< HEAD
	


=======
			
    }
>>>>>>> branch 'master' of https://github.com/kiren29/Poker.git
	/**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	int rounds = 0;
    	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	
    	model.getDeck().shuffle();
    	rounds++;
    	}
    }
    
    /**}
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
 
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		pp.updatePlayerDisplay();
        		
        	}
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
<<<<<<< HEAD
    	
//evaluate winner
    	   	ArrayList<Player> winner = new ArrayList<Player>();
    	   	winner.add(model.getPlayer(0));

        	for (int i = 1; i < PokerGame.NUM_PLAYERS; i++) {
        	Player o = model.getPlayer(i);
        	Player p = winner.get(0);	 
        	if (p.compareTo(o) == -1){
        		winner.remove(0);
        		winner.add(model.getPlayer(i));
            	System.out.println("-1"+o.getPlayerName());	
        	}
        	if (p.compareTo(o) == 1){
        	System.out.println("1"+p.getPlayerName());	
        	}
        	if (p.compareTo(o) == 0) {
        		if(p.evaluateValues().ordinal() < o.evaluateValues().ordinal()) {
            		winner.remove(0);
            		winner.add(model.getPlayer(i));
           			}
        		if(p.evaluateValues().ordinal() > o.evaluateValues().ordinal()) {
          			 
          			}
        	}
        	if (winner.isEmpty() == true) {
        		System.out.println("tiebreak");
        		view.updateWinnerDisplay(null);
        	}
        	else{
        		for (Player myWinners : winner)
        		System.out.println("Winner is " + (myWinners.getPlayerName()));
        		view.updateWinnerDisplay(winner);
        	}  
        	
    	}
=======
    	    	
	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++)
    {
        PlayerPane pp = view.getPlayerPane(i);
        pp.getLblRoundsWon().setText(Integer.toString(model.getPlayer(i).getRoundsWon()));
>>>>>>> branch 'master' of https://github.com/kiren29/Poker.git
    }
<<<<<<< HEAD
}
=======
}

}

>>>>>>> branch 'master' of https://github.com/kiren29/Poker.git
