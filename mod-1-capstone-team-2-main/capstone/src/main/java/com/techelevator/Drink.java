package com.techelevator;

public class Drink extends Item{

    //CONSTRUCTOR FROM SUPERCLASS

    public Drink(String name, int price){
        super(name, price);
    }

    //DEFAULT CONSTRUCTOR

    public Drink(){

    }

    //METHOD

    @Override
    public String itemDisplayMsg(){
        String message = "Glug Glug, Yum!";
        return message;
    }
}
