package com.example.elite_classroom.Models.Recycler_Models;

public class Class_Fixtures {

    Integer week_no;
    String sun;
    String mon;
    String tue;
    String wed;
    String thu;
    String fri;
    String sat;
    String class_code;
   String class_link;
   String class_name;
   String desciption;
   Integer owner_id;
   String profile_pic;

    public Class_Fixtures(Integer week_no, String sun, String mon, String tue,
                          String wed, String thu, String fri, String sat, String class_code,
                          String class_link, String class_name, String desciption) {
        this.week_no = week_no;
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.class_code = class_code;
        this.class_link = class_link;
        this.class_name = class_name;
        this.desciption = desciption;
    }

    public Integer getWeek_no() {
        return week_no;
    }

    public void setWeek_no(Integer week_no) {
        this.week_no = week_no;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTue() {
        return tue;
    }

    public void setTue(String tue) {
        this.tue = tue;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_link() {
        return class_link;
    }

    public void setClass_link(String class_link) {
        this.class_link = class_link;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
