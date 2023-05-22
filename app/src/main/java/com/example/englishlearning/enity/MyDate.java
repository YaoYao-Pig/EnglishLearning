package com.example.englishlearning.enity;

import org.litepal.annotation.Column;

public class MyDate {

//    @Column(unique = true)
//    private int id;

    // 年
    private int year;

    // 月
    private int month;

    // 日
    private int date;

    // 在这一天新学多少单词
    private int wordLearnNumber;

    // 在这一天复习多少单词
    private int wordReviewNumber;

    // 在这一天的心情感悟
    private String remark;

    // 归属用户
    private int userId;

    public MyDate(String formatDay){
        String[] days=formatDay.split("-");
        year=Integer.parseInt(days[0]);
        month=Integer.parseInt(days[1]);
        date=Integer.parseInt(days[2]);
    }
    public MyDate(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }

    public MyDate(int year, int month, int date, int wordLearnNumber, int wordReviewNumber, String remark, int userId) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.wordLearnNumber = wordLearnNumber;
        this.wordReviewNumber = wordReviewNumber;
        this.remark = remark;
        this.userId = userId;
    }


    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getWordLearnNumber() {
        return wordLearnNumber;
    }

    public void setWordLearnNumber(int wordLearnNumber) {
        this.wordLearnNumber = wordLearnNumber;
    }

    public int getWordReviewNumber() {
        return wordReviewNumber;
    }

    public void setWordReviewNumber(int wordReviewNumber) {
        this.wordReviewNumber = wordReviewNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
