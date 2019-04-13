package store_house.model;

import store_house.abstraction.Publication;
import store_house.abstraction.Validatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CD extends Publication implements Validatable, Comparable<CD> {
    private String producer;
    private ArrayList<Song> songsList;

    public CD(){
        super();
        producer = "Producer";
        songsList = new ArrayList<>();
    }

    public CD(CD cd){
        super(cd);
        this.producer = new String(cd.producer);
        this.songsList = new ArrayList<>(cd.songsList);
        Collections.sort(this.songsList);
    }

    public CD(String name, int price, String ISBN, String producer){
        super(name, price, ISBN);
        this.producer = producer;
        this.songsList = new ArrayList<>();
    }

    public CD(String name, int price, String ISBN, String producer, ArrayList<Song> songsList){
        super(name, price, ISBN);
        this.producer = producer;
        this.songsList = new ArrayList<>(songsList);
        Collections.sort(this.songsList);
    }

    @Override
    public boolean isValid(){
        Pattern namePattern = Pattern.compile(".+");
        Matcher nameMatcher = namePattern.matcher(this.name);
        boolean nameB = nameMatcher.matches();

        Pattern pricePattern = Pattern.compile("\\d+(\\.\\d\\d)?");
        Matcher priceMatcher = pricePattern.matcher(String.valueOf(this.price));
        boolean priceB = priceMatcher.matches();

        Pattern ISBNPattern = Pattern.compile("\\d(-?\\d){12}");
        Matcher ISBNMatcher = ISBNPattern.matcher(this.ISBN);
        boolean ISBNB = ISBNMatcher.matches();

        Pattern producerPattern = Pattern.compile(".+");
        Matcher producerMatcher = producerPattern.matcher(this.producer);
        boolean producerB = producerMatcher.matches();

        boolean isSongsListEmpty = this.songsList.isEmpty();

        return nameB && priceB && ISBNB && producerB;
    }

    public String getProducer(){
        return producer;
    }

    public void setProducer(String producer){
        this.producer = producer;
    }

    public Song getSong(int number){
        return songsList.get(number);
    }

    public Song getSong(String title){
        Song song = null;
        for(Song songFE : songsList){
            if(songFE.getTitle().equals(title)){
                song = songFE;
            }
        }
        return song;
    }

    public ArrayList<Song> getSongsList(){
        return songsList;
    }

    public void setSong(int number, String title, String duration, ArrayList<Person> composersList, ArrayList<Person> performersList){
        Song p = new Song(title, duration, composersList, performersList);
        songsList.set(number, p);
        Collections.sort(songsList);
    }

    public void addSongsList(ArrayList<Song> songsList){
        this.songsList = new ArrayList<>(songsList);
        Collections.sort(this.songsList);
    }

    public void addToSongsList(String title, String duration, ArrayList<Person> composersList, ArrayList<Person> performersList){
        Song song = new Song(title, duration, composersList, performersList);
        songsList.add(song);
        Collections.sort(songsList);
    }

    public void addToSongsList(Song song){
        songsList.add(song);
        Collections.sort(songsList);
    }

    public void removeSong(int number){
        songsList.remove(number);
        Collections.sort(this.songsList);
    }

    public void removeSongsList(){
        songsList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        final CD tmp = (CD) obj;
        return tmp.name.equals(this.name) && tmp.price == this.price &&
                tmp.ISBN.equals(this.ISBN) && tmp.producer.equals(this.producer) && tmp.songsList.equals(this.songsList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.producer);
        hash = 23 * hash + Objects.hashCode(this.songsList);
        return hash;
    }

    @Override
    public String toString(){
        String list1 = "";
        int numeracja = 0;
        for(int i = 0; i < songsList.size(); i++){
            numeracja += 1;
            list1 += String.valueOf(numeracja) + ". " + "|"+ songsList.get(i);
        }
        return "CD" + "\r\n" +
                name + "|" +
                price + "|" +
                ISBN + "|" +
                producer +
                "\r\n" + songsList.size()+
                "\r\nList of Songs: \r\n" + list1 + "\r\n";
    }

    @Override
    public int compareTo(CD cd){
        int comparedCDs = this.name.compareTo(cd.name);
        if(comparedCDs == 0){
            Integer price2 = this.price;
            Integer price3 = cd.price;
            return price2.compareTo(price3);
        }
        else{
            return comparedCDs;
        }
    }
}

