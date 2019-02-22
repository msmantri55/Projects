public class Card {
private String suit;
private int value;

public Card(int cardValue, String cardSuit) {
suit = cardSuit;
value = cardValue;
if((value < 1) || (value > 13)) {
throw new IllegalArgumentException("Error: The value of the card must be between 1 and 13.");
}
}

public String getSuit() {
return (this.suit);
}

public int getValue() {
return (this.value);
}

public void print() {
System.out.println(value + " " + "of" + " " + suit + ".");
}
}