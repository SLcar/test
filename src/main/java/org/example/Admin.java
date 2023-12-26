package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;

import static org.example.Registration.logger;

public class Admin {

    Order order = new Order();

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    String first ;
    String sec;
    String third ;

    public boolean isAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(boolean adminLogin) {
        this.adminLogin = adminLogin;
    }

    private boolean adminLogin ;

    public boolean isProductsFlag() {
        return productsFlag;
    }

    public void setProductsFlag(boolean productsFlag) {
        this.productsFlag = productsFlag;
    }

    public boolean isCategoriesFlag() {
        return categoriesFlag;
    }

    public void setCategoriesFlag(boolean categoriesFlag) {
        this.categoriesFlag = categoriesFlag;
    }

    public boolean isUserAccountsFlag() {
        return userAccountsFlag;
    }

    public void setUserAccountsFlag(boolean userAccountsFlag) {
        this.userAccountsFlag = userAccountsFlag;
    }

    private boolean productsFlag;
    private boolean categoriesFlag;
    private boolean userAccountsFlag;

    public boolean isOrderCustomerFlag() {
        return orderCustomerFlag;
    }

    public void setOrderCustomerFlag(boolean orderCustomerFlag) {
        this.orderCustomerFlag = orderCustomerFlag;
    }

    public boolean isInstallationRequestsFlag() {
        return installationRequestsFlag;
    }

    public void setInstallationRequestsFlag(boolean installationRequestsFlag) {
        this.installationRequestsFlag = installationRequestsFlag;
    }

    private boolean orderCustomerFlag;
    private boolean installationRequestsFlag;

    public int getAdminRoleChoice() {
        return adminRoleChoice;
    }

    public void setAdminRoleChoice(int adminRoleChoice) {

        this.adminRoleChoice = adminRoleChoice;
    }

    private int adminRoleChoice ;
    Product product = new Product();
    Category category = new Category();
    Customer customer = new Customer();
    Installer installer =new Installer();
    public Admin() {
        adminLogin = true;
        productsFlag=false;
        categoriesFlag=false;
        userAccountsFlag=false;
    }
    public void whatAdminEnter(String AdminChoice){
        if (AdminChoice.equals("1")){
            setProductsFlag(true);
        } else if (AdminChoice.equals("2")) {
            setCategoriesFlag(true);
        } else if (AdminChoice.equals("3")) {
            setUserAccountsFlag(true);
        }
        else {
            setCategoriesFlag(false);
            setUserAccountsFlag(false);
            setProductsFlag(false);
        }
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    String adminName;
    public void Admin_menu (String AdminName){
        setProductsFlag(false);
        setCategoriesFlag(false);
        setUserAccountsFlag(false);
        setOrderCustomerFlag(false);
        setInstallationRequestsFlag(false);

        setAdminName(AdminName);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[37m" + "----------  Welcome " +  AdminName  + " -------"+"\n"+
                "|    1. Manage products                  |\n"+
                "|    2. Manage categories                |\n"+
                "|    3. Manage user accounts.            |\n"+
                "|    4. Manage order Customer.           |\n"+
                "|    5. Manage Installation Requests     |\n"+
                "------------------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            productsFlag=true;
            userAccountMenu();
        } else if (choice == 2) {
            categoriesFlag=true;
            userAccountMenu();

        } else if (choice == 3) {
            userAccountsFlag =true;
            userAccountMenu();

        }
        else if (choice == 4) {
            orderCustomerFlag=true;
            userAccountMenu();

        }
        else if (choice == 5) {
            installationRequestsFlag=true;
            userAccountMenu();

        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
            Admin_menu(AdminName);
        }

    }
    public void userAccountMenu(){
        if (productsFlag){
            product.menuProduct();
        }
        else if (categoriesFlag) {
            category.menuCategory();
        }
        else if (userAccountsFlag) {
            menuManageAccountUser();
        }
        else if (orderCustomerFlag) {
            menuOrderCustomer();
        }
        else if (installationRequestsFlag) {
            installer.installer_menu(getAdminName());
        }
    }
    public void menuOrderCustomer(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[34m" + "----- Manage Order Customer -----"+"\n"+
                "|     1. Show All Order         |\n"+
                "|     2. edit Customer Order    |\n"+
                "|     3. back                   |\n"+
                "---------------------------------n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            order.viewAllOrderToAdmin();
            menuOrderCustomer();
        }
        else if (choice ==2) {
            enterData();
            menuOrderCustomer();
        }
        else if (choice ==3) {
            Admin_menu(getAdminName());}

        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
            menuOrderCustomer();
        }

    }

