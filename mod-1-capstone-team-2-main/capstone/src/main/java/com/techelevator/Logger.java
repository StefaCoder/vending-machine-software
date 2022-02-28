package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Logger {

    private File log;

    //CONSTRUCTOR

    public Logger(String logFile){
        this.log = new File(logFile);

    }

    //DEFAULT CONSTRUCTOR

    public Logger(){

    }

    //METHOD

    public void log(String message, BigDecimal intAmount, BigDecimal finalAmount){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(log, true))){

            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            pw.println(LocalDateTime.now().format(format) + " " + message + " $" + intAmount + " $" + finalAmount);

        }
        catch (FileNotFoundException fnf){
            System.out.println("File not found! " + fnf.getMessage());
        }
    }

    public void salesReport(Map<String, List<String>> productMap, Map<String, Integer> inventoryMap){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss");
        String fileName = "salesReport" + "_" + LocalDateTime.now().format(format) + ".txt";

        File salesFile = new File(fileName);

        try(PrintWriter pwSalesReport = new PrintWriter(salesFile)){
            BigDecimal totalSales = BigDecimal.valueOf(0);

            for(Map.Entry<String, List<String>> item : productMap.entrySet()){
                String itemName = item.getValue().get(0);
                Integer amountItemSold = 5 - inventoryMap.get(item.getKey());
                String itemPriceStr = item.getValue().get(1);
                double itemPriceDbl = Double.parseDouble(itemPriceStr);
                BigDecimal itemPrice = BigDecimal.valueOf(itemPriceDbl);

                totalSales = totalSales.add(itemPrice.multiply(BigDecimal.valueOf(amountItemSold)));

                pwSalesReport.println(itemName + "|" + amountItemSold);
            }
            pwSalesReport.println();
            pwSalesReport.println("TOTAL SALES: $" + totalSales);

        }
        catch(FileNotFoundException fnf){
            System.out.println("The file doesn't exist! " + fnf.getMessage());
        }
    }
}
