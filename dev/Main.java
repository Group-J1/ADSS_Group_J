import LoginRegister.Presentation.LoginMenuNew;
import LoginRegister.Presentation.StoreManagerMenu;

public class Main {
    public static void main(String[] args) {
        //StoreManagerMenu storeManagerMenu = StoreManagerMenu.getLoginMenu();
        //storeManagerMenu.begin();
        LoginMenuNew loginMenuNew = LoginMenuNew.getInstance();
        loginMenuNew.begin();
    }
}