package com.example.e_sale.ui.reg;

public class User {
//    public String firstName;
//    public String lastName;

    public String userName;
    public String email;
    public String password;
    public  String phone;
    public  String address;

//    public String text;
//    public String dateOfBirth;

    public User() {
        // Default constructor required for Firebase
    }

//    public User(String firstName, String lastName, String email,String password) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//    }

    public void AccInfo(String userName,String email,String phone,String address,String password)
    {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;

    }

//    public void TextFiles(String text)
//    {
//        this.text = text;
//    }

//    public String getFirstName() {
//        return firstName;
//    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }

//    public String getLastName() {
//        return lastName;
//    }

//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
