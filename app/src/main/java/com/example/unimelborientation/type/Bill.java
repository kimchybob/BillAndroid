package com.example.unimelborientation.type;

import java.math.BigDecimal;

public class Bill {
//    TODO name命名待讨论

    int bId;
    String bill;
    String uname;
    float expense;
    String date;
    int room;

    public Bill(int bId, String bill, String uname, float expense, String date, int room) {
        this.bId = bId;
        this.bill = bill;
        this.uname = uname;
        this.expense = expense;
        this.date = date;
        this.room = room;
    }

    public Bill(int bId, String bill, String uname, float expense, String date) {
        this.bId = bId;
        this.bill = bill;
        this.uname = uname;
        this.expense = expense;
        this.date = date;
    }



    public String getRowLargeText(){
        return "$" + getExpense();
    }

    public float getExpense() {return expense;}

    public String getDate() {return date;}

    public String getRowSmallText(){
        return "Date: " + getDate();
    }

}
