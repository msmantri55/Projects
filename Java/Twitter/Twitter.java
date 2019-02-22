// Importing the ArrayList, HashSet, File IO and HashMap to use in the later methods.
import java.util.HashMap;
import java.util.HashSet;
import java.io.*;
import java.util.ArrayList;
public class Twitter {
private ArrayList<Tweet> tweets;

public Twitter () {
 // Initialzing the ArrayList.
 tweets = new ArrayList<Tweet>();
}
public void loadDB (String tweetsFileName) {
  // Writing down the code for the file to be read.
    FileReader fr = null;
    BufferedReader br = null;
    String[] parts;
    Tweet currentTweet;
    try{
        fr = new FileReader(tweetsFileName);
        br = new BufferedReader(fr);
        String currentLine = br.readLine();
        while(currentLine != null) {
          // Splitting the line with a tab if it has content in it.
          parts = currentLine.split("\t");
          // Assigning the new currentTweet variable to the first four elements of the already created parts array.
          currentTweet = new Tweet(parts[0], parts[1], parts[2], parts[3]);
          // Adding the currentTweet array if the checkMessage from the Tweet class is true.
          if(currentTweet.checkMessage() == true) {
          tweets.add(currentTweet);
          }
          currentLine = br.readLine();
        }
         br.close();
         fr.close();
    }
   catch(IOException e) {
     // Catching an exception if an invalid file name is input.
    System.out.println("File does not exist.");
  }
  sortTwitter();
}
// Creating an additional method called addTweet for testing purposes.
public void addTweet(Tweet thisTweet) {
 this.tweets.add(thisTweet);
}
public void sortTwitter() {
  Tweet t1;
  Tweet t2;
  for(int i = 0; i < tweets.size(); i++) {
    // Breaking from the for loop if the integer i is greater that the size of the tweets ArrayList.
    if(i+1 >= tweets.size()) {
    break;
    }
  t1 = tweets.get(i);
  t2 = tweets.get(i+1);
  // Sorting the tweets using an if statement so that they are arranged in the appropriate order.
  if(t1.isBefore(t2) == false) {
    tweets.set(i, t2);
    tweets.set(i+1, t1);
    // Setting i as -1 so that the for loop stars with i = 0.
    i = -1;
    }
  }
}
// The next two methods return the required index and the size of the ArrayList.
public int getSizeTwitter() {
 return this.tweets.size();
}
public Tweet getTweet(int index) {
 return this.tweets.get(index);
}
public String printDB() {
 String allTweets = "";
 Tweet oneTweet;
 // Using a for loop to look through the entire tweets ArrayList.
 for(int i = 0; i < tweets.size(); i++) {
  oneTweet = tweets.get(i);
  // Inserting a new line to separate all the tweets.
  // Assigning the allTweets variable to be an increment of the oneTweet variable and toString method.
  allTweets += oneTweet.toString() + "\n";
  }
  return allTweets;
 }
public ArrayList<Tweet> rangeTweets(Tweet tweet1, Tweet tweet2) {
  // Creating a new ArrayList called newTweets.
 ArrayList<Tweet> newTweets = new ArrayList<Tweet>();
 int startIndex, endIndex;
 // Using an if-else statement to assignin the respective indexes, depending on whether tweet1 is before or after tweet2.
 if(tweet1.isBefore(tweet2) == true) {
    startIndex = tweets.indexOf(tweet1);
    endIndex = tweets.indexOf(tweet2);
 }
 else {
  startIndex = tweets.indexOf(tweet2);
  endIndex = tweets.indexOf(tweet1);
  }
 // Using a for loop to keep adding tweets as long as the integer i is less than the length of the endIndex.
  for(int i = startIndex; i <= endIndex; i++) {
   newTweets.add(tweets.get(i));
  }
  return newTweets;
}
public void saveDB(String fileName) {
  // Writing down all the code for writing to the file.
 FileWriter fw = null;
 BufferedWriter bw = null;
 String allTweets;
  try{
    // Assigning the local allTweets variable to the printDB method created above.
    allTweets = this.printDB();
        fw = new FileWriter(fileName);
        bw = new BufferedWriter(fw);
        bw.write(allTweets);
         bw.close();
         fw.close();
    }
  catch(IOException e) {
    // Catching an exception if the file entered is not valid.
    System.out.println("File does not exist.");
  }
}
  public String trendingTopic() {
    // Setting up our tweetsHashMap.
   HashMap<String, Integer> tweetsHashMap = new HashMap<String, Integer>();
   HashSet<String> tweetWords;
   // Declaring all the variables that we will use in this method.
   Tweet thisTweet;
   String thisMessage;
   String[] parts;
   String word;
   int countValue;
  // Using a for loop to search through the entire tweets ArrayList.
  for(int i = 0; i < tweets.size(); i++) {
    // Assigning all the variables to the respective ArrayList and variables.
   thisTweet = tweets.get(i);
   thisMessage = thisTweet.getMessage();
   parts = thisMessage.split(" ");
   tweetWords = new HashSet<String>();
   // Creating an inner for loop to check the parts array and repalce all the punctuations with a null.
     for(int j = 0; j < parts.length; j++) {
       word = parts[j].toLowerCase();
       word = word.replace(".", "");
       word = word.replace(",", "");
       word = word.replace(";", "");
       word = word.replace(":", "");
       // Using an if statement to check whether or not stopWords are present. If they are not, the word is added.
       if(Tweet.stopWords.contains(word) == false) {
       tweetWords.add(word);
       }
     }
     // Looking through the tweetWords HashSet using a for loop.
    for(String sr : tweetWords) {
      // Values in the HashMap are counted if they are appropriate.
     if(tweetsHashMap.containsKey(sr) == true) {
      countValue = tweetsHashMap.get(sr);
      // If the word is already in the HashMap, we increment its value by one.
      tweetsHashMap.put(sr, countValue + 1);
     }
     // When the word does not exist in our HashMap.
     else {
      tweetsHashMap.put(sr, 1);
     }
    }
  }
  countValue = 0;
  word = "";
  // Looking through the tweetsHashMap to check for all the words present. All the words in the tweetsHashMap are counted.
  for(String s : tweetsHashMap.keySet()) {
    if(countValue < tweetsHashMap.get(s)) {
     word = s;
     countValue = tweetsHashMap.get(s);
    }
  }
  // The word that is present in the greatest quanity is returned at the very end.
  return word;
}
 public static void main (String[] args) {
   // Creating a main method to test some of our created methods. 
    Twitter myTwitter = new Twitter();
    String fileName = "";
    String tweetsFileName = "";
    String sortedFileName = "";
    Tweet.loadStopWords(fileName);
    myTwitter.loadDB(tweetsFileName);
    ArrayList<Tweet> filteredTweets;
    Tweet myTweet1 = myTwitter.tweets.get(6);
    Tweet myTweet2 = myTwitter.tweets.get(30);
    filteredTweets = myTwitter.rangeTweets (myTweet1, myTweet2);
    for (int i = 0; i < filteredTweets.size(); i++) {
    System.out.println(filteredTweets.get(i).toString());
  }
 }