package com.techelevator;

import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_HIDDEN_SALES_REPORT = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_HIDDEN_SALES_REPORT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;

	Scanner userInput = new Scanner(System.in);

	VendingMachine vm = new VendingMachine();
	Chips chips = new Chips();
	Candy candy = new Candy();
	Drink drink = new Drink();
	Gum gum = new Gum();

	Menu dollarMenu = new Menu(System.in, System.out);
	CoinBox coinBox = new CoinBox(dollarMenu, BigDecimal.valueOf(0.00).setScale(2));
	Logger logSales = new Logger();

	//CONSTRUCTOR

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	//METHODS

	public void run() {

		//STOCKING VENDING MACHINE FROM CSV FILE
		vm.readCSV();

		while (true) {

			System.out.println("        ******* MAIN MENU *******              ");

			String choice = (String) menu.getChoiceFromOptionsLastHidden(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vm.displayProducts();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				//IMPLEMENTED THE PURCHASE MENU

				while (true) {
					System.out.println("        ******* PURCHASE MENU *******              ");
					System.out.println();
					System.out.println("Current Balance: $" + coinBox.getBalance());


					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

						coinBox.insertDollars();

					} else if (choice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						vm.displayProducts();

						System.out.print("Select the product (code numbers required!): ");
						String productCode = "";

						//VERIFYING USER INPUT IS A VALID CODE
						try {
							productCode = userInput.nextLine().toUpperCase();
							if (!vm.productMap.containsKey(productCode)){
								throw new IllegalArgumentException("Wrong input!");
							}
						}
						catch (IllegalArgumentException iae){
							System.out.println("Please enter the product code! " + iae.getMessage());
							break;
						}

						String itemName = vm.productMap.get(productCode).get(0);

						String itemPriceStr = vm.productMap.get(productCode).get(1);
						double itemPriceDbl = Double.parseDouble(itemPriceStr);
						BigDecimal itemPrice = BigDecimal.valueOf(itemPriceDbl);

						Integer quantity = vm.inventoryMap.get(productCode);

						if (coinBox.getBalance().compareTo(itemPrice) == -1) {
							System.out.println();
							System.out.println("-----------------------------------------------");
							System.out.println("Insufficient Funds!");
							System.out.println("Use Option 1 (Feed Money) to make purchase");
							System.out.println("-----------------------------------------------");
							System.out.println();
						}
						else if (quantity == 0) {
							System.out.println();
							System.out.println("-----------------------------------------------");
							System.out.println("Sorry, the product is SOLD OUT!");
							System.out.println("Please select another product to purchase");
							System.out.println("-----------------------------------------------");
							System.out.println();
						}
						else {
							coinBox.transaction(itemName, productCode, itemPrice);

							vm.itemQty.subtractFromQty(productCode);

							System.out.println();
							System.out.println("You selected " + itemName + " for $" + itemPriceStr);

							if (vm.productMap.get(productCode).contains("Chip")) {
								System.out.println(chips.itemDisplayMsg());
							}
							if (vm.productMap.get(productCode).contains("Candy")) {
								System.out.println(candy.itemDisplayMsg());
							}
							if (vm.productMap.get(productCode).contains("Drink")) {
								System.out.println(drink.itemDisplayMsg());
							}
							if (vm.productMap.get(productCode).contains("Gum")) {
								System.out.println(gum.itemDisplayMsg());
							}
								System.out.println();
						}

					}else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
						BigDecimal cashOutBalance = coinBox.getBalance();
						coinBox.change(cashOutBalance);
						System.out.println();
						System.out.println("Current Balance: " + coinBox.getBalance());
						break;
					}
				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println();
				System.out.println("-----------------------------------------------");
				System.out.println("Thanks for using our Vending Machine!");
				System.out.println("-----------------------------------------------");
				System.exit(0);
			}
			else if (choice.equals(MAIN_MENU_HIDDEN_SALES_REPORT)){
				logSales.salesReport(vm.productMap, vm.inventoryMap);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		System.out.println();
		System.out.println("***********************************************");
		System.out.println("******** WELCOME TO THE VENDING MACHINE *******");
		System.out.println("***********************************************");
		System.out.println();

		cli.run();
	}
}
