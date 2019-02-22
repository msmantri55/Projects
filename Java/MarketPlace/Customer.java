public class Customer {
private String name;
private int balance;
Basket products;

public Customer(String customerName, int initialBalance) {
products = new Basket();
name = customerName;
balance = initialBalance;
}

public String getName() {
return this.name;
}

public int getBalance() {
return this.balance;
}

public Basket getBasket() {
return this.products;
}

public int addFunds(int cents) {
  if(cents < 0) {
  throw new IllegalArgumentException("The balance cannot be below 0 cents.");
  }
  balance = balance + cents;
  return balance;
}

public void addToBasket(MarketProduct marketProduct) {
this.products.add(marketProduct);
}

public boolean removeFromBasket(MarketProduct marketProduct) {
this.products.remove(marketProduct);
if(this.products.remove(marketProduct) == true) {
return true;
}
return false;
}

public String checkOut() {
  String receipt = "";
  if(balance < products.getTotalCost()) {
  throw new IllegalStateException("The customer did not have enough funds.");
  }
  receipt = this.products.toString();
  balance = balance - products.getTotalCost();
  this.products.clear();
  return receipt;
}
}