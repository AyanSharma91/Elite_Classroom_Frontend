package com.example.elite_classroom.Models.Recycler_Models;

public class Get_Classes_List {

    String class_name;
    String owner_name;
    String students_no;

    public Get_Classes_List(String class_name, String owner_name, String students_no) {

        this.class_name= class_name;
        this.owner_name = owner_name;
        this.students_no= students_no;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getStudents_no() {
        return students_no;
    }

    public void setStudents_no(String students_no) {
        this.students_no = students_no;
    }
}
