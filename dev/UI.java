import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;


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
 */


public class UI {
    public void startMenu() {
        Chain chain;
        boolean running = true;
        String numOfMarkets = "0";
        Scanner input = new Scanner(System.in);
        while(running) {

            System.out.println("Enter the amount of markets in the chain: ");
            numOfMarkets = input.next();
            if (numOfMarkets.matches("[0-9]+") && Integer.parseInt(numOfMarkets) > 0) {
                running = false;
            }
        }
        chain = new Chain(Integer.parseInt(numOfMarkets));
        running = true;
        while (running){
            String categoryStr,subCategoryStr,subSubCategoryStr,manufacturer,productID,reason;
            int quantity,minQuantity,uniqueCode;
            double weight,discount;
            int marketNum;
            Date expirationDate;
            Market market;
            Product product;

            System.out.println("-------- Welcome to the Stock menu --------");
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
            numOfMarkets = input.next();
            char selection = numOfMarkets.charAt(0);
            switch (selection){
                case '1':

                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);


                    System.out.println("Whats is your product's category? ");
                    categoryStr = input.next();
                    // TODO: input check for category
                    System.out.println("Whats is your product's sub-category? ");
                    subCategoryStr = input.next();
                    // TODO: input check for sub category

                    System.out.println("Whats is your product's sub-sub-category? ");
                    subSubCategoryStr = input.next();
                    // TODO: input check for sub sub category, and the print is wierd

                    System.out.println("Whats is your product's manufacturer? ");
                    manufacturer = input.next();

                    System.out.println("Whats is your product's quantity? ");
                    quantity = input.nextInt();

                    System.out.println("Whats is your product's weight? ");
                    weight = input.nextDouble();

                    System.out.println("Whats is your product's minimum quantity? ");
                    minQuantity = input.nextInt();

                    expirationDate = dateInput();

                    market.addNewProduct(categoryStr,subCategoryStr,subSubCategoryStr,manufacturer,quantity,
                            minQuantity,weight,expirationDate);
                    // did not take location as input from user!!!
                    break;

                case '2':
                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);

                    System.out.println("Whats is your product's category? ");
                    categoryStr = input.next();

                    System.out.println("Whats is your product's sub-category? ");
                    subCategoryStr = input.next();


                    System.out.println("Whats is your product's sub-sub-category? ");
                    subSubCategoryStr = input.next();

                    product = market.getProductByCategories(categoryStr,subCategoryStr,subSubCategoryStr);

                    if(product == null){
                        System.out.println("Product was not found.");
                        break;
                    }

                    System.out.println("How many "+product.getSubCategoryName().getName()+ " "
                            +product.getSubSubCategory().getName()+ " you want to add? ");
                    quantity = input.nextInt();

                    expirationDate = dateInput();

                    product.addMoreItemsToProduct(quantity, expirationDate);

                    break;
                case '3':
                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);
                    System.out.println("What is the ID of the product you sell/remove? ");
                    productID = input.next();
                    Product sold = market.getByProductID(productID);
                    // looks for product if found return it else return null
                    if (sold == null){
                        System.out.println("The product was not found!");
                        break;
                    }
                    System.out.println("What is the  product  quantity you sell/remove? ");
                    quantity = input.nextInt();
                    sold.sell(quantity);

                    break;


