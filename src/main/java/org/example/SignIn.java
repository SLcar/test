package org.example;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SignIn {
    Order order = new Order();
    public int getNumberOfLine() {
        return numberOfLine;
    }

    private int numberOfLine;
    private String adminMenu;
    private String customerName;
    Admin admin = new Admin();
    Customer customer = new Customer();
    Installer installer = new Installer();
    static Logger logger = Logger.getLogger(Registration.class.getName());
    private Scanner scanner;
    private  String installerName;
    private String name;
    private String email;
    private String password;
    private String TrueEmail;
    private String TruePassword;
    private int theUser;
    private int id;
    private boolean adminloged;
    private boolean customerLogin;
    private boolean installerLogin;

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


    public int getTheUser() {
        return theUser;
    }

    public void setTheUser(int theUser) {
        this.theUser = theUser;
    }


    public void AdminInInSystem(){
        admin.setAdminLogin(getAdminloged());
    }
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdminloged(boolean adminloged) {

        this.adminloged = adminloged;
    }

    public boolean getAdminloged() {

        return adminloged;
    }
    public SignIn(){
        theUser = 0;
        adminloged = false;
        customerLogin = false;
        installerLogin = false;
    }

    public void customerIslLogin(String email, String password) {
        numberOfLine=-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
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
                    return;
                }
            }
            notFoundEmailCustomer(email,password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void notFoundEmailCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                    notFoundPasswordCustomer(email,password);
                    return;
                }
            }
            logger.log(Level.WARNING,"Customer Email is Wrong! Try Again");
             whoIsLogin();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void notFoundPasswordCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                TrueEmail = loginCustomer[1];
                TruePassword = loginCustomer[2];

                if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                    logger.log(Level.WARNING,"Customer password is Wrong! Try Again ");
                    whoIsLogin();
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
                    adminMenu = loginCustomer[0];
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];

                    if (TrueEmail.equals(email) && TruePassword.equals(password)) {
                        setAdminloged(true);
                        AdminInInSystem();
                        return;

                    }
                }

                AdminWrongEmail(email,password);

            } catch (IOException e) {


                throw new RuntimeException(e);

            }
        }

        public void AdminWrongEmail(String email, String password) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];

                    if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                        AdminWrongPass(email,password);
                        return;
                    }
                }
                logger.log(Level.WARNING,"Admin Email is Wrong! Try Again");
                whoIsLogin();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }
        public void AdminWrongPass(String email, String password) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/AdminData.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];

                    if (TrueEmail.equals(email) && !TruePassword.equals(password)) {
                        logger.log(Level.WARNING,"Admin password is Wrong! Try Again ");
                        whoIsLogin();
                        return;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }}

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
                        return;

                    }
                }

                installerWrongEmail(enterEmail,enterPassword);

            } catch (IOException e) {


                throw new RuntimeException(e);

            }
        }
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
                whoIsLogin();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void installerWrongPassword(String enterEmail, String enterPassword) {
            try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/installer.txt", "rw")) {
                String s;
                while ((s = ref.readLine()) != null) {
                    String[] loginCustomer = s.split(",");
                    TrueEmail = loginCustomer[1];
                    TruePassword = loginCustomer[2];

                    if (TrueEmail.equals(enterEmail) && !TruePassword.equals(enterPassword)) {
                        logger.log(Level.WARNING,"installer password is Wrong! Try Again ");
                        whoIsLogin();
                        return;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        public String enterEmail(){
            scanner = new Scanner(System.in);
            logger.log(Level.INFO,"\u001B[32m" + "Enter the email:" + "\u001B[0m");
            return scanner.nextLine();
        }
        public String enterPass(){
            scanner = new Scanner(System.in);
            logger.log(Level.INFO,"\u001B[32m"+ "Enter the password:" + "\u001B[0m");
            return scanner.nextLine();
        }

        public void loginMenu (){
            int  choice1 ;
            Scanner scanner1 = new Scanner(System.in);
            logger.log(Level.INFO, "\n\u001B[36m------ Welcome to login Page ------"+"\n"+
                    "|                                |\n" +
                    "|          1. Admin              |\n"+
                    "|          2. Custumor           |\n"+
                    "|          3. Installer          |\n"+
                    "|                                |\n"+
                    "----------------------------------\n");
            logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
            choice1 = scanner1.nextInt();
            if (choice1 == 1){
                setTheUser(1);
                whoIsLogin();
            }
            else if (choice1 ==2){
                setTheUser(2);
                whoIsLogin();
            }
            else if (choice1==3) {
                setTheUser(3);
                whoIsLogin();

            }
            else{
                logger.log(Level.WARNING,"Invalid choice! Please enter a valid choice.");
            }
        }

        public void whoIsLogin(){
            if(getTheUser() == 1){
                String enterEmail = enterEmail();
                String enterPassword = enterPass();
                AdminLogin(enterEmail,enterPassword);
                if (adminloged)
                    admin.adminMenu(adminMenu);
            }

            else if (getTheUser() == 2) {
                String enterEmail = enterEmail();
                String enterPassword = enterPass();
                customerIslLogin(enterEmail,enterPassword);
                if(customerLogin) {
                    customer.Customer_menu(customerName);
                    customer.setTheCustomerIs(getNumberOfLine());
                }
            }   else if (getTheUser() == 3) {
                String enterEmail = enterEmail();
                String enterPassword = enterPass();
                installerIsLogin(enterEmail,enterPassword);
                if(installerLogin)
                    installer.installer_menu(installerName);
            }

        }
    }
