package com.birzeit.assigment2.Models;

public class CV {

    private String Name;
    private String Gender;
    private String MotherLang;
    private String Address;
    private String Email;
    private String Phone;
    private String WorkExp;
    private String Skills;

    public CV() {
    }

    public CV(String Name, String gender, String motherLang, String address, String email, String phone, String workExp, String skills) {
        this.Name = Name;
        this.Gender = gender;
        this.MotherLang = motherLang;
        this.Address = address;
        this.Email = email;
        this.Phone = phone;
        this.WorkExp = workExp;
        this.Skills = skills;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getMotherLang() {
        return MotherLang;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getWorkExp() {
        return WorkExp;
    }

    public String getSkills() {
        return Skills;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public void setMotherLang(String motherLang) {
        this.MotherLang = motherLang;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public void setWorkExp(String workExp) {
        this.WorkExp = workExp;
    }

    public void setSkills(String skills) {
        this.Skills = skills;
    }
}
