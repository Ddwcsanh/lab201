package mng;

import data.Product;
import data.ProductList;
import tools.MyTools;

public class ProductManagement {

    public static void main(String[] args) {
        MyTools.readIntPattern("Input", Product.INTEGER_INPUT);
        String[] options = {"Print", "Create a Product", "Check exits Product",
            "Search Product information by Name", "Update Product",
            "Save Products to file", "Print list Products from the file", "Quit."};
        Menu menu = new Menu(options);
        ProductList pList = new ProductList();
        
        int choice1 = 0;
        do {
            int choice2 = 0;
            choice1 = menu.getChoice("\t---PRODUCT LIST---");
            switch (choice1) {
                case 1:
                    
                    break;
                default:
                    throw new AssertionError();
            }
        } while (choice1 > 0 && choice1 < 8);
        System.out.println("Bye");
        
    }
}
