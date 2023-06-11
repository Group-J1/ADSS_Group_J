import Stock.Presentation.StockMainUI;

//public class Main {
//    public static void main(String[] args) {
//        StockMainUI stockMainUi = new StockMainUI();
//        stockMainUi.startMenu();
//    }
//

//}

public class Main {
    public static void main(String[] args) {
        LoginMenu loginMenu= LoginMenu.getLoginMenu();
        loginMenu.begin();
    }
}