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
    String last_modified_on;
    String email;
    String name;
    String google_token;
    String profile_pic;

    public Class_Fixtures(Integer week_no, String sun, String mon, String tue, String wed, String thu,
                          String fri, String sat, String class_code, String last_modified_on, String email,
                          String name, String google_token, String profile_pic)
    {
        this.week_no = week_no;
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.class_code = class_code;
        this.last_modified_on = last_modified_on;
        this.email = email;
        this.name = name;
        this.google_token = google_token;
        this.profile_pic = profile_pic;
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

    public String getLast_modified_on() {
        return last_modified_on;
    }

    public void setLast_modified_on(String last_modified_on) {
        this.last_modified_on = last_modified_on;
    }

    public String getEmail() {
        return email;
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

    public String getGoogle_token() {
        return google_token;
    }

    public void setGoogle_token(String google_token) {
        this.google_token = google_token;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
