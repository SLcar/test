package org.example;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Customer extends RuntimeException {

    public Customer(String message, Throwable cause) {
        super(message, cause);

    }


    public static final Logger logger = Logger.getLogger(Customer.class.getName());
    public static final String CUSTUMOR_DATA = "custumorData";
    public static final String SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT = "src/main/resources/Data/custumorData.txt";
    Product product = new Product();
     Order order =new Order();
    public String getUserName() {
        return userName;
    }


  public static int getNumberOfLine() {
     return numberOfLine;
   }

 public static void setNumberOfLine(int numberOfLine) {
      Customer.numberOfLine = numberOfLine;
   }

    protected  static  int  numberOfLine ;


    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getGmail() {
        return Gmail;
    }
    public void setGmail(String gmail) {
        Gmail = gmail;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    protected String userName;
    protected String Gmail;
    protected String Password;
    protected String address;
    protected int id;
    protected int phone;
    public static void setCustomerLogin(boolean customerLogin) {
        Customer.customerLogin = customerLogin;
    }
    protected static boolean customerLogin;
    protected boolean browseProductsFlag;
    protected boolean settingFlag;

    public boolean isEditData() {
        return editData;
    }

    public void setEditData(boolean editData) {
        this.editData = editData;
    }

    public boolean editData;

    public boolean isSettingFlag() {
        return settingFlag;
    }
    public void setSettingFlag(boolean settingFlag) {
        this.settingFlag = settingFlag;
    }
    public boolean isInstallationServices() {
        return installationServices;
    }
    public void setInstallationServices(boolean installationServices) {
        this.installationServices = installationServices;
    }

    public boolean installationServices;
    public boolean isBrowseProductsFlag() {
        return browseProductsFlag;
    }

    public void setBrowseProductsFlag(boolean browseProductsFlag) {
        this.browseProductsFlag = browseProductsFlag;
    }

    public boolean isMakePurchasesFlag() {
        return makePurchasesFlag;
    }

    public void setMakePurchasesFlag(boolean makePurchasesFlag) {
        this.makePurchasesFlag = makePurchasesFlag;
    }

    public boolean isViewOrdersFlag() {
        return viewOrdersFlag;
    }

    public void setViewOrdersFlag(boolean viewOrdersFlag) {
        this.viewOrdersFlag = viewOrdersFlag;
    }

    protected boolean makePurchasesFlag;
    protected boolean viewOrdersFlag;
    public Customer() {
        customerLogin = true;
        browseProductsFlag=false;
        makePurchasesFlag=false;
        viewOrdersFlag=false;
        installationServices=false;
        settingFlag=false;
    }
    public boolean isCustomerLogin() {return customerLogin;}

    public void ShowTheProduct(String nameCato) {
        product.ifCategoryExist(nameCato);
        if (product.isCategoryExistFlag()){
            product.printAllProductAndCategories(nameCato);} else {logger.log(Level.WARNING, "This category does not exist.");}
    }

    public void whatCustomerEnter(String customerChoice) {
        switch (customerChoice) {
            case "1" -> setBrowseProductsFlag(true);
            case "2" -> setMakePurchasesFlag(true);
            case "3" -> setViewOrdersFlag(true);
            default -> {
                setBrowseProductsFlag(false);
                setMakePurchasesFlag(false);
                setViewOrdersFlag(false);
            }
        }
    }


    public boolean truepass (String pass, String ConfirmPass){
        return pass.equals(ConfirmPass);
    }
    public void writeToFile(String dataToWrite, String fileName) {
        try (RandomAccessFile file = new RandomAccessFile("src/main/resources/Data/" + fileName + ".txt", "rw")) {
            file.seek(file.length());
            file.writeBytes(dataToWrite);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }

    public final void setTheCustomerIs(int numberOfLineCustomer){

        setNumberOfLine(numberOfLineCustomer);
}

    public void editeGmail(String newGmail){
        searchTheCustomer();
        deleteLine();
        String data= getUserName()+","+newGmail+","+getPassword()+","+getAddress()+","+getId()+","+getPhone()+"\n";
        writeToFile(data, CUSTUMOR_DATA);
        searchTheCustomerNewLine();
    }
    public void editeNumber(String newPhone){
        searchTheCustomer();
        deleteLine();
        String data= getUserName()+","+getGmail()+","+getPassword()+","+getAddress()+","+getId()+","+newPhone+"\n";
        writeToFile(data, CUSTUMOR_DATA);
        searchTheCustomerNewLine();
    }
    public void editeAddress(String newAddress){
        searchTheCustomer();
        deleteLine();
        String data= getUserName()+","+getGmail()+","+getPassword()+","+newAddress+","+getId()+","+getPhone()+"\n";
        writeToFile(data, CUSTUMOR_DATA);
        searchTheCustomerNewLine();
    }
    public void  editePassword(String oldPass,String newPass,String conPass){
        searchTheCustomer();
        if(truepass(oldPass,getPassword())){
            if(truepass(newPass,conPass)) {
                deleteLine();
                String data = getUserName() + "," + getGmail() + "," + newPass + "," + getAddress() + "," + getId() + "," + getPhone() + "\n";
                writeToFile(data,  CUSTUMOR_DATA);
                searchTheCustomerNewLine();
                logger.log(Level.INFO, "The Password has been changed successfully");

            }
            else
                logger.log(Level.WARNING,"The Two password does not match");

        }

        else
            logger.log(Level.WARNING, "The password is incorrect");

}

public void deleteLine() {
    try {
        RandomAccessFile raf = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT, "rw");
        long start = 0;
        long currentPos = raf.getFilePointer();
        int currentLine = -1;

        while (currentLine < getNumberOfLine()){
            start = currentPos;
            raf.readLine();
            currentPos = raf.getFilePointer();
            currentLine++;
        }
        long end = raf.length();
        byte[] remainingBytes = new byte[(int) (end - currentPos)];
        raf.read(remainingBytes);

        raf.seek(start);
        raf.write(remainingBytes);
        raf.setLength(start + remainingBytes.length);
        raf.close();

    } catch (IOException e) {
        throw new Customer("Error during customer login", e);
    }

}
    public void extractedStoreData(String[] productInfo) {
            setUserName(productInfo[0]);
            setGmail(productInfo[1]);
            setPassword(productInfo[2]);
            setAddress(productInfo[3]);
            setId(Integer.parseInt(productInfo[4]));
            setPhone(Integer.parseInt(productInfo[5]));
            order.setIdCustomer(productInfo[4]);
            order.setCustomerName(productInfo[0]);
            Installer.setIdCustomer(productInfo[4]);
            Installer.setCustomerName(productInfo[0]);
            Installer.setPhoneCustomer(productInfo[5]);
            Installer.setGmail(productInfo[1]);
            Installer.setAddress(productInfo[3]);
    }

    public void searchTheCustomer() throws RuntimeException {
        int count =-1;
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                count=count+1;
                String[] productInfo = s.split(",");
                if (count==getNumberOfLine()) {
                    extractedStoreData(productInfo);
                    return;
                }
            }
        } catch (IOException e) {
            throw new Customer("Error during customer login", e);
        }
    }
    public  void searchTheCustomerNewLine() {
        int count=-1;
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT, "rw")) {
            String s;
            String isd= String.valueOf(getId());
            while ((s = ref.readLine()) != null) {
                count=count+1;
                String[] productInfo = s.split(",");
                if (isd.equals(productInfo[4]))
                {
                    setUserName(productInfo[0]);
                    setNumberOfLine(count);
                    return;
                }
            }
        } catch (IOException e) {
            throw new Customer("Error during customer login", e);
        }
    }
    }



