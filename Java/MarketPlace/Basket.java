public class Basket {
private MarketProduct[] marketProducts;
private int count;

public Basket() {
marketProducts = new MarketProduct[10];
count = 0;
}

private MarketProduct[] expand(int addSize) {
MarketProduct[] newArray = new MarketProduct[marketProducts.length + addSize];
for(int i = 0; i < marketProducts.length; i++) {
newArray[i] = marketProducts[i];
}
return newArray;
}

public MarketProduct[] getProducts() {
MarketProduct[] shallowCopy = new MarketProduct[marketProducts.length];
for(int i = 0; i < marketProducts.length; i++) {
shallowCopy[i] = this.marketProducts[i]; 
}
return shallowCopy;
}

public void add(MarketProduct addMarketProduct) {
  if(count >= marketProducts.length) {
  marketProducts = expand(10);
  }
   for(int i = 0; i < marketProducts.length; i++) {
    if(this.marketProducts[i] == null) {
    this.marketProducts[i] = addMarketProduct;
    count++;
    break;
    }
  }
}

public boolean remove(MarketProduct removeMarketProduct) {
  for(int i = 0; i < marketProducts.length; i++) {
    if(this.marketProducts[i] != null) {
     if(removeMarketProduct.equals(this.marketProducts[i]) == true) {
       count--;
      for(int j = i + 1; j < marketProducts.length; j++) {
      if(this.marketProducts[j] != null) {
       marketProducts[j-1] = marketProducts[j];
       marketProducts[j] = null;
       }
    } 
      return true;
    }
    }
  }
return false;
}

public void clear() {
  for(int i = 0; i < marketProducts.length; i++) {
    this.marketProducts[i] = null;
  }  
}

public int getNumOfProducts() {
int numProducts = 0;
for(int i = 0; i < marketProducts.length; i++) {
  if(this.marketProducts[i] != null) {
  numProducts++;
}
}
return numProducts;
}

public int getSubTotal() {
int subTotal = 0;
for(int i = 0; i < marketProducts.length; i++) {
  if(this.marketProducts[i] != null) {
  subTotal += this.marketProducts[i].getCost();
  }
}
return subTotal;
}

public int getTotalTax() {
double totalTax = 0;
for(int i = 0; i < marketProducts.length; i++) {
  if(this.marketProducts[i] instanceof Jam) {
totalTax += ((double)marketProducts[i].getCost()*(double)0.15);
  }
}
return (int) totalTax;
}

public int getTotalCost() {
int subTotal = getSubTotal();
int totalCost = getSubTotal() + getTotalTax();
return totalCost;
}

private String toStringHelper(int cents) {
String cost = "";
double dollar = 0;
if((double)cents <= (double)0) {
cost = "-";
return cost;
}
dollar = (double)cents/(double)100;
cost = String.format("%.2f", dollar);
return cost;
}

public String toString() {
String receipt = "";
String items = "";
for(int i = 0; i < marketProducts.length; i++) {
  if(this.marketProducts[i] != null) {
  items += this.marketProducts[i].getName() + "\t" + toStringHelper(this.marketProducts[i].getCost()) + "\n";
  }
}
receipt = items + "\n" + "Subtotal" + "\t" + toStringHelper(getSubTotal()) + "\n" + "Total Tax" + "\t" + toStringHelper(getTotalTax()) + "\n" + "\n" + "Total Cost" + "\t" + toStringHelper(getTotalCost());
return receipt;
}

}