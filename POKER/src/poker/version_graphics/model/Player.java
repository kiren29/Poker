package poker.version_graphics.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import poker.version_graphics.model.Card.Rank;
 

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    public Rank [] ranks = new Rank [HAND_SIZE];
    private int roundsWon;
    private boolean winsRound;
    private int roundsLost;
 
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }

    public void discardHand() {
        cards.clear();
        handType = null;
    }

    public int getNumCards() {
        return cards.size();
    }

    /**
     * If the hand has not been evaluated, but does have all cards,
     * then evaluate it.
     */

    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }

    public Rank evaluateValues() {
        Rank rank = null;
        cards.sort(Comparator.comparing(Card::getRank));
        if (handType == HandType.OnePair) {
               for (int i = 0; i < cards.size()-1 && rank == null; i++) {
                   if (cards.get(i).getRank().compareTo(cards.get(i + 1).getRank()) == 0) {
                      rank= cards.get(i).getRank();
                   }
               }
        }
 
        if (handType == HandType.ThreeOfAKind) {
               for (int i = 0; i < cards.size()-1 && rank == null; i++) {
                  if (cards.get(i).getRank().compareTo(cards.get(i + 1).getRank()) == 0) {
                     rank = cards.get(i).getRank();
                  }
               }
        }

        if (handType == HandType.Straight) {
               rank = cards.get(4).getRank();
        }

        if (handType == HandType.FourOfAKind) {
               for (int i = 0; i < cards.size()-1 && rank == null; i++) {
                     if (cards.get(i).getRank().compareTo(cards.get(i + 1).getRank()) == 0) {
                        rank= cards.get(i).getRank();
                     }
               }
        }

        if (handType == HandType.StraightFlush) {
               rank = cards.get(4).getRank();
        }

        if (handType == HandType.RoyalFlush) {
            rank = cards.get(4).getRank();
     }
        return rank;
    }

    public Card.Rank[] compareMoreThanOneValue() {
        Rank rank1 = null;
        Rank rank2 = null;
        Rank rank3 = null;
        Rank rank4 = null;
        Rank rank5 = null;
        cards.sort(Comparator.comparing(Card::getRank));
               rank1 = cards.get(4).getRank();
               rank2 = cards.get(3).getRank();
               rank3 = cards.get(2).getRank();
               rank4 = cards.get(1).getRank();
               rank5 = cards.get(0).getRank();

              if (handType == HandType.OnePair) {
            	  Card pairCard1 = null;
            	  Card pairCard2 = null;
                  ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
            	        // Find the pair; if found, remove the cards from the list
                  boolean firstPairFound = false;
            	  for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            		  for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
            			  if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
            				  firstPairFound = true;
            				  pairCard1 = clonedCards.get(j);
            				  pairCard2 = clonedCards.get(i);
            				  
            	              clonedCards.remove(j);  // Remove the later card
            	              clonedCards.remove(i);  // Before the earlier one
            	              }
            	           }
            	  }
            	  
                  rank1 = pairCard1.getRank();
                  rank2 = pairCard2.getRank();
                  rank3 = clonedCards.get(2).getRank();
                  rank4 = clonedCards.get(1).getRank();
                  rank5 = clonedCards.get(0).getRank();           	            	  
              }
              
              if (handType == HandType.FullHouse) {
            	  Card trioCard1 = null;
            	  Card trioCard2 = null;
            	  Card trioCard3 = null;
                  ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

                  boolean threeOfAKindFound = false;
                  for (int i = 0; i < cards.size() - 2 && !threeOfAKindFound; i++) {
                      for (int j = i + 1; j < cards.size() - 1 && !threeOfAKindFound; j++) {
                          for (int l = j + 1; l < cards.size() && !threeOfAKindFound; l++) {
                              if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(l).getRank()) {
                                  threeOfAKindFound = true;

            				  trioCard1 = clonedCards.get(j);
            				  trioCard2 = clonedCards.get(i);
            				  trioCard3 = clonedCards.get(l);
                              clonedCards.remove(l);
                              clonedCards.remove(j);
                              clonedCards.remove(i); 
            	              }
            	           }
            	  } 
            	  
                  rank1 = trioCard1.getRank();
                  rank2 = trioCard2.getRank();
                  rank3 = trioCard3.getRank();
                  rank4 = clonedCards.get(1).getRank();
                  rank5 = clonedCards.get(0).getRank();             	  
                  }          	  
              }
               Rank [] ranks = {rank1, rank2, rank3, rank4, rank5};
        return ranks;
        }

    /**
     * Hands are compared, based on the evaluation they have.
     */

    @Override

    public int compareTo(Player o) {
              return handType.compareTo(o.handType);
    }

    public int getRoundsWon() {	
    	return roundsWon;
    }
    
    public int getRoundsLost() {
    	return roundsLost;
    }
    
	public int setRoundsWon(ArrayList<Player> winner) {
		if (winner.contains(this)) {
			this.roundsWon++;
		}
		else {
			this.roundsLost++;
		}
		return roundsWon;
	}
	public void setRoundWinner(ArrayList<Player> winner) {
		winsRound = false;
		if (winner.contains(this)) {
		winsRound = true;
		}
	}
	public boolean getRoundWinner() {
		return winsRound;
	}
}