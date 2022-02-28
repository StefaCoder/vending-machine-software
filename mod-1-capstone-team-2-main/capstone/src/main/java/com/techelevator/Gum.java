package com.techelevator;

public class Gum extends Item{

    //CONSTRUCTOR FROM SUPERCLASS

    public Gum(String name, int price){
        super(name, price);
    }

    //DEFAULT CONSTRUCTOR

    public Gum(){

    }

    //METHOD

    @Override
    public String itemDisplayMsg(){
        String message = "Chew Chew, Yum!";
        return message;
    }
}
