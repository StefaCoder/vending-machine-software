package com.techelevator;

abstract class Item {

    private String name;
    private int price;

    //GETTERS

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    //CONSTRUCTOR

    public Item(String name, int price){
        this.name=name;
        this.price=price;
    }

    //DEFAULT CONSTRUCTOR

    public Item(){

    }

    //METHODS

    public abstract String itemDisplayMsg();

}
