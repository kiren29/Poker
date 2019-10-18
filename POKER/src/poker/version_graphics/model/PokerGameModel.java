package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	public static final int rounds = 10;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			players.add(new Player("Player " + i));
			
		}
		
		deck = new DeckOfCards();
	}
	public void PokerGameRounds() {
		for (int i = 0; i < rounds; i++) {
			players.add(new Player("Rounds: " + i));
		}
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
}
