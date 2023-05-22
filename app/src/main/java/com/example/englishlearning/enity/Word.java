package com.example.englishlearning.enity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {
    private Integer word_id;
    private Integer book_id;
    private String word;
    private String ps; //音标

    private Integer has_learn;

    private Integer flag=0;




    //one to many 所有意思的对应的id
    private List<Word_Translation> trans;//
    private List<Word_Sentence> sentence;

    public Word() {
        ;
    }


    public List<String> getSContentToString(){
        int len=sentence.size();
        List<String> strings=new ArrayList<>();
        for(int i=0;i<len;++i){
            strings.add(sentence.get(i).getS_content());
        }
        return strings;
    }
    public List<String> getSCnToString(){
        int len=sentence.size();
        List<String> strings=new ArrayList<>();
        for(int i=0;i<len;++i){
            strings.add(sentence.get(i).getS_cn());
        }
        return strings;
    }
    public List<String> getPosToString(){
        int len=trans.size();
        List<String> strings=new ArrayList<>();
        for(int i=0;i<len;++i){
            strings.add(trans.get(i).getPos());
        }
        return strings;
    }

    public List<String> getTransToString() {
        int len=trans.size();
        List<String> strings=new ArrayList<>();
        for(int i=0;i<len;++i){
            strings.add(trans.get(i).getTranCn());
        }
        return strings;
    }


    public void setFlag(Integer flag) {
        this.flag = flag;
    }


    public Integer getFlag() {
        return flag;
    }

    public Word(Integer word_id, Integer book_id, String word, String ps, Integer has_learn, List<Word_Translation> trans, List<Word_Sentence> sentence, Integer flag) {
        this.word_id = word_id;
        this.book_id = book_id;
        this.word = word;
        this.ps = ps;
        this.has_learn = has_learn;
        this.trans = trans;
        this.sentence = sentence;
        this.flag=flag;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word_id=" + word_id +
                ", book_id=" + book_id +
                ", word='" + word + '\'' +
                ", ps='" + ps + '\'' +
                ", has_learn=" + has_learn +
                ", flag=" + flag +
                ", trans=" + trans +
                ", sentence=" + sentence +
                '}';
    }

    public List<Word_Sentence> getSentence() {
        return sentence;
    }

    public void setSentence(List<Word_Sentence> sentence) {
        this.sentence = sentence;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }



    public void setHas_learn(Integer has_learn) {
        this.has_learn = has_learn;
    }

    public void setTrans(List<Word_Translation> trans) {
        this.trans = trans;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public String getWord() {
        return word;
    }

    public String getPs() {
        return ps;
    }



    public Integer getHas_learn() {
        return has_learn;
    }

    public List<Word_Translation> getTrans() {
        return trans;
    }



}
