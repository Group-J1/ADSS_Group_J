import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *         UI menu:
 *         1) Add a new product.
 *         2) Update quantity of existing product.
 *         3) sell/remove from stock a single product.
 *         4) change the store sizes.
 *         5) inform on a defected/expired product.
 *         6) Reports.
 *         7) get information on selected product.
 *         8) update the discounts.
 *         9) Exit.
 *
// */


public class UI {
    public void startMenu() {
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
        if (numOfMarketToManagement.matches("[0-9]+") && Integer.parseInt(numOfMarketToManagement) > 0 &&
                Integer.parseInt(numOfMarketToManagement) <= Integer.parseInt(numOfMarkets)) {
            running = false;
        }
        while (running) {
            System.out.println("The number of market is not valid, please enter a valid number of market: ");
            numOfMarketToManagement = input.nextLine();
            if (numOfMarketToManagement.matches("[0-9]+") && Integer.parseInt(numOfMarketToManagement) > 0 &&
                    Integer.parseInt(numOfMarketToManagement) <= Integer.parseInt(numOfMarkets)) {
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
        running = true;
        while (running) {
            String categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, productCatalogNumber, reason, quantity,
                    minQuantity, uniqueCode, weight, discount;
            int marketNum;
            Date expirationDate;
            Product product;

            System.out.println("-------- Welcome to the Stock menu of market number " +
                    Integer.parseInt(numOfMarketToManagement) + " --------");
            System.out.println("1) Add a new product.");
            System.out.println("2) Update quantity of existing product.");
            System.out.println("3) sell/remove from stock a single product. ");
            System.out.println("4) change the store sizes. ");
            System.out.println("5) inform on a defected/expired product.");
            System.out.println("6) Reports.");
            System.out.println("7) get information on selected product.");
            System.out.println("8) update the discounts.");
            System.out.println("9) Exit.");
            System.out.println("Select the number you would like to access.");
            System.out.println("-----------------------");
            char selection = input.next().charAt(0);
            input.nextLine();
            switch (selection) {
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
                    if (!market.addNewProduct(categoryStr,subCategoryStr,subSubCategoryStr,manufacturer,
                            Integer.parseInt(quantity), Integer.parseInt(minQuantity), Double.parseDouble(weight),
                            expirationDate)) {
                        break;
                    }
                    else {
                        System.out.println("Product added! ");
                    }
                    break;

                case '2':
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
                    product = market.getProductByCategories(categoryStr,subCategoryStr,subSubCategoryStr);
                    if(product == null){
                        System.out.println("Product was not found.");
                        break;
                    }

                    System.out.println("How many " + product.getSubCategoryName().getName()+ " "
                            + product.getSubSubCategory().getName()+ " do you want to add? ");
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
                    System.out.println("Product's quantity updated! ");
                    break;
//                case '3':
//                    System.out.println("What is the ID of the product you sell/remove? ");
//                    productID = input.nextLine();
//                    Product sold = market.getByProductID(productID);
//                    // looks for product if found return it else return null
//                    if (sold == null){
//                        System.out.println("The product was not found!");
//                        break;
//                    }
//                    System.out.println("What is the  product  quantity you sell/remove? ");
//                    quantity = input.nextInt();
//                    sold.sell(quantity);
//
//                    break;
//
//
                case '4':
                    String addedShelves;
                    char option;
                    boolean flag = true;
                    while(flag) {
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
                                }
                                else {
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
                                }
                                else {
                                    System.out.println("The shelves added to the store! ");
                                }                                flag = false;
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
                case '5':
                    System.out.println("What is the catalog number of the defected product? ");
                    productCatalogNumber = input.nextLine();
                    Product defected = market.getByProductID(productCatalogNumber);
//                    // looks for product by ID if found return it else return null
                    if (defected == null){
                        System.out.println("The product was not found!");
                        break;
                    }
                    System.out.println("What is the unique code (barcode) of the product? ");
                    // the unique idb(barcode) for each instance of the product
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
                        defected.markAsDamaged(Integer.parseInt(uniqueCode),reason);
                    }
                    else {
                        System.out.println("The unique code (barcode) is invalid!");
                    }
                    break;
//                case '6':
//                    flag = true;
//                    while(flag){
//                        System.out.println("which report you want to create? ");
//                        System.out.println("1) Create order report.");
//                        System.out.println("2) Create shortages report.");
//                        System.out.println("3) Create damaged products report.");
//                        System.out.println("4) return to menu.");
//                        int report = input.nextInt();
//                        switch (report){
//                            case 1:
//                                market.createOrderReport();
//                                flag = false;
//                                break;
//
//                            case 2:
//                                market.createShortageReport();
//                                flag = false;
//                                break;
//
//                            case 3:
//                                market.createDamagedReport();
//                                flag = false;
//                                break;
//
//                            case 4:
//                                flag = false;
//
//                            default:
//                                System.out.println("Wrong input");
//                                break;
//
//                        }
//
//                    }
//                    break;
//                case '7':
//                    // information about spesific product.
//                    marketNum = getMarketNumber(chain.numberOfMarkets);
//                    market = chain.getMarketByIndex(marketNum);
//
//                    flag = true;
//                    while(flag){
//                        System.out.println("which report you want to create? ");
//                        System.out.println("1) Information by product's categories.");
//                        System.out.println("2)  Information by product's ID.");
//                        System.out.println("3) return to menu.");
//                        int check = input.nextInt();
//                        switch (check){
//                            case 1:
//                                marketNum = getMarketNumber(chain.numberOfMarkets);
//                                market = chain.getMarketByIndex(marketNum);
//
//                                System.out.println("Whats is your product's category? ");
//                                categoryStr = input.next();
//
//                                System.out.println("Whats is your product's sub-category? ");
//                                subCategoryStr = input.next();
//
//
//                                System.out.println("Whats is your product's sub-sub-category? ");
//                                subSubCategoryStr = input.next();
//
//                                product = market.getProductByCategories(categoryStr,subCategoryStr,subSubCategoryStr);
//
//                                if(product == null){
//                                    System.out.println("Product was not found.");
//                                    flag = false;
//                                    break;
//                                }
//                                product.printProductInformation();
//                                flag = false;
//                                break;
//                            case 2:
//                                System.out.println("What is the ID of the defected product? ");
//                                productID = input.next();
//                                product = market.getByProductID(productID);
//                                // looks for product by ID if found return it else return null
//                                if (product == null){
//                                    System.out.println("The product was not found!");
//                                    flag = false;
//                                    break;
//
//                                }
//                                product.printProductInformation();
//                                flag = false;
//                                break;
//                            case 3:
//                                flag = false;
//
//                            default:
//                                System.out.println("Wrong input");
//                                break;
//
//                        }
//
//                    }
//
//                    break;
//                case '8':
//                    //update the discounts
//                    marketNum = getMarketNumber(chain.numberOfMarkets);
//                    market = chain.getMarketByIndex(marketNum);
//
//                    flag = true;
//
//                    while(flag){
//                        System.out.println("which report you want to create? ");
//                        System.out.println("1) Create a discount on a category.");
//                        System.out.println("2) Create a discount on a product.");
//                        System.out.println("3) return to menu.");
//                        int discountOption = input.nextInt();
//                        switch (discountOption){
//                            case 1:
//                                System.out.println("what is the category name? ");
//                                categoryStr = input.next();
//                                if(market.isCategoryExist(categoryStr)){       // boolean
//
//                                    System.out.println("what is the value of the discount? (calculated as %) ");
//                                     discount = input.nextDouble();
//                                    if(discount <= 0 || discount >100){
//                                        System.out.println("Wrong discount value");
//                                        flag = false;
//                                        break;
//                                    }
//                                    market.setDiscountToCategory(categoryStr, discount);
//                                    flag = false;
//                                    break;
//                                }
//                                else {
//                                    System.out.println("The category was not found.");
//                                    flag = false;
//                                    break;
//                                }
//                            case 2:
//                                boolean flag1 = true;
//                                while(flag1) {
//                                    System.out.println("How you want to get to the product? ");
//                                    System.out.println("1) by product's categories.");
//                                    System.out.println("2) by product's ID.");
//                                    System.out.println("3) return to menu.");
//                                    int check = input.nextInt();
//                                    switch (check) {
//                                        case 1:
//                                            marketNum = getMarketNumber(chain.numberOfMarkets);
//                                            market = chain.getMarketByIndex(marketNum);
//
//                                            System.out.println("Whats is your product's category? ");
//                                            categoryStr = input.next();
//
//                                            System.out.println("Whats is your product's sub-category? ");
//                                            subCategoryStr = input.next();
//
//
//                                            System.out.println("Whats is your product's sub-sub-category? ");
//                                            subSubCategoryStr = input.next();
//
//                                            product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);
//
//                                            if (product == null) {
//                                                System.out.println("Product was not found.");
//                                                flag1 = false;
//                                                break;
//                                            }
//                                            System.out.println("what is the value of the discount? (calculated as %) ");
//                                             discount = input.nextDouble();
//                                            if(discount <= 0 || discount >100){
//                                                System.out.println("Wrong discount value");
//                                                flag1 = false;
//                                                break;
//                                            }
//
//                                            product.setDiscount(discount);
//                                            // not sure if i need to send the value or to divide by 100
//                                            flag1 = false;
//                                            break;
//                                        case 2:
//                                            System.out.println("What is the ID of the defected product? ");
//                                            productID = input.next();
//                                            product = market.getByProductID(productID);
//                                            // looks for product by ID if found return it else return null
//                                            if (product == null) {
//                                                System.out.println("The product was not found!");
//                                                flag1 = false;
//                                                break;
//                                            }
//                                            System.out.println("what is the value of the discount? (calculated as %) ");
//                                             discount = input.nextDouble();
//                                            if(discount <= 0 || discount >100){
//                                                System.out.println("Wrong discount value");
//                                                flag1 = false;
//                                                break;
//                                            }
//
//                                            product.setDiscount(discount);
//                                            // not sure if i need to send the value or to divide by 100
//
//                                            flag1= false;
//                                            break;
//                                        case 3:
//                                            flag1 = false;
//
//                                        default:
//                                            System.out.println("Wrong input");
//                                            break;
//
//                                    }
//                                }
//
//                        }
//                    }
//                    break;
//
                case '9':
                    running = false;
                    break;

                default:
                    System.out.println("Wrong input");
                    break;
//
//
//
//            }
//       }
//    }
            }
        }
    }

    Boolean checkSubSubCategory(String subSubCategoryStr) {
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
            }
            catch (NumberFormatException e) {
                System.out.println("your product's subSubCategory does not match the format.");
                return false;
            }
        }
        else {
            System.out.println("your product's subSubCategory does not match the format.");
            return false;
        }
        return true;
    }

    Date dateInput(){
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
}

