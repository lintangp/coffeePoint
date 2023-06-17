package com.lintang.coffee_point.Model;

public class HistoryTransaction {

    String transaction, date, time, payment;

    public HistoryTransaction(String transaction, String date, String time, String payment){
        this.transaction = transaction;
        this.date = date;
        this.time = time;
        this.payment = payment;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
