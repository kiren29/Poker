package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
    private final ArrayList<Player> players = new ArrayList<>();
    private DeckOfCards deck;
    private int rounds = 0;
    
    public PokerGameModel() {
    	super(); // Always call super-constructor first !!
       
        for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
            players.add(new Player("Player " + (i+1)));         
        }        
        setDeck(new DeckOfCards());    
    }
    
    public Player getPlayer(int i) {
        return players.get(i);
    }
    
    public DeckOfCards getDeck() {
        return deck;
    }

	public void setDeck(DeckOfCards deck) {
		this.deck = deck;
	}
	public Player addPlayer() { // Button Add Player - to fit the Player Numbers
		players.add(new Player("Player " + (players.size()+1)));
		return players.get(players.size()-1);
	}
}
