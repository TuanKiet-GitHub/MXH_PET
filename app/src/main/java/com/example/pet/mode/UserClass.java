package com.example.pet.mode;

public class UserClass {
     String username , password  , sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public UserClass(String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public UserClass(String username, String password , String sdt) {
        this.username = username;
        this.password = password;
        this.sdt = sdt ;
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
}
