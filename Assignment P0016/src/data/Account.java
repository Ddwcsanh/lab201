package data;

public class Account {

    private String accName;
    private String passwd;
    private String role;

    public Account() {
    }

    public Account(String accName, String passwd, String role) {
        this.accName = accName;
        this.passwd = passwd;
        this.role = role;
    }

    public String getAccName() {
        return accName;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getRole() {
        return role;
    }

}
