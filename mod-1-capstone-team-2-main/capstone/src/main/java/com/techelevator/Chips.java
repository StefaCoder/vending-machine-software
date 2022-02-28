package com.techelevator;

public class Chips extends Item {

    //CONSTRUCTOR FROM SUPERCLASS

    public Chips(String name, int price){
        super(name, price);
    }

    //DEFAULT CONSTRUCTOR

    public Chips(){

    }

    //METHOD

    @Override
    public String itemDisplayMsg(){
        String message = "Crunch Crunch, Yum!";
        return message;
    }
}
