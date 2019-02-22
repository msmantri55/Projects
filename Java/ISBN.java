public class ISBN {

 public static void main(String[] args) {
 

  //Declaring the variables to represent the ISBN number
   int n;
   int lastDigitOfISBN = 0;
   int firstDigit = 0; 
   int secondDigit= 0;
   int thirdDigit= 0;
   int fourthDigit= 0;
   int remainder= 0; 
   int sum = 0;
   

        n= Integer.parseInt(args[0]);
        firstDigit = n/1000;
        remainder = n % 1000;
        
        secondDigit = remainder/100;
        remainder= remainder % 100;
        
        thirdDigit = remainder/10;
        remainder= remainder % 10;
        
        fourthDigit = remainder;
        
        sum = 5*firstDigit + 4*secondDigit + 3*thirdDigit + 2*fourthDigit;
        
        
        remainder= sum%11;
       
        if(remainder>0) {
          lastDigitOfISBN= 11-remainder;}
        if(lastDigitOfISBN == 10) {System.out.println ("X");}
        if (lastDigitOfISBN != 10 ) {
          System.out.println( lastDigitOfISBN);}

 }

}
