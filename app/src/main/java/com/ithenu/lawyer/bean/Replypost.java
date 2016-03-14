package com.ithenu.lawyer.bean;

import java.io.Serializable;

public class Replypost implements Serializable {
    private int id;
    private int root;
    private int pid;
    private String content;
    private String author;
    private String date;

    public Replypost(int root, int pid, String content, String author, String date) {
        super();
        this.root = root;
        this.pid = pid;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public Replypost() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
        return "Replypost [id=" + id + ", root=" + root + ", pid=" + pid + ", content=" + content + ", author=" + author
                + ", date=" + date + "]";
    }


}
