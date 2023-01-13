package data;

import DAO.ProductDAO;
import DAO.ProductImplement;
import java.util.ArrayList;
import java.util.List;
import tool.MyTool;

public class ProductController {

    ProductImplement prodDAO = new ProductImplement();
    Product prodct;

    public void printTheList() {
        List<Product> result = new ArrayList();
        result.addAll(prodDAO.getNewProduct());
        if (result.isEmpty()) {
            System.out.println("Empty list!");
            System.out.println("Create a new product and try again!");
        } else {
            System.out.println("-----------------------------------------");
            System.out.println("\t      Product List");
            System.out.println("-----------------------------------------");
            result.sort((Product o1, Product o2) -> {
                if ((o2.getQuantity() - o1.getQuantity()) != 0) {
                    return (o2.getQuantity() - o1.getQuantity());
                } else {
                    return (int) (o1.getUnitPrice() - o2.getUnitPrice());
                }
            });
            for (Product product : result) {
                System.out.println(product);
            }
            System.out.println("Total: " + result.size() + " product(s).");
            System.out.println("-----------------------------------------");
        }
//        if (prodDAO.getNewProduct().isEmpty()) {
//            System.out.println("Empty list!");
//            System.out.println("Create a product and try again.");
//        }
//        for (Product product : prodDAO.getNewProduct()) {
//            System.out.println(product);
//        }
    }

    public void createProduct() {
        prodDAO.createProduct();
    }

    public void checkProduct() {
        String cID = MyTool.readPattern("Enter product ID for checking", Product.ID_PARTTERN).toUpperCase();
        prodct = prodDAO.getProductByID(cID);
        if (prodct != null) {
            System.out.println("Exist product!");
        } else {
            System.out.println("No Product found!");
        }
    }

    public void searchByName() {
        String pName = MyTool.readNonBlank("Enter product name for searching").toUpperCase();
        List<Product> list = new ArrayList<>();
        list = prodDAO.getProductByName(pName);
        if (list != null) {
            list.sort((Product o1, Product o2) -> {
                return o1.getProductName().compareTo(o2.getProductName());
            });
            for (Product product : list) {
                System.out.println(product);
            }
        } else {
            System.out.println("Have no any Product!");
        }
    }

    public void updateProduct() {
        String uID = MyTool.readPattern("Enter product ID for updating", Product.ID_PARTTERN);
        prodct = prodDAO.getProductByID(uID);
        if (prodct != null) {
            prodDAO.updateProduct(prodct);
            System.out.println("After updating:\n" + prodct);
        } else {
            System.out.println("Product does not exist!");
        }
    }

    public void deleteProduct() {
        String uID = MyTool.readPattern("Enter product ID for deleting", Product.ID_PARTTERN);
        prodct = prodDAO.getProductByID(uID);
        if (prodct != null) {
            prodDAO.deleteProduct(prodct);
        } else {
            System.out.println("Product does not exist!");
        }
    }

    public void saveFile() {
        prodDAO.saveFile();
    }

    public void printFromFile() {
        List<Product> result = new ArrayList();
//        for (Product product : prodDAO.getNewProduct()) {
//            result.add(prodct);
//        }
//        result.addAll(prodDAO.getNewProduct());
        result.addAll(prodDAO.getFileProduct());
        if (result.isEmpty()) {
            System.out.println("Empty list!");
            System.out.println("Create a new product and try again!");
        } else {
            System.out.println("-----------------------------------------");
            System.out.println("\t     List From File");
            System.out.println("-----------------------------------------");
            result.sort((Product o1, Product o2) -> {
                if ((o2.getQuantity() - o1.getQuantity()) != 0) {
                    return (o2.getQuantity() - o1.getQuantity());
                } else {
                    return (int) (o1.getUnitPrice() - o2.getUnitPrice());
                }
            });
            for (Product product : result) {
                System.out.println(product);
            }
            System.out.println("Total: " + result.size() + " product(s).");
            System.out.println("-----------------------------------------");
        }
    }

    public boolean isChanged() {
        return prodDAO.isChanged();
    }
}
