package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.SecureRandom;

public class Installer extends RuntimeException {
    public Installer(){

    }

    public Installer(String message, Throwable cause) {
        super(message, cause);

    }


    public static final String SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT = "src/main/resources/Data/installer.txt";
    String data ;
    Order order = new Order();


    static String first;
    public static final Logger logger = Logger.getLogger(Installer.class.getName());
    public static final String ENTER_CHOICE_MESSAGE = "Enter your choice: ";
    public static final String BOLD = "\u001B[1m";
    public static final String RESET_COLOR = "\u001B[0m";


    public long getIdInstallerRequest() {
        return idInstallerRequest;
    }
    public void setIdInstallerRequest(long idInstallerRequest) {
       this.idInstallerRequest = idInstallerRequest;
    }
    public String getPreferredDate() {
        return preferredDate;
    }
    public void setPreferredDate(String preferredDate) {
        this.preferredDate = preferredDate;
    }
    public String getPreferredHour() {
        return preferredHour;
    }
    public void setPreferredHour(String preferredHour) {
        this.preferredHour = preferredHour;
    }
    public String getLocationInstalling() {
        return locationInstalling;
    }
    public void setLocationInstalling(String locationInstalling) {
        this.locationInstalling = locationInstalling;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getStatusInstalling() {
        return statusInstalling;
    }
    public void setStatusInstalling(String statusInstalling) {
        this.statusInstalling = statusInstalling;
    }
    public String getCompletionDate() {
        return completionDate;
    }
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public boolean isIdInstallationFlag() {
        return idInstallationFlag;
    }

    public void setIdInstallationFlag(boolean idInstallationFlag) {
       this.idInstallationFlag = idInstallationFlag;
    }
    boolean idInstallationFlag = false;
    long  idInstallerRequest =0;
    String preferredDate = null;
    String preferredHour;
    String product;
    String locationInstalling;
    String statusInstalling;
    String completionDate;
    public static final String REQUEST_INSTALLATION_FILE_PATH = "src/main/resources/Data/requestInstallation.txt";
    public  String getInstallerName() {
        return installerName;
    }
    public   void   setInstallerName(String setInstallerName) {
        installerName = setInstallerName;
    }
    String installerName;
    ArrayList<String> listPrint = new ArrayList<>();

    public boolean isTheDataCancel() {
        return theDataCancel;
    }

    public  void setTheDataCancel(boolean theDataCancel) {
        this.theDataCancel = theDataCancel;
    }

    boolean theDataCancel;
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
            throw new Installer("Error during customer login", e);
        }
    }



   public boolean isViewRequestsFlag() {
        return viewRequestsFlag;
    }

    public  void setViewRequestsFlag(boolean viewRequestsFlag) {
        this.viewRequestsFlag = viewRequestsFlag;
    }

    boolean  viewRequestsFlag;

    public boolean isScheduleAppointmentFlag() {
        return scheduleAppointmentFlag;
    }

    public void setScheduleAppointmentFlag(boolean scheduleAppointmentFlag) {
        this.scheduleAppointmentFlag = scheduleAppointmentFlag;
    }

     boolean scheduleAppointmentFlag;
    boolean installerLogin;

    public  void setInstallerLogin(boolean installerLogin) {
        this.installerLogin = installerLogin;
    }





    public boolean isChangeTimeOrHour() {
        return changeTimeOrHour;
    }

    public  void setChangeTimeOrHour(boolean changeTimeOrHour) {
        this.changeTimeOrHour = changeTimeOrHour;
    }

    boolean changeTimeOrHour;





    public boolean truepass (String pass, String confirmPass){
        return pass.equals(confirmPass);
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
            file = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw");
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
        try (RandomAccessFile raf = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw")) {
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
        } catch (IOException e) {
            throw new Installer("Error during customer login", e);
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

    public  String sec;
    public String third;
    public void fileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw")) {
            String s;
            while ((s = raf.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                first=loginCustomer[0];
                sec=loginCustomer[1];
                third=loginCustomer[2];
            }

        } catch (IOException e) {
            throw new Installer("Error during customer login", e);
        }

    }
    public void showInstallerAccount() {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw")) {
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
            throw new Installer("Error during customer login", e);
        }
    }


    public void ifExitIdInstallerRequestPending() {
        numberOfLine= -1;
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH, "rw")) {
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
            throw new Installer("Error during customer login", e);
        }
    }
    public void ifExitIdInstallerRequestPendingToAdmin() {
        numberOfLine= -1;
        try (RandomAccessFile ref = new RandomAccessFile(REQUEST_INSTALLATION_FILE_PATH , "rw")) {
            String s;
            String IdInstallation = String.valueOf(getIdInstallerRequest());
            while ((s = ref.readLine()) != null) {
                numberOfLine=numberOfLine+1;
                System.out.println(numberOfLine);
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
            throw new Installer("Error during customer login", e);
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
            throw new Installer("Error during customer login", e);
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

    public void showAllInstallationRequestPending()  {
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
            throw new Installer("Error during customer login", e);

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
            throw new Installer("Error during customer login", e);
        }
    }

    public void  dataTrueOrNO() {
        if (ifDataTrue()) {
            checkIfDayAndHourAppropriate(getPreferredDate(), getPreferredHour());
            if (installerAvailableToCustomer || getPreferredHour().equals("Any Time") || getPreferredDate().equals("Any Day")) {
                addThisInstallerRequest();
                logger.log(Level.INFO, BOLD + "The request sent to installation to conform, " +
                        "You will be contacted via email and phone," +
                        "and the time and day will be confirmed if appropriate to the installer ");
            }
            else {
                logger.log(Level.WARNING, "The installer Not available enter Another day and Hour.");
                enterNewDayAndTime();
            }
        }

        else{
            logger.log(Level.WARNING, BOLD + "The information you entered is incomplete. Re-enter the information and fill it correctly.");
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




















































    public static void setAddress(String address) {
        Installer.address = address;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public static void setIdCustomer(String idCustomer) {
        Installer.idCustomer = idCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public static void setPhoneCustomer(String phoneCustomer) {
        Installer.phoneCustomer = phoneCustomer;
    }

    public String getGmail() {
        return gmail;
    }

    public static void setGmail(String gmail) {
        Installer.gmail = gmail;
    }

    public static  String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {Installer.customerName = customerName;
    }

    public static String address,idCustomer,phoneCustomer,gmail,customerName;


    public  void setInstallerAvailableToCustomer(boolean installerAvailableToCustomer) {
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
            throw new Installer("Error during customer login", e);
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
            throw new Installer("Error during customer login", e);
        }

    }


}
