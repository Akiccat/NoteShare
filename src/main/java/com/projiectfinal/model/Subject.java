package com.projiectfinal.model;

public class Subject {
    private int subjectId;
    private String name;
    private String picPath;

    public Subject(int subjectId, String name, String picPath) {
        this.subjectId = subjectId;
        this.name = name;
        this.picPath = picPath;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