    private void enterData() {
        Scanner scanner4 = new Scanner(System.in);
        int choice;
        logger.log(Level.INFO,"Enter The Number Of Order: "+"\u001B[0m");
        order.setOrderNumber(Long.parseLong(scanner4.next()));
        order.ifEnterOrderExitToChangeSt(order.getOrderNumber());

        if(order.isIfOrderExist()){
            order.searchAboutCustomer("orderToAdmin",order.getOrderNumber());
            order.searchAboutGmail();
            logger.log(Level.INFO, """
            
            \u001B[35m-------------------------------
            |                              |
            |        1. shipped            |
            |        2. delivered          |
            |        3. canceled           |
            |                              |
            --------------------------------
            """);
            logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");

            choice = scanner4.nextInt();
            if (choice==1) {
                order.setStatusOrder("shipped");

            } else if (choice == 2) {
                order.setStatusOrder("delivered");
            } else if (choice == 3) {
                order.setStatusOrder("canceled");

            } else {
                logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m\n");
                menuOrderCustomer();
            }

            order.editTheOrder(order.getStatusOrder());
            menuOrderCustomer();


        }

        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The order Not Found" + "\u001B[0m");
            menuOrderCustomer();
        }
    }


    public void menuManageAccountUser(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[34m" + "----- Manage user -----"+"\n"+
                "|     1. Admin         |\n"+
                "|     2. Customer      |\n"+
                "|     3. Installer     |\n"+
                "|     4. Back          |\n"+
                "-----------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            editAdminProfile();
        }
        else if (choice ==2) {
            customer.menuCustomerAdmin();
        }
        else if (choice ==3) {
            installer.menuInstallerAdmin();
        }
        else if (choice ==4) {
            Admin_menu(getAdminName());
        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
            menuManageAccountUser();
        }

    }

    public void editAdminProfile(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        logger.log(Level.INFO,"\n\u001B[36m" + "----- Admin Profile -----"+"\n"+
                "|   1. edit userName   |\n"+
                "|   2. edit Password   |\n"+
                "|   3. edit Gmail      |\n"+
                "|   4. back            |\n"+
                "-----------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        String choice2 ;
        String oldPass ;
        String newPass ;
        String newPassCon ;

        if (choice == 1) {
            logger.log(Level.INFO,"Enter The new user Name:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
            editeUserName(choice2);
            logger.log(Level.INFO,"The user name has been changed successfully"+"\u001B[0m");
            editAdminProfile();
        }
        else if (choice ==2) {
            logger.log(Level.INFO,"Enter The old password:"+"\u001B[0m");
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO,"Enter The new password:"+"\u001B[0m");
            newPass = scanner1.nextLine();
            logger.log(Level.INFO,"Confirm The  password:"+"\u001B[0m");
            newPassCon = scanner1.nextLine();
            editePassword(oldPass,newPass,newPassCon);
            editAdminProfile();

        }
        else if (choice ==3) {
            logger.log(Level.INFO,"Enter The new Gmail:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
            editeGmail(choice2);
            logger.log(Level.INFO,"The Gmail has been changed successfully"+"\u001B[0m");
            editAdminProfile();

        }
        else if (choice==4){

        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }

    private void editeGmail(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(getFirst()+","+choice2+","+getThird());

    }
    private void editePassword(String oldPass, String newPass, String newPassCon) {
        fileFunction();
        if(truepass(oldPass,getThird())){
            if(truepass(newPass,newPassCon)){
                deleteFileFunction();
                writeToFile(getFirst()+","+getSec()+","+newPass);
                logger.log(Level.INFO,"\u001B[35m"+"The Password has been changed successfully"+"\u001B[0m");

            }
            else
                logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mThe Two password does not match"+"\u001B[0m");

        }
        else
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mThe password is incorrect"+"\u001B[0m");

    }

    private void editeUserName(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(choice2+","+getSec()+","+getThird());
    }
    public void fileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
            String s;
            while ((s = raf.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                first=loginCustomer[0];
                sec=loginCustomer[1];
                third=loginCustomer[2];
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteFileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
            // Seek to the beginning of the file
            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;

            while (currentLine < 0) {
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
    public void writeToFile(String dataToWrite){
        try {


            RandomAccessFile file = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw");

            // Go to the end of the file
            file.seek(file.length());

            // Write the string to the file
            file.writeBytes(dataToWrite);

            // Close the file
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public boolean truepass (String pass, String ConfirmPass){
        if(pass.equals(ConfirmPass)){
            return true;
        }
        return false;
    }
}

