package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.SecureRandom;

public class Order extends RuntimeException  {

    public Order(String message, Throwable cause) {
        super(message, cause);

    }
    public Order (){

    }
    public static final String MSG ="Error during order login";
    public static final String M_THE_ORDER_NOT_FOUND = "\u001B[31m The order Not Found";
    public static final String M_INVALID_VALUE = "\u001B[31mInvalid value ";
    public static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    public static final String SRC_MAIN_RESOURCES_DATA = "src/main/resources/Data/";
    public ArrayList<Integer> lines = new ArrayList<>();
    protected static final  String[] arrayOfTopic =  {"Order confirmation", "Receiving the order", "Cancel the order"}; // Creating an array that can hold 3 strings
    protected    static final String[] arrayOfMsg = {"The order has been confirmed", "The order was delivered successfully. Thank you for taking it from our store. We always welcome you. If there is a problem, please contact the number:059233522","We are sorry, but the order has been canceled due to logistical restrictions beyond our store's control"}; // Creating an array that can hold 3 strings
    public static final Logger logger = Logger.getLogger(Order.class.getName());
    public final SecureRandom secureRandom = new SecureRandom();
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

    protected boolean sendEmailConfirmation=false;
    protected boolean sendEmailReceiving=false;
    protected boolean sendEmailCancel=false;
    public String delivered = getDelivered();
    public void  ifEmailSending (String status){

        switch (status) {
            case "shipped" -> setSendEmailConfirmation(true);
            case "delivered" -> setSendEmailReceiving(true);
            case "Canceled" -> setSendEmailCancel(true);
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

    int numberOfLine;

    public int getNumberOfLine2() {
        return numberOfLine2;
    }


    public int getNumberOfLine3() {
        return numberOfLine3;
    }

    protected int numberOfLine2;
    protected int numberOfLine3;

    public String getDateOfReceiptOfTheOrder() {
        return dateOfReceiptOfTheOrder;
    }

    public void setDateOfReceiptOfTheOrder(String dateOfReceiptOfTheOrder) {
        this.dateOfReceiptOfTheOrder = dateOfReceiptOfTheOrder;
    }

    String dateOfReceiptOfTheOrder;
    
    public void setStatusOrder(String statusOrder) {
        Order.statusOrder = statusOrder;
    }
    public String getStatusOrder() {
        return statusOrder;
    }

    
    
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    String orderDate;


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
        Order.orderNumber = orderNumber;
    }




    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        Order.idCustomer = idCustomer;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public static final String pending ="pending";
    public String categoryName;



    public String  show;
    public static String statusOrder;
    public static  long orderNumber;
    public static String idCustomer;
    public static float orderPrice;
    public static int quantitiesProduct;



    public static String getString2() {
        return "\u001B[1m";
    }



    public void whatSetProductPrice(String categoryName,String id) {
        try (RandomAccessFile ref = new RandomAccessFile(SRC_MAIN_RESOURCES_DATA + categoryName + ".txt", "rw")) {
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
            throw new Order( MSG, e);
        }
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float productPrice;

    public void setOrderPrice(float orderPrice) {
        Order.orderPrice = orderPrice;
    }


    public int getQuantitiesProduct() {
        return quantitiesProduct;
    }

    public void setQuantitiesProduct(int quantitiesProduct) {
        Order.quantitiesProduct = quantitiesProduct;
    }


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
            logger.log(Level.INFO, getString2() + "\u001B[31m" + "The category Not exist.\u001B[0m");
            makePurchasesMenu1();
        }

    }

    private void extracted1(Scanner scanner1) {
        if(product.isiDExistFlag()){
            ifProductAvailable(getCategoryName(), String.valueOf(getProductID()));

            extracted4(scanner1);
        }

        else{
            logger.log(Level.INFO, getString2() + "\u001B[31m" + "The Product Not exist.\u001B[0m");
            enterAnotherProduct(scanner1);
        }
    }

    private void extracted4(Scanner scanner1) {
        if(isProductAvailable()){
            returnEnterQuantities();

        }
        else {
            logger.log(Level.INFO, getString2() + "\u001B[31m" + "The Product Not Available.\u001B[0m");
            enterAnotherProduct(scanner1);

        }
    }

