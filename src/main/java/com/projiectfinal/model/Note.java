package com.projiectfinal.model;

import java.util.Date;

public class Note {
    private int noteId;
    private int isFree;
    private int subjectId;
    private String picPath;
    private int userId;
    private String noteName;
    private Date uploadDate;
    private String noteCode;

    public Note(int noteId, int isFree, int subjectId, String picPath, int userId, String noteName) {
        this.noteId = noteId;
        this.isFree = isFree;
        this.subjectId = subjectId;
        this.picPath = picPath;
        this.userId = userId;
        this.noteName = noteName;

    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public Note() {

    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
}

