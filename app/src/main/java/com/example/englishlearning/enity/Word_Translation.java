package com.example.englishlearning.enity;

import java.io.Serializable;
import java.net.Inet4Address;
import java.security.SecureRandom;

public class Word_Translation implements Serializable {
    private Integer tran_id;
    private Integer word_id;
    private String tranCn;//中文翻译
    private String pos;//词性
    private String tranOther;//英文意思解释

    public Word_Translation(Integer tran_id, Integer word_id, String tranCn, String pos, String tranOther) {
        this.tran_id = tran_id;
        this.word_id = word_id;
        this.tranCn = tranCn;
        this.pos = pos;
        this.tranOther = tranOther;
    }

    public Integer getTran_id() {
        return tran_id;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public String getTranCn() {
        return tranCn;
    }

    public String getPos() {
        return pos;
    }

    public String getTranOther() {
        return tranOther;
    }

    public void setTran_id(Integer tran_id) {
        this.tran_id = tran_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public void setTranCn(String tranCn) {
        this.tranCn = tranCn;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setTranOther(String tranOther) {
        this.tranOther = tranOther;
    }

    @Override
    public String toString() {
        return "Word_Translation{" +
                "tran_id=" + tran_id +
                ", word_id=" + word_id +
                ", tranCn='" + tranCn + '\'' +
                ", pos='" + pos + '\'' +
                ", tranOther='" + tranOther + '\'' +
                '}';
    }
}
