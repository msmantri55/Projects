public class SlotMachine {

  public static int diceRoll() {
  return (int)Math.ceil(Math.random()*6); 
  }
  
  public static String getSymbol(int number) {
    if(number == 1) {
    return "Cherries";
    }
    if(number == 2) {
    return "Oranges";
    }
    if(number == 3) {
    return "Plums";
    }
    if(number == 4) {
    return "Bells";
    }
    if(number == 5) {
    return "Melons";
    }
    if(number == 6) {
    return "Bars";
    }
    return "ERROR";
  }
  
  public static int getMultiplier(String symbol1, String symbol2, String symbol3) {
    if(symbol1.equals("Bells") && symbol2.equals("Bells") && symbol3.equals("Bells")) {
    return 5;
    }
    if(((symbol1.equals(symbol2) == true) && (symbol1.equals(symbol3) == true) && (symbol2.equals(symbol3) == true))  && ((symbol1 != "Bells") && (symbol2 != "Bells") && (symbol3 != "Bells"))) {
    return 3;
    }
    if(((symbol1.equals(symbol2) == true) && (symbol1.equals(symbol3) == true) && (symbol2.equals(symbol3) == false)) || ((symbol1.equals(symbol2) == true) && (symbol1.equals(symbol3) == false) && (symbol2.equals(symbol3) == true)) || ((symbol1.equals(symbol2) == false) && (symbol1.equals(symbol3) == true) && (symbol2.equals(symbol3) == true))) {
    return 2;
    }
    return 0;
  }
  
  public static boolean canPlay(double pocket, double roundBet) {
    if((pocket > roundBet) && (roundBet > 0)) {
    return true;
    }
       return false;
  }
  
  public static boolean goalReached(double pocket, double goal) {
    if(pocket >= goal) {
    return true;
    }
    return false;
  }
  
  public static void displaySymbols(String symbol1, String symbol2, String symbol3) {
  System.out.println(symbol1 + " " + symbol2 + " " + symbol3);
  }
  
  public static String formatMoney(double m) {
  return String.format("$%.2f", m);
  }
  
  public static void playMachine(double money, double roundBet, double goal) {
    if(canPlay(money, roundBet) == false) {
    System.out.println("You do not have enough money. The game can not be played.");
    return;
    }
    
    if(goalReached(money, goal) == true) {
    System.out.println("Your goal has been reached. No further gambling is allowed.");
    return;
    }
    String symbol1 = "";
    String symbol2 = "";
    String symbol3 = "";
    int count = 0;
    while((canPlay(money, roundBet) == true) && (goalReached(money, goal)) == false) {
    count++;
    System.out.println("You are now playing round " + count + ".");
    money = money - roundBet;
    symbol1 = getSymbol(diceRoll());
    symbol2 = getSymbol(diceRoll());
    symbol3 = getSymbol(diceRoll());
    displaySymbols(symbol1, symbol2, symbol3);
    money = money + roundBet * getMultiplier(symbol1, symbol2, symbol3);
    formatMoney(money);
    System.out.println("Your current money is " + money + ".");
    }
    if(money < goal) {
      System.out.println("Your goal was not reached. You lost the game.");
      formatMoney(money);
        System.out.println("You now have " + money + " remaining.");
        return;
    }
    if(money >= goal) {
    System.out.println("Your goal was reached. You won the game!");
    formatMoney(money);
    System.out.println("You now have " + money + " remaining.");
    return;
    }
    
    }
  
  public static void main (String[] args) {
  double money = 2.0;
  double roundBet = 5.0;
  double goal = 20.0;
  playMachine(money, roundBet, goal);
  }
}
