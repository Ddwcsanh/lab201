
package DAO;

import data.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> getNewProduct();
    List<Product> getFileProduct();
    Product searchProductByID(String ID);
    boolean searchProductByName(String productName);
    Product getProductByID(String ID);
    List<Product> getProductByName(String productName);
    void createProduct();
    void updateProduct(Product product);
    void deleteProduct(Product product);
    void saveFile(); 
}