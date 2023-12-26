package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.example.Registration.logger;

public class Installer {
    String data ;
    Order order = new Order();
    Gmail gmailSend = new Gmail();
    public   String[] arrayOfTopic =  {"Installation confirmation", "installer not available", "Cancel Installation Request","Task finished"}; // Creating an array that can hold 3 strings
    public   String[] arrayOfMsg = {"The Installation has been confirmed","We are sorry, but the Installation Request has been canceled due to logistical restrictions beyond our store's control","Thank you for using our company"}; // Creating an array that can hold 3 strings
    private String first;
    private static final Logger logger = Logger.getLogger(Installer.class.getName());

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
    public boolean idInstallationFlag;
    public long idInstallerRequest;
    public String preferredDate;
    public String preferredHour;
    public String product;
    public String locationInstalling;
    public String statusInstalling;
    public String completionDate;
    ///////////////////////////installer Data /////////////
    public String getInstallerName() {
        return installerName;
    }
    public void   setInstallerName(String setInstallerName) {
        this.installerName = setInstallerName;
    }
    public String getInstallerAvailable() {
        return installerAvailable;
    }
    public void   setInstallerAvailable(String installerAvailable) {
        this.installerAvailable = installerAvailable;
    }
    public String installerName;
    public String installerAvailable;

    ///////////////////////////////////////////////////////////////
    ArrayList<String> listPrint = new ArrayList<>();


     private Random random = new Random();

    public void randomNumberGenerator() {
        long min = 1000000000L; // Minimum 10-digit number
        long max = 9999999999L; // Maximum 10-digit number

        long randomNum = min + ((long) (random.nextDouble() * (max - min)));
        setIdInstallerRequest(randomNum);
    }
    public void ifRandomNumberGeneratorNotFound() {

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
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
        this.viewRequestsFlag = viewRequestsFlag;
    }

    private boolean  viewRequestsFlag;

    public boolean isScheduleAppointmentFlag() {
        return scheduleAppointmentFlag;
    }

    public void setScheduleAppointmentFlag(boolean scheduleAppointmentFlag) {
        this.scheduleAppointmentFlag = scheduleAppointmentFlag;
    }

    private boolean scheduleAppointmentFlag;
    public boolean isInstallerLogin() {
        return InstallerLogin;
    }

    public void setInstallerLogin(boolean installerLogin) {
        this.InstallerLogin = installerLogin;
    }

    private  boolean InstallerLogin;

