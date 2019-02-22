public abstract class MarketProduct {
private String name;

public MarketProduct(String productName) {
name = productName;
}

public final String getName() {
return this.name;
}

public abstract int getCost();

public abstract boolean equals(Object o);
}
