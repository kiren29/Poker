package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Comparator;

import poker.version_graphics.model.Card.Rank;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    

    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
    	boolean found = false;

        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
            	for (int k = j+1; k < cards.size() && !found; k++) {

                if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(i).getRank() == cards.get(k).getRank()) 

                	found = true;
            	}
            }
        }
        return found;
    }
    
    public static boolean isStraight(ArrayList<Card> cards) {
    	int counter = 0;
    	boolean found = false;
    	
    	cards.sort(Comparator.comparing(Card::getRank));
    	
    	 for (int i = 0; i < cards.size()-1; i++) {
             if (cards.get(i).getRank().compareTo(cards.get(i + 1).getRank()) == -1) {
                 counter++;
                 if (counter == 4) {
                     found = true;
                 }
             }
        }
        return found;
     }
    
    public static boolean isFlush(ArrayList<Card> cards) {
    	boolean found = false;
    	for (int i = 0; i < cards.size() - 1 && !found; i++) {
    		for (int j = i+1; j < cards.size() && !found; j++) {
    			for (int h = j+1; h < cards.size() && !found; h++) {
    				for (int k = h+1; k < cards.size() && !found; k++) {
    					for (int l = k+1; l < cards.size() && !found; l++) {
    						if (cards.get(i).getSuit() == cards.get(j).getSuit() && cards.get(j).getSuit()
    								== cards.get(h).getSuit() && cards.get(h).getSuit() == cards.get(k).getSuit()
    								&& cards.get(k).getSuit()== cards.get(l).getSuit()) found = true;
    					}
    				}
    			}
    		}
    	}
        return found;
    }

    
    public static boolean isFullHouse(ArrayList<Card> cards) {
    	boolean threeOfAKindFound = false;
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        for (int i = 0; i < cards.size() - 2 && !threeOfAKindFound; i++) {
            for (int j = i + 1; j < cards.size() - 1 && !threeOfAKindFound; j++) {
                for (int k = j + 1; k < cards.size() && !threeOfAKindFound; k++) {
                    if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(k).getRank()) {
                        threeOfAKindFound = true;
                        clonedCards.remove(k);
                        clonedCards.remove(j);
                        clonedCards.remove(i);  
                    }
                }
            }
        }
        return threeOfAKindFound && isOnePair(clonedCards);
    }

    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {       

    	boolean found = false;

        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
            	for (int k = j+1; k < cards.size() && !found; k++) {
            		for (int l = k+1; l < cards.size() && !found; l++) {

                if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(i).getRank() == cards.get(k).getRank() && cards.get(i).getRank() == cards.get(l).getRank()) 
                	found = true;
            		}
            	}
            }
        }
		return found;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
    	boolean found = false;
    	if(isStraight(cards)&& isFlush(cards)) {
        	found = true;
        }
        return found;
    }
}

