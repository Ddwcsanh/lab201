package DAO;

import data.Product;
import java.util.ArrayList;
import java.util.List;
import mng.ProductManager;
import tool.MyTool;

public class ProductImplement implements ProductDAO {

    private final List<Product> prod;
    private final List<Product> prod1;
    private boolean changed = false;
    Product product;

    public ProductImplement() {
        prod = new ArrayList<>();
        prod1 = MyTool.readLinesFromFile(MyTool.PRODUCT_FILE);
        this.changed = changed;
    }

    @Override
    public List<Product> getNewProduct() {
//        if (prod.isEmpty()) {
//            System.out.println("Empty list!");
//        }
        return prod;
    }

    @Override
    public List<Product> getFileProduct() {
//        prod1 = MyTool.readLinesFromFile(MyTool.PRODUCT_FILE);
//        if (prod1.isEmpty()) {
//            System.out.println("Empty file!");
//        }
        return prod1;
    }

    @Override
    public Product searchProductByID(String ID) {
        //search from list
        for (Product p : prod) {
            if (p.getProductID().equalsIgnoreCase(ID)) {
                return p;
            }
        }
        //search from file
//        prod1 = MyTool.readLinesFromFile(MyTool.PRODUCT_FILE);
        for (Product p : prod1) {
            if (p.getProductID().equalsIgnoreCase(ID)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean searchProductByName(String productName) {
        //search from list
        for (Product p : prod) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        //search from file
//        prod1 = MyTool.readLinesFromFile(MyTool.PRODUCT_FILE);
        for (Product p : prod1) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product getProductByID(String ID) {
        //get from list
        for (Product p : prod) {
            if (p.getProductID().equalsIgnoreCase(ID)) {
                return p;
            }
        }
        //or get from file
//        prod1 = MyTool.readLinesFromFile(MyTool.PRODUCT_FILE);
        for (Product p : prod1) {
            if (p.getProductID().equalsIgnoreCase(ID)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> getProductByName(String productName) {
        List<Product> list = new ArrayList<>();
        //get from list
        for (Product p : prod) {
            if (p.getProductName().contains(productName)) {
                list.add(p);
            }
        }
        //get from file
//        prod1 = MyTool.readLinesFromFile(MyTool.PRODUCT_FILE);
        for (Product p : prod1) {
            if (p.getProductName().contains(productName.toUpperCase())) {
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public void createProduct() {
        String productID;
        String productName;
        double unitPrice;
        int quantity;
        String status;

        do {
            productID = MyTool.readPattern("Enter new product ID (P---)", Product.ID_PARTTERN).toUpperCase();
            product = searchProductByID(productID);
            if (product != null) {
                System.out.println("ID is duplicated!");
            }
        } while (product != null);

        do {
            productName = MyTool.readPattern("New product name (at least 5 characters)", Product.NAME_PARTTERN).toUpperCase();
            if (searchProductByName(productName)) {
                System.out.println("Product name is duplicated!");
            }
        } while (searchProductByName(productName));

        unitPrice = MyTool.readDouble("New unit price (0 - 10,000)");
        quantity = MyTool.readInt("New quantity (0 - 1,000)");
        boolean status1 = MyTool.readBool("Is available? ");
        if (status1) {
            status = "Available";
        } else {
            status = "Not available";
        }

        prod.add(new Product(productID, productName, unitPrice, quantity, status));
        System.out.println("New product has been created!");
        changed = true;
    }

    @Override
    public void updateProduct(Product p) {
        String newName;
        double newPrice;
        int newQuantity;
        boolean newStatus;

        System.out.print("New name, ENTER for omitting: ");
        newName = MyTool.sc.nextLine().trim().toUpperCase();
        if (!newName.isEmpty() && searchProductByName(newName) == false) {
            p.setProductName(newName);
            changed = true;
        }

        newPrice = MyTool.readDoublePattern("New unit price, ENTER for omitting", Product.DOUBLE_INPUT);
        if (newPrice >= 0) {
            p.setUnitPrice(newPrice);
            changed = true;
        }

//            System.out.print("New unit price, ENTER for omitting: ");
        newQuantity = MyTool.readIntPattern("New quantity, ENTER for omitting", Product.INTEGER_INPUT);
        if (newQuantity >= 0) {
            p.setQuantity(newQuantity);
            changed = true;
        }

        System.out.print("Is available? [1/0 - Y/N - T/F], ENTER for omitting: ");
        String newStatus1 = MyTool.sc.nextLine();
        if (!newStatus1.isEmpty()) {
            if (newStatus = MyTool.parseBool(newStatus1)) {
                p.setStatus("Available");
            } else {
                p.setStatus("Not available");
            }
            changed = true;
        }
        System.out.println("\nProduct [" + p.getProductID() + "] has been updated.");
    }

    @Override
    public void deleteProduct(Product p) {
        prod.remove(p);
        prod1.remove(p);
        System.out.println(p.getProductName() + " has been deleted!");
        changed = true;
    }

    @Override
    public void saveFile() {
        MyTool.writeFile(prod1);
        MyTool.saveToFile(prod);
        System.out.println("Products have been saved to the file.");
        changed = false;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
