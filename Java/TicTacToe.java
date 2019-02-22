// Importing java to use the scanner function in the getUserMove and play method.
import java.util.Scanner;
public class TicTacToe {
  // Linking the variables from the other methods to here in the main method.
  public static void main (String [] args) {
    play();
    
  }
  
  public static char[][]  createBoard (int n) {
    char[][] array = new char[n][n];
 // Delcaring the integer r to represent the rows and the integer c to represent the columns of the array.   
    for (int r = 0; r < n; r++) 
    {
      for (int c = 0; c < n; c++) {
        array[r][c] = ' ';
       }
    }
    return array;
  }
  
  public static void  displayBoard (char[][] board){
    // Declaring an integer for the dimension of the board, abbreviated as "dim". 
    // Using board.length to measure the length of each dimension of our array. 
    // We do not need to include square brackets because it is a square array with equal dimensions.
    int dim = board.length;
    // Declaring a string for the horizontal lines of the board.
    String line = "";
    // Building a line with the amount of +- equal to the number of rows/columns.
    for (int i = 0; i < dim; i++) {
      line += "+-";
    }
    // Adding an additional "+" sign to create the appropriate board.
    line += "+";
    // Printing out the first line.
    System.out.println(line);
    // Building the board with the contents of the vertical lines along with the vertical bars.
    for (int i = 0; i < dim; i++) {
      String row = "";
      for (int j = 0; j < dim; j++) {
      row += "|" + board[i][j];
      }
      // Adding an additional "|" sign to create the appropriate board.
      row += "|";
      // Printing the rows and the lines below.
      System.out.println(row);
      System.out.println(line);
    }
  
  }
  public static void writeOnBoard (char[][] board, char z, int x, int y) {
    // Using an if statement to print an error message when the number of rows exceeds or is equal to the board length. 
    // Otherwise, it will return so that the program stops.
    if(x >= board.length) {
      System.out.println ("Error: Input is larger than the dimension of the board");
      return;
  }
    // Using an if statement to print an error message when the number of columns exceeds or is equal to the board length.
    // Otherwise, it will return so that the program stops.
    if (y >= board.length) {
      System.out.println ("Error: Input is larger than the dimension of the board");
    return;
    }
    // Writing an if statement so that when the board already contains a value, print an error message.
    if(board[x][y] != ' ') {
      System.out.println ("Error: The position on the board contains a value");
      return;
      }
    board[x][y] = z;

}
  public static void getUserMove (char[][] board) {
    // Inserting the scanner function to read the user move input.
  Scanner read = new Scanner(System.in);
  // Declaring the variables for each of the respective moves.
  int moveX;
  int moveY;
  // Using a while loop so that the user can continue to enter in different mvoes until they enter a valid one.
  while (true){
  System.out.print("Please enter your move: ");
  // Creating a blank line so that the separate boards can be seen more clearly.
  System.out.println("                      ");
  moveX = read.nextInt();
  moveY = read.nextInt();
  if (moveX < board.length && moveY < board.length){
    if (board[moveX][moveY] != ' ') {
      System.out.println("Error: The positon on the board contains a value.");
      displayBoard(board);
    }
      // When the user has entered a valid input, the while loop breaks.
      else {
        board[moveX][moveY] = 'x';
        displayBoard(board);
        break;
      }
  }
   
  // Using if statements to explain why the game can not proceed if they enter a too large row or column number.
  if(moveX >= board.length) {
    System.out.println ("The entered row number is out of bounds. Please retry.");
  }
  
  if (moveY >= board.length) {
    System.out.println ("The entered column number is out of bounds. Please retry.");
   }
  }
  }
  
