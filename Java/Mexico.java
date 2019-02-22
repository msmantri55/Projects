public class Mexico {
  // Linking the variables from the other methods to here in the main method.
  public static void main (String [] args) {
  double buyIn = Double.parseDouble(args[0]);
  double bet = Double.parseDouble(args[1]);
  playMexico(buyIn, bet);
  }
  
  // diceRoll will return an integer and does not require any inputs
  public static int diceRoll () {
    // Declaring my number variables for the values on the dice.
    double n;
    int m;
    // Multiplying Math.random() by 6 so that I can reach the interval length needed.
    n = 6 * Math.random();
    // Adding 1 so that an integer of 6 can also be present.The (int) being used is known as typecasting. It is used to convert double to integer.
    m = (int) n + 1;
    return m;
  }
  public static int getScore (int score1, int score2) {
    // Declaring the variable combinedScore to denote the score when the two numbers are joined together.
    int combinedScore;
    // To get the combined score, we have to multiply the higher value on the dice by 10 and then add the lower value on the other dice.
    if (score2>score1) { combinedScore = 10*score2+score1; 
    return combinedScore;}  
    else { combinedScore = 10*score1+score2;
      return combinedScore;}
  }
  // playOneRound takes input for the player name and returns a combined integer
  public static int playOneRound (String player1) {
    // Declaring the variables for each number on the dice and the combinedScore. Then printing out statements that denote which score belongs to whom.
    int score1;
    int score2;
    int combinedScore;
    score1 = diceRoll();
    score2 = diceRoll();
    System.out.println(player1 +" rolled: " + score1 + " " + score2);
    combinedScore = getScore(score1, score2);
    System.out.println(player1 + "'s score is " + combinedScore);
    return combinedScore;
}
  public static String getWinner (String player1, String player2) {
  // Declaring all the score variables related to each player.
  int scorePlayer1;
  int scorePlayer2;
  scorePlayer1 = playOneRound(player1);
  scorePlayer2 = playOneRound(player2);
  // Outlining all the rules of the game through if statements.
  if (scorePlayer1 == 21 && scorePlayer2 == 21) {return "tie";}
  if (scorePlayer1 == 21) { 
    return player1;}
  if (scorePlayer2 == 21) {
    return player2;}
  // If a number has the same two digits, it is divisible by 11, hence why I am taking the modulus of 11 and checking whether it outputs 0 as a remainder or not.
  if (scorePlayer1 %11 == 0) {
    if (scorePlayer2 %11 == 0) {
      if (scorePlayer1>scorePlayer2) { return player1;}
      if (scorePlayer2>scorePlayer1) { return player2;}
      return "tie";
    }
   return player1;
  }
  // If a number has the same two digits, it is divisible by 11, hence why I am taking the modulus of 11 and checking whether it outputs 0 as a remainder or not.
 if (scorePlayer2 %11 == 0) {
    if (scorePlayer1 %11 == 0) {
      if (scorePlayer1>scorePlayer2) { return player1;}
      if (scorePlayer2>scorePlayer1) { return player2;}
      return "tie";
    }
   return player2;
  }    
 if (scorePlayer1 == scorePlayer2) {return "tie";}
 if (scorePlayer1 > scorePlayer2) {return player1;}
 return player2; 
}
  public static boolean canPlay (double buyIn, double bet) {
    // Using if statements to make the program print either true or false values, depending on the values of the buyIn and the bet.
    if (buyIn > 0 && buyIn > bet) {
   // Using a modulus function to see if the division between the buyIn and bet is equal to zero. This shows that buyIn is a multiple of bet.  
      if (buyIn%bet == 0) {return true;}}
      return false;
  }
  public static void playMexico (double buyIn, double bet) {
    // Denoting player1 as Guilia and player2 and David.
    String player1 = "Giulia";
    String player2 = "David";
    // Declaring the initial about of money that each player has. This is the value of the buyIn.
    double player1Money = buyIn;
    double player2Money = buyIn;
    // Declaring the variable for winner.
    String winner;
    int round = 0;
    // If the buyIn is not greather than or equal to th bet, the game can not proceed.
    if (canPlay(buyIn, bet) == false) {System.out.println("Can not play the game");
      return;}
    // This while loop is used to simulate the playing of the rounds until a winner emerges.
    // Both players need to have money greater than the bet value.
    while (player1Money >= bet && player2Money >= bet) {
    round++; 
    // Making the program denote the phrases for the rounds.
    System.out.println("----Round " + round + "-----");
    
    winner = getWinner (player1, player2);
    // Using if statements to assign the results to all different scenarios.
    if(winner == "tie") {System.out.println("The round is a tie...");}
    else {
      System.out.println("Winner of this round is " + winner);}
    if (winner == player1) {player2Money = player2Money - bet;}
    else { player1Money = player1Money - bet;}
    System.out.println("");
    }
    if(player1Money > player2Money) {System.out.println("Overall Winner is: " + player1);}
      else {System.out.println("Overall Winner is: " + player2);}
  }
}
 