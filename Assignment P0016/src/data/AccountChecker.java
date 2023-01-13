package data;

import java.io.IOException;
import java.util.List;
import tools.MyTool;

public class AccountChecker {

    private String accFile;
    private static String SEPERATOR = ",";

    public AccountChecker() throws IOException {
        setupAccFile();
    }

    private void setupAccFile() throws IOException {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }

    public boolean check(Account acc) throws IOException {
        List<String> lines = MyTool.readLinesFromFile(accFile);
        for (String line : lines) {
            String[] parts = line.split(this.SEPERATOR);
            if (parts.length < 3) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(acc.getAccName())
                    && parts[1].equalsIgnoreCase(acc.getPasswd())
                    && parts[2].equalsIgnoreCase(acc.getRole())) {
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] args) throws IOException {
//        AccountChecker aChk = new AccountChecker();
//        Account acc = new Account("E001", "12345678", "BOSS");
//        boolean valid = aChk.check(acc);
//        System.out.println("Needs OK, OK?: " + valid);
//        acc = new Account("E002", "23456789", "ACC-1");
//        valid = aChk.check(acc);
//        System.out.println("Needs OK, OK?: " + valid);
//        acc = new Account("E003", "34567890", "ACC-2");
//        valid = aChk.check(acc);
//        System.out.println(valid); 
//    }
}
