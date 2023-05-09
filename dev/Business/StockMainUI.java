package Business;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;


/**
 * UI menu:
 * 1) Add a new product. ---done---
 * 2) Update quantity of existing product. ---done---
 * 3) sell/remove from stock a single product. ---done---
 * 4) change store and storage sizes. ---done---
 * 5) inform on a defected/expired product. ---done---
 * 6) Reports. ---done---
 * 7) get information on selected product. ---done---
 * 8) update the discounts: on category and on product. ---done---
 * 9) Show all the products that sold ---done---
 * 10) Change min quantity to product ---done---
 * 11) Show all shortages in stock ---done---
 * 12) Exit.
 * <p>
 * //
 */


/**
 * This class represents the user interface for managing a chain of markets and their stock.
 * The user can add and update products, change store and storage sizes, report defects and
 * expirations, generate reports, and more.
 */
public class StockMainUI {
    public void startMenu() {
        /**
         * Displays the initial menu for selecting the market to manage and entering the number of shelves.
         * Prompts the user to create a default market and then displays the stock menu for managing products.
         */
        Chain chain;
        Market market;
        boolean running = true;
        boolean validSubSubCategory;
        String numOfMarkets = "0";
        String numOfShelves = "0";
        String numOfMarketToManagement = "0";
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.println("Enter the amount of markets in the chain: ");
            numOfMarkets = input.nextLine();
            if (numOfMarkets.matches("[0-9]+") && Integer.parseInt(numOfMarkets) > 0) {
                running = false;
            } else {
                System.out.print("Your number of markets is not valid, ");
            }
        }
        chain = new Chain(Integer.parseInt(numOfMarkets));
        running = true;
        System.out.println("Enter the the number of the market you want to management: ");
        numOfMarketToManagement = input.nextLine();
        if (numOfMarketToManagement.matches("[0-9]+") && Integer.parseInt(numOfMarketToManagement) > 0 && Integer.parseInt(numOfMarketToManagement) <= Integer.parseInt(numOfMarkets)) {
            running = false;
        }
        while (running) {
            System.out.println("The number of market is not valid, please enter a valid number of market: ");
            numOfMarketToManagement = input.nextLine();
            if (numOfMarketToManagement.matches("[0-9]+") && Integer.parseInt(numOfMarketToManagement) > 0 && Integer.parseInt(numOfMarketToManagement) <= Integer.parseInt(numOfMarkets)) {
                running = false;
            }
        }
        running = true;
        System.out.println("Enter the number of shelves you have in store and in storage");
        numOfShelves = input.nextLine();
        if (numOfShelves.matches("[0-9]+") && Integer.parseInt(numOfShelves) > 0) {
            running = false;
        }
        while (running) {
            System.out.println("The number of shelves is not valid, please enter a valid number: ");
            numOfShelves = input.nextLine();
            if (numOfShelves.matches("[0-9]+") && Integer.parseInt(numOfShelves) > 0) {
                running = false;
            }
        }
        market = chain.getMarketByIndex(Integer.parseInt(numOfMarketToManagement) - 1);
        market = new Market(Integer.parseInt(numOfShelves));
        System.out.println("if you want to create the default market, write yes: ");
        String answer = input.nextLine();
        if (answer.equals("yes")) {
            System.out.println("The BarCodes are: \n");
            defaultMarket(market);
            System.out.println("");
        }
        running = true;
        while (running) {
            String categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, productCatalogNumber, reason, quantity, minQuantity, uniqueCode, weight, discount;
            int marketNum;
            Date expirationDate;
            Product product;
            System.out.println("-------- Welcome to the Business.Stock menu of market number " + Integer.parseInt(numOfMarketToManagement) + " --------");
            System.out.println("1) Add a new product. ");
            System.out.println("2) Update quantity of existing product. ");
            System.out.println("3) Sell/remove from stock a single product. ");
            System.out.println("4) Change the store and storage sizes. ");
            System.out.println("5) Inform on a defected/ expired product. ");
            System.out.println("6) Reports. ");
            System.out.println("7) Get information on selected product. ");
            System.out.println("8) Update the discounts. ");
            System.out.println("9) Show all the products that sold. ");
            System.out.println("10) Change min quantity to product. ");
            System.out.println("11) Show all shortages in stock. ");
            System.out.println("12) Exit. ");
            System.out.println("Select the number you would like to access.");
            System.out.println("-----------------------");
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    System.out.println("Whats is your product's category? ");
                    categoryStr = input.nextLine();
                    if (!categoryStr.matches("[a-zA-Z' ]+")) {
                        System.out.println("your product's category is not a valid string ");
                        break;
                    }
                    System.out.println("Whats is your product's sub-category? ");
                    subCategoryStr = input.nextLine();
                    if (!subCategoryStr.matches("[a-zA-Z0-9% ]+")) {
                        System.out.println("your product's subCategory is not a valid string ");
                        break;
                    }
                    System.out.println("Whats is your product's sub-sub-category, in <double string> format? ");
                    subSubCategoryStr = input.nextLine();
                    validSubSubCategory = checkSubSubCategory(subSubCategoryStr);
                    if (!validSubSubCategory) {
                        break;
                    }

                    System.out.println("Whats is your product's manufacturer? ");
                    manufacturer = input.nextLine();
                    if (!manufacturer.matches("[a-zA-Z' ]+")) {
                        System.out.println("your product's manufacturer is not a valid string ");
                        break;
                    }

                    System.out.println("Whats is your product's quantity? ");
                    quantity = input.nextLine();
                    if (!(quantity.matches("[0-9]+") && Integer.parseInt(quantity) > 0)) {
                        System.out.println("your product's quantity is not a positive number ");
                        break;
                    }


                    System.out.println("Whats is your product's weight? ");
                    weight = input.nextLine();
                    if (!(weight.matches("[0-9.]+") && Double.parseDouble(weight) > 0)) {
                        System.out.println("your product's weight is not a positive number ");
                        break;
                    }

                    System.out.println("Whats is your product's minimum quantity? ");
                    minQuantity = input.nextLine();
                    if (!(minQuantity.matches("[0-9]+") && Integer.parseInt(minQuantity) > 0)) {
                        System.out.println("your product's minimum quantity is not a positive number ");
                        break;
                    }

                    expirationDate = dateInput();
                    if (expirationDate == null) {
                        break;
                    }
                    if (!market.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, Integer.parseInt(quantity), Integer.parseInt(minQuantity), Double.parseDouble(weight), expirationDate)) {
                        System.out.println("The product already exist in stock! ");
                        break;
                    } else {
                        System.out.println("Business.Product added! ");
                    }
                    break;

