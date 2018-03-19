package edu.latria;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CashRegisterProcessorTest {
    CashRegisterProcessor cashRegisterProcessor;
    @Before
    public void setUp() throws Exception {
        cashRegisterProcessor = new CashRegisterProcessor(new HashMap<String, BigDecimal>() {{
            put("fish", new BigDecimal(1.5));
            put("meat", new BigDecimal(2.5));
            put("bread", new BigDecimal(5.5));
        }});
        cashRegisterProcessor.addToCheck("fish",2);
        cashRegisterProcessor.addToCheck("bread",4);
        cashRegisterProcessor.addToCheck("meat",5);
        cashRegisterProcessor.addToCheck("fish",2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTerminal() {
    }

    @Test
    public void testGetGoods() {
        for (Map.Entry<String,Integer> entry:
             cashRegisterProcessor.getGoods().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + " " + entry.getValue()*
                    cashRegisterProcessor.getPrice(entry.getKey()).doubleValue());
        }
    }

    @Test
    public void testGetPrice() {
    }
}