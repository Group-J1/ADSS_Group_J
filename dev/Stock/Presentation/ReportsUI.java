package Stock.Presentation;

import Stock.Business.ProductManager;
import Stock.DataAccess.ProductDAO;
import Stock.Service.ReportsService;

import java.util.Scanner;

public class ReportsUI {
    private static ReportsService reportsService = ReportsService.getInstance();
    Scanner input = new Scanner(System.in);



    public void startMenu(String numOfMarketToManagement) {
        boolean running = true;
        while (running) {
            System.out.println("-------- Welcome to the Reports menu of market number " + Integer.parseInt(numOfMarketToManagement) + " --------");
            System.out.println("1) Create stock report. ");
            System.out.println("2) Create order report. ");
            System.out.println("3) Create damaged products report. ");
            System.out.println("4) Create shortages report. ");
            System.out.println("5) Go back to Stock menu ");
            System.out.println("6) next day ");

            System.out.println("Select the number you would like to access ");
            System.out.println("-----------------------");
            String selection = input.nextLine();
            switch (selection) {
                case "1":
                    createStockReportCase1();
                    break;

                case "2":
                    createOrderReportCase2();
                    break;

                case "3":
                    createDamagedReportCase3();
                    break;

                case "4":
                    createShortageReportCase4();
                    break;
                case "5":
                    running = false;
                    break;
                case "6":
                    ProductDAO.getInstance().updateForNextDay();
                    break;


                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
    private void createStockReportCase1(){
        if (!reportsService.createStockReport()) {
            System.out.println("-------- Error in creation of stock report --------");
        }
    }

    private void createOrderReportCase2(){
        if (!reportsService.createOrderReport()) {
            System.out.println("-------- Error in creation of order report --------");
        }
    }

    private void createDamagedReportCase3(){
        if (!reportsService.createDamagedReport()) {
            System.out.println("-------- Error in creation of damaged report --------");
        }
    }

    private void createShortageReportCase4(){
        if (!reportsService.createShortageReport()) {
            System.out.println("-------- Error in creation of shortage report --------");
        }
    }




}
