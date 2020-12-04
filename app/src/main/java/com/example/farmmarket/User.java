package com.example.farmmarket;

public class User {
    private String name;
    private String email;
    private String password;
    private String codeName;
    private boolean isAdmin;

    public User(String email) {
        this.email = email;
        isAdmin = false;
        codeName = "Not admin";
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public boolean getIsAdmin() {
        if(getCodeName().equals("bamidele")
                ||getCodeName().equals("chinedu")
                ||getCodeName().equals("yvonne")){
            setAdmin(true);
            return isAdmin;
        }
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
