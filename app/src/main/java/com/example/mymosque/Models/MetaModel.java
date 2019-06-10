package com.example.mymosque.Models;

public class MetaModel
{
    private int current_page;
    private int from;
    private int last_page;
    private String path;
    private int per_page;
    private int to;
    private int total;

    public MetaModel(int current_page, int from, int last_page, String path, int per_page, int to, int total) {
        this.current_page = current_page;
        this.from = from;
        this.last_page = last_page;
        this.path = path;
        this.per_page = per_page;
        this.to = to;
        this.total = total;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
