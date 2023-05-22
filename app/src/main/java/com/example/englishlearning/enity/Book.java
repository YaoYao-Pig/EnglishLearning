package com.example.englishlearning.enity;

public class Book {
    private Integer book_id;
    private String book_name;
    private String cover;
    private String content;
    private Integer word_number;

    private String file_name;



    public Book(Integer book_id, String book_name, String cover, String content, Integer word_number, String file_name) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.cover = cover;
        this.content = content;
        this.word_number = word_number;
        this.file_name = file_name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", cover='" + cover + '\'' +
                ", content='" + content + '\'' +
                ", word_number=" + word_number +
                ", file_name='" + file_name + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setWord_number(Integer word_number) {
        this.word_number = word_number;
    }


    public Integer getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getCover() {
        return cover;
    }

    public Integer getWord_number() {
        return word_number;
    }



}
