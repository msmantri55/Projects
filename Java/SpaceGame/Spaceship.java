import java.util.ArrayList;

public class Spaceship {
String name;
double curHealth;
double maxHealth;
int numArtifacts;
int numWins;
Planet curPlanet;
ArrayList<Planet> planets = new ArrayList<Planet>();

public Spaceship(String spaceShipName, double maximumHealth, int numberWins) {
name = spaceShipName;
maxHealth = maximumHealth;
numWins = numberWins;
curHealth = maximumHealth;
}

public double getHealth() {
return this.curHealth;
}

public int getNumArtifacts() {
return this.numArtifacts;
}

public String getName() {
return this.name;
}

public double getMaxHealth() {
return this.maxHealth;
}

public String toString() {
String facts = "Spaceship Name: " + name + " " + "Current health: " + curHealth + " " + "Number of Alien Artifacts found: " + numArtifacts;
return facts;
}

public void setPlanets(ArrayList<Planet> iplanets) {
 for(int i = 0; i < iplanets.size(); i++) {
    this.planets.add(iplanets.get(i));
    System.out.println(iplanets.get(i).toString());
  }
}

public void moveTo(String planetName) {
int index = 0;
index = Planet.findPlanet(planetName, this.planets);
if(index == -1) {
System.out.println("The " + name + " could not move to " + planetName + " because it does not exist in this solar system!");
return;
}
this.curPlanet = planets.get(index);
System.out.println("The " + name + " moved to " + planetName + ".");
}

public void moveIn() {
int index = 0;
index  = Planet.findPlanet(curPlanet.getName(), planets);
index = index - 1;
if(index < 0) {
  System.out.println(name + " couldn't move in. No planet is closer in.");
  return;
}
moveTo(planets.get(index).getName());
System.out.println("Now moving to " + planets.get(index).getName());
}

public void moveOut() {
int index = 0;
index  = Planet.findPlanet(curPlanet.getName(), planets);
index = index + 1;
if(index > planets.size()-1) {
  System.out.println(name + " couldn't move out. No planet is further out.");
  return;
}
moveTo(planets.get(index).getName());
System.out.println("Now moving to " + planets.get(index).getName());
}

public void increaseWins() {
numWins = numWins + 1;
}


public int getNumWins() {
return this.numWins;
}

public void doSearch() {
boolean x = this.curPlanet.searchForArtifact();
if(x == true) {
System.out.println("The " + this.name + " found an artifact.");
this.numArtifacts = this.numArtifacts + 1;
}
if(x == false) {
  System.out.println("The " + this.name + " did not find an artifact.");
}
double damage = this.curPlanet.getDamageTaken();
String damageStr = String.format("%1$.2f", damage);
System.out.println("The " + this.name + " took " + damageStr + " damage.");
this.curHealth = this.curHealth - damage;
if(this.curHealth < 0) {
System.out.println("The " + this.name + " explodes.");
}
}
}