    public void installer_menu(String installerName) {
       setInstallerName(installerName);
        setViewRequestsFlag(false);
        setScheduleAppointmentFlag(false);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[35m" + "------- Welcome  " + installerName +"  ------\n"+
                "|                                  |\n"+
                "|     1.View requests history.     |\n"+
                "|     2.schedule appointment.      |\n"+
                "|                                  |\n"+
                " ---------------------------------- \n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[37m");

        choice = scanner.nextInt();
        if (choice == 1) {
            setViewRequestsFlag(true);
            userAccountMenu();
        } else if (choice == 2) {
            setScheduleAppointmentFlag(true);
            userAccountMenu();

        } else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
            installer_menu(installerName);
        }
    }
    public void userAccountMenu(){
        if (isViewRequestsFlag()){
            showAllInstallationRequestToAdminANDInstaller("completed");
            installer_menu(getInstallerName());
        }
        else if (isScheduleAppointmentFlag()) {
            scheduleAppointment_menu();
            installer_menu(getInstallerName());
        }

    }

    public void scheduleAppointment_menu(){
        setCompletionDate("--");
        int choice;
        Scanner scanner = new Scanner(System.in);
        long id;
        logger.log(Level.INFO,"\n\u001B[36m" + "--------------- appointment scheduling ---------------"+"\n"+
                "|                                                           |\n"+
                "|     1. Approval of the request and sending an email.      |\n"+
                "|     2. The installer is not available.                    |\n"+
                "|     3. Cancelling installation requests.                  |\n"+
                "|     4. Completion of the request.                         |\n"+
                "|     5. View scheduled and incomplete requests.            |\n"+
                "|                                                           |\n"+
                "-------------------------------------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            showAllInstallationRequestToAdminANDInstaller("pending");
            enterID();
            approvalOfTheRequestFromAdminOrInstaller(getIdInstallerRequest());
        } else if (choice == 2) {
            showAllInstallationRequestToAdminANDInstaller("pending");
            enterID();
            setDAta(getIdInstallerRequest(),"pending");
            sendEmailNewDayHour();
        }
        else if (choice == 3) {
            showAllInstallationRequestToAdminANDInstaller("pending");
            enterID();
            setDAta(getIdInstallerRequest(),"pending");
            order.deleteOrder2("requestInstallation",getNumberOfLine());
            gmailSend.sendEmail(getGmail(),arrayOfTopic[2],arrayOfMsg[1]);
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[35m Cancelling installation requests successfully" + "\u001B[0m");

        }
        else if (choice ==4) {
            showAllInstallationRequestToAdminANDInstaller("scheduled");
            enterID();
            setDAta(idInstallerRequest,"scheduled");
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[33m Did you accomplish this task? " + "\u001B[0m");
            if(yesOrNo()==1){
                order.deleteOrder2("requestInstallation",getNumberOfLine());
                setStatusInstalling("completed");
                setCompletionDate(getPreferredDate());
                addThisInstallerRequest();
                gmailSend.sendEmail(getGmail(),arrayOfTopic[3],arrayOfMsg[2]);
                setCompletionDate("--");
            }
            else {
                scheduleAppointment_menu();
            }
        }
        else if (choice == 5) {
            showAllInstallationRequestToAdminANDInstaller("scheduled");


        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }

    private void sendEmailNewDayHour() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[35m" + "------- Welcome  " + installerName +"  ------\n"+
                "|                                  |\n"+
                "|     1. Send new day and hour.    |\n"+
                "|     2. Change day.               |\n"+
                "|     3. Change Hour.              |\n"+
                "|                                  |\n"+
                " ---------------------------------- \n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[37m");
        Scanner scanner1 = new Scanner(System.in);
        choice = scanner.nextInt();
        if (choice == 1) {
            String msg1 ;
            logger.log(Level.INFO,"\u001B[1m"+"\u001B[32m Write a message to the customer about the new appointment "+"\u001B[0m");
            msg1=scanner1.nextLine();
            gmailSend.sendEmail(getGmail(),arrayOfTopic[1],msg1);
            logger.log(Level.INFO,"\u001B[1m"+"\u001B[32m The email was sent successfully"+"\u001B[0m");

        } else if (choice == 2) {
            logger.log(Level.INFO,"\u001B[1m"+"\u001B[32m Write a new day to schedule"+"\u001B[0m");
            setPreferredDate(scanner1.nextLine());
            putDay(getPreferredDate());
        }
        else if (choice == 3) {
            logger.log(Level.INFO,"\u001B[1m"+"\u001B[32m Write a new hour to schedule "+"\u001B[0m");
            setPreferredHour(scanner1.nextLine());
            putTime(getPreferredHour());
        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
            installer_menu(installerName);
        }
    }

    public boolean isChangeTimeOrHour() {
        return changeTimeOrHour;
    }

    public void setChangeTimeOrHour(boolean changeTimeOrHour) {
        this.changeTimeOrHour = changeTimeOrHour;
    }

    public boolean changeTimeOrHour;

    public void enterID() {
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Enter The id Of installation requests: "+"\u001B[0m");
        setIdInstallerRequest(scanner.nextLong());
    }


    public void menuInstallerAdmin() {
        setCompletionDate("--");
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[36m" + "------- Manage Installer Accounts ------"+"\n"+
                "|                                      |\n"+
                "|     1. View Installer accounts.      |\n"+
                "|     2. Edit Installer Account.       |\n"+
                "|                                      |\n"+
                "----------------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            showInstallerAccount();
        } else if (choice == 2) {
            changeInstallerAccountMenu();
        }

        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }

    private void changeInstallerAccountMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        logger.log(Level.INFO,"\n\u001B[36m" + "----- Admin Profile -----"+"\n"+
                "|   1. edit userName   |\n"+
                "|   2. edit Password   |\n"+
                "|   3. edit Gmail      |\n"+
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
        }
        else if (choice ==2) {
            logger.log(Level.INFO,"Enter The old password:"+"\u001B[0m");
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO,"Enter The new password:"+"\u001B[0m");
            newPass = scanner1.nextLine();
            logger.log(Level.INFO,"Confirm The  password:"+"\u001B[0m");
            newPassCon = scanner1.nextLine();
            editePassword(oldPass,newPass,newPassCon);
           // editAdminProfile();

        }
        else if (choice ==3) {
            logger.log(Level.INFO,"Enter The new Gmail:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
             editeGmail(choice2);
            logger.log(Level.INFO,"The Gmail has been changed successfully"+"\u001B[0m");
        //    editAdminProfile();

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
    private void editeGmail(String choice2) {
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

    public String sec;
    public String third;
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


    //////////////////////////////////////menu Customer Serves//////////////////////////////////
  public void installerServicesMenu() {
      setCompletionDate("--");
      int choice;
      long choice1;
      Scanner scanner = new Scanner(System.in);
      logger.log(Level.INFO,"\n\u001B[36m" + "----------- Installation Services ----------"+"\n"+
              "|                                                     |\n"+
              "|     1. Request an installation service              |\n"+
              "|     2. View installation requests history.          |\n"+
              "|     3. Cancel pending installation requests         |\n"+
              "|                                                     |\n"+
              "------------------------------------------------------\n");
      logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
      choice = scanner.nextInt();
      if (choice == 1) {
       enterDataOfRequest();
      } else if (choice == 2) {
         showAllInstallationRequest();
      }
      else if (choice == 3) {
          showAllInstallationRequestPending();
          logger.log(Level.INFO,"Enter the installation requests ID To Cancel it : "+"\u001B[0m");
          setIdInstallerRequest(scanner.nextLong());
          ifExitIdInstallerRequestPending();
          cancelIt();
      }
      else {
          logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
      }
  }

    public void cancelIt() {
        if(isIdInstallationFlag()){
            order.deleteOrder2("requestInstallation",getNumberOfLine());
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[36m The installation requests canceled."+"\u001B[0m");

        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31m The installation requests ID Not Found."+"\u001B[0m");
            installerServicesMenu();
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
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ifExitIdInstallerRequestPendingToAdmin() {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
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
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void showAllInstallationRequestToAdminANDInstaller(String status) {
        boolean dv =false;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
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
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printDataToAdmin() {
        logger.info("\u001B[34m The ID Of Installation Request :\u001B[35m " +listPrint.get(0)+" |"
                +"\u001B[34m ID Customer :\u001B[35m "+listPrint.get(2)+" |"
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
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
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
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllInstallationRequestPending() {
        boolean dv =false;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
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
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
    ///////////////////////////////////////////////////////////////////////////////////////////

 ///////////////////////////////////////// enterDataOfRequest()////////////////////////////
    public void  enterDataOfRequest(){
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Describe the product you need service for : "+"\u001B[0m");
        setProduct(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred date for this service like (1/1/2022) or (Any Date): "+"\u001B[0m");
        setPreferredDate(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred Hour of this date like (10:00 AM) or (Any Time): "+"\u001B[0m");
        setPreferredHour(scanner.nextLine());
        logger.log(Level.INFO,"Describe the installing location ( City-Street-Building Name-Floor number\"): "+"\u001B[0m");
        setLocationInstalling(scanner.nextLine());
        setStatusInstalling("pending");
        randomNumberGenerator();
        ifRandomNumberGeneratorNotFound();
        //ifDataTrue();
        dataTrueOrNO();
    }

    public  boolean ifFilledTheDateNull(){
        return !getPreferredDate().equals("");
    }
    public  boolean ifFilledTheHourNull(){
        return !getPreferredHour().equals("");

    } public  boolean ifFilledTheProductNull(){
        return !getProduct().equals("");

    } public  boolean ifFilledTheLocationNull(){
        return !getLocationInstalling().equals("");
    } public  boolean ifFilledTheAnyDayAndAnyTime(){
        return getPreferredDate().equals("Any Day") && getPreferredHour().equals("Any Time");
    }
    public boolean ifDataTrue() {
    if((ifFilledTheProductNull() && ifFilledTheDateNull() && ifFilledTheHourNull()&&ifFilledTheLocationNull())
            || (ifFilledTheProductNull()&& ifFilledTheAnyDayAndAnyTime()) &&ifFilledTheLocationNull())
        return true;

    else
       return false;
    }

    public void ifAnyDayAnyTime(String preferredDate,String preferredHour){
        setInstallerAvailableToCustomer(true);

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[9].equals("scheduled") && productInfo[6].equals(preferredDate)&& productInfo[7].equals(preferredHour)) {
                    setInstallerAvailableToCustomer(false);
                }
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void  dataTrueOrNO() {
        if (ifDataTrue()) {
            checkIfDayAndHourAppropriate(getPreferredDate(), getPreferredHour());
            if (installerAvailableToCustomer || getPreferredHour().equals("Any Time") || getPreferredDate().equals("Any Day")) {
                addThisInstallerRequest();
                logger.log(Level.INFO, "\u001B[1m" + "\u001B[34m The request sent to installation to conform, " +
                        "You will be contacted via email and phone," +
                        "and the time and day will be confirmed if appropriate to the installer " + "\u001B[0m");
            }
         else {
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The installer Not available enter Another day and Hour." + "\u001B[0m");
            enterNewDayAndTime();
        }
    }

        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The information you entered is incomplete. Re-enter the information and fill it correctly." + "\u001B[0m");
            enterDataOfRequest();
        }

    }

   public  void enterNewDayAndTime() {
       Scanner scanner = new Scanner(System.in);
       logger.log(Level.INFO,"Select the preferred date for this service like (1/1/2022) or (Any Day): "+"\u001B[0m");
       setPreferredDate(scanner.nextLine());
       logger.log(Level.INFO,"Select the preferred Hour of this date like (10:00 AM) or (Any Time): "+"\u001B[0m");
       setPreferredHour(scanner.nextLine());
       dataTrueOrNO();
    }

   public void addThisInstallerRequest() {
        RandomAccessFile file=null;
        data = getIdInstallerRequest()+","+getIdCustomer()+","+getPhoneCustomer()+","+getCustomerName()+","+getGmail()+","+getProduct()+","+getPreferredDate()+","+getPreferredHour()+","+getLocationInstalling()+","+getStatusInstalling()+","+getCompletionDate()+"\n";
        try {
             file = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw");
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




















































  /////////////////////////////////////////////Data of Customer //////////////////////////////
  public String getAddress() {
      return address;
  }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String address;
    public String idCustomer;
    public String phoneCustomer;
    public String gmail;
    public String customerName;
 // 0              1    2          3        4    5        6          7             8               9                   10
 // id installer +id + customer + phone + name + email + product + preferred date+ preferred day+installing location + status
    ///////////////////////////////////////////////////////////////////////////////////////////


    public boolean isInstallerAvailableToCustomer() {
        return installerAvailableToCustomer;
    }
    public void setInstallerAvailableToCustomer(boolean installerAvailableToCustomer) {
        this.installerAvailableToCustomer = installerAvailableToCustomer;
    }
    public  boolean installerAvailableToCustomer;


    public void checkIfDayAndHourAppropriate(String dateDay, String hour) {
        numberOfLine=-1;
        setInstallerAvailableToCustomer(true);
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                setNumberOfLine(numberOfLine+1);
                String[] productInfo = s.split(",");
                if(productInfo[9].equals("scheduled") && productInfo[6].equals(dateDay)&& productInfo[7].equals(hour)) {
                         setInstallerAvailableToCustomer(false);
                }
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putDay(String anyDay) {
       order.deleteOrder2("requestInstallation",getNumberOfLine());
       checkIfDayAndHourAppropriate(anyDay,getPreferredHour());
        dataTrueOrNO();
      // addThisInstallerRequest();
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
    public void approvalOfTheRequestFromAdminOrInstaller(long idInstallerRequest){
        setDAta(idInstallerRequest,"pending");
        logger.log(Level.WARNING, "\u001B[1m" + "\u001B[34m Do you agree to this request? " + "\u001B[0m");
        if(yesOrNo()==1){
           order.deleteOrder2("requestInstallation",getNumberOfLine());
           setStatusInstalling("scheduled");
           addThisInstallerRequest();
           gmailSend.sendEmail(getGmail(),arrayOfTopic[0],arrayOfMsg[0]);
        }
        else {
            scheduleAppointment_menu();
        }

    }
    public int yesOrNo (){
        Scanner scanner = new Scanner(System.in);
        int choice;
        logger.log(Level.INFO, """
            
            \u001B[35m---------------------------------
            |                                |
            |          1. yes                |
            |          2. no                 |
            |                                |
            ----------------------------------
            """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        return choice;
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
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/requestInstallation.txt", "rw")) {
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
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
