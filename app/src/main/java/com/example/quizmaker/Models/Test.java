package com.example.quizmaker.Models;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {
    int id;
    List<Question> questionList;
    int soluong,dung;
    boolean isTrain;

    public boolean isTrain() {
        return isTrain;
    }

    public void setTrain(boolean train) {
        isTrain = train;
    }

    public Test(int id, List<Question> questionList, int soluong, int dung, boolean isTrain) {
        this.id = id;
        this.questionList = questionList;
        this.soluong = soluong;
        this.dung = dung;
        this.isTrain = isTrain;
    }

    public Test(){

    }
    public Test(List<Question> questionList, int soluong, int dung) {
        this.questionList = questionList;
        this.soluong = soluong;
        this.dung = dung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test(int id, List<Question> questionList, int soluong, int dung) {
        this.id = id;
        this.questionList = questionList;
        this.soluong = soluong;
        this.dung = dung;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDung() {
        return dung;
    }

    public void setDung(int dung) {
        this.dung = dung;
    }
}
