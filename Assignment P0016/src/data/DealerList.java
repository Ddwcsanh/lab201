package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import mng.LogIn;
import tools.MyTool;

public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    private boolean changed = false;

    public DealerList(LogIn loginObj) {
        this.dataFile = dataFile;
        this.changed = changed;
    }

    private void loadDealerFromFile() throws IOException {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for (String line : lines) {
            try {
//                String s = line;
                StringTokenizer stk = new StringTokenizer(line, ",");
                while (stk.hasMoreTokens()) {
                    String id = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    String addr = stk.nextToken().trim();
                    String phone = stk.nextToken().trim();
                    String continuing = stk.nextToken().trim();
                    boolean newContinuing = continuing.equalsIgnoreCase("true");
                    this.add(new Dealer(id, name, addr, phone, newContinuing));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void initWithFile() throws IOException {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList result = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing()) {
                result.add(d);
            }
        }
        return result;
    }

    public DealerList getUnContinuingList() {
        DealerList result = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == false) {
                result.add(d);
            }
        }
        return result;
    }

    public int searchDealer(String ID) {
        String sID = ID.toUpperCase();
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (sID.equals(this.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        String sID = MyTool.readPattern("Enter ID for searching", Dealer.ID_FORMAT);
        int pos = searchDealer(sID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            System.out.println(this.get(pos));
        }
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readPattern("ID of new dealer D---", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer");
        phone = MyTool.readPattern("Phone number", Dealer.PHONE_FORMAT);
        continuing = true;
        this.add(new Dealer(ID, name, addr, phone, continuing));
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public void removeDealer() {
        String rID = MyTool.readPattern("Enter ID for removing D---", Dealer.ID_FORMAT);
        int pos = searchDealer(rID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            this.get(pos).setContinuing(false);
            this.remove(pos);
            System.out.println("Dealer has been removed.");
            changed = true;
        }
    }

    public void updateDealer() {
        String uID = MyTool.readPattern("Enter ID for updating D---", Dealer.ID_FORMAT);
        int pos = searchDealer(uID);
        if (pos < 0) {
            System.out.println("Dealer " + uID + " not found!");
        } else {
            Dealer d = this.get(pos);
            System.out.println(d + "\n");
//            String newName = MyTool.readNonBlank("New name, ENTER for omitting").toUpperCase();
            System.out.print("New name, ENTER for omitting: ");
            String newName = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }

//            String newAddr = MyTool.readNonBlank("New address, ENTER for omitting");
            System.out.print("New address, ENTER for omitting: ");
            String newAddr = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }

            boolean cont;
            do {
                cont = false;
                System.out.print("New phone number, ENTER for omitting: ");
                String newPhone = MyTool.sc.nextLine().trim();
                if (!newPhone.isEmpty()) {
                    if (newPhone.matches(PHONEPATERN)) {
                        d.setPhone(newPhone);
                        changed = true;
                    } else {
                        System.out.println("Wrong format!");
                        cont = true;
                    }
                }
            } while (cont);

            System.out.print("Dealer continuing [1/0 - Y/N - T/F], ENTER for omitting: ");
            String newContinuing = MyTool.sc.nextLine();
            boolean newContinuing1 = MyTool.parseBool(newContinuing);
            if (!newContinuing.isEmpty()) {
                d.setContinuing(newContinuing1);;
                changed = true;
            }
//            d.setContinuing(newContinuing);
            System.out.println("\nDealer " + d.getID() + " has been updated.");
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
            System.out.println("Try again!");
        } else {
            System.out.println("\tDealers List");
            for (Dealer dealer : this) {
                System.out.println(dealer);
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealerToFile() throws IOException {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            System.out.println("Data has been written to Dealer.txt");
            changed = false;
        } else {
            System.out.println("Data remained unchanged!");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
