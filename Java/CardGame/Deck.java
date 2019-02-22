import java.util.Random;

public class Deck {
private Card[] cards = new Card[52];

public Deck() {
String[] suitValues = new String[4];
suitValues[0] = "Diamonds";
suitValues[1] = "Hearts";
suitValues[2] = "Clubs";
suitValues[3] = "Spades";
 for(int i = 0; i < 4; i++) {
  for(int j = 0; j < 13; j++) {
    cards[i*13+j] = new Card(j+1, suitValues[i]);
  }
 }
}

public void shuffle() {
Random rand = new Random();
int x = 0;
int y = 0;
Card tempVar;
for(int i = 0; i < 1000; i++) {
x = rand.nextInt(52);
y = rand.nextInt(52);
tempVar = cards[x];
cards[x] = cards[y];
cards[y] = tempVar;
}
}

public Card[] dealHand(int n, int playerNumber) {
Card[] handCards = new Card[n];
if((n*playerNumber) > 52) {
throw new IllegalArgumentException("Error: There are not enough cards in the deck.");
}

for(int i = 0; i < n; i++) {
handCards[i] = cards[n*(playerNumber-1)+i];
}

return handCards;
}
}