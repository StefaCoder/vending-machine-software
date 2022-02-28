package com.techelevator;

import com.techelevator.view.Menu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

public class CoinBoxTest {

    private CoinBox cb;

    @Before
    public void setup() {
        cb = new CoinBox();
    }

    @After
    public void teardown() {
        cb = null;
    }

    @Test
    public void insertDollars_Adds_To_Balance() {
        BigDecimal expectedResult = BigDecimal.valueOf(1.00);

        BigDecimal balance = BigDecimal.valueOf(0.00);
        BigDecimal amountAdded = BigDecimal.valueOf(1.00);
        balance = balance.add(amountAdded);

        Assert.assertEquals(expectedResult, balance);
    }

    @Test
    public void change_Method_CashOut_Returns_Zero() {
        BigDecimal expectedResult = BigDecimal.valueOf(0.00).setScale(2);

        BigDecimal cashOutAmount = BigDecimal.valueOf(0.65);
        cb.change(cashOutAmount);

        Assert.assertEquals(expectedResult, cb.getBalance());
    }

    @Test
    public void transaction_Method_Subtract_From_Balance() {
        BigDecimal expectedResult = BigDecimal.valueOf(0.95);

        Menu menu = new Menu(System.in, System.out);
        CoinBox cbTest = new CoinBox(menu, BigDecimal.valueOf(4.00));
        cbTest.transaction("Potato Crisps", "A1", BigDecimal.valueOf(3.05));

        Assert.assertEquals(expectedResult, cbTest.getBalance());
    }
}
