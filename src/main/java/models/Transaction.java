package models;

public class Transaction {
    private String tid;
    private float price;
    private int date;

    public Transaction(){

    }
    public Transaction(String tid, float price, int date){
        this.tid = tid;
        this.price = price;
        this.date = date;
    }

    public String getTid(){
        return this.tid;
    }

    public void setTid(String tid){
        this.tid = tid;
    }

    public float getPrice (){
        return this.price;
    }

    public void setPrice(float price){
        this.price = price;
    }
    public int getDate(){
        return this.date;
    }
    public void setDate(int date){
        this.date = date;
    }


}
