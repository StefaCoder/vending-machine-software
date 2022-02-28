package com.techelevator;

public class Candy extends Item{

    //CONSTRUCTOR FROM SUPERCLASS

    public Candy(String name, int price){
        super(name, price);
    }

    //DEFAULT CONSTRUCTOR

    public Candy(){

    }

    //METHOD

    @Override
    public String itemDisplayMsg(){
        String message = "Munch Munch, Yum!";
        return message;
    }
}
