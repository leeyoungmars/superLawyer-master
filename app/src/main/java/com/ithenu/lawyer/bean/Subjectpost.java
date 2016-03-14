package com.ithenu.lawyer.bean;

import java.io.Serializable;

public class Subjectpost implements Serializable {
    private int id;
    private String title;
    private String content;
    private String author;
    private String date;

    public Subjectpost() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Subjectpost(String title, String content, String author, String date) {
        super();
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Subjectpost [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", date="
                + date + "]";
    }


}
