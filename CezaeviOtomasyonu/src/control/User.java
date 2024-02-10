package control;

import DB_Item.DBConnection;

public class User {

    private int id;
    private String tckn, name, password, type;
    DBConnection conn = new DBConnection();

    public User(int id, String tckn, String name) {
        this.id = id;
        this.tckn = tckn;
        this.name = name;
    }

    public User(int id, String tckn, String name, String password, String type) {
        this.id = id;
        this.tckn = tckn;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTckn() {
        return tckn;
    }

    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    

}
