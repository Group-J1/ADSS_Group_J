import GUI.loginRegisterGui.loginRegisterGUI;
import LoginRegister.Presentation.LoginMenuNew;
import LoginRegister.Presentation.StoreManagerMenu;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String choice;

        // Display the menu options
        System.out.println("Menu:");
        System.out.println("1. Run program through GUI");
        System.out.println("2. Run program through CLI");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Call GUI function here
                try {
                    loginRegisterGUI gui = new loginRegisterGUI();
                    gui.run();
                    System.out.println("Running program through GUI...");
                    break;
                }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            case "2":
                // Call CLI function here
                LoginMenuNew loginMenuNew = LoginMenuNew.getInstance();
                loginMenuNew.begin();

                System.out.println("Running program through CLI...");
                break;
            case "0":
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        scanner.close();
    }

}