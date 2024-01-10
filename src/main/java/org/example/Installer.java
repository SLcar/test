package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.SecureRandom;

public class Installer {
    String data ;
    Order order = new Order();

   public String[] arrayOfTopic =  {"Installation confirmation", "installer not available", "Cancel Installation Request","Task finished"}; // Creating an array that can hold 3 strings
    public String[] arrayOfMsg = {"The Installation has been confirmed","We are sorry, but the Installation Request has been canceled due to logistical restrictions beyond our store's control","Thank you for using our company"}; // Creating an array that can hold 3 strings
    public String first;
    public static final Logger logger = Logger.getLogger(Installer.class.getName());
    public static final String ENTER_CHOICE_MESSAGE = "Enter your choice: ";
    public static final String LINE_SEPARATOR = "|                                  |";
    public static final String BOLD = "\u001B[1m";
    public static final String RESET_COLOR = "\u001B[0m";


    public long getIdInstallerRequest() {
        return idInstallerRequest;
    }
    public static void setIdInstallerRequest(long idInstallerRequest) {
        Installer.idInstallerRequest = idInstallerRequest;
    }
    public String getPreferredDate() {
        return preferredDate;
    }
    public  static void setPreferredDate(String preferredDate) {
        Installer.preferredDate = preferredDate;
    }
    public String getPreferredHour() {
        return preferredHour;
    }
    public static void setPreferredHour(String preferredHour) {
        Installer.preferredHour = preferredHour;
    }
    public String getLocationInstalling() {
        return locationInstalling;
    }
    public static void setLocationInstalling(String locationInstalling) {
        Installer.locationInstalling = locationInstalling;
    }
    public String getProduct() {
        return product;
    }
    public static void setProduct(String product) {
        Installer.product = product;
    }
    public String getStatusInstalling() {
        return statusInstalling;
    }
    public  static void setStatusInstalling(String statusInstalling) {
        Installer.statusInstalling = statusInstalling;
    }
    public String getCompletionDate() {
        return completionDate;
    }
    public static  void setCompletionDate(String completionDate) {
        Installer.completionDate = completionDate;
    }

    public boolean isIdInstallationFlag() {
        return idInstallationFlag;
    }

    public static void setIdInstallationFlag(boolean idInstallationFlag) {
        Installer.idInstallationFlag = idInstallationFlag;
    }
    public static boolean idInstallationFlag;
    public static long idInstallerRequest;
    public static String preferredDate,preferredHour,product,locationInstalling,statusInstalling,completionDate;
    public static final String REQUEST_INSTALLATION_FILE_PATH = "src/main/resources/Data/requestInstallation.txt";
    public String getInstallerName() {
        return installerName;
    }
    public  void   setInstallerName(String setInstallerName) {
        installerName = setInstallerName;
    }
    public static String installerName;
    public ArrayList<String> listPrint = new ArrayList<>();

    public boolean isTheDataCancel() {
        return theDataCancel;
    }

    public static void setTheDataCancel(boolean theDataCancel) {
        Installer.theDataCancel = theDataCancel;
    }

    public static boolean theDataCancel;
    public  static final SecureRandom secureRandom = new SecureRandom();

