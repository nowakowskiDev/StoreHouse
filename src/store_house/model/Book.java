package store_house.model;

import store_house.abstraction.Publication;
import store_house.abstraction.Validatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book extends Publication implements Validatable, Comparable<Book> {
    private String language;
    private int pagesAmount;
    private String publisher;
    private ArrayList<Person> authorsList;
    private ArrayList<Person> translatorsList;

    public Book(){
        super();
        language = "Language";
        pagesAmount = 0;
        publisher = "Publisher";
        authorsList = new ArrayList<>();
        translatorsList = new ArrayList<>();
        Collections.sort(this.authorsList);
        Collections.sort(this.translatorsList);
    }

    public Book(Book book){
        super(book);
        this.language = new String(book.language);
        this.pagesAmount = book.pagesAmount;
        this.publisher = new String(book.publisher);
        this.authorsList = new ArrayList<>(book.authorsList);
        this.translatorsList = new ArrayList<>(book.translatorsList);
        Collections.sort(this.authorsList);
        Collections.sort(this.translatorsList);

    }

    public Book(String name, int price, String ISBN, String language, int pagesAmount, String publisher){
        super(name, price, ISBN);
        this.language = language;
        this.pagesAmount = pagesAmount;
        this.publisher = publisher;
        this.authorsList = new ArrayList<>();
        this.translatorsList = new ArrayList<>();
    }

    public Book(String name, int price, String ISBN, String language, int pagesAmount, String publisher, ArrayList<Person> authorsList, ArrayList<Person> translatorsList){
        super(name, price, ISBN);
        this.language = language;
        this.pagesAmount = pagesAmount;
        this.publisher = publisher;
        this.authorsList = new ArrayList<>(authorsList);
        this.translatorsList = new ArrayList<>(translatorsList);
        Collections.sort(this.authorsList);
        Collections.sort(this.translatorsList);
    }

    @Override
    public boolean isValid(){
        Pattern namePattern = Pattern.compile(".+");
        Matcher nameMatcher = namePattern.matcher(this.name);
        boolean nameB = nameMatcher.matches();

        Pattern pricePattern = Pattern.compile("\\d+(\\.\\d\\d)?");
        Matcher priceMatcher = pricePattern.matcher(String.valueOf(price));
        boolean priceB = priceMatcher.matches();

        Pattern ISBNPattern = Pattern.compile("\\d(-?\\d){12}");
        Matcher ISBNMatcher = ISBNPattern.matcher(this.ISBN);
        boolean ISBNB = ISBNMatcher.matches();

        Pattern languagePattern = Pattern.compile("[a-zA-Z]+");
        Matcher languageMatcher = languagePattern.matcher(this.language);
        boolean languageB = languageMatcher.matches();

        Pattern pagesAmountPattern = Pattern.compile("\\d+");
        Matcher pagesAmountMatcher = pagesAmountPattern.matcher(String.valueOf(this.pagesAmount));
        boolean pagesAmountB = pagesAmountMatcher.matches();

        Pattern publisherPattern = Pattern.compile(".+");
        Matcher publisherMatcher = publisherPattern.matcher(this.publisher);
        boolean publisherB = publisherMatcher.matches();

        boolean isAuthorsListEmpty = this.authorsList.isEmpty();
        boolean isTranslatorsListEmpty = this.translatorsList.isEmpty();

        return nameB && priceB && ISBNB && languageB && pagesAmountB && publisherB && !isAuthorsListEmpty && !isTranslatorsListEmpty;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public int getPagesAmount(){
        return pagesAmount;
    }

    public void setPagesAmount(int pagesAmount){
        this.pagesAmount = pagesAmount;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
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
        Collections.sort(authorsList);
    }

    public void setAuthorsList(ArrayList<Person> authorsList){
        this.authorsList = new ArrayList<>(authorsList);
        Collections.sort(this.authorsList);
    }

    public void addAuthorList(ArrayList<Person> authorsList){
        this.authorsList = new ArrayList<>(authorsList);
        Collections.sort(this.authorsList);
    }

    public void addToAuthorsList(String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        authorsList.add(p);
        Collections.sort(authorsList);
    }

    public void addToAuthorsList(Person p){
        authorsList.add(p);
        Collections.sort(authorsList);
    }

    public void removeAuthor(int number){
        authorsList.remove(number);
        Collections.sort(authorsList);
    }

    public void removeAuthorsList(){
        authorsList = new ArrayList<>();
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
        Collections.sort(translatorsList);
    }

    public void setTranslatorsList(ArrayList<Person> translatorsList){
        this.translatorsList = translatorsList;
        Collections.sort(this.translatorsList);
    }

    public void addTranslatorsList(ArrayList<Person> translatorsList){
        this.translatorsList = new ArrayList<>(translatorsList);
        Collections.sort(this.translatorsList);
    }

    public void addTranlator(String firstName, String surname, int PESEL, String dateOfBirth){
        Person p = new Person(firstName, surname, PESEL, dateOfBirth);
        translatorsList.add(p);
        Collections.sort(translatorsList);
    }

    public void addTranslator(Person p){
        translatorsList.add(p);
        Collections.sort(translatorsList);
    }

    public void removeTranslator(int number){
        translatorsList.remove(number);
        Collections.sort(translatorsList);
    }

    public void removeTranslatorsList(){
        translatorsList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        final Book tmp = (Book) obj;
        return tmp.name.equals(this.name) && tmp.price == this.price &&
                tmp.ISBN.equals(this.ISBN) && tmp.language.equals(this.language) && tmp.pagesAmount == this.pagesAmount &&
                tmp.publisher.equals(this.publisher) && tmp.authorsList.equals(this.authorsList) && tmp.translatorsList.equals(this.translatorsList);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.language);
        hash = 83 * hash + this.pagesAmount;
        hash = 83 * hash + Objects.hashCode(this.publisher);
        hash = 83 * hash + Objects.hashCode(this.authorsList);
        hash = 83 * hash + Objects.hashCode(this.translatorsList);
        return hash;
    }

    @Override
    public String toString(){
        String list1 = "";
        for(int i = 0; i < authorsList.size(); i++){
            list1 += authorsList.get(i);
        }
        String list2 = "";
        for(int i = 0; i < translatorsList.size(); i++){
            list2 += translatorsList.get(i);
        }
        return "Book" + "\r\n" +
                name +
                "|" + price +
                "|" + ISBN +
                "|" + language +
                "|" + pagesAmount +
                "|" + publisher +
                "\r\n" +
                this.authorsList.size() +
                "\r\nAuthors: \r\n" + list1 +
                this.translatorsList.size() +
                "\r\nTranslators: \r\n" + list2 + "\r\n";
    }

    @Override
    public int compareTo(Book book) {
        int comparedNames = this.name.compareTo(book.name);
        if(comparedNames == 0){
            Integer price2 = this.price;
            Integer price3 = book.price;
            return price2.compareTo(price3);
        }
        else{
            return comparedNames;
        }
    }
}


