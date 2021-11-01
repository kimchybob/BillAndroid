package com.example.unimelborientation.type;

import java.math.BigDecimal;

public class Subject {
//    TODO name命名待讨论

    String subjname;
    String subjcode;
    float theoryscore;
    float practiscore;
    float diffiscore;
    int sid;
    int prisid;
    String course;
    String lasttime;
    String descrip;

    public int getSid() {
        return sid;
    }

    public Subject(String subjname, String subjcode, float theoryscore, float practiscore, float diffiscore) {
        this.subjname = subjname;
        this.subjcode = subjcode;
        this.theoryscore = theoryscore;
        this.practiscore = practiscore;
        this.diffiscore = diffiscore;
    }

    public Subject(String subjname, String subjcode, float theoryscore, float practiscore, float diffiscore, int sid){
        this.subjname = subjname;
        this.subjcode = subjcode;
        this.theoryscore = theoryscore;
        this.practiscore = practiscore;
        this.diffiscore = diffiscore;
        this.sid = sid;
    }
    private float beautifyFloat(float f){
        BigDecimal b=new BigDecimal(f);
        float res = b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();
        return res;
    }

    public String getSubjname() {
        return subjname;
    }

    public void setSubjname(String subjname) {
        this.subjname = subjname;
    }

    public String getSubjcode() {
        return subjcode;
    }

    public void setSubjcode(String subjcode) {
        this.subjcode = subjcode;
    }

    public float getTheoryscore() {
        return beautifyFloat(theoryscore);
    }

    public void setTheoryscore(float theoryscore) {
        this.theoryscore = theoryscore;
    }

    public float getPractiscore() {
        return beautifyFloat(practiscore);
    }

    public void setPractiscore(float practiscore) {
        this.practiscore = practiscore;
    }

    public float getDiffiscore() {
        return beautifyFloat(diffiscore);
    }

    public void setDiffiscore(float diffiscore) {
        this.diffiscore = diffiscore;
    }

    public String getDescrip() { return descrip; }

    public void setDescrip(String descrip) { this.descrip = descrip; }

    public String getRowLargeText(){
        return getSubjcode() + " " + getSubjname();
    }

    public String getRowSmallText(){
        return "Theory: " + getTheoryscore() + " Practice: " + getPractiscore() + " Difficulty: " + getDiffiscore();
    }

}
