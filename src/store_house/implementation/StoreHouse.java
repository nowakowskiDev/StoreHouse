package store_house;

import java.util.HashMap;
import java.util.Map;

public class StoreHouse {
    private HashMap<Publication, Integer>  publicationsList;

    public StoreHouse(){
        publicationsList = new HashMap<>();
    }

    public StoreHouse(HashMap<Publication, Integer> publicationsList){
        this.publicationsList = new HashMap<>(publicationsList);
    }

    public StoreHouse(StoreHouse publicationsList){
        this.publicationsList = new HashMap<>(publicationsList.getPublicationsList());
    }

    public HashMap<Publication, Integer> getPublicationsList(){
        return this.publicationsList;
    }

    public void setPublicationsList(HashMap<Publication, Integer> publicationsList){
        this.publicationsList = new HashMap<>(publicationsList);
    }

    public void addPublication(Publication publication){
        if(containsPublication(publication)){
            increasePublicationsAmount(publication, 1);
        }
        else{
            publicationsList.put(publication, 1);
        }
    }

    public void addPublication(Publication publication, int amount){
        if(containsPublication(publication)){
            increasePublicationsAmount(publication, amount);
        }
        else{
            publicationsList.put(publication, amount);
        }
    }

    public void removePublication(Publication publication){
        if(isPublicationEmpty(publication)){
            return;
        }
        if(isPublicationsAmountEqualsONE(publication)){
            publicationsList.put(publication, 0);
        }
        if(isPublicationsAmountMoreThanONE(publication)){
            decreasePublicationsAmountByONE(publication);
        }
    }

    public void removePublication(Publication publication, int amount){
        if(amount == 0) return;
        if(isPublicationEmpty(publication)){
            return;
        }
        if(isPublicationsAmountEqualsONE(publication) && amount != 0){
            publicationsList.put(publication, 0);
        }
        if(isPublicationsAmountMoreThanONE(publication)){
            decreasePublicationsAmount(publication, amount);
        }
        if(isPublicationsAmountLessThanZERO(publication)){
            publicationsList.put(publication, 0);
        }
    }



    private boolean containsPublication(Publication publication) {
        return publicationsList.containsKey(publication);
    }

    private Integer increasePublicationsAmount(Publication publication, int amount) {
        return publicationsList.put(publication, publicationsList.get(publication) + amount);
    }

    private Integer decreasePublicationsAmount(Publication publication, int amount) {
        return publicationsList.put(publication, publicationsList.get(publication) - amount);
    }

    private Integer decreasePublicationsAmountByONE(Publication publication) {
        return decreasePublicationsAmount(publication, 1);
    }

    private boolean isPublicationEmpty(Publication publication) {
        return isPublicationsAmountEqualsZERO(publication);
    }

    private boolean isPublicationsAmountEqualsZERO(Publication publication) {
        return containsPublication(publication) && publicationsList.get(publication) == 0;
    }

    private boolean isPublicationsAmountLessThanZERO(Publication publication) {
        return publicationsList.get(publication) < 0;
    }

    private boolean isPublicationsAmountEqualsONE(Publication publication) {
        return containsPublication(publication) && publicationsList.get(publication) == 1;
    }

    private boolean isPublicationsAmountMoreThanONE(Publication publication) {
        return containsPublication(publication) && publicationsList.get(publication) > 1;
    }



    public Publication getPublication(String name){
        Publication publication = null;
        for(Map.Entry<Publication, Integer> entry : publicationsList.entrySet()){
            if((entry.getKey().getName()).equals(name)){
                publication = entry.getKey();
            }
        }
        return publication;
    }

    @Override
    public String toString(){
        String list = "";
        for(Map.Entry<Publication, Integer> entry : publicationsList.entrySet()){
            if(entry.getValue() <= 0){
                continue;
            }
            else{
                list += entry.getKey() + "Amount: " + "|" + entry.getValue() + "|" + "pieces in the StoreHouse" + "\r\n===========================\r\n\r\n";
            }

        }
        return "|| StoreHouse || \r\n" + list;
    }
}
