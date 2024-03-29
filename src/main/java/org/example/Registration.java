package org.example;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Registration {
    static Logger logger = Logger.getLogger(Registration.class.getName());
    private String name;
    private String email;
    private String password;
    private String comPassword;
    private String address;
    private int id;
    private int phone;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isIfEmailFound() {
        return ifEmailFound;
    }

    public void setIfEmailFound(boolean ifEmailFound) {
        this.ifEmailFound = ifEmailFound;
    }
    private boolean ifEmailFound;

    public boolean getCustomerRegistrationCompleted() {
        return customerRegistrationCompleted;
    }
    public void setCustomerRegistrationCompleted(boolean customerRegistrationCompleted) {
        this.customerRegistrationCompleted = customerRegistrationCompleted;
    }
    private boolean customerRegistrationCompleted;
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
    public String getComPassword() {
        return comPassword;
    }
    public void setComPassword(String comPassword) {
        this.comPassword = comPassword;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Registration() {
        customerRegistrationCompleted = false;
        ifEmailFound = false;
    }
    public void storeDataToFile(String dataToWrite) {
        RandomAccessFile file =null;
        try {
            file = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw");
            file.seek(file.length());
            file.writeBytes(dataToWrite);
            file.close();
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        finally {
            if (file != null) {
                try
                {
                    file.close();
                }
                catch (IOException e) {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
        }
    }
    public boolean truepass (String pass, String ConfirmPass){
        return pass.equals(ConfirmPass);
    }
    public void isCustomerRegistrationCompleted (String pass, String conformPass){
        setCustomerRegistrationCompleted(false);
        if((truepass(pass,conformPass))){
            setCustomerRegistrationCompleted(true);
        }
    }
    public void searchIfEmailIsAlreadyExist(String enteredEmail) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                String trueEmail = loginCustomer[1];
                if (trueEmail.equals(enteredEmail)) {
                    setIfEmailFound(true);
                    return;
                }
            }
            setIfEmailFound(false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
