package org.example;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SignIn {
    Order order = new Order();
    public int getNumberOfLine() {return numberOfLine;} private int numberOfLine;
    public String getAdminMenuName() {return adminMenuName;}
    public void setAdminMenuName(String adminMenuName) {this.adminMenuName = adminMenuName;}
    public String adminMenuName; public String customerName;
    Admin admin = new Admin(); Customer customer = new Customer(); Installer installer = new Installer();static Logger logger = Logger.getLogger(Registration.class.getName());
    private Scanner scanner; public   String installerName,name;
    private String email;
    private String password;
    private String TrueEmail;
    private String TruePassword;
    public void setTheUser(int theUser) {this.theUser = theUser;}
    private  int theUser,id;
    public boolean adminloged,customerLogin,installerLogin;
    public boolean getCustomerLogin() {
        return customerLogin;
    }
    public void setCustomerLogin(boolean customerLogin) {
        this.customerLogin = customerLogin;
    }
    public boolean getInstallerLogin() {
        return installerLogin;
    }
    public void setInstallerLogin(boolean installerLogin) {
        this.installerLogin = installerLogin;
    }
    public int getTheUser() {return theUser;}
    public void AdminInInSystem(){admin.setAdminLogin(getAdminloged());}
    public void CustomerInInSystem(){
        customer.setCustomerLogin(getCustomerLogin());
    }
    public void installerInSystem(){
        installer.setInstallerLogin(getInstallerLogin());
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public void setAdminloged(boolean adminloged) {this.adminloged = adminloged;}
    public boolean getAdminloged() {
        return adminloged;}
    public SignIn(){
        theUser = 0; adminloged = false; customerLogin = false; installerLogin = false;}
    public  void customerIslLogin(String email, String password) {numberOfLine=-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;while ((s = ref.readLine()) != null) {
                numberOfLine = numberOfLine+1;
                String[] loginCustomer = s.split(",");
                customerName=loginCustomer[0];
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && TruePassword.equals(password)) {
                    order.setGmailIs(email);
                    setCustomerLogin(true);
                    CustomerInInSystem();
                    customer.setTheCustomerIs(numberOfLine);

                    return;}}
            notFoundEmailCustomer(email,password);
        } catch (IOException e) {
            throw new RuntimeException(e);}}
    public void notFoundEmailCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];
                if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                    notFoundPasswordCustomer(email,password);
                    return;}}logger.log(Level.WARNING,"Customer Email is Wrong! Try Again");

        } catch (IOException e) {
            throw new RuntimeException(e);}}
    public void notFoundPasswordCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];
                if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                    logger.log(Level.WARNING,"Customer password is Wrong! Try Again ");
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        public void AdminLogin(String email, String password) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    setAdminMenuName(loginCustomer[0]);
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];
                    if (TrueEmail.equals(email) && TruePassword.equals(password)) {
                        setAdminloged(true);
                        AdminInInSystem();
                        return;}}
                AdminWrongEmail(email,password);
            } catch (IOException e) {throw new RuntimeException(e);}}
        public void AdminWrongEmail(String email, String password) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];

                    if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                        AdminWrongPass(email,password);
                        return;}}
                logger.log(Level.WARNING,"Admin Email is Wrong! Try Again");} catch (IOException e) {
                throw new RuntimeException(e);}}
        public void AdminWrongPass(String email, String password) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];
                    if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                        logger.log(Level.WARNING,"Admin password is Wrong! Try Again ");
                        return;}}} catch (IOException e) {throw new RuntimeException(e);}}
        public void installerIsLogin(String enterEmail, String enterPassword) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    installerName = loginCustomer[0];
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];
                    if (TrueEmail.equals(enterEmail) && TruePassword.equals(enterPassword)) {
                        setInstallerLogin(true);
                        installerInSystem();
                        return;}}
                installerWrongEmail(enterEmail,enterPassword);} catch (IOException e) {
                throw new RuntimeException(e);}}
        public void installerWrongEmail(String enterEmail, String enterPassword) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];

                    if (TrueEmail.equals(enterEmail) && !TruePassword.equals(enterPassword)) {
                        installerWrongPassword(enterEmail,enterPassword);
                        return;
                    }
                }
                logger.log(Level.WARNING,"installer Email is Wrong! Try Again");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void installerWrongPassword(String enterEmail, String enterPassword) {try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {String s;while ((s = ref.readLine()) != null) {String[] loginCustomer = s.split(",");TrueEmail = loginCustomer[1];TruePassword = loginCustomer[2];if (TrueEmail.equals(enterEmail) && !TruePassword.equals(enterPassword)) {logger.log(Level.WARNING, "installer password is Wrong! Try Again ");return;}}} catch (IOException e) {throw new RuntimeException(e);}}
        public String enterEmail(){scanner = new Scanner(System.in);logger.log(Level.INFO,"\u001B[32m" + "Enter the email:" + "\u001B[0m");return scanner.nextLine();}
        public String enterPass(){scanner = new Scanner(System.in);logger.log(Level.INFO,"\u001B[32m"+ "Enter the password:" + "\u001B[0m");return scanner.nextLine();}}