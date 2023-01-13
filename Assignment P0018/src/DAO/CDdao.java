package DAO;

import data.CD;
import java.util.List;

public interface CDdao {
    
    void getNewCD();
    List<CD> getAllCD();
    CD searchCDByID(String code);
    boolean searchCDByTitle(String name);
    CD getCDByID(String code);
    List<CD> getCDByTitle(String name);
    void addCD();
    void updateCD(CD cd);
    void deleteCD(CD cd);
    void saveFile();
    
}
