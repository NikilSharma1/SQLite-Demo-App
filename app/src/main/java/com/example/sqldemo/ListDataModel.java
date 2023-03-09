package com.example.sqldemo;

public class ListDataModel {
    private String name;
    private int age;
    private boolean status;
    public ListDataModel(String name,int age,boolean status){
        this.age=age;
        this.name=name;
        this.status=status;
    }
    public String getName(){return name;}
    public int getAge(){return age;}
    public boolean getStatus(){return status;}
}
