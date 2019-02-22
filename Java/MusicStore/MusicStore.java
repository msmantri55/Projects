import java.util.ArrayList;
import java.util.LinkedList;

public class MusicStore {
 private MyHashTable<String, Song> titleList;
 private MyHashTable<String, ArrayList<Song>> artistList;
 private MyHashTable<Integer, ArrayList<Song>> yearList;

 public MusicStore(ArrayList<Song> songs) {
  int initialCapacity = songs.size();
  if(songs.size() == 0) {
      initialCapacity = 1;
  }
  this.titleList = new MyHashTable<String, Song>(initialCapacity);
  this.artistList = new MyHashTable<String, ArrayList<Song>>(initialCapacity);
  this.yearList = new MyHashTable<Integer, ArrayList<Song>>(initialCapacity);
  for (Song song : songs) {
   this.titleList.put(song.getTitle(), song);
   if (this.artistList.get(song.getArtist()) == null) {
    ArrayList<Song> artistArrayList = new ArrayList<Song>();
    artistArrayList.add(song);
    this.artistList.put(song.getArtist(), artistArrayList);
   } else {
    ArrayList<Song> artistArrayList = new ArrayList<Song>();
    artistArrayList = this.artistList.get(song.getArtist());
    artistArrayList.add(song);
    this.artistList.put(song.getArtist(), artistArrayList);
   }
   if (this.yearList.get(song.getYear()) == null) {
    ArrayList<Song> yearArrayList = new ArrayList<Song>();
    yearArrayList.add(song);
    this.yearList.put(song.getYear(), yearArrayList);
   } else {
    ArrayList<Song> yearArrayList = new ArrayList<Song>();
    yearArrayList = this.yearList.get(song.getYear());
    yearArrayList.add(song);
    this.yearList.put(song.getYear(), yearArrayList);
   }
  }
  
 }

 /**
  * Add Song s to this MusicStore
  */
 public void addSong(Song s) {
  this.titleList.put(s.getTitle(), s);
  if (this.artistList.get(s.getArtist()) == null) {
   ArrayList<Song> artistArrayList = new ArrayList<Song>();
   artistArrayList.add(s);
   this.artistList.put(s.getArtist(), artistArrayList);
  } else {
     ArrayList<Song> artistArrayList = new ArrayList<Song>();
   artistArrayList = this.artistList.get(s.getArtist());
   artistArrayList.add(s);
   this.artistList.put(s.getArtist(), artistArrayList);
  }
  if (this.yearList.get(s.getYear()) == null) {
      ArrayList<Song> yearArrayList = new ArrayList<Song>();
   yearArrayList.add(s);
   this.yearList.put(s.getYear(), yearArrayList);
  } else {
      ArrayList<Song> yearArrayList = new ArrayList<Song>();
   yearArrayList = this.yearList.get(s.getYear());
   yearArrayList.add(s);
   this.yearList.put(s.getYear(), yearArrayList);
  }
 }

 /**
  * Search this MusicStore for Song by title and return any one song by that
  * title
  */
 public Song searchByTitle(String title) {
  return this.titleList.get(title);
 }

 /**
  * Search this MusicStore for song by `artist' and return an ArrayList of all
  * such Songs.
  */
 public ArrayList<Song> searchByArtist(String artist) {
  return this.artistList.get(artist);
 }

 /**
  * Search this MusicSotre for all songs from a `year' and return an ArrayList of
  * all such Songs
  */
 public ArrayList<Song> searchByYear(Integer year) {
  return this.yearList.get(year);

 }
}
