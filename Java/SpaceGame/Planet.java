import java.util.ArrayList;
import java.util.Random;

public class Planet {
String name;
double chance;
double damage;

public Planet(String planetName, double successChance, double artifactDamage) {
name = planetName;
chance = successChance;
damage = artifactDamage;
if((chance < 0) || (chance > 1)) {
throw new IllegalArgumentException("Error: The chance of success must be between 0 and 1 (inclusive).");
}
}

public String getName() {
return (this.name);
}

public String toString() {
String facts = "Name: " + name + " Chance:" + (chance*100) + "%" + " Possible Damage: " + damage;
return facts;
}

public static int findPlanet(String planet, ArrayList<Planet> planets) {
int planetIndex = 0;
Planet p;
  for(int i = 0; i < planets.size(); i++) {
    p = planets.get(i);
   if(p.getName().equalsIgnoreCase(planet)) {
    planetIndex = i;
    return planetIndex;
    }
}
  planetIndex = -1;
return planetIndex;
}

public boolean searchForArtifact() {
Random rand = new Random();
double x = rand.nextDouble();
if(x < this.chance) {
return true;
}
return false;
}

public double getDamageTaken() {
Random rand = new Random();
double y = rand.nextDouble()*this.damage;
return y;
}
}