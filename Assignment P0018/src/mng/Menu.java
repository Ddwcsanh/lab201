package mng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tool.MyTool;

public class Menu extends ArrayList<String> {

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
        System.out.print("Please choose an option 1..." + this.size() + ": ");
        try {
            response = Integer.parseInt(MyTool.sc.nextLine());
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
        System.out.print("Choose 1 - 2: ");
        try {
            response = Integer.parseInt(MyTool.sc.nextLine());
            System.out.println("");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input!");
            response = 0;
        }
        return response;
    }

//    public static void main(String[] args) {
//        String r;
//        r = MyTool.sc.nextLine();
//        System.out.println(MyTool.validStr(r, "(4\\.1)|(4\\.2)"));
//    }
}
