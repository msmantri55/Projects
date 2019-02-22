public class Fruit extends MarketProduct {
private double weight;
private int price;

public Fruit(String productName, double foodWeight, int foodPrice) {
super(productName);
weight = foodWeight;
price = foodPrice;
}

public int getCost() {
int cost = 0;
cost = (int) (weight*(double)price);
return cost;
}
  public boolean equals(Object f) {
    if(f instanceof Fruit) {
    Fruit fruit = (Fruit)f;
    return ((this.getName()).equals(fruit.getName())) && ((this.weight) == (fruit.weight)) && ((this.price) == (fruit.price));
    }
    return false;
  }
}