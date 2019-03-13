package store_house;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article implements Validatable, Comparable<Article> {
    private String title;
    private ArrayList<Person> authorsList;
    private ArrayList<Person> translatorsList;

    public Article(){
        title = "Title";
        authorsList = new ArrayList<>();
        translatorsList = new ArrayList<>();
    }

    public Article(Article article){
        this.title = new String(article.title);
        this.authorsList = new ArrayList(article.authorsList);
        this.translatorsList = new ArrayList(article.translatorsList);
        Collections.sort(this.authorsList);
        Collections.sort(this.translatorsList);

    }

    public Article(String title){
        this.title = title;
        authorsList = new ArrayList<>();
        translatorsList = new ArrayList<>();
    }

    public Article(String title, ArrayList<Person> authorsList, ArrayList<Person> translatorsList){
        this.title = title;
        this.authorsList = new ArrayList<>(authorsList);
        this.translatorsList = new ArrayList<>(translatorsList);
        Collections.sort(this.authorsList);
        Collections.sort(this.translatorsList);
    }

    @Override
    public boolean isValid(){
        Pattern titlePattern = Pattern.compile(".+");
        Matcher titleMatcher = titlePattern.matcher(this.title);
        boolean titleB = titleMatcher.matches();

        boolean isAuthorsListEmpty = this.authorsList.isEmpty();
        boolean isTranslatorsListEmpty = this.translatorsList.isEmpty();

        return titleB && !isAuthorsListEmpty && !isTranslatorsListEmpty;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Person getAuthor(int number){
        return authorsList.get(number);
    }

    public ArrayList<Person> getAuthorsList(){
        return authorsList;
    }

    public void setAuthor(int number, String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        authorsList.set(number, p);
        Collections.sort(this.authorsList);
    }

    public void setAuthorsList(ArrayList<Person> authorsList){
        this.authorsList = authorsList;
        Collections.sort(this.authorsList);
    }

    public void addToAuthorsList(String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        authorsList.add(p);
        Collections.sort(this.authorsList);
    }

    public void addToAuthorsList(Person p){
        authorsList.add(p);
        Collections.sort(this.authorsList);
    }

    public void removeAuthor(int number){
        authorsList.remove(number);
        Collections.sort(this.authorsList);
    }

    public void removeAuthorsList(){
        authorsList = new ArrayList<>();
        Collections.sort(this.authorsList);
    }

    public Person getTranslator(int number){
        return translatorsList.get(number);
    }

    public ArrayList<Person> getTranslatorsList(){
        return translatorsList;
    }

    public void setTranslator(int number, String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        translatorsList.set(number, p);
        Collections.sort(this.translatorsList);
    }

    public void setTranslatorsList(ArrayList<Person> translatorsList){
        this.translatorsList = translatorsList;
        Collections.sort(this.translatorsList);
    }

    public void addTranslator(String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        translatorsList.add(p);
        Collections.sort(this.translatorsList);
    }

    public void addTranslator(Person p){
        translatorsList.add(p);
        Collections.sort(this.translatorsList);
    }

    public void removeTranslator(int number){
        translatorsList.remove(number);
        Collections.sort(this.translatorsList);
    }

    public void removeTranslatorsList(){
        translatorsList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != this.getClass()) return false;
        final Article tmp = (Article) obj;
        return tmp.title.equals(this.title) && tmp.authorsList.equals(this.authorsList)
                && tmp.translatorsList.equals(this.translatorsList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.authorsList);
        hash = 53 * hash + Objects.hashCode(this.translatorsList);
        return hash;
    }

    @Override
    public String toString(){
        String list1 = "";
        String list2 = "";
        for(int i = 0; i < authorsList.size(); i++){
            list1 += authorsList.get(i);
        }
        for(int i = 0; i < translatorsList.size(); i++){
            list2 += translatorsList.get(i);
        }
        return "Title: " + "|" +
                title +
                "\r\n" +
                this.authorsList.size() +
                "\r\nAuthors: \r\n" + list1 +
                this.translatorsList.size() +
                "\r\nTranslators: \r\n" + list2;

    }

    @Override
    public int compareTo(Article article){
        int comparedArticles = this.title.compareTo(article.title);
        return comparedArticles;
    }
}
