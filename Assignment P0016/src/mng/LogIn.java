package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import java.io.IOException;
import tools.MyTool;

public class LogIn {

    private Account acc = null;

    public LogIn() {
    }

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String accName = MyTool.readPattern("Enter account name", "E\\d{3}|e\\d{3}");
        String passwd = MyTool.readPattern("Enter password", ".*[\\d]+.*");
        String accRole = MyTool.readNonBlank("Enter role");
        Account acc = new Account(accName, passwd, accRole);
        return acc;
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Account acc = null;
        boolean cont;
        boolean valid = false;
        do {
            cont = false;
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account - Try again? ");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
//        System.out.print("Login");
//        for (int i = 0; i < 3; i++) {
//            Thread.sleep(800);
//            System.out.print(".");
//        }
//        Thread.sleep(500);
//        System.out.println("");

        LogIn loginObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer", "Search a dealer",
                "Remove a dealer", "Update a dealer",
                "Print all dealers", "Print continuing dealers",
                "Print UN-continuing dealers", "Write to file", "Exit"
            };
            Menu menu = new Menu(options);
            DealerList dList = new DealerList(loginObj);
            dList.initWithFile();
            int choice = 0;
            do {
                choice = menu.getChoice("\t---Managing dealers---");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file? ");
                            if (res) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < menu.size());
            System.out.println("Bye.");
        }

    }
}
