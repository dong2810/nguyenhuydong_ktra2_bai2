package com.example.nguyenhuydong_ktra2_bai2;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Test implements Serializable {
    private int id;
    private String name;
    private String date;
    private String time;
    private boolean isWrite;

    public Test(){

    }

    public Test(int id, String name, String date, String time, boolean isWrite) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.isWrite = isWrite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isWrite() {
        return isWrite;
    }

    public void setWrite(boolean write) {
        isWrite = write;
    }
}
