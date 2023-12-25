package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;


import static org.example.Registration.logger;

public class Order {
    ArrayList<Integer> lines = new ArrayList<>();
  public   String[] arrayOfTopic =  {"Order confirmation", "Receiving the order", "Cancel the order"}; // Creating an array that can hold 3 strings
  public   String[] arrayOfMsg = {"The order has been confirmed", "The order was delivered successfully. Thank you for taking it from our store. We always welcome you. If there is a problem, please contact the number:059233522","We are sorry, but the order has been canceled due to logistical restrictions beyond our store's control"}; // Creating an array that can hold 3 strings

    public boolean isSendEmailConfirmation() {
        return sendEmailConfirmation;
    }

    public void setSendEmailConfirmation(boolean sendEmailConfirmation) {
        this.sendEmailConfirmation = sendEmailConfirmation;
    }

    public boolean isSendEmailReceiving() {
        return sendEmailReceiving;
    }

    public void setSendEmailReceiving(boolean sendEmailReceiving) {
        this.sendEmailReceiving = sendEmailReceiving;
    }

    public boolean isSendEmailCancel() {
        return sendEmailCancel;
    }

    public void setSendEmailCancel(boolean sendEmailCancel) {
        this.sendEmailCancel = sendEmailCancel;
    }

  public   boolean sendEmailConfirmation=false;
  public   boolean sendEmailReceiving=false;
  public   boolean sendEmailCancel=false;

    public void  ifEmailSending (String status){

        if(status.equals("shipped")){
            setSendEmailConfirmation(true);
        }
        else if (status.equals("delivered")){
            setSendEmailReceiving(true);

        } else if(status.equals("Canceled")){
            setSendEmailCancel(true);
        }
    }



    Gmail gmail = new Gmail();
    Product product = new Product();

    public int getCountOfLine() {
        return countOfLine;
    }

    public void setCountOfLine(int countOfLine) {
        this.countOfLine = countOfLine;
    }

    private int countOfLine;

    public int getNumberOfLine() {
        return numberOfLine;
    }

    public void setNumberOfLine(int numberOfLine) {
        this.numberOfLine = numberOfLine;
    }

    int numberOfLine;

    public int getNumberOfLine2() {
        return numberOfLine2;
    }

    public void setNumberOfLine2(int numberOfLine2) {
        this.numberOfLine2 = numberOfLine2;
    }

    public int getNumberOfLine3() {
        return numberOfLine3;
    }

    public void setNumberOfLine3(int numberOfLine3) {
        this.numberOfLine3 = numberOfLine3;
    }

    int numberOfLine2;
    int numberOfLine3;

    public String getDateOfReceiptOfTheOrder() {
        return dateOfReceiptOfTheOrder;
    }

    public void setDateOfReceiptOfTheOrder(String dateOfReceiptOfTheOrder) {
        this.dateOfReceiptOfTheOrder = dateOfReceiptOfTheOrder;
    }

    public String dateOfReceiptOfTheOrder;

