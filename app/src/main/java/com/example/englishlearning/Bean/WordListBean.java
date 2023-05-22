package com.example.englishlearning.Bean;

public class WordListBean {
    Integer wordId;
    String word;
    String trans;

    public WordListBean(Integer wordId,String word, String trans) {
        this.wordId=wordId;
        this.word = word;
        this.trans = trans;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public String getTrans() {
        return trans;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
}
