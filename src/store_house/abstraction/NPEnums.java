package store_house.abstraction;

public enum NPEnums {
    Type("Type"),
    Diary("Diary"),
    Weekly("Weekly"),
    Monthly("Monthly");

    private String value;

    NPEnums(String value){
        this.value = value;
    }

    public String getType(){
        return value;
    }
    public void setType(String type){
        value = type;
    }
}