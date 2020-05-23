package com.example.rohiniparseresponcedemo;

public class ModelRecycler {

    String fname,lname,Subject_Name,SubSubject_Name;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSubject_Name() {
        return Subject_Name;
    }

    public void setSubject_Name(String subject_Name) {
        Subject_Name = subject_Name;
    }

    public String getSubSubject_Name() {
        return SubSubject_Name;
    }

    public void setSubSubject_Name(String subSubject_Name) {
        SubSubject_Name = subSubject_Name;
    }

    @Override
    public String toString() {
        return "ModelRecycler{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", Subject_Name='" + Subject_Name + '\'' +
                ", SubSubject_Name='" + SubSubject_Name + '\'' +
                '}';
    }
}
