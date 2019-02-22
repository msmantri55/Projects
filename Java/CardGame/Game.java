public class Game{
  public static void main(String[] args) {
  Deck deck = new Deck();
  deck.shuffle();
  Card[][] cardHands = new Card[4][13];
  int handValue = 0;
  
  for(int i = 0; i < 4; i++) {
    cardHands[i] = deck.dealHand(13, i+1);
  }
  
  BridgeUtilities bridgeUtilities = new BridgeUtilities();
  for(int j = 0; j < 4; j++) {
   System.out.println("Player " + (j+1) + " was dealt the following hand of cards:");
    for(int k = 0; k < 13; k++) {
         System.out.println("Player " + (j+1) + " " + cardHands[j][k].getSuit() + " " + cardHands[j][k].getValue());
    }
   handValue = bridgeUtilities.countPoints(cardHands[j]);
    System.out.println("Player " + (j+1) + " has " + handValue + " points.");
    }
  }
  
  
  
 }
