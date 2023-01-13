package data;

import java.io.IOException;
import java.util.List;
import tools.MyTool;

public class Config {

    private static final String CONFIG_FILE = "DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() throws IOException {
        readData();
    }

    private void readData() throws IOException {
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.indexOf("ACCOUNT") >= 0) {
                accountFile = "DealerData/" + parts[1].trim();
            } else if (line.indexOf("DEALER") >= 0) {
                dealerFile = "DealerData/" + parts[1].trim();
            } else if (line.indexOf("DELIVERY") >= 0) {
                deliveryFile = "DealerData/" + parts[1].trim();
            }
        }
    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

}
