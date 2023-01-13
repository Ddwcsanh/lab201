package mng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Menu extends Vector<String> {

    List<String> list = new ArrayList<>();

    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        this.addAll(Arrays.asList(items));
    }

    public int getChoice(String title) {
        int response;
        int n = this.size();
        System.out.println(title);
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

    public int getChoice2(String title) {
        int response;
        int n = list.size();
        System.out.println(title);
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " - " + list.get(i));
        }
        try {
            System.out.print("Choose 1..." + list.size() + ": ");
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
