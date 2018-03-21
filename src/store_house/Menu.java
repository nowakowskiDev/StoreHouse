package store_house;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    public static void menu() throws IOException {
        Scanner Sc = new Scanner(System.in);
        int choice = 1;
        StoreHouse storeHouse = new StoreHouse(loadStoreFromFile()); // Creation of the Store House and loading of data from the file to it
        while(choice != 0){
            displayMenuOptions();
            choice = Sc.nextInt();
            switch(choice){
                case 0: System.out.println("The program has been closed");
                    break;
                case 1: System.out.println("Enter the type of the publication: Book, NewsPaper, CD");
                    menuAddPublication(Sc.next(), storeHouse);
                    break;
                case 2: increaseAmountPublicationInMenu(storeHouse);
                    break;
                case 3: decreaseAmountPublicationInMenu(storeHouse);
                    break;
                case 4: loadStoreFromFileMenu(storeHouse);
                    break;
                case 5: displayPublication(storeHouse);
                    break;
                case 6: isPublicationValidMenu(storeHouse);
                    break;
                case 7: displayStoreHouse(storeHouse);
                    break;
                case 8: saveStoreToFile(storeHouse.toString());
                    break;
                case 9: addAuthorsToBookMenu(storeHouse);
                    break;
                case 10: addTranslatorsToBookMenu(storeHouse);
                    break;
                case 11: addArticlesToNewsPaperMenu(storeHouse);
                    break;
                case 12: addAuthorsToArticleMenu(storeHouse);
                    break;
                case 13: addTranslatorsToArticleMenu(storeHouse);
                    break;
                case 14: addSongsToCDMenu(storeHouse);
                    break;
                case 15: addComposersToSongMenu(storeHouse);
                    break;
                case 16: addPerformersToSongMenu(storeHouse);
                    break;
                default: System.out.println("An incorrect number has been entered. Try again, please");
            }
        }
    }

    public static void menuAddPublication(String publication, StoreHouse storeHouse){
        Scanner Sc = new Scanner(System.in);

        switch(publication){
            case "Book":
                PublicationsCreator.createBook(storeHouse, Sc);
                break;
            case "NewsPaper":
                PublicationsCreator.createNewsPaper(storeHouse, Sc);
                break;
            case "CD":
                PublicationsCreator.createCD(storeHouse, Sc);
                break;
        }
        System.out.println("The publication has been added successfully");
    }

    private static void displayMenuOptions(){
        System.out.println("|| Menu ||\n"
                + "Enter the number:\n0 to terminate the program\n1 to add an publication\n2 to increase amount of the selected publication"
                + "\n3 to remove the publication\n4 to load the Store House data from file"
                + "\n5 to display selected publication \n6 to check if the publication you selected is correctly saved in memory"
                + "\n7 to display the current state of the Store House \n8 to save the current state of the Store House to the file"
                + "\n9 to add the list of Authors to selected BOOK" + "\n10 to add the list of Translators to selected BOOK"
                + "\n11 to add the list of Articles to selected NEWS PAPER"
                + "\n12 to add the list of Authors to selected ARTICLE" + "\n13 to add the list of translators to selected ARTICLE"
                + "\n14 to add the list of songs to selected CD" + "\n15 to add the list of composers to selected SONG"
                + "\n16 to add the list of performers to the selected SONG");
    }

    private static void increaseAmountPublicationInMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the publication whose amount you want to increase and then after the ENTER the number by which you will increase the amount of this publication");
        Scanner Sc = new Scanner(System.in);
        String publication = Sc.nextLine();
        int amount1 = Sc.nextInt();
        if(storeHouse.getPublicationsList().containsKey(storeHouse.getPublication(publication))){
            storeHouse.addPublication(storeHouse.getPublication(publication), amount1);
            System.out.println("Amount of the publication \"" + publication + "\" in the Store House has been increased by " + amount1 + " pieces");
        }
        else{System.out.println("There is no publication in the Store House with this name");}
    }

    private static void decreaseAmountPublicationInMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the publication You want to remove");
        Scanner Sc = new Scanner(System.in);
        String name = Sc.nextLine();
        System.out.println("Enter the amount of the publication to be removed");
        int amount2 = Sc.nextInt();
        storeHouse.removePublication(storeHouse.getPublication(name), amount2);
        if (storeHouse.getPublicationsList().get(storeHouse.getPublication(name)) <= 0){
            System.out.println("Currently in the Store House there are 0 pieces of the publication with name " + storeHouse.getPublication(name).getName());
        }
        else{System.out.println("Currently in the Store House there are " + storeHouse.getPublicationsList().get(storeHouse.getPublication(name)) +
                " pieces of the publication with name " + storeHouse.getPublication(name).getName());}
    }

    private static void loadStoreFromFileMenu(StoreHouse storeHouse){
        try {
            storeHouse.setPublicationsList(loadStoreFromFile());
            System.out.println("The Store House was successfully loaded from the file");
        } catch (IOException ex) {
            ex.getMessage();
            System.out.println("Something went wrong...");
        }
    }

    private static void displayPublication(StoreHouse storeHouse){
        System.out.println("Enter the name of the publication You want to display");
        Scanner Sc = new Scanner(System.in);

        System.out.println(storeHouse.getPublication(Sc.nextLine()));
    }

    private static void isPublicationValidMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the publication, which you want to check for correct storage in memory");

        Scanner Sc = new Scanner(System.in);
        Publication publication1 = storeHouse.getPublication(Sc.nextLine());
        switch(publication1.getClass().getSimpleName()){
            case "Book": Book book = (Book) publication1;
                if(book.isValid()){System.out.println("The publication is saved correctly");}
                else{System.out.println("The publication is saved incorrectly");}
                break;
            case "NewsPaper": NewsPaper newsPaper = (NewsPaper) publication1;
                if(newsPaper.isValid()){System.out.println("The publication is saved correctly");}
                else{System.out.println("The publication is saved incorrectly");}
                break;
            case "CD": CD cd = (CD) publication1;
                if(cd.isValid()){System.out.println("The publication is saved correctly");}
                else{System.out.println("The publication is saved incorrectly");}
        }
    }

    private static void displayStoreHouse(StoreHouse storeHouse) {
        System.out.println(storeHouse);
    }

    private static void addAuthorsToBookMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the book You want to add the authors list to");

        Scanner Sc = new Scanner(System.in);
        String bookName = Sc.nextLine();
        System.out.println("Specify how many authors will be added to the book " + bookName);
        int authorsAmount = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < authorsAmount; i++){
            System.out.println("Enter the name of the book's author " + bookName);
            String firstName = Sc.nextLine();
            System.out.println("Enter the surname of the book's author " + bookName);
            String surname = Sc.nextLine();
            System.out.println("Enter the date of birth of the book's author " + bookName);
            String dateOfBirth = Sc.nextLine();
            System.out.println("Enter the pesel of the book's author " + bookName);
            int PESEL = Sc.nextInt();
            Sc.nextLine();// Discard the new line character from the buffer
            Person author = new Person(firstName, surname, PESEL, dateOfBirth);
            Publication publication2 = storeHouse.getPublication(bookName);
            Book book = (Book) publication2;
            book.addToAuthorsList(author);
        }
        System.out.println("Authors has been added to the list successfully");
    }

    private static void addTranslatorsToBookMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the book You want to add the translators list to");

        Scanner Sc = new Scanner(System.in);
        String bookName2 = Sc.nextLine();
        System.out.println("Specify how many translators will be added to the book " + bookName2);
        int translatorsAmount = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < translatorsAmount; i++){
            System.out.println("Enter the name of the book's translator  " + bookName2);
            String firstName = Sc.nextLine();
            System.out.println("Enter the name of the book's translator  " + bookName2);
            String surname = Sc.nextLine();
            System.out.println("Enter the name of the book's translator  " + bookName2);
            String dateOfBirth = Sc.nextLine();
            System.out.println("Enter the name of the book's translator  " + bookName2);
            int PESEL = Sc.nextInt();
            Sc.nextLine();// Discard the new line character from the buffer
            Person translator = new Person(firstName, surname, PESEL, dateOfBirth);
            Publication publication2 = storeHouse.getPublication(bookName2);
            Book book = (Book) publication2;
            book.addTranslator(translator);
        }
        System.out.println("Translators has been added to the list successfully");
    }

    private static void addArticlesToNewsPaperMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the news paper You want to add the articles list to");

        Scanner Sc = new Scanner(System.in);
        String newsPaperName = Sc.nextLine();
        System.out.println("Specify how many articles will be added to the news paper " + newsPaperName);
        int articlesAmount = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < articlesAmount; i++){
            System.out.println("Enter the name of the article from the news paper " + newsPaperName);
            String articleTitle = Sc.nextLine();
            Article article = new Article(articleTitle);
            Publication publicationNewsPaper = storeHouse.getPublication(newsPaperName);
            NewsPaper newsPaper = (NewsPaper) publicationNewsPaper;
            newsPaper.addToArticlesList(article);
        }
        System.out.println("Articles has been added to the list successfully");
    }

    private static void addAuthorsToArticleMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the newspaper from which the article comes");

        Scanner Sc = new Scanner(System.in);
        String newsPaperName2 = Sc.nextLine();
        System.out.println("Enter the title of article You want to add the authors list to");
        String articleTitle = Sc.nextLine();
        System.out.println("Specify how many authors will be added to the article " + articleTitle);
        int authorsAmount2 = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < authorsAmount2; i++){
            System.out.println("Enter the name of the article's author " + articleTitle);
            String firstName = Sc.nextLine();
            System.out.println("Enter the surname of the article's author " + articleTitle);
            String surname = Sc.nextLine();
            System.out.println("Enter the date of birth of the article's author " + articleTitle);
            String dateOfBirth = Sc.nextLine();
            System.out.println("Enter the pesel of the article's author " + articleTitle);
            int PESEL = Sc.nextInt();
            Sc.nextLine();// Discard the new line character from the buffer
            Person author = new Person(firstName, surname, PESEL, dateOfBirth);
            Publication publicationNewsPaper = storeHouse.getPublication(newsPaperName2);
            NewsPaper newsPaper = (NewsPaper) publicationNewsPaper;
            newsPaper.getArticle(articleTitle).addToAuthorsList(author);
        }
        System.out.println("Authors has been added to the list successfully");
    }

    private static void addTranslatorsToArticleMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the newspaper from which the article comes");

        Scanner Sc = new Scanner(System.in);
        String newsPaperName3 = Sc.nextLine();
        System.out.println("Enter the title of article You want to add the translators list to");
        String articleTitle2 = Sc.nextLine();
        System.out.println("Specify how many translators will be added to the article " + articleTitle2);
        int translatorsAmount2 = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < translatorsAmount2; i++){
            System.out.println("Enter the name of the article's translator " + articleTitle2);
            String firstName = Sc.nextLine();
            System.out.println("Enter the surname of the article's translator " + articleTitle2);
            String surname = Sc.nextLine();
            System.out.println("Enter the date of birth of the article's translator " + articleTitle2);
            String dateOfBirth = Sc.nextLine();
            System.out.println("Enter the pesel of the article's translator " + articleTitle2);
            int PESEL = Sc.nextInt();
            Sc.nextLine();// Discard the new line character from the buffer
            Person translator = new Person(firstName, surname, PESEL, dateOfBirth);
            Publication publicationNewsPaper = storeHouse.getPublication(newsPaperName3);
            NewsPaper newsPaper = (NewsPaper) publicationNewsPaper;
            newsPaper.getArticle(articleTitle2).addTranslator(translator);
        }
        System.out.println("Translators has been added to the list successfully");
    }

    private static void addSongsToCDMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the CD You want to add the songs list to");
        Scanner Sc = new Scanner(System.in);
        String cdName1 = Sc.nextLine();
        System.out.println("Specify how many songs will be added to the CD " + cdName1);
        int songsList = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        Publication publicationCD = storeHouse.getPublication(cdName1);
        CD cd1 = (CD) publicationCD;
        for(int i = 0; i < songsList; i++){
            System.out.println("Enter the title of the song");
            String songTitle = Sc.nextLine();
            System.out.println("Enter the duration of the song (minutes:seconds)");
            String songDuration = Sc.nextLine();
            Song song = new Song(songTitle, songDuration);
            cd1.addToSongsList(song);
        }
        System.out.println("Songs has been added to the list successfully");
    }

    private static void addComposersToSongMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the CD from which the song comes");

        Scanner Sc = new Scanner(System.in);
        String cdName2 = Sc.nextLine();
        System.out.println("Enter the title of the song you want to add a list of composers to");
        String songTitle = Sc.nextLine();
        System.out.println("Specify how many composers will be added to the song " + songTitle);
        int cdComposersAmount = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < cdComposersAmount; i++){
            System.out.println("Enter the name of the song's composer " + songTitle);
            String firstName = Sc.nextLine();
            System.out.println("Enter the surname of the song's composer " + songTitle);
            String surname = Sc.nextLine();
            System.out.println("Enter the date of birth of the song's composer " + songTitle);
            String dateOfBirth = Sc.nextLine();
            System.out.println("Enter the pesel of the song's composer " + songTitle);
            int PESEL = Sc.nextInt();
            Sc.nextLine();// Discard the new line character from the buffer
            Person composer = new Person(firstName, surname, PESEL, dateOfBirth);
            Publication publicationCD2 = storeHouse.getPublication(cdName2);
            CD cd2 = (CD) publicationCD2;
            cd2.getSong(songTitle).addToComposersList(composer);
        }
        System.out.println("Composers has been added to the list successfully");
    }

    private static void addPerformersToSongMenu(StoreHouse storeHouse){
        System.out.println("Enter the name of the CD from which the song comes");
        Scanner Sc = new Scanner(System.in);
        String cdName3 = Sc.nextLine();
        System.out.println("Enter the title of the song you want to add a list of performers to");
        String songTitle2 = Sc.nextLine();
        System.out.println("Specify how many performers will be added to the song " + songTitle2);
        int cdPerformersAmount = Sc.nextInt();
        Sc.nextLine(); // Discard the new line character from the buffer
        for(int i = 0; i < cdPerformersAmount; i++){
            System.out.println("Enter the name of the song's performer " + songTitle2);
            String firstName = Sc.nextLine();
            System.out.println("Enter the surname of the song's performer " + songTitle2);
            String surname = Sc.nextLine();
            System.out.println("Enter the date of birth of the song's performer " + songTitle2);
            String dateOfBirth = Sc.nextLine();
            System.out.println("Enter the pesel of the song's performer " + songTitle2);
            int PESEL = Sc.nextInt();
            Sc.nextLine();// Discard the new line character from the buffer
            Person performer = new Person(firstName, surname, PESEL, dateOfBirth);
            Publication publicationCD2 = storeHouse.getPublication(cdName3);
            CD cd2 = (CD) publicationCD2;
            cd2.getSong(songTitle2).addPerformer(performer);
        }
        System.out.println("Performers has been added to the list successfully");
    }

    private static void saveStoreToFile(String publicationsList) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("Database (list of publications).txt"));

        writer.write(publicationsList);

        writer.close();
        System.out.println("The state of the Store House has been saved successfully");
    }

    private static HashMap<Publication, Integer> loadStoreFromFile() throws FileNotFoundException, IOException{

        BufferedReader reader = new BufferedReader(new FileReader("Database (list of publications).txt"));
        StoreHouse storeHouseToAdd = new StoreHouse();
        reader.readLine(); // The line with title "StoreHouse"
        String stmp;
        while((stmp = reader.readLine()) != null){
            switch(stmp){
                case "Book": addBookToStoreHouse(reader, storeHouseToAdd);
                    break;
                case "News Paper": addNewsPaperToStoreHouse(reader, storeHouseToAdd);
                    break;
                case "CD": addCDToStoreHouse(reader, storeHouseToAdd);
                    break;
            }
        }
        reader.close();
        return storeHouseToAdd.getPublicationsList();
    }


    public static void addBookToStoreHouse(BufferedReader loadReader, StoreHouse loadStoreHouse) throws IOException{
        Book book = new Book();
        String line = loadReader.readLine(); // Line under the "BOOK"
        String[] tokenizer = line.split("\\|");
        book.setName(tokenizer[0]);
        book.setPrice(Integer.parseInt(tokenizer[1]));
        book.setISBN(tokenizer[2]);
        book.setLanguage(tokenizer[3]);
        book.setPagesAmount(Integer.parseInt(tokenizer[4]));
        book.setPublisher(tokenizer[5]);

        book.addAuthorList(createPersonsList(loadReader)); // Using method createPersonsList() to create the list of Authors
        book.addTranslatorsList(createPersonsList(loadReader)); // Using method createPersonsList() to create the list of Translators
        loadReader.readLine();
        String line2 = loadReader.readLine();
        String[] tokenizer2 = line2.split("\\|");
        loadStoreHouse.addPublication(book, Integer.parseInt(tokenizer2[1]));
        loadReader.readLine();// The line with =============
        loadReader.readLine();
    }

    public static void addNewsPaperToStoreHouse(BufferedReader loadReader, StoreHouse loadStoreHouse) throws IOException{
        NewsPaper NP = new NewsPaper();
        String line = loadReader.readLine();
        String[] tokenizer = line.split("\\|");
        NP.setName(tokenizer[0]);
        NP.setPrice(Integer.parseInt(tokenizer[1]));
        NP.setISBN(tokenizer[2]);
        NP.setLanguage(tokenizer[3]);
        NP.setPagesAmount(Integer.parseInt(tokenizer[4]));
        NP.setPublisher(tokenizer[5]);
        NP.setType(tokenizer[6]);
        int articlesListLength = Integer.parseInt(loadReader.readLine());
        loadReader.readLine(); // The line with title "Articles"
        ArrayList<Article> articlesList = new ArrayList<>();
        for(int i = 0; i < articlesListLength; i++){
            Article article = new Article();
            String line2 = loadReader.readLine();
            String[] tokenizer2 = line2.split("\\|");

            article.setTitle(tokenizer2[2]);

            article.setAuthorsList(createPersonsList(loadReader)); // Using method createPersonsList() to create list of Authors
            article.setTranslatorsList(createPersonsList(loadReader)); // Using method createPersonsList() to create list of Translators
            articlesList.add(article);

        }
        NP.addArticlesList(articlesList);
        loadReader.readLine();
        String line3 = loadReader.readLine();
        String[] tokenizer3 = line3.split("\\|");
        loadStoreHouse.addPublication(NP, Integer.parseInt(tokenizer3[1]));
        loadReader.readLine(); // The line with =============
        loadReader.readLine();
    }

    public static void addCDToStoreHouse(BufferedReader loadReader, StoreHouse loadStoreHouse) throws IOException{
        CD cd = new CD();
        String line = loadReader.readLine();
        String[] tokenizer = line.split("\\|");
        cd.setName(tokenizer[0]);
        cd.setPrice(Integer.parseInt(tokenizer[1]));
        cd.setISBN(tokenizer[2]);
        cd.setProducer(tokenizer[3]);
        int songsListLength = Integer.parseInt(loadReader.readLine());
        loadReader.readLine(); // The line with title "List of Songs"
        ArrayList<Song> songsList = new ArrayList<>();
        for(int i = 0; i < songsListLength; i++){
            Song song = new Song();
            String line2 = loadReader.readLine();
            String[] tokenizer2 = line2.split("\\|");
            song.setTitle(tokenizer2[2]);
            String line3 = loadReader.readLine();
            String[] tokenizer3 = line3.split("\\|");
            song.setDuration(tokenizer3[1]);

            song.setComposersList(createPersonsList(loadReader));
            song.setPerformersList(createPersonsList(loadReader));
            songsList.add(song);
        }
        cd.addSongsList(songsList);
        loadReader.readLine();
        String line2 = loadReader.readLine();
        String[] tokenizer4 = line2.split("\\|");
        loadStoreHouse.addPublication(cd, Integer.parseInt(tokenizer4[1]));
        loadReader.readLine(); // The line with ============
        loadReader.readLine();
    }

    public static ArrayList<Person> createPersonsList(BufferedReader loadReader2) throws IOException{
        int personsListLength = Integer.parseInt(loadReader2.readLine()); // Reading the length of the Authors list. Remember about implement the list length in the file.
        ArrayList<Person> personsList = new ArrayList<>();
        loadReader2.readLine(); // The line with the name of a person: Author, Translator etc.
        for(int i = 0; i < personsListLength; i++){
            Person person = new Person();
            String line = loadReader2.readLine();
            String[] tokenizer = line.split("\\|");
            person.setFirstName(tokenizer[0]);
            person.setSurname(tokenizer[1]);
            person.setPESEL(Integer.parseInt(tokenizer[2]));
            person.setDateOfBirth(tokenizer[3]);
            personsList.add(person);
        }
        return personsList;
    }
}