    public String getStatusOrder() {
        return statusOrder;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String orderDate;

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public float getTotalPriceProduct() {
        return totalPriceProduct;
    }


    public void setTotalPriceProduct(float totalPriceProduct) {
        this.totalPriceProduct = totalPriceProduct;
    }
    public void fullProductPrice(){
       setTotalPriceProduct(getProductPrice() * getQuantitiesProduct());

    }


    public float totalPriceProduct;

    public long getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }




    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String CategoryName;
    public void manageOrderMenu() {
        setIfCustomerShowPendingOrder(false);
        setIfCustomerShowDeliveredOrder(false);

        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[32m" + " ------------- <3 -------------"+"\n"+
                "|                                |\n" +
                "|     1. Pending Order           |\n"+
                "|     2. Delivered Order         |\n"+
                "|                                |\n"+
                "----------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            viewPendingOrder("pending",getIdCustomer());
            userAccountMenu();

        } else if (choice == 2) {
            viewDeliveredOrder("delivered",getIdCustomer());
            userAccountMenu();

        } else {
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter a valid choice." + "\u001B[0m");
        }

    }

    private void userAccountMenu() {
        if(isIfCustomerShowPendingOrder()){
            viewPendingOrder1("pending",getIdCustomer(),getCustomerName());
            viewPendingOrder("pending",getIdCustomer());
            pendingMenu();
        }
        else if (isIfCustomerShowDeliveredOrder()) {
            viewPendingOrder1("delivered",getIdCustomer(),getCustomerName());
            viewPendingOrder("delivered",getIdCustomer());
            deliveredMenu();
        }
    }

    private void deliveredMenu() {

        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[32m" + " ------------------ <3 -----------------"+"\n"+
                "|                                      |\n" +
                "|    1. Show delivered Order Product   |\n"+
                "|                                      |\n"+
                "---------------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            logger.log(Level.INFO,"Enter The Number Of Order: "+"\u001B[0m");
            setOrderNumber(scanner.nextLong());
            ifOrderExitDelivered(String.valueOf(getOrderNumber()));
            if(isIfOrderExist()){
                viewDeliveredOrder("delivered",getIdCustomer());
                viewPendingOrderProduct(getIdCustomer(),getCustomerName(), String.valueOf(getOrderNumber()));

                manageOrderMenu();
            }

            else{
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The order Not Found" + "\u001B[0m");
                manageOrderMenu();
            }}

        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter a valid choice." + "\u001B[0m");
            manageOrderMenu();
        }}



    private void pendingMenu() {

        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        logger.log(Level.INFO,"\n\u001B[32m" + " --------------- <3 ---------------"+"\n"+
                "|                                      |\n" +
                "|     1. Show Pending Order Product    |\n"+
                "|     2. edit Pending Product          |\n"+
                "|     3. Delete Order                  |\n"+
                "|                                      |\n"+
                "---------------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            ifOrderExit(scanner);
            if(isIfOrderExist()){
                viewPendingOrderProduct(getIdCustomer(),getCustomerName(), String.valueOf(getOrderNumber()));

            }

            else{
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The order Not Found" + "\u001B[0m");

            }

        }
        else if (choice == 2) {
           ifOrderExit(scanner1);
            if(isIfOrderExist()){
                viewPendingOrderProduct(getIdCustomer(),getCustomerName(), String.valueOf(getOrderNumber()));
                extractedEdit(scanner1);

            }

            else{
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The order Not Found" + "\u001B[0m");
                pendingMenu();
            }

        } else if (choice == 3) {
            ifOrderExit(scanner1);
            if(isIfOrderExist()){

                String name=getCustomerName()+"-"+getIdCustomer();
                deleteOrder(name);

                logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m The Order is deleted Successfully" + "\u001B[0m");

            }

            else{
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The order Not Found" + "\u001B[0m");
                pendingMenu();
            }
        }
        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter a valid choice." + "\u001B[0m");
        }}
    private void editProductMenu() {
       int productID = getProductID();

        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[32m" + " --------------- <3 ---------------"+"\n"+
                "|     1. Modify the product quantity    |\n"+
                "|     2. Delete The Product             |\n"+
                "----------------------------------------\n");

        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if(choice==1){
            viewPendingOrderProduct(getIdCustomer(),getCustomerName(), String.valueOf(getOrderNumber()));
            setProductID(productID);
            logger.log(Level.INFO,"\n\u001B[32mWhat is the new quantity?");
            int newQuantity = scanner2.nextInt();
            ifQuantitiesAllowed(String.valueOf(newQuantity));
            ifQuantitiesGraterThan(newQuantity);

        } else if (choice==2) {
            viewPendingOrderProduct(getIdCustomer(),getCustomerName(), String.valueOf(getOrderNumber()));
            setProductID(productID);
            String name = getCustomerName()+"-"+getIdCustomer();
                if(ifFileOfCustomerOrderNoItem(getCustomerName(),getIdCustomer())){
                    logger.log(Level.INFO,"Do you want to cancel the order?"+"\u001B[0m");
                    logger.log(Level.INFO, """
            
            \u001B[35m---------------------
            |                       |
            |      1. YES           |
            |      2. NO            |
            |                       | 
            -------------------------
            """);


                    if (scanner2.nextInt()==1){
                        deleteOrder(name);
                        logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m The Order is canceled Successfully" + "\u001B[0m");

                    }
                        else {
                        editProductMenu();
                    }
                }
                else{
                        logger.log(Level.INFO,"Do you want to delete the product?"+"\u001B[0m");
                        logger.log(Level.INFO, """
            
            \u001B[35m---------------------
            |                       |
            |      1. YES           |
            |      2. NO            |
            |                       | 
            -------------------------
            """);
                 if(scanner2.nextInt()==1){
                    deleteThisProductFromCustomer(name,getNumberOfLine());
                    deleteThisProductFromCustomer("orderToAdmin",getNumberOfLine2());
                    deleteThisProductFromCustomer("orderAllProduct",getNumberOfLine3());
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = currentDate.format(formatter);
                    calculateTheTotalCost(getCustomerName(),getIdCustomer());
                    String product2 =getOrderNumber()+","+getCustomerName()+","+getIdCustomer()+","+getOrderPrice()+","+formattedDate+","+"--"+","+"pending"+"\n";
                    addNewOrderPending(product2);
                    logger.log(Level.INFO,"The product is deleted and the Total cost is :" +getOrderPrice() + "\u001B[0m");

                 }
                    else{
                     logger.log(Level.INFO,"The product Not deleted"+"\u001B[0m");
                        }
                }
            }

        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid value " + "\u001B[0m");

        }

    }


    public void whatSetProductPrice(String categoryName,String id) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + categoryName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String idProduct = productInfo[0];
                if (id.equals(idProduct)) {
                    setProductPrice(Integer.parseInt(productInfo[3]));

                    return ;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float productPrice;

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getFileOrderName() {
        return fileOrderName;
    }

    public void setFileOrderName(String fileOrderName) {
        this.fileOrderName = fileOrderName;
    }

    public int getQuantitiesProduct() {
        return quantitiesProduct;
    }

    public void setQuantitiesProduct(int quantitiesProduct) {
        this.quantitiesProduct = quantitiesProduct;
    }

    private String statusOrder;
    private long orderNumber;
    private String idCustomer;
    private float orderPrice;
    private String fileOrderName;
    private int quantitiesProduct;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    private int productID;

    public boolean isQuantitiesAllowedFlag() {
        return QuantitiesAllowedFlag;
    }

    public void setQuantitiesAllowedFlag(boolean quantitiesAllowedFlag) {
        QuantitiesAllowedFlag = quantitiesAllowedFlag;
    }

    private boolean QuantitiesAllowedFlag;



    public void makePurchasesMenu() {
            randomNumberGenerator();
            ifRandomNumberGeneratorNotFound();
            makePurchasesMenu1();
    }
    public void makePurchasesMenu1(){
            Scanner scanner1 = new Scanner(System.in);
            Scanner scanner2 = new Scanner(System.in);

        product.printAllCategory();
        logger.log(Level.INFO, "\u001B[35m"+"Enter The category of product to add : ");
        setCategoryName(scanner2.nextLine());
        product.ifCategoryExist(getCategoryName());
        if(product.isCategoryExistFlag()){
        product.printAllProductAndCategories(getCategoryName());
        logger.log(Level.INFO, "\u001B[36m"+"Enter The ID of product to add : ");
           setProductID(Integer.parseInt(scanner1.nextLine()));
           product.ifProductIdExist(getCategoryName(), String.valueOf(getProductID()));
        extracted1(scanner1);}
        else {
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m" + "The category Not exist.\u001B[0m");
            makePurchasesMenu1();
        }

    }

    private void extracted1(Scanner scanner1) {
        if(product.isiDExistFlag()){
            ifProductAvailable(getCategoryName(), String.valueOf(getProductID()));

            extracted4(scanner1);
        }

        else{
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m" + "The Product Not exist.\u001B[0m");
            enterAnotherProduct(scanner1);
        }
    }

    private void extracted4(Scanner scanner1) {
        if(isProductAvailable()){
            extracted3(scanner1);
        }
        else {
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m" + "The Product Not Available.\u001B[0m");
            enterAnotherProduct(scanner1);

        }
    }

    private void extracted3(Scanner scanner1) {
        returnEnterQuantities();
        whatSetProductPrice(getCategoryName(), String.valueOf(getProductID()));
        fullProductPrice();
        String product =getCategoryName()+","+getOrderNumber()+","+getIdCustomer()+","+getProductID()+","+getQuantitiesProduct()+","+getTotalPriceProduct()+"\n";
        addNewOrderToCustomer(getCustomerName(),getIdCustomer(),product);
        enterAnotherProduct(scanner1);
    }

    private void enterAnotherProduct(Scanner scanner1) {
        logger.log(Level.INFO, "\u001B[36m"+"Do u want add another product?"+"\n 1. Yes"+"\n 2. No"+"\n 3. Cancel Order");
        int yesOrNo= scanner1.nextInt();
        if(yesOrNo==1){
            makePurchasesMenu1();
        }
        else if (yesOrNo==2){

            extracted2();
        }
        else {
            logger.log(Level.INFO, "\u001B[36m"+"The Order Canceled");

        }
    }

    private void extracted2() {
        logger.log(Level.INFO, "\u001B[34m"+"The order has been added");
        calculateTheTotalCost(getCustomerName(),getIdCustomer());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        String product2 =getOrderNumber()+","+getCustomerName()+","+getIdCustomer()+","+getOrderPrice()+","+formattedDate+","+"--"+","+"pending"+"\n";
        addNewOrderPending(product2);
        logger.log(Level.INFO, "\u001B[35m"+"The Total order price is : "+getOrderPrice()+"$");
    }


    public void returnEnterQuantities(){
       Scanner scanner1 = new Scanner(System.in);
       logger.log(Level.INFO, "Enter the quantity you want of this product (the quantity is greater than 0): ");
       setQuantitiesProduct(scanner1.nextInt());
       ifQuantitiesAllowed(String.valueOf(getQuantitiesProduct()));
       if(isQuantitiesAllowedFlag()) {
           return;
       }
       else{
           logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31m The quantity is greater than 0."+"\u001B[0m");
           returnEnterQuantities();
       }
   }

    public void ifQuantitiesAllowed(String quantity) {
        setQuantitiesAllowedFlag(Integer.parseInt(quantity) > 0);
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    String customerName;

    public void addNewOrderToCustomer(String customerName,String idCustomer,String data) {
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/Data/" + customerName + "-" + idCustomer + ".txt", "rw");
            RandomAccessFile fil2 = new RandomAccessFile("src/main/resources/Data/orderAllProduct.txt", "rw");
            file.seek(file.length());
            file.writeBytes(data);
            file.close();
            fil2.seek(fil2.length());
            fil2.writeBytes(data);
            fil2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addNewOrderPending(String data) {
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw");
            file.seek(file.length());
            file.writeBytes(data);

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void randomNumberGenerator() {
        Random random = new Random();
        long min = 1000000000L; // Minimum 10-digit number
        long max = 9999999999L; // Maximum 10-digit number

        long randomNum = min + ((long) (random.nextDouble() * (max - min)));
        setOrderNumber(randomNum);
    }

    public void ifRandomNumberGeneratorNotFound() {

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String idProduct = productInfo[0];
                randomNumberGenerator();
                String id = String.valueOf(getOrderNumber());
                if (id.equals(idProduct)) {
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

    public boolean isCostCalculatedFlag() {
        return costCalculatedFlag;
    }

    public void setCostCalculatedFlag(boolean costCalculatedFlag) {
        this.costCalculatedFlag = costCalculatedFlag;
    }

    boolean costCalculatedFlag;

public void calculateTheTotalCost(String name, String numberOfCustomer) {
        float cost = 0;
    try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + name + "-" + numberOfCustomer + ".txt", "rw")) {
        String s;
        while ((s = ref.readLine()) != null) {
            String[] productInfo = s.split(",");
            if(getOrderNumber()== Long.parseLong(productInfo[1])) {
                float productCost = Float.parseFloat(productInfo[5]);
                cost = productCost + cost;
                setOrderPrice(cost);
            }
        }
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


}

    public boolean isProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    boolean productAvailable;
public void ifProductAvailable(String categoryName,String idProduct){
    try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + categoryName + ".txt", "rw")) {
        String s;
        while ((s = ref.readLine()) != null) {
            String[] productInfo = s.split(",");
            if(productInfo[0].equals(idProduct)){
                if(productInfo[4].equals("available"))
                    setProductAvailable(true);
                else
                    setProductAvailable(false);
            }
        }
    }catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

///////////////////////////////////////////////////////view orders./////////////////////////////////////////////

    public boolean isIfCustomerShowPendingOrder() {
        return ifCustomerShowPendingOrder;
    }

    public void setIfCustomerShowPendingOrder(boolean ifCustomerShowPendingOrder) {
        this.ifCustomerShowPendingOrder = ifCustomerShowPendingOrder;
    }

    public boolean isIfCustomerCancelPendingOrder() {
        return ifCustomerCancelPendingOrder;
    }

    public void setIfCustomerCancelPendingOrder(boolean ifCustomerCancelPendingOrder) {
        this.ifCustomerCancelPendingOrder = ifCustomerCancelPendingOrder;
    }

    boolean ifCustomerShowPendingOrder;
    boolean ifCustomerCancelPendingOrder;

    public boolean isIfCustomerShowDeliveredOrder() {
        return ifCustomerShowDeliveredOrder;
    }

    public void setIfCustomerShowDeliveredOrder(boolean ifCustomerShowDeliveredOrder) {
        this.ifCustomerShowDeliveredOrder = ifCustomerShowDeliveredOrder;
    }

    boolean ifCustomerShowDeliveredOrder;

    public boolean isIfOrderExist() {
        return ifOrderExist;
    }

    public void setIfOrderExist(boolean ifOrderExist) {
        this.ifOrderExist = ifOrderExist;
    }

    boolean ifOrderExist;

    public void viewPendingOrder(String pending, String idCustomer) {
        boolean bd =false;
        int countPending=0;
        int countDelivered=0;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[6].equals(pending) && productInfo[2].equals(idCustomer)){
                    countPending=countPending+1;
                    countPendingFun( countPending);
                     bd = true;
                }
            }
            if (!bd){
                logger.info("There is no pending Orders");
            }



        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void countPendingFun(int countPending) {
       if(countPending > 0)
           setIfCustomerShowPendingOrder(true);
       else
        setIfCustomerShowPendingOrder(false);

    }

    public void viewDeliveredOrder(String delivered, String idCustomer) {
        boolean dv =false;
        int countDelivered=0;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[6].equals(delivered) && productInfo[2].equals(idCustomer)){
                    countDelivered=countDelivered+1;
                    countDeliveredFun( countDelivered);
                    dv = true;
                }

            }
            if (!dv){
                logger.info("There is no delivered Orders");
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void countDeliveredFun(int countDelivered) {
        if(countDelivered>0)
            setIfCustomerShowDeliveredOrder(true);
        else
        setIfCustomerShowDeliveredOrder(false);
    }

    public void viewPendingOrder1(String pending, String idCustomer, String name) {
        try (
                RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw"
                )) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[6].equals(pending) && productInfo[2].equals(idCustomer)){
                    setOrderNumber(Long.parseLong(productInfo[0]));
                    setCustomerName(productInfo[1]);
                    setIdCustomer(productInfo[2]);
                    setOrderPrice(Float.parseFloat(productInfo[3]));
                    setOrderDate(productInfo[4]);
                    setDateOfReceiptOfTheOrder(productInfo[5]);
                    setStatusOrder(productInfo[6]);
                    extractedPrintThePendingId();
                }
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void viewAllOrderToAdmin() {
        try (
                RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw"
                )) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");

                    setOrderNumber(Long.parseLong(productInfo[0]));
                    setCustomerName(productInfo[1]);
                    setIdCustomer(productInfo[2]);
                    setOrderPrice(Float.parseFloat(productInfo[3]));
                    setOrderDate(productInfo[4]);
                    setDateOfReceiptOfTheOrder(productInfo[5]);
                    setStatusOrder(productInfo[6]);
                    extractedPrintThePendingId();

            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void viewPendingOrderProduct(String idCustomer, String name,String idOrder) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/"+name+"-"+idCustomer+".txt", "rw")) {

            String s;
            while ((s = ref.readLine()) != null) {

                String[] productInfo = s.split(",");
                if(productInfo[1].equals(idOrder)){
                setCategoryName(productInfo[0]);
                setOrderNumber(Long.parseLong(productInfo[1]));
                setIdCustomer(productInfo[2]);
                setProductID(Integer.parseInt(productInfo[3]));
                setQuantitiesProduct(Integer.parseInt(productInfo[4]));
                setTotalPriceProduct(Float.parseFloat(productInfo[5]));
                extractedPrintThePendingId2Product();
                product.ifProductIdExist(getCategoryName(), String.valueOf(getProductID()));
                product.extractedSearchById(getCategoryName(),getProductID());
            }}

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void extractedPrintThePendingId() {
        logger.info("\u001B[34m The ID Of Order :\u001B[35m " +getOrderNumber()+" |"
                +"\u001B[34m Customer Name:\u001B[35m "+getCustomerName()+" |"
                +"\u001B[34m Customer ID :\u001B[35m "+getIdCustomer()+" |"
                +"\u001B[34m The Price:\u001B[35m "+getOrderPrice()+"$ |"
                +"\u001B[34m Order Date:\u001B[35m "+getOrderDate()+" |"
                +"\u001B[34m Received date:\u001B[35m "+getDateOfReceiptOfTheOrder()+" |"
                +"\u001B[34m Status Order:\u001B[35m "+getStatusOrder()+" |");

    }
    private void extractedPrintThePendingId2Product() {
        logger.info("\u001B[34m The ID Of Order :\u001B[35m " +getOrderNumber()+" |"
                +"\u001B[34m Catefory Name:\u001B[35m "+getCategoryName()+" |"
                +"\u001B[34m Customer ID :\u001B[35m "+getIdCustomer()+" |"
                +"\u001B[34m The Price:\u001B[35m "+getTotalPriceProduct()+"$ |"
                +"\u001B[34m Quantities Product:\u001B[35m "+getQuantitiesProduct()+" |"
                +"\u001B[34m Product ID :\u001B[35m "+getProductID()+" |");

    }


    public void ifEnterOrderExitToCancelPending(String idOrder){
        numberOfLine2=-1;
        try (
                RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw"
                )) {
            String s;
            while ((s = ref.readLine()) != null) {
                numberOfLine2=numberOfLine2+1;
                String[] productInfo = s.split(",");
                if(productInfo[0].equals(idOrder)&&productInfo[6].equals("pending")){
                setIfOrderExist(true);
                setIfCustomerCancelPendingOrder(true);
                return;
                }

                setIfOrderExist(false);
                setIfCustomerCancelPendingOrder(false);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ifEnterOrderExitToChangeSt(long idOrder){
        try (
                RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw"
                )) {

            String s;
            String longs= String.valueOf(idOrder);
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[0].equals(longs)){
                    setIfOrderExist(true);
                    return;
                }

                setIfOrderExist(false);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ifOrderExitDelivered(String idOrder){
        try (
                RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw"
                )) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[0].equals(idOrder) && productInfo[6].equals("delivered")){
                    setIfOrderExist(true);
                    return;
                }
                setIfOrderExist(false);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteOrder(String name) {
        deleteOrder1(1,"orderAllProduct", String.valueOf(getOrderNumber()));
        for (Integer i : lines){
            deleteOrder2("orderAllProduct",i);
        }
        lines.clear();
        deleteOrder1(1,name, String.valueOf(getOrderNumber()));
        for (Integer i : lines){
            deleteOrder2(name,i);
        }
        lines.clear();
        deleteOrder1(0,"orderToAdmin", String.valueOf(getOrderNumber()));
        for (Integer i : lines){
            deleteOrder2("orderToAdmin",i);
        }
    }

    public void ifOrderExit(Scanner scanner) {
        logger.log(Level.INFO,"Enter The Number Of Order: "+"\u001B[0m");
        setOrderNumber(Long.parseLong(scanner.next()));
        ifEnterOrderExitToCancelPending(String.valueOf(getOrderNumber()));
    }

    private void extractedEdit(Scanner scanner) {

        logger.log(Level.INFO,"Enter The Product ID : "+"\u001B[0m");
        setProductID(Integer.parseInt(scanner.next()));
        ifProductExitToEdit(getProductID(),getOrderNumber(),getCustomerName(),getIdCustomer());
        foundNumber2Line(getProductID(),getOrderNumber());
        if(isProductExitFlag()){
            editProductMenu();

        }
        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m The Product Not Found" + "\u001B[0m");
            pendingMenu();
        }
    }

    public void deleteOrder1(int nm,String name,String orderNumber){
        String s;
        int lineToDelete=-1;
        try {
           RandomAccessFile raf2 = new RandomAccessFile("src/main/resources/Data/"+name+".txt", "rw");

            while ((s = raf2.readLine()) != null) {
                lineToDelete=lineToDelete+1;
                String[] productInfo = s.split(",");
                if (productInfo[nm].equals(orderNumber)) {
                       lines.add(lineToDelete);
                    lineToDelete=lineToDelete-1;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteOrder2(String name,int id){
        try {
            RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/"+name+".txt", "rw");
            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;

            while (currentLine < id ) {
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




    private void ifQuantitiesGraterThan(int quantitiesProduct) {
        if(isQuantitiesAllowedFlag()){
                ifProductAvailable(getCategoryName(), String.valueOf(getProductID()));
                if(isProductAvailable()){

                    setQuantitiesProduct(quantitiesProduct);
                    calculateThePriceOfNewProduct(getProductID(),getCategoryName());
                    fullProductPrice();
                    deleteAndEdit();
                }
                else{
                    logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31m This product is no longer available" + "\u001B[0m");

                }

        }
        else{
            logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid value " + "\u001B[0m");
            editProductMenu();

        }
    }

    private void deleteAndEdit() {
        String name=getCustomerName()+"-"+getIdCustomer();
        deleteThisProductFromCustomer(name,getNumberOfLine());
        deleteThisProductFromCustomer("orderToAdmin",getNumberOfLine2());
        deleteThisProductFromCustomer("orderAllProduct",getNumberOfLine3());
        String product =getCategoryName()+","+getOrderNumber()+","+getIdCustomer()+","+getProductID()+","+getQuantitiesProduct()+","+getTotalPriceProduct()+"\n";
        addNewOrderToCustomer(getCustomerName(),getIdCustomer(),product);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        calculateTheTotalCost(getCustomerName(),getIdCustomer());
        String product2 =getOrderNumber()+","+getCustomerName()+","+getIdCustomer()+","+getOrderPrice()+","+formattedDate+","+"--"+","+"pending"+"\n";
        addNewOrderPending(product2);
        logger.log(Level.INFO,"The new Total cost is :" +getOrderPrice() + "\u001B[0m");

    }

   public boolean ifFileOfCustomerOrderNoItem(String nameCustomer,String customerId){
        countOfLine=-1;
       try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" +nameCustomer+"-"+idCustomer + ".txt", "rw")) {
           String s;
           setProductExitFlag(false);
           while ((s = ref.readLine()) != null) {
                   setCountOfLine(countOfLine+1);
               }
           if (getCountOfLine()==0){
               return true;

           } else if (getCountOfLine()>0) {
               return false;
           }
       }
       catch (IOException e) {
           throw new RuntimeException(e);
       }

      System.out.println("out");
       return false;
   }


    private void calculateThePriceOfNewProduct(int idProduct,String category) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + category + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String id = String.valueOf(idProduct);
                if (productInfo[0].equals(id)) {
                    setProductPrice(Float.parseFloat(productInfo[3]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void editThisProductImCustomer(String data) {
        try (RandomAccessFile file = new RandomAccessFile("src/main/resources/Data/" + customerName+"-"+idCustomer + ".txt", "rw")) {
            file.seek(file.length());
            file.writeBytes(data);
            file.close();
            logger.log(Level.INFO, "The product Order updated successfully");

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ifProductExitToEdit(int productID, long order, String name, String idCustomer) {
        numberOfLine = -1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + name+"-"+idCustomer + ".txt", "rw")) {
            String s;
            setProductExitFlag(false);
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String id = String.valueOf(productID);
                String order1 = String.valueOf(order);
                numberOfLine=numberOfLine+1;
                if( productInfo[3].equals(id) && productInfo[1].equals(order1)){
                    setCategoryName(productInfo[0]);
                    setOrderNumber(Long.parseLong(productInfo[1]));
                    setIdCustomer(productInfo[2]);
                    setQuantitiesProduct(Integer.parseInt(productInfo[4]));
                    setTotalPriceProduct(Float.parseFloat(productInfo[5]));
                    setProductExitFlag(true);

                   return;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void foundNumber2Line(int productID,long order) {
        numberOfLine3 = -1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderAllProduct.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String id = String.valueOf(productID);
                String order1 = String.valueOf(order);
                numberOfLine3=numberOfLine3+1;
                if( productInfo[3].equals(id) && productInfo[1].equals(order1)){
                    return;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isProductExitFlag() {
        return productExitFlag;
    }

    public void setProductExitFlag(boolean productExitFlag) {
        this.productExitFlag = productExitFlag;
    }

    boolean productExitFlag;
    public void  deleteThisProductFromCustomer(String name,int id) {
        try {
            RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/" + name +".txt", "rw");

            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;

            while (currentLine < id) {
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


    public void editTheOrder(String statusOrder) {
        for (Integer i : lines){
            deleteOrder2("orderToAdmin",i);
        }
        lines.clear();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        setDateOfReceiptOfTheOrder(formattedDate);
        String product2 =getOrderNumber()+","+getCustomerName()+","+getIdCustomer()+","+getOrderPrice()+","+getOrderDate()+","+getDateOfReceiptOfTheOrder()+","+getStatusOrder()+"\n";
        addNewOrderPending(product2);
        extractedPrintThePendingId();
        if (getStatusOrder().equals("canceled"))
        {
            gmail.sendEmail(getGmailIs(),arrayOfTopic[2],arrayOfMsg[2]);
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m The Order is canceled " +
                    "Successfully and send email to customer" + "\u001B[0m");
        }
        else if (getStatusOrder().equals("shipped")){
            gmail.sendEmail(getGmailIs(),arrayOfTopic[0],arrayOfMsg[0]);
            logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m The Order is shipped " +
                    "Successfully and send email to customer" + "\u001B[0m");}
        else if (getStatusOrder().equals("delivered")){
        gmail.sendEmail(getGmailIs(),arrayOfTopic[1],arrayOfMsg[1]);
        logger.log(Level.INFO, "\u001B[1m" + "\u001B[31m The Order is updated " +
                "Successfully and send email to customer" + "\u001B[0m");}
    }


    public void searchAboutCustomer(String name,long orderNumber) {
        String s;
        int lineToDelete=-1;
        try {

            RandomAccessFile raf2 = new RandomAccessFile("src/main/resources/Data/"+name+".txt", "rw");

            String or = String.valueOf(orderNumber);
            while ((s = raf2.readLine()) != null) {
                lineToDelete=lineToDelete+1;
                String[] productInfo = s.split(",");
                if (productInfo[0].equals(or)) {
                    lines.add(lineToDelete);
                    setOrderNumber(Long.parseLong(productInfo[0]));
                    setCustomerName(productInfo[1]);
                    setIdCustomer(productInfo[2]);
                    setOrderPrice(Float.parseFloat(productInfo[3]));
                    setOrderDate(productInfo[4]);
                    setDateOfReceiptOfTheOrder(productInfo[5]);
                    setStatusOrder(productInfo[6]);

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchAboutGmail() {
        String s;
        try {
            RandomAccessFile raf3 = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw");
            while ((s = raf3.readLine()) != null) {
                String[] productInfo = s.split(",");
                if (productInfo[4].equals(getIdCustomer())) {
                    setGmailIs(productInfo[1]);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public String getGmailIs() {
        return gmailIs;
    }

    public void setGmailIs(String gmailIs) {
        this.gmailIs = gmailIs;
    }

    String gmailIs;
}