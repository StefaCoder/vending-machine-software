package com.techelevator;

import com.techelevator.view.Menu;

import java.awt.print.PrinterException;
import java.awt.print.PrinterIOException;
import java.io.File;
import java.math.BigDecimal;

public class CoinBox implements Purchasable{

    private static final String DOLLAR_AMT_1 = "$1";
    private static final String DOLLAR_AMT_2 = "$2";
    private static final String DOLLAR_AMT_5 = "$5";
    private static final String DOLLAR_AMT_10 = "$10";
    private static final String DOLLAR_EXIT = "Back to purchase menu";
    private static final String[] DOLLAR_MENU_OPTIONS = {DOLLAR_AMT_1, DOLLAR_AMT_2, DOLLAR_AMT_5, DOLLAR_AMT_10, DOLLAR_EXIT};

    private BigDecimal balance;

    //LOGGER

    private static Logger appLog = new Logger("Log.txt");

    //GETTER

    public BigDecimal getBalance() {
        return balance;
    }

    //MENU

    private Menu menu;

    //CONSTRUCTOR

    public CoinBox(Menu menu, BigDecimal balance) {
        this.menu = menu;
        this.balance=balance;
    }

    //DEFAULT CONSTRUCTOR
    public CoinBox() {}

    //METHODS FROM PURCHASABLE INTERFACE

    @Override
    public void insertDollars() {

        try {
            while (true) {
                System.out.println("        ******* DOLLAR MENU *******              ");
                System.out.println();
                System.out.println("Current Balance: $" + balance);
                String choice = (String) menu.getChoiceFromOptions(DOLLAR_MENU_OPTIONS);

                if (choice.equals(DOLLAR_AMT_1)) {
                    BigDecimal amountAdded = BigDecimal.valueOf(1.00);
                    balance = balance.add(amountAdded);
                    BigDecimal finalAmount = balance;

                    appLog.log("FEED MONEY:", amountAdded, finalAmount);

                } else if (choice.equals(DOLLAR_AMT_2)) {
                    BigDecimal amountAdded = BigDecimal.valueOf(2.00);
                    balance = balance.add(amountAdded);
                    BigDecimal finalAmount = balance;

                    appLog.log("FEED MONEY:", amountAdded, finalAmount);

                } else if (choice.equals(DOLLAR_AMT_5)) {
                    BigDecimal amountAdded = BigDecimal.valueOf(5.00);
                    balance = balance.add(amountAdded);
                    BigDecimal finalAmount = balance;

                    appLog.log("FEED MONEY:", amountAdded, finalAmount);

                } else if (choice.equals(DOLLAR_AMT_10)) {
                    BigDecimal amountAdded = BigDecimal.valueOf(10.00);
                    balance = balance.add(amountAdded);
                    BigDecimal finalAmount = balance;

                    appLog.log("FEED MONEY:", amountAdded, finalAmount);

                } else if (choice.equals(DOLLAR_EXIT)) {
                    break;
                }
            }
        }catch (Exception exc){
            System.out.println("Fail to Log: " + exc.getMessage());
        }
    }

    @Override
    public void transaction(String name, String key, BigDecimal itemPrice) {

        BigDecimal startingAmount = balance;
        balance = balance.subtract(itemPrice);

        try {
            appLog.log(name + " " + key, startingAmount, balance);

        }catch (Exception exc){
            System.out.println("Fail to Log: " + exc.getMessage());
        }
    }

    @Override
    public void change(BigDecimal cashOutBalance) {

        int quarter = 0;
        int dime = 0;
        int nickel = 0;

        BigDecimal startingBalance = balance;

        while(cashOutBalance.compareTo(BigDecimal.valueOf(0.25)) == 1
                || cashOutBalance.compareTo(BigDecimal.valueOf(0.25)) == 0) {
            cashOutBalance = cashOutBalance.subtract(BigDecimal.valueOf(0.25));
            quarter++;
        }

        while(cashOutBalance.compareTo(BigDecimal.valueOf(0.10)) == 1
                || cashOutBalance.compareTo(BigDecimal.valueOf(0.10)) == 0){
            cashOutBalance =cashOutBalance.subtract(BigDecimal.valueOf(0.10));
            dime++;
        }

        while(cashOutBalance.compareTo(BigDecimal.valueOf(0.05)) == 1
                || cashOutBalance.compareTo(BigDecimal.valueOf(0.05)) == 0){
            cashOutBalance = cashOutBalance.subtract(BigDecimal.valueOf(0.05));
            nickel++;
        }

        balance = cashOutBalance;

        try {
            appLog.log("GIVE CHANGE:", startingBalance, cashOutBalance);

        }catch (Exception exc){
            System.out.println("Fail to Log: " + exc.getMessage());
        }

        System.out.println();
        System.out.println("Your change is " + quarter + " quarter(s), " + dime + " dime(s), " + nickel + " nickel(s)");
    }
}
