public class Mountains {

  public static double getNextPoint(double height, double steepness, double maxHeight) {
    double randomHeight = (2*steepness*Math.random())-steepness;
    double newPoint = height + randomHeight;
    if(newPoint > maxHeight) {
    newPoint = maxHeight;
    }
    if(newPoint < 0) {
    newPoint = 0;
    }
   return newPoint;
  }
  public static double[] generateMountains(int numberOfPoints, double steepness, double maxHeight) {
  double[] pointHeights = new double[numberOfPoints];
  double previousHeight = 0;
  pointHeights[0] = maxHeight/2;
  for(int i = 1; i < numberOfPoints-1; i++) {
  previousHeight = pointHeights[i-1];
  pointHeights[i] = getNextPoint(previousHeight, steepness, maxHeight);
    
  }
  return pointHeights;
  }
  
  public static double findMaxHeight(double[] pointHeights) {
    double maxHeight = 0;
    if(pointHeights == null) {
    return 0;
    }
    maxHeight = pointHeights[0];
    for(int i = 1; i < pointHeights.length-1; i++) {
    if(pointHeights[i] > maxHeight) {
    maxHeight = pointHeights[i];
    }
    }
  return maxHeight;
  }
  
  public static void drawMountains(double[] heightOfPoints, String symbol) {
  int maxHeight = (int) findMaxHeight(heightOfPoints) + 1;
   for(int y = maxHeight-1; y >= 0; y--) {
    for(int x = 0; x < heightOfPoints.length-1; x++) {
      if(y == 0) {
      System.out.printf("-");
      }
      else if(y > heightOfPoints[x]) {
      System.out.printf(" ");
      }
      else if(((y - heightOfPoints[x]) < 1) && ((y - heightOfPoints[x]) > -1)) {
      System.out.printf("^");
      }
      else if(y < heightOfPoints[x]) {
      System.out.printf("%s", symbol);
      } 
    }
    System.out.printf("\n");
  }
 
  }
  
  public static void main(String[] args) {
  int numberOfPoints = 100;
  double steepness = 8;
  double maxHeight = 20;
  double myMountain[] = generateMountains(numberOfPoints, steepness, maxHeight);
  String symbol = "*";
  drawMountains(myMountain, symbol);
  }
}