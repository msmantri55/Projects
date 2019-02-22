import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K, V> implements Iterable<HashPair<K, V>> {
 // num of entries to the table
 private int numEntries;
 // num of buckets
 private int numBuckets;
 // load factor needed to check for rehashing
 private static final double MAX_LOAD_FACTOR = 0.75;
 // ArrayList of buckets. Each bucket is a LinkedList of HashPair
 private ArrayList<LinkedList<HashPair<K, V>>> buckets;

 // constructor
 public MyHashTable(int initialCapacity) {
  this.numBuckets = initialCapacity;
  this.numEntries = 0;
  this.buckets = new ArrayList<>(initialCapacity);
  for (int i = 0; i < this.numBuckets; i++) {
   LinkedList<HashPair<K, V>> newList = new LinkedList<HashPair<K, V>>();
   this.buckets.add(newList);
  }
 }

 public int size() {
  return this.numEntries;
 }

 public int numBuckets() {
  return this.numBuckets;
 }

 /**
  * Returns the buckets vairable. Usefull for testing purposes.
  */
 public ArrayList<LinkedList<HashPair<K, V>>> getBuckets() {
  return this.buckets;
 }

 /**
  * Given a key, return the bucket position for the key.
  */
 public int hashFunction(K key) {
  int hashValue = Math.abs(key.hashCode()) % this.numBuckets;
  return hashValue;
 }

 /**
  * Takes a key and a value as input and adds the corresponding HashPair to this
  * HashTable. Expected average run time O(1)
  */
 public V put(K key, V value) {
   if (key == null || value == null) {
   throw new NullPointerException("There was an error.");
  }
  LinkedList<HashPair<K, V>> pairList = this.buckets.get(hashFunction(key));
  int index = hashFunction(key);
  if (!(pairList.isEmpty())) {
   for (HashPair<K, V> pair : pairList) {
    if (pair.getKey().equals(key)) {
     V oldValue = pair.getValue();
     pair.setValue(value);
     return oldValue;
    }
   }
  }
  double curLoadFactor = (double) ((double) (this.numEntries) / (double) (this.numBuckets));
   if (curLoadFactor > MAX_LOAD_FACTOR) {
   rehash();
   index = hashFunction(key);
  } 
  this.numEntries = this.numEntries + 1; 
  HashPair<K, V> newPair = new HashPair<K, V>(key, value);
  this.buckets.get(index).add(newPair);
  return null;
 }

 /**
  * Get the value corresponding to key. Expected average runtime = O(1)
  */

 public V get(K key) {
  LinkedList<HashPair<K, V>> pairList = this.buckets.get(hashFunction(key));
  if (!(pairList.isEmpty())) {
   for (HashPair<K, V> pair : pairList) {
    if (pair.getKey().equals(key)) {
     return pair.getValue();
    }
   }
  }
  return null;
 }

 /**
  * Remove the HashPair correspoinding to key . Expected average runtime O(1)
  */
 public V remove(K key) {
  LinkedList<HashPair<K, V>> pairList = this.buckets.get(hashFunction(key));
  if (!(pairList.isEmpty())) {
   for (HashPair<K, V> pair : pairList) {
    if (pair.getKey().equals(key)) {
     V value = pair.getValue();
     pairList.remove(pair);
     this.numEntries = this.numEntries - 1;
     return value;
    }
   }
  }
  return null;
 }

 // Method to double the size of the hashtable if load factor increases
 // beyond MAX_LOAD_FACTOR.
 // Made public for ease of testing.

 public void rehash() {
  ArrayList<LinkedList<HashPair<K, V>>> copy = this.buckets;
  this.numBuckets = this.numBuckets * 2;
  ArrayList<LinkedList<HashPair<K, V>>> buckets2 = new ArrayList<LinkedList<HashPair<K, V>>>(this.numBuckets);
  int num = 0;
  for (int i = 0; i < this.numBuckets; i++) {
   LinkedList<HashPair<K, V>> newList = new LinkedList<>();
   buckets2.add(newList);
  }
  for (LinkedList<HashPair<K, V>> list : copy) {
   for (HashPair<K, V> pair : list) {
    num = hashFunction(pair.getKey());
    buckets2.get(num).add(pair);
   }
  }
  this.buckets = buckets2;
 }

 /**
  * Return a list of all the keys present in this hashtable.
  */

 public ArrayList<K> keys() {
  ArrayList<K> keyList = new ArrayList<K>();
  LinkedList<HashPair<K, V>> pairList = null;
  for (int i = 0; i < this.numBuckets; i++) {
   pairList = this.buckets.get(i);
   for (HashPair<K, V> pair : pairList) {
    keyList.add(pair.getKey());
   }
  }
  return keyList;
 }

 /**
  * Returns an ArrayList of unique values present in this hashtable. Expected
  * average runtime is O(n)
  */
 public ArrayList<V> values() {
  ArrayList<V> valueList = new ArrayList<V>();
  MyHashTable<V, V> newHashTable = new MyHashTable<V, V>(this.numBuckets);
  LinkedList<HashPair<K, V>> pairList = null;
  for (int i = 0; i < this.numBuckets; i++) {
   pairList = this.buckets.get(i);
   for (HashPair<K, V> pair : pairList) {
    newHashTable.put(pair.getValue(), pair.getValue());
   }
  }
  valueList = newHashTable.keys();
  return valueList;
 }

 @Override
 public MyHashIterator iterator() {
  return new MyHashIterator();
 }

 private class MyHashIterator implements Iterator<HashPair<K, V>> {
  private LinkedList<HashPair<K, V>> entries;

  private MyHashIterator() {
   this.entries =  new LinkedList<>();
   for (LinkedList<HashPair<K, V>> entries2 : buckets) {
    for (HashPair<K, V> pair : entries2) {
     entries.add(pair);
    }
   }
  }

  @Override
  public boolean hasNext() {
   if (!(entries.isEmpty())) {
    return true;
   }
   return false;
  }

  @Override
  public HashPair<K, V> next() {
   return entries.removeFirst();
  }
 }

}
