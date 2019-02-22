import java.io.*;
import java.util.ArrayList;

public class FileIO {
  public Spaceship loadSpaceship(String fileName) {
    FileReader fr = null;
    BufferedReader br = null;
    String spaceshipName = "";
    String maxHealth = "";
    String numWins = "";
    try {
    fr = new FileReader(fileName);
    br = new BufferedReader(fr);
    spaceshipName = br.readLine();
    maxHealth = br.readLine();
    numWins = br.readLine();
    br.close();
    fr.close();
    }
    catch(FileNotFoundException f) {
    throw new IllegalArgumentException("Error: the file was not found.");
    }
    catch(IOException e) {
    throw new IllegalArgumentException("There was another error.");
    }
    double health = Double.parseDouble(maxHealth);
    int numberWins = Integer.parseInt(numWins);
    Spaceship spaceShip = new Spaceship(spaceshipName, health, numberWins);
    return spaceShip;
  }
  
  public ArrayList<Planet> loadPlanets(String fileName) {
  ArrayList<Planet> planets = new ArrayList<Planet>();
  FileReader fr = null;
    BufferedReader br = null;
    String currentLine = "";
    String[] parts = new String[3];
    double success=0;
    double damage=0;
    Planet line;
    try {
    fr = new FileReader(fileName);
    br = new BufferedReader(fr);
    currentLine = br.readLine();
    while(currentLine != null) {
      parts = currentLine.split(" ");
      success = Double.parseDouble(parts[1]);
      damage = Double.parseDouble(parts[2]);
      line = new Planet(parts[0], success, damage);
      planets.add(line);
      currentLine = br.readLine();
    }
    br.close();
    fr.close();
    }
    catch(FileNotFoundException f) {
    throw new IllegalArgumentException("Error: the file was not found.");
    }
    catch(IOException e) {
    throw new IllegalArgumentException("There was another error.");
    } 
  return planets;
  }
  
  public static void saveSpaceship(Spaceship spaceship, String fileName) throws IOException {
  FileWriter fw = null;
  BufferedWriter bw = null;
  String charName = spaceship.getName();
  double maxHealth = spaceship.getMaxHealth();
  int numWins = spaceship.getNumWins();
  fw = new FileWriter(fileName);
  bw = new BufferedWriter(fw);
  bw.write(charName);
  bw.write(Double.toString(maxHealth));
  bw.write(Integer.toString(numWins));
  bw.close();
  fw.close();
  }
}