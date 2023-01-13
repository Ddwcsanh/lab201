package data;

import java.io.Serializable;

public class CD implements Serializable, Comparable {

    private String CDId;
    private String CDTitle;
    private double unitPrice;
    private String CDType;
    private String CDCollection;
    private int pulishingYear;

    public static final String ID_PARTTERN = "C\\d{3}|p\\d{3}";
    public static final String DOUBLE_INPUT = "[0-9]+\\.?[0-9]*";
    public static final String INTEGER_INPUT = "[0-9]+";

    public CD() {
    }

    public CD(String CDCode, String CDTitle, double unitPrice, String CDType, String CDCollection, int pulishingYear) {
        this.CDId = CDCode;
        this.CDTitle = CDTitle;
        this.unitPrice = unitPrice;
        this.CDType = CDType;
        this.CDCollection = CDCollection;
        this.pulishingYear = pulishingYear;
    }

    public String getCDId() {
        return CDId;
    }

    public void setCDId(String CDId) {
        this.CDId = CDId;
    }

    public String getCDTitle() {
        return CDTitle;
    }

    public void setCDTitle(String CDTitle) {
        this.CDTitle = CDTitle;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCDType() {
        return CDType;
    }

    public void setCDType(String CDType) {
        this.CDType = CDType;
    }

    public String getCDCollection() {
        return CDCollection;
    }

    public void setCDCollection(String CDCollection) {
        this.CDCollection = CDCollection;
    }

    public int getPulishingYear() {
        return pulishingYear;
    }

    public void setPulishingYear(int pulishingYear) {
        this.pulishingYear = pulishingYear;
    }

    public void print() {
        System.out.println(CDId + ", " + CDTitle + ", " + unitPrice + ", " + CDType + ", " + CDCollection + ", " + pulishingYear);
    }

    @Override
    public String toString() {
        return CDId + ", " + CDTitle + ", " + unitPrice + ", " + CDType + ", " + CDCollection + ", " + pulishingYear;
    }

    @Override
    public int compareTo(Object a) {
        return this.getCDTitle().compareTo(((CD) a).getCDTitle());
    }

}
