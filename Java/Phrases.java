public class Phrases {
  
  public static String join(String[] words) {
  String sentence = words[0];
  for(int i = 1; i < words.length; i++) {
  sentence = sentence + " " + words[i];
  }
  return sentence;
  }
  
  public static int findInStringArray(String word, String[] words) {
  int positionFirstWord = -1;
  for(int i = 0; i < words.length; i++) {
    if(word.equalsIgnoreCase(words[i]) == true) {
    positionFirstWord = i;
    break;
    }
  }
  return positionFirstWord;
  }
  
  public static int countWords(String sentence) {
  int wordCount = 0;
  char word;
  for(int i = 0; i < sentence.length()-1; i++) {
    word = sentence.charAt(i);
    if(word == ' ') {
    wordCount++;
    }
  }
  wordCount = wordCount + 1;
  return wordCount;
  }
  
  public static String[] tokenize(String sentence) {
  int wordCount = countWords(sentence);
  String[] sentenceWords = new String[wordCount];
  int curWord = 0;
  for(int i = 0; i < sentence.length(); i++) {
    if (sentence.charAt(i) == ' '  || i == sentence.length()-1) {
      curWord++;
    }
  else {
      if(sentenceWords[curWord] == null) {
      sentenceWords[curWord] = "";
      }
      sentenceWords[curWord] += sentence.charAt(i);
    }
  }
  return sentenceWords;
  }
  
  public static String[] combineArray(String[] array1, String[] array2, int pos1, int pos2) {
  String[] combinedSentence = new String[(pos1+1) + (array2.length-pos2-1)];
  for(int i = 0; i < combinedSentence.length; i++) {
    if(i <= pos1) {
  combinedSentence[i] = array1[i];
    }
  else {
  combinedSentence[i] = array2[i+pos2-pos1];
  }
  }
  return combinedSentence;
  }
  
  public static String combineStrings(String phrase1, String phrase2) {
  String combined = "";
  String[] splitString1 = tokenize(phrase1); 
  String[] splitString2 = tokenize(phrase2);
  int pos1 = -1;
  int pos2 = -1;
  for(int i = 0; i < splitString1.length; i++) {
  pos2 = findInStringArray(splitString1[i], splitString2);
  if(pos2 >= 0){
    pos1 = i;
    break;
  }
  }
  if(pos1 == -1 && pos2 == -1) {
  throw new IllegalArgumentException("The two phrases do not share a common word.");
  }
  else{
  String[] combinedString = combineArray(splitString1, splitString2, pos1, pos2);
  combined = join(combinedString);
  }
  return combined;
  }
  
  public static void main (String[] args) {
  String phrase1 = "I am dog?";
  String phrase2 = "You Dog are?";
  String combined = combineStrings(phrase1, phrase2);
  System.out.println(combined);
  }
}