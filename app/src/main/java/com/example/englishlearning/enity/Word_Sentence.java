package com.example.englishlearning.enity;

import java.io.Serializable;

public class Word_Sentence implements Serializable {
    private Integer word_sentence_id;
    private Integer word_id;
    private String s_content;
    private String s_cn;

    @Override
    public String toString() {
        return "Word_Sentence{" +
                "word_sentence_id=" + word_sentence_id +
                ", word_id=" + word_id +
                ", s_content='" + s_content + '\'' +
                ", s_cn='" + s_cn + '\'' +
                '}';
    }

    public void setWord_sentence_id(Integer word_sentence_id) {
        this.word_sentence_id = word_sentence_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public void setS_content(String s_content) {
        this.s_content = s_content;
    }

    public void setS_cn(String s_cn) {
        this.s_cn = s_cn;
    }

    public Integer getWord_sentence_id() {
        return word_sentence_id;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public String getS_content() {
        return s_content;
    }

    public String getS_cn() {
        return s_cn;
    }

    public Word_Sentence(Integer word_sentence_id, Integer word_id, String s_content, String s_cn) {
        this.word_sentence_id = word_sentence_id;
        this.word_id = word_id;
        this.s_content = s_content;
        this.s_cn = s_cn;
    }
}
