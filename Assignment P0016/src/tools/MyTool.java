package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTool {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.trim().matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d]+.*")
                && str.matches(".*[\\W]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dateToStr(Date date, String dateFormat) {
        SimpleDateFormat dateF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dateF.applyPattern(dateFormat);
        return dateF.format(date);
    }

    public static boolean parseBool(String boolStr) {
        if (!boolStr.isEmpty()) {
            char c = boolStr.trim().toUpperCase().charAt(0);
            return (c == '1' || c == 'Y' || c == 'T');
        } else {
            return false;
        }
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

    public static void main(String[] args) {
        char[] a = {'a', 'b'};
        for (char c : a) {
            System.out.println(c);
        }
    }

//    public static void main(String[] args) {
//        // Phone: 9 or 11 digits 
//        System.out.println("Tests with phone numbers:");
//        System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
//        System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
//        System.out.println(validStr("12345678", "\\d{9}|\\d11}"));
//        System.out.println("");
//        // Test password 
//        System.out.println("Test password: ");
//        System.out.println(validPassword("qwerty", 8));
//        System.out.println(validPassword("qwertyABC", 8));
//        System.out.println(validPassword("12345678", 8));
//        System.out.println(validPassword("qbc123456", 8));
//        System.out.println(validPassword("qbc@123456", 8));
//        System.out.println("");
//        // ID format D000
//        System.out.println("Tests with IDs:");
//        System.out.println(validStr("A0001", "D\\d{3}"));
//        System.out.println(validStr("10001", "D\\d{3}"));
//        System.out.println(validStr("DO001", "D\\d{3}"));
//        System.out.println(validStr("D101", "D\\d{3}"));
//        System.out.println("");
//        //Test date format
//        System.out.println("Test Date format: ");
//        Date d = parseDate("2022:12:07", "yyyy:MM:dd");
//        System.out.println(d);
//        System.out.println(dateToStr(d, "dd/MM/yyyy"));
//        d = parseDate("12/07/2022", "MM/dd/yyyy");
//        System.out.println(d);
//        d = parseDate("2022/07/12", "yyyy/dd/MM");
//        System.out.println(d);
//        d = parseDate("2000/29/02", "yyyy/dd/MM");
//        System.out.println(d);
//        d = parseDate("2000/30/02", "yyyy/dd/MM");
//        System.out.println(d);
//        d = parseDate("2000/40/16", "yyyy/dd/MM");
//        System.out.println(d);
//        // Test iput data 
//        String input = readNonBlank("Input a non-blank string");
//        System.out.println(input);
//        input = readPattern("Phone 9/11 digits", "\\d{9}|\\d{11}");
//        System.out.println(input);
//        input = readPattern("ID- format X00000", "X\\d{5}");
//        System.out.println(input);
//        boolean b = readBool("Input boolean");
//        System.out.println(b);
//    }
}
