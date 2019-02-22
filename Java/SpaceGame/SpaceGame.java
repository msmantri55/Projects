import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class SpaceGame {
Scanner in;
Spaceship player;
Spaceship enemy;
static final int NUM_ARTIFACTS_WIN = 5;

public SpaceGame(String fileName) {
in = new Scanner(System.in);
FileIO fileIO = new FileIO();
player = fileIO.loadSpaceship("player.txt");
enemy = fileIO.loadSpaceship("enemy.txt");
ArrayList<Planet> planets = new ArrayList<Planet>();
planets = fileIO.loadPlanets(fileName);
player.setPlanets(planets);
enemy.setPlanets(planets);
player.moveTo(planets.get(0).getName());
enemy.moveTo(planets.get(planets.size()-1).getName());
System.out.println("Hello and welcome to the game! You must find " + NUM_ARTIFACTS_WIN + " artifacts to win the game.");
}

private int checkForDestroyed() {
double playerHealth = this.player.getHealth();
double enemyHealth = this.enemy.getHealth();
if(playerHealth <= 0) {
return 1;
}
if(enemyHealth < 0) {
return 2;
}
return 0;
}

private int checkForWin() {
double playerNumArtifacts = this.player.getNumArtifacts();
double enemyNumArtifacts = this.enemy.getNumArtifacts();
if(playerNumArtifacts >= NUM_ARTIFACTS_WIN) {
return 1;
}
if(enemyNumArtifacts >= NUM_ARTIFACTS_WIN) {
return 2;
}
return 0;
}

public void playGame() {
  String command = "";
  String destination = "";
  FileIO fileIO = new FileIO();
  while((checkForDestroyed() == 0) && (checkForWin() == 0)) {
  System.out.print("What is your command?");
  command = this.in.nextLine();
  if(command.equalsIgnoreCase("moveIn")) {
  this.player.moveIn();
  }
  else if(command.equalsIgnoreCase("moveOut")) {
  this.player.moveOut();
  }
  else if(command.equalsIgnoreCase("moveTo")) {
  System.out.print("What would you like your destination to be?");
  destination = in.nextLine();
  this.player.moveTo(destination);
  }
  else if(command.equalsIgnoreCase("search")) {
  this.player.doSearch();
  }
  else {
  System.out.println("The input was not recognized");
  }
  Random rand = new Random();
  int x = rand.nextInt(3);
  if(x == 0) {
  this.enemy.doSearch();
  }
  if(x == 1) {
  this.enemy.moveIn();
  }
  if(x == 2) {
  this.enemy.moveOut();
  }
  System.out.println(player.toString());
  System.out.println(enemy.toString());
  }
  if(checkForDestroyed() == 1) {
    try {
  System.out.println("Your spaceship was destroyed. You lost the game.");
  this.enemy.increaseWins();
  System.out.println("You have " + player.getNumWins() + " wins.");
  System.out.println("The enemy has " + enemy.getNumWins() + " wins.");
  fileIO.saveSpaceship(enemy, "enemy.txt");
    }
    catch (IOException e) {
  throw new IllegalArgumentException("There was an error with enemy.txt.");
  }
  }
  else if(checkForWin() == 1) {
    try {
  System.out.println("You found enough artifacts. You won the game!");
  this.player.increaseWins();
  System.out.println("You have " + player.getNumWins() + " wins.");
  System.out.println("The enemy has " + enemy.getNumWins() + " wins.");
  fileIO.saveSpaceship(player, "player.txt");
    }
    catch (IOException e) {
  throw new IllegalArgumentException("There was an error with player.txt");
  }
  }
  if(checkForDestroyed() == 2) {
    try {
  System.out.println("The enemy was destroyed. You won the game!");
  this.player.increaseWins();
  System.out.println("You have " + player.getNumWins() + " wins.");
  System.out.println("The enemy has " + enemy.getNumWins() + " wins.");
  fileIO.saveSpaceship(player, "player.txt");
    }
  catch (IOException e) {
  throw new IllegalArgumentException("There was an error with player.txt");
  }
  }
  else if(checkForWin() == 2) {
    try {
    System.out.println("The enemy collected enough artifacts. You lost the game.");
    this.enemy.increaseWins();
    System.out.println("You have " + player.getNumWins() + " wins.");
    System.out.println("The enemy has " + enemy.getNumWins() + " wins.");
    fileIO.saveSpaceship(enemy, "enemy.txt");
    }
    catch (IOException e) {
  throw new IllegalArgumentException("There was an error with enemy.txt.");
  }
  }
}

public static void main(String[] args) {
SpaceGame myGame = new SpaceGame("sol.txt");
myGame.playGame();
}
}