public class BridgeUtilities {
  
  public BridgeUtilities() {
  }
 
  private int countValue(Card[] card, int cardValue) {
  int numCards = 0;
  for(int i = 0; i < card.length; i++) {
    if(card[i].getValue() == cardValue) {
    numCards++;
    }
  }
  return numCards;
  }
  
  private int countSuit(Card[] card, String cardSuit) {
  int numCards = 0;
  for(int j = 0; j < card.length; j++) {
    if(card[j].getSuit().equalsIgnoreCase(cardSuit)) {
    numCards++;
    }
  }
  return numCards;
  }
  
  public int countPoints(Card[] card) {
  int hcp = 0;
  int dist = 0;  
  int cardValue = 0;
  int numPoints = 0;
  String[] cardSuit = new String[4];
  cardSuit[0] = "Diamonds";
  cardSuit[1] = "Hearts";
  cardSuit[2] = "Clubs";
  cardSuit[3] = "Spades";
  for(int i = 0; i < 4; i++) {
    
    if(countSuit(card, cardSuit[i]) > 4) {
    dist = dist + countSuit(card, cardSuit[i]) - 4;
    }
  }
  for(int j = 0; j < card.length; j++) {
    cardValue = card[j].getValue();
  if(cardValue == 1) {
    hcp = hcp + 4;
    }
    if(cardValue == 13) {
    hcp = hcp + 3;
    }
    if(cardValue == 12) {
    hcp = hcp + 2;
    }
    if(cardValue == 11) {
    hcp = hcp + 1;
    }
  }
  numPoints = hcp + dist;
  return numPoints;
  }
}