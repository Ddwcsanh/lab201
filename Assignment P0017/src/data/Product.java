package data;

public class Product {

    private String productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;
    
    public static final String ID_PARTTERN = "P\\d{3}";
    public static final String NAME_PARTTERN = "\\[S{5}]+";
    public static final String DOUBLE_INPUT = "([0-9]+\\.?[0-9]*+)|";
    public static final String INTEGER_INPUT = "[0-9]+|";

    public Product() {
    }

    public Product(String productID, String productName, double unitPrice, int quantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "productID=" + productID + ", productName=" + productName + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", status=" + status;
    }

}
