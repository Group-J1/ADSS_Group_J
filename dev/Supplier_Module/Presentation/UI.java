package Supplier_Module.Presentation;

import Supplier_Module.Business.Agreement.Agreement;
import Supplier_Module.Business.Card.SupplierCard;
import Supplier_Module.Business.Managers.Order_Manager;
import Supplier_Module.Business.Supplier;
import Supplier_Module.Business.Managers.SupplyManager;
import Supplier_Module.DAO.DBTables;

import java.time.LocalDate;
import java.util.*;

public class UI {

    private static UI user = null;

    //singleton
    SupplyManager sup_manager;
    Order_Manager order_manager;
    Data Data;
    AgreementMenu agreementMenu;
    CardMenu cardMenu;
    Scanner input = new Scanner(System.in);


    private UI() {
        this.sup_manager = SupplyManager.getSupply_manager();
        this.order_manager = Order_Manager.getOrder_Manager();
        this.Data= Data.getLoadData();
        this.cardMenu=CardMenu.getCardMenu();
        this.agreementMenu = agreementMenu.getAgreementMenu();
        DBTables.getInstance();
    }

    public static UI getUser() {
        if (user == null) {
            user = new UI();
        }
        return user;
    }

    public void beginSupplierMenu(LocalDate localDate){
        int choice1 = 0;
        while (choice1!=3) {
            System.out.println("Welcome to Suppliers system! \nDo you want to upload existing data, edit current data,create new data or start over? \n1.Load data \n2.Edit existing data  \n3.Return to the login menu");
            choice1 = input.nextInt();
            switch (choice1) {
                case 1: {
                    user.Data.loadData();
                    System.out.println("Data load successfully!");
                    break;
                }

                case 2:{
                    System.out.println("Please select one of the following options:");
                    mainMenu(localDate);
                    break;

            }
                case 3:{
                    break;
                }
                default:
                    System.out.println("This is not valid option!");
            }
        }
    }


    public void mainMenu(LocalDate localDate){
        int choice = 9;
        while (choice != 0)
        {
            System.out.println("1. Add new supplier.");
            System.out.println("2. Edit existing supplier details.");
            System.out.println("3. Delete an existing supplier.");
            System.out.println("4. View order history of supplier");
            System.out.println("5. View Supplier details");
//            System.out.println("6 delete all sup");
//            System.out.println("7 delete all orders");
//            System.out.println("8 period orders");
            System.out.println("0. Back");
            choice = input.nextInt();

            switch (choice)
            {
                case 0:
                {
                    break;
                }
                case 1:
                {
                    this.CreateSupplier();
                    break;
                }

                case 2:
                {
                    this.EditSupplier();
                    break;
                }

                case 3:
                {
                    System.out.println("Please enter supplier id:");
                    int sup_id = input.nextInt();
                    this.sup_manager.removeSupplierByUser(sup_id);
                    break;
                }
                case 4:
                {
                    System.out.println("Please enter supplier ID:");
                    int id = input.nextInt();
                    this.sup_manager.PrintSupplierOrders(id);
                    break;
                }
                case 5: // work
                {
                    System.out.println("Please enter supplier ID:");
                    int id = input.nextInt();
                    this.sup_manager.PrintSupplierDetailes(id);
                    break;
                }
//                case 6:
//                {
//                    sup_manager.deleteAllData();
//                    break;
//                }
//                case 7:
//                {
//                    order_manager.deleteAllData();
//                    break;
//                }
//
//                case 8: // not good, check why
//                {
//                    Map<String,Integer> ans = new HashMap<>();
//                    ans.put("Dog Food", 200);
//                    ans.put("Chicken", 400);
//                    ans.put("Captain-Crunch", 400);
//                    ans.put("Lays-chips", 400);
//                    ans.put("Cini-Minis", 400);
//                    ans.put("Coca cola", 400);
//                    ans.put("Soup", 400);
//                    ans.put("Wipes", 400);
//                    ans.put("Banana", 400);
//                    order_manager.updatePeriodOrders(ans,localDate);
//                    break;
//                }
//                case 9: { // work but check again with another product, לבדוק מה קורה שיש רשימה עם כמה מוצרים וחלקם בדרך
//                    Map<String,Integer> ans = new HashMap<>();
//                    ans.put("Captain-Crunch", 1000);
//                    order_manager.lackReport(ans,localDate);
//                    break;
//                }
//                case 10:{// period order
//                    LinkedList<String> under_minimum = new LinkedList<>();
//                    under_minimum.add("Chicken");
//                    under_minimum.add("Banana");
//                    order_manager.ConfirmPeriodOrders(under_minimum,localDate);
//                    break;
//                }

                default:
                {
                    System.out.println("This option is not valid!");
                    break;
                }

            }
        }


    }

    public void CreateSupplier()
    {
        SupplierCard card=this.cardMenu.CreateSupplierCard();
        Agreement agreement=this.agreementMenu.CreateAgreement(card.getSupplier_number());
        this.sup_manager.CreateSupplier(card,agreement);

        //this.sup_manager.CreateSupplier(this.cardMenu.CreateSupplierCard(), this.agreementMenu.CreateAgreement());
    }

    /**
     * function asks from user supplier number and let him edit the supplier details
     */
    public void EditSupplier() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the supplier number you want to edit:");
        int num = input.nextInt();
        if (this.sup_manager.isSupplierNumberExist(num)) // the number is exist
        {
            Supplier supplier=this.sup_manager.getSupplier(num);
            int choice = 9;
            while (choice != 0) {
                System.out.println("1. Edit supplier card");
                System.out.println("2. Edit agreement");
                System.out.println("0. Quit");
                choice = input.nextInt();
                if (choice == 1) {
                    this.cardMenu.EditCard(supplier.getCard());
                    //EditCard(suppliers_list.get(num).getCard());
                } else if (choice == 2) {
                    this.agreementMenu.EditAgreement(supplier);
                }
            }
        } else
            System.out.println("This supplier number is invalid");
    }



}


