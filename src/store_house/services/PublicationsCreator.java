package store_house.services;

import store_house.model.Book;
import store_house.model.CD;
import store_house.model.NewsPaper;
import store_house.model.StoreHouse;

import java.util.Scanner;

public class PublicationsCreator {

    public static void createBook(StoreHouse storeHouse, Scanner sc){
        Book book = new Book();
        System.out.println("Enter the name of the Book");
        book.setName(sc.nextLine());
        System.out.println("Enter the price of the Book in cents");
        book.setPrice(sc.nextInt());
        System.out.println("Enter the ISBN of the Book");
        book.setISBN(sc.next());
        System.out.println("Enter the language of the Book");
        book.setLanguage(sc.next());
        System.out.println("Enter pages amount of the Book");
        book.setPagesAmount(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter the publisher of the Book");
        book.setPublisher(sc.nextLine());
        System.out.println("Enter the amount of publication you want to add to the Store House");
        storeHouse.addPublication(book, sc.nextInt());
    }

    public static void createCD(StoreHouse storeHouse, Scanner sc){
        CD cd = new CD();
        System.out.println("Enter the name of the CD");
        cd.setName(sc.nextLine());
        System.out.println("Enter the price of the CD in cents");
        cd.setPrice(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter the ISBN of the CD");
        cd.setISBN(sc.nextLine());
        System.out.println("Enter the producer of the CD");
        cd.setProducer(sc.nextLine());
        System.out.println("Enter the amount of publication you want to add to the Magazine");
        storeHouse.addPublication(cd, sc.nextInt());
    }

    public static void createNewsPaper(StoreHouse storeHouse, Scanner sc){
        NewsPaper newsPaper = new NewsPaper();
        System.out.println("Enter the name of the News Paper");
        newsPaper.setName(sc.nextLine());
        System.out.println("Enter the price of the News Paper in cents");
        newsPaper.setPrice(sc.nextInt());
        System.out.println("Enter the ISBN of the News Paper");
        newsPaper.setISBN(sc.next());
        System.out.println("Enter the language of the News Paper");
        newsPaper.setLanguage(sc.next());
        System.out.println("Enter pages amount of the News Paper");
        newsPaper.setPagesAmount(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter the publisher of the News Paper");
        newsPaper.setPublisher(sc.nextLine());
        System.out.println("Enter the type of the News Paper: Diary, Weekly, Monthly");
        newsPaper.setType(sc.next());
        System.out.println("Enter the amount of publication you want to add to the Store House");
        storeHouse.addPublication(newsPaper, sc.nextInt());
    }
}
