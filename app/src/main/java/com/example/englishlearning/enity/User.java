package com.example.englishlearning.enity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private Integer user_id;
    private String username;
    private String password;
    private String create_time;
    private Integer c_day;
    private Integer has_learn_word;
    private Integer coin;

    public User(Integer user_id, String username, String password, String create_time, Integer c_day, Integer has_learn_word, Integer coin, String flag) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.create_time = create_time;
        this.c_day = c_day;
        this.has_learn_word = has_learn_word;
        this.coin = coin;
        this.flag = flag;
    }

    public void setC_day(Integer c_day) {
        this.c_day = c_day;
    }

    public void setHas_learn_word(Integer has_learn_word) {
        this.has_learn_word = has_learn_word;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getC_day() {
        return c_day;
    }

    public Integer getHas_learn_word() {
        return has_learn_word;
    }

    public Integer getCoin() {
        return coin;
    }

    private String flag;


    public User(Integer user_id, String username, String password, String create_time, String flag) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.create_time = create_time;
        this.flag = flag;
        this.c_day=0;
        this.has_learn_word=0;
        this.coin=0;
    }
    public static String getFormatDate(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public User(Integer user_id, String username, String password) {
        this(user_id,username,password, getFormatDate(new Date()),"0");
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getFlag() {
        return flag;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", create_time=" + create_time +
                ", flag='" + flag + '\'' +
                '}';
    }
}
