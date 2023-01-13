package DAO;

import data.CD;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tool.MyTool;

public class CDImplement implements CDdao {

    private List<CD> list;
    private CD[] cd;
    private int numOfCD;
    private final int MAX = 3;
    String filename = "CD.dat";

    public CDImplement() {
        list = new ArrayList<>();
        cd = new CD[MAX];
        numOfCD = 0;
    }

    @Override
    public void getNewCD() {
        if (numOfCD == 0) {
            System.out.println("Empty List.");
        } else {
            for (int i = 0; i < numOfCD; i++) {
                cd[i].print();
            }
        }
    }

    @Override
    public List<CD> getAllCD() {
        list = MyTool.loadFromFile(filename, list);
        if (list.isEmpty()) {
            System.out.println("Empty File!");
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public CD searchCDByID(String code) {
        for (CD tmp : cd) {
            if (tmp.getCDId().equalsIgnoreCase(code)) {
                return tmp;
            }
        }
        return null;
    }

    @Override
    public boolean searchCDByTitle(String name) {
        for (CD tmp : list) {
            if (tmp.getCDTitle().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CD getCDByID(String code) {
        list = MyTool.loadFromFile(filename, list);
        for (CD tmp : list) {
            if (tmp.getCDId().equalsIgnoreCase(code)) {
                return tmp;
            }
        }
        return null;
    }

    @Override
    public List<CD> getCDByTitle(String name) {
        list = MyTool.loadFromFile(filename, list);
        if (list.isEmpty()) {
            System.out.print("File is empty!");
        }
        List<CD> list = new ArrayList<>();
        for (CD tmp : this.list) {
            if (tmp != null && tmp.getCDTitle().contains(name)) {
                list.add(tmp);
            }
        }
        return list;
    }

    @Override
    public void addCD() {
        String id, title, type, collection;
        double unitPrice;
        int year;
        CD check;

        if (numOfCD >= MAX) {
            System.out.println("List id full, cannot add more CD!");
        } else {
            do {
                id = MyTool.readPattern(" - CD id (C---)", CD.ID_PARTTERN).toUpperCase();
                check = getCDByID(id);
                if (check != null) {
                    System.out.println("   ID is duplicated!");
                }
            } while (check != null);
            do {
                System.out.print(" - CD title: ");
                title = MyTool.sc.nextLine().trim().toUpperCase();
            } while (title.isEmpty());
            unitPrice = MyTool.readDouble(" - CD unit price");
            type = MyTool.readPattern(" - Type (audio/video)", "(audio)|(video)");
            collection = MyTool.readPattern(" - Collection (game/movie/music)", "(game)|(movie)|(music)");
            year = MyTool.readInt(" - Publishing year");

            cd[numOfCD] = new CD(id, title, unitPrice, type, collection, year);
            numOfCD++;
            System.out.println("     New item has been added!");
        }
    }

    @Override
    public void updateCD(CD cd) {
        String newTitle, newType, newCollection;
        double newUnitPrice;
        int newYear;

        System.out.print(" - New title, ENTER for omitting: ");
        newTitle = MyTool.sc.nextLine().toUpperCase();
        if (!newTitle.isEmpty()) {
            cd.setCDTitle(newTitle);
        }

        newUnitPrice = MyTool.readDoublePattern(" - New unit price, ENTER for omitting", "([0-9]+\\.?[0-9]*)|");
        if (newUnitPrice >= 0) {
            cd.setUnitPrice(newUnitPrice);
        }

        newType = MyTool.readPattern(" - New type (audio/video), ENTER for omitting", "(audio)|(video)|");
        if (!newType.isEmpty()) {
            cd.setCDType(newType);
        }

        newCollection = MyTool.readPattern(" - New collection (game/movie/music), ENTER for omitting", "(game)|(movie)|(music)|");
        if (!newCollection.isEmpty()) {
            cd.setCDCollection(newCollection);
        }

        newYear = MyTool.readIntPattern(" - New publishing year, ENTER for omitting", "[0-9]+|");
        if (newYear >= 0) {
            cd.setPulishingYear(newYear);
        }
        System.out.println("   The " + cd.getCDId() + " is updated!");
        System.out.println("");
    }

    public int searchByCode(CD cd) {
        for (int i = 0; i < numOfCD; i++) {
            if (this.cd[i] == cd) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void deleteCD(CD cd) {
        int pos = searchByCode(cd);
        if (pos >= 0 && pos < numOfCD) {
            for (int i = pos; i < (numOfCD - 1); i++) {
                this.cd[i] = this.cd[i + 1];
            }
            numOfCD--;
            System.out.println("   The item is deleted!");
            System.out.println("");
        }
    }

    @Override
    public void saveFile() {
        MyTool.saveToFile(cd, numOfCD);
        System.out.println("New item(s) has been saved to the file!");
    }

}
