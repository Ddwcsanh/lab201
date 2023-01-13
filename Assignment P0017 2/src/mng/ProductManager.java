package mng;

import DAO.ProductImplement;
import data.ProductController;
import tool.MyTool;

public class ProductManager {

    public static void main(String[] args) {
        boolean res;
        String[] options = {"Print", "Create a Product",
            "Check exits Product", "Search Product information by Name",
            "Update Product", "Save Products to file",
            "Print list Products from the file", "Quit."};
        Menu menu = new Menu(options);
        ProductController pCon = new ProductController();
        ProductImplement p = new ProductImplement();
        int choice1;
        do {
            boolean cont = true;
            int choice2;
            choice1 = menu.getChoice("\t    ---MENU---");
            switch (choice1) {
                case 1:
                    pCon.printTheList();
                    break;
                case 2:
                    pCon.createProduct();
                    break;
                case 3:
                    pCon.checkProduct();
                    break;
                case 4:
                    pCon.searchByName();
                    break;
                case 5:
                    do {
                        menu.list.clear();
                        menu.list.add("Update a product");
                        menu.list.add("Delete a product");
                        choice2 = menu.getChoice2("    -Update product-");
                        switch (choice2) {
                            case 1:
                                pCon.updateProduct();
                                if (!(res = MyTool.readBool("Continue?"))) {
                                    cont = false;
                                }
                                break;
                            case 2:
                                pCon.deleteProduct();
                                if (!(res = MyTool.readBool("Continue?"))) {
                                    cont = false;
                                }
                                break;
                            default:
                                cont = false;
                        }
                        System.out.println("");
                    } while (cont);
                    break;
                case 6:
                    pCon.saveFile();
                    break;
                case 7:
                    pCon.printFromFile();
                    break;
                default:
                    if (pCon.isChanged()) {
                        if (res = MyTool.readBool("Data changed! Write to file? ")) {
                            pCon.saveFile();
                        }
                    }
            }
            System.out.println("");
        } while (choice1 > 0 && choice1 < 8);
        System.out.println("Bye");
    }

}
