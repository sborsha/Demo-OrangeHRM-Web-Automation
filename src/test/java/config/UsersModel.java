package config;

public class UsersModel {
    private String firstname;
    private String lastname;
    private String empID;
    private String username;
    private String password;

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }
    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UsersModel(String firstname, String lastname,String empID, String username, String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.empID = empID;
        this.username=username;
        this.password=password;
    }
    public UsersModel(){

    }
}
