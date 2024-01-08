package org.example;

import java.util.Scanner;
import java.util.logging.Level;

import static org.example.Registration.logger;



public class Main {
    static Category category=new Category();
    static Registration ob=new Registration();
    static Admin admin =new Admin();
    static Product product= new Product();
    static Installer installer = new Installer();
    static Order order = new Order();
    static Customer customer = new Customer();
    static SignIn sign = new SignIn();
    public static void menu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """
            
            \u001B[35m------ Welcome to Home Page ------
            |                                |
            |          1. Sign Up            |
            |          2. Login              |
            |          3. Exit               |
            |                                |
            ----------------------------------
            """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {signupMenu(); menu();
        } else if (choice == 2) {
            loginMenu ();
            menu();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m\n");
            menu();
        }

    }
    public static void signupMenu(){

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        logger.log(Level.INFO,"\u001B[35m"+"----- Welcome to signup Page -----");
        logger.log(Level.INFO,"Enter your name:");
        ob.setName(scanner.next());
        logger.log(Level.INFO,"Enter your Email:");
        ob.setEmail(scanner.next());
        logger.log(Level.INFO,"Enter your ID:");
        ob.setId(scanner.nextInt());
        logger.log(Level.INFO,"Enter your PhoneNumber:");
        ob.setPhone(scanner.nextInt());
        logger.log(Level.INFO,"Enter your Address e.g(Nablus/Palestine Street/Al Quds Bakery)");
        ob.setAddress(scanner1.nextLine());
        logger.log(Level.INFO,"Enter your Password:");
        ob.setPassword(scanner.next());
        logger.log(Level.INFO,"ConFirmed your Password:");
        ob.setComPassword(scanner.next());
        ob.searchIfEmailIsAlreadyExist(ob.getEmail());
        if(ob.isIfEmailFound()){
            logger.log(Level.WARNING,"This email is already exist");
            ob.returnEnterEmail();

        }
        else if(!ob.truepass(ob.getPassword(),ob.getComPassword())){
           ob.returnEnterPass(ob);
        }
        else {
            String dD =ob.getName()+"," +ob.getEmail() + "," + ob.getPassword() + "," + ob.getAddress() + "," + ob.getId()+","+ob.getPhone()+"\n";
            ob.storeDataToFile(dD);
            logger.log(Level.INFO, "The registration process was completed successfully");

        }
    }
    public static void menuCategory() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """

                \u001B[34m----------------------------------
                |                                 |
                |     1.View all categories       |
                |     2.add new category          |
                |     3.edit category             |
                |     4.delete category           |
                |                                 |
                ----------------------------------
                """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[37m");
        choice = scanner.nextInt();
        if (choice == 1) {
            category.printAllCategory();
            back();
        }
        else if (choice == 2) {
            logger.log(Level.INFO,"Enter The name of category");
            String names = Category.getTheNameOfCat(scanner);
            category.addNewCategory(names);
            category.extractedAddNewCat(names);
            back();
        }
        else if (choice == 3) {
            logger.log(Level.INFO,"Enter The name of category that you will modify");
            String names = Category.getTheNameOfCat(scanner);
            logger.log(Level.INFO,"Enter The new name of category");
            String newName = Category.getTheNameOfCat(scanner);
            category.editTheName(names);
            category.extractedEditCat(names,newName);
            back();
        }
        else if (choice == 4) {
            logger.log(Level.INFO,"Enter The name of category");
            String names = Category.getTheNameOfCat(scanner);
            category.deleteTheCategory(names);
            category.extractedDeleteCat(names);
            back();
        }

        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+ Category.RESET_COLOR);
            menuCategory();
        }

    }
    public static void back(){
        logger.log(Level.INFO, """

                1) back\s
                2) Exit""");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        if(choice==1)
            menuCategory();
        System.exit(0);
    }
    public static  void userAccountMenu(){
        if (admin.isProductsFlag()) menuProduct();
        else if (admin.isCategoriesFlag()) menuCategory();
        else if (admin.isUserAccountsFlag()) menuManageAccountUser();
        else if (admin.orderCustomerFlag) menuOrderCustomer();
        else if (admin.installationRequestsFlag) installer.installer_menu(admin.getAdminName());
    }
    public static String enter;
    public static void menuOrderCustomer(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        enter="""

                \u001B[34m----- Manage Order Customer -----
                |     1. Show All Order         |
                |     2. edit Customer Order    |
                |     3. back                   |
                ---------------------------------n""";
        logger.log(Level.INFO, admin.enter);
        logger.log(Level.INFO, Admin.getMsg);
        choice = scanner.nextInt();
        if (choice == 1) {
            order.viewAllOrderToAdmin();
            menuOrderCustomer();
        }
        else if (choice ==2) {
            enterData();
            menuOrderCustomer();
        }
        else if (choice ==3) {
            adminMenu(admin.getAdminName());}

        else {
            logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
            menuOrderCustomer();
        }

    }

    public static void menuManageAccountUser(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        enter="""

                \u001B[34m----- Manage user -----
                |     1. Admin         |
                |     2. Customer      |
                |     3. Installer     |
                |     4. Back          |
                -----------------------
                """;
        logger.log(Level.INFO, admin.enter);
        logger.log(Level.INFO, Admin.getMsg);
        choice = scanner.nextInt();
        if (choice == 1) {
            editAdminProfile();
        }
        else if (choice ==2) {
           menuCustomerAdmin();
        }
        else if (choice ==3) {
            installer.menuInstallerAdmin();
        }
        else if (choice ==4) {
            adminMenu(admin.getAdminName());
        }
        else {
            logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
            menuManageAccountUser();
        }

    }
    public static void adminMenu(String adminName) {
        admin.setProductsFlag(false);
        admin.setCategoriesFlag(false);
        admin.setUserAccountsFlag(false);
        admin.setOrderCustomerFlag(false);
        admin.setInstallationRequestsFlag(false);
        admin.setAdminName(adminName);

        String menu = "\n\u001B[37m" + "----------  Welcome " + adminName + " -------" + "\n" +
                "|    1. Manage products                  |\n" +
                "|    2. Manage categories                |\n" +
                "|    3. Manage user accounts.            |\n" +
                "|    4. Manage order Customer.           |\n" +
                "|    5. Manage Installation Requests     |\n" +
                "|    6. logout                           |\n" +
                "------------------------------------------\n";

        logger.log(Level.INFO, menu);
        logger.log(Level.INFO, Admin.getMsg);

        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> admin.productsFlag = true;
            case 2 -> admin.categoriesFlag = true;
            case 3 -> admin.userAccountsFlag = true;
            case 4 -> admin.orderCustomerFlag = true;
            case 5 -> admin.installationRequestsFlag = true;
            case 6 ->menu();
            default -> {
                logger.log(Level.WARNING, String.format("%s", Admin.INVALID_CHOICE_MESSAGE));
                adminMenu(adminName);
                return;
            }
        }
        userAccountMenu();
    }
    public static void loginMenu (){
        int  choice1 ;
        Scanner scanner1 = new Scanner(System.in);
        logger.log(Level.INFO, """

                \u001B[36m------ Welcome to login Page ------
                |                                |
                |          1. Admin              |
                |          2. Custumor           |
                |          3. Installer          |
                |                                |
                ----------------------------------
                """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice1 = scanner1.nextInt();
        if (choice1 == 1){
            sign.setTheUser(1);
            whoIsLogin();
        }
        else if (choice1 ==2){
            sign.setTheUser(2);
            whoIsLogin();
        }
        else if (choice1==3) {
            sign.setTheUser(3);
            whoIsLogin();

        }
        else{
            logger.log(Level.WARNING,"Invalid choice! Please enter a valid choice.");
        }
    }
    public  static  void whoIsLogin(){
        if(sign.getTheUser() == 1){
            String enterEmail = sign.enterEmail();
            String enterPassword = sign.enterPass();
            sign.AdminLogin(enterEmail,enterPassword);
            if (sign.adminloged)
                adminMenu(sign.getAdminMenuName());
        }

        else if (sign.getTheUser() == 2) {
            String enterEmail = sign.enterEmail();
            String enterPassword = sign.enterPass();
            sign.customerIslLogin(enterEmail,enterPassword);
            if(sign.customerLogin) {
                Customer_menu(sign.customerName);
                customer.setTheCustomerIs(sign.getNumberOfLine());
            }
        }   else if (sign.getTheUser() == 3) {
            String enterEmail = sign.enterEmail();
            String enterPassword = sign.enterPass();
            sign.installerIsLogin(enterEmail,enterPassword);
            if(sign.installerLogin)
                installer.installer_menu(sign.installerName);
        }

    }
     public static void menuProduct() {
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
                |     6. Home                     |
                ----------------------------------
                """;
        logger.info( enter);
        logger.info(Product.getString() + Product.getString1());

        choice = scanner.nextInt();
        if(choice==1)
        {
            product.printAllCategory();
            logger.log(Level.INFO,"Enter The name of category");
            String nameCato= Product.getTheNameOfCat(scanner);
            product.ifCategoryExist(nameCato);
            if (product.isCategoryExistFlag()){
                product.printAllProductAndCategories(nameCato);
                back();
            }
            else {
                logger.log(Level.WARNING, Product.CATEGORY_NOT_EXIST_WARNING +"\n");
                menuProduct();
            }

        }
        else if (choice == 2) {
            product.addNewProducts();
            menuProduct();
        } else if (choice == 3) {
            product.editProducts();
            menuProduct();
        } else if (choice == 4) {
           product.deleteProducts();
            menuProduct();
        }

        else if (choice == 5) {
            searchMenu();
            menuProduct();
        } else if (choice==6) adminMenu(admin.getAdminName());

            else {
            logger.log(Level.INFO, "Invalid choice! Please enter a valid choice.");
            menuProduct();
        }
    }
    public static void searchMenuPrint(String catName){
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
        logger.info(Product.getString() + Product.getString2());

        choice = scanner.nextInt();
        if(choice==1)
        {
            logger.info( "Enter The id to search: ");
            int productID = scanner.nextInt();
            product.ifProductIdExist(catName, String.valueOf(productID));
            product.extractedSearchById(catName, productID);
        }
        else if (choice==2) {
            logger.info( "Enter The name to search: ");
            String productName = scanner2.nextLine();
            product.ifProductNameExist(catName,productName);
            product.extractedSearchByName(catName,productName);
        }
        else if (choice==3) {
            logger.info( "Enter The description to search: ");
            String productDescription = scanner2.nextLine();
            product.ifProductDescriptionsExist(catName,productDescription);
            product.extractedSerachByDescription(catName,productDescription);
        }
        else if (choice==4) {
            logger.info( "Enter The (available/not available) to search: ");
            String productAvailable = scanner2.nextLine();
            product.ifProductAvailabilityExist(catName,productAvailable);
            product.extractedSearchByAvailability(catName,productAvailable);
        }

        else {
            logger.log(Level.INFO,"Invalid choice! Please enter a valid choice.");
            menuProduct();
        }
    }
    public static void searchMenu(){
        Scanner scanner = new Scanner(System.in);
        product.printAllCategory();

        logger.log(Level.INFO, "\u001B[35m What is the category of product?");
        product.categoryName = scanner.nextLine();
        product.ifCategoryExist(product.categoryName);
        if(product.isCategoryExistFlag()){
            searchMenuPrint(product.categoryName);
        }
        else
            logger.log(Level.WARNING, Product.CATEGORY_NOT_EXIST_WARNING +"\n");

    }
    public static void Customer_menu (String CostName) {
        customer.setUserName(CostName);
        customer.setBrowseProductsFlag(false);
        customer.setViewOrdersFlag(false);
        customer.setMakePurchasesFlag(false);
        customer.setInstallationServices(false);
        customer.setSettingFlag(false);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n\u001B[32m" + " -------  Welcome " +  CostName  + " ---------"+"\n"+
                "|                                  |\n" +
                "|      1.Browse products           |\n"+
                "|      2.make purchases            |\n"+
                "|      3.view orders               |\n"+
                "|      4.Installation Services     |\n"+
                "|      5.Setting                   |\n"+
                "|      6.logout                    |\n"+
                "|                                  |\n"+
                "-----------------------------------\n");
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
        choice = scanner.nextInt();
        if (choice == 1) {
            customer.browseProductsFlag = true;
            userAccountMenuCustomer();
        } else if (choice == 2) {
            customer.makePurchasesFlag = true;
            userAccountMenuCustomer();

        } else if (choice == 3) {
            customer.viewOrdersFlag = true;
            userAccountMenuCustomer();

        }
        else if (choice == 4) {
            customer.installationServices = true;
            userAccountMenuCustomer();

        }
        else if (choice == 5) {
            customer.settingFlag = true;
            settingMenuCustomer();

        }
        else if (choice == 6) menu();
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }
    public static void userAccountMenuCustomer(){
        if (customer.browseProductsFlag){
            BrowseProductsMenu();
        }
        else if (customer.makePurchasesFlag) {
            customer.searchTheCustomer();
            order.makePurchasesMenu();
        }
        else if (customer.viewOrdersFlag) {
            customer.searchTheCustomer();
            order.manageOrderMenu();
        }
        else if (customer.isInstallationServices()) {
            customer.searchTheCustomer();
            installer.installerServicesMenu();
        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }
    public static void settingMenuCustomer() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        logger.log(Level.INFO, """

                \u001B[36m------- Customer Profile -------
                |                             |
                |     1. edit Password        |
                |     2. edit Gmail           |
                |     3. edit PhoneNumber     |
                |     4. edit Address         |
                |     5. back                 |
                |                             |
                ------------------------------
                """);
        logger.log(Level.INFO,"Enter your choice: ");
        choice = scanner.nextInt();
        String choice2 ;
        String oldPass ;
        String newPass ;
        String newPassCon ;


        if (choice ==1) {
            logger.log(Level.INFO,"Enter The old password:");
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO,"Enter The new password:");
            newPass = scanner1.nextLine();
            logger.log(Level.INFO,"Confirm The  password:");
            newPassCon = scanner1.nextLine();
            customer.editePassword(oldPass,newPass,newPassCon);
            settingMenuCustomer();
        }
        else if (choice ==2) {
            logger.log(Level.INFO,"Enter The new Gmail:");
            choice2 = scanner1.nextLine();
            customer.editeGmail(choice2);
            logger.log(Level.INFO,"The Gmail has been changed successfully"+"\u001B[0m");
            settingMenuCustomer();

        }
        else if (choice==3){
            logger.log(Level.INFO,"Enter The new Phone Number:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
            customer.editeNumber(choice2);
            logger.log(Level.INFO,"The Phone Number has been changed successfully"+"\u001B[0m");
            settingMenuCustomer();

        }
        else if (choice==4){
            logger.log(Level.INFO,"Enter The new Address:"+"\u001B[0m");
            choice2 = scanner1.nextLine();
            customer.editeAddress(choice2);
            logger.log(Level.INFO,"The Address has been changed successfully"+"\u001B[0m");
            settingMenuCustomer();
        }
        else if (choice==5){
            Customer_menu (customer.getUserName());
        }
        else {
            logger.log(Level.WARNING,"\u001B[1m"+"\u001B[31mInvalid choice! Please enter a valid choice."+"\u001B[0m");
        }
    }
    public static void BrowseProductsMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """
            
            \u001B[35m---------------------------------
            |                                    |
            |      1. Show all products          |
            |      2. Search products            |
            |                                    |\s
            --------------------------------------
            """);
        logger.log(Level.INFO,"Enter your choice: ");
        choice = scanner.nextInt();
        if (choice == 1) {
            Scanner scanner2 = new Scanner(System.in);
            logger.log(Level.INFO,"Enter The name of category");
            customer.ShowTheProduct(scanner2.next());
            product.printAllCategory();}
        else if (choice == 2) {searchMenu(); menuProduct();}
        else {logger.log(Level.INFO, "\u001B[1m" + "\u001B[31" + "Invalid choice! Please enter a valid choice.\u001B[0m");}}

    public static void menuCustomerAdmin() {
        logger.log(Level.INFO, """

                \u001B[33m----- Manage Customer Accounts -----
                |                                    |
                |     1. View customer accounts.     |
                |     2. Add customer Account.       |
                |     3. Delete customer Account.    |
                |                                    |
                -------------------------------------
                """);
        logger.log(Level.INFO,"Enter your choice: "+"\u001B[0m");
    }

    public static void enterData() {
        Scanner scanner4 = new Scanner(System.in);
        int choice;
        logger.log(Level.INFO,"Enter The Number Of Order: ");
        order.setOrderNumber(Long.parseLong(scanner4.next()));
        order.ifEnterOrderExitToChangeSt(order.getOrderNumber());

        if(order.isIfOrderExist()){
            order.searchAboutCustomer("orderToAdmin",order.getOrderNumber());
            order.searchAboutGmail();
            enter= """
            
            \u001B[35m-------------------------------
            |                              |
            |        1. shipped            |
            |        2. delivered          |
            |        3. canceled           |
            |                              |
            --------------------------------
            """;
            logger.log(Level.INFO,enter);
            logger.log(Level.INFO, Admin.getMsg);

            choice = scanner4.nextInt();
            if (choice==1) {
                order.setStatusOrder("shipped");

            } else if (choice == 2) {
                order.setStatusOrder(Admin.getDelivered);
            } else if (choice == 3) {
                order.setStatusOrder("canceled");

            } else {
                logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
                //menuOrderCustomer();
            }

            order.editTheOrder(order.getStatusOrder());
            //menuOrderCustomer();
        }

        else{
            logger.log(Level.WARNING,"The order Not Found");
            // menuOrderCustomer();
        }
    }
    public static void editAdminProfile() {
        while (true) {
            int choice;
            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);
            enter = """

                    \u001B[36m----- Admin Profile -----
                    |   1. edit userName   |
                    |   2. edit Password   |
                    |   3. edit Gmail      |
                    -----------------------
                    """;
            logger.log(Level.INFO, enter);
            logger.log(Level.INFO, Admin.getMsg);
            choice = scanner.nextInt();
            String choice2, oldPass, newPass, newPassCon;
            if (choice == 1) {
                logger.log(Level.INFO, "Enter The new user Name:");
                choice2 = scanner1.nextLine();
                admin.editeUserName(choice2);
                logger.log(Level.INFO, "The user name has been changed successfully");
                continue;
            } else if (choice == 2) {
                logger.log(Level.INFO, "Enter The old password:");
                oldPass = scanner1.nextLine();
                logger.log(Level.INFO, "Enter The new password:");
                newPass = scanner1.nextLine();
                logger.log(Level.INFO, "Confirm The  password:");
                newPassCon = scanner1.nextLine();
                admin.editePassword(oldPass, newPass, newPassCon);
                continue;

            } else if (choice == 3) {
                logger.log(Level.INFO, "Enter The new Gmail:");
                choice2 = scanner1.nextLine();
                admin.editeGmail(choice2);
                logger.log(Level.INFO, "The Gmail has been changed successfully");
                continue;

            } else {
                logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
            }
            return;
        }
    }

    public static void main(String[] args) {
        menu();
    }}
