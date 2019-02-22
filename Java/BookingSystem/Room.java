public class Room {
  // Declaring and initializing all the private variables that will be used in the following methods.
  private String type = "";
  private double price = 0.0;
  private boolean availability = false;

  public Room(String roomType){
    // Using if statement to check whether the room type entered is valid. Or else an error message is shown.
    if(roomType != "double" && roomType != "queen" && roomType != "king") {
      throw new IllegalArgumentException("No room of such type can be created.");
      }                                  
    else {
      // Using if statements to assign all the appropriate rooms to the respective prices.
      type = roomType;
      if (roomType == "double") {
        price = 90.0;
      }
      if (roomType == "queen") {
        price = 110.0;
      }
      if (roomType == "king") {
        price = 150.0;
      }
      // Changing the room avaliability to true, as required.
      availability = true;
    }
      }
  // Creating the method that returns the type of room.
  public String getType () {
    return type;
    }
  // Creating the method that returns the price of the room.
  public double getPrice () {
  return this.price;
  }
  // Creating the method that returns the availability of the room.
  public boolean getAvailability () {
  return availability;
  }
  // Using an if statement to change the value of availability between true and false, depending on the reservations made.
  public void changeAvailability () {
    if (this.availability == true) {
     this.availability = false;
    } 
    else {this.availability = true;
    }
  }
  public Room findAvailableRoom (Room [] roomList, String desiredType) {
    boolean nowAvailability;
    String nowType;
    // Running a for loop until the desiredType is found.
    for (int i = 0; i < roomList.length; i++) {
      nowAvailability = roomList[i].getAvailability();
      nowType = roomList[i].getType();
      // Using if statements to make sure that the room is not occupied and that a room of such type exists.
      if (nowAvailability == true && desiredType.equalsIgnoreCase(nowType) ) {
        return roomList[i];
      }
    } 
    return null;
    }
   
}


  

