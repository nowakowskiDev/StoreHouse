package store_house;

public abstract class Publication {
    protected String name;
    protected int price;
    protected String ISBN;

    public Publication(){
        name = "Name";
        price = 0;
        ISBN = "Number";
    }
    public Publication(Publication publication){
        this.name = new String(publication.name);
        this.price = publication.price;
        this.ISBN = new String(publication.ISBN);
    }
    public Publication(String name, int price, String ISBN){
        this.name = name;
        this.price = price;
        this.ISBN = ISBN;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getPrice(){
        return this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    public String getISBN(){
        return this.ISBN;
    }
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
}