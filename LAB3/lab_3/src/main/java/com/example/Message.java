package com.example;

import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;
    public Message(){}
    public Message(Integer number, String content){
        this.number=number;
        this.content=content;
    }
    public Message(String content){
        this.number=0;
        this.content=content;
    }
    public Integer getNumber(){
        return number;
    }
    public String getContent(){
        return content;
    }
    public void setNumber(int value){
        number = value;
    }
    public void setContent(String value){
        content = value;
    }
    public String toString(){
        return "Message nr. "+Integer.toString(number)+": "+content;
    }
}