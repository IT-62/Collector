package edu.latria;

import edu.exceptions.CantParseException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CashRegisterProcessor {
    private Map<String, Integer> goods = new TreeMap<>();
    private Map<String, BigDecimal> prices;

    public CashRegisterProcessor(HashMap<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public void terminal(){
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Enter end to stop");
        while((line = sc.nextLine()) != "end"){
            System.out.print(">");
            try {
                String[] couple = line.trim().split("\\s");
                addToCheck(couple[0], Integer.parseInt(couple[1]));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void addToCheck(String name, int count){
        goods.put(name, count);
    }

    public Map<String, Integer> getGoods() {
        return goods;
    }

    public BigDecimal getPrice(String name){
        return prices.get(name);
    }


}
