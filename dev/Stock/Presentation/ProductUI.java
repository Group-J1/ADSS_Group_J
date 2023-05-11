package Stock.Presentation;
import Stock.Service.ProductService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ProductUI {

    private static final ProductService productService = ProductService.getInstance();

    public void startMenu(String numOfMarketToManagement) {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        String categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, quantity, minQuantity, weight;
        boolean validSubSubCategory;
        Date expirationDate;
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
            System.out.println("5) Change min quantity to product. ");
            System.out.println("6) Go back to Stock menu ");
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
                    if (!productService.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr, manufacturer,
                            Integer.parseInt(quantity), Integer.parseInt(minQuantity), Double.parseDouble(weight), expirationDate)) {
                        System.out.println("The product already exist in stock! ");
                        break;
                    } else {
                        System.out.println("Stock.Business.Product added! ");
                    }
                    break;

                case "2":
                    ReportsUI reportsUi = new ReportsUI();
                    //reportsUi.startMenu();
                    break;

                case "3":
                    MarketUI marketUI = new MarketUI();
                    //marketUI.startMenu();
                    break;

                case "4":
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



}
