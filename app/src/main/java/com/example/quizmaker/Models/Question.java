package com.example.quizmaker.Models;

import java.io.Serializable;

public class Question implements Serializable {
    int id;
    String context;
    String[] answerList;
    int answer;
    int quizid;
    int correct;
    int attemp;

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getAttemp() {
        return attemp;
    }

    public void setAttemp(int attemp) {
        this.attemp = attemp;
    }

    public Question(int id, String context, String[] answerList, int answer, int quizid, int correct, int attemp) {
        this.id = id;
        this.context = context;
        this.answerList = answerList;
        this.answer = answer;
        this.quizid = quizid;
        this.correct = correct;
        this.attemp = attemp;
    }

    public Question(int id, String context, String[] answerList, int answer, int quizid) {
        this.id = id;
        this.context = context;
        this.answerList = answerList;
        this.answer = answer;
        this.quizid = quizid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String[] getAnswerList() {
        return answerList;
    }

    public void setAnswerList(String[] answerList) {
        this.answerList = answerList;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getQuizid() {
        return quizid;
    }

    public void setQuizid(int quizid) {
        this.quizid = quizid;
    }

    public Question(){

    }
}
