import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

 DTNode rootDTNode;
 int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
 //Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
 public static final long serialVersionUID = 343L;
 public DecisionTree(ArrayList<Datum> datalist , int min) {
  minSizeDatalist = min;
  rootDTNode = (new DTNode()).fillDTNode(datalist);
 }

 class DTNode implements Serializable{
  //Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
  public static final long serialVersionUID = 438L;
  boolean leaf;
  int label = -1;      // only defined if node is a leaf
  int attribute; // only defined if node is not a leaf
  double threshold;  // only defined if node is not a leaf



  DTNode left, right; //the left and right child of a particular node. (null if leaf)

  DTNode() {
   leaf = true;
   threshold = Double.MAX_VALUE;
  }



  // this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
  // the calling DTNode object as the root of a decision tree trained using the datapoints present in the
  // datalist variable
  DTNode fillDTNode(ArrayList<Datum> datalist) {

    ArrayList<Datum> bestData1 = new ArrayList<Datum>();
    ArrayList<Datum> bestData2 = new ArrayList<Datum>();
    double bestAvgEntropy = Double.MAX_VALUE;
    int bestAttribute = -1;
    double bestThreshold = -1;
    double currentAvgEntropy = Double.MAX_VALUE;
    boolean test = true;
    int temp = 0;  
    if(datalist.size() >= minSizeDatalist) {
      temp = datalist.get(0).y;
      for(int i = 1; i < datalist.size(); i++) {
        if(temp != datalist.get(i).y) {
          test = false;
          break;
        }
     }
     if(test){
       this.leaf = true;
       this.label = temp;
       return this;
     }
         for(int j = 0; j < 2; j++) {
           for(Datum point1: datalist) {
             ArrayList<Datum> data1 = new ArrayList<Datum>();
             ArrayList<Datum> data2 = new ArrayList<Datum>();
             for(Datum point2: datalist) {
               if(point2.x[j] < point1.x[j]) {
               data1.add(point2);
               }
               else{
               data2.add(point2);
               }
             }
             currentAvgEntropy = ((double)data1.size()/(double)datalist.size())*calcEntropy(data1) + ((double)data2.size()/(double)datalist.size())*calcEntropy(data2);
             if(bestAvgEntropy > currentAvgEntropy) {
               bestAvgEntropy = currentAvgEntropy;
               bestAttribute = j;
               bestThreshold = point1.x[j];
               bestData1 = data1;
               bestData2 = data2;
             }
           }
         }
             this.leaf = false;
             this.attribute = bestAttribute;
             this.threshold = bestThreshold;
             if(bestData1.size() != 0 || bestData2.size() != 0) {
             this.left = (new DTNode()).fillDTNode(bestData1);
             this.right = (new DTNode()).fillDTNode(bestData2);
             }
     
    }
    else {
      this.leaf = true;
      this.label = findMajority(datalist);
    }
     return this;
  }



  //This is a helper method. Given a datalist, this method returns the label that has the most
  // occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
  int findMajority(ArrayList<Datum> datalist)
  {
   int l = datalist.get(0).x.length;
   int [] votes = new int[l];

   //loop through the data and count the occurrences of datapoints of each label
   for (Datum data : datalist)
   {
    votes[data.y]+=1;
   }
   int max = -1;
   int max_index = -1;
   //find the label with the max occurrences
   for (int i = 0 ; i < l ;i++)
   {
    if (max<votes[i])
    {
     max = votes[i];
     max_index = i;
    }
   }
   return max_index;
  }




  // This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
  // returns its corresponding label, as determined by the decision tree
  int classifyAtNode(double[] xQuery) {
   if(this.leaf == true) {
   return this.label;
   }
   else {
     if(xQuery[this.attribute] < this.threshold) {
       return this.left.classifyAtNode(xQuery);     
     }
     else {
       return this.right.classifyAtNode(xQuery);
     }
   }
  }


  //given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
  //at DTNode object passed as the parameter
 public boolean equals(Object dt2)
  {

   boolean test = true; 
   if(!(dt2 instanceof DTNode)){
    return false;
   }
   if(dt2 == null && this == null){
   return true; 
   }
   else if(dt2 == null || this == null){
   return false;
   }
   DTNode dtNode = (DTNode)dt2; 
   if(this.leaf == false){
   if(dtNode.leaf == true){
     test = false;
   }
   else if((this.attribute != dtNode.attribute) || (this.threshold != dtNode.threshold)){
     test = false;
    }
   else{
     test = (this.left.equals(dtNode.left) && this.right.equals(dtNode.right));
    }
   }
   else if((this.leaf == true) || (dtNode.leaf == true)){
    if((this.leaf == true) && (dtNode.leaf == true)){
     test = (this.label == dtNode.label);
    }
    else{
     test = false;
    }
   }
  return test;
  }

 }



 //Given a dataset, this retuns the entropy of the dataset
 double calcEntropy(ArrayList<Datum> datalist)
 {
  double entropy = 0;
  double px = 0;
  float [] counter= new float[2];
  if (datalist.size()==0)
   return 0;
  double num0 = 0.00000001,num1 = 0.000000001;

  //calculates the number of points belonging to each of the labels
  for (Datum d : datalist)
  {
   counter[d.y]+=1;
  }
  //calculates the entropy using the formula specified in the document
  for (int i = 0 ; i< counter.length ; i++)
  {
   if (counter[i]>0)
   {
    px = counter[i]/datalist.size();
    entropy -= (px*Math.log(px)/Math.log(2));
   }
  }

  return entropy;
 }


 // given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
 int classify(double[] xQuery ) {
  DTNode node = this.rootDTNode;
  return node.classifyAtNode( xQuery );
 }

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
 {
  DecimalFormat df = new DecimalFormat("0.000");
  float total = datalist.size();
  float count = 0;

  for (int s = 0 ; s < datalist.size() ; s++) {
   double[] x = datalist.get(s).x;
   int result = datalist.get(s).y;
   if (classify(x) != result) {
    count = count + 1;
   }
  }

  return df.format((count/total));
 }


 //Given two DecisionTree objects, this method checks if both the trees are equal by
 //calling onto the DTNode.equals() method
 public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
 {
  boolean flag = true;
  flag = dt1.rootDTNode.equals(dt2.rootDTNode);
  return flag;
 }

}
