package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;

import static org.example.Installer.ENTER_CHOICE_MESSAGE;
import static org.example.Installer.RESET_COLOR;
import static org.example.Registration.logger;



public class Main {
    public static final String[] ARRAY_OF_TOPIC =  {"Installation confirmation", "installer not available", "Cancel Installation Request","Task finished"}; // Creating an array that can hold 3 strings
    public static final String[] ARRAY_OF_MSG = {"The Installation has been confirmed","We are sorry, but the Installation Request has been canceled due to logistical restrictions beyond our store's control","Thank you for using our company"}; // Creating an array that can hold 3 strings
    public static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    public static final String ENTER_THE_NAME_OF_CATEGORY = "Enter The name of category";
    public static final String INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE = "Invalid choice! Please enter a valid choice.\n";
    public static final String ENTER_THE_OLD_PASSWORD = "Enter The old password:";
    public static final String ENTER_THE_NEW_PASSWORD = "Enter The new password:";
    public static final String CONFIRM_THE_PASSWORD = "Confirm The  password:";
    public static final String THE_REGISTRATION_PROCESS_WAS_COMPLETED_SUCCESSFULLY = "The registration process was completed successfully\n";
    public static final String THE_GMAIL_HAS_BEEN_CHANGED_SUCCESSFULLY = "The Gmail has been changed successfully";
    public static final String ENTER_THE_NEW_GMAIL = "Enter The new Gmail:";
    public static final String PENDING = "pending";
    public static final String SCHEDULED = "scheduled";
    public static final String REQUEST_INSTALLATION = "requestInstallation";
    static Scanner  scanner = new Scanner(System.in);
    public static final String WRONG_DATA = "Invalid choice! Please enter a valid choice.";
    public static final String ENTER_THE_NUMBER_OF_ORDER = "Enter The Number Of Order: ";
    static Category category=new Category();
    static Gmail  gmailSend = new Gmail();
    static Registration ob=new Registration();
    static Admin admin =new Admin();
    static Product product= new Product();
    static Installer installer = new Installer();
    static Order order = new Order();
    static Customer customer = new Customer();
    static SignIn sign = new SignIn();
    public static void menu() {
        int choice;
        logger.log(Level.INFO, """
            
            \u001B[35m------ Welcome to Home Page ------
            |                                |
            |          1. Sign Up            |
            |          2. Login              |
            |          3. Exit               |
            |                                |
            ----------------------------------
            """);
        logger.log(Level.INFO, ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        if (choice == 1) {signupMenu(); menu();
        } else if (choice == 2) {
            loginMenu ();
            menu();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            menu();
        }

    }
    public static void signupMenu(){
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
            returnEnterEmail();

        }
        else if(!ob.truepass(ob.getPassword(),ob.getComPassword())){
          returnEnterPass(ob);
        }
        else {
            String dD =ob.getName()+"," +ob.getEmail() + "," + ob.getPassword() + "," + ob.getAddress() + "," + ob.getId()+","+ob.getPhone()+"\n";
            ob.storeDataToFile(dD);
            logger.log(Level.INFO, "The registration process was completed successfully");

        }
    }
    public static void menuCategory() {
        int choice;
        logger.log(Level.INFO, """

                \u001B[34m----------------------------------
                |                                 |
                |     1.View all categories       |
                |     2.add new category          |
                |     3.edit category             |
                |     4.delete category           |
                |     5. Home Page                |
                |                                 |
                ----------------------------------
                """);
        logger.log(Level.INFO,ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        if (choice == 1) {
            category.printAllCategory(); back();}
        else if (choice == 2) {
            logger.log(Level.INFO, ENTER_THE_NAME_OF_CATEGORY);
            String names = Category.getTheNameOfCat(scanner); category.addNewCategory(names);
            category.extractedAddNewCat(names);back();}
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
            logger.log(Level.INFO,ENTER_THE_NAME_OF_CATEGORY);
            String names = Category.getTheNameOfCat(scanner);
            category.deleteTheCategory(names);
            category.extractedDeleteCat(names);
            back();
        }
        else if (choice==5) menu();

        else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            menuCategory();
        }

    }
    public static void back(){
        logger.log(Level.INFO, """

                1) back\s
                2) Exit""");
        int choice;
        choice = scanner.nextInt();
        if(choice==1)  menuCategory();
        else System.exit(0); }

    public static  void userAccountMenu(){
        if (admin.isProductsFlag()) menuProduct();
        else if (admin.isCategoriesFlag()) menuCategory();
        else if (admin.isUserAccountsFlag()) menuManageAccountUserAdmin();
        else if (admin.orderCustomerFlag) menuOrderCustomer();
        else if (admin.installationRequestsFlag) installer_menu(admin.getAdminName());
    }
    static String enter;
    public static void menuOrderCustomer(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        enter="""

                \u001B[34m----- Manage Order Customer -----
                |     1. Show All Order         |
                |     2. edit Customer Order    |
                |     3. back                   |
                ---------------------------------n""";
        logger.log(Level.INFO, enter);
        logger.log(Level.INFO, Admin.getMsg);
        choice = scanner.nextInt();
        if (choice == 1) { order.viewAllOrderToAdmin(); menuOrderCustomer();}
        else if (choice ==2) {
            enterData(); menuOrderCustomer();}
        else if (choice ==3) {adminMenu(admin.getAdminName());} else if (choice==4) {menu();}
        else {logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
            menuOrderCustomer();}
    }

    public static void menuManageAccountUserAdmin(){
        int choice;
        enter="""

                \u001B[34m------- Manage user -------
                |     1. Admin            |
                |     2. Customer         |
                |     3. Installer        |
                |     4. Back             |
                ---------------------------
                """;
        logger.log(Level.INFO, enter);
        logger.log(Level.INFO, Admin.getMsg);
        choice = scanner.nextInt();
        if (choice == 1) {
            editAdminProfile();
        }
        else if (choice ==2) {
           menuCustomerAdmin();
        }
        else if (choice ==3) {
            menuInstallerAdmin();
        }
        else if (choice ==4) {
            adminMenu(admin.getAdminName());
        }
        else {
            logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
            menuManageAccountUserAdmin();
        }

    }
    public static void adminMenu(String adminName) {
        admin.setProductsFlag(false);
        admin.setCategoriesFlag(false);
        admin.setUserAccountsFlag(false);
        admin.setOrderCustomerFlag(false);
        admin.setInstallationRequestsFlag(false);
        admin.setAdminName(adminName);

        String menu = "\n----------  Welcome " + adminName + " -------\n" +
                "|    1. Manage products                  |\n" +
                "|    2. Manage categories                |\n" +
                "|    3. Manage user accounts.            |\n" +
                "|    4. Manage order Customer.           |\n" +
                "|    5. Manage Installation Requests     |\n" +
                "|    6. Home                             |\n" +
                "------------------------------------------\n";

        logger.log(Level.INFO, menu);
        logger.log(Level.INFO, Admin.getMsg);

        int choice;
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> admin.productsFlag = true;
            case 2 -> admin.categoriesFlag = true;
            case 3 -> admin.userAccountsFlag = true;
            case 4 -> admin.orderCustomerFlag = true;
            case 5 -> admin.installationRequestsFlag = true;
            case 6 ->menu();
            default -> {
                logger.log(Level.WARNING,Admin.INVALID_CHOICE_MESSAGE);
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
                |          4. Home               |
                ----------------------------------
                """);
        logger.log(Level.INFO,ENTER_YOUR_CHOICE);
        choice1 = scanner1.nextInt();
        if (choice1 == 1){ sign.setTheUser(1); whoIsLogin();}
        else if (choice1 ==2){sign.setTheUser(2);whoIsLogin();}
        else if (choice1==3) {sign.setTheUser(3);whoIsLogin();}
        else if (choice1==4) menu();
        else{logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);}
    }

    public  static  void whoIsLogin(){
        if(sign.getTheUser() == 1){
            String enterEmail = enterEmailS();
            String enterPassword = enterPassS();
            sign.adminLogin(enterEmail,enterPassword);
            if (sign.adminloged)
                adminMenu(sign.getAdminMenuName());
        }

        else if (sign.getTheUser() == 2) {
            String enterEmail = enterEmailS();
            String enterPassword = enterPassS();
            sign.customerIslLogin(enterEmail,enterPassword);
            if(sign.customerLogin) {
                customerMenu(sign.customerName);
                customer.setTheCustomerIs(sign.getNumberOfLine());
            }
        }   else if (sign.getTheUser() == 3) {
            String enterEmail = enterEmailS();
            String enterPassword = enterPassS();
            sign.installerIsLogin(enterEmail,enterPassword);
            if(sign.installerLogin)
                installer_menu(sign.installerName);
        }

    }
     public static void menuProduct() {
        int choice;
        enter ="""

                \u001B[33m----------------------------------
                |                                 |
                |     1. Show all products        |           \s
                |     2. add products             |
                |     3. edit products            |
                |     4. delete products          |
                |     5. Search products          |
                |     6. back                     |
                |                                 |
                ----------------------------------
                """;
        logger.info( enter);
        logger.info(Product.getString() + Product.getString1());

        choice = scanner.nextInt();
        if(choice==1)
        {
            product.printAllCategory();
            logger.log(Level.INFO,ENTER_THE_NAME_OF_CATEGORY);
            String nameCato= Product.getTheNameOfCat(scanner);
            product.ifCategoryExist(nameCato);
            if (product.isCategoryExistFlag()){
                product.printAllProductAndCategories(nameCato);
                menuProduct();
            }
            else {
                logger.log(Level.WARNING, Product.CATEGORY_NOT_EXIST_WARNING +"\n");
                menuProduct();
            }

        }
        else if (choice == 2) {
            product.newAddProductMenu();
            menuProduct();
        } else if (choice == 3) {
            product.editProductsMenu();
            menuProduct();
        } else if (choice == 4) {
           product.deleteProductsMenu();
            menuProduct();
        }

        else if (choice == 5) {
            searchMenu();
            menuProduct();
        } else if (choice==6) adminMenu(admin.getAdminName());

            else {
            logger.log(Level.INFO, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            menuProduct();
        }
    }
    public static void searchMenuPrint(String catName){
        int choice;
        Scanner scanner2 = new Scanner(System.in);
        enter= """

                \u001B[33m----------------------------------
                |                                 |
                |     1. ID                       |           \s
                |     2. name of product          |
                |     3. descriptions of product  |
                |     4. availability             |
                |     5. back                     |
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
        else if (choice==5) adminMenu(admin.getAdminName());

        else {
            logger.log(Level.INFO,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            menuProduct();
        }
    }
    public static void searchMenu(){
        Scanner scanner = new Scanner(System.in);
        product.printAllCategory();

        logger.log(Level.INFO, "What is the category of product?");
        product.categoryName = scanner.nextLine();
        product.ifCategoryExist(product.categoryName);
        if(product.isCategoryExistFlag()){
            searchMenuPrint(product.categoryName);
        }
        else
            logger.log(Level.WARNING, Product.CATEGORY_NOT_EXIST_WARNING +"\n");

    }
    public static void customerMenu(String costName) {
        customer.setUserName(costName);
        customer.setBrowseProductsFlag(false);
        customer.setViewOrdersFlag(false);
        customer.setMakePurchasesFlag(false);
        customer.setInstallationServices(false);
        customer.setSettingFlag(false);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"\n -------  Welcome " +  costName  + " ---------\n"+
                "|      1.Browse products           |\n"+
                "|      2.make purchases            |\n"+
                "|      3.view orders               |\n"+
                "|      4.Installation Services     |\n"+
                "|      5.Setting                   |\n"+
                "|      6.logout                    |\n"+
                "-----------------------------------\n");
        logger.log(Level.INFO,ENTER_YOUR_CHOICE);
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
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }
    }
    public static void userAccountMenuCustomer(){
        if (customer.browseProductsFlag){
            browseProductsMenu();
        }
        else if (customer.makePurchasesFlag) {
            customer.searchTheCustomer();
            order.makePurchasesMenu();
            order.makePurchasesMenu1();
            customerMenu(sign.customerName);
        }
        else if (customer.viewOrdersFlag) {
            customer.searchTheCustomer();
            manageOrderMenu();


        }
        else if (customer.isInstallationServices()) {
            customer.searchTheCustomer();
            installerServicesMenu();

        }
        else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }
    }
    public static void settingMenuCustomer() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        logger.log(Level.INFO, """

                \u001B[36m------- Customer Profile -------
                |     1. edit Password        |
                |     2. edit Gmail           |
                |     3. edit PhoneNumber     |
                |     4. edit Address         |
                |     5. back                 |
                ------------------------------
                """);
        logger.log(Level.INFO,ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        String choice2 ;
        String oldPass ;
        String newPass ;
        String newPassCon ;


        if (choice ==1) {
            logger.log(Level.INFO, ENTER_THE_OLD_PASSWORD);
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO, ENTER_THE_NEW_PASSWORD);
            newPass = scanner1.nextLine();
            logger.log(Level.INFO, CONFIRM_THE_PASSWORD);
            newPassCon = scanner1.nextLine();
            customer.editePassword(oldPass,newPass,newPassCon);
            settingMenuCustomer();
        }
        else if (choice ==2) {
            logger.log(Level.INFO, ENTER_THE_NEW_GMAIL);
            choice2 = scanner1.nextLine();
            customer.editeGmail(choice2);
            logger.log(Level.INFO, THE_GMAIL_HAS_BEEN_CHANGED_SUCCESSFULLY);
            settingMenuCustomer();

        }
        else if (choice==3){
            logger.log(Level.INFO,"Enter The new Phone Number:");
            choice2 = scanner1.nextLine();
            customer.editeNumber(choice2);
            logger.log(Level.INFO,"The Phone Number has been changed successfully");
            settingMenuCustomer();

        }
        else if (choice==4){
            logger.log(Level.INFO,"Enter The new Address:");
            choice2 = scanner1.nextLine();
            customer.editeAddress(choice2);
            logger.log(Level.INFO,"The Address has been changed successfully");
            settingMenuCustomer();
        }
        else if (choice==5){
            customerMenu(customer.getUserName());
        }
        else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }
    }
    public static void browseProductsMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """
            
            \u001B[35m---------------------------------
            |      1. Show all products          |
            |      2. Search products            |
            |      3. Back                       |\s
            --------------------------------------
            """);
        logger.log(Level.INFO,ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        if (choice == 1) {
            product.printAllCategory();
            logger.log(Level.INFO,ENTER_THE_NAME_OF_CATEGORY);
            Scanner scanner2 = new Scanner(System.in);
            customer.ShowTheProduct(scanner2.next());
            browseProductsMenu();
        }
        else if (choice == 2) {searchMenu();
            browseProductsMenu();
        } else if (choice==3) {
            customerMenu(sign.customerName);
        } else {
            logger.log(Level.INFO, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            browseProductsMenu();

        }
    }

    public static void menuCustomerAdmin() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """

                \u001B[33m----- Manage Customer Accounts -----
                |                                    |
                |     1. View customer accounts.     |
                |     2. Add customer Account.       |
                |     3. Delete customer Account.    |
                |     4. back                        |
                |                                    |
                -------------------------------------
                """);
        logger.log(Level.INFO, ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        if (choice == 4) {
            menuManageAccountUserAdmin();
        }
        else
        {logger.log(Level.INFO, WRONG_DATA);}

}
    public static void enterData() {
        Scanner scanner4 = new Scanner(System.in);
        int choice;
        logger.log(Level.INFO,ENTER_THE_NUMBER_OF_ORDER);
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
            }

            order.editTheOrder();
        }

        else{
            logger.log(Level.WARNING,"The order Not Found");
        }
    }
    public static void editAdminProfile() {
            int choice;
            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);
            enter = """

                    \u001B[36m----- Admin Profile -----
                    |   1. edit userName   |
                    |   2. edit Password   |
                    |   3. edit Gmail      |
                    |   4. back            |
                    -----------------------
                    """;
            logger.log(Level.INFO, enter);
            logger.log(Level.INFO, Admin.getMsg);
            choice = scanner.nextInt();
            String choice2;
            String oldPass;
            String newPass;
            String newPassCon;
            if (choice == 1) {
                logger.log(Level.INFO, "Enter The new user Name:");
                choice2 = scanner1.nextLine();
                admin.editeUserName(choice2);
                logger.log(Level.INFO, "The user name has been changed successfully");
                editAdminProfile();
            } else if (choice == 2) {
                logger.log(Level.INFO, ENTER_THE_OLD_PASSWORD);
                oldPass = scanner1.nextLine();
                logger.log(Level.INFO, ENTER_THE_NEW_PASSWORD);
                newPass = scanner1.nextLine();
                logger.log(Level.INFO, CONFIRM_THE_PASSWORD);
                newPassCon = scanner1.nextLine();
                admin.editePassword(oldPass, newPass, newPassCon);
                editAdminProfile();
            } else if (choice == 3) {
                logger.log(Level.INFO, ENTER_THE_NEW_GMAIL);
                choice2 = scanner1.nextLine();
                admin.editeGmail(choice2);
                logger.log(Level.INFO, THE_GMAIL_HAS_BEEN_CHANGED_SUCCESSFULLY);
                editAdminProfile();
            } else if (choice==4) {
                menuManageAccountUserAdmin();
            } else {
                logger.log(Level.WARNING, Admin.INVALID_CHOICE_MESSAGE);
                editAdminProfile();
            }
        }

    public static void scheduleAppointmentMenu(){
        installer.setCompletionDate("--");
        int choice;
            logger.log(Level.INFO, """

                    \u001B[36m--------------- appointment scheduling ---------------
                    |                                                           |
                    |     1. Approval of the request and sending an email.      |
                    |     2. The installer is not available.                    |
                    |     3. Cancelling installation requests.                  |
                    |     4. Completion of the request.                         |
                    |     5. View scheduled and incomplete requests.            |
                    |     6. Back                                               |
                    -------------------------------------------------------------
                    """);
            logger.log(Level.INFO, ENTER_CHOICE_MESSAGE);
            choice = scanner.nextInt();

        if (choice == 1) {
            installer.showAllInstallationRequestToAdminANDInstaller(PENDING);
            enterID2();
            approvalOfTheRequestFromAdminOrInstaller(installer.getIdInstallerRequest());
            logger.log(Level.INFO, "The installation requests has been confirmed successfully");
            scheduleAppointmentMenu();
        } else if (choice == 2) {
            installer.showAllInstallationRequestToAdminANDInstaller(PENDING);
            enterID2();
            installer.setDAta(installer.getIdInstallerRequest(),PENDING);
            sendEmailNewDayHour();
            scheduleAppointmentMenu();
        }
        else if (choice == 3) {
            installer.showAllInstallationRequestToAdminANDInstaller(PENDING);
            enterID2();
            installer.setDAta(installer.getIdInstallerRequest(),PENDING);
            order.deleteOrder2(REQUEST_INSTALLATION,installer.getNumberOfLine());
            gmailSend.sendEmail(installer.getGmail(), ARRAY_OF_TOPIC[2], ARRAY_OF_MSG[1]);
            logger.log(Level.INFO, Installer.BOLD + "Cancelling installation requests successfully");
            scheduleAppointmentMenu();
        }
        else if (choice ==4) {
            installer.showAllInstallationRequestToAdminANDInstaller(SCHEDULED);
            enterID2();

            installer.setDAta(installer.idInstallerRequest,SCHEDULED);
            logger.log(Level.WARNING, Installer.BOLD + "Did you accomplish this task?");
            if(yesOrNo()==1){
                order.deleteOrder2(REQUEST_INSTALLATION,installer.getNumberOfLine());
                installer.setStatusInstalling("completed");
                installer.setCompletionDate(installer.getPreferredDate());
                installer.addThisInstallerRequest();
                gmailSend.sendEmail(installer.getGmail(), ARRAY_OF_TOPIC[3], ARRAY_OF_MSG[2]);
                installer.setCompletionDate("--");
                scheduleAppointmentMenu();
            }
            else {
                scheduleAppointmentMenu();
            }
        }
        else if (choice == 5) {
            installer.showAllInstallationRequestToAdminANDInstaller(SCHEDULED);
            scheduleAppointmentMenu();
        } else if (choice==6) {
            installer_menu(sign.installerName);

        } else {
            logger.log(Level.WARNING, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            scheduleAppointmentMenu();
        }
    }
     public static void enterID2() {
        long choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Enter The id Of installation requests: ");
        choice = scanner.nextLong();
        installer.setIdInstallerRequest(choice);
    }

    public static void sendEmailNewDayHour() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """
                \u001B[35m
                -------------------------
                |                                  |
                |     1. Send new day and hour.    |
                |     2. Change day.               |
                |     3. Change Hour.              |
                |     4. Back                      |
                |                                  |
                 ----------------------------------\s
                """);
        logger.log(Level.INFO, ENTER_CHOICE_MESSAGE);
        Scanner scanner1 = new Scanner(System.in);
        choice = scanner.nextInt();
        if (choice == 1) {
            String msg1 ;
            logger.log(Level.INFO,"Write a message to the customer about the new appointment ");
            msg1=scanner1.nextLine();
            gmailSend.sendEmail(installer.getGmail(), ARRAY_OF_TOPIC[1],msg1);
            logger.log(Level.INFO,"The email was sent successfully");
            sendEmailNewDayHour();

        } else if (choice == 2) {
            logger.log(Level.INFO,"Write a new day to schedule");
            installer.setPreferredDate(scanner1.nextLine());
            installer.putDay(installer.getPreferredDate());
            sendEmailNewDayHour();
        }
        else if (choice == 3) {
            logger.log(Level.INFO,"Write a new hour to schedule ");
            installer.setPreferredHour(scanner1.nextLine());
            installer.putTime(installer.getPreferredHour());
            sendEmailNewDayHour();
        } else if (choice==4) {
            scheduleAppointmentMenu();
        } else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            sendEmailNewDayHour();
        }
    }
    public static void installer_menu(String installerName) {
        installer.setInstallerName(installerName);
        installer.setViewRequestsFlag(false);
        installer.setScheduleAppointmentFlag(false);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"------- Welcome  " + installerName +"  ------\n"+
                "|     1.View history requests      |\n"+
                "|     2.schedule appointment.      |\n"+
                "|     3.Home                       |\n"+
                " ---------------------------------- \n");
        logger.log(Level.INFO,ENTER_YOUR_CHOICE);

        choice = scanner.nextInt();
        if (choice == 1) {
           installer.setViewRequestsFlag(true);
            userAccountMenuIns();
        } else if (choice == 2) {
            installer.setScheduleAppointmentFlag(true);
            userAccountMenuIns();
        } else if (choice==3) {
            menu();
        } else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            installer_menu(installerName);
        }

    }
    public static void userAccountMenuIns(){
        if (installer.isViewRequestsFlag()){
            installer.showAllInstallationRequestToAdminANDInstaller("completed");
            installer_menu(installer.getInstallerName());
        }
        else if (installer.isScheduleAppointmentFlag()) {
            scheduleAppointmentMenu();

        }

    }
    public static void approvalOfTheRequestFromAdminOrInstaller(long idInstallerRequest){
        installer.setDAta(idInstallerRequest,PENDING);
        logger.log(Level.WARNING, "Do you agree to this request? ");
        if(yesOrNo()==1){
            order.deleteOrder2(REQUEST_INSTALLATION,installer.getNumberOfLine());
            installer.setStatusInstalling(SCHEDULED);
            installer.addThisInstallerRequest();
            gmailSend.sendEmail(installer.getGmail(), ARRAY_OF_TOPIC[0], ARRAY_OF_MSG[0]);
        }
        else {
            scheduleAppointmentMenu();
        }

    }
    public static int yesOrNo (){
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
        logger.log(Level.INFO,ENTER_CHOICE_MESSAGE);
        choice = scanner.nextInt();
        return choice;
    }
    public static void menuInstallerAdmin() {
        installer.setCompletionDate("--");
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """

                \u001B[36m------- Manage Installer Accounts ------
                |                                      |
                |     1. View Installer accounts.      |
                |     2. Edit Installer Account.       |
                |     3. Back                          |
                |                                      |
                ----------------------------------------
                """);
        logger.log(Level.INFO,ENTER_CHOICE_MESSAGE+RESET_COLOR);
        choice = scanner.nextInt();
        if (choice == 1) {
            installer.showInstallerAccount();
            menuInstallerAdmin();
        } else if (choice == 2) {
            changeInstallerAccountMenu();
            menuInstallerAdmin();
        }
        else if (choice==3)   menuManageAccountUserAdmin();

        else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }
    }
    public static void changeInstallerAccountMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        logger.log(Level.INFO, """
                \u001B[36m
                ----- Admin Profile ------
                |    1. edit userName    |
                |    2. edit Password    |
                |    3. edit Gmail       |
                |    4. Back             |
                --------------------------
                """);
        logger.log(Level.INFO, ENTER_CHOICE_MESSAGE + RESET_COLOR);
        choice = scanner.nextInt();
        String choice2;
        String oldPass;
        String newPass;
        String newPassCon;
        if (choice == 1) {
            logger.log(Level.INFO, "Enter The new user Name:");
            choice2 = scanner1.nextLine();
            installer.editeUserName(choice2);
            logger.log(Level.INFO, "The user name has been changed successfully");
        } else if (choice == 2) {
            logger.log(Level.INFO, ENTER_THE_OLD_PASSWORD);
            oldPass = scanner1.nextLine();
            logger.log(Level.INFO, ENTER_THE_NEW_PASSWORD);
            newPass = scanner1.nextLine();
            logger.log(Level.INFO, CONFIRM_THE_PASSWORD);
            newPassCon = scanner1.nextLine();
            installer.editePassword(oldPass, newPass, newPassCon);
        } else if (choice == 3) {
            logger.log(Level.INFO, ENTER_THE_NEW_GMAIL);
            choice2 = scanner1.nextLine();
            installer.editeGmail(choice2);
            logger.log(Level.INFO,THE_GMAIL_HAS_BEEN_CHANGED_SUCCESSFULLY);
        } else if (choice==4) {
            menuInstallerAdmin();
        } else {
            logger.log(Level.WARNING, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }
    }
    public static void installerServicesMenu() {
        installer.setCompletionDate("--");
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, """

                \u001B[36m----------- Installation Services ----------
                |                                                     |
                |     1. Request an installation service              |
                |     2. View installation requests history.          |
                |     3. Cancel pending installation requests         |
                |     4. Home                                         |
                ------------------------------------------------------
                """);
        logger.log(Level.INFO,ENTER_CHOICE_MESSAGE+RESET_COLOR);
        choice = scanner.nextInt();
        if (choice == 1) {
            enterDataOfRequest();
            installerServicesMenu();
        } else if (choice == 2) {
            installer.showAllInstallationRequest();
            installerServicesMenu();
        }
        else if (choice == 3) {
            installer.showAllInstallationRequestPending();
            logger.log(Level.INFO,"Enter the installation requests ID To Cancel it : ");
            installer.setIdInstallerRequest(scanner.nextLong());
            installer.ifExitIdInstallerRequestPending();
            cancelIt();
            installerServicesMenu();
        } else if (choice==4) {
            customerMenu(sign.customerName);
        } else {
            logger.log(Level.WARNING,INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }
    }

    public static void cancelIt() {
        if(installer.isIdInstallationFlag()){
            order.deleteOrder2(REQUEST_INSTALLATION,installer.getNumberOfLine());
            logger.log(Level.WARNING,"The installation requests canceled.");

        }
        else {
            logger.log(Level.WARNING,"The installation requests ID Not Found.");
            installerServicesMenu();
        }
    }

    public static void manageOrderMenu() {
        order.setIfCustomerShowPendingOrder(false);
        order.setIfCustomerShowDeliveredOrder(false);
        order.show= """

                \u001B[32m ------------- <3 -------------
                |                                |
                |     1. Pending Order           |
                |     2. Delivered Order         |
                |     3. Back                    |
                |                                |
                ----------------------------------
                """;
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,order.show);
        logger.log(Level.INFO, Order.ENTER_YOUR_CHOICE );
        choice = scanner.nextInt();
        if (choice == 1) {
            order.viewPendingOrder(Order.pending,order.getIdCustomer());
            userAccountMenuOrder();

        } else if (choice == 2) {
            order.viewDeliveredOrder(order.delivered,order.getIdCustomer());
            userAccountMenuOrder();

        } else if (choice==3) {
            customerMenu(sign.customerName);
        } else {
            logger.log(Level.WARNING, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }

    }
    public static void userAccountMenuOrder() {
        if(order.isIfCustomerShowPendingOrder()){
            order.viewPendingOrder1(Order.pending,order.getIdCustomer());
            order.viewPendingOrder(Order.pending,order.getIdCustomer());
            pendingMenu();
        }
        else if (order.isIfCustomerShowDeliveredOrder()) {
            order.viewPendingOrder1(order.delivered,order.getIdCustomer());
            order.viewPendingOrder(order.delivered,order.getIdCustomer());
            deliveredMenu();
        }
    }
    public static void pendingMenu() {

        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        order.show= """

                \u001B[32m --------------- <3 ---------------
                |                                      |
                |     1. Show Pending Order Product    |
                |     2. edit Pending Product          |
                |     3. Delete Order                  |
                |     4. Back                          |
                |                                      |
                ---------------------------------------
                """;
        logger.log(Level.INFO,order.show);
        logger.log(Level.INFO, Order.ENTER_YOUR_CHOICE );
        choice = scanner.nextInt();
        if (choice == 1) {
            order.ifOrderExit(scanner);
            if(order.isIfOrderExist()){
                order.viewPendingOrderProduct(order.getIdCustomer(),order.getCustomerName(), String.valueOf(order.getOrderNumber()));
                pendingMenu();
            }
            else{
                logger.log(Level.WARNING, Order.M_THE_ORDER_NOT_FOUND);
                pendingMenu();
            }
        }
        else if (choice == 2) {
            order.ifOrderExit(scanner1);
            if(order.isIfOrderExist()){
                order.viewPendingOrderProduct(order.getIdCustomer(),order.getCustomerName(), String.valueOf(order.getOrderNumber()));
                extractedEdit(scanner1);
                pendingMenu();
            }

            else{
                logger.log(Level.WARNING, Order.M_THE_ORDER_NOT_FOUND);
                pendingMenu();
            }

        } else if (choice == 3) {
            order.ifOrderExit(scanner1);
            if(order.isIfOrderExist()) {
                String name = order.getCustomerName() + "-" + order.getIdCustomer();
                order.deleteOrder(name);
                logger.log(Level.INFO, "The Order is deleted Successfully");
                pendingMenu();
            }
            else{
                logger.log(Level.WARNING, Order.M_THE_ORDER_NOT_FOUND);
                pendingMenu();
            }
        } else if (choice==4) {
            manageOrderMenu();
        } else{
            logger.log(Level.WARNING, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
        }}
    public  static void extractedEdit(Scanner scanner) {

        logger.log(Level.INFO,"Enter The Product ID : ");
        order.setProductID(Integer.parseInt(scanner.next()));
        order.ifProductExitToEdit(order.getProductID(),order.getOrderNumber(),order.getCustomerName(),order.getIdCustomer());
        order.foundNumber2Line(order.getProductID(),order.getOrderNumber());
        if(order.isProductExitFlag()){
            editOrderMenu();

        }
        else{
            logger.log(Level.WARNING, "The Product Not Found");
            pendingMenu();
        }
    }
    public static void deliveredMenu() {
        order.show= """

                \u001B[32m ------------------ <3 -----------------
                |                                      |
                |    1. Show delivered Order Product   |
                |                                      |
                ---------------------------------------
                """;
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,order.show);
        logger.log(Level.INFO, Order.ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        if (choice == 1) {
            logger.log(Level.INFO,ENTER_THE_NUMBER_OF_ORDER);
            order.setOrderNumber(scanner.nextLong());
            order.ifOrderExitDelivered(String.valueOf(order.getOrderNumber()));
            if(order.isIfOrderExist()){
                order.viewDeliveredOrder(Order.getDelivered(),order.getIdCustomer());
                order.viewPendingOrderProduct(order.getIdCustomer(),order.getCustomerName(), String.valueOf(order.getOrderNumber()));

                manageOrderMenu();
            }

            else{
                logger.log(Level.WARNING, Order.M_THE_ORDER_NOT_FOUND);
                manageOrderMenu();
            }}

        else{
            logger.log(Level.WARNING, INVALID_CHOICE_PLEASE_ENTER_A_VALID_CHOICE);
            manageOrderMenu();
        }}
    public static void returnEnterEmail() {
        logger.log(Level.INFO,"Enter The Email:");
        Scanner scanner = new Scanner(System.in);
        ob.setEmail(scanner.next());
        ob.searchIfEmailIsAlreadyExist(ob.getEmail());
        if(ob.isIfEmailFound()){
            returnEnterEmail();
        }
        else if (!ob.truepass(ob.getPassword(),ob.getComPassword())) {
            returnEnterPass(ob);
        }
        else {
            String dD =ob.getName()+"," +ob.getEmail() + "," + ob.getPassword() + "," +  ob.getAddress()+ "," + ob.getId()+","+ob.getPhone()+"\n";
            ob.storeDataToFile(dD);
            logger.log(Level.INFO,THE_REGISTRATION_PROCESS_WAS_COMPLETED_SUCCESSFULLY);
            loginMenu();
        }
    }
    public static void returnEnterPass(Registration ob ){
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.WARNING,"The two passwords do not match!");
        logger.log(Level.INFO,"Enter your Password:");
        ob.setPassword(scanner.next());
        logger.log(Level.INFO,CONFIRM_THE_PASSWORD);
        ob.setComPassword(scanner.next());
        if(ob.truepass(ob.getPassword(), ob.getComPassword())){
            String dD =ob.getName()+"," +ob.getEmail() + "," + ob.getPassword() + "," + ob.getAddress() + "," + ob.getId()+","+ob.getPhone()+"\n";
            ob.storeDataToFile(dD);
            logger.log(Level.INFO, THE_REGISTRATION_PROCESS_WAS_COMPLETED_SUCCESSFULLY);
            loginMenu();
        }
        else {
            returnEnterPass(ob);
        }
    }
    public  static void  enterDataOfRequest(){
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Describe the product you need service for : ");
        installer.setProduct(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred date for this service like (1/1/2022) or (Any Date): "+RESET_COLOR);
        installer.setPreferredDate(scanner.nextLine());
        logger.log(Level.INFO,"Select the preferred Hour of this date like (10:00 AM) or (Any Time): "+RESET_COLOR);
        installer.setPreferredHour(scanner.nextLine());
        logger.log(Level.INFO,"Describe the installing location ( City-Street-Building Name-Floor number\"): "+RESET_COLOR);
        installer.setLocationInstalling(scanner.nextLine());
        installer.setStatusInstalling("pending");
        installer.randomNumberGenerator();
        installer.ifRandomNumberGeneratorNotFound();
        installer.dataTrueOrNO();
    }
    public static void editOrderMenu() {
        int productID = order.getProductID();

        int choice;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        order.show= """

                \u001B[32m --------------- <3 ---------------
                |     1. Modify the product quantity    |
                |     2. Delete The Product             |
                ----------------------------------------
                """;
        logger.log(Level.INFO,order.show);

        logger.log(Level.INFO, ENTER_YOUR_CHOICE);
        choice = scanner.nextInt();
        if(choice==1){
            order.viewPendingOrderProduct(order.getIdCustomer(),order.getCustomerName(), String.valueOf(order.getOrderNumber()));
            order.setProductID(productID);
            logger.log(Level.INFO,"\n\u001B[32mWhat is the new quantity?");
            int newQuantity = scanner2.nextInt();
            order.ifQuantitiesAllowed(String.valueOf(newQuantity));
            order.ifQuantitiesGraterThan(newQuantity);

        } else if (choice==2) {
            order.show= """
        
            \u001B[35m---------------------
            |                       |
            |      1. YES           |
            |      2. NO            |
            |                       |\s
            -------------------------
            """;
            order.viewPendingOrderProduct(order.getIdCustomer(),order.getCustomerName(), String.valueOf(order.getOrderNumber()));
            order.setProductID(productID);
            String name = order.getCustomerName()+"-"+order.getIdCustomer();
            if(order.ifFileOfCustomerOrderNoItem(order.getCustomerName())){
                logger.log(Level.INFO,"Do you want to cancel the order?");
                logger.log(Level.INFO,order.show);


                if (scanner2.nextInt()==1){
                    order.deleteOrder(name);
                    logger.log(Level.INFO, Order.getString2() + "\u001B[31m The Order is canceled Successfully");

                }
                else {
                    editOrderMenu();                }
            }
            else{
                logger.log(Level.INFO,"Do you want to delete the product?");
                logger.log(Level.INFO, """
            
            \u001B[35m---------------------
            |                       |
            |      1. YES           |
            |      2. NO            |
            -------------------------
            """);
                if(scanner2.nextInt()==1){
                    order.deleteThisProductFromCustomer(name,product.getNumberOfLine());
                    order.deleteThisProductFromCustomer("orderToAdmin",order.getNumberOfLine2());
                    order.deleteThisProductFromCustomer("orderAllProduct",order.getNumberOfLine3());
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = currentDate.format(formatter);
                    order.calculateTheTotalCost(order.getCustomerName(),order.getIdCustomer());
                    String product2 =order.getOrderNumber()+","+order.getCustomerName()+","+order.getIdCustomer()+","+order.getOrderPrice()+","+formattedDate+","+"--"+","+"pending"+"\n";
                    order.addNewOrderPending(product2);
                    logger.log(Level.INFO,"The product is deleted and the Total cost is :" +order.getOrderPrice());

                }
                else{
                    logger.log(Level.INFO,"The product Not deleted");
                }
            }
        }

        else{
            logger.log(Level.WARNING, Order.M_INVALID_VALUE);

        }

    }
    public static String enterEmailS(){scanner = new Scanner(System.in);logger.log(Level.INFO,"\u001B[32mEnter the email:" + "\u001B[0m");return scanner.nextLine();}
    public static String enterPassS(){scanner = new Scanner(System.in);logger.log(Level.INFO,"Enter the password:");return scanner.nextLine();}

    public static void main(String[] args) {
        menu();
    }}
