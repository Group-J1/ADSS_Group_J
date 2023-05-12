package Stock.Presentation;

import Stock.DataAccess.ProductDAO;
import Stock.Service.MarketService;

import java.util.Scanner;

public class MarketUI {
    private static MarketService marketService = MarketService.getInstance();

    Scanner input = new Scanner(System.in);



    public void startMenu(String numOfMarketToManagement) {
        boolean running = true;
        while (running) {
            System.out.println("-------- Welcome to the Reports menu of market number " + Integer.parseInt(numOfMarketToManagement) + " --------");
            System.out.println("1) Set discount for product by categories. ");
            System.out.println("2) Set discount for product by Category Number. ");
            System.out.println("3) Set discount for category. ");
            System.out.println("4) Add shelves to the store and the storage. ");
            System.out.println("5) Go back to Stock menu ");

            System.out.println("Select the number you would like to access ");
            System.out.println("-----------------------");
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    setDiscountByCategoriesCase1();
                    break;

                case "2":
                    setDiscountByCatalogNumberCase2();
                    break;

                case "3":
                    setDiscountForCategoryCase3();
                    break;

                case "4":
                    addShelvesToMarketCase4();
                    break;
                case "5":
                    running = false;
                    break;



                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    boolean checkIfPositiveDoubleNumber(String number) {
        try {
            double d = Double.parseDouble(number);
            return d > 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//    Boolean checkIfPositiveDoubleNumber(String number) {
//        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
//    }

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
    Boolean checkIfPositiveIntegerNumber(String number) {
        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
    }

    Boolean checkReason(String reason) {
        return reason.matches("[a-zA-Z0-9/ ]+");
    }

    private void setDiscountByCategoriesCase1(){

        String categoryStr,subCategoryStr,subSubCategoryStr,discount;

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
        System.out.println("Whats is the product's discount? ");
        discount = input.nextLine();
        if (!checkIfPositiveDoubleNumber(discount)) {
            System.out.println("your discount is not a positive number ");
            return;
        }
        marketService.setDiscountForProduct(categoryStr,subCategoryStr,subSubCategoryStr,Double.parseDouble(discount));
    }

    private void setDiscountByCatalogNumberCase2(){
        String catalogNumber,discount;
        System.out.println("Whats is the catalog number? ");
        catalogNumber = input.nextLine();
        System.out.println("Whats is the product's discount? ");
        discount = input.nextLine();
        if (!checkIfPositiveDoubleNumber(discount)) {
            System.out.println("your discount is not a positive number ");
            return;
        }
        marketService.setDiscountForProduct(catalogNumber,Double.parseDouble(discount));
    }


    private void setDiscountForCategoryCase3(){
        String categoryStr,discount;
        System.out.println("Whats is the category? ");
        categoryStr = input.nextLine();
        if (!checkIfOnlyLetters(categoryStr)) {
            System.out.println("your product's category is not a valid string ");
            return;
        }
        discount = input.nextLine();
        if (!checkIfPositiveDoubleNumber(discount)) {
            System.out.println("your discount is not a positive number ");
            return;
        }
        marketService.setDiscountForCategory(categoryStr,Double.parseDouble(discount));
    }

    private void addShelvesToMarketCase4(){
        String extraShelves;
        System.out.println("Whats is your product's minimum quantity? ");
        extraShelves = input.nextLine();
        if (!checkIfPositiveIntegerNumber(extraShelves)) {
            System.out.println("your product's minimum quantity is not a positive number ");
            return;
        }
        marketService.appendMarket(Integer.parseInt(extraShelves));

    }


}
