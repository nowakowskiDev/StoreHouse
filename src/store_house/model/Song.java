package store_house.model;

import store_house.abstraction.Validatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Song implements Validatable, Comparable<Song>{
    private String title;
    private String duration;
    private ArrayList<Person> composersList;
    private ArrayList<Person> performersList;

    public Song(){
        title = "Title";
        duration = "Duration";
        composersList = new ArrayList<>();
        performersList = new ArrayList<>();
    }

    public Song(Song song){
        this.title = new String(song.title);
        this.duration = new String(song.duration);
        composersList = new ArrayList<>(song.composersList);
        performersList = new ArrayList<>(song.performersList);
        Collections.sort(this.composersList);
        Collections.sort(this.performersList);
    }

    public Song(String title, String duration){
        this.title = title;
        this.duration = duration;
        this.composersList = new ArrayList<>();
        this.performersList = new ArrayList<>();
    }

    public Song(String title, String duration, ArrayList<Person> composersList, ArrayList<Person> performersList){
        this.title = title;
        this.duration = duration;
        this.composersList = new ArrayList<>(composersList);
        this.performersList = new ArrayList<>(performersList);
        Collections.sort(this.composersList);
        Collections.sort(this.performersList);
    }

    public boolean isValid(){
        Pattern titlePattern = Pattern.compile(".+");
        Matcher titleMatcher = titlePattern.matcher(this.title);
        boolean titleB = titleMatcher.matches();

        Pattern durationPattern = Pattern.compile("\\d\\d(\\.\\d\\d)?");
        Matcher durationMatcher = durationPattern.matcher(this.duration);
        boolean durationB = durationMatcher.matches();

        boolean isComposersListEmpty = this.composersList.isEmpty();
        boolean isPerformersListEmpty = this.performersList.isEmpty();

        return titleB && durationB && !isComposersListEmpty && !isPerformersListEmpty;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDuration(){
        return duration;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }

    public Person getComposer(int number){
        return composersList.get(number);
    }

    public ArrayList<Person> getComposerList(){
        return composersList;
    }

    public void setComposers(int number, String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        composersList.set(number, p);
        Collections.sort(composersList);
    }

    public void setComposersList(ArrayList<Person> composersList){
        this.composersList = new ArrayList<>(composersList);
        Collections.sort(this.composersList);
    }

    public void addComposersList(ArrayList<Person> composersList){
        this.composersList = new ArrayList<>(composersList);
        Collections.sort(this.composersList);
    }

    public void addToComposersList(String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        composersList.add(p);
        Collections.sort(composersList);
    }

    public void addToComposersList(Person p){
        composersList.add(p);
        Collections.sort(this.composersList);
    }

    public void removeComposer(int number){
        composersList.remove(number);
        Collections.sort(composersList);
    }

    public void removeComposersList(){
        composersList = new ArrayList<>();
        Collections.sort(composersList);
    }

    public Person getPerformer(int number){
        return performersList.get(number);
    }

    public ArrayList<Person> getPerformersList(){
        return performersList;
    }

    public void setPerformer(int number, String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        performersList.set(number, p);
        Collections.sort(performersList);
    }

    public void setPerformersList(ArrayList<Person> performersList){
        this.performersList = new ArrayList<>(performersList);
        Collections.sort(this.performersList);
    }

    public void addPerformersList(ArrayList<Person> performersList){
        this.performersList = new ArrayList<>(performersList);
        Collections.sort(this.performersList);
    }

    public void addPerformer(String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        performersList.add(p);
        Collections.sort(performersList);
    }

    public void addPerformer(Person p){
        performersList.add(p);
        Collections.sort(performersList);
    }

    public void removePerformer(int number){
        performersList.remove(number);
        Collections.sort(performersList);
    }

    public void removePerformersList(){
        performersList = new ArrayList<>();
        Collections.sort(performersList);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        final Song tmp = (Song) obj;
        return tmp.title.equals(this.title) && tmp.duration.equals(obj) && tmp.composersList.equals(this.composersList) &&
                tmp.performersList.equals(this.performersList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.duration);
        hash = 37 * hash + Objects.hashCode(this.composersList);
        hash = 37 * hash + Objects.hashCode(this.performersList);
        return hash;
    }

    @Override
    public String toString(){
        String list1 = "";
        String list2 = "";
        for(int i = 0; i < composersList.size(); i++){
            list1 += composersList.get(i);
        }
        for(int i = 0; i < performersList.size(); i++){
            list2 += performersList.get(i);
        }
        return "Title: " + "|" +
                title +
                "\r\nDuration: " + "|" + duration +
                "\r\n" + composersList.size() +
                "\r\nComposers: \r\n" + list1 +
                performersList.size() +
                "\r\nPerformers: \r\n" + list2;
    }

    @Override
    public int compareTo(Song song) {
        int comparedSongs = this.title.compareTo(song.title);
        return comparedSongs;
    }
}
