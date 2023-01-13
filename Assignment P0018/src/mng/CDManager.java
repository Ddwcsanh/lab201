package mng;

import DAO.CDImplement;
import DAO.CDdao;
import data.CD;
import java.util.ArrayList;
import java.util.List;
import tool.MyTool;

public class CDManager {

    public static void main(String[] args) {
        String[] options = {"Add CD to the catalog",
            "Search CD by CD title",
            "Display the catalog",
            "Update CD",
            "Save account to file",
            "Print list CDs from file",
            "Others- Quit"
        };
        Menu mnu = new Menu(options);
        int choice = 0;
        String response, input;
        CD cd;
        boolean changed = false;
        CDdao cdDao = new CDImplement();
        do {
            int choice2;
            boolean quit = true;
            choice = mnu.getChoice("    ---CD MANAGER---");
            switch (choice) {
                case 1:
                    cdDao.addCD();
                    changed = true;
                    break;
                case 2:
                    System.out.print("Enter title of the CD: ");
                    input = MyTool.sc.nextLine().trim().toUpperCase();
                    List<CD> list = new ArrayList<>();
                    list = cdDao.getCDByTitle(input);
                    if (list.isEmpty()) {
                        System.out.println(" No CD Found!");
                    } else {
                        for (CD cd1 : list) {
                            System.out.println(cd1.getCDId() + ", "
                                    + cd1.getCDTitle() + ", " + cd1.getUnitPrice() + ", "
                                    + cd1.getCDType() + ", " + cd1.getCDCollection() + ", "
                                    + cd1.getPulishingYear());
                        }
                    }
                    break;
                case 3:
                    cdDao.getNewCD();
                    break;
                case 4:
                    do {
                        mnu.list.clear();
                        mnu.list.add("Update CD");
                        mnu.list.add("Delete CD");
                        choice2 = mnu.getChoice2("  Update CD");
                        switch (choice2) {
                            case 1:
                                System.out.print("Enter ID of CD: ");
                                input = MyTool.sc.nextLine().trim().toUpperCase();
                                try {
                                    cd = cdDao.searchCDByID(input);
                                } catch (Exception e) {
                                    cd = null;
                                }
                                if (cd != null) {
                                    cdDao.updateCD(cd);
                                    changed = true;
                                } else {
                                    System.out.println("No CD Found!");
                                }
                                System.out.print("Continue Y/N? ");
                                response = MyTool.sc.nextLine().toUpperCase();
                                if (response.startsWith("Y")) {
                                    quit = false;
                                    System.out.println("");
                                    break;
                                }
                                break;
                            case 2:
                                System.out.print("Enter ID of CD: ");
                                input = MyTool.sc.nextLine().trim().toUpperCase();
                                try {
                                    cd = cdDao.searchCDByID(input);
                                } catch (Exception e) {
                                    cd = null;
                                }
                                if (cd != null) {
                                    cdDao.deleteCD(cd);
                                    changed = true;
                                } else {
                                    System.out.println("No CD Found!");
                                }
                                System.out.print("Continue Y/N? ");
                                response = MyTool.sc.nextLine().toUpperCase();
                                if (response.startsWith("Y")) {
                                    quit = false;
                                    System.out.println("");
                                    break;
                                }
                                break;
                            default:
                                quit = true;
                        }
                    } while (!quit);
                    break;
                case 5:
                    if (changed) {
                        cdDao.saveFile();
                        changed = false;
                    } else {
                        System.out.println("Nothing to saved!");
                    }
                    break;
                case 6:
                    for (CD cd1 : cdDao.getAllCD()) {
                        if (cd1 != null) {
                            cd1.print();
                        }
                    }
                    break;
                default:
                    if (changed) {
                        System.out.print("Save changes Y/N? ");
                        response = MyTool.sc.nextLine().toUpperCase();
                        if (response.startsWith("Y")) {
                            cdDao.saveFile();
                        }
                    }
            }
            System.out.println("");
        } while (choice > 0 && choice < 7);
    }
}