    private void extracted3(Scanner scanner1) {

        whatSetProductPrice(getCategoryName(), String.valueOf(getProductID()));
        fullProductPrice();
        String product =getCategoryName()+","+getOrderNumber()+","+getIdCustomer()+","+getProductID()+","+getQuantitiesProduct()+","+getTotalPriceProduct()+"\n";
        addNewOrderToCustomer(getCustomerName(),getIdCustomer(),product);
        enterAnotherProduct(scanner1);
    }

    private void enterAnotherProduct(Scanner scanner1) {
        show="""
                \u001B[36mDo u want add another product?
                 1. Yes
                 2. No
                 3. Cancel Order""";
        logger.log(Level.INFO, show);
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

    public void extracted2() {
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
            extracted3(scanner1);
        }
        else{
            logger.log(Level.WARNING, "\u001B[31m The quantity is greater than 0.");
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
        Order.customerName = customerName;
    }

    public static String customerName;

      public void addNewOrderToCustomer(String customerName,String idCustomer,String data)
    {
        RandomAccessFile file = null;
        RandomAccessFile fil2=null;
        try
        {
            file = new RandomAccessFile("src/main/resources/Data/" + customerName + "-" + idCustomer + ".txt", "rw");
            fil2 = new RandomAccessFile("src/main/resources/Data/orderAllProduct.txt", "rw");
            file.seek(file.length());
            file.writeBytes(data);
            //file.close();
            fil2.seek(fil2.length());
            fil2.writeBytes(data);
            fil2.close();
        } catch (IOException e)
        {
            logger.log(Level.SEVERE, "An error occurred", e);
           
        }
        finally
        {
            if (file != null)
            {
                try
                {
                    file.close();
                } catch (IOException e)
                {
                   logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
            if (fil2 != null)
            {
                try
                {
                    fil2.close();
                } catch (IOException e)
                {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
        }
    }
    public void addNewOrderPending(String data) {
        RandomAccessFile file=null;
        try {
            file = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw");
            file.seek(file.length());
            file.writeBytes(data);

            file.close();
        } catch (IOException e) {
           logger.log(Level.SEVERE, "An error occurred", e);
        }finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                   logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
        }

    }
    

    public void randomNumberGenerator() {
        long min = 1000000000L; // Minimum 10-digit number
        long max = 9999999999L; // Maximum 10-digit number

        long randomNum = min + ((long) (secureRandom.nextDouble() * (max - min)));
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
            throw new Order( MSG, e);
        }
    }


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
        } catch (IOException e) {
            throw new Order( MSG, e);
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
                    setProductAvailable(productInfo[4].equals("available"));
                }
            }
        } catch (IOException e) {
            throw new Order( MSG, e);
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



        } catch (IOException e) {
            throw new Order( MSG, e);
        }

    }


    private void countPendingFun(int countPending) {
        setIfCustomerShowPendingOrder(countPending > 0);

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
        } catch (IOException e) {
            throw new Order( MSG, e);
        }
    }

    private void countDeliveredFun(int countDelivered) {
        setIfCustomerShowDeliveredOrder(countDelivered > 0);
    }

    public void viewPendingOrder1(String pending, String idCustomer) {
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
        } catch (IOException e) {
            throw new Order( MSG, e);
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
        } catch (IOException e) {
            throw new Order( MSG, e);
        }

    }
    public void viewPendingOrderProduct(String idCustomer, String name,String idOrder) {
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

        } catch (IOException e) {
            throw new Order( MSG, e);
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
        } catch (IOException e) {
            throw new Order( MSG, e);
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
        } catch (IOException e) {
            throw new Order( MSG, e);
        }
    }
    public void ifOrderExitDelivered(String idOrder){
        try (
                RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/orderToAdmin.txt", "rw"
                )) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                if(productInfo[0].equals(idOrder) && productInfo[6].equals(getDelivered())){
                    setIfOrderExist(true);
                    return;
                }
                setIfOrderExist(false);
            }
        } catch (IOException e) {
            throw new Order( MSG, e);
        }
    }

    public static String getDelivered() {
        return "delivered";
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
        logger.log(Level.INFO,"Enter The Number Of Order: ");
        setOrderNumber(Long.parseLong(scanner.next()));
        ifEnterOrderExitToCancelPending(String.valueOf(getOrderNumber()));
    }

   public void deleteOrder1(int nm,String name,String orderNumber){
        RandomAccessFile raf2=null;
        String s;
        int lineToDelete=-1;
        try {
            raf2 = new RandomAccessFile("src/main/resources/Data/"+name+".txt", "rw");

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
        finally
        {
            if (raf2 != null)
            {
                try
                {
                    raf2.close();
                } catch (IOException e)
                {
                   logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
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
            throw new Order( MSG, e);
        }
    }




    public void ifQuantitiesGraterThan(int quantitiesProduct) {
        if(isQuantitiesAllowedFlag()){
            ifProductAvailable(getCategoryName(), String.valueOf(getProductID()));
            if(isProductAvailable()){
                setQuantitiesProduct(quantitiesProduct);
                calculateThePriceOfNewProduct(getProductID(),getCategoryName());
                fullProductPrice();
                deleteAndEdit();
            }
            else{
                logger.log(Level.WARNING, "\u001B[31m This product is no longer available");

            }

        }
        else{
            logger.log(Level.WARNING, "\u001B[31mInvalid value ");
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
        logger.log(Level.INFO,"The new Total cost is :" +getOrderPrice());

    }

    public boolean ifFileOfCustomerOrderNoItem(String nameCustomer){
        countOfLine=-1;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" +nameCustomer+"-"+idCustomer + ".txt", "rw")) {
            setProductExitFlag(false);
            while (ref.readLine() != null) {
                setCountOfLine(countOfLine+1);
            }
            if (getCountOfLine()==0){
                return true;

            } else if (getCountOfLine()>0) {
                return false;
            }
        }
        catch (IOException e) {
            throw new Order( MSG, e);
        }

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
            throw new Order( MSG, e);
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
            throw new Order( MSG, e);
        }

    }

    public void foundNumber2Line(int productID,long order) {
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
            throw new Order( MSG, e);
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

            long end = raf.length();
            byte[] remainingBytes = new byte[(int) (end - currentPos)];
            raf.read(remainingBytes);

            raf.seek(start);
            raf.write(remainingBytes);
            raf.setLength(start + remainingBytes.length);
            raf.close();


        } catch (IOException e) {
            throw new Order( MSG, e);
        }
    }


    public void editTheOrder() {
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
            logger.log(Level.INFO, getString2() + "\u001B[31m The Order is canceled " +
                    "Successfully and send email to customer");
        }
        else if (getStatusOrder().equals("shipped")){
            gmail.sendEmail(getGmailIs(),arrayOfTopic[0],arrayOfMsg[0]);
            logger.log(Level.INFO, getString2() + "\u001B[31m The Order is shipped " +
         "Successfully and send email to customer");}
        else if (getStatusOrder().equals(getDelivered())){
            gmail.sendEmail(getGmailIs(),arrayOfTopic[1],arrayOfMsg[1]);
            logger.log(Level.INFO, getString2() + "\u001B[31m The Order is updated " +
                    "Successfully and send email to customer");}
    }


   public void searchAboutCustomer(String name,long orderNumber)
    {
        String s;
        int lineToDelete=-1;
        RandomAccessFile raf2 =null;
        try
        {

            raf2 = new RandomAccessFile("src/main/resources/Data/"+name+".txt", "rw");

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

        } catch (IOException e)
        {
            throw new Order( MSG, e);
        }
        finally
        {
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
        }
    }

    public void searchAboutGmail() {
        String s;
        RandomAccessFile raf3 =null;
        try {
            raf3 = new RandomAccessFile("src/main/resources/Data/custumorData.txt", "rw");
            while ((s = raf3.readLine()) != null) {
                String[] productInfo = s.split(",");
                if (productInfo[4].equals(getIdCustomer())) {
                    setGmailIs(productInfo[1]);
                }
            }

        } catch (IOException e) {
            throw new Order( MSG, e);
        }
        finally
        {
            if (raf3 != null) {
                try {
                    raf3.close();
                } catch (IOException e) {
                   logger.log(Level.SEVERE, "An error occurred", e);
                }
            }
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
