public class Reservation {
  private String name;
  private Room roomReserved;
  // Creating the constructor method to initialize the private attributes.
  public Reservation (Room theRoom, String personName){
    roomReserved = theRoom;
    name = personName;
  }
  // Returning the name of the person who has made the reservation.
  public String getName () {
    return (this.name);
}
  public Room getRoom () {
    // Returning the room that has been reserved.
    return (this.roomReserved);
  }
}

  