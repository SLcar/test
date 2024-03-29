package org.example;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.Main.installer;

public class SignIn {
    public static final String SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT = "src/main/resources/Data/custumorData.txt";
    public static final String SRC_MAIN_RESOURCES_DATA_ADMIN_DATA_TXT = "src/main/resources/Data/AdminData.txt";
    public static final String SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT = "src/main/resources/Data/installer.txt";
    Order order = new Order();

    public int getNumberOfLine() {
        return numberOfLine;
    }

    private int numberOfLine;

    public String getAdminMenuName() {
        return adminMenuName;
    }

    public void setAdminMenuName(String adminMenuName) {
        this.adminMenuName = adminMenuName;
    }

    String adminMenuName;
    String customerName;
    Customer customer = new Customer();
    static Logger logger = Logger.getLogger(SignIn.class.getName());
    public String installerName;
    public String name;
    private String email;
    private String password;
    private String trueEmail;
    private String truePassword;

    public void setTheUser(int theUser) {
        this.theUser = theUser;
    }

    private int theUser;
    private int  id;
     boolean adminloged;
    boolean customerLogin;
     boolean  installerLogin;

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

    public void adminInInSystem() {
        Admin.setAdminLogin(getAdminloged());}

    public void customerInInSystem() {
        Customer.setCustomerLogin(getCustomerLogin());
    }

    public void installerInSystem() {
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

    public SignIn() {
        theUser = 0;
        adminloged = false;
        customerLogin = false;
        installerLogin = false;
    }

    public void customerIslLogin(String email, String password) {
        numberOfLine = -1;
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                numberOfLine = numberOfLine + 1;
                String[] loginCustomer = s.split(",");
                customerName = loginCustomer[0];
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];

                if (trueEmail.equals(email) && truePassword.equals(password)) {
                    order.setGmailIs(email);
                    setCustomerLogin(true);
                    customerInInSystem();
                    customer.setTheCustomerIs(numberOfLine);

                    return;
                }
            }
            notFoundEmailCustomer(email, password);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public void notFoundEmailCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];
                if (trueEmail.equals(email) && !truePassword.equals(password)) {
                    notFoundPasswordCustomer(email, password);
                    return;
                }
            }
            logger.log(Level.WARNING, "Customer Email is Wrong! Try Again");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void notFoundPasswordCustomer(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_CUSTUMOR_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];
                if (trueEmail.equals(email) && !truePassword.equals(password)) {
                    logger.log(Level.WARNING, "Customer password is Wrong! Try Again ");
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void adminLogin(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_ADMIN_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                setAdminMenuName(loginCustomer[0]);
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];
                if (trueEmail.equals(email) && truePassword.equals(password)) {
                    setAdminloged(true);
                    adminInInSystem();
                    return;
                }
            }
            adminWrongEmail(email, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void adminWrongEmail(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_ADMIN_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];

                if (trueEmail.equals(email) && !truePassword.equals(password)) {
                    adminWrongPass(email, password);
                    return;
                }
            }
            logger.log(Level.WARNING, "Admin Email is Wrong! Try Again");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void adminWrongPass(String email, String password) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_ADMIN_DATA_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];
                if (trueEmail.equals(email) && !truePassword.equals(password)) {
                    logger.log(Level.WARNING, "Admin password is Wrong! Try Again ");
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void installerIsLogin(String enterEmail, String enterPassword) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                installerName = loginCustomer[0];
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];
                if (trueEmail.equals(enterEmail) && truePassword.equals(enterPassword)) {
                    setInstallerLogin(true);
                    installerInSystem();
                    return;
                }
            }
            installerWrongEmail(enterEmail, enterPassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void installerWrongEmail(String enterEmail, String enterPassword) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];

                if (trueEmail.equals(enterEmail) && !truePassword.equals(enterPassword)) {
                    installerWrongPassword(enterEmail, enterPassword);
                    return;
                }
            }
            logger.log(Level.WARNING, "installer Email is Wrong! Try Again");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void installerWrongPassword(String enterEmail, String enterPassword) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA_INSTALLER_TXT, "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                trueEmail = loginCustomer[1];
                truePassword = loginCustomer[2];
                if (trueEmail.equals(enterEmail) && !truePassword.equals(enterPassword)) {
                    logger.log(Level.WARNING, "installer password is Wrong! Try Again ");
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}