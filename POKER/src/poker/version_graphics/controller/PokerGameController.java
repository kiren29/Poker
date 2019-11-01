package poker.version_graphics.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.Card.Rank;
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
    protected int rounds;
    
    ArrayList<Player> winner = new ArrayList<Player>();

    ArrayList<Player> clonedList = new ArrayList<Player>();
    Rank [] ranksP = new Rank [Player.HAND_SIZE];
    Rank [] ranksO = new Rank [Player.HAND_SIZE];
    int counter = 0;
    
    
    public PokerGameController(PokerGameModel model, PokerGameView view) {
        this.model = model;
        this.view = view;
        
        view.getShuffleButton().setOnAction( e -> shuffle() );
        view.getDealButton().setOnAction( e -> deal() );
        view.getAddPlayerButton().setOnAction(e -> addPlayer());
    }
    
    private void addPlayer() { // SetOnAction Add Player Button ruft view auf
    	if(PokerGame.NUM_PLAYERS<6) {
    		PokerGame.increaseNumPlayers();
    		view.addPlayerPane();
    		}       
    }
    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
        for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            p.discardHand();
            PlayerPane pp = view.getPlayerPane(i);
            pp.updatePlayerDisplay();
        
        model.getDeck().shuffle();
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
        evaluateWinner();
        System.out.println(counter);
        
        for (Player myWinners : winner)
        view.updateWinnerDisplay(winner);

        for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
        	PlayerPane pp = view.getPlayerPane(i);
        	pp.getRoundsWon().setText(Integer.toString(model.getPlayer(i).getRoundsWon()));
        }
    }
        
        private ArrayList<Player> evaluateWinner() {
        winner.clear();
        winner.add(model.getPlayer(0));
        int counter = 0;
        for (int i = 1; i < PokerGame.NUM_PLAYERS; i++) {

             Player p = winner.get(0);
             Player o = model.getPlayer(i);

             if (p.compareTo(o) < 0){
                   if (winner.size()>1) {
                	   winner.clear();

                          counter=0;
                   }
                   else{
                          winner.remove(0);
                   }
                   winner.add(model.getPlayer(i));
             }
             if (p.compareTo(o) > 0){
             }
             if (p.compareTo(o) == 0) {
                   counter++;
                   winner.add(model.getPlayer(i));
             }
             System.out.println(p.compareTo(o));
             System.out.println(winner.get(0).getPlayerName());
        }
        
        if (counter > 0) {
        evaluateTiebreaks();
        }
        return winner;
        }
        
        public ArrayList<Player> evaluateTiebreaks(){
        if (winner.get(0).evaluateHand() == HandType.OnePair ||winner.get(0).evaluateHand() == HandType.ThreeOfAKind ||winner.get(0).evaluateHand() == HandType.FourOfAKind || winner.get(0).evaluateHand() == HandType.Straight) {
             clonedList = (ArrayList<Player>) winner.clone();
             winner.clear();
             winner.add(clonedList.get(0));
             for (int j = 1; j < clonedList.size(); j++) {
                   Player p1 = winner.get(0);
                   Player o1 = clonedList.get(j);
                      if(p1.evaluateValues().ordinal() < o1.evaluateValues().ordinal()) {
                             winner.remove(p1);
                             winner.add(o1);
                             System.out.println(winner);
                      }
                      if (p1.evaluateValues().ordinal() > o1.evaluateValues().ordinal()) {
                             System.out.println(winner);
                      }
                      if (p1.evaluateValues().ordinal() == o1.evaluateValues().ordinal()) {
                             winner.add(o1);
                             System.out.println(winner);
                      }
             }
        }

        if (winner.get(0).evaluateHand() == HandType.HighCard || winner.get(0).evaluateHand() == HandType.Flush) {
             clonedList = (ArrayList<Player>) winner.clone();
             winner.clear();
             winner.add(clonedList.get(0));
             for (int k = 1; k < clonedList.size(); k++) {
                   Player p1 = winner.get(0);
                   Player o1 = clonedList.get(k);                 

                   ranksP = p1.compareMoreThanOneValue();
                   ranksO = o1.compareMoreThanOneValue();
                   boolean foundWinner = false;
                   
                   for (int l = 0; l < Player.HAND_SIZE && !foundWinner; l++) {

                          if (ranksP[l].compareTo(ranksO[l]) < 0) {
                          System.out.println(ranksP[l]+ "  " + ranksO[l]);
                          foundWinner = true;
                                winner.remove(p1);
                                winner.add(o1);
                          }
                          if (ranksP[l].compareTo(ranksO[l]) > 0) {
                                foundWinner = true;
                          }
                         }
                        if (!foundWinner) {
                               winner.add(o1);
                   }
             }   
        }

        if (winner.get(0).evaluateHand() == HandType.TwoPair || winner.get(0).evaluateHand() == HandType.FullHouse) {
                  clonedList = (ArrayList<Player>) winner.clone();

             winner.clear();
             winner.add(clonedList.get(0));
            

             for (int k = 1; k < clonedList.size(); k++) {
                   Player p1 = winner.get(0);
                   Player o1 = clonedList.get(k);          

                   ranksP = p1.compareMoreThanOneValue();
                   ranksO = o1.compareMoreThanOneValue();

                   boolean foundWinner = false;
                   for (int l = 1; l < Player.HAND_SIZE && !foundWinner; l=+2) {

                          if (ranksP[l].compareTo(ranksO[l]) < 0) {
                          System.out.println(ranksP[l]+ "  " + ranksO[l]);
                          foundWinner = true;
                                winner.remove(p1);
                                winner.add(o1);
                          }

                          if (ranksP[l].compareTo(ranksO[l]) > 0) {

                                foundWinner = true;
                          }
                         }
                        if (!foundWinner) {
                               winner.add(o1);
                        }
             	}
         } 
        return winner;
        }
}