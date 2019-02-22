public class JudgeScore {
 public static void main(String[] args) {
 

 
  //Declaring the variables for storing the judges scores.
  int judge1, judge2, judge3, judge4;
  judge1 = Integer.valueOf(args[0]);
  judge2 = Integer.valueOf(args[1]);
  judge3 = Integer.valueOf(args[2]);
  judge4 = Integer.valueOf(args[3]);
  

  int lowest = judge1 ;
  if (lowest > judge2) { lowest = judge2;
    if (lowest > judge3) { lowest = judge3;
      if (lowest > judge4) { lowest = judge4;}}}
  if (lowest > judge3) { lowest = judge3;
    if (lowest > judge4) { lowest = judge4;}}
  if (lowest > judge4) { lowest = judge4;}
  
  
  int highest = judge1 ;
  if (highest < judge2) { highest = judge2;
    if (highest <  judge3) { highest = judge3;
      if (highest < judge4) { highest = judge4;}}}
  if (highest < judge3) { highest = judge3;
    if (highest < judge4) { highest = judge4;}}
  if (highest < judge4) { highest = judge4;}
 
  
  double average = 0.0;
  average = ((judge1 + judge2 + judge3 + judge4 - lowest - highest)/2.0);
  System.out.println (average);
    
       

  
 }
}
