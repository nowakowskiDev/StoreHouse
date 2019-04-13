package store_house.model;

import store_house.abstraction.Validatable;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person implements Validatable, Comparable<Person> {
    private String firstName;
    private String surname;
    private long PESEL;
    private String dateOfBirth;

    public Person(){
        firstName = "First Name";
        surname = "Surname";
        PESEL = 0;
        dateOfBirth = "Date of Birth";
    }

    public Person(Person person){
        this.firstName = new String(person.firstName);
        this.surname = new String(person.surname);
        this.PESEL = person.PESEL;
        this.dateOfBirth = new String(person.dateOfBirth);
    }

    public Person(String firstName, String surname, long PESEL, String dateOfBirth){
        this.firstName = firstName;
        this.surname = surname;
        this.PESEL = PESEL;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean isValid(){
        Pattern namePattern = Pattern.compile("[A-Z][a-z]+");
        Matcher nameMatcher = namePattern.matcher(this.firstName);
        boolean nameB = nameMatcher.matches();

        Pattern surnamePattern = Pattern.compile("[A-Z][a-z]+");
        Matcher surnameMatcher = surnamePattern.matcher(this.surname);
        boolean personSurnameB = surnameMatcher.matches();

        Pattern PESELPattern = Pattern.compile("\\d{11}");
        Matcher peselMatcher = PESELPattern.matcher(String.valueOf(this.PESEL));
        boolean PESELB = peselMatcher.matches();

        Pattern dateOfBirthPattern = Pattern.compile("[1-3]\\d\\.[0-1]\\d\\.[1-2][90]\\d\\d");
        Matcher dateOfBirthMatcher = dateOfBirthPattern.matcher(this.dateOfBirth);
        boolean dateOfBirth = nameMatcher.matches();

        return nameB && personSurnameB && PESELB && dateOfBirth;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public long getPESEL(){
        return PESEL;
    }

    public void setPESEL(long PESEL){
        this.PESEL = PESEL;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != this.getClass()) return false;
        final Person tmp = (Person) obj;
        return tmp.firstName.equals(this.firstName) && tmp.surname.equals(this.surname)
                && tmp.PESEL == this.PESEL && tmp.dateOfBirth.equals(this.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.surname);
        hash = (int) (59 * hash + this.PESEL);
        hash = 59 * hash + Objects.hashCode(this.dateOfBirth);
        return hash;
    }

    @Override
    public String toString() {
        return firstName + "|" +
                surname + "|" +
                PESEL + "|" +
                dateOfBirth + "\r\n";
    }

    @Override
    public int compareTo(Person p) {
        int compareSurname = this.surname.compareTo(p.surname);
        if(compareSurname == 0){
            return this.firstName.compareTo(p.firstName);
        }
        else{
            return compareSurname;
        }
    }
}
