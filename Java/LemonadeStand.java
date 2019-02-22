public class LemonadeStand {

  
  public static void printTotalSales(int numSales, double price) {
  double product = numSales * price;
  System.out.println("We sold " + numSales + " units at $" + price + " each, which totals $" + product + ".");
  }
  
  public static int divide(int first, int second) {
    int division = first/second;
    if(second == 0) {
    System.out.println("Error: cannot divide by 0.");
    }
   return division;
  }
  
  public static int getMaximum(int a, int b) {
    if(a > b) {
    return a;
    }
    if(a < b) {
    return b;
    }
    return a;
  }
  public static void standStats(int numDays, int numSales, double price) {
  printTotalSales(numSales, price);
  int salesPerDay = divide(numSales, numDays);
  System.out.println("With " + numSales + " sales over " + numDays + " days, the sales per day were " + salesPerDay + ".");
  int targetSales = getMaximum(salesPerDay, 10);
  System.out.println("The target sales are now: " + targetSales + ".");
  }
  public static void main(String[] args) {
    int days = 2;
    int sales = 37;
    double lemonadePrice = 2.25;
    standStats(days, sales, lemonadePrice);
  }
}
