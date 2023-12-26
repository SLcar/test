package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product {
    int numberOfLine;
    public int getNumberOfLine() {
        return numberOfLine;
    }
    private static final Logger logger = Logger.getLogger( Product.class.getName());

    public int getCount() {
        return count;
    }

    int count;


    String enter;
    public Product() {
        categoryExistFlag =false;
        iDExistFlag =false;
    }

    public boolean isiDExistFlag() {
        return iDExistFlag;
    }

    public void setIDExistFlag(boolean iDExistFlag) {
        this.iDExistFlag = iDExistFlag;
    }

    private boolean iDExistFlag;
    private final Category category = new Category();
    private String nameProduct;



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;

    public boolean isCategoryExistFlag() {
        return categoryExistFlag;
    }

    public void setCategoryExistFlag(boolean categoryExistFlag) {
        this.categoryExistFlag = categoryExistFlag;
    }

    private boolean categoryExistFlag;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    private String descriptionProduct;
    private String availability;
    private String imgProduct;

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    private int iD;
    private String priceProduct;
    boolean addProductsFlag;


    public void setAddProductsFlag(boolean addProductsFlag) {
        this.addProductsFlag = addProductsFlag;
    }




    public void menuProduct() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        enter ="""

                \u001B[33m----------------------------------
                |                                 |
                |     1. Show all products        |           \s
                |     2. add products             |
                |     3. edit products            |
                |     4. delete products          |
                |     5. Search products          |
                |                                 |
                ----------------------------------
                """;
        logger.info( enter);
        logger.info( getString() + getString1());

        choice = scanner.nextInt();
        if(choice==1)
        {
            printAllCategory();
            logger.log(Level.INFO,"Enter The name of category");
            String nameCato=getTheNameOfCat(scanner);
            ifCategoryExist(nameCato);
            if (isCategoryExistFlag()){
                printAllProductAndCategories(nameCato);
                back();
            }
            else {
                logger.log(Level.WARNING, getString4() + "\u001B[31mThis category does not exist." + getString3() +"\n");
                back();
            }

        }
        else if (choice == 2) {
            addNewProducts();
            back();
        } else if (choice == 3) {
            editProducts();
            back();
        } else if (choice == 4) {
            deleteProducts();
            back();
        }

        else if (choice == 5) {
            searchOfProducts();
            back();
        }
        else {
            logger.log(Level.INFO, getString4() + "\u001B[31" + "Invalid choice! Please enter a valid choice.\u001B[0m");
            menuProduct();
        }
    }

    private static String getString3() {
        return "\u001B[0m";
    }

    private static String getString1() {
        return getString2();
    }

    /////////////////////////////////back/////////////////////////////////////
    public void back() {
        logger.log(Level.INFO, """

                1) back\s
                2) Exit""");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        if (choice == 1)
            menuProduct();
        System.exit(0);
    }
    /////////////////////////////////back/////////////////////////////////////

    public static String getTheNameOfCat(Scanner scanner) {
        return scanner.next();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    public void searchOfProducts() {
        searchMenu();
    }
    public void deleteProducts() {
        deleteProductsMenu();
    }

    public void editProducts() {
        editProductsMenu();

    }
    private void addNewProducts() {
        newAddProductMenu();
    }

    public void enterDataOfProduct() {
        int available;
        Scanner scanner1 = new Scanner(System.in);
        logger.log(Level.INFO, "\u001B[33m"+"Enter The ID : ");
        setID(Integer.parseInt(scanner1.nextLine()));
        logger.log(Level.INFO, "Enter The Name : ");
        setNameProduct(scanner1.nextLine());
        logger.log(Level.INFO, "Enter The Description : ");
        setDescriptionProduct(scanner1.nextLine());
        logger.log(Level.INFO, "Enter The Price: ");
        setPriceProduct(scanner1.nextLine());
        logger.log(Level.INFO, "Enter The img of product: ");
        setImgProduct(scanner1.nextLine());
        logger.log(Level.INFO, "The availability : ");
        logger.log(Level.INFO, "1) available");
        logger.log(Level.INFO, "2) Not available\u001B[34m");
        available = scanner1.nextInt();
        if (available == 1) {
            setAvailability("available");
        } else
            setAvailability("Not available");

    }
    ////////////////////////////////////////add product////////////////////////////////////////////////////////

    public void newAddProductMenu() {
        Scanner scanner = new Scanner(System.in);

        logger.log(Level.INFO, "\u001B[35m" + "What categories of product do you want to add ?");
        printAllCategory();
        logger.log(Level.INFO, "\u001B[36m" + "new categories" + getString2());
        logger.log(Level.INFO, getString() + getString2());

        categoryName = scanner.nextLine();
        if (categoryName.equals("new categories")) {
            logger.log(Level.INFO, "Enter The name of category");
            String names = scanner.next();
            ifCategoryExist(names);
            if(isCategoryExistFlag()){
                logger.log(Level.WARNING, getString4() +"\u001B[31mThis category exist."+"\u001B[0m\n");

            }
            else
                addNewCategoriesProduct(names);
        }
        else {
            ifCategoryExist(getCategoryName());
            if(isCategoryExistFlag()) {
                extractedIfProduct("added");
            }
            else
                logger.log(Level.WARNING, getString4() +"\u001B[31mThis category does not exist."+"\u001B[0m\n");

        }
    }

    private static String getString2() {
        return getString3();
    }

    public void extractedIfProduct(String addOrUpdate) {
        enterDataOfProduct();
        ifProductIdExist(getCategoryName(), String.valueOf(getID()));
        if(isiDExistFlag())
            logger.log(Level.WARNING, getString4() +"\u001B[31mThis ID Of Product is already exist."+"\u001B[0m\n");
        else
            addNewProducts(categoryName,addOrUpdate);
    }

    private static String getString4() {
        return "\u001B[1m";
    }

    public void addNewProducts(String catName,String addOrUpdate) 
    {
        RandomAccessFile file= null;
        try {
             file = new RandomAccessFile("src/main/resources/Data/" + catName + ".txt", "rw");
            file.seek(file.length());
            String product = getID() + "," + getNameProduct() + "," + getDescriptionProduct() + "," + getPriceProduct() + "," + getAvailability() + "," + getImgProduct() + "\n";
            file.writeBytes(product);
            file.close();
            logger.log(Level.INFO, "The product "+ addOrUpdate +" successfully");
            setAddProductsFlag(true);
        } catch (IOException e) {
            e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        setAddProductsFlag(false);

    }


    public void addNewCategoriesProduct(String name) {
        category.addNewCategory(name);
        if (category.isAddNewCategoryFlag()) {
            category.addThisCategory(name);
            enterDataOfProduct();
            addNewProducts(name,"added");
        }}
    ////////////////////////////////////////update product////////////////////////////////////////////////////////
    public void editProductsMenu(){
        Scanner scanner = new Scanner(System.in);
        printAllCategory();

        logger.log(Level.INFO, "\u001B[35m What is the product category you would like to modify?");
        categoryName = scanner.nextLine();
        ifCategoryExist(categoryName);
        if(isCategoryExistFlag()){
            printAllProductAndCategories(categoryName);
            logger.log(Level.INFO, "\u001B[34m" + "What is the product ID that you want to modify?");
            String id = scanner.nextLine();
            editProducts1(categoryName,id);
        }
        else
            logger.log(Level.WARNING, getString4() +"\u001B[31mThis category does not exist."+"\u001B[0m\n");

    }

    private void editProducts1(String categoryName, String id) {
        ifProductIdExist(categoryName,id);


        if(isiDExistFlag())
        {
            searchTheProductByID(categoryName,id);
            extractedPrintTheProduct();
            deleteThisProduct(categoryName);
            extractedIfProduct("updated");
        }
        else
            logger.log(Level.WARNING, getString4() + getString5() +"This product does not exist."+"\u001B[0m\n");

    }

    private static String getString5() {
        return "\u001B[31m";
    }

    private void extractedPrintTheProduct() {
        logger.info("\u001B[34m The ID :\u001B[35m " + getID()+" |"
                +"\u001B[34m The Name:\u001B[35m "+getNameProduct()+" |"
                +"\u001B[34m The Description:\u001B[35m "+getDescriptionProduct()+" |"
                +"\u001B[34m The Price:\u001B[35m "+getPriceProduct()+"$ |"
                +"\u001B[34m The Availability:\u001B[35m "+getAvailability()+" |"
                +"\u001B[34m The img:\u001B[35m "+getImgProduct()+" |");
    }


////////////////////////////////////////update product////////////////////////////////////////////////////////
////////////////////////////////////////delete product////////////////////////////////////////////////////////

    public void deleteProductsMenu(){
        Scanner scanner = new Scanner(System.in);
        printAllCategory();

        logger.log(Level.INFO, "\u001B[35m What is the category of product?");
        categoryName = scanner.nextLine();
        ifCategoryExist(categoryName);
        if(isCategoryExistFlag()){
            printAllProductAndCategories(categoryName);
            logger.log(Level.INFO, "\u001B[34m" + "What is the product ID that you want to delete?");
            String id = scanner.nextLine();
            ifProductIdExist(categoryName,id);
            if(isiDExistFlag()) {
                deleteThisProduct(categoryName);
                logger.log(Level.INFO, "The product deleted successfully");
            }
            else
                logger.log(Level.WARNING, getString4() +"\u001B[31mThis product does not exist."+"\u001B[0m\n");

        }
        else
            logger.log(Level.WARNING, getString4() +"\u001B[31mThis category does not exist."+"\u001B[0m\n");

    }


    public void  deleteThisProduct(String categoryName){
        try {
            RandomAccessFile raf = new RandomAccessFile("src/main/resources/Data/"+categoryName+".txt", "rw");
            long start = 0;
            long currentPos = raf.getFilePointer();
            int currentLine = -1;

            while (currentLine < getNumberOfLine()) {
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
            throw new RuntimeException(e);
        }

    }

    ////////////////////////////////////////delete product////////////////////////////////////////////////////////
////////////////////////////////////////Search product////////////////////////////////////////////////////////
    public void ifProductAvailabilityExist(String categoryName, String availability) {
        count=0;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + categoryName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String idProduct = productInfo[4];
                if (availability.equalsIgnoreCase(idProduct)) {
                    count=count+1;
                    ifProductNameExist2(count);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void PrintProductAvailabilityExist(String categoryName, String availability) {
        count=0;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + categoryName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String idProduct = productInfo[4];
                if (availability.equalsIgnoreCase(idProduct)) {
                    extractedStoreData(productInfo);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
    public void searchTheProductByID(String categoryName, String id) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + categoryName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String idProduct = productInfo[0];
                if (id.equals(idProduct)) {
                    setID(Integer.parseInt(productInfo[0]));
                    setNameProduct(productInfo[1]);
                    setDescriptionProduct(productInfo[2]);
                    setPriceProduct(productInfo[3]);
                    setAvailability(productInfo[4]);
                    setImgProduct(productInfo[5]);
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void searchMenu(){
        Scanner scanner = new Scanner(System.in);
        printAllCategory();

        logger.log(Level.INFO, "\u001B[35m What is the category of product?");
        categoryName = scanner.nextLine();
        ifCategoryExist(categoryName);
        if(isCategoryExistFlag()){
            searchMenuPrint(categoryName);
        }
        else
            logger.log(Level.WARNING, getString4() +"\u001B[31mThis category does not exist."+"\u001B[0m\n");

    }
    public void ifProductNameExist(String catName, String productName) {
        count = 0;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + catName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String productToSearch1 = productInfo[1];
                String[] productToSearch2 =productToSearch1.split(" ");
                if(productName.equalsIgnoreCase(productToSearch1)){
                    count=count+1;
                    ifProductNameExist2(count);
                }
                else{
                    for (String i : productToSearch2 ) {
                        if (productName.equalsIgnoreCase(i)) {
                            count=count+1;
                            ifProductNameExist2(count);
                        }
                    }}}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ifProductNameExist2(int count){
        return count > 0;
    }
    public void printTheResultSearchByName(String catName, String productName,int num) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + catName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String productToSearch1 = productInfo[num];
                String[] productToSearch2 =productToSearch1.split(" ");
                if(productName.equalsIgnoreCase(productToSearch1)){
                    extractedStoreData(productInfo);
                }
                else{
                    for (String i : productToSearch2 ) {
                        if (productName.equalsIgnoreCase(i)) {
                            extractedStoreData(productInfo);
                        }
                    }}}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractedStoreData(String[] productInfo) {
        setID(Integer.parseInt(productInfo[0]));
        setNameProduct(productInfo[1]);
        setDescriptionProduct(productInfo[2]);
        setPriceProduct(productInfo[3]);
        setAvailability(productInfo[4]);
        setImgProduct(productInfo[5]);
        extractedPrintTheProduct();
    }

    public void ifProductDescriptionsExist(String catName, String productDescriptions) {
        count = 0;
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + catName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                String[] productInfo = s.split(",");
                String productToSearch1 = productInfo[2];
                String[] productToSearch2 =productToSearch1.split(" ");
                if(productDescriptions.equalsIgnoreCase(productToSearch1)){
                    count=count+1;
                    ifProductNameExist2(count);
                }
                else{
                    for (String i : productToSearch2 ) {
                        if (productDescriptions.equalsIgnoreCase(i)) {
                            count=count+1;
                            ifProductNameExist2(count);
                        }
                    }}}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

////////////////////////////////////////Search product////////////////////////////////////////////////////////

    public void printAllCategory() {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/categoryData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) logger.log(Level.INFO, "\u001B[36m" + s + getString2());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ifCategoryExist(String CategoryName) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/categoryData.txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                if (s.equals(CategoryName)) {
                    setCategoryExistFlag(true);
                    return;
                }
                setCategoryExistFlag(false);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ifProductIdExist(String CategoryName, String iD) {
        numberOfLine = -1;

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/" + CategoryName + ".txt", "rw")) {
            String s;
            while ((s = ref.readLine()) != null) {
                numberOfLine = numberOfLine +1;
                String[] productInfo = s.split(",");
                String idProduct = productInfo[0];
                if (iD.equals(idProduct)) {
                    setIDExistFlag(true);
                    return;
                }
                setIDExistFlag(false);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printAllProductAndCategories(String CategoryName) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/Data/"+CategoryName+".txt", "rw")) {
            String s;
            logger.log(Level.INFO,"\u001B[35m"+CategoryName+" Product : \n\u001B[37m");
            while ((s = ref.readLine()) != null) {
                String[] productData = s.split(",");
                String iDLoop = productData[0];
                String nameLoop  = productData[1];
                String descriptionsLoop  = productData[2];
                String pricesLoop  = productData[3];
                String availabilityLoop  = productData[4];
                String imgLoop  = productData[5];
                logger.info("\u001B[36m"+"ID : "+iDLoop +
                        "  Name: "+nameLoop+"  description: "+descriptionsLoop+
                        "  price: "+pricesLoop+"$"+"  availability: "+availabilityLoop +
                        "  img link:  "+imgLoop+"\n\u001B[37m");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void   searchMenuPrint(String catName){
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
enter= """

                \u001B[33m----------------------------------
                |                                 |
                |     1. ID                       |           \s
                |     2. name of product          |
                |     3. descriptions of product  |
                |     4. availability             |
                |                                 |
                ----------------------------------
                """;
        logger.info( enter);
        logger.info( getString() + getString2());

        choice = scanner.nextInt();
        if(choice==1)
        {
            logger.info( "Enter The id to search: " + getString2());
            int productID = scanner.nextInt();
            ifProductIdExist(catName, String.valueOf(productID));
            extractedSearchById(catName, productID);
        }
        else if (choice==2) {
            logger.info( "Enter The name to search: " + getString2());
            String productName = scanner2.nextLine();
            ifProductNameExist(catName,productName);
            extractedSearchByName(catName,productName);
        }
        else if (choice==3) {
            logger.info( "Enter The description to search: " + getString2());
            String productDescription = scanner2.nextLine();
            ifProductDescriptionsExist(catName,productDescription);
            extractedSerachByDescription(catName,productDescription);
        }
        else if (choice==4) {
            logger.info( "Enter The (available/not available) to search: " + getString2());
            String productAvailable = scanner2.nextLine();
            ifProductAvailabilityExist(catName,productAvailable);
            extractedSearchByAvailability(catName,productAvailable);
        }

        else {
            logger.log(Level.INFO, getString4() + getString5() + "Invalid choice! Please enter a valid choice.\u001B[0m");
            menuProduct();
        }
    }

    private static String getString() {
        return "Enter your choice: ";
    }

    public void extractedSearchById(String catName, int productID) {
        if (isiDExistFlag()){
            searchTheProductByID(catName, String.valueOf(productID));
            extractedPrintTheProduct();
        }
        else {
            logger.log(Level.WARNING, getString4() + "\u001B[31m The product Not found" + "\u001B[0m\n");
        }
    }

    private void extractedSearchByName(String catName, String productName) {
        if (ifProductNameExist2(getCount())){
            printTheResultSearchByName(catName,productName,1);
        }
        else {
            logger.log(Level.WARNING, getString4() + "\u001B[31m The product Not found" + "\u001B[0m\n");
        }
    }
    private void  extractedSerachByDescription(String catName,String productDescription){
        if (ifProductNameExist2(getCount())){
            printTheResultSearchByName(catName,productDescription,2);
        }
        else {
            logger.log(Level.WARNING, getString4() + "\u001B[31m The product Not found" + "\u001B[0m\n");
        }
    }

    private void  extractedSearchByAvailability(String catName,String productAvailability){
        if (ifProductNameExist2(getCount())){
            PrintProductAvailabilityExist(catName,productAvailability);
        }
        else {
            logger.log(Level.WARNING, getString4() + "\u001B[31m The product Not found" + "\u001B[0m\n");
        }
    }
}

