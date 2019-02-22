// Importing the HashSet, File, Date and SimpleDateFormat for use later in the class.
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.HashSet;
public class Tweet {
  // Declaring and initializing all the required private attributes.
  private String userAccount = "";
  private String date = "";
  private String time = "";
  private String message = "";
  public static HashSet<String> stopWords = new HashSet<String>();
  
  public Tweet(String inAccount, String inDate, String inTime, String inMessage) {
  // Setting all the initial variables to the ones declared in this constructor method.
   userAccount = inAccount;
   date = inDate;
   time = inTime;
   message = inMessage;
  }

  public boolean checkMessage() {
    // Throwing a NullPointerException when the stopWords file has not been initialized.
    if(stopWords.size() == 0) {
      throw new NullPointerException ("The HashSet stopWords has not been initialized.");
    }
    int wordCount = 0;
    // Creating an array called parts that looks for all the stopWords. Splitting all of them with quotation marks.
    String[] parts = message.split(" ");
    String word;
    // Looking through all of the parts array for any punctuation and empty spaces.
    for(int i = 0; i < parts.length; i++) {
     word = parts[i].toLowerCase();
     word = word.replace(".", "");
     word = word.replace(",", "");
     word = word.replace(";", "");
     word = word.replace(":", "");
     // Making sure that any inputs in the tweetMessage are converted to lower case before comparison, so that the case is ingored.
     if(!stopWords.contains(word)) {
     wordCount++;
     }
   }
    // A tweet must have between 1 and 15 words, so a false must be returned otherwise.
   if(wordCount < 16 && wordCount > 0) {
    return true;
   }
    else return false;
  }
  // The following 4 methods return all the required formats of a tweet.
  public String getDate() {
   return this.date;
  }
  public String getTime() {
    return this.time;
  }
  public String getMessage() {
   return this.message;
  }
  public String getUserAccount() {
   return this.userAccount;
  }
  public String toString() {
    // Creating a combinedString that separates each aspect of the tweet with a tab.
   String combinedString = this.userAccount + "\t" + this.date + "\t" + this.time + "\t" + this.message;
   return combinedString;
  }
  public boolean isBefore(Tweet inputTime) {
    // Using the SimpleDateFormat to sort the tweets.
   SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   // Initializing the two dates we are going to be comparing.
    Date d1;
    Date d2;
    String s1;
    String s2;
    boolean status = false;
    // Assigning the date and time to the two already declared string variables.
    s1 = inputTime.date + " " + inputTime.time;
    s2 = this.date + " " + this.time;
    // Using an if statement to check whether string s1 is equal to string s2. The appropriate return values are then outputted.
    if(s1.equalsIgnoreCase(s2) == true) {
    return true;
    }
    // Creating a try-catch block to check the "status" of the date and time for each of d1 and d2.
 try{
     d1 = sd.parse(s1);
     d2 = sd.parse(s2);
     status = d1.after(d2);
     }
 catch(Exception e) {
  System.out.println("An excpetion occurred.");
 }
  return status;
  }
  public static void loadStopWords (String fileName){
    // Inserting the code that will read the file.
    FileReader fr = null;
    BufferedReader br = null;
    try{
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String currentLine = br.readLine();
        while(currentLine != null) {
          // Making sure that any inputs in the stopWords are changed to lower case so as to match the file.
          stopWords.add(currentLine.toLowerCase());
          currentLine = br.readLine();
        }
         br.close();
         fr.close();
    }
   catch(IOException e) {
     // An exception is caught if an invalid file name is inserted.
    System.out.println("File does not exist.");
  }
 }
}
   