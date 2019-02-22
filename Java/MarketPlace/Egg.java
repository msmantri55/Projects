public class Egg extends MarketProduct {
  private int numEggs;
  private int priceEggs;
  
  public Egg(String productName, int numRequired, int priceEggsDozen) {
  super(productName); 
  numEggs = numRequired;
  priceEggs = priceEggsDozen;
  }
  
  public int getCost() {
  double cost = 0;
  cost = ((double)((double)numEggs/(double)12))*(double)priceEggs;
  return (int) cost;
  }
  public boolean equals(Object e) {
    if(e instanceof Egg) {
    Egg egg = (Egg)e;
    return ((this.getName()).equals(egg.getName())) && ((this.numEggs) == (egg.numEggs)) && ((this.priceEggs) == (egg.priceEggs));
    }
    return false;
  }
}