package com.techelevator;

import java.math.BigDecimal;

public interface Purchasable {

    //METHODS

    void insertDollars();

    void transaction(String name, String key, BigDecimal itemPrice);

    void change(BigDecimal cashOutBalance);

}