                case '4':


                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);

                    int addedShelves;
                    int option;
                    boolean flag = true;
                    while(flag) {
                        System.out.println("which part of the store you want to update?");
                        System.out.println("1) Update the size of the storage.");
                        System.out.println("2) Update the size of the store.");
                        System.out.println("3) return to menu.");
                        option = input.nextInt();
                        switch (option) {
                            case 1:
                                System.out.println("How many shelves you want to add to the storage? ");
                                addedShelves = input.nextInt();
                                market.appendStorage(addedShelves);
                                flag = false;
                                break;
                            case 2:
                                System.out.println("How many shelves you want to add to the store? ");
                                addedShelves = input.nextInt();
                                market.appendStore(addedShelves);
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
                case '5':

                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);

                    System.out.println("What is the ID of the defected product? ");
                    productID = input.next();
                    Product defected = market.getByProductID(productID);
                    // looks for product by ID if found return it else return null
                    if (defected == null){
                        System.out.println("The product was not found!");
                        break;

                    }
                    System.out.println("What is the unique code of the item? ");
                    // the unique id for each instance of the product
                    uniqueCode = input.nextInt();
                    if(defected.getUniqueProduct(uniqueCode)){
                        System.out.println("What is the Problem with the product? ");
                        reason = input.next();
                        defected.markAsDamaged(uniqueCode,reason);
                    }
                    else {
                        System.out.println("the Unique Code is invalid!");
                    }

                    break;

                case '6':

                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);

                    flag = true;
                    while(flag){
                        System.out.println("which report you want to create? ");
                        System.out.println("1) Create order report.");
                        System.out.println("2) Create shortages report.");
                        System.out.println("3) Create damaged products report.");
                        System.out.println("4) return to menu.");
                        int report = input.nextInt();
                        switch (report){
                            case 1:
                                market.createOrderReport();
                                flag = false;
                                break;

                            case 2:
                                market.createShortageReport();
                                flag = false;
                                break;

                            case 3:
                                market.createDamagedReport();
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
                case '7':
                    // information about spesific product.
                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);

                    flag = true;
                    while(flag){
                        System.out.println("which report you want to create? ");
                        System.out.println("1) Information by product's categories.");
                        System.out.println("2)  Information by product's ID.");
                        System.out.println("3) return to menu.");
                        int check = input.nextInt();
                        switch (check){
                            case 1:
                                marketNum = getMarketNumber(chain.numberOfMarkets);
                                market = chain.getMarketByIndex(marketNum);

                                System.out.println("Whats is your product's category? ");
                                categoryStr = input.next();

                                System.out.println("Whats is your product's sub-category? ");
                                subCategoryStr = input.next();


                                System.out.println("Whats is your product's sub-sub-category? ");
                                subSubCategoryStr = input.next();

                                product = market.getProductByCategories(categoryStr,subCategoryStr,subSubCategoryStr);

                                if(product == null){
                                    System.out.println("Product was not found.");
                                    flag = false;
                                    break;
                                }
                                product.printProductInformation();
                                flag = false;
                                break;
                            case 2:
                                System.out.println("What is the ID of the defected product? ");
                                productID = input.next();
                                product = market.getByProductID(productID);
                                // looks for product by ID if found return it else return null
                                if (product == null){
                                    System.out.println("The product was not found!");
                                    flag = false;
                                    break;

                                }
                                product.printProductInformation();
                                flag = false;
                                break;
                            case 3:
                                flag = false;

                            default:
                                System.out.println("Wrong input");
                                break;

                        }

                    }

                    break;
                case '8':
                    //update the discounts
                    marketNum = getMarketNumber(chain.numberOfMarkets);
                    market = chain.getMarketByIndex(marketNum);

                    flag = true;

                    while(flag){
                        System.out.println("which report you want to create? ");
                        System.out.println("1) Create a discount on a category.");
                        System.out.println("2) Create a discount on a product.");
                        System.out.println("3) return to menu.");
                        int discountOption = input.nextInt();
                        switch (discountOption){
                            case 1:
                                System.out.println("what is the category name? ");
                                categoryStr = input.next();
                                if(market.isCategoryExist(categoryStr)){       // boolean

                                    System.out.println("what is the value of the discount? (calculated as %) ");
                                     discount = input.nextDouble();
                                    if(discount <= 0 || discount >100){
                                        System.out.println("Wrong discount value");
                                        flag = false;
                                        break;
                                    }
                                    market.setDiscountToCategory(categoryStr, discount);
                                    flag = false;
                                    break;
                                }
                                else {
                                    System.out.println("The category was not found.");
                                    flag = false;
                                    break;
                                }
                            case 2:
                                boolean flag1 = true;
                                while(flag1) {
                                    System.out.println("How you want to get to the product? ");
                                    System.out.println("1) by product's categories.");
                                    System.out.println("2) by product's ID.");
                                    System.out.println("3) return to menu.");
                                    int check = input.nextInt();
                                    switch (check) {
                                        case 1:
                                            marketNum = getMarketNumber(chain.numberOfMarkets);
                                            market = chain.getMarketByIndex(marketNum);

                                            System.out.println("Whats is your product's category? ");
                                            categoryStr = input.next();

                                            System.out.println("Whats is your product's sub-category? ");
                                            subCategoryStr = input.next();


                                            System.out.println("Whats is your product's sub-sub-category? ");
                                            subSubCategoryStr = input.next();

                                            product = market.getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr);

                                            if (product == null) {
                                                System.out.println("Product was not found.");
                                                flag1 = false;
                                                break;
                                            }
                                            System.out.println("what is the value of the discount? (calculated as %) ");
                                             discount = input.nextDouble();
                                            if(discount <= 0 || discount >100){
                                                System.out.println("Wrong discount value");
                                                flag1 = false;
                                                break;
                                            }

                                            product.setDiscount(discount);
                                            // not sure if i need to send the value or to divide by 100
                                            flag1 = false;
                                            break;
                                        case 2:
                                            System.out.println("What is the ID of the defected product? ");
                                            productID = input.next();
                                            product = market.getByProductID(productID);
                                            // looks for product by ID if found return it else return null
                                            if (product == null) {
                                                System.out.println("The product was not found!");
                                                flag1 = false;
                                                break;
                                            }
                                            System.out.println("what is the value of the discount? (calculated as %) ");
                                             discount = input.nextDouble();
                                            if(discount <= 0 || discount >100){
                                                System.out.println("Wrong discount value");
                                                flag1 = false;
                                                break;
                                            }

                                            product.setDiscount(discount);
                                            // not sure if i need to send the value or to divide by 100

                                            flag1= false;
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

                case '9':
                    running = false;
                    break;

                default:
                    System.out.println("Wrong input");
                    break;



            }
       }
    }
    int getMarketNumber(int marketsAmount){
        int num = -1;
        boolean running = true;
        while(running) {
            System.out.println("Select the number you would like to access.");
            Scanner scanner = new Scanner(System.in);
            String numStr = scanner.next();
            if (numStr.matches("[0-9]+") && Integer.parseInt(numStr) >= 0 && Integer.parseInt(numStr) < marketsAmount) {
                num = Integer.parseInt(numStr);
                running = false;
            }
            else {
                System.out.println("wrong market number");
            }


        }
        return num;

    }

    Date dateInput(){
        Scanner input = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;
        boolean running = true;
        while (running) {
            System.out.println("Enter a expiration date in dd/MM/yyyy format:");
            String dateStr = input.nextLine();

            try {
                date = dateFormat.parse(dateStr);
                System.out.println("The date entered is: " + date);

            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date in dd/MM/yyyy format.");
            }
        }
        return date;

    }
}
