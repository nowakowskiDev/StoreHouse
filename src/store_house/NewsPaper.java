package store_house;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsPaper extends Publication implements Validatable, Comparable<NewsPaper> {
    private String language;
    private  int pagesAmount;
    private String publisher;
    private NPEnums type;
    private ArrayList<Article> articlesList;

    public NewsPaper(){
        super();
        language = "Language";
        pagesAmount = 0;
        publisher = "Publisher";
        type = NPEnums.Type;
        articlesList = new ArrayList<>();
    }

    public NewsPaper(NewsPaper newsPaper){
        super(newsPaper);
        this.language = new String(newsPaper.language);
        this.pagesAmount = newsPaper.pagesAmount;
        this.publisher = new String(newsPaper.publisher);
        this.articlesList = new ArrayList<>(newsPaper.articlesList);
        Collections.sort(articlesList);

    }

    public NewsPaper(String name, int price, String ISBN, String language, int pagesAmount, String publisher, NPEnums type){
        super(name, price, ISBN);
        this.language = language;
        this.pagesAmount = pagesAmount;
        this.publisher = publisher;
        this.type = type;
        this.articlesList = new ArrayList<>();
    }

    public NewsPaper(String name, int price, String ISBN, String language, int pagesAmount, String publisher, NPEnums type, ArrayList<Article> articlesList){
        super(name, price, ISBN);
        this.language = language;
        this.pagesAmount = pagesAmount;
        this.publisher = publisher;
        this.type = type;
        this.articlesList = new ArrayList<>(articlesList);
        Collections.sort(articlesList);
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

        Pattern languagePattern = Pattern.compile("[a-zA-Z]+");
        Matcher languageMatcher = languagePattern.matcher(this.language);
        boolean languageB = languageMatcher.matches();

        Pattern pagesAmountPattern = Pattern.compile("\\d+");
        Matcher pagesAmountMatcher = pagesAmountPattern.matcher(String.valueOf(this.pagesAmount));
        boolean pagesAmountB = pagesAmountMatcher.matches();

        Pattern publisherPattern = Pattern.compile(".+");
        Matcher publisherMatcher = publisherPattern.matcher(this.publisher);
        boolean publisherB = publisherMatcher.matches();

        boolean isArticlesListEmpty = this.articlesList.isEmpty();

        return nameB && priceB && ISBNB && languageB && pagesAmountB && publisherB && !isArticlesListEmpty;
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

    public NPEnums getType(String type){
        return NPEnums.valueOf(type);
    }

    public void setType(String type){
        this.type = NPEnums.valueOf(type);
    }

    public Article getArticle(int number){
        return articlesList.get(number);
    }

    public Article getArticle(String title) {
        Article article = null;
        for(Article articleRBF : this.articlesList){
            if(articleRBF.getTitle().equals(title)){
                article = articleRBF;
            }
        }
        return article;
    }

    public ArrayList<Article> getArticlesList(){
        return articlesList;
    }

    public void setArticle(int number, String title, ArrayList<Person> authorsList, ArrayList<Person> translatorsList){
        Article article = new Article(title, authorsList, translatorsList);
        articlesList.set(number, article);
        Collections.sort(articlesList);
    }

    public void addArticlesList(ArrayList<Article> articlesList){
        this.articlesList = new ArrayList<>(articlesList);
        Collections.sort(articlesList);
    }

    public void addToArticlesList(String title, ArrayList<Person> authorsList, ArrayList<Person> translatorsList){
        Article article = new Article(title, authorsList, translatorsList);
        articlesList.add(article);
        Collections.sort(articlesList);
    }

    public void addToArticlesList(Article article){
        articlesList.add(article);
        Collections.sort(articlesList);
    }

    public void removeArticle(int number){
        articlesList.remove(number);
        Collections.sort(articlesList);
    }

    public void removeArticlesList(){
        articlesList = new ArrayList<>();
        Collections.sort(articlesList);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != this.getClass()) return false;
        final NewsPaper tmp = (NewsPaper) obj;
        return tmp.name.equals(this.name) && tmp.price == this.price && tmp.ISBN.equals(this.ISBN) &&
                tmp.language.equals(this.ISBN) && tmp.pagesAmount == this.pagesAmount && tmp.publisher.equals(obj)
                && tmp.type.equals(this.type) && tmp.articlesList.equals(this.articlesList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.language);
        hash = 43 * hash + this.pagesAmount;
        hash = 43 * hash + Objects.hashCode(this.publisher);
        hash = 43 * hash + Objects.hashCode(this.type);
        hash = 43 * hash + Objects.hashCode(this.articlesList);
        return hash;
    }

    @Override
    public String toString(){
        String lista1 = "";
        int numeracja = 0;
        for(int i = 0; i < articlesList.size(); i++){
            numeracja += 1;
            lista1 += String.valueOf(numeracja) + ". " + "|" + articlesList.get(i);
        }
        return "News Paper" + "\r\n" +
                name + "|" +
                price + "|" +
                ISBN + "|" +
                language + "|" +
                pagesAmount + "|" +
                publisher + "|" +
                type +
                "\r\n" + this.articlesList.size() +
                "\r\nArticles: \r\n" + lista1 + "\r\n";
    }

    @Override
    public int compareTo(NewsPaper newsPaper) {
        int comparedNames = this.name.compareTo(newsPaper.name);
        if(comparedNames == 0){
            Integer price2 = this.price;
            Integer price3 = newsPaper .price;
            return price2.compareTo(price3);
        }
        else{
            return comparedNames;
        }
    }

}



