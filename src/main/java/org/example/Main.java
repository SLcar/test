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
        if (choice == 1) {
            signupMenu();
            menu();
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
        if (admin.isProductsFlag()) product.menuProduct();
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
        logger.log(Level.INFO, Admin.getMsg());
        choice = scanner.nextInt();
        if (choice == 1) {
            order.viewAllOrderToAdmin();
            menuOrderCustomer();
        }
        else if (choice ==2) {
            admin.enterData();
            menuOrderCustomer();
        }
        else if (choice ==3) {
            adminMenu(admin.getAdminName());}

        else {
            logger.log(Level.WARNING, Admin.COLOR_2 + Admin.INVALID_CHOICE_MESSAGE + Admin.COLOR);
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
        logger.log(Level.INFO, Admin.getMsg());
        choice = scanner.nextInt();
        if (choice == 1) {
            admin.editAdminProfile();
        }
        else if (choice ==2) {
            customer.menuCustomerAdmin();
        }
        else if (choice ==3) {
            installer.menuInstallerAdmin();
        }
        else if (choice ==4) {
            adminMenu(admin.getAdminName());
        }
        else {
            logger.log(Level.WARNING, Admin.COLOR_2 + Admin.INVALID_CHOICE_MESSAGE + Admin.COLOR);
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
                "------------------------------------------\n";

        logger.log(Level.INFO, menu);
        logger.log(Level.INFO, Admin.getMsg());

        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> admin.productsFlag = true;
            case 2 -> admin.categoriesFlag = true;
            case 3 -> admin.userAccountsFlag = true;
            case 4 -> admin.orderCustomerFlag = true;
            case 5 -> admin.installationRequestsFlag = true;
            default -> {
                logger.log(Level.WARNING, String.format("%s%s%s", Admin.COLOR_2, Admin.INVALID_CHOICE_MESSAGE, Admin.COLOR));
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
                customer.Customer_menu(sign.customerName);
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
    public static void main(String[] args) {
        menu();
    }}
