import java.math.BigInteger;

public class Polynomial 
{
 private SLinkedList<Term> polynomial;
 public int size()
 { 
  return polynomial.size();
 }
 private Polynomial(SLinkedList<Term> p)
 {
  polynomial = p;
 }
 
 public Polynomial()
 {
  polynomial = new SLinkedList<Term>();
 }
 
 // Returns a deep copy of the object.
 public Polynomial deepClone()
 { 
  return new Polynomial(polynomial.deepClone());
 }
 

 public void addTerm(Term t)
 { 
  int i = 0;
  BigInteger coefficientSum = BigInteger.valueOf(0);
  Term newTerm = t;
 if((t.getExponent() < 0) || (t.getCoefficient().equals(new BigInteger("0")))) {
  throw new IllegalArgumentException("The input is invalid.");
  }
  for (Term currentTerm: polynomial)
  {
     if(currentTerm.getExponent() < t.getExponent()) {
    polynomial.add(i, t);
    return;
    }
    if(currentTerm.getExponent() == t.getExponent()) {
    polynomial.remove(i);
    coefficientSum = currentTerm.getCoefficient().add(t.getCoefficient());
    newTerm.setExponent(t.getExponent());
    newTerm.setCoefficient(coefficientSum);
    if(newTerm.getCoefficient().equals(new BigInteger("0"))) {
    return;
    }
    polynomial.add(i, newTerm);
    return;
    }
    i++;
  }
  polynomial.addLast(t);
  return;
}
 
 public Term getTerm(int index)
 {
  return polynomial.get(index);
 }
 
 public static Polynomial add(Polynomial p1, Polynomial p2)
 { 
  int ep1 = 0;
  int ep2 = 0;
  BigInteger plus = BigInteger.valueOf(0);
  Polynomial sum = new Polynomial(); 
  Polynomial p1Clone = p1.deepClone();
  Polynomial p2Clone = p2.deepClone();
  while(((p1Clone.size() > 0) || (p2Clone.size() > 0))) {
    if(p1Clone.size() > 0) {
    ep1 = p1Clone.polynomial.get(0).getExponent();
    }
    else {
    ep1 = -1;
    }
    if(p2Clone.size() > 0) {
    ep2 = p2Clone.polynomial.get(0).getExponent();
    }
    else {
    ep2 = -1;
    }
    if(ep2 > ep1) {
    sum.addTermLast(p2Clone.polynomial.get(0));
    p2Clone.polynomial.removeFirst();
    }
    if(ep2 < ep1) {
    sum.addTermLast(p1Clone.polynomial.get(0));
    p1Clone.polynomial.removeFirst();
    }
    if(ep2 == ep1) {
    plus = p1Clone.polynomial.get(0).getCoefficient().add(p2Clone.polynomial.get(0).getCoefficient());
    p1Clone.polynomial.get(0).setCoefficient(plus);
    sum.addTermLast(p1Clone.polynomial.get(0));
    p1Clone.polynomial.removeFirst(); 
    p2Clone.polynomial.removeFirst();
    }
  }
  return sum;
 }
 
private void multiplyTerm(Term t)
 { 
  BigInteger newCoefficient = BigInteger.valueOf(0);
  int newExponent = 0;
  if((t.getExponent() < 0) || (t.getCoefficient().equals(new BigInteger("0")))) {
  throw new IllegalArgumentException("The input is invalid.");
  }
  for (Term currentTerm: polynomial)
  {  
    newCoefficient = currentTerm.getCoefficient().multiply(t.getCoefficient());
    newExponent = currentTerm.getExponent() + t.getExponent();   
    currentTerm.setExponent(newExponent);
    currentTerm.setCoefficient(newCoefficient);
    if(currentTerm.getCoefficient().equals(new BigInteger("0"))) {
    return;
    }  
  }
 }
 
 public static Polynomial multiply(Polynomial p1, Polynomial p2)
 {
  int cp1 = 0;
  Term t1 = new Term(0, new BigInteger("0"));
  Polynomial temp = new Polynomial();
  Polynomial multiply = new Polynomial(); 
  for(;cp1 < p1.size(); cp1++) {
    temp = p2.deepClone();
    t1 = p1.getTerm(cp1);
    temp.multiplyTerm(t1);
    multiply = Polynomial.add(multiply, temp);
   }  
  return multiply;
 }
 

 public BigInteger eval(BigInteger x)
 {

  BigInteger value = BigInteger.valueOf(0);
   for(Term currentTerm: polynomial) {
     value = value.add(currentTerm.getCoefficient().multiply((x).pow(currentTerm.getExponent())));
   }
  return value;
 }
 
 // Checks if this polynomial is same as the polynomial in the argument
 public boolean checkEqual(Polynomial p)
 { 
  if (polynomial == null || p.polynomial == null || size() != p.size())
   return false;
  
  int index = 0;
  for (Term term0 : polynomial)
  {
   Term term1 = p.getTerm(index);
   
   if (term0.getExponent() != term1.getExponent() ||
    term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
     return false;
   
   index++;
  }
  return true;
 }
 
 // This method blindly adds a term to the end of LinkedList polynomial. 
 public void addTermLast(Term t)
 { 
  polynomial.addLast(t);
 }
 
 // This is used for testing multiplyTerm
 public void multiplyTermTest(Term t)
 {
  multiplyTerm(t);
 }
 
 @Override
 public String toString()
 { 
  if (polynomial.size() == 0) return "0";
  return polynomial.toString();
 }
}
