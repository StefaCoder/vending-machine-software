package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {

    Map<String, List<String>> productMap = new LinkedHashMap<>();
    Map<String, Integer> inventoryMap = new HashMap<>();
    Inventory itemQty = new Inventory(inventoryMap);

    public void readCSV() {

        String path = "vendingmachine.csv";
        File testMachine = new File(path);

        try(Scanner stockVendingMachine = new Scanner(testMachine)) {
            while (stockVendingMachine.hasNextLine()) {
                String item = stockVendingMachine.nextLine();

                String[] splitItems = item.split("\\|");

                List<String> productList = new ArrayList<>();
                productList.add(splitItems[1]);
                productList.add(splitItems[2]);
                productList.add(splitItems[3]);

                productMap.put(splitItems[0], productList);
                inventoryMap.put(splitItems[0], 5);
            }
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File error: " + fnf.getMessage());
        }
    }


    public void displayProducts() {

        System.out.println("********************************");
        System.out.println("******** ITEMS AVAILABLE *******");
        System.out.println("********************************");
        System.out.println();

        System.out.println();

        for (Map.Entry<String, List<String>> item : productMap.entrySet()) {
            if (inventoryMap.get(item.getKey()) == 0) {
                System.out.println(item.getKey() + " = "
                        + item.getValue().get(0) + ", $"
                        + item.getValue().get(1) + ", "
                        + "Qty: SOLD OUT");
            }
            else {
                System.out.println(item.getKey() + " = "
                        + item.getValue().get(0) + ", $"
                        + item.getValue().get(1) + ", "
                        + "Qty: " + inventoryMap.get(item.getKey()));
            }
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
    }
}
