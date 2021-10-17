package com.example.unimelborientation.type;

import java.math.BigDecimal;

public class RowSubject {
//    TODO name命名待讨论

    String subjectName;
    String subjectCode;
    float theoryDegree;
    float practiceDegree;
    float difficultyDegree;
    int sid;

    public int getSid() {
        return sid;
    }

    public RowSubject(String subjectName, String subjectCode, float theoryDegree, float practiceDegree, float difficultyDegree) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.theoryDegree = theoryDegree;
        this.practiceDegree = practiceDegree;
        this.difficultyDegree = difficultyDegree;
    }

    public RowSubject(String subjectName, String subjectCode, float theoryDegree, float practiceDegree, float difficultyDegree, int sid){
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.theoryDegree = theoryDegree;
        this.practiceDegree = practiceDegree;
        this.difficultyDegree = difficultyDegree;
        this.sid = sid;
    }
    private float beautifyFloat(float f){
        BigDecimal b=new BigDecimal(f);
        float res = b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();
        return res;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public float getTheoryDegree() {
        return beautifyFloat(theoryDegree);
    }

    public void setTheoryDegree(float theoryDegree) {
        this.theoryDegree = theoryDegree;
    }

    public float getPracticeDegree() {
        return beautifyFloat(practiceDegree);
    }

    public void setPracticeDegree(float practiceDegree) {
        this.practiceDegree = practiceDegree;
    }

    public float getDifficultyDegree() {
        return beautifyFloat(difficultyDegree);
    }

    public void setDifficultyDegree(float difficultyDegree) {
        this.difficultyDegree = difficultyDegree;
    }

    public String getRowLargeText(){
        return getSubjectCode() + " " + getSubjectName();
    }

    public String getRowSmallText(){
        return "Theory: " + getTheoryDegree() + " Practice: " + getPracticeDegree() + " Difficulty: " + getDifficultyDegree();
    }

}
