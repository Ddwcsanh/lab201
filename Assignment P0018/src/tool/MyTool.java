package tool;

import data.CD;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTool {

    public static final String filename = "CD.dat";
    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.trim().matches(regEx);
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
    
    public static double readDouble(String message) {
        double input = 0;
        do {
            try {
                System.out.print(message + ": ");
                input = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("     Invalid input!");
                input = -1;
            }
        } while (input < 0);
        return (Math.round(input * 100.0) / 100.0);
    }
    
    public static int readInt(String message) {
        int input = 0;
        do {
            try {
                System.out.print(message + ": ");
                input = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("     Invalid input!");
                input = -1;
            }
        } while (input < 0 || input > 2022);
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
                    return d;
                } else {
                    d = Double.parseDouble(input);
                }
            }
        } while (d < 0);
        return (Math.round(d * 100.0) / 100.0);
    }

    public static int readIntPattern(String message, String pattern) {
        String input;
        int i = -1;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            if (!validStr(input, pattern)) {
                System.out.println("Invalid input!");
            } else {
                if (input.isEmpty()) {
                    return i;
                } else {
                    i = Integer.parseInt(input);
                }
            }
        } while (i < 0 || i > 2022);
        return i;
    }

    public static List<CD> loadFromFile(String fName, List<CD> list) {
        if (list.size() > 0) {
            list.clear();
        }
        FileInputStream fi = null;
        ObjectInputStream fo = null;
        try {
            fi = new FileInputStream(fName);
            fo = new ObjectInputStream(fi);
            CD obj;
            while (true) {
                obj = (CD) (fo.readObject());
                list.add(obj);
            }
        } catch (Exception e) {
//            System.out.println(e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<String>();
        try {
            FileInputStream fi = new FileInputStream(filename); // read()
            ObjectInputStream fo = new ObjectInputStream(fi); // read Object()
            String b;
            while ((b = (String) (fo.readObject())) != null) {
                list.add(b);
            }
            fo.close();
            fi.close();
        } catch (EOFException e) {
            //end of file reached, do nothing
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //check if the file has the object saved in or not
    public static boolean hasObject(String filePath) {
        FileInputStream fi;
        boolean check = true;
        try {
            fi = new FileInputStream(filePath);
            ObjectInputStream fo = new ObjectInputStream(fi);
            if (fo.readObject() == null) {
                check = false;
            }
            fo.close();
        } catch (FileNotFoundException e) {
            check = false;
        } catch (IOException e) {
            check = false;
        } catch (ClassNotFoundException e) {
            check = false;
            e.printStackTrace();
        }
        return check;
    }

    public static void saveToFile(CD[] list, int numOfItem) {
        FileOutputStream fi = null;
        ObjectOutputStream fo = null;
        try {
            if (!hasObject(filename)) {
                fi = new FileOutputStream(filename);
                fo = new ObjectOutputStream(fi);
            } else {
                fi = new FileOutputStream(filename, true);
                fo = new ObjectOutputStream(fi) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }
            for (int i = 0; i < numOfItem; i++) {
                fo.writeObject(list[i]);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeFile(String filename, List list) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f); // write()
            PrintWriter pw = new PrintWriter(fw); // println()
            for (Object object : list) {
                pw.print(object);
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
