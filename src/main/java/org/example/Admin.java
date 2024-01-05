package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {
    String enter ;
public static final String FILE_PATH = "src/main/resources/Data/AdminData.txt";
public static final Logger logger = Logger.getLogger(Admin.class.getName());
    Order order = new Order();

    public String getFirst() {
        return first;
    }

    public String getSec() {
        return sec;
    }


    public String getThird() {
        return third;
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

    public boolean productsFlag;
    public boolean categoriesFlag;
    public boolean userAccountsFlag;

    public void setOrderCustomerFlag(boolean orderCustomerFlag) {
        this.orderCustomerFlag = orderCustomerFlag;
    }

    public void setInstallationRequestsFlag(boolean installationRequestsFlag) {
        this.installationRequestsFlag = installationRequestsFlag;
    }

    public boolean orderCustomerFlag;
    public boolean installationRequestsFlag;
    public Admin() {
        adminLogin = true;
        productsFlag=false;
        categoriesFlag=false;
        userAccountsFlag=false;
    }
    public void whatAdminEnter(String adminChoice){
        switch (adminChoice) {
            case "1" -> setProductsFlag(true);
            case "2" -> setCategoriesFlag(true);
            case "3" -> setUserAccountsFlag(true);
            default -> {
                setCategoriesFlag(false);
                setUserAccountsFlag(false);
                setProductsFlag(false);
            }
        }
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    String adminName;



    public static final String COLOR = "\u001B[0m";

    public static final String COLOR_2 = "\u001B[1m";

public static final String INVALID_CHOICE_MESSAGE = "\u001B[31mInvalid choice! Please enter a valid choice.\u001B[0m";

    public static String getMsg() {
        return "Enter your choice: " + COLOR;
    }

    public void enterData() {
        Scanner scanner4 = new Scanner(System.in);
        int choice;
        logger.log(Level.INFO,"Enter The Number Of Order: "+ COLOR);
        order.setOrderNumber(Long.parseLong(scanner4.next()));
        order.ifEnterOrderExitToChangeSt(order.getOrderNumber());

        if(order.isIfOrderExist()){
            order.searchAboutCustomer("orderToAdmin",order.getOrderNumber());
            order.searchAboutGmail();
            enter= """
            
            \u001B[35m-------------------------------
            |                              |
            |        1. shipped            |
            |        2. delivered          |
            |        3. canceled           |
            |                              |
            --------------------------------
            """;
            logger.log(Level.INFO,enter);
            logger.log(Level.INFO, getMsg());

            choice = scanner4.nextInt();
            if (choice==1) {
                order.setStatusOrder("shipped");

            } else if (choice == 2) {
                order.setStatusOrder(getDelivered());
            } else if (choice == 3) {
                order.setStatusOrder("canceled");

            } else {
                logger.log(Level.WARNING, COLOR_2 +INVALID_CHOICE_MESSAGE +"\u001B[0m\n");
                //menuOrderCustomer();
            }

            order.editTheOrder(order.getStatusOrder());
            //menuOrderCustomer();


        }

        else{
            logger.log(Level.WARNING, COLOR_2 + "\u001B[31m The order Not Found" + COLOR);
           // menuOrderCustomer();
        }
    }

    public static String getDelivered() {
        return "delivered";
    }




    public void editAdminProfile(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        enter="""

                \u001B[36m----- Admin Profile -----
                |   1. edit userName   |
                |   2. edit Password   |
                |   3. edit Gmail      |
                -----------------------
                """;
        logger.log(Level.INFO, enter);
        logger.log(Level.INFO, getMsg());
        choice = scanner.nextInt();
        String choice2 ;
        String oldPass ;
        String newPass ;
        String newPassCon ;

        if (choice == 1) {
            logger.log(Level.INFO,"Enter The new user Name:"+ COLOR);
            choice2 = scanner1.nextLine();
            editeUserName(choice2);
            logger.log(Level.INFO,"The user name has been changed successfully"+ COLOR);
            editAdminProfile();
        }
        else if (choice ==2) {
            logger.log(Level.INFO,"Enter The old password:"+ COLOR);
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO,"Enter The new password:"+ COLOR);
            newPass = scanner1.nextLine();
            logger.log(Level.INFO,"Confirm The  password:"+ COLOR);
            newPassCon = scanner1.nextLine();
            editePassword(oldPass,newPass,newPassCon);
            editAdminProfile();

        }
        else if (choice ==3) {
            logger.log(Level.INFO,"Enter The new Gmail:"+ COLOR);
            choice2 = scanner1.nextLine();
            editeGmail(choice2);
            logger.log(Level.INFO,"The Gmail has been changed successfully"+ COLOR);
            editAdminProfile();

        }
        else {
            logger.log(Level.WARNING, COLOR_2 + INVALID_CHOICE_MESSAGE + COLOR);
        }
    }

    public void editeGmail(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(getFirst()+","+choice2+","+getThird());

    }
    public void editePassword(String oldPass, String newPass, String newPassCon) {
        fileFunction();
        if(truepass(oldPass,getThird())){
            if(truepass(newPass,newPassCon)){
                deleteFileFunction();
                writeToFile(getFirst()+","+getSec()+","+newPass);
                logger.log(Level.INFO,"\u001B[35m"+"The Password has been changed successfully"+ COLOR);

            }
            else
                logger.log(Level.WARNING, COLOR_2 +"\u001B[31mThe Two password does not match"+ COLOR);

        }
        else
            logger.log(Level.WARNING, COLOR_2 +"\u001B[31mThe password is incorrect"+ COLOR);

    }

    public void editeUserName(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(choice2+","+getSec()+","+getThird());
    }
    public void fileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
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

        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;
            while (currentLine < 0) {
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
   public void writeToFile(String dataToWrite) {
    try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
        // Go to the end of the file
        file.seek(file.length());
        // Write the string to the file
        file.writeBytes(dataToWrite);
    } catch (IOException e) {
        logger.log(Level.SEVERE, "An error occurred", e);
    }
}



    public boolean truepass (String pass, String ConfirmPass){
        return pass.equals(ConfirmPass);
    }
}
