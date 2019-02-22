// Importing the java.util for use in the removeReservation method.
import java.util.NoSuchElementException;
public class Hotel {
  private String name;
  private Room[] hotelRooms;
  private Reservation[] hotelReservation;
  
  public Hotel (String hotelName, Room [] roomList) {
  // Any input received is assigned to the local variable, name.
  name = hotelName;  
  int numOfRooms;
  // Assigning the number of rooms to the length of the room list.
  numOfRooms = roomList.length;
  hotelRooms = new Room[numOfRooms];
  hotelReservation = new Reservation[numOfRooms];
  for (int i = 0; i < numOfRooms; i++) {
    // Copying the room references to the Hotel array.
   hotelRooms[i] = roomList[i];
   }
  }
  private void addReservation (Reservation newReservation) {

    for(int i = 0; i < hotelReservation.length; i++) {
      if(hotelReservation[i] == null) {
        // Assigning the null spot in the hotelReservation to the newReservation.
      hotelReservation[i] = newReservation;
      hotelReservation[i].getRoom().changeAvailability();
      break;
      }
    }
  }
  private void removeReservation (String guestName, String roomType) {
    int length = hotelReservation.length;
    // Checking through all the hotel reservations to find any reservation that matches the name of the guest.
    for(int i = 0; i < length; i++) {
      if(hotelReservation[i] != null && hotelReservation[i].getName().equalsIgnoreCase(guestName)) {
        // If the hotelReservation is equivalent to the roomType, the room then becomes available and the reservation is removed from the array.
        if(hotelReservation[i].getRoom().getType().equalsIgnoreCase(roomType)) {
        hotelReservation[i].getRoom().changeAvailability();
        hotelReservation[i] = null;
        return;
        }
      }
    }
    throw new NoSuchElementException("No such reservation is available.");
  }
  public void createReservation (String guestName, String roomType) {
  // Assigning a variable to the findAvailableRoom method.  
  // Declaring a variable of type Reservation to use in the later if statement.
  Reservation roomReservation;
  Room roomAvailability;
  // Inserting a temporary variable to run the findAvailableRoom method.
  Room temp = new Room ("king");
  roomAvailability = temp.findAvailableRoom(hotelRooms, roomType);
  if(roomAvailability != null) {
  roomReservation = new Reservation(roomAvailability, guestName);
  System.out.println("You have successfully reserved a " + roomType + " room under the name of " + guestName + ". We look forward to having you at the " + name + " Hotel!");
  addReservation(roomReservation);
  }
  else {
    System.out.println("A room of this type is not available.");
  }
}
  public void cancelReservation (String guestName, String roomType) {
    // Using a try catch block to catch any reservations that are full.
    try {
    removeReservation(guestName, roomType);
    }
    catch(NoSuchElementException e){
      System.out.println("No matching reservation was found.");
      return;
    }
    System.out.println("The operation was successful.");
    
  }
  public void printInvoice (String guestName) {
    double totalFees = 0;
    // Using a for loop to go through the entire list one by one, checking for the reservations that have been made. 
    for (int i = 0; i < hotelReservation.length; i++) {
      if(hotelReservation[i] != null && hotelReservation[i].getName().equalsIgnoreCase(guestName)) {
        // Printing out the total fees due by the guest by combining the getRoom and getPrice methods.
        System.out.println("Room " + (i+1) + " " + hotelReservation[i].getRoom().getPrice());
        totalFees += hotelReservation[i].getRoom().getPrice(); 
    }
  }
    System.out.println("This is your total fees. " + totalFees);
}
  public String toString () {
  // Declaring all the counter variables for all the different types of rooms.
  int k = 0;
  int q = 0;
  int d = 0;
  String returnString;
  returnString = "";
  returnString += "Hotel Name: " + name + "      ";
    for(int i = 0; i < hotelRooms.length; i++) {
    // Using if statements to check if the room is available so that the counter can proceed.
    if(hotelRooms[i].getAvailability() == true) {
      if(hotelRooms[i].getType() == "double") {
        d++;
    }
      if(hotelRooms[i].getType() == "queen") {
      q++;
      }
      if(hotelRooms[i].getType() == "king") {
        k++;
    }
  }
    }
    // Adding all the counters for the respective rooms to the currently empty returnString variable.
    returnString += " Available double rooms = " + d;
    returnString += " Available queen rooms = " + q;
    returnString += " Available king rooms = " + k;
    return returnString;
 }
}
    
  
                    

    
    
