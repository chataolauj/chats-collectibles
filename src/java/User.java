import java.io.*;

public class User implements Serializable{
    int userID;
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    String rolename;
    String addressOne;
    String addressTwo;
    String city;
    String state;
    String postCode;
    String country;
    
    public User() {
        firstName = "";
        lastName = "";
        email = "";
        username = "";
        password = "";
        rolename = "";
        addressOne = "";
        addressTwo = "";
        city = "";
        state = "";
        postCode = "";
        country = "";
    }
    
    public User(String firstName, String lastName, String email, String username, String password, String rolename, String addressOne, String addressTwo, String city, String state, String postCode, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rolename = rolename;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.country = country;
    }
    
    void setUserID(int userID) {
        this.userID = userID;
    }
    
    public int getUserID() {
        return userID;
    }
    
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getRolename() {
        return rolename;
    }
    
    void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }
    
    public String getAddressOne() {
        return addressOne;
    }
    
    void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }
    
    public String getAddressTwo() {
        return addressTwo;
    }
    
    void setCity(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
    
    void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    public String getPostCode() {
        return postCode;
    }
    
    void setCountry(String country) {
        this.country = country;
    }
    
    public String getCountry() {
        return country;
    }
}