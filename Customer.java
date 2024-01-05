package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.example.Registration.logger;

public class Customer {
     private static final Logger logger = Logger.getLogger(Customer.class.getName());
    Product product = new Product();
     Order order =new Order();
    Installer installer = new Installer();
    public String getUserName() {
        return userName;
    }

    public int getNumberOfLine() {
        return numberOfLine;
    }

    public void setNumberOfLine(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }

    int  numberOfLine;
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

    String userName;
    String Gmail;
    String Password;
    String address;
    int id;
    int phone;
    public void setCustomerLogin(boolean customerLogin) {
        this.customerLogin = customerLogin;
    }
    private boolean customerLogin ;
    private boolean browseProductsFlag;

    public boolean isSettingFlag() {
        return settingFlag;
    }

    public void setSettingFlag(boolean settingFlag) {
        this.settingFlag = settingFlag;
    }

    public boolean settingFlag;

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



    private boolean makePurchasesFlag;
    private boolean viewOrdersFlag;
    public Customer() {
        customerLogin = true;
        browseProductsFlag=false;
        makePurchasesFlag=false;
        viewOrdersFlag=false;
        installationServices=false;
        settingFlag=false;
    }

    public boolean isCustomerLogin() {
        return customerLogin;
    }



    public void Customer_menu (String CostName) {
        setUserName(CostName);
        setBrowseProductsFlag(false);
        setViewOrdersFlag(false);
        setMakePurchasesFlag(false);
        setInstallationServices(false);
        setSettingFlag(false);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[32m" + " -------  Welcome " +  CostName  + " ---------"+"\n"+
                "|                                  |\n" +
                "|      1.Browse products           |\n"+
                "|      2.make purchases            |\n"+
                "|      3.view orders               |\n"+
                "|      4.Installation Services     |\n"+
                "|      5.Setting                   |\n"+
                "|                                  |\n"+
                "-----------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            browseProductsFlag = true;
            userAccountMenu();
        } else if (choice == 2) {
            makePurchasesFlag = true;
            userAccountMenu();

        } else if (choice == 3) {
            viewOrdersFlag = true;
            userAccountMenu();

        }
        else if (choice == 4) {
            installationServices = true;
            userAccountMenu();

        }
        else if (choice == 5) {
            settingFlag = true;
            settingMenu();

        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }


    public static String getTheNameOfCat(Scanner scanner) {
        return scanner.next();
    }

    public void userAccountMenu(){
        if (browseProductsFlag){
            BrowseProductsMenu();
            back();
        }
        else if (makePurchasesFlag) {
            searchTheCustomer();
            order.makePurchasesMenu();
            back();
        }
        else if (viewOrdersFlag) {
            searchTheCustomer();
            order.manageOrderMenu();
            back();
        }
        else if (installationServices) {
            searchTheCustomer();
            installer.installerServicesMenu();
            back();
        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
            back();
        }
    }
    public void back() {
        logger.log(Level.INFO, """

                1) back\s
                2) Exit""");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        if (choice == 1)
            Customer_menu(getUserName());
        System.exit(0);
    }

    public void BrowseProductsMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """
            
            \u001B[35m---------------------------------
            |                                    |
            |      1. Show all products          |
            |      2. Search products            |
            |                                    | 
            --------------------------------------
            """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            ShowTheProduct();
            product.printAllCategory();
        } else if (choice == 2) {
            searchOfProduct();
        }
        else {
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[31" + "Invalid choice! Please enter a valid choice.\u001B[0m");
        }
    }

    private void ShowTheProduct() {
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Enter The name of category");
        String nameCato= getTheNameOfCat(scanner);
        product.ifCategoryExist(nameCato);
        if (product.isCategoryExistFlag()){
            product.printAllProductAndCategories(nameCato);
        }
        else {
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mThis category does not exist." + "\u001B[0m\n");
        }

    }

    public void searchOfProduct() {
        product.searchOfProducts();
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


    public void menuCustomerAdmin() {
        logger.log(Level.INFO,"\n\u001B[33m"+"----- Manage Customer Accounts -----"+"\n"+
                "|                                    |\n" +
                "|     1. View customer accounts.     |\n"+
                "|     2. Add customer Account.       |\n"+
                "|     3. Delete customer Account.    |\n"+
                "|                                    |\n"+
                "-------------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
    }

    private void settingMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        logger.log(Level.INFO,"\n\u001B[36m" + "------- Customer Profile -------"+"\n"+
                "|                             |\n"+
                "|     1. edit Password        |\n"+
                "|     2. edit Gmail           |\n"+
                "|     3. edit PhoneNumber     |\n"+
                "|     4. edit Address         |\n"+
                "|     5. back                 |\n"+
                "|                             |\n"+
                "------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        String choice2 ;
        String oldPass ;
        String newPass ;
        String newPassCon ;


        if (choice ==1) {
            logger.log(Level.INFO,"Enter The old password:"+"\u001B[0m");
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO,"Enter The new password:"+"\u001B[0m");
            newPass = scanner1.nextLine();
            logger.log(Level.INFO,"Confirm The  password:"+"\u001B[0m");
            newPassCon = scanner1.nextLine();
            editePassword(oldPass,newPass,newPassCon);
             settingMenu();
        }
        else if (choice ==2) {
            logger.log(Level.INFO,"Enter The new Gmail:"+"\u001B[0m");
              choice2 = scanner1.nextLine();
              editeGmail(choice2);
              logger.log(Level.INFO,"The Gmail has been changed successfully"+"\u001B[0m");
            settingMenu();

        }
        else if (choice==3){
            logger.log(Level.INFO,"Enter The new Phone Number:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
            editeNumber(choice2);
            logger.log(Level.INFO,"The Phone Number has been changed successfully"+"\u001B[0m");
            settingMenu();

        }
        else if (choice==4){
            logger.log(Level.INFO,"Enter The new Address:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
            editeAddress(choice2);
            logger.log(Level.INFO,"The Address has been changed successfully"+"\u001B[0m");
            settingMenu();
        }
        else if (choice==5){
            Customer_menu (getUserName());
        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }


    public boolean truepass (String pass, String ConfirmPass){
        if(pass.equals(ConfirmPass)){
            return true;
        }
        return false;
    }
   public void writeToFile(String dataToWrite,String fileName){
        RandomAccessFile file=null;
        try {
            file = new RandomAccessFile("src/main/resources/Data/"+fileName+".txt", "rw");

            file.seek(file.length());
            file.writeBytes(dataToWrite);
            file.close();
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
        }
    }
public void setTheCustomerIs(int numberOfLineCustomer){
        setNumberOfLine(numberOfLineCustomer);
}

    public void  editeUserName(String newName){
       searchTheCustomer();
       deleteLine();
       String data= newName+","+getGmail()+","+getPassword()+","+getAddress()+","+getId()+","+getPhone()+"\n";
       writeToFile(data,"custumorData");
       searchTheCustomerNewLine();
    }
    public void editeGmail(String newGmail){
        searchTheCustomer();
        deleteLine();
        String data= getUserName()+","+newGmail+","+getPassword()+","+getAddress()+","+getId()+","+getPhone()+"\n";
        writeToFile(data,"custumorData");
        searchTheCustomerNewLine();
    }
    public void editeNumber(String newPhone){
        searchTheCustomer();
        deleteLine();
        String data= getUserName()+","+getGmail()+","+getPassword()+","+getAddress()+","+getId()+","+newPhone+"\n";
        writeToFile(data,"custumorData");
        searchTheCustomerNewLine();
    }
    public void editeAddress(String newAddress){
        searchTheCustomer();
        deleteLine();
        String data= getUserName()+","+getGmail()+","+getPassword()+","+newAddress+","+getId()+","+getPhone()+"\n";
        writeToFile(data,"custumorData");
        searchTheCustomerNewLine();
    }
    public void  editePassword(String oldPass,String newPass,String conPass){
        searchTheCustomer();
        if(truepass(oldPass,getPassword())){
            if(truepass(newPass,conPass)) {
                deleteLine();
                String data = getUserName() + "," + getGmail() + "," + newPass + "," + getAddress() + "," + getId() + "," + getPhone() + "\n";
                writeToFile(data, "custumorData");
                searchTheCustomerNewLine();
                logger.log(Level.INFO, "\u001B[35m" + "The Password has been changed successfully" + "\u001B[0m");

            }
            else
                logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mThe Two password does not match"+"\u001B[0m");

        }

        else
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mThe password is incorrect" + "\u001B[0m");

}

public void deleteLine() {
    try {
        RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw");
        long start = 0;
        long currentPos = raf.getFilePointer();
        int currentLine = -1;

        while (currentLine < getNumberOfLine()){
            start = currentPos;
            raf.readLine();
            currentPos = raf.getFilePointer();
            currentLine++;
        }

        // Save the rest of the file after the line to be deleted
        long end = raf.length();
        byte[] remainingBytes = new byte[(int) (end - currentPos)];
        raf.read(remainingBytes);

        raf.seek(start);
        raf.write(remainingBytes);
        raf.setLength(start + remainingBytes.length);
        raf.close();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}
    private void extractedStoreData(String[] productInfo) {
            setUserName(productInfo[0]);
            setGmail(productInfo[1]);
            setPassword(productInfo[2]);
            setAddress(productInfo[3]);
            setId(Integer.parseInt(productInfo[4]));
            setPhone(Integer.parseInt(productInfo[5]));
            order.setIdCustomer(productInfo[4]);
            order.setCustomerName(productInfo[0]);
            installer.setIdCustomer(productInfo[4]);
            installer.setCustomerName(productInfo[0]);
            installer.setPhoneCustomer(productInfo[5]);
            installer.setGmail(productInfo[1]);
            installer.setAddress(productInfo[3]);
    }


    public void searchTheCustomer() {
        int count =-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
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
            throw new RuntimeException(e);
        }
    }
    public void searchTheCustomerNewLine() {
        int count=-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
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
            throw new RuntimeException(e);
        }
    }
    }



