package mng;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }

    public int getChoice(String title) {
        int response;
        int n = this.size();
        System.out.println("\n" + title);
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " - " + this.get(i));
        }
        try {
            System.out.print("Please choose an option 1..." + this.size() + ": ");
            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());
            System.out.println("");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input!");
            response = 0;
        }
        return response;
    }

}
