package com.example.unimelborientation.type;

import java.math.BigDecimal;

public class Bill {
//    TODO name命名待讨论

    int bId;
    String bill;
    String uname;
    float expense;
    String date;

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

    private float getExpense() {return expense;}

    private String getDate() {return date;}

    public String getRowSmallText(){
        return "Date: " + getDate();
    }

}
