package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTools {

    public static final String PRODUCT_FILE = "ProductData/Product.txt";

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.trim().matches(regEx);
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static double readDouble(String message) {
        double input = 0;
        do {
            try {
                System.out.print(message + ": ");
                input = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                input = -1;
            }
        } while (input < 0 || input > 10000);
        return (Math.round(input * 100.0) / 100.0);
    }

    public static int readInt(String message) {
        int input = 0;
        do {
            try {
                System.out.print(message + ": ");
                input = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                input = -1;
            }
        } while (input < 0 || input > 1000);
        return input;
    }

    public static double readDoublePattern(String message, String pattern) {
        String input;
        double d = -1;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine();
            if (!validStr(input, pattern)) {
                System.out.println("Invalid input!");
            } else {
                if (input.isEmpty()) {
                    return -1;
                } else {
                    d = Double.parseDouble(input);
                }
            }
        } while (d < 0 || d > 10000);
        return (Math.round(d * 100.0) / 100.0);
    }

    public static int readIntPattern(String message, String pattern) {
        String input;
        int i = -1;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine();
            if (!validStr(input, pattern)) {
                System.out.println("Invalid input!");
            } else {
                if (!input.isEmpty()) {
                    return i = Integer.parseInt(input);
                }
                else return i;
            }
        } while (i < 0 || i > 1000);
        return i;
    }

    public static boolean parseBool(String boolStr) {
        if (!boolStr.isEmpty()) {
            char c = boolStr.trim().toUpperCase().charAt(0);
            return (c == '1' || c == 'Y' || c == 'T');
        } else {
            return false;
        }
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0 - Y/N - T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename) throws IOException {
        ArrayList<String> list = new ArrayList();
        File f = new File(filename);
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        if (!f.exists()) {
            System.out.println("File not exist!");
            return null;
        }
        String s;
        while ((s = br.readLine()) != null) {
            if (!s.isEmpty()) {
                list.add(s);
            }
        }
        br.close();
        fr.close();
        return list;
    }

    public static void writeFile(String filename, List list) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < list.size(); i++) {
            String s = "";
            s += list.get(i) + "\n";
            bw.write(s);
        }
        bw.close();
        fw.close();
    }

}
