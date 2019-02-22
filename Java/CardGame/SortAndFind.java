import java.util.Random;

public class SortAndFind {

  public static int[][] generateRandomMatrix(int m, int n) {
  int[][] intArray = new int[m][n];
  Random rand = new Random();
  int x = 0;
  for(int i = 0; i < m; i++) {
    for(int j = 0; j < n; j++) {
    x = rand.nextInt(50);
    intArray[i][j] = x;
    }
  }
  return intArray;
  }
  
  public static void displayMatrix(int[][] intArray) {
    for(int i = 0; i < intArray.length; i++) {
      System.out.printf("\n");
      for(int j = 0; j < intArray[i].length; j++) {
    System.out.printf("%d\t",intArray[i][j]);
      }
    }
  }
  
  public static void sortOneRow(int[] intArray) {
    boolean swapped = true;
    int min = intArray[0];
    int minIndex = 0;
    int sortPos = 0;
    int i=0;
    while (swapped == true) {
    for(i = sortPos; i < intArray.length; i++) {
      if(intArray[i] <= min) {
        min = intArray[i];
        minIndex = i;
      }
    }
    if (minIndex >= sortPos) {
      intArray[minIndex] = intArray[sortPos];
      intArray[sortPos] = min;
      sortPos++;
      min = intArray[sortPos];
    }
    if (sortPos >= intArray.length-1) {
      swapped = false;
    }
  }
  }
  
  public static void sortOneColumn(int[][] intArray, int column) {
   int[] oneColumn = new int [intArray.length];
   for(int i = 0; i < intArray.length; i++) {
   oneColumn[i] = intArray[i][column];
   }
   sortOneRow(oneColumn);
   for(int j = 0; j < intArray.length; j++) {
   intArray[j][column] = oneColumn[j];
   }
  }
  
  public static void sortMatrix(int[][] intArray) {
    for(int i = 0; i < intArray.length; i++) {
      sortOneRow(intArray[i]);
      }
    
   for(int j = 0; j < intArray[0].length; j++) {
    sortOneColumn(intArray, j);
    }
    }
  
  public static int[] findElement(int[][] intArray, int n) {
    int i = 0;
    int j = 0;
    //int count = intArray[i][0];
  int[] twoIntArray = new int[2];
  for(i = 0; i < intArray.length; i++) {
    for(j = 0; j < intArray[i].length; j++) {
      if(intArray[i][j] == n) {
      twoIntArray[0] = i;
      twoIntArray[1] = j;
      return twoIntArray;
      }
      if (intArray[i][j] > n)
      {
        break;
      }
      /*if(n < count) {
      count++;
      if(count == n) {
      twoIntArray[0] = i;
      twoIntArray[1] = j;
      return twoIntArray;
      }
      }
      if(n > count) {
      count--;
      if(count == n) {
      twoIntArray[0] = i;
      twoIntArray[1] = j;
      return twoIntArray;
      }
      }*/
    }
    
  }
    twoIntArray[0] = -1;
    twoIntArray[1] = -1;
    return twoIntArray;
  }
   
  
  public static void main(String[] args) {
  int m = 6;
  int n = 8;
  int[][] matrix = generateRandomMatrix(m, n);
  displayMatrix(matrix);
  sortMatrix(matrix);
  System.out.println(" ");
  System.out.println("--------------------------------------------------------------------");
  displayMatrix(matrix);
  System.out.println(" ");
  int[] secondMatrix = new int[2];
  secondMatrix = findElement(matrix, 5);
  System.out.println("The element 5 is in position" + "[" + secondMatrix[0] + ", " + secondMatrix[1] + "]" + "."); 
  }
}