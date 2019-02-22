public class SeasonalFruit extends Fruit {
  public SeasonalFruit(String productName, double weight, int price) {
  super(productName, weight, price);
  }

  public int getCost() {
  double cost = 0;
  cost = (double)((double)super.getCost()*0.85);
  return (int) cost;
  }
}