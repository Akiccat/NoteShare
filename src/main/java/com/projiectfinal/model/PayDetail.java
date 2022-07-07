package com.projiectfinal.model;

import java.util.Date;

public class PayDetail {
    private int dstUserid;
    private int srcUserid;
    private String payCode;
    private String word;
    private String noteCode;
    private int noteId;
    private int price;
    private Date date;

    private String srcUsername;

    public PayDetail() {
    }

    public PayDetail(int dstUserid, int srcUserid, String payCode, String word, String noteCode, int noteId, int price, Date date, String srcUsername) {
        this.dstUserid = dstUserid;
        this.srcUserid = srcUserid;
        this.payCode = payCode;
        this.word = word;
        this.noteCode = noteCode;
        this.noteId = noteId;
        this.price = price;
        this.date = date;
        this.srcUsername = srcUsername;
    }

    public String getSrcUsername() {
        return srcUsername;
    }
    public void setSrcUsername(String srcUsername) {
        this.srcUsername = srcUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDstUserid() {
        return dstUserid;
    }

    public void setDstUserid(int dstUserid) {
        this.dstUserid = dstUserid;
    }

    public int getSrcUserid() {
        return srcUserid;
    }

    public void setSrcUserid(int srcUserid) {
        this.srcUserid = srcUserid;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
