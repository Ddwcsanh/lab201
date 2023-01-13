package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import tools.MyTools;

public class ProductList extends ArrayList<Product> {

    List<Product> product;
    String dataFile = MyTools.PRODUCT_FILE;
    private boolean changed = false;

    public ProductList() {
    }

    private List<Product> loadProductFromFile() throws IOException{
        List<String> lines = MyTools.readLinesFromFile(dataFile);
        List<Product> result = null;
        for (String line : lines) {
            try {
//                String s = line;
                StringTokenizer stk = new StringTokenizer(line, ",");
                while (stk.hasMoreTokens()) {
                    String productID = stk.nextToken().trim();
                    String productName = stk.nextToken().trim();
                    String unitPrice = stk.nextToken().trim();
                    int newUnitPrice = Integer.parseInt(unitPrice);
                    String quantity = stk.nextToken().trim();
                    int newQuantity = Integer.parseInt(quantity);
                    String status = stk.nextToken().trim();
                    result.add(new Product(productID, productName, newUnitPrice, newQuantity, status));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return result;
    }

    public int searchProduct(String ID) throws IOException {
        for (int i = 0; i < product.size(); i++) {
            if (ID.equalsIgnoreCase(product.get(i).getProductID())) {
                return i;
            }
        }
        product = loadProductFromFile();
        for (int i = 0; i < product.size(); i++) {
            if (ID.equalsIgnoreCase(product.get(i).getProductID())) {
                return i;
            }
        }
        return -1;
    }

    //sear
    public void searchProduct() throws IOException {
        String sID = MyTools.readPattern("Enter ID for searching", Product.ID_PARTTERN);
        int pos = searchProduct(sID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            System.out.println(product.get(pos));
        }
    }

    public int searchProductName(String pName) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (pName.equalsIgnoreCase(this.get(i).getProductName())) {
                return i;
            }
        }
        return -1;
    }

    public void searchProductName() {
        String sName = MyTools.readPattern("Enter name of product for checking", Product.NAME_PARTTERN);
        int pos = searchProductName(sName);
        if (pos < 0) {
            System.out.println("No Product Found!");
        } else {
            System.out.println("Product Existed!");
        }
    }

    public void searchProductByName() {
        String sName = MyTools.readNonBlank("Enter name of product for searching");
        ProductList result = new ProductList();
        for (Product thi : this) {
            if (thi.getProductName().contains(sName)) {
                result.add(thi);
            }
        }
        if (!result.isEmpty()) {
            Collections.sort(result, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getProductName().compareTo(o2.getProductName()) != 0) {
                        return o1.getProductName().compareTo(o2.getProductName());
                    } else {
                        return (o1.getProductID().compareTo(o2.getProductID()));
                    }
                }
            }
            );
            for (Product product : result) {
                System.out.println(product);
            }
        } else {
            System.out.println("Have no any Product");
        }
    }

    public void creatProduct() {
        String productID;
        String productName;
        double unitPrice;
        int quantity;
        String status;

        int pos;
        do {
            productID = MyTools.readPattern("Enter new product ID", Product.ID_PARTTERN);
            productID = productID.toUpperCase();
            pos = searchProduct(productID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos < 0);

        do {
            productName = MyTools.readPattern("New product name", Product.NAME_PARTTERN);
            pos = searchProductName(productName);
            if (pos >= 0) {
                System.out.println("Product name is duplicated!");
            }
        } while (pos < 0);

        unitPrice = MyTools.readDouble("New unit price (0 - 10,000)");
        quantity = MyTools.readInt("New quantity (0 - 1,000)");
        boolean status1 = MyTools.readBool("Is available? ");
        if (status1) {
            status = "Available";
        } else {
            status = "Not Available";
        }

        this.add(new Product(productID, productName, unitPrice, quantity, status));
        System.out.println("New product has been created!");
        changed = true;
    }

    public void updateProduct() {
        String uID;
        String newName;
        double newPrice = 0;
        int newQuantity;
        String newStatus;

        uID = MyTools.readPattern("Enter Product ID for updating", Product.ID_PARTTERN);
        int pos = searchProduct(uID);
        if (pos < 0) {
            System.out.println("Product " + uID + " not found!");
        } else {
            Product p = this.get(pos);
            System.out.println(p + "\n");
            System.out.print("New name, ENTER for omitting: ");
            newName = MyTools.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                p.setProductName(newName);
                changed = true;
            }

            newPrice = MyTools.readDoublePattern("New unit price, ENTER for omitting", Product.DOUBLE_INPUT);
            if (newPrice >= 0) {
                p.setUnitPrice(newPrice);
                changed = true;
            }

            System.out.print("New unit price, ENTER for omitting: ");
            newQuantity = MyTools.readIntPattern("New unit price, ENTER for omitting", Product.INTEGER_INPUT);
            if (newQuantity >= 0) {
                p.setQuantity(newQuantity);
                changed = true;
            }

            System.out.print("Is available? [1/0 - Y/N - T/F], ENTER for omitting: ");
            newStatus = MyTools.sc.nextLine();
            boolean newStatus1 = MyTools.parseBool(newStatus);
            if (!newStatus.isEmpty()) {
                if (newStatus1) {
                    newStatus = "Available";
                    p.setStatus(newStatus);
                } else {
                    newStatus = "Not Available";
                }
                p.setStatus(newStatus);
                changed = true;
            }
            System.out.println("\nProduct [" + p + "] has been updated.");
        }
    }

    public void removeProduct() {
        String rID = MyTools.readPattern("Enter Product ID for removing", Product.ID_PARTTERN);
        int pos = searchProduct(rID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            this.remove(pos);
            System.out.println("Product has been removed.");
            changed = true;
        }
    }

    public void printFromList() throws IOException {
        List<Product> result;
        result = loadProductFromFile();
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            System.out.println("Create a new product and try again!");
        } else {
            Collections.sort(this, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o2.getQuantity() - o1.getQuantity() != 0) {
                        return (o2.getQuantity() - o1.getQuantity());
                    } else {
                        return (int) (o1.getUnitPrice() - o2.getUnitPrice());
                    }
                }
            });
            for (Product thi : this) {
                System.out.println("\tProduct List");
                System.out.println("-------------------------------------");
                System.out.println(thi);
            }
            System.out.println("Total: " + this.size() + "product(s).");
        }
    }

//    public static void main(String[] args) {
//        
//    }
}
