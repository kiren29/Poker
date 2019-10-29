package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Comparator;

import poker.version_graphics.model.Card.Rank;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public void addPlayer() {
        

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

   

        if (handType == HandType.HighCard) {

               rank = cards.get(4).getRank();

        }

        if (handType == HandType.OnePair) {

               for (int i = 0; i < cards.size()-1 && rank == null; i++) {

                   if (cards.get(i).getRank().compareTo(cards.get(i + 1).getRank()) == 0) {

                      rank= cards.get(i).getRank();

                   }

               }

        }

        // noch definieren: if (handType == HandType.TwoPair){}

   

        if (handType == HandType.ThreeOfAKind) {

               for (int i = 0; i < cards.size()-1 && rank == null; i++) {

                  if (cards.get(i).getRank().compareTo(cards.get(i + 1).getRank()) == 0) {

                     rank= cards.get(i).getRank();

                  }

               }

        }

        if (handType == HandType.Straight) {

               rank = cards.get(4).getRank();

        }

        if (handType == HandType.Flush) {

               rank = cards.get(4).getRank();

               //nächsthöhere vergleichen - alle fünf

        }

        // noch definieren: if (handType == HandType.FullHouse){}

   

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

        return rank;
    }

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
        return handType.compareTo(o.handType);
    }

	public int getRoundsWon() {
		
		return 0;
	}

	
	}


