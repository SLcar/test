package org.example;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {
    String enter ;
public static final String FILE_PATH = "src/main/resources/Data/AdminData.txt";
public static final Logger logger = Logger.getLogger(Admin.class.getName());
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

    public boolean productsFlag,categoriesFlag,userAccountsFlag;

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
public static final String INVALID_CHOICE_MESSAGE = "\u001B[31mInvalid choice! Please enter a valid choice.";

    public static String getMsg = "Enter your choice: ";


    public static String getDelivered = "delivered";






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
                logger.log(Level.INFO,"The Password has been changed successfully");

            }
            else
                logger.log(Level.WARNING, "\u001B[31mThe Two password does not match");

        }
        else
            logger.log(Level.WARNING, "The password is incorrect");

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
        file.seek(file.length());
        file.writeBytes(dataToWrite);
    } catch (IOException e) {
        logger.log(Level.SEVERE, "An error occurred", e);
    }
}



    public boolean truepass (String pass, String ConfirmPass){
        return pass.equals(ConfirmPass);
    }
}
