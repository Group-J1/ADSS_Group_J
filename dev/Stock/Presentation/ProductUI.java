package Stock.Presentation;
import Stock.Business.Product;
import Stock.Business.ProductManager;
import Stock.Business.UniqueStringGenerator;
import Stock.DataAccess.ProductDAO;
import Stock.Service.ProductService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ProductUI {

    private static final ProductService productService = ProductService.getInstance();

    String categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, quantity, minQuantity, weight,
            productCatalogNumber, uniqueCode, reason;
    Date expirationDate;
    Scanner input = new Scanner(System.in);

    public void startMenu(String numOfMarketToManagement) {
        boolean running = true;
        while (running) {
            System.out.println("-------- Welcome to the Product menu of market number " + Integer.parseInt(numOfMarketToManagement) + " --------");
            // case 1 at MainUI
            System.out.println("1) Add a new product ");
            // case 2 at MainUI
            System.out.println("2) Update quantity of existing product ");
            // case 5 at MainUI
            System.out.println("3) Inform on a defected/ expired product ");
            // case 7 at MainUI
            System.out.println("4) Get information on selected product ");
            // case 10 at MainUI
            System.out.println("5) Change min quantity to product ");
            // Exit from product's menu to stock's menu
            System.out.println("6) Go back to Stock menu ");

            System.out.println("Select the number you would like to access ");
            System.out.println("-----------------------");
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    addNewProductCase1();
                    break;

                case "2":
                    addMoreItemsToProductCase2();
                    break;

                case "3":
                    markAsDamagedCase3();
                    break;

                case "4":
                    ProductDAO.getInstance().updateForNextDay();
                    //MarketUI marketUI = new MarketUI();
                    //marketUI.startMenu();
                    break;

                case "5":
                    //MarketUI marketUI = new MarketUI();
                    //marketUI.startMenu();
                    break;

                case "6":
                    running = false;
                    break;

                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    // Case 1 in product's menu
    void addNewProductCase1() {
        System.out.println("Whats is your product's category? ");
        categoryStr = input.nextLine();
        if (!checkIfOnlyLetters(categoryStr)) {
            System.out.println("your product's category is not a valid string ");
            return;
        }
        System.out.println("Whats is your product's sub-category? ");
        subCategoryStr = input.nextLine();
        if (!checkSubCategory(subCategoryStr)) {
            System.out.println("your product's subCategory is not a valid string ");
            return;
        }
        System.out.println("Whats is your product's sub-sub-category, in <double string> format? ");
        subSubCategoryStr = input.nextLine();
        if (!checkSubSubCategory(subSubCategoryStr)) {
            return;
        }

        System.out.println("Whats is your product's manufacturer? ");
        manufacturer = input.nextLine();
        if (!checkIfOnlyLetters(manufacturer)) {
            System.out.println("your product's manufacturer is not a valid string ");
            return;
        }

        System.out.println("Whats is your product's quantity? ");
        quantity = input.nextLine();
        if (!checkIfPositiveIntegerNumber(quantity)) {
            System.out.println("your product's quantity is not a positive number ");
            return;
        }

        System.out.println("Whats is your product's weight? ");
        weight = input.nextLine();
        if (!checkIfPositiveDoubleNumber(weight)) {
            System.out.println("your product's weight is not a positive number ");
            return;
        }

        System.out.println("Whats is your product's minimum quantity? ");
        minQuantity = input.nextLine();
        if (!checkIfPositiveIntegerNumber(minQuantity)) {
            System.out.println("your product's minimum quantity is not a positive number ");
            return;
        }

        expirationDate = dateInput();
        if (expirationDate == null) {
            return;
        }
        if (!productService.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr, manufacturer,
                Integer.parseInt(quantity), Integer.parseInt(minQuantity), Double.parseDouble(weight), expirationDate)) {
            System.out.println("The product already exist in stock! ");
        }
        else {
            System.out.println("Product added! ");
        }
    }

    // Case 2 in product's menu
    void addMoreItemsToProductCase2() {
        System.out.println("Whats is your product's category? ");
        categoryStr = input.nextLine();
        if (!checkIfOnlyLetters(categoryStr)) {
            System.out.println("your product's category is not a valid string ");
            return;
        }
        System.out.println("Whats is your product's sub-category? ");
        subCategoryStr = input.nextLine();
        if (!checkSubCategory(subCategoryStr)) {
            System.out.println("your product's subCategory is not a valid string ");
            return;
        }
        System.out.println("What is your product's sub-sub-category? ");
        subSubCategoryStr = input.nextLine();
        if (!checkSubSubCategory(subSubCategoryStr)) {
            return;
        }
//        product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);
        Product product = productService.getProductByCategories(subCategoryStr, subSubCategoryStr);
        if (product == null) {
            System.out.println("Product was not found ");
            return;
        }
        System.out.println("How many " + product.getSubCategoryName().getName() + " " + product.getSubSubCategory().getName() + " do you want to add? ");
        quantity = input.nextLine();
        if (!checkIfPositiveIntegerNumber(quantity)) {
            System.out.println("You have to add a positive number for quantity ");
            return;
        }
        expirationDate = dateInput();
        if (expirationDate == null) {
            return;
        }
        //product.addMoreItemsToProduct(Integer.parseInt(quantity), expirationDate);
        // ---------- already exists in ProductManager ----------
        //product.setCatalogNumber();
        productService.addMoreItemsToProduct(product, expirationDate, Integer.parseInt(quantity));
        //ProductManager.getInstance().addMoreItemsToProduct(product,expirationDate,Integer.parseInt(quantity));
        System.out.println("Product's quantity updated! ");
    }

    void markAsDamagedCase3() {
        System.out.println("What is the catalog number of the defected product? ");
        productCatalogNumber = input.nextLine();
        Product defectedProduct = productService.getProductByUniqueCode(productCatalogNumber);
        //Product defected = market.getByProductID(productCatalogNumber);

        // looks for product by ID if found return it else return null
        if (defectedProduct == null) {
            System.out.println("The product was not found!");
            return;
        }
        System.out.println("What is the unique code (barcode) of the product? ");
        uniqueCode = input.nextLine();
        if (!checkIfPositiveIntegerNumber(uniqueCode)) {
            System.out.println("You have to enter a positive number of barcode ");
            return;
        }
        if (defectedProduct.getUniqueProduct(Integer.parseInt(uniqueCode))) {
            System.out.println("What is the Problem with the product? ");
            reason = input.nextLine();
            if (!checkReason(reason)) {
                System.out.println("The Problem with the product is not a valid string ");
                return;
            }
            productService.markAsDamaged(defectedProduct, Integer.parseInt(uniqueCode), reason);
            System.out.println("Product mark as Damaged! ");
            //defectedProduct.markAsDamaged(Integer.parseInt(uniqueCode), reason);
        }
        else {
            System.out.println("The unique code (barcode) is invalid!");
        }
    }



    Boolean checkIfPositiveIntegerNumber(String number) {
        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
    }

    Boolean checkIfPositiveDoubleNumber(String number) {
        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
    }

    Boolean checkIfOnlyLetters(String str) {
        return str.matches("[a-zA-Z' ]+");
    }

    Boolean checkSubCategory(String subCategoryStr) {
        return subCategoryStr.matches("[a-zA-Z0-9% ]+");
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

    Boolean checkReason(String reason) {
        return reason.matches("[a-zA-Z0-9/ ]+");
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



}