                case "2":
                    System.out.println("Whats is your product's category? ");
                    categoryStr = input.nextLine();
                    if (!categoryStr.matches("[a-zA-Z' ]+")) {
                        System.out.println("your product's category is not a valid string ");
                        break;
                    }

                    System.out.println("Whats is your product's sub-category? ");
                    subCategoryStr = input.nextLine();
                    if (!subCategoryStr.matches("[a-zA-Z0-9% ]+")) {
                        System.out.println("your product's subCategory is not a valid string ");
                        break;
                    }

                    System.out.println("What is your product's sub-sub-category? ");
                    subSubCategoryStr = input.nextLine();
                    validSubSubCategory = checkSubSubCategory(subSubCategoryStr);
                    if (!validSubSubCategory) {
                        break;
                    }
                    product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);
                    if (product == null) {
                        System.out.println("Business.Product was not found.");
                        break;
                    }

                    System.out.println("How many " + product.getSubCategoryName().getName() + " " + product.getSubSubCategory().getName() + " do you want to add? ");
                    quantity = input.nextLine();
                    if (!(quantity.matches("[0-9]+") && Integer.parseInt(quantity) > 0)) {
                        System.out.println("You have to add a positive number for quantity ");
                        break;
                    }
                    expirationDate = dateInput();
                    if (expirationDate == null) {
                        break;
                    }
                    product.addMoreItemsToProduct(Integer.parseInt(quantity), expirationDate);
                    System.out.println("Business.Product's quantity updated! ");
                    break;
                case "3":
                    System.out.println("What is the ID of the product you sell/remove? ");
                    String productID = input.nextLine();
                    Product sold = market.getByProductID(productID);
                    // looks for product if found return it else return null
                    if (sold == null) {
                        System.out.println("The product was not found!");
                        break;
                    }
                    System.out.println("What is the  product  quantity you sell/remove? ");

                    quantity = input.nextLine();
                    if (!quantity.matches("[0-9]+") && Integer.parseInt(quantity) < 0 || Integer.parseInt(quantity) >= 31) {
                        System.out.println("The quantity value was invalid! you can by up to 30 products at once");
                        break;
                    }
                    market.sellProductsByID(productID, Integer.parseInt(quantity));
                    if(sold.getStoreQuantity() + sold.getStorageQuantity() < market.stock.getBlackLine(sold)){
                        System.out.println("ALERT!!!!\nthe product: " + sold.getName()+" is under the minimum quantity");

                    }

                    break;


                case "4":
                    String addedShelves;
                    char option;
                    boolean flag = true;
                    while (flag) {
                        System.out.println("which part of the store you want to update?");
                        System.out.println("1) Update the size of the storage.");
                        System.out.println("2) Update the size of the store.");
                        System.out.println("3) return to menu.");
                        option = input.next().charAt(0);
                        input.nextLine();
                        switch (option) {
                            case '1':
                                System.out.println("How many shelves you want to add to the storage? ");
                                addedShelves = input.nextLine();
                                if (!(addedShelves.matches("[0-9]+") && Integer.parseInt(addedShelves) > 0)) {
                                    System.out.println("You have to add a positive number of shelves ");
                                    break;
                                }
                                if (!market.appendStorage(Integer.parseInt(addedShelves))) {
                                    break;
                                } else {
                                    System.out.println("The shelves added to the storage! ");
                                }
                                flag = false;
                                break;
                            case '2':
                                System.out.println("How many shelves you want to add to the store? ");
                                addedShelves = input.nextLine();
                                if (!(addedShelves.matches("[0-9]+") && Integer.parseInt(addedShelves) > 0)) {
                                    System.out.println("You have to add a positive number of shelves ");
                                    break;
                                }
                                if (!market.appendStore(Integer.parseInt(addedShelves))) {
                                    break;
                                } else {
                                    System.out.println("The shelves added to the store! ");
                                }
                                flag = false;
                                break;
                            case '3':
                                flag = false;
                                break;
                            default:
                                System.out.println("Wrong input");
                                break;
                        }
                    }
                    break;
                case "5":
                    System.out.println("What is the catalog number of the defected product? ");
                    productCatalogNumber = input.nextLine();
                    Product defected = market.getByProductID(productCatalogNumber);
                    // looks for product by ID if found return it else return null
                    if (defected == null) {
                        System.out.println("The product was not found!");
                        break;
                    }
                    System.out.println("What is the unique code (barcode) of the product? ");
                    uniqueCode = input.nextLine();
                    if (!(uniqueCode.matches("[0-9]+") && Integer.parseInt(uniqueCode) > 0)) {
                        System.out.println("You have to enter a positive number of barcode ");
                        break;
                    }
                    if (defected.getUniqueProduct(Integer.parseInt(uniqueCode))) {
                        System.out.println("What is the Problem with the product? ");
                        reason = input.nextLine();
                        if (!reason.matches("[a-zA-Z0-9/ ]+")) {
                            System.out.println("The Problem with the product is not a valid string ");
                            break;
                        }
                        defected.markAsDamaged(Integer.parseInt(uniqueCode), reason);
                    } else {
                        System.out.println("The unique code (barcode) is invalid!");
                    }
                    break;
                case "6":
                    char report;
                    flag = true;
                    while (flag) {
                        System.out.println("which report you want to create? ");
                        System.out.println("1) Create stock report.");
                        System.out.println("2) Create order report.");
                        System.out.println("3) Create damaged products report.");
                        System.out.println("4) return to menu.");
                        report = input.next().charAt(0);
                        input.nextLine();
                        switch (report) {
                            case '1':
                                if (!market.createStockReport()) {
                                    System.out.println("-------- Error in creation of stock report --------");
                                }
                                flag = false;
                                break;

                            case '2':
                                if (!market.createOrderReport()) {
                                    System.out.println("-------- Error in creation of order report --------");
                                }
                                flag = false;
                                break;

                            case '3':
                                if (!market.createDamagedReport()) {
                                    System.out.println("-------- Error in creation of damaged report --------");
                                }
                                flag = false;
                                break;

                            case 4:
                                flag = false;

                            default:
                                System.out.println("Wrong input");
                                break;
                        }
                    }
                    break;
                case "7":
                    // information about specific product.
                    flag = true;
                    char check;
                    while (flag) {
                        System.out.println("How do you want to receive the information about product? ");
                        System.out.println("1) Information by product's categories.");
                        System.out.println("2)  Information by product's catalog number.");
                        System.out.println("3) return to menu.");
                        check = input.next().charAt(0);
                        input.nextLine();
                        switch (check) {
                            case '1':
                                System.out.println("Whats is your product's category? ");
                                categoryStr = input.nextLine();
                                if (!categoryStr.matches("[a-zA-Z' ]+")) {
                                    System.out.println("your product's category is not a valid string ");
                                    break;
                                }
                                System.out.println("Whats is your product's sub-category? ");
                                subCategoryStr = input.nextLine();
                                if (!subCategoryStr.matches("[a-zA-Z0-9% ]+")) {
                                    System.out.println("your product's subCategory is not a valid string ");
                                    break;
                                }
                                System.out.println("Whats is your product's sub-sub-category, in <double string> format? ");
                                subSubCategoryStr = input.nextLine();

                                validSubSubCategory = checkSubSubCategory(subSubCategoryStr);
                                if (!validSubSubCategory) {
                                    break;
                                }

                                product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);
                                if (product == null) {
                                    System.out.println("Business.Product was not found.");
                                    flag = false;
                                    break;
                                }
                                market.printProductInformation(1, product);
                                flag = false;
                                break;
                            case '2':
                                System.out.println("What is the catalog number of the product? ");
                                productCatalogNumber = input.nextLine();
                                product = market.getByProductID(productCatalogNumber);
                                // looks for product by ID if found return it else return null
                                if (product == null) {
                                    System.out.println("The product was not found!");
                                    flag = false;
                                    break;
                                }
                                market.printProductInformation(2, product);
                                flag = false;
                                break;
                            case 3:
                                flag = false;
                                break;
                            default:
                                System.out.println("Wrong input");
                                break;
                        }
                    }
                    break;
                case "8":
                    //update the discounts
                    flag = true;
                    char discountOption;
                    while (flag) {
                        System.out.println("Do you want to update a discount on a category or product? ");
                        System.out.println("1) Create a discount on a category.");
                        System.out.println("2) Create a discount on a product.");
                        System.out.println("3) return to menu.");
                        discountOption = input.next().charAt(0);
                        input.nextLine();
                        switch (discountOption) {
                            case '1':
                                System.out.println("what is the category name? ");
                                categoryStr = input.nextLine();
                                if (!categoryStr.matches("[a-zA-Z' ]+")) {
                                    System.out.println("your product's category is not a valid string ");
                                    break;
                                }
                                if (market.isCategoryExist(categoryStr)) {       // boolean
                                    System.out.println("what is the value of the discount? (calculated as %) ");
                                    discount = input.nextLine();
                                    if (!discount.matches("[0-9.]+")) {
                                        System.out.println("your discount's value is not a positive number ");
                                        break;
                                    }
                                    if (Double.parseDouble(discount) <= 0 || Double.parseDouble(discount) >= 100) {
                                        System.out.println("Wrong discount value");
                                        flag = false;
                                        break;
                                    }
                                    market.setDiscountToCategory(categoryStr, Double.parseDouble(discount));
                                    flag = false;
                                    break;
                                } else {
                                    System.out.println("The category was not found.");
                                    flag = false;
                                    break;
                                }
                            case 2:
                                boolean flag1 = true;
                                while (flag1) {
                                    System.out.println("How you want to get the product to update discount? ");
                                    System.out.println("1) by product's categories.");
                                    System.out.println("2) by product's catalog number.");
                                    System.out.println("3) return to menu.");
                                    check = input.next().charAt(0);
                                    input.nextLine();
                                    switch (check) {
                                        case 1:
                                            System.out.println("Whats is your product's category? ");
                                            categoryStr = input.nextLine();
                                            if (!categoryStr.matches("[a-zA-Z' ]+")) {
                                                System.out.println("your product's category is not a valid string ");
                                                break;
                                            }
                                            System.out.println("Whats is your product's sub-category? ");
                                            subCategoryStr = input.nextLine();
                                            if (!subCategoryStr.matches("[a-zA-Z0-9% ]+")) {
                                                System.out.println("your product's subCategory is not a valid string ");
                                                break;
                                            }
                                            System.out.println("Whats is your product's sub-sub-category, in <double string> format? ");
                                            subSubCategoryStr = input.nextLine();

                                            validSubSubCategory = checkSubSubCategory(subSubCategoryStr);
                                            if (!validSubSubCategory) {
                                                break;
                                            }
                                            product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);
                                            if (product == null) {
                                                System.out.println("Business.Product was not found.");
                                                flag1 = false;
                                                break;
                                            }

                                            System.out.println("what is the value of the discount? (calculated as %) ");
                                            discount = input.nextLine();
                                            if (!discount.matches("[0-9.]+")) {
                                                System.out.println("your discount's value is not a positive number ");
                                                break;
                                            }
                                            if (Double.parseDouble(discount) <= 0 || Double.parseDouble(discount) >= 100) {
                                                System.out.println("Wrong discount value");
                                                flag1 = false;
                                                break;
                                            }
                                            product.setDiscount(Double.parseDouble(discount));
                                            // not sure if i need to send the value or to divide by 100
                                            flag1 = false;
                                            break;
                                        case 2:
                                            System.out.println("What is the catalog number of the product? ");
                                            productCatalogNumber = input.nextLine();
                                            product = market.getByProductID(productCatalogNumber);
                                            // looks for product by ID if found return it else return null
                                            if (product == null) {
                                                System.out.println("The product was not found!");
                                                flag1 = false;
                                                break;
                                            }
                                            System.out.println("what is the value of the discount? (calculated as %) ");
                                            discount = input.nextLine();
                                            if (!discount.matches("[0-9.]+")) {
                                                System.out.println("your discount's value is not a positive number ");
                                                break;
                                            }
                                            if (Double.parseDouble(discount) <= 0 || Double.parseDouble(discount) >= 100) {
                                                System.out.println("Wrong discount value");
                                                flag1 = false;
                                                break;
                                            }
                                            product.setDiscount(Double.parseDouble(discount));
                                            flag1 = false;
                                            break;
                                        case 3:
                                            flag1 = false;

                                        default:
                                            System.out.println("Wrong input");
                                            break;
                                    }
                                }
                        }
                    }
                    break;
                case "9":
                    market.printSold();
                    break;

                case "10":
                    flag = true;
                    while (flag) {
                        System.out.println("which way you want to Update minimum amount? ");
                        System.out.println("1) by product's categories.");
                        System.out.println("2) by product's ID.");
                        System.out.println("3) return to menu.");
                        check = input.next().charAt(0);
                        input.nextLine();
                        String quantityStr;
                        switch (check) {

                            case '1':
                                System.out.println("Whats is your product's category? ");
                                categoryStr = input.nextLine();
                                if (!categoryStr.matches("[a-zA-Z' ]+")) {
                                    System.out.println("your product's category is not a valid string ");
                                    break;
                                }
                                System.out.println("Whats is your product's sub-category? ");
                                subCategoryStr = input.nextLine();
                                if (!subCategoryStr.matches("[a-zA-Z0-9% ]+")) {
                                    System.out.println("your product's subCategory is not a valid string ");
                                    break;
                                }
                                System.out.println("Whats is your product's sub-sub-category, in <double string> format? ");
                                subSubCategoryStr = input.nextLine();

                                validSubSubCategory = checkSubSubCategory(subSubCategoryStr);
                                if (!validSubSubCategory) {
                                    break;
                                }

                                product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);
                                if (product == null) {
                                    System.out.println("Business.Product was not found.");
                                    flag = false;
                                    break;
                                }
                                System.out.println("What is the new minimum quantity? ");
                                quantityStr = input.nextLine();
                                if (!(quantityStr.matches("[0-9]+") && Integer.parseInt(quantityStr) > 0)) {
                                    System.out.println("You have to add a positive number of shelves ");
                                    break;
                                }

                                product.setMinimumQuantity(Integer.parseInt(quantityStr));
                                System.out.println("The new minimum quantity of " + product.getName() + " is " + quantityStr);
                                flag = false;
                                break;
                            case '2':
                                System.out.println("What is the catalog number of the product? ");
                                productCatalogNumber = input.nextLine();
                                product = market.getByProductID(productCatalogNumber);
                                // looks for product by ID if found return it else return null
                                if (product == null) {
                                    System.out.println("The product was not found!");
                                    flag = false;
                                    break;
                                }
                                System.out.println("What is the new minimum quantity? ");
                                quantityStr = input.nextLine();
                                if (!(quantityStr.matches("[0-9]+") && Integer.parseInt(quantityStr) > 0)) {
                                    System.out.println("You have to add a positive number of shelves ");
                                    break;
                                }

                                product.setMinimumQuantity(Integer.parseInt(quantityStr));
                                System.out.println("The new minimum quantity of " + product.getName() + " is " + quantityStr);
                                flag = false;
                                break;
                            case 3:
                                flag = false;
                                break;
                            default:
                                System.out.println("Wrong input");
                                break;
                        }
                    }
                    break;

                case "11":

                    market.printShortages();
                    break;

                case "12":
                    running = false;
                    break;

                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    Boolean checkSubSubCategory(String subSubCategoryStr) {
        /**
         * Checks if a given string matches the format of a sub-sub category.
         * A sub-sub category should consist of a number followed by a space and a word.
         * Example: "5 g", "1 l", "10 p".
         *
         * @param subSubCategoryStr the sub-sub category string to be checked
         * @return true if the string matches the format of a sub-sub category, false otherwise
         */
        String[] parts = subSubCategoryStr.split(" ");
        double number;
        if (parts.length == 2) {
            try {
                number = Double.parseDouble(parts[0]);
                String word = parts[1];
                if (!word.matches("[a-zA-Z]+")) {
                    System.out.println("your product's subSubCategory does not match the format.");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("your product's subSubCategory does not match the format.");
                return false;
            }
        } else {
            System.out.println("your product's subSubCategory does not match the format.");
            return false;
        }
        return true;
    }

    Date dateInput() {
        /**
         * Prompts the user to enter a date in the format "dd/MM/yyyy" and returns it as a Date object.
         * If the date is invalid or in the past, it prints an error message and returns null.
         *
         * @return the date entered by the user as a Date object, or null if it is invalid or in the past.
         */
        Scanner input = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter a expiration date in dd/MM/yyyy format:");
        String dateStr = input.nextLine();
        String[] parts = dateStr.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (month < 1 || month > 12) {
            System.out.println("Invalid month");
            return null;
        }
        if (day < 1 || day > 31) {
            System.out.println("Invalid day");
            return null;
        }
        LocalDate date = LocalDate.of(year, month, day);
        if (date.isBefore(LocalDate.now())) {
            System.out.println("The date is not in the future");
        }
        Date dateToReturn = Date.from(date.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        return dateToReturn;
    }

    public void defaultMarket(Market market) {
        /**
         * Adds several default products to the provided market object.
         * The products include dairy, bakery, meat, fruits, vegetables, snacks, and beverages.
         * Each product has a name, a brand, a weight or volume, a supplier, a price, a quantity, a discount, and an expiration date.
         * @param market The market object to which the products will be added.
         */
        Date d1 = new Date(2024, Calendar.MAY, 23);
        Date d2 = new Date(2023, Calendar.JUNE, 12);
        Date d3 = new Date(2023, Calendar.JULY, 1);
        Date d4 = new Date(2023, Calendar.AUGUST, 15);
        Date d5 = new Date(2023, Calendar.SEPTEMBER, 2);
        Date d6 = new Date(2023, Calendar.OCTOBER, 10);
        Date d7 = new Date(2023, Calendar.NOVEMBER, 6);
        Date d8 = new Date(2024, Calendar.JANUARY, 24);
        Date d9 = new Date(2024, Calendar.FEBRUARY, 8);
        Date d10 = new Date(2024, Calendar.MARCH, 15);
        Date d11 = new Date(2024, Calendar.APRIL, 9);
        Date d12 = new Date(2023, Calendar.OCTOBER, 6);
        Date d13 = new Date(2023, Calendar.NOVEMBER, 24);
        Date d14 = new Date(2023, Calendar.DECEMBER, 17);
        Date d15 = new Date(2024, Calendar.JANUARY, 20);
        Date d16 = new Date(2024, Calendar.FEBRUARY, 8);
        Date d17 = new Date(2024, Calendar.MARCH, 8);
        Date d18 = new Date(2024, Calendar.APRIL, 5);
        Date d19 = new Date(2024, Calendar.MAY, 23);
        Date d20 = new Date(2024, Calendar.JUNE, 12);
        Date d21 = new Date(2024, Calendar.JULY, 16);
        Date d22 = new Date(2024, Calendar.AUGUST, 3);
        Date d23 = new Date(2024, Calendar.SEPTEMBER, 1);
        Date d24 = new Date(2024, Calendar.OCTOBER, 1);
        Date d25 = new Date(2024, Calendar.NOVEMBER, 7);
        Date d26 = new Date(2024, Calendar.DECEMBER, 13);
        Date d27 = new Date(2025, Calendar.JANUARY, 18);
        Date d28 = new Date(2025, Calendar.FEBRUARY, 26);
        Date d29 = new Date(2025, Calendar.MARCH, 13);
        Date d30 = new Date(2025, Calendar.APRIL, 16);
        Date d31 = new Date(2025, Calendar.MAY, 12);
        Date d32 = new Date(2025, Calendar.JUNE, 14);
        market.addNewProduct("Dairy", "Milk 3%", "1 l", "Dairy Co.", 100, 20, 1, d1);
        market.addNewProduct("Bakery", "White bread", "500 g", "Bakery Co.", 40, 10, 0.5, d2);
        market.addNewProduct("Dairy", "Milk 1%", "1.5 l", "Dairy Co.", 50, 10, 1.5, d1);
        market.addNewProduct("Bakery", "Bagels", "100 g", "Bagel Co.", 40, 8, 0.1, d9);
        market.addNewProduct("Meat", "Beef", "300 g", "Meat Co.", 20, 5, 0.3, d10);
        market.addNewProduct("Fruits", "Bananas", "50 g", "Fruit Farms", 60, 12, 0.05, d11);
        market.addNewProduct("Vegetables", "Carrots", "500 g", "Veggie Co.", 30, 6, 0.5, d12);
        market.addNewProduct("Snacks", "Cookies", "250 g", "Cookie Co.", 80, 16, 0.25, d13);
        market.addNewProduct("Beverages", "Soda", "2 l", "Soda Co.", 100, 20, 2, d14);
        market.addNewProduct("Beverages", "Soda", "1 l", "Soda Co.", 100, 20, 1, d14);
        market.addNewProduct("Dairy", "Yogurt", "200 g", "Yogurt Co.", 50, 10, 0.2, d15);
        market.addNewProduct("Bakery", "Croissants", "150 g", "Croissant Co.", 40, 8, 0.15, d16);
        market.addNewProduct("Meat", "Chicken", "400 g", "Poultry Co.", 20, 5, 0.4, d17);
        market.addNewProduct("Bakery", "Baguette", "400 g", "Bakery Co.", 30, 6, 0.4, d18);
        market.addNewProduct("Fruits", "Oranges", "1 kg", "Fruit Farms", 70, 14, 1, d19);
        market.addNewProduct("Vegetables", "Tomatoes", "1 kg", "Veggie Co.", 40, 8, 1, d20);
        market.addNewProduct("Dairy", "Cheese", "100 g", "Cheese Co.", 60, 12, 0.1, d21);
        market.addNewProduct("Snacks", "Potato chips", "200 g", "Snack Co.", 100, 20, 0.2, d22);
        market.addNewProduct("Beverages", "Milkshake", "500 ml", "Milkshake Co.", 50, 10, 0.5, d23);
        market.addNewProduct("Beverages", "Juice", "1 l", "Juice Co.", 80, 16, 1, d24);
        market.addNewProduct("Dairy", "Butter", "250 g", "Butter Co.", 40, 8, 0.25, d25);
        market.addNewProduct("Bakery", "Croissants", "100 g", "Croissant Co.", 20, 5, 0.1, d26);
        market.addNewProduct("Meat", "Pork", "500 g", "Meat Co.", 30, 6, 0.5, d27);
        market.addNewProduct("Fruits", "Apples", "500 g", "Fruit Farms", 60, 12, 0.5, d28);
        market.addNewProduct("Vegetables", "Onions", "500 g", "Veggie Co.", 20, 4, 0.5, d29);
        market.addNewProduct("Snacks", "Nuts", "300 g", "Nuts Co.", 50, 10, 0.3, d30);
        market.addNewProduct("Beverages", "Energy drink", "250 ml", "Energy Co.", 100, 20, 0.25, d31);
        market.addNewProduct("Dairy", "Sour cream", "250 g", "Sour Cream Co.", 40, 8, 0.25, d32);
        market.addNewProduct("Dairy", "Cheese", "250 g", "Dairy Co.", 60, 15, 0.25, d1);
        market.addNewProduct("Bakery", "Multigrain bread", "1 kg", "Bakery Co.", 45, 10, 1, d2);
        market.addNewProduct("Dairy", "Butter", "500 g", "Dairy Co.", 70, 14, 0.5, d3);
        market.addNewProduct("Bakery", "Cinnamon rolls", "300 g", "Bakery Co.", 50, 12, 0.3, d4);
        market.addNewProduct("Meat", "Pork chops", "400 g", "Meat Co.", 25, 5, 0.4, d5);
        market.addNewProduct("Fruits", "Apples", "1 kg", "Fruit Farms", 80, 16, 1, d6);
        market.addNewProduct("Vegetables", "Broccoli", "250 g", "Veggie Co.", 35, 7, 0.25, d7);
        market.addNewProduct("Snacks", "Potato chips", "1 kg", "Snack Co.", 70, 14, 1, d8);
        market.addNewProduct("Beverages", "Coffee", "1 kg", "Coffee Co.", 120, 24, 1, d10);
        market.addNewProduct("Dairy", "Sour cream", "200 g", "Dairy Co.", 45, 9, 0.2, d11);
        market.addNewProduct("Meat", "Lamb", "500 g", "Meat Co.", 30, 6, 0.5, d13);
        market.addNewProduct("Fruits", "Oranges", "500 g", "Fruit Farms", 70, 14, 0.5, d14);
        market.addNewProduct("Vegetables", "Cucumbers", "100 g", "Veggie Co.", 25, 5, 0.1, d15);
        market.addNewProduct("Snacks", "Pretzels", "50 g", "Snack Co.", 50, 10, 0.5, d16);
        market.addNewProduct("Beverages", "Juice", "2 l", "Juice Co.", 80, 16, 2, d17);
        market.addNewProduct("Beverages", "Energy drink", "500 ml", "Energy Co.", 90, 18, 0.5, d18);
        market.addNewProduct("Dairy", "Whipped cream", "200 g", "Dairy Co.", 50, 10, 0.2, d19);
        market.addNewProduct("Bakery", "Croissants", "250 g", "Croissant Co.", 45, 9, 0.25, d20);
        market.addNewProduct("Dairy", "Greek Yogurt", "500 g", "Yogurt Co.", 70, 14, 0.5, d3);
        market.addNewProduct("Bakery", "Cinnamon Rolls", "200 g", "Croissant Co.", 45, 9, 0.2, d19);
        market.addNewProduct("Fruits", "Strawberries", "250 g", "Fruit Farms", 80, 16, 0.25, d16);
        market.addNewProduct("Vegetables", "Broccoli", "500 g", "Veggie Co.", 35, 7, 0.5, d10);
        market.addNewProduct("Snacks", "Potato Chips", "100 g", "Snack Co.", 50, 10, 0.1, d27);
        market.addNewProduct("Beverages", "Orange Juice", "1 l", "Juice Co.", 90, 18, 1, d12);
        market.addNewProduct("Bakery", "Baguette", "500 g", "Bakery Co.", 30, 6, 0.5, d23);
        market.addNewProduct("Dairy", "Butter", "100 g", "Dairy Co.", 60, 12, 0.1, d15);
        market.addNewProduct("Meat", "Ground Beef", "1 kg", "Meat Co.", 60, 12, 1, d8);
        market.addNewProduct("Fruits", "Pineapple", "500 g", "Fruit Farms", 40, 8, 0.5, d28);
        market.addNewProduct("Vegetables", "Potatoes", "2 kg", "Veggie Co.", 20, 5, 2, d7);
        market.addNewProduct("Snacks", "Trail Mix", "250 g", "Snack Co.", 55, 11, 0.25, d22);
        market.addNewProduct("Beverages", "Apple Cider", "2 l", "Juice Co.", 100, 20, 2, d26);
        market.addNewProduct("Dairy", "Cheese", "200 g", "Dairy Co.", 65, 13, 0.2, d18);
        market.addNewProduct("Meat", "Lamb Chops", "400 g", "Poultry Co.", 35, 9, 0.4, d13);
        market.addNewProduct("Fruits", "Watermelon", "1 kg", "Fruit Farms", 30, 6, 1, d31);
        market.addNewProduct("Vegetables", "Lettuce", "200 g", "Veggie Co.", 25, 5, 0.2, d21);
        market.addNewProduct("Snacks", "Pretzels", "250 g", "Snack Co.", 50, 10, 0.25, d24);
        market.addNewProduct("Beverages", "Iced Tea", "1.5 l", "Soda Co.", 80, 16, 1.5, d17);
        market.addNewProduct("Beverages", "Tea", "100 g", "Tea Co.", 30, 8, 0.1, d4);
        market.addNewProduct("Dairy", "Cottage cheese", "500 g", "Dairy Co.", 50, 10, 0.5, d19);
        market.addNewProduct("Fruits", "Pineapple", "1 kg", "Fruit Farms", 80, 16, 1, d13);
        market.addNewProduct("Vegetables", "Potatoes", "1 kg", "Veggie Co.", 20, 4, 1, d17);
        market.addNewProduct("Snacks", "Popcorn", "200 g", "Popcorn Co.", 60, 12, 0.2, d22);
        market.addNewProduct("Beverages", "Coffee", "500 g", "Coffee Co.", 100, 20, 0.5, d29);
        market.addNewProduct("Dairy", "Mozzarella", "250 g", "Cheese Co.", 70, 14, 0.25, d30);
        market.addNewProduct("Bakery", "Brownies", "200 g", "Bakery Co.", 50, 10, 0.2, d31);
        market.addNewProduct("Meat", "Salmon", "400 g", "Fish Co.", 80, 16, 0.4, d7);
        market.addNewProduct("Fruits", "Grapes", "500 g", "Fruit Farms", 40, 8, 0.5, d1);
        market.addNewProduct("Vegetables", "Lettuce", "500 g", "Veggie Co.", 30, 6, 0.5, d2);
        market.addNewProduct("Snacks", "Crackers", "200 g", "Cracker Co.", 60, 12, 0.2, d3);
        market.addNewProduct("Beverages", "Wine", "750 ml", "Wine Co.", 150, 30, 0.75, d4);
        market.addNewProduct("Dairy", "Cream cheese", "200 g", "Dairy Co.", 40, 8, 0.2, d5);
        market.addNewProduct("Meat", "Turkey", "500 g", "Poultry Co.", 30, 6, 0.5, d5);
        market.addNewProduct("Fruits", "Mangoes", "1 kg", "Fruit Farms", 100, 20, 1, d4);
        market.addNewProduct("Vegetables", "Cucumbers", "500 g", "Veggie Co.", 20, 4, 0.5, d22);
        market.addNewProduct("Snacks", "Pretzel sticks", "200 g", "Pretzel Co.", 50, 10, 0.2, d7);
    }
}