public class Jam extends MarketProduct {
private int numberJars;
private int price;

public Jam(String productName, int jarNumber,int jarPrice) {
super(productName);
numberJars = jarNumber;
price = jarPrice;
}

public int getCost() {
int cost = 0;
cost = numberJars*price;
return cost;
}

  public boolean equals(Object a) {
    if(a instanceof Jam) {
    Jam jam = (Jam)a;
    return ((this.getName()).equals(jam.getName())) && ((this.numberJars) == (jam.numberJars)) && ((this.price) == (jam.price));
    }
    return false;
  }
}