package com.android.mohammadhf.thisorthat.model;

public class Question {

    private int id;
    private String AnsOne;
    private String AnsTwo;
    private int AnsOneNums;
    private int AnsTwoNums;

    public Question() {
    }

    public Question(int id) {
        this.id = id;
    }


    public Question(int id, String AnsOne, String AnsTwo, int AnsOneNums, int AnsTwoNums) {
        this.id = id;
        this.AnsOne = AnsOne;
        this.AnsTwo = AnsTwo;
        this.AnsOneNums = AnsOneNums;
        this.AnsTwoNums = AnsTwoNums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnsOne() {
        return AnsOne;
    }

    public void setAnsOne(String AnsOne) {
        this.AnsOne = AnsOne;
    }

    public String getAnsTwo() {
        return AnsTwo;
    }

    public void setAnsTwo(String AnsTwo) {
        this.AnsTwo = AnsTwo;
    }

    public int getAnsOneNums() {
        return AnsOneNums;
    }

    public void setAnsOneNums(int AnsOnePersent) {
        this.AnsOneNums = AnsOnePersent;
    }

    public int getAnsTwoNums() {
        return AnsTwoNums;
    }

    public void setAnsTwoNums(int AnsTwoPersent) {
        this.AnsTwoNums = AnsTwoPersent;
    }
}