  public static boolean checkForObviousMove (char[][] board) {
    boolean result = false;
    // The first check is to see if the AI wins.
    result = myCheckForObviousMove (board, 'x');
    if (result == false) {
    // The second check is to see if the user/player is winning.
    result = myCheckForObviousMove (board, 'o');
    }
    return result;
  }
  // Creating an additional method that follows through with all the requirements of the checkForObviousMove method.
  // This method shortens the total code required.
   public static boolean myCheckForObviousMove (char[][] board, char z) {
    // Declaring the variables for the obvious moves in each of the rows or columns. 
    // These two will be the coordinates in the array that will be the obvious move.
    int obvMovRow = -1;
    int obvMovCol = -1;
    // Checking across all the rows.
    for (int r = 0; r < board.length; r++) {
    // Checking across all the columns in one row.
      for (int c = 0; c < board.length; c++) {
        // Checking whether or not the space does not contain an input character or contains nothing.
        if (board[r][c] == z) {
          obvMovRow = -1;
          obvMovCol = -1;
          break;
        }
        // Using an if statement to show that we have found a second empty space on the row.
        if (board[r][c] == ' ' && obvMovRow != -1 && obvMovCol != -1){
          obvMovRow = -1;
          obvMovCol = -1;
          break;
        }
        if (board[r][c] == ' ') {
            obvMovRow = r;
            obvMovCol = c;
          }
        // Using if statements to check that there is no 'x' and there is an empty space. 
        // There is one empty space because there will no longer be a -1 on the space.
        if (obvMovRow != -1 && obvMovCol != -1 && c == board.length - 1) {
           writeOnBoard(board, 'o', obvMovRow, obvMovCol);
           return true;
        }
        }
    }
    // Checking across all the columns.
     for (int c = 0; c < board.length; c++) {
    // Checking across all the rows in one column.
      for (int r = 0; r < board.length; r++) {
        // Checking whether or not the space does not contain an x or contains nothing.
        if (board[r][c] == z) {
          obvMovRow = -1;
          obvMovCol = -1;
          break;
        }
        // Using an if statement to show that we have found a second empty space on the row.
        if (board[r][c] == ' ' && obvMovRow != -1 && obvMovCol != -1){
          obvMovRow = -1;
          obvMovCol = -1;
          break;
        }
        if (board[r][c] == ' ') {
            obvMovRow = r;
            obvMovCol = c;
          }
        // Using if statements to check that there is no 'x' and there is an empty space. 
        // There is one empty space because there will no longer be a -1 on the space.
        if (obvMovRow != -1 && obvMovCol != -1 && r == board.length - 1) {
           writeOnBoard(board, 'o', obvMovRow, obvMovCol);
           return true;
        }
        }
      
    }
     // Checking across the diagonals.
     for (int r = 0; r < board.length; r++) {
     // Checking whether or not the space does not contain an x or contains nothing.
        if (board[r][r] == z) {
          obvMovRow = -1;
          obvMovCol = -1;
          break;
        }
        // Using an if statement to show that we have found a second empty space on the row.
        if (board[r][r] == ' ' && obvMovRow != -1 && obvMovCol != -1){
          obvMovRow = -1;
          obvMovCol = -1;
          break;
        }
        if (board[r][r] == ' ') {
            obvMovRow = r;
            obvMovCol = r;
          }
        // Using if statements to check that there is no 'x' and there is an empty space. 
        // There is one empty space because there will no longer be a -1 on the space.
        if (obvMovRow != -1 && obvMovCol != -1 && r == board.length - 1) {
           writeOnBoard(board, 'o', obvMovRow, obvMovCol);
           return true;
     }
       
  }
      // We are returning false because no obvious move has been found in the upper loops.
        return false;
  }
  public static void getAIMove (char[][] board) {
  int moveRow;
  int moveColumn;
  double randNum;
  // Checking whether the previous method outputs false, so that we know it is not an obvious move.
  if (checkForObviousMove(board) == false) {
    // Using an infinite while loop to generate random numbers until an empty spot is found.
    while (true) {
    // Generating random numbers to represent the different number entries.
    randNum = Math.random() * board.length;
    // Using typecasting to change the number from a double to an int.
    moveRow = (int) randNum;
    randNum = Math.random() * board.length;
    moveColumn = (int) randNum;
    if (board[moveRow][moveColumn] == ' ') {
     writeOnBoard(board, 'o', moveRow, moveColumn);
     // Breaking the while loop because we have found an empty spot.
     break;
     }
  }
  }
  }
  public static char checkForWinner (char[][] board) {
  char winner = ' ';
  // Checking across all the rows to find a winner.
   for (int r = 0; r < board.length; r++) {
    for (int c = 0; c < board.length; c++) {
      // Checking for a space because if there is a space, there can not be a winner on this row.
      if (board[r][c] == ' ') {
      winner = ' ';
      break;
      }
      // Checking to see that the previous character found matches the one found currently.
      if (winner != board[r][c] && winner != ' '){
        winner = ' ';
        break;
      }
      // Checking that winner is not a space and that we have reached the last position on the column to confirm that that we have a winner.
      if (winner != ' ' && c == board.length - 1) {
        return winner;
      }
       winner = board[r][c];
      }
    }
   // Checking across all the columns to find a winner.
   for (int c = 0; c < board.length; c++) {
    for (int r = 0; r < board.length; r++) {
      // Checking for a space because if there is a space, there can not be a winner on this column.
      if (board[r][c] == ' ') {
      winner = ' ';
      break;
      }
      // Checking to see that the previous character found matches the one found currently.
      if (winner != board[r][c] && winner != ' '){
        winner = ' ';
        break;
      }
      // Checking that winner is not a space and that we have reached the last position on the row to confirm that that we have a winner.
      if (winner != ' ' && r == board.length - 1) {
        return winner;
      }
      winner = board[r][c];
      }
   }
    // Checking across the diagonal to find a winner.
    for (int r = 0; r < board.length; r++) {
      // Checking for a space because if there is a space, there can not be a winner on this diagonal.
      if (board[r][r] == ' ') {
      winner = ' ';
      break;
      }
      // Checking to see that the previous character found matches the one found currently.
      if (winner != board[r][r] && winner != ' '){
        winner = ' ';
        break;
      }
      // Checking that winner is not a space and that we have reached the last position on the diagonal to confirm that that we have a winner.
      if (winner != ' ' && r == board.length - 1) {
        return winner;
      }
      winner = board[r][r];
      }
    return winner;
    }
  public static void play () {
  // Inserting the scanner function to receive inputs from the user.
  Scanner read = new Scanner(System.in);
  String name;
  System.out.print("Please enter your name:  ");
  name = read.nextLine();
  System.out.println("Welcome, " + name + "!" + " Are you ready to play?");
  int dim = 0;
  // Using a do while loop to make sure the user enters a correct move.
  do{
  System.out.print("Please choose a suitable dimension for your board:  ");
  dim = read.nextInt();
  // Setting dimension of 2 and under as unsuitable to play the game.
  }while(dim <= 2);
  char[][] board;
  board = createBoard(dim);
  int i = 0;
  double randNum;
  // Multiplying the random number by 2 so that the coin toss has the required 2 possibilities.
  randNum = Math.random() * 2;
  // Using if statements to assign random number values to the coin toss.
  if(randNum <= 1.0) {
    System.out.println("You have the first move    ");
  }
      else {
        System.out.println("The AI has the first move    ");
        getAIMove(board);
        i++; 
        displayBoard(board);
    }
      // Using a while loop to play the game until a winner emerges or there are no more spaces on the board.
      while(checkForWinner(board) == ' ' && i < dim*dim) {
      getUserMove(board);
      i++;
        // Putting it all in if statements so that we make sure we have a space to insert a random integer.
        if(i < dim*dim){
          System.out.println(" The AI's move is below.");
          getAIMove(board);
          i++;
          displayBoard(board);
          }
        }
      // Using if statements to display the final status of the game.
      if (checkForWinner(board) == 'x') {
        System.out.println("You won the game!");
      }
        if (checkForWinner(board) == 'o'){
          System.out.println("The AI won the game.");
        }
          if (checkForWinner(board) == ' '){
            System.out.println("The game is a tie.");
          }
      
  
  }
}