    public void randomNumberGenerator() {
        long min = 1000000000L; // Minimum 10-digit number
        long max = 9999999999L; // Maximum 10-digit number

        long randomNum = min + ((long) (secureRandom.nextDouble() * (max - min)));
        setIdInstallerRequest(randomNum);
    }
    public void ifRandomNumberGeneratorNotFound() {

        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String idInstallersRequest = productInfo[0];
                randomNumberGenerator();
                String id = String.valueOf(getIdInstallerRequest());
                if (id.equals(idInstallersRequest)) {
                    randomNumberGenerator();
                    ifRandomNumberGeneratorNotFound();
                }
                else {
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Installer() {
        viewRequestsFlag =false;
        scheduleAppointmentFlag =false;
    }

    public boolean isViewRequestsFlag() {
        return viewRequestsFlag;
    }

    public void setViewRequestsFlag(boolean viewRequestsFlag) {
        Installer.viewRequestsFlag = viewRequestsFlag;
    }

    public  static boolean  viewRequestsFlag;

    public boolean isScheduleAppointmentFlag() {
        return scheduleAppointmentFlag;
    }

    public void setScheduleAppointmentFlag(boolean scheduleAppointmentFlag) {
        Installer.scheduleAppointmentFlag = scheduleAppointmentFlag;
    }

    public static boolean scheduleAppointmentFlag,installerLogin;

    public void setInstallerLogin(boolean installerLogin) {
        Installer.installerLogin = installerLogin;
    }





    public boolean isChangeTimeOrHour() {
        return changeTimeOrHour;
    }

    public void setChangeTimeOrHour(boolean changeTimeOrHour) {
        Installer.changeTimeOrHour = changeTimeOrHour;
    }

    public static boolean changeTimeOrHour;







    public boolean truepass (String pass, String ConfirmPass){
        return pass.equals(ConfirmPass);
    }
    public void editePassword(String oldPass, String newPass, String newPassCon) {
        fileFunction();
        if(truepass(oldPass,getThird())){
            if(truepass(newPass,newPassCon)){
                deleteFileFunction();
                writeToFile(getFirst()+","+getSec()+","+newPass);
                logger.log(Level.INFO,"\u001B[35m"+"The Password has been changed successfully"+RESET_COLOR);

            }
            else
                logger.log(Level.WARNING,BOLD+"\u001B[31mThe Two password does not match"+RESET_COLOR);

        }
        else
            logger.log(Level.WARNING,BOLD+"\u001B[31mThe password is incorrect"+RESET_COLOR);

    }
    public void editeUserName(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(choice2+","+getSec()+","+getThird());
    }
    public void editeGmail(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(getFirst()+","+choice2+","+getThird());

    }


    public void writeToFile(String s) {
        RandomAccessFile file=null;
        try {
            file = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw");
            file.seek(file.length());
            file.writeBytes(s);
            file.close();
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
            }}

    }

    private void deleteFileFunction() {
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {
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

    public String getFirst() {
        return first;
    }
    public String getSec() {
        return sec;
    }
    public String getThird() {
        return third;
    }

    public  static String sec;
    public static String third;
    public void fileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {
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
    public void showInstallerAccount() {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productData = s.split(",");
                String nameLoop  = productData[0];
                String mail  = productData[1];
                String password  = productData[2];
                logger.info("\u001B[36m"+"Name : "+nameLoop +
                        "  Gmail:  "+mail+" password : "+password+"\n\u001B[37m");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void ifExitIdInstallerRequestPending() {
        numberOfLine=-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
            String s;
            String IdInstallation = String.valueOf(getIdInstallerRequest());
            while ((s = ref.readLine()) != null) {
                numberOfLine=numberOfLine+1;
                String[] productInfo = s.split(",");
                if(productInfo[1].equals(getIdCustomer()) && productInfo[0].equals(IdInstallation) && productInfo[9].equals("pending")){
                    setIdInstallationFlag(true);
                    return;
                }
                else{
                    setIdInstallationFlag(false);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ifExitIdInstallerRequestPendingToAdmin() {
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            String IdInstallation = String.valueOf(getIdInstallerRequest());
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[0].equals(IdInstallation) && productInfo[9].equals("pending")){
                    setIdInstallationFlag(true);
                    return;
                }
                else{
                    setIdInstallationFlag(false);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void showAllInstallationRequestToAdminANDInstaller(String status) {
        boolean dv =false;
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[9].equals(status)) {

                    listPrint.add(productInfo[0]);
                    listPrint.add(productInfo[1]);
                    listPrint.add(productInfo[2]);
                    listPrint.add(productInfo[3]);
                    listPrint.add(productInfo[4]);
                    listPrint.add(productInfo[5]);
                    listPrint.add(productInfo[6]);
                    listPrint.add(productInfo[7]);
                    listPrint.add(productInfo[8]);
                    listPrint.add(productInfo[9]);
                    listPrint.add(productInfo[10]);
                    dv = true;
                    printDataToAdmin();
                    listPrint.clear();
                }
            }
            if (!dv){
                logger.info("There is no Installation Request history");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printDataToAdmin() {
        logger.info("\u001B[34m The ID Of Installation Request :\u001B[35m " +listPrint.get(0)+" |"
                +"\u001B[34m ID Customer :\u001B[35m "+listPrint.get(1)+" |"
                +"\u001B[34m Phone Number :\u001B[35m "+listPrint.get(2)+" |"
                +"\u001B[34m Name :\u001B[35m "+listPrint.get(3)+" |"
                +"\u001B[34m Email :\u001B[35m "+listPrint.get(4)+" |"
                +"\u001B[34m Request for produc :\u001B[35m "+listPrint.get(5)+" |"
                +"\u001B[34m Preferred date  :\u001B[35m "+listPrint.get(6)+" |"
                +"\u001B[34m preferred day :\u001B[35m "+listPrint.get(7)+" |"
                +"\u001B[34m Installing location :\u001B[35m "+listPrint.get(8)+" |"
                +"\u001B[34m Request Status :\u001B[35m "+listPrint.get(9)+" |"
                +"\u001B[34m Completion date :\u001B[35m "+listPrint.get(10)+" |");
    }

    public void showAllInstallationRequest() {
        boolean dv =false;
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[1].equals(getIdCustomer())){
                    listPrint.add(productInfo[0]);
                    listPrint.add(productInfo[2]);
                    listPrint.add(productInfo[3]);
                    listPrint.add(productInfo[4]);
                    listPrint.add(productInfo[5]);
                    listPrint.add(productInfo[6]);
                    listPrint.add(productInfo[7]);
                    listPrint.add(productInfo[8]);
                    listPrint.add(productInfo[9]);
                    listPrint.add(productInfo[10]);

                    dv = true;
                    printDataToCustomer();
                    listPrint.clear();
                }


            }
            if (!dv){
                logger.info("There is no Installation Request history");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllInstallationRequestPending() {
        boolean dv =false;
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[1].equals(getIdCustomer()) && productInfo[9].equals("pending")){
                    listPrint.add(productInfo[0]);
                    listPrint.add(productInfo[2]);
                    listPrint.add(productInfo[3]);
                    listPrint.add(productInfo[4]);
                    listPrint.add(productInfo[5]);
                    listPrint.add(productInfo[6]);
                    listPrint.add(productInfo[7]);
                    listPrint.add(productInfo[8]);
                    listPrint.add(productInfo[9]);
                    listPrint.add(productInfo[10]);

                    dv = true;
                    printDataToCustomer();
                    listPrint.clear();
                }


            }
            if (!dv){
                logger.info("There is no Installation Request pending");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printDataToCustomer() {
        logger.info("\u001B[34m The ID Of Installation Request :\u001B[35m " +listPrint.get(0)+" |"
                +"\u001B[34m Your Phone Number :\u001B[35m "+listPrint.get(1)+" |"
                +"\u001B[34m Your Name :\u001B[35m "+listPrint.get(2)+" |"
                +"\u001B[34m Your Email :\u001B[35m "+listPrint.get(3)+" |"
                +"\u001B[34m Request for produc :\u001B[35m "+listPrint.get(4)+" |"
                +"\u001B[34m Preferred date  :\u001B[35m "+listPrint.get(5)+" |"
                +"\u001B[34m preferred day :\u001B[35m "+listPrint.get(6)+" |"
                +"\u001B[34m Installing location :\u001B[35m "+listPrint.get(7)+" |"
                +"\u001B[34m Request Status :\u001B[35m "+listPrint.get(8)+" |"
                +"\u001B[34m Completion date :\u001B[35m "+listPrint.get(9)+" |");

    }

    ///////////////////////////////////////// enterDataOfRequest()////////////////////////////

    public  boolean ifFilledTheDateNull(){
        return !getPreferredDate().equals("");
    }
    public   boolean ifFilledTheHourNull(){
        return !getPreferredHour().equals("");

    } public  boolean ifFilledTheProductNull(){
        return !getProduct().equals("");

    } public  boolean ifFilledTheLocationNull(){
        return !getLocationInstalling().equals("");
    } public  boolean ifFilledTheAnyDayAndAnyTime(){
        return getPreferredDate().equals("Any Day") && getPreferredHour().equals("Any Time");
    }
    public boolean ifDataTrue() {
        return (ifFilledTheProductNull() && ifFilledTheDateNull() && ifFilledTheHourNull() && ifFilledTheLocationNull())
                || (ifFilledTheProductNull() && ifFilledTheAnyDayAndAnyTime()) && ifFilledTheLocationNull();
    }

    public void ifAnyDayAnyTime(String preferredDate,String preferredHour){
        setInstallerAvailableToCustomer(true);

        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[9].equals("scheduled") && productInfo[6].equals(preferredDate)&& productInfo[7].equals(preferredHour)) {
                    setInstallerAvailableToCustomer(false);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void  enterDataOfRequest(){
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Describe the product you need service for : "+RESET_COLOR);
        setProduct(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred date for this service like (1/1/2022) or (Any Date): "+RESET_COLOR);
        setPreferredDate(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred Hour of this date like (10:00 AM) or (Any Time): "+RESET_COLOR);
        setPreferredHour(scanner.nextLine());
        logger.log(Level.INFO,"Describe the installing location ( City-Street-Building Name-Floor number\"): "+RESET_COLOR);
        setLocationInstalling(scanner.nextLine());
        setStatusInstalling("pending");
        randomNumberGenerator();
        ifRandomNumberGeneratorNotFound();
        dataTrueOrNO();
    }
    public void  dataTrueOrNO() {
        if (ifDataTrue()) {
            checkIfDayAndHourAppropriate(getPreferredDate(), getPreferredHour());
            if (installerAvailableToCustomer || getPreferredHour().equals("Any Time") || getPreferredDate().equals("Any Day")) {
                addThisInstallerRequest();
                logger.log(Level.INFO, BOLD + "\u001B[34m The request sent to installation to conform, " +
                        "You will be contacted via email and phone," +
                        "and the time and day will be confirmed if appropriate to the installer " + RESET_COLOR);
            }
            else {
                logger.log(Level.WARNING, BOLD + "\u001B[31m The installer Not available enter Another day and Hour." + RESET_COLOR);
                enterNewDayAndTime();
            }
        }

        else{
            logger.log(Level.WARNING, BOLD + "\u001B[31m The information you entered is incomplete. Re-enter the information and fill it correctly." + RESET_COLOR);
            enterDataOfRequest();
        }

    }

    public  void enterNewDayAndTime() {
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Select the preferred date for this service like (1/1/2022) or (Any Day): "+RESET_COLOR);
        setPreferredDate(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred Hour of this date like (10:00 AM) or (Any Time): "+RESET_COLOR);
        setPreferredHour(scanner.nextLine());
        dataTrueOrNO();
    }

    public void addThisInstallerRequest() {
        RandomAccessFile file=null;
        data = getIdInstallerRequest()+","+getIdCustomer()+","+getPhoneCustomer()+","+getCustomerName()+","+getGmail()+","+getProduct()+","+getPreferredDate()+","+getPreferredHour()+","+getLocationInstalling()+","+getStatusInstalling()+","+getCompletionDate()+"\n";
        try {
            file = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw");
            file.seek(file.length());
            file.writeBytes(data);

            file.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
            }}
    }
    /////////////////////////////////////////////////////////////////////////////////////////




















































    public void setAddress(String address) {
        Installer.address = address;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        Installer.idCustomer = idCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        Installer.phoneCustomer = phoneCustomer;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        Installer.gmail = gmail;
    }

    public static  String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {Installer.customerName = customerName;
    }

    public static String address,idCustomer,phoneCustomer,gmail,customerName;


    public void setInstallerAvailableToCustomer(boolean installerAvailableToCustomer) {
        this.installerAvailableToCustomer = installerAvailableToCustomer;
    }
    public  boolean installerAvailableToCustomer;


    public void checkIfDayAndHourAppropriate(String dateDay, String hour) {
        numberOfLine=-1;
        setInstallerAvailableToCustomer(true);
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                setNumberOfLine(numberOfLine+1);
                String[] productInfo = s.split(",");
                if(productInfo[9].equals("scheduled") && productInfo[6].equals(dateDay)&& productInfo[7].equals(hour)) {
                    setInstallerAvailableToCustomer(false);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putDay(String anyDay) {
        order.deleteOrder2("requestInstallation",getNumberOfLine());
        checkIfDayAndHourAppropriate(anyDay,getPreferredHour());
        dataTrueOrNO();
    }

    public void putTime(String anyTime) {
        order.deleteOrder2("requestInstallation",getNumberOfLine());
        checkIfDayAndHourAppropriate(getPreferredDate(),anyTime);
        dataTrueOrNO();
        //   addThisInstallerRequest();

    }
    public void putDayAndTime(String anyDay,String time) {
        setPreferredDate(anyDay);
        setPreferredHour(time);
        setChangeTimeOrHour(true);
    }

    public boolean isChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(boolean changeStatus) {
        this.changeStatus = changeStatus;
    }
    public boolean changeStatus;
    public void changeStatus(){
        setChangeStatus(true);
    }



    public int getNumberOfLine() {
        return numberOfLine;
    }
    public void setNumberOfLine(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }
    public int numberOfLine;
    public void setDAta(long idI,String status){
        numberOfLine=-1;
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            String id = String.valueOf(idI);
            while ((s = ref.readLine()) != null) {
                setNumberOfLine(numberOfLine+1);
                String[] productInfo = s.split(",");
                if(productInfo[0].equals(id) && productInfo[9].equals(status)) {
                    listPrint.add(productInfo[0]); setIdInstallerRequest(Long.parseLong(listPrint.get(0)));
                    listPrint.add(productInfo[1]); setIdCustomer(listPrint.get(1));
                    listPrint.add(productInfo[2]); setPhoneCustomer(listPrint.get(2));
                    listPrint.add(productInfo[3]); setCustomerName(listPrint.get(3));
                    listPrint.add(productInfo[4]); setGmail(listPrint.get(4));
                    listPrint.add(productInfo[5]); setProduct(listPrint.get(5));
                    listPrint.add(productInfo[6]); setPreferredDate(listPrint.get(6));
                    listPrint.add(productInfo[7]); setPreferredHour(listPrint.get(7));
                    listPrint.add(productInfo[8]); setLocationInstalling(listPrint.get(8));
                    listPrint.add(productInfo[9]); setStatusInstalling(listPrint.get(9));
                    listPrint.add(productInfo[10]); setCompletionDate(listPrint.get(10));

                    printDataToAdmin();
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
