package Entity;

public class Person {
    public String name;
    private String password;
    private String email;

    public Person(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
        this.email = null;
    }

    public Person() {
        this.name = null;
        this.password = null;
        this.email = null;
    }

    public String getName() {return name;}
    public void setName(String s) {this.name = s;}

    public String getPassword() {return password;}
    public void setPassword(String s) {this.password = s;}

    public String getEmail() {return email;}
    public void setEmail(String s) {this.email = s;}


}
