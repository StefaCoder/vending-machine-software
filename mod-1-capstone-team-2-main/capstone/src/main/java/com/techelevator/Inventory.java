package com.techelevator;

import java.util.Map;

public class Inventory {

    private Map<String, Integer> itemQty;

    //CONSTRUCTOR

    public Inventory(Map<String, Integer> itemQty) {
        this.itemQty = itemQty;
    }

    //DEFAULT CONSTRUCTOR

    public Inventory() {}

    //METHOD

    public void subtractFromQty(String key) {
       Integer quantity = itemQty.get(key);
       quantity--;
       itemQty.put(key, quantity);
    }
}
