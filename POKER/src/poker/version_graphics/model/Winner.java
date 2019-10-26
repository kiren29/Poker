package poker.version_graphics.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import poker.version_graphics.PokerGame;
import poker.version_graphics.view.PokerGameView;

public class Winner {
	
	private PokerGameModel model;
	private PokerGameView view;

	public Winner(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
	}
		
	public void getWinnerName() {
		Player w = null;
		Player p = model.getPlayer(0); 	
	for (int i = 1; i < PokerGame.NUM_PLAYERS; i++) {
		Player o = model.getPlayer(i);
	
		if (p.compareTo(o) == -1){
			w = model.getPlayer(i);
		}
		if (p.compareTo(o) == 1){
			w = p;
		}
		if (p.compareTo(o) == 0) {
			if(p.evaluateValues().ordinal() < o.evaluateValues().ordinal()) {
	   			 w = model.getPlayer(i);
	   			}
			if(p.evaluateValues().ordinal() > o.evaluateValues().ordinal()) {
	  			 w = p;
	  			}
		}
		if (w == null) {
			System.out.println("tiebreak");
		}
		else{
			System.out.println("Winner is " + (w.getPlayerName()));
		}  
		
	
	}
	
		}
